package ru.vasire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vasire.dao.PersonDAO;
import ru.vasire.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String adminView(Model model, @ModelAttribute("person") Person person)
    {
        model.addAttribute("people", personDAO.getAll());
        return "admin/edit";
    }

    @PatchMapping("/add")
    public String addAdmin(@ModelAttribute("person") Person person){
        System.out.println("person = " + person);
        return "redirect:/people";
    }
}
