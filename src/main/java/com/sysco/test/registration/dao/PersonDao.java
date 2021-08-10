package com.sysco.test.registration.dao;

import com.sysco.test.registration.model.Person;

import java.util.Optional;

public interface PersonDao {
    Optional<Person> getPersonByUsername(String username);

    void createPerson(Person person);

    void deletePersonByUsername(String username);
}
