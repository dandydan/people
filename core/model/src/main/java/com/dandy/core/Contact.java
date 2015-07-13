package com.dandy.core;

public class Contact {
    private int contactId;
    private String description;
    private long number;
    private int personId;
    private Person person;

    public Contact() {}

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setNumber(long number) {
        this.number = number;
    }
    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public int getContactId() {
        return this.contactId;
    }
    public String getDescription() {
        return this.description;
    }
    public long getNumber() {
        return this.number;
    }
    public int getPersonId() {
        return this.personId;
    }
    public Person getPerson() {
        return this.person;
    }
}
