package com.sysco.test.registration.dao.impl;

import com.sysco.test.registration.dao.PersonDao;
import com.sysco.test.registration.db.PersonTable;
import com.sysco.test.registration.entity.PersonEntity;
import com.sysco.test.registration.mapper.PersonMapper;
import com.sysco.test.registration.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonDaoImpl implements PersonDao {

    @Autowired
    private PersonTable personTable;

    @Override
    public Optional<Person> getPersonByUsername(String username) {
        PersonEntity personEntity= personTable.getData(username);
        if(personEntity != null){
            Person person = PersonMapper.mapToModel(personEntity);
             return Optional.of(person);
        }
        return Optional.ofNullable(null);
    }

    @Override
    public void createPerson(Person person) {
        personTable.insertData(PersonMapper.mapToEntity(person));
    }

    @Override
    public void deletePersonByUsername(String username) {
        personTable.deleteData(username);
    }
}
