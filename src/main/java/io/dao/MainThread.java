
package io.dao;

import java.sql.Connection;
import java.sql.SQLException;

import io.dao.common.DAOException;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class MainThread extends Thread {

    @Override
    public void run() {

        try {

            doRun();

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (DAOException e) {

            e.printStackTrace();

        }

    }

    private void doRun()
        throws SQLException, DAOException {

        Connection connection = App.factory.openConnection();

        try {

            new App().doRun(connection);

        } finally {

            connection.close();

        }

    }

}
