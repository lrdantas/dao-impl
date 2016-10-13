
package io.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 *
 */
public class ConnectionFactory {

    private static class Nested {

        public static final ConnectionFactory INSTANCE = createConnectionFactory();

        private static ConnectionFactory createConnectionFactory() {

            return new ConnectionFactory();

        }

    }

    public static ConnectionFactory getInstance() {

        return Nested.INSTANCE;

    }

    private ConnectionFactory() { }

    public Connection openConnection()
        throws SQLException {

        Connection connection;

        Properties props = new Properties();
        props.put("user", "horusweb");
        props.put("password", "");
        // props.put("useLegacyDatetimeCode", false);
        props.put("serverTimezone", "America/Fortaleza");

        // Class.forName("com.mysql.cj.jdbc.Driver");

        try {

            connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:4040/dao_testing", props);

        } catch (SQLException e) {

            throw e;

        }

        return connection;

    }

}
