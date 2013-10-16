package com.hubery.exam.domain;

import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = -8577144162762801823L;

    private String name;

    private Person father;

    public Person(String _name) {
        name = _name;
    }

    public Person(String _name, Person _parent) {
        name = _name;
        father = _parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

}
