package com.controller;

import com.domain.Man;
import com.dtos.ManDto;
import com.service.CRUDService;
import com.service.CRUDServiceSpringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "man")
public class ManController {

    private final CRUDService<Man> crudService;

    private final CRUDServiceSpringData<Man> CRUDServiceData;

    @Autowired
    public ManController(@Qualifier("manService") CRUDService<Man> crudService,
                         @Qualifier("manSpringDataService") CRUDServiceSpringData<Man> CRUDServiceData) {
        this.crudService = crudService;
        this.CRUDServiceData = CRUDServiceData;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Man> save(@RequestBody Man man) {
        HttpStatus status = man != null ? HttpStatus.CREATED : HttpStatus.NOT_FOUND;
        CRUDServiceData.save(man);
        return new ResponseEntity<>(man, status);
    }

    @GetMapping(value = "/find/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Man> find(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("findMan", "man");
        headers.getLastModified();
        headers.get("findMan");
        return new ResponseEntity<Man>(CRUDServiceData.finById(id), headers, HttpStatus.FOUND);

    }

    @PutMapping(value = "/update")
    public void update(@RequestBody Man man) {
        crudService.update(man);
//        throw new IllegalArgumentException("Man not found");
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") int id) {
        CRUDServiceData.deleteById(id);
    }

    @GetMapping("/list")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<Man> findAll() {
        if (1 == 1) {
            throw new IllegalArgumentException("Man not found");
        }
        return CRUDServiceData.findAllByAge(20);
    }

    @GetMapping("/get/dto")
    public ManDto findDto() {
        Man man = crudService.finById(4);
        return new ManDto(man);
    }

    @DeleteMapping("/deleteByAge/{age}")
    public void deleteByAge(@PathVariable("age") int age) {
        CRUDServiceData.deleteAllByAge(age);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> workWithException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }
}
