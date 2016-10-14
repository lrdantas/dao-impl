
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

    public Iterable<Endereco> findByUsuario(Usuario usuario) {

        return null;

    }

    @Override
    public void remove(Endereco obj) throws DAOException {

        //
    }

    @Override
    protected Iterator<Endereco> doFindAll(long page, long count) throws SQLException {

        return null;
    }

    @Override
    protected void doRemove(int id) throws SQLException {

        //
    }

    @Override
    protected void doSave(Endereco obj) throws SQLException {

        //
    }

    @Override
    protected Iterator<Endereco> iterator(ResultSet resultSet) {

        return null;

    }

}
