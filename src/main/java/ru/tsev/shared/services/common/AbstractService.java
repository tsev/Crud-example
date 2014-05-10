package ru.tsev.shared.services.common;

import org.springframework.transaction.annotation.Transactional;
import ru.tsev.shared.dao.common.CrudOperations;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractService<T extends Serializable> implements CrudOperations<T> {

    @Override
    public T retrieve(final long id) {
        return getDao().retrieve(id);
    }

    @Override
    public List<T> retrieveAll() {
        return getDao().retrieveAll();
    }

    @Override
    public void create(final T entity) {
        getDao().create(entity);
    }

    @Override
    public T update(final T entity) {
        return getDao().update(entity);
    }

    @Override
    public void delete(final T entity) {
        getDao().delete(entity);
    }

    @Override
    public void delete(final long entityId) {
        getDao().delete(entityId);
    }

    protected abstract CrudOperations<T> getDao();

}
