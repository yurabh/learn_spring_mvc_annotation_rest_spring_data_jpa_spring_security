package com.service;

import com.dao.CrudDao;
import com.domain.Man;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManService implements CRUDService<Man> {

    private CrudDao<Man> dao;

    @Autowired
    public ManService(CrudDao<Man> dao) {
        this.dao = dao;
    }

    @Override
    public void save(Man man) {
        dao.save(man);
    }

    @Override
    public void update(Man man) {
        dao.update(man);
    }

    @Override
    public Man finById(int id) {
        return dao.findById(id);
    }

    @Override
    public void deleteById(int id) {
        dao.delete(id);
    }
}