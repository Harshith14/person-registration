package com.sysco.test.registration.controller;

import com.sysco.test.registration.exception.PersonAlreadyExistsException;
import com.sysco.test.registration.exception.PersonNotFoundException;
import com.sysco.test.registration.model.Person;
import com.sysco.test.registration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public void createPerson(@RequestBody @Valid Person person) throws PersonAlreadyExistsException {
           personService.createPerson(person);
    }

    @GetMapping("/{username}")
    public Person getPersonByUsername(@PathVariable String username) throws PersonNotFoundException {
     return personService.getPersonByUsername(username);
    }

    @DeleteMapping("/{username}")
    public void deletePersonByUsername(@PathVariable String username) throws PersonNotFoundException {
         personService.deletePersonByUsername(username);
    }

}
