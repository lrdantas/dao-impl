
package io.dao;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.dao.common.DAOException;

import io.dao.mysql.EnderecoDAO;
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

    public void run(Connection connection)
        throws DAOException, SQLException {

        int size = 5;

        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        EnderecoDAO enderecoDAO = new EnderecoDAO(connection);

        List<Usuario> cache = new ArrayList<Usuario>(size);

        System.out.println("\n:: Inserindo usuários de teste");

        for (int i = 0; i < size; i++) {

            Usuario usuario = new Usuario("José Augusto #" + UUID.randomUUID());

            usuarioDAO.save(usuario);

            cache.add(usuario);

            System.out.println(usuario);

        }

        Iterable<Endereco> enderecos = enderecoDAO.findByUsuario(usuarioDAO.findById(1));

        for (Endereco endereco : enderecos) {

            System.out.println(endereco);

        }

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
