package com.dandy.core;

public class Contact {
    private int contactId;
    private String description;
    private long number;

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
    public int getContactId() {
        return this.contactId;
    }
    public String getDescription() {
        return this.description;
    }
    public long getNumber() {
        return this.number;
    }
}
