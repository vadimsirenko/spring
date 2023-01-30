package ru.vasire.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vasire.models.Person;
import ru.vasire.repositories.PeopleRepository;
import ru.vasire.services.PeopleService;

@Component
public class PersonValidator implements Validator {
    PeopleService peopeService;

    @Autowired
    public PersonValidator(PeopleService peopeService) {
        this.peopeService = peopeService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(!peopeService.checkEmail(person.getId(), person.getEmail())){
            errors.rejectValue("email", null, "Дубликат e-mail");
        }
    }
}
