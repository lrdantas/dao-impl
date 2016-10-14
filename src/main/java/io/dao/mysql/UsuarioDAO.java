
package io.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Iterator;

import io.dao.common.DAOException;
import io.dao.common.DatabaseAccessException;

import io.model.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class UsuarioDAO extends JdbcDAO<Usuario> {

    public UsuarioDAO(Connection connection) {

        super(connection);

    }

    @Override
    public void close()
        throws DAOException {

        super.close();

    }

    @Override
    protected Iterable<Usuario> doFindAll(long page, long count)
        throws SQLException {

        String query = ""
            + "SELECT id, nome "
            + "FROM usuario "
            + "LIMIT ?, ?";

        ResultSet rs;
        PreparedStatement stmt;

        try {

            stmt = this.getConnection().prepareStatement(query);

            stmt.setLong(1, page);
            stmt.setLong(2, count);

            rs = stmt.executeQuery();

        } catch (SQLException e) {

            e.printStackTrace();

            throw new DatabaseAccessException(e.getMessage(), e);
        }

        return super.iterable(rs);

    }

    @Override
    protected void doRemove(int id)
        throws SQLException {

        String query = "DELETE FROM usuario WHERE id = ?";

        PreparedStatement stmt;

        stmt = this.prepare(query);

        stmt.setLong(1, id);

        int affetectRows = stmt.executeUpdate();

        System.out.println(String.format(
            "Affected rows: %d",

            affetectRows));

    }

    @Override
    protected void doSave(Usuario obj)
        throws SQLException {

        String query = ""
            + "INSERT INTO usuario (id, nome) "
            + "VALUES (?, ?) "
            + "ON DUPLICATE KEY UPDATE "
            + "  nome = VALUES(nome) ";

        PreparedStatement stmt = this.getStmtSave(query);

        if (obj.getId() == 0)
            stmt.setNull(1, Types.BIGINT);
        else
            stmt.setLong(1, obj.getId());

        stmt.setString(2, obj.getNome());

        int affetectRows = stmt.executeUpdate();

        System.out.println(String.format(
            "Affected rows: %d",

            affetectRows));

    }

    @Override
    protected Iterator<Usuario> iterator(ResultSet resultSet) {

        return new UsuarioIterator(resultSet);

    }

    @Override
    public void remove(Usuario obj)
        throws DAOException {

        this.removeById(obj.getId());

    }

}
