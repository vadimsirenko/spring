package ru.vasire.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vasire.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    List<Person> people;
    private static String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "postgres";
    private static Connection connection;

    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Person show(int id){

        Person person = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, age, email FROM person WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            person = new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public List<Person> getAll(){
        List<Person> personList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, age, email FROM person");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                personList.add(new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personList;
    }

    public void create(Person person){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO person (id, name, age, email) VALUES (1, ?,?,?)");

            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void update(int id, Person person){
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE person SET name = ?, age = ?, email =? WHERE id=?");

            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());
            statement.setInt(4, person.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id)
    {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM person WHERE id=?");

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
