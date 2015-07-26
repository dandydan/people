package com.dandy.core;

import java.util.List;

public class ResultModel {
    private int personId;
    private String firstName;
    private String lastName;
    private float gwa;
    private List<Long> number;
    private List<String> pos;
    private int zipcode;
    
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGwa(float gwa) {
        this.gwa = gwa;
    }

    public void setNumber(List<Long> number) {
        this.number = number;
    }

    public void setPos(List<String> pos) {
        this.pos = pos;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public int getPersonId() {
        return this.personId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public float getGwa() {
        return this.gwa;
    }

    public List<Long> getNumber() {
        return this.number;
    }

    public List<String> getPos() {
        return this.pos;
    }

    public int getZipcode() {
        return this.zipcode;
    }

}
