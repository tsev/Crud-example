package ru.tsev.shared.dao.common;

import java.io.Serializable;
import java.util.List;

public interface CrudOperations<T extends Serializable> {

    T retrieve(final long id);

    List<T> retrieveAll();

    void create(final T entity);

    T update(final T entity);

    void delete(final long entityId);

    void delete(T entry);

}
