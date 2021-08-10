package com.sysco.test.registration.dao;

import com.sysco.test.registration.dao.impl.PersonDaoImpl;
import com.sysco.test.registration.db.PersonTable;
import com.sysco.test.registration.entity.PersonEntity;
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

@ExtendWith(MockitoExtension.class)
public class PersonDaoTest {

    @Autowired
    @InjectMocks
    private PersonDaoImpl personDao;

    @Mock
    private PersonTable personTable;

    @Test
    public void createPersonTest(){
       Person person= MockPersonUtils.createPerson();
        Mockito.doNothing().when(personTable).insertData(Mockito.any(PersonEntity.class));
        personDao.createPerson(person);
        Mockito.verify(personTable,Mockito.times(1)).insertData(Mockito.any(PersonEntity.class));
    }

    @Test
    public void getPersonByUsernameTestSuccess(){
       PersonEntity personEntity = MockPersonUtils.createPersonEntity();
       Mockito.when(personTable.getData(personEntity.getEmailId())).thenReturn(personEntity);
       Optional<Person> person = personDao.getPersonByUsername(personEntity.getEmailId());
       Assertions.assertEquals("Harshu",person.get().getFirstName());
        Assertions.assertEquals("Jangam",person.get().getLastName());
        Assertions.assertEquals("92991416",person.get().getMobileNumber());
        Assertions.assertEquals("harshu@gmail.com",person.get().getEmailId());
        Assertions.assertEquals(null,person.get().getPassword());

    }

    @Test
    public void getPersonByUsernameTestFail(){
        Mockito.when(personTable.getData(Mockito.anyString())).thenReturn(null);
        Optional<Person> person= personDao.getPersonByUsername(Mockito.anyString());
        Assertions.assertEquals(Optional.empty(),person);
    }

    @Test
    public void deletePersonByUsernameTest(){
        Mockito.doNothing().when(personTable).deleteData(Mockito.anyString());
        personDao.deletePersonByUsername(Mockito.anyString());
        Mockito.verify(personTable,Mockito.times(1)).deleteData(Mockito.anyString());
    }

}
