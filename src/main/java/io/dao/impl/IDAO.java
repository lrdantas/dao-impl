
package io.dao.impl;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public interface IDAO<T> {

    void save(T obj)
        throws DAOException;

    void remove(T obj)
        throws DAOException;
    void removeById(Object id)
        throws DAOException;

    Iterable<T> findAll()
        throws DAOException;
    Iterable<T> findAll(long page, long count)
        throws DAOException;

    T findById(Object id)
        throws DAOException;

    void close()
        throws DAOException;

}
