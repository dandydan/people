package com.dandy.core;

import java.util.List;
import java.util.ArrayList;

import com.dandy.core.PersonDao;
import com.dandy.core.Person;

public class PersonService {
    private PersonDao personDao;

    public void addPerson(Person person) {
        personDao = new PersonDao();
        personDao.addPerson(person);
    }

    public void updatePerson(Person person){
        personDao = new PersonDao();
        personDao.updatePerson(person);
    }
    
    public Person getPersonById(int personId){
        personDao = new PersonDao();
        Person person = personDao.getPersonById(personId);
        return person;
    }

    public void removePerson(Person person) {
        personDao = new PersonDao();
        personDao.removePerson(person);
    }

    public void removeContacts(Person person) {
        personDao = new PersonDao();
        personDao.removeContacts(person);
    }
    public List<Role> getRoles() {
        personDao = new PersonDao();
        return personDao.getRoles();
    }

    public void addRoles(int personId, int roleId) {
        personDao = new PersonDao();
        personDao.addRoles(personId, roleId);
    }

    public List<Role> getRolesById(int personId) {
        personDao = new PersonDao();
        List<Role> roles = personDao.getRolesById(personId);
        return roles;
    }

    public void removeRole(int personId, int roleId) {
        personDao = new PersonDao();
        personDao.removeRole(personId, roleId);
    }

    public List<PersonDTO> getPersons(int conditionVar, String stringToSearch) {
        personDao = new PersonDao();
        List<PersonDTO> result = personDao.getPersons(conditionVar, stringToSearch);
        return result;
    }
}
