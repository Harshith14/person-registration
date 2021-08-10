package com.sysco.test.registration.db;

import com.sysco.test.registration.entity.PersonEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PersonTable {
   private static Map<String, PersonEntity> map= new HashMap();

   public  PersonEntity getData(String username){
       return map.get(username);
   }

    public void insertData(PersonEntity personEntity){
        map.put(personEntity.getEmailId(),personEntity);
    }

    public  void deleteData(String username){
       map.remove(username);
    }

}
