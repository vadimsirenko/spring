package ru.vasire.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vasire.dao.PersonDAO;
import ru.vasire.model.Person;

@Component
public class PersonValidator implements Validator {
    PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personDAO.show(person.getId(), person.getEmail()).isPresent()){
            errors.rejectValue("email", null, "Дубликал e-mail");
        }
    }
}
