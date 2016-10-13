
package io.dao.impl;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 *
 */
public class DatabaseAccessException extends RuntimeException {

    public DatabaseAccessException(String message, Exception cause) {

        super(message, cause);

    }

}
