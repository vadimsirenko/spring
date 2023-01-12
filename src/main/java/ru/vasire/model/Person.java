package ru.vasire.model;

public class Person {
    String name;
    int id;

    public Person(int id, String name) {
        this.name = name;
        this.id = id;
    }
    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
