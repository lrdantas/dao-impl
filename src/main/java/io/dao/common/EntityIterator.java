
package io.dao.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 *
 */
public abstract class EntityIterator<T> implements Iterator<T> {

    private final ResultSet resultSet;

    private boolean isInitialized;
    private boolean hasNext;

    public EntityIterator(ResultSet resultSet) {

        this.resultSet = resultSet;

    }

    protected abstract T fetchCurrent(ResultSet resultSet)
        throws SQLException;

    @Override
    public boolean hasNext() {

        if (!this.isInitialized) {

            this.isInitialized = true;

            try {

                return this.resultSet.first();

            } catch (SQLException e) {

                this.hasNext = false;

                throw new DatabaseAccessException(
                    "Erro ao inicializar result set", e);
            }

        }

        return this.hasNext;

    }

    @Override
    public T next() {

        T obj;

        try {

            obj = this.fetchCurrent(this.resultSet);

            this.hasNext = this.resultSet.next();

        } catch (SQLException e) {

            e.printStackTrace();

            this.hasNext = false;

            throw new DatabaseAccessException(
                "Erro ao avançar no result set", e);

        }

        return obj;

    }

}
