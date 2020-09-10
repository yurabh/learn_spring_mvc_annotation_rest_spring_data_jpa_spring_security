package com.service;

public interface CRUDService<T> {

    void save(T entity);

    void update(T entity);

    T finById(int id);

    void deleteById(int id);
}
