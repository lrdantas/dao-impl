
package io.dao;

import java.sql.Connection;
import java.sql.SQLException;

import io.dao.common.DAOException;
import io.dao.common.IDAO;

import io.dao.mysql.UsuarioDAO;

import io.model.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class App {

    /**
     * @param args
     * @throws DAOException
     * @throws SQLException
     */
    public static void main(String[] args)
        throws DAOException {

        Connection connection;

        try {

            connection = ConnectionFactory.getInstance().openConnection();

        } catch (SQLException e) {

            e.printStackTrace();

            System.exit(-1);

            return;

        }

        IDAO<Usuario> dao = new UsuarioDAO(connection);

        Usuario u1 = new Usuario("José Augusto");

        dao.save(u1);

        for (Usuario usuario : dao.findAll(0, 3)) {

            System.out.println(usuario);

            dao.remove(usuario);

        }

    }

}
