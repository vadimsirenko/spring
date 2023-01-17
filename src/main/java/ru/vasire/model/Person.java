package ru.vasire.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    int id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min=2, max=30, message = "Имя должно содержать от 2 до 30 символов")
    String name;
    @Min(value = 0,message = "Возраст должен быть 0 или больше")
    int age;
    @NotEmpty(message = "Адрес электронной почты должен быть заполнен")
    @Email(message = "Не корректный адрес электронной почты")
    String email;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person(int id, String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
