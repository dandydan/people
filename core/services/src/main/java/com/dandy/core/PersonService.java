package com.dandy.core;

import java.util.Set;
import java.util.List;
import java.util.Collections;

import com.dandy.core.PersonDao;
import com.dandy.core.Person;

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

    public void updatePerson(Person person){
        PersonDao personDao = new PersonDao();
        personDao.updatePerson(person);
    }
    
    public Person getPerson(String firstName, String middleName, String lastName){
        PersonDao personDao = new PersonDao();
        Person person = personDao.getPerson(firstName, middleName, lastName);
        return person;
    }

    public void removePerson(Person person) {
        PersonDao personDao = new PersonDao();
        personDao.removePerson(person);
    }
    public void addContacts(Person person) {
        PersonDao personDao = new PersonDao();
        personDao.updatePerson(person);
    }
    public void removeContacts(Person person) {
        PersonDao personDao = new PersonDao();
        personDao.removeContacts(person);
    }
    public List<Person> getPersonSortedByName () {
        PersonDao personDao = new PersonDao();
        return personDao.getPersonSortedByName();
    }
    public List<Person> getPersonSortedByBirthday() {
        PersonDao personDao = new PersonDao();
        return personDao.getPersonSortedByBirthday();
    } 
    public List<Person> getPersons() {
        PersonDao personDao = new PersonDao();
        List<Person> personList = personDao.getPersons();
        Collections.sort(personList, new AscendingComparator());
        return personList;
    } 
}
