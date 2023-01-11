package ru.vasire.dao;

import org.springframework.stereotype.Component;
import ru.vasire.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeopleDAO {
    List<Person> people;
    int PERSON_COUNT;

    {
        people = new ArrayList<>();
        people.add(new Person(++PERSON_COUNT, "Вадим"));
        people.add(new Person(++PERSON_COUNT, "Елена"));
        people.add(new Person(++PERSON_COUNT, "Алексей"));
        people.add(new Person(++PERSON_COUNT, "Дмитрий"));
    }

    public Person getPerson(int id){
        return people.stream().filter(p->p.getId()==id).findAny().orElse(null);
    }

    public List<Person> getAll(){
        return people;
    }

}
