
package io.dao.common;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public interface IDAO<T> {

    void close() throws DAOException;

    Iterable<T> findAll() throws DAOException;
    Iterable<T> findAll(long page, long count) throws DAOException;

    T findById(Object id) throws DAOException;

    void remove(T obj) throws DAOException;
    void removeById(Object id) throws DAOException;

    void save(T obj) throws DAOException;

    //default T findFirst()
    //    throws DAOException {
    //
    //    for (T obj : this.findAll(0, 1)) {
    //
    //        return obj;
    //
    //    }
    //
    //    return null;
    //
    //}

}
