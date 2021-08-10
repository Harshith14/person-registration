package com.sysco.test.registration.entity;

import com.sysco.test.registration.utils.PersonUtils;

public class PersonEntity {

    private String id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String emailId;
    private byte[] password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PersonUtils.hashWithPepper(password);
    }


}
