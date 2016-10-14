
package io.dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.UUID;

import io.dao.common.DAOException;
import io.dao.common.IDAO;

import io.dao.mysql.UsuarioDAO;

import io.model.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class App {

    public static void main(String[] args) {

        try {

            new App().run(ConnectionFactory.getInstance());

        } catch (Exception e) {

            e.printStackTrace();

            System.exit(-1);

            return;

        }

    }

    private void run(ConnectionFactory connectionFactory)
        throws DAOException, SQLException {

        Connection connection = connectionFactory.openConnection();

        IDAO<Usuario> dao = new UsuarioDAO(connection);

        Usuario u1 = new Usuario("José Augusto #" + UUID.randomUUID());

        dao.save(u1);

        for (Usuario usuario : dao.findAll()) {

            System.out.println(usuario);

            dao.remove(usuario);

        }

    }

}
