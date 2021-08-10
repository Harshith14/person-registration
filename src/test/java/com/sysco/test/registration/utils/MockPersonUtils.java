package com.sysco.test.registration.utils;

import com.sysco.test.registration.entity.PersonEntity;
import com.sysco.test.registration.model.Person;

public class MockPersonUtils {

    public static Person createPerson() {
        Person person= new Person();
        person.setFirstName("Harshu");
        person.setLastName("Jangam");
        person.setMobileNumber("92991416");
        person.setEmailId("harshu@gmail.com");
        person.setPassword("harshu");
        return person;
    }

    public static PersonEntity createPersonEntity() {
        PersonEntity personEntity= new PersonEntity();
        personEntity.setFirstName("Harshu");
        personEntity.setLastName("Jangam");
        personEntity.setMobileNumber("92991416");
        personEntity.setEmailId("harshu@gmail.com");
        personEntity.setPassword("harshu");
        return personEntity;
    }
}
