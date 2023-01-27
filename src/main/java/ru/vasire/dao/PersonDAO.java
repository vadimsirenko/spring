package ru.vasire.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vasire.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Person> getAll(){
       // Session session = sessionFactory.getCurrentSession();
        Session session = sessionFactory.openSession();
        List<Person> people = session.createQuery("select p from Person p", Person.class)
                .getResultList();
        return people;
    }

    public Person show(int id){
        /*return jdbcTemplate.query("SELECT id, name, age, email FROM person WHERE id =?",
                        new Object[]{id},
                        new int[]{Types.INTEGER},
                        new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);

         */
        return null;
    }

    public Optional<Person> show(int id, String email){
       /* return jdbcTemplate.query("SELECT id, name, age, email FROM person WHERE id <>? AND email = ?",
                new Object[]{id, email},
                new int[]{Types.INTEGER, Types.VARCHAR},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();

        */
        return null;
    }

    public void create(Person person){
      /* jdbcTemplate.update("INSERT INTO person (name, age, email) VALUES (?,?,?)",
                new Object[]{person.getName(), person.getAge(), person.getEmail()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});

       */
    }
    public void update(int id, Person person){
       /* jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id =?",
                new Object[]{person.getName(), person.getAge(), person.getEmail(), person.getId()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER});

        */
    }

    public void delete(int id)
    {
        /*jdbcTemplate.update("DELETE FROM person WHERE id =?",
                new Object[]{id},
                new int[]{Types.INTEGER});

         */
    }
}
