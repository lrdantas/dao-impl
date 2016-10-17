
package io.dao.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class LazyCollection<T> implements Collection<T> {

    private Collection<T> storage;
    private Iterable<T> iterable;

    private boolean isInitialized;

    public LazyCollection(Iterable<T> iter) {

        this.storage = new LinkedList<T>();
        this.iterable = iter;

        this.isInitialized = false;

    }

    @Override
    public int size() {

        this.initialize();

        return this.storage.size();

    }

    private void initialize() {

        if (this.isInitialized)
            return;

        this.isInitialized = true;

        for (T obj : this.iterable) {

            this.add(obj);

        }

    }

    @Override
    public boolean isEmpty() {

        this.initialize();

        return this.storage.isEmpty();

    }

    @Override
    public boolean contains(Object o) {

        this.initialize();

        return this.storage.contains(o);

    }

    @Override
    public Iterator<T> iterator() {

        final LazyCollection<T> self = this;

        return new Iterator<T>() {

            final Iterator<T> iterator = self.isInitialized ? self.storage.iterator() : self.iterable.iterator();

            @Override
            public boolean hasNext() {

                return this.iterator.hasNext();

            }

            @Override
            public T next() {

                T obj = this.iterator.next();

                self.add(obj);

                return obj;

            }

        };

    }

    @Override
    public Object[] toArray() {

        this.initialize();

        return this.storage.toArray();

    }

    @Override
    public <E> E[] toArray(E[] a) {

        this.initialize();

        return this.storage.toArray(a);

    }

    @Override
    public boolean add(T e) {

        return this.storage.add(e);

    }

    @Override
    public boolean remove(Object o) {

        this.initialize();

        return this.storage.remove(o);

    }

    @Override
    public boolean containsAll(Collection<?> c) {

        this.initialize();

        return this.storage.containsAll(c);

    }

    @Override
    public boolean addAll(Collection<? extends T> c) {

        this.initialize();

        return this.storage.addAll(c);

    }

    @Override
    public boolean removeAll(Collection<?> c) {

        this.initialize();

        return this.storage.removeAll(c);

    }

    @Override
    public boolean retainAll(Collection<?> c) {

        this.initialize();

        return this.storage.retainAll(c);

    }

    @Override
    public void clear() {

        this.iterable = null;

        this.storage.clear();

    }

}
