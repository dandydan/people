package com.dandy.core;

import java.sql.Date;
import java.util.Set;

public class Person {
    int personId;
    Date birthday;
    String employmentStatus;
    float gwa;
    String gender;
    Name name;
    Address address;
    Set<Contact> contacts;

    public Person(){}

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }
    public void setGwa(float gwa) {
        this.gwa = gwa;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setName(Name name) {
        this.name = name;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
    public int getPersonId() {
        return this.personId;
    }
    public Date getBirthday() {
        return this.birthday;
    }
    public String getEmploymentStatus() {
        return this.employmentStatus;
    }
    public float getGwa() {
        return this.gwa;
    }
    public String getGender() {
        return this.gender;
    }
    public Name getName() {
        return this.name;
    }
    public Address getAddress() {
        return this.address;
    }
    public Set<Contact> getContacts() {
        return this.contacts;
    }
}
