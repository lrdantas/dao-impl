
package io.dao.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Iterator;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class JdbcDAO<T> implements IDAO<T> {

    private Connection connection;

    private PreparedStatement stmtSave;

    public JdbcDAO(Connection connection) {

        this.connection = connection;

    }

    protected PreparedStatement bindStatement(String query, Object... args) {

        PreparedStatement stmt;

        try {

            stmt = this.getConnection().prepareStatement(query);

            for (int i = 0; i < args.length; i++) {

                int paramIndex = i + 1;

                Object arg = args[i];

                if (arg instanceof Integer) {

                    stmt.setInt(paramIndex, (Integer) arg);

                } else if (arg instanceof String) {

                    stmt.setString(paramIndex, (String) arg);

                } else if (arg instanceof Long) {

                    stmt.setLong(paramIndex, (Long) arg);

                }

            }


        } catch (SQLException e) {

            e.printStackTrace();

            throw new DatabaseAccessException(e.getMessage(), e);

        }

        return stmt;

    }

    @Override
    public void close()
        throws DAOException {

        this.connection = null;

    }

    protected abstract Iterator<T> doFindAll(long page, long count)
        throws SQLException, DAOException;

    protected abstract T doFindById(Object id)
        throws SQLException, DAOException;

    protected abstract void doRemove(int id)
        throws SQLException, DAOException;

    protected abstract void doSave(T obj)
        throws SQLException, DAOException;

    protected ResultSet executeFind(String query, Object...args) {

        PreparedStatement stmt = this.bindStatement(query, args);

        return this.executeQuery(stmt);

    }

    protected ResultSet executeQuery(PreparedStatement stmt) {

        ResultSet resultSet;

        try {

            resultSet = stmt.executeQuery();

        } catch (SQLException e) {

            e.printStackTrace();

            throw new DatabaseAccessException(e.getMessage(), e);
        }

        return resultSet;

    }

    @Override
    public Iterable<T> findAll()
        throws DAOException {

        return this.findAll(0, Long.MAX_VALUE);

    }

    @Override
    public Iterable<T> findAll(final long page, final long count)
        throws DAOException {

        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {

                try {

                    return JdbcDAO.this.doFindAll(page, count);

                } catch (SQLException e) {

                    e.printStackTrace();

                    throw new DatabaseAccessException("Erro ao buscar", e);

                } catch (DAOException e) {

                    e.printStackTrace();

                    throw new DatabaseAccessException("Erro ao buscar", e);

                }

            }

        };

    }

    @Override
    public T findById(Object id)
        throws DAOException {

        try {

            return this.doFindById(id);

        } catch (SQLException e) {

            e.printStackTrace();

            throw new DAOException("Erro ao buscar por id", e);

        }

    }

    protected Connection getConnection() {

        return this.connection;

    }

    protected PreparedStatement getStmtSave(String query)
        throws SQLException {

        if (this.stmtSave == null) {

            this.stmtSave = this.getConnection().prepareStatement(
                query,
                PreparedStatement.RETURN_GENERATED_KEYS);

        }

        return this.stmtSave;

    }

    protected Iterable<T> iterable(final ResultSet resultSet) {

        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {

                return JdbcDAO.this.iterator(resultSet);

            }

        };

    }

    protected abstract Iterator<T> iterator(ResultSet resultSet);

    protected PreparedStatement prepare(String query) {

        try {

            return this.getConnection().prepareStatement(query);

        } catch (SQLException e) {

            e.printStackTrace();

            throw new DatabaseAccessException(e.getMessage(), e);

        }

    }

    @Override
    public void removeById(Object id)
        throws DAOException {

        try {

            this.doRemove((Integer) id);

        } catch (SQLException e) {

            e.printStackTrace();

            throw new DAOException("Erro ao remover", e);

        }

    }

    private void restoreAutoCommit(boolean autoCommitValue) {

        try {

            this.getConnection().setAutoCommit(autoCommitValue);

        } catch (SQLException e) {

            e.printStackTrace();

            throw new DatabaseAccessException(e.getMessage(), e);

        }

    }

    private void rollback() {

        try {

            this.getConnection().rollback();

        } catch (SQLException e) {

            e.printStackTrace();

            throw new DatabaseAccessException(e.getMessage(), e);

        }

    }

    @Override
    public void save(T obj)
        throws DAOException {

        boolean autoCommitValue = true;

        try {

            autoCommitValue = this.getConnection().getAutoCommit();

            this.getConnection().setAutoCommit(false);

            this.doSave(obj);

            this.getConnection().commit();

        } catch (SQLException e) {

            e.printStackTrace();

            this.rollback();

            throw new DAOException("Falha ao salvar", e);

        } finally {

            this.restoreAutoCommit(autoCommitValue);

        }

    }

}
