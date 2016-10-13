
package io.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import io.dao.impl.DAOException;
import io.model.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class UsuarioDAO extends MySQLDAO<Usuario> {

    public UsuarioDAO(Connection connection) {

        super(connection);

    }

    @Override
    public void save(Usuario obj)
        throws DAOException {

        try {

            doSave(obj);

        } catch (SQLException e) {

            e.printStackTrace();

            throw new DAOException(
                "Falha ao salvar usuário",
                e);
        }

    }

    private void doSave(Usuario obj)
        throws SQLException {

        String query = ""
            + "INSERT INTO usuario (id, nome) "
            + "VALUES (?, ?) "
            + "ON DUPLICATE KEY UPDATE "
            + "  nome = VALUES(nome) ";

        PreparedStatement stmt;

        stmt = this.getConnection().prepareStatement(query);

        if (obj.getId() == 0)
            stmt.setNull(1, Types.BIGINT);
        else
            stmt.setInt(1, obj.getId());

        stmt.setString(2, obj.getNome());

        int affetectRows = stmt.executeUpdate();

        System.out.println(String.format(
            "Affected rows: %d",

            affetectRows));

    }

    @Override
    public void remove(Usuario obj) throws DAOException {

        //

    }

    @Override
    public void removeById(Object id) throws DAOException {

        //

    }

    @Override
    public Iterable<Usuario> findAll() throws DAOException {

        return null;
    }

    @Override
    public Iterable<Usuario> findAll(long page, long count)
        throws DAOException {

        return null;
    }

    @Override
    public Usuario findById(Object id) throws DAOException {

        return null;
    }

    @Override
    public void close() throws DAOException {

        //

    }

}
