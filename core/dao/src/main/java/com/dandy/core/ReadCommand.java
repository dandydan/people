package com.dandy.core;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.FetchMode;
import org.hibernate.transform.Transformers;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

public class ReadCommand implements DbCommand {

    int             methodNumber;
    int             personId;
    int             conditionVar;    
    String          stringToSearch;
    Session         session;
    Person          person     = new Person();
    List<Role>      roles      = new ArrayList<Role>();
    List<PersonDTO> personDTOs = new ArrayList<PersonDTO>();


    ReadCommand(int personId, int methodNumber) {
        this.personId = personId;
        this.methodNumber = methodNumber;
    }

    ReadCommand(int conditionVar, String stringToSearch, int methodNumber) {
        this.conditionVar = conditionVar;
        this.stringToSearch = stringToSearch;
        this.methodNumber = methodNumber;
    }

    @Override
    public void execute (Session session){
        this.session = session;
        switch(methodNumber) {
            case 1:
                setPersonById();
                break;
            case 2:
                setRoleList();
                break;
            case 3:
                setRoleListById();
                break;
            case 4:
                setPersonDTOs();
                break;
        }
    }

    public void setPersonById() {
        Criteria crit = this.session.createCriteria(Person.class, "person");
        crit.add(Restrictions.eq("person.personId", this.personId));
        crit.setFetchMode("contacts", FetchMode.JOIN);
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        if (crit.uniqueResult() != null) {
            this.person = (Person) crit.uniqueResult();
        } 
    }

    public Person getPersonById() {
        return this.person;
    }

    public void setRoleList() {
        Criteria crit = this.session.createCriteria(Role.class);
        crit.setCacheable(true);
        this.roles = crit.list();
    }

    public List<Role> getRoleList() {
        return this.roles;
    }

    public void setRoleListById() {
        Criteria crit = this.session.createCriteria(Person.class, "person");
        crit.add(Restrictions.eq("person.personId", personId));
        crit.createAlias("roles", "roles", Criteria.LEFT_JOIN);
        crit.setProjection(Projections.projectionList()
                           .add( Projections.property("roles.roleId"), "roleId")
                           .add( Projections.property("roles.pos"), "pos"));
        crit.setResultTransformer(Transformers.aliasToBean(Role.class));

        this.roles = crit.list();
    }

    public void setPersonDTOs() {
        Criteria crit = this.session.createCriteria(Person.class, "person");
        crit.createAlias("contacts", "contacts", Criteria.LEFT_JOIN);
        crit.createAlias("roles", "roles", Criteria.LEFT_JOIN);
        crit.createAlias("address", "address");
        crit.setProjection(Projections.distinct(Projections.projectionList()
                           .add( Projections.property("person.personId"), "personId")
                           .add( Projections.property("person.firstName"), "firstName")
                           .add( Projections.property("person.lastName"), "lastName")
                           .add( Projections.property("person.gwa"), "gwa")
                           .add( Projections.property("address.zipcode"), "zipcode")
                           .add( Projections.property("person.birthday"), "birthday")
                           .add( Projections.property("contacts.number"), "number")
                           .add( Projections.property("roles.pos"),"pos")));
        crit.setResultTransformer(Transformers.aliasToBean(PersonDTO.class));

        switch(this.conditionVar) {
            case 9:
                crit.add(Restrictions.ilike("lastName", this.stringToSearch, MatchMode.ANYWHERE));
                break;
            case 10:
                crit.add(Restrictions.ilike("roles.pos", this.stringToSearch, MatchMode.ANYWHERE));
                break;
            case 11:
                crit.addOrder(Order.asc("lastName"));
                break;
            case 12:
                crit.addOrder(Order.asc("birthday"));
                break;
        }

        this.personDTOs = crit.list();
    }

    public List<PersonDTO> getPersonDTOs() {
        return this.personDTOs;
    }

}
