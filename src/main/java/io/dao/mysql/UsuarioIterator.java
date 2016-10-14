
package io.dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.model.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class UsuarioIterator extends EntityIterator<Usuario> {

    public UsuarioIterator(ResultSet resultSet) {

        super(resultSet);

    }

    @Override
    protected Usuario fetchCurrent(ResultSet resultSet)
        throws SQLException {

        Usuario usuario = new Usuario();

        usuario.setId(resultSet.getInt("id"));
        usuario.setNome(resultSet.getString("nome"));

        return usuario;

    }

}
