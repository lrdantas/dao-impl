
package io.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.Iterator;

import io.dao.common.DAOException;
import io.dao.common.JdbcDAO;
import io.dao.common.LazyCollection;
import io.dao.common.UsuarioIterator;

import io.model.Endereco;
import io.model.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class UsuarioDAO extends JdbcDAO<Usuario> {

    private final EnderecoDAO enderecoDAO;

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
        throws SQLException, DAOException {

        String query = ""
            + "SELECT u.id, u.nome "

            + "FROM usuario u "

            + "LIMIT ?, ? ";

        ResultSet resultSet = super.executeFind(query, page, count);

        return this.iterator(resultSet);

    }

    @Override
    protected Usuario doFindById(Object id)
        throws SQLException, DAOException {

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
        throws SQLException, DAOException {

        String query = "DELETE FROM usuario WHERE id = ?";

        PreparedStatement stmt;

        stmt = this.prepare(query);

        stmt.setLong(1, id);

        int affectedRows = stmt.executeUpdate();

        System.out.println(String.format(
            "Affected rows: %d",

            affectedRows));

    }

    @Override
    protected void doSave(Usuario usuario)
        throws SQLException, DAOException {

        String query = ""
            + "INSERT INTO usuario (id, nome) "
            + "VALUES (?, ?) "
            + "ON DUPLICATE KEY UPDATE "
            + "  nome = VALUES(nome) ";

        PreparedStatement stmt = this.getStmtSave(query);

        if (usuario.getId() == 0) {

            stmt.setNull(1, Types.BIGINT);

        } else {

            stmt.setLong(1, usuario.getId());

        }

        stmt.setString(2, usuario.getNome());

        int affectedRows = stmt.executeUpdate();

        ResultSet generatedKeys = stmt.getGeneratedKeys();

        if (usuario.getId() == 0 && generatedKeys != null) {

            generatedKeys.next();

            usuario.setId(generatedKeys.getInt(1));

        }

        for (Endereco endereco : usuario.getEnderecos()) {

            enderecoDAO.doSave(endereco);

            this.saveEndereco(usuario, endereco);

        }

        System.out.println(String.format(
            "Affected rows: %d",

            affectedRows));

    }

    private void saveEndereco(Usuario usuario, Endereco endereco)
        throws SQLException {

        String query = ""
            + "INSERT INTO usuario_endereco (usuario_id, endereco_id) "
            + "VALUES (?, ?) "
            + "ON DUPLICATE KEY UPDATE "
            + " usuario_id = VALUES(usuario_id) ";

        PreparedStatement stmt = super.bindStatement(
            query,

            usuario.getId(),
            endereco.getId());

        stmt.executeUpdate();

    }

    @Override
    protected Iterator<Usuario> iterator(final ResultSet resultSet) {

        final Iterator<Usuario> iter = new UsuarioIterator(resultSet);

        return new Iterator<Usuario>() {

            @Override
            public boolean hasNext() {

                return iter.hasNext();
            }

            @Override
            public Usuario next() {

                final Usuario usuario = iter.next();

                Iterable<Endereco> enderecos = new Iterable<Endereco>() {

                    @Override
                    public Iterator<Endereco> iterator() {

                        return enderecoDAO.findByUsuario(usuario).iterator();

                    }

                };

                usuario.setEnderecos(new LazyCollection<Endereco>(enderecos));

                return usuario;
            }

        };

    }

    @Override
    public void remove(Usuario obj)
        throws DAOException {

        this.removeById(obj.getId());

    }

}
