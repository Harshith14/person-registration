package com.sysco.test.registration.config;

import com.sysco.test.registration.db.PersonTable;
import com.sysco.test.registration.entity.PersonEntity;
import com.sysco.test.registration.utils.PersonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PersonTable personTable;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Authentication auth = null;
       if(checkUsernameAndPassword(username,password)){
            auth = new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
       }
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private  boolean checkUsernameAndPassword(String username, String password){
        PersonEntity personEntity = personTable.getData(username);
        if(personEntity != null){
           byte[] hashedPassword = PersonUtils.hashWithPepper(password);
           if( Arrays.equals(hashedPassword,personEntity.getPassword())){
               return true;
           }
        }
        return false;
    }
}
