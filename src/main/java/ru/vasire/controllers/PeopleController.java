package ru.vasire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vasire.dao.PeopleDAO;
import ru.vasire.model.Person;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleDAO peopleDao;

    public PeopleController(PeopleDAO peopleDao) {
        this.peopleDao = peopleDao;
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Person> people = peopleDao.getAll();
        model.addAttribute("people", people);
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Person person = peopleDao.getPerson(id);
        model.addAttribute("person", person);
        return "people/show";
    }
}
