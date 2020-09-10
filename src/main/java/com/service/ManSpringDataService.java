package com.service;

import com.domain.Man;
import com.repository.ManRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ManSpringDataService implements CRUDServiceSpringData<Man> {

    private ManRepository manRepository;

    @Autowired
    public ManSpringDataService(ManRepository manRepository) {
        this.manRepository = manRepository;
    }

    @Override
    public void save(Man man) {
        man.getAddresses().forEach(a -> a.setMan(man));
        manRepository.save(man);
    }

    @Override
    public void update(Man man) {
        manRepository.save(man);
    }

    @Override
    public Man finById(int id) {
        return manRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        manRepository.deleteById(id);
    }

    @Override
    public Man findByAgeAndName(int age, String name) {
        return manRepository.findByAgeAndName(age, name);
    }

    @Override
    public List<Man> findAllSortByNameDesc() {
        Sort sort = Sort.by("name").descending();
        return manRepository.findAll(sort);
    }

    @Override
    public List<Man> findAllByAge(int age) {
        return manRepository.findAllByAge(age);
    }

    @Override
    public void deleteAllByAge(int age) {
//        manRepository.deleteAllByAge(age);
        List<Man> men = manRepository.findAllByAge(age);
        manRepository.deleteAll(men);
    }
}
