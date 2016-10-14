
package io.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Iterator;

import io.dao.common.DAOException;
import io.dao.common.DatabaseAccessException;
import io.dao.common.IDAO;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public abstract class JdbcDAO<T> implements IDAO<T> {

    private Connection connection;

    private PreparedStatement stmtSave;

    public JdbcDAO(Connection connection) {

        this.connection = connection;

    }

    @Override
    public void close()
        throws DAOException {

        this.connection = null;

    }

    protected abstract Iterator<T> doFindAll(long page, long count)
        throws SQLException;

    protected abstract void doRemove(int id)
        throws SQLException;

    protected abstract void doSave(T obj)
        throws SQLException;

    @Override
    public Iterable<T> findAll()
        throws DAOException {

        return this.findAll(0, Long.MAX_VALUE);

    }

    @Override
    public Iterable<T> findAll(long page, long count)
        throws DAOException {

        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {

                try {

                    return JdbcDAO.this.doFindAll(page, count);

                } catch (SQLException e) {

                    e.printStackTrace();

                    throw new DatabaseAccessException("Erro ao buscar", e);

                }

            }

        };

    }

    @Override
    public T findById(Object id)
        throws DAOException {

        return null;
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

    protected Iterable<T> iterable(final ResultSet rs) {

        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {

                return JdbcDAO.this.iterator(rs);

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

    @Override
    public void save(T obj)
        throws DAOException {

        try {

            this.doSave(obj);

        } catch (SQLException e) {

            e.printStackTrace();

            throw new DAOException("Falha ao salvar usuário", e);

        }

    }

}
