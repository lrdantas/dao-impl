package io.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import io.dao.impl.IDAO;
import io.dao.mysql.UsuarioDAO;
import io.model.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class App {

    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args)
        throws Exception {

        Connection connection;

        Properties props = new Properties();
        props.put("user", "horusweb");
        props.put("password", "");
        //props.put("useLegacyDatetimeCode", false);
        props.put("serverTimezone", "America/Fortaleza");

        // Class.forName("com.mysql.cj.jdbc.Driver");

        try {

            connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:4040/dao_testing",
                props);

        } catch (SQLException e) {

            throw e;

        }

        IDAO<Usuario> dao = new UsuarioDAO(connection);
        Usuario u1 = new Usuario("José Augusto");

        dao.save(u1);

    }

}
