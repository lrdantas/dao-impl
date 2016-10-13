
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
    protected Usuario fetchCurrent()
        throws SQLException {

        Usuario usuario = new Usuario();

        usuario.setId(this.resultSet.getInt(1));
        usuario.setNome(this.resultSet.getString(2));

        return usuario;

    }

}
