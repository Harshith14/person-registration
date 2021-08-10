package com.sysco.test.registration.utils;

import com.sysco.test.registration.constants.PersonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PersonUtils {

   private final static Logger LOGGER = LoggerFactory.getLogger(PersonUtils.class);

    public static byte[] hashWithPepper(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(PersonConstants.SHA512);
            String passwordWithPepper = password + PersonConstants.PEPPER;
            return md.digest(passwordWithPepper.getBytes(StandardCharsets.UTF_8));

        }catch (NoSuchAlgorithmException e){
            LOGGER.error("Provided hash algorithm not found");

        }
        return null;
    }
}
