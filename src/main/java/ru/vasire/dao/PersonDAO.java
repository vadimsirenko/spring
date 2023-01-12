package ru.vasire.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vasire.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    List<Person> people;
    int PERSON_COUNT;

    {
        people = new ArrayList<>();
        people.add(new Person(++PERSON_COUNT, "Вадим"));
        people.add(new Person(++PERSON_COUNT, "Елена"));
        people.add(new Person(++PERSON_COUNT, "Алексей"));
        people.add(new Person(++PERSON_COUNT, "Дмитрий"));
    }

    public Person show(int id){
        return people.stream().filter(p->p.getId()==id).findAny().orElse(null);
    }

    public List<Person> getAll(){
        return people;
    }

    public void create(Person person){
        people.add(new Person(++PERSON_COUNT, person.getName()));
    }
    public void update(int id, Person person){
        Person personToUpdate = show(id);
        personToUpdate.setName(person.getName());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
