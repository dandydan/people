package com.dandy.core;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.ArrayList;

import com.dandy.core.Person;
import com.dandy.core.PersonDTO;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.infra.HibernateUtil;

class PersonDao {

    SaveCommand     saveCommand;
    UpdateCommand   updateCommand;
    DeleteCommand   deleteCommand;
    ReadCommand     readCommand;

    private Session getSession() {
        Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
	if (sess == null) {
	    sess = HibernateUtil.getSessionFactory().openSession();
	}
	return sess;
    }

    public void updatePerson(Person person){
        updateCommand = new UpdateCommand(person);
        execute(updateCommand);
    }

    public void removeContacts(Person person) {
        person.getContacts().clear();
        updateCommand = new UpdateCommand(person);
        execute(updateCommand);
    }

    public void removePerson(Person person){
        deleteCommand = new DeleteCommand(person);
        execute(deleteCommand);
    }

    public void addPerson(Person person){
        saveCommand = new SaveCommand(person);
        execute(saveCommand);
    }

    public Person getPersonById(int personId) {
        int methodNumber = 1;
        readCommand = new ReadCommand(personId, methodNumber);
        execute(readCommand);
        Person person = readCommand.getPersonById();
        return person;
    }

    public void removeRole(int personId, int roleId) {
        updateCommand = new UpdateCommand(personId, roleId, false);
        execute(updateCommand);
    }

    public void addRoles(int personId, int roleId) {
        updateCommand = new UpdateCommand(personId, roleId, true);
        execute(updateCommand);
    }

    public List<Role> getRoles() {
        int methodNumber = 2;
        int personId = 0;
        readCommand = new ReadCommand(personId, methodNumber);
        List<Role> roles = new ArrayList<Role>();
        execute(readCommand);
        roles = readCommand.getRoleList();
        return roles;
    }

    public List<Role> getRolesById(int personId) {
        int methodNumber = 3;
        readCommand = new ReadCommand(personId, methodNumber);
        List<Role> roles = new ArrayList<Role>();
        execute(readCommand);
        roles = readCommand.getRoleList();
        return roles;
    }

    public List<PersonDTO> getPersons(int conditionVar, String stringToSearch) {
        int methodNumber = 4;
        readCommand = new ReadCommand(conditionVar, stringToSearch, methodNumber);
        List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();
        execute(readCommand);
        personDTOs = readCommand.getPersonDTOs();
        return personDTOs;
    }

    void execute(DbCommand dbCommand) {
        try {
            getSession().beginTransaction();
            dbCommand.execute(getSession());
            getSession().getTransaction().commit();
        } catch (HibernateException e) {
            getSession().getTransaction().rollback();
        }
    }

}
