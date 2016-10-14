
package io.dao;

import java.sql.Connection;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 *
 */
public class MainThread extends Thread {

    @Override
    public void run() {

        Connection connection;

        try {
            connection = App.factory.openConnection();

            new App().run(connection);

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();

            this.interrupt();
        }

    }

}
