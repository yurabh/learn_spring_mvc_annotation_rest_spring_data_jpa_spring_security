package com.dao;

public interface CrudDao<T> {

    void save(T entity);

    T findById(int id);

    void update(T entity);

    void delete(int id);
}
