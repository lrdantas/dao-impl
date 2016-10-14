
package io.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class HikariCPConnectionFactory extends ConnectionFactory {

    private HikariDataSource dataSource;

    public HikariCPConnectionFactory() {

        Properties props = new Properties();

        props.setProperty("jdbcUrl", "jdbc:mysql://127.0.0.1:4040/dao_testing?serverTimezone=America/Fortaleza");
        props.setProperty("dataSource.user", "horusweb");
        props.setProperty("dataSource.password", "");
        // props.setProperty("dataSource.databaseName", "dao_testing");

        props.put("dataSource.logWriter", new PrintWriter(System.out));

        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);

        this.dataSource = ds;

    }

    @Override
    public Connection openConnection() throws SQLException {

        return this.dataSource.getConnection();

    }

}
