package com.dandy.core;

import org.hibernate.HibernateException; 
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.FetchMode;
import org.hibernate.sql.JoinType;
import org.hibernate.stat.Statistics;

import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;

import com.dandy.core.Person;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.infra.HibernateUtil;
class PersonDao {

    private void begin() {
        getSession().beginTransaction();
    }
 
    private void commit() {
        getSession().getTransaction().commit();
    }
 
    private void rollback() {
        getSession().getTransaction().rollback();
    }

    private Session getSession() {
	Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
	if (sess == null) {
	    sess = HibernateUtil.getSessionFactory().openSession();
	}
	return sess;
    }

    public void addPerson(Person person){
        try {
            begin();
      	    Criteria crit = getSession().createCriteria(Person.class);
            crit.add(Restrictions.eq("firstName", person.getFirstName()));
            crit.add(Restrictions.eq("middleName", person.getMiddleName()));
            crit.add(Restrictions.eq("lastName", person.getLastName()));
            crit.setProjection(Property.forName("personId"));
            if ((Integer)crit.uniqueResult()!=null) {
                System.out.println("Person already exist, edit or add another person");
            } else {
               getSession().save(person);
            }
            commit();
        } catch (HibernateException e) {
            rollback();
        }
    }


    public Person getPersonById(int personId) {
        Person person = new Person();
        try {
            begin();
            Criteria crit = getSession().createCriteria(Person.class, "person");
            crit.add(Restrictions.eq("person.personId", personId));
            crit.setFetchMode("contacts", FetchMode.JOIN);
            crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            if (crit.uniqueResult()!=null) {
                person = (Person) crit.uniqueResult();
            } 
            commit();
        } catch (HibernateException e) {
            rollback();
        }
        return person;
    }

    public void updatePerson(Person person){
        try {
            begin();
            getSession().update(person);
            commit();
        } catch (HibernateException e) {
            rollback();
        }
    }

    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<Role>();
        Statistics stats = HibernateUtil.getSessionFactory().getStatistics();
        stats.setStatisticsEnabled(true);
        try {
            begin();
            Criteria crit = getSession().createCriteria(Role.class);
            crit.setCacheable(true);
            roles = crit.list();
            commit();
        } catch (HibernateException e) {
            rollback();
        } finally{
          System.out.println("Second Level Cache hits " + HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
        }
        return roles;
    }

    public void addRoles(int personId, int roleId) {
        try {
            begin();
            Person person = (Person) getSession().load(Person.class, personId);
            Role role = (Role) getSession().get(Role.class, roleId);
            person.getRoles().add(role);
            getSession().update(person);
            commit();
        } catch (HibernateException e) {
            rollback();
        }
    }

    public void removePerson(Person person){
        try {
            begin();
            getSession().delete(person);
            commit();
        } catch (HibernateException e) {
            rollback();
        }
    }

    public void removeContacts(Person person) {
        try {
            begin();
            person.getContacts().clear();
            getSession().update(person);
            commit();
        } catch (HibernateException e) {
            rollback();
        }
    }

    public List<Object[]> getPersons(int conditionVar, String stringToSearch) {
        List<Object[]>  result = new ArrayList<Object[]>();
        try {
            begin();
	    Criteria crit = getSession().createCriteria(Person.class, "person");

            crit.createAlias("contacts", "contacts", Criteria.LEFT_JOIN);
            crit.createAlias("roles", "roles", Criteria.LEFT_JOIN);
            crit.createAlias("address", "address");
            crit.setProjection(Projections.projectionList()
                               .add( Projections.property("person.personId"))
                               .add( Projections.property("person.firstName"))
                               .add( Projections.property("person.lastName"), "lastName")
                               .add( Projections.property("person.gwa"))
                               .add( Projections.property("address.zipcode"))
                               .add( Projections.property("contacts.number"))
                               .add( Projections.property("roles.pos"),"pos")
                               .add( Projections.property("person.birthday")));
            switch(conditionVar) {
                case 9:
                    crit.add(Restrictions.eq("lastName", stringToSearch));
                    break;
                case 10:
                    crit.add(Restrictions.eq("roles.pos", stringToSearch));
                    break;
                case 11:
                    crit.addOrder(Order.asc("lastName"));
                    break;
                case 12:
                    crit.addOrder(Order.asc("person.birthday"));
                    break;
            }
            result = crit.list();
            commit();
        } catch (HibernateException e) {
            rollback();
        }
	return result;
    }

    public List<Object[]> getRolesById(int personId) {
        List<Object[]>  result = new ArrayList<Object[]>();
        try {
            begin();
	    Criteria crit = getSession().createCriteria(Person.class, "person");
            crit.add(Restrictions.eq("person.personId", personId));
            crit.createAlias("roles", "roles", Criteria.LEFT_JOIN);
            crit.setProjection(Projections.projectionList()
                               .add( Projections.property("roles.roleId"))
                               .add( Projections.property("roles.pos")));
            result = crit.list();
            commit();
        } catch (HibernateException e) {
            rollback();
        }
	return result;
    }

    public void removeRole(int personId, int roleId) {
        try {
            begin();
            Person person = (Person) getSession().get(Person.class, personId);
            Role role = (Role) getSession().get(Role.class, roleId);
            person.getRoles().remove(role);
            getSession().update(person);
            commit();
        } catch (HibernateException e) {
            rollback();
        }
    }

}
