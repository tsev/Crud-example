package ru.tsev.shared.dao.common;

import java.io.Serializable;

public interface GenericDao<T extends Serializable> extends CrudOperations<T> {

}
