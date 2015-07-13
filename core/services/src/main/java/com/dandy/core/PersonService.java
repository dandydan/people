package com.dandy.core;

import java.util.Set;

import com.dandy.core.PersonDao;
public class PersonService {
    public void addPerson(Person person, Name name, Address address, Set<Contact> contacts) {
        PersonDao personDao = new PersonDao();
        person.setName(name);
        person.setAddress(address);
        person.setContacts(contacts);
        name.setPerson(person);
        address.setPerson(person);
        personDao.addPerson(person);
    }

}
