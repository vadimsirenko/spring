package ru.vasire.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.vasire.model.Person;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT id, name, age, email FROM person WHERE id =?",
                        new Object[]{id},
                        new int[]{Types.INTEGER},
                        new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> show(int id, String email){
        return jdbcTemplate.query("SELECT id, name, age, email FROM person WHERE id <>? AND email = ?",
                new Object[]{id, email},
                new int[]{Types.INTEGER, Types.VARCHAR},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Person> getAll(){
        return jdbcTemplate.query("SELECT id, name, age, email FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public void create(Person person){
        jdbcTemplate.update("INSERT INTO person (name, age, email) VALUES (?,?,?)",
                new Object[]{person.getName(), person.getAge(), person.getEmail()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
    }
    public void update(int id, Person person){
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id =?",
                new Object[]{person.getName(), person.getAge(), person.getEmail(), person.getId()},
                new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER});
    }

    public void delete(int id)
    {
        jdbcTemplate.update("DELETE FROM person WHERE id =?",
                new Object[]{id},
                new int[]{Types.INTEGER});
    }
}
