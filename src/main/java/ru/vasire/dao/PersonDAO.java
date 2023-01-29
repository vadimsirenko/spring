package ru.vasire.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vasire.config.SessionManager;
import ru.vasire.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final SessionManager sessionManager;

    @Autowired
    public PersonDAO(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED )
    public List<Person> getAll(){
        Session session = sessionManager.currentSession();
        List<Person> people = session.createQuery("select p from Person p", Person.class)
                .getResultList();
        return people;
    }

    public Person show(int id){
        Session session = sessionManager.currentSession();
        return session.get(Person.class, id);
    }

    public Optional<Person> show(int id, String email){
        Session session = sessionManager.currentSession();
        return session.createQuery("select p from Person p WHERE p.email=:email and p.id <> :id", Person.class)
                .setParameter("email", email)
                .setParameter("id", id)
                .getResultList().stream().findFirst();

    }

    public void create(Person person){
        Session session = sessionManager.currentSession();
        Transaction tx= session.beginTransaction();
        Person personObj = new Person(person.getName(), person.getAge(), person.getEmail());
        session.persist(personObj);
        tx.commit();
    }

    public void update(int id, Person person){
        Session session = sessionManager.currentSession();
        Transaction tx= session.beginTransaction();
        Person personObj = session.get(Person.class, id);
        personObj.setName(person.getName());
        personObj.setAge(person.getAge());
        personObj.setEmail(person.getEmail());
        session.persist(personObj);
        tx.commit();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(int id)
    {
        Session session = sessionManager.currentSession();
        Transaction tx= session.beginTransaction();
        Person personObj = session.get(Person.class, id);
        session.remove(personObj);
        tx.commit();
    }
}
