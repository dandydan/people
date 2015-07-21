package com.dandy.core;

import java.sql.Date;
import java.util.Set;

public class Person {
    int personId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String title;
    private Date birthday;
    private String employmentStatus;
    private float gwa;
    private Gender gender;
    private Address address;
    private Set<Contact> contacts;

    public Person(){}

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public void setGender(Gender gender) {
        this.gender = gender;
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
    public String getFirstName() {
        return this.firstName;
    }
    public String getMiddleName() {
        return this.middleName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getSuffix() {
        return this.suffix;
    }
    public String getTitle() {
        return this.title;
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
    public Gender getGender() {
        return this.gender;
    }
    public Address getAddress() {
        return this.address;
    }
    public Set<Contact> getContacts() {
        return this.contacts;
    }
}
