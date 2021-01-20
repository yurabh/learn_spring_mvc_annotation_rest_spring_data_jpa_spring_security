package com.dtos;

import com.domain.Man;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManDto manDto = (ManDto) o;
        return id == manDto.id &&
                age == manDto.age &&
                Objects.equals(name, manDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return "ManDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
