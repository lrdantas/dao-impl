
package io.dao.mysql;

import java.sql.Connection;

import io.dao.impl.IDAO;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 *
 */
public abstract class MySQLDAO<T> implements IDAO<T> {

    private final Connection connection;

    public MySQLDAO(Connection connection) {

        this.connection = connection;

    }

    protected Connection getConnection() {

        return this.connection;

    }

}
