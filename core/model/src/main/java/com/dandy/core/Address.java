package com.dandy.core;

public class Address {
    private int personId;
    private int stNo;
    private String brgy;
    private String subdivision;
    private String city;
    private int zipcode;
    private Person person;

    public Address() {}

    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public void setStNo(int stNo) {
        this.stNo = stNo;
    }
    public void setBrgy(String brgy) {
        this.brgy = brgy;
    }
    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public int getPersonId() {
        return this.personId;
    }
    public int getStNo() {
        return this.stNo;
    }
    public String getBrgy() {
        return this.brgy;
    }
    public String getSubdivision() {
        return this.subdivision;
    }
    public String getCity() {
        return this.city;
    }
    public int getZipcode() {
        return this.zipcode;
    }
    public Person getPerson() {
        return this.person;
    }
}
