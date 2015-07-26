package com.dandy.core;

import java.util.List;
import java.util.ArrayList;

import com.dandy.core.PersonDao;
import com.dandy.core.Person;
import com.dandy.core.ResultModel;

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
        List<Object[]> result = personDao.getRolesById(personId);
        List<Role> roles = new ArrayList<Role>();
        try{
            for(Object[] object : result) {
                Role role = new Role();
                role.setRoleId((Integer) object[0]);
                role.setPos((String) object[1]);
                roles.add(role);
            }
        } catch (NullPointerException e) {
        }
        return roles;
    }

    public void removeRole(int personId, int roleId) {
        personDao = new PersonDao();
        personDao.removeRole(personId, roleId);
    }

    public List<ResultModel> getPersons(int conditionVar, String stringToSearch) {
        personDao = new PersonDao();
        List<Object[]> result = personDao.getPersons(conditionVar, stringToSearch);
        List<ResultModel> personInfos = new ArrayList<ResultModel>();

        for(Object[] object : result) {
            List<Long> number = new ArrayList<Long>();
            List<String> pos = new ArrayList<String>();
            ResultModel resultModel = new ResultModel();
            resultModel.setPersonId((Integer)object[0]);
            resultModel.setFirstName((String) object[1]);
            resultModel.setLastName((String) object[2]);
            resultModel.setGwa((Float) object[3]);
            resultModel.setZipcode((Integer) object[4]);

            if((personInfos.size()!= 0) && (personInfos.get(personInfos.size()-1).getPersonId() == resultModel.getPersonId())) {
                if(object[5] != null) {
                    personInfos.get(personInfos.size()-1).getNumber().add((Long)object[5]);
                }
                if(object[6] != null) {
                    personInfos.get(personInfos.size()-1).getPos().add((String)object[6]);
                }
            } else {
                if(object[5] != null) {
                    number.add((Long)object[5]);
                    resultModel.setNumber(number);
                }
                if(object[6] != null) {
                    pos.add((String)object[6]);
                    resultModel.setPos(pos);
                }
                personInfos.add(resultModel);
            }
        }
        return personInfos;
    }
}
