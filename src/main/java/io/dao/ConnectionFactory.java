
package io.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 *
 */
public abstract class ConnectionFactory {

    private static class Nested {

        public static final ConnectionFactory INSTANCE = createConnectionFactory();

        private static ConnectionFactory createConnectionFactory() {

            return new MySqlLocalConnectionFactory();
            //return new HikariCPConnectionFactory();

        }

    }

    public static ConnectionFactory getInstance() {

        return Nested.INSTANCE;

    }

    public abstract Connection openConnection()
        throws SQLException;

}
