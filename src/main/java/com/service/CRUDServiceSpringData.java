package com.service;

import com.domain.Man;

import java.util.List;

public interface CRUDServiceSpringData<T> {

    void save(T entity);

    void update(T entity);

    T finById(int id);

    void deleteById(int id);

    Man findByAgeAndName(int age, String name);

    List<Man> findAllSortByNameDesc();

    List<Man> findAllByAge(int age);

    void deleteAllByAge(int age);
}
