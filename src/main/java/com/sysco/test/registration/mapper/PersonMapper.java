package com.sysco.test.registration.mapper;

import com.sysco.test.registration.entity.PersonEntity;
import com.sysco.test.registration.model.Person;

public class PersonMapper {

    public static PersonEntity mapToEntity(Person person){
        PersonEntity personEntity= new PersonEntity();
        personEntity.setFirstName(person.getFirstName());
        personEntity.setLastName(person.getLastName());
        personEntity.setEmailId(person.getEmailId());
        personEntity.setMobileNumber(person.getMobileNumber());
        personEntity.setPassword(person.getPassword());
        return personEntity;
    }

    public static Person mapToModel(PersonEntity personEntity){
        Person person = new Person();
        person.setFirstName(personEntity.getFirstName());
        person.setLastName(personEntity.getLastName());
        person.setEmailId(personEntity.getEmailId());
        person.setMobileNumber(personEntity.getMobileNumber());
        return person;
    }
}
