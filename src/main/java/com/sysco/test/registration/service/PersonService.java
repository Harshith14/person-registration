package com.sysco.test.registration.service;

import com.sysco.test.registration.dao.PersonDao;
import com.sysco.test.registration.exception.PersonAlreadyExistsException;
import com.sysco.test.registration.exception.PersonNotFoundException;
import com.sysco.test.registration.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;


    public void createPerson(Person person) throws PersonAlreadyExistsException {
        Optional<Person> personByUsername = personDao.getPersonByUsername(person.getEmailId());
        if(!personByUsername.isPresent()){
            personDao.createPerson(person);
        }else
            throw new PersonAlreadyExistsException("Person already exists with: "+person.getEmailId());
    }

    public Person getPersonByUsername(String username) throws PersonNotFoundException {
        return personDao.getPersonByUsername(username).orElseThrow(() -> new PersonNotFoundException("Person not found with: "+username));
    }

    public void deletePersonByUsername(String username) throws PersonNotFoundException {
        if(personDao.getPersonByUsername(username).isPresent()){
            personDao.deletePersonByUsername(username);
        }else
            throw new PersonNotFoundException("Person not found with: "+username);
    }
}
