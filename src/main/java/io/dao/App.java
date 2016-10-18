
package io.dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.dao.common.DAOException;

import io.dao.mysql.UsuarioDAO;

import io.model.Endereco;
import io.model.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class App {

    public static final ConnectionFactory factory = ConnectionFactory.getInstance();

    public static void main(String[] args) {

        try {

            for (int i = 0; i < 1; i++) {

                new MainThread().start();

            }

        } catch (Exception e) {

            e.printStackTrace();

            System.exit(-1);

            return;

        }

    }

    public void doRun(Connection connection)
        throws DAOException, SQLException {

        final int SIZE = 1;

        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

        List<Usuario> cache = new ArrayList<Usuario>(SIZE);

        System.out.println("\n:: Inserindo usuários de teste");

        for (int i = 0; i < SIZE; i++) {

            Usuario usuario = new Usuario("José Augusto #" + UUID.randomUUID());
            Endereco endereco = new Endereco(
                "R. dos Programas", "1", "IOException", "Natal", "RN", "59056000");

            usuario.addEndereco(endereco);

            usuarioDAO.save(usuario);

            cache.add(usuario);

            System.out.println(usuario);

        }

        Usuario fstUsuario = cache.get(0);

        /* for (Usuario usuario : usuarioDAO.findAll(0, 1)) {

            fstUsuario = usuario;

        } */

        Iterable<Endereco> enderecos = fstUsuario.getEnderecos();

        for (Endereco endereco : enderecos) {

            System.out.println(endereco);

        }

        System.exit(0);

        System.out.println("\n:: Listando alguns Usuários Cadastrados");

        for (Usuario usuario : usuarioDAO.findAll(0, 3)) {

            System.out.println(usuario);

        }

        System.out.println("\n:: Removendo usuários de teste");

        for (Usuario usuario : cache) {

            System.out.println(usuario);

            // usuarioDAO.remove(usuario);

        }

    }

}
