
package io.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Iterator;

import io.dao.common.DAOException;

import io.model.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class UsuarioDAO extends JdbcDAO<Usuario> {

    private EnderecoDAO enderecoDAO;

    public UsuarioDAO(Connection connection) {

        super(connection);

        this.enderecoDAO = new EnderecoDAO(connection);

    }

    @Override
    public void close()
        throws DAOException {

        super.close();

    }

    @Override
    protected Iterator<Usuario> doFindAll(long page, long count)
        throws SQLException {

        String query = ""
            + "SELECT u.id, u.nome "

            + "FROM usuario u "

            + "LIMIT ?, ? ";

        ResultSet resultSet = super.executeFind(query, page, count);

        return this.iterator(resultSet);

    }

    @Override
    protected Usuario doFindById(Object id)
        throws SQLException {

        String query = ""
            + "SELECT u.id, u.nome "

            + "FROM usuario u "

            + "WHERE u.id = ? ";

        ResultSet resultSet = super.executeFind(query, (Integer)id);

        for (Usuario usuario : this.iterable(resultSet)) {

            return usuario;

        }

        // TODO Jogar NotFoundException
        return null;

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

        if (obj.getId() == 0) {

            stmt.setNull(1, Types.BIGINT);

        } else {

            stmt.setLong(1, obj.getId());

        }

        stmt.setString(2, obj.getNome());

        int affetectRows = stmt.executeUpdate();

        ResultSet generatedKeys = stmt.getGeneratedKeys();

        if (obj.getId() == 0 && generatedKeys != null) {

            generatedKeys.next();

            obj.setId(generatedKeys.getInt(1));

        }

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
