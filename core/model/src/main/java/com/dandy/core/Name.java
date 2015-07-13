package com.dandy.core;

public class Name {
    private int personId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String title;
    private Person person;

    public Name() {}

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
    public void setPerson(Person person) {
        this.person = person;
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
    public Person getPerson() {
        return this.person;
    }
}
