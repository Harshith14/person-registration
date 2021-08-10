package com.sysco.test.registration.service;

import com.sysco.test.registration.dao.PersonDao;
import com.sysco.test.registration.exception.PersonAlreadyExistsException;
import com.sysco.test.registration.exception.PersonNotFoundException;
import com.sysco.test.registration.model.Person;
import com.sysco.test.registration.utils.MockPersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonDao personDao;

    @Autowired
    @InjectMocks
    private PersonService personService;


    @Test
    public void createPersonTestSuccess() throws PersonAlreadyExistsException {
        Person person= MockPersonUtils.createPerson();
        when(personDao.getPersonByUsername(person.getEmailId()))
                .thenReturn(Optional.ofNullable(null));
        doNothing().when(personDao).createPerson(person);
        personService.createPerson(person);
        verify(personDao,times(1)).createPerson(person);
    }

    @Test()
    public void createPersonTestFail() throws PersonAlreadyExistsException {
        Person person= MockPersonUtils.createPerson();
        when(personDao.getPersonByUsername(person.getEmailId()))
                .thenReturn(Optional.of(person));
        Assertions.assertThrows(PersonAlreadyExistsException.class,
                ()->personService.createPerson(person));
    }

    @Test
    public void getPersonByUsernameTestSuccess() throws PersonNotFoundException {
        Person person = MockPersonUtils.createPerson();
        when(personDao.getPersonByUsername(person.getEmailId())).thenReturn(Optional.of(person));
        personService.getPersonByUsername(person.getEmailId());
    }

    @Test
    public void getPersonByUsernameTestFail() throws PersonNotFoundException {
        when(personDao.getPersonByUsername(anyString())).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(PersonNotFoundException.class,
                ()-> personService.getPersonByUsername(anyString()));
    }

    @Test
    public void deletePersonByUsernameTestSuccess() throws PersonNotFoundException {
        Person person= MockPersonUtils.createPerson();
        when(personDao.getPersonByUsername(person.getEmailId()))
                .thenReturn(Optional.of(person));
        doNothing().when(personDao).deletePersonByUsername(person.getEmailId());
        personService.deletePersonByUsername(person.getEmailId());
        verify(personDao,times(1)).deletePersonByUsername(anyString());
    }

    @Test
    public void deletePersonByUsernameTestFail(){
        when(personDao.getPersonByUsername(Mockito.anyString()))
                .thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(PersonNotFoundException.class,
                ()->personService.deletePersonByUsername(Mockito.anyString()));
    }


}
