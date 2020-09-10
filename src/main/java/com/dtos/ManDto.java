package com.dtos;

import com.domain.Man;

public class ManDto {
    private int id;
    private String name;
    private int age;

    public ManDto(Man man) {
        id = man.getId();
        name = man.getName();
        age = man.getAge();
    }

    public ManDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
