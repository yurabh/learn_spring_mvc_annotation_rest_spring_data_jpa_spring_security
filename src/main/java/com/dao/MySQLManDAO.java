package com.dao;

import com.domain.Man;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class MySQLManDAO implements CrudDao<Man> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Man man) {
        man.getAddresses().forEach(a -> a.setMan(man));
        entityManager.persist(man);
    }

    @Override
    @Transactional(readOnly = true)
    public Man findById(int id) {
        return entityManager.find(Man.class, id);
    }

    @Override
    public void update(Man man) {
        Man manNew = entityManager.merge(man);
        entityManager.persist(manNew);
    }

    @Override
    public void delete(int id) {
        Man man = entityManager.find(Man.class, id);
        entityManager.remove(man);
    }
}