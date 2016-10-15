
package io.dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import io.dao.common.DAOException;

import io.model.Endereco;
import io.model.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class EnderecoDAO extends JdbcDAO<Endereco> {

    public EnderecoDAO(Connection connection) {

        super(connection);

    }

    @Override
    protected Iterator<Endereco> doFindAll(long page, long count)
        throws SQLException {

        String query = ""
            + "SELECT e.id, e.logradouro, e.numero, e.bairro, e.cidade, e.uf, e.cep "
            + "FROM endereco e"
            + "LIMIT ?, ? ";

        ResultSet resultSet = super.executeFind(query, page, count);

        return this.iterator(resultSet);

    }

    @Override
    protected Endereco doFindById(Object id)
        throws SQLException {

        return null;
    }

    @Override
    protected void doRemove(int id)
        throws SQLException {

        //
    }

    @Override
    protected void doSave(Endereco obj)
        throws SQLException {

        //
    }

    public Iterable<Endereco> findByUsuario(Usuario usuario) {

        String query = ""
            + "SELECT e.id, e.logradouro, e.numero, e.bairro, e.cidade, e.uf, e.cep "
            + "FROM endereco e "
            + "INNER JOIN usuario_endereco eu "
            + "ON eu.endereco_id = e.id "
            + "WHERE usuario_id = ? ";

        ResultSet resultSet = super.executeFind(query, usuario.getId());

        return this.iterable(resultSet);

    }

    @Override
    protected Iterator<Endereco> iterator(ResultSet resultSet) {

        return new EnderecoIterator(resultSet);

    }

    @Override
    public void remove(Endereco obj)
        throws DAOException {

        //
    }

}
