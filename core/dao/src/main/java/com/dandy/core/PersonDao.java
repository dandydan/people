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

import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;

import com.dandy.core.Person;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.infra.HibernateUtil;
class PersonDao {

    private Session getSession() {
	Session sess = HibernateUtil.getSessionFactory().getCurrentSession();
	if (sess == null) {
	    sess = HibernateUtil.getSessionFactory().openSession();
	}
	return sess;
    }

    public void addPerson(Person person){
            getSession().beginTransaction();
            Query query = getSession().createQuery("SELECT person from Person person "
                                           + " where person.firstName = :firstName "
                                           + " AND person.lastName = :lastName " 
                                           + " AND person.middleName = :middleName");
            query.setParameter("firstName", person.getFirstName());
            query.setParameter("middleName", person.getMiddleName());
            query.setParameter("lastName", person.getLastName());
            if (query.list().size()==0) {
                getSession().save(person);
            } else {
                System.out.println("Person already exist, edit or add another person");
            }
        getSession().getTransaction().commit();
    }

    public Person getPersonById(int personId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(Person.class, "person");
        crit.setFetchMode("contacts", FetchMode.JOIN);
        crit.add(Restrictions.eq("person.personId", personId));
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        Person person = (Person) crit.uniqueResult();
        session.getTransaction().commit();
        return person;
    }

    public void addRoles(int personId, List<Integer> roleIds) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Person person = (Person) session.load(Person.class, personId);
        Role role;
        for(Integer roleId : roleIds) {
            role = (Role) session.get(Role.class, roleId);
            person.getRoles().add( role );
        }
        session.update(person);
        session.getTransaction().commit();
    }
    
    public void updatePerson(Person person){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(person);
        session.getTransaction().commit();
    }

    public void removePerson(Person person){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(person);
        session.getTransaction().commit();
    }

    public void removeContacts(Person person) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        person.getContacts().clear();
        session.update(person);
        session.getTransaction().commit();
    }

    public List<Person> getPersonSortedByName() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
	Criteria crit = session.createCriteria(Person.class);
	crit.addOrder(Order.asc("lastName"));
        crit.addOrder(Order.asc("firstName"));
        List<Person> persons = crit.list();
        session.getTransaction().commit();
	return persons;
    }

    public List<Person> getPersonSortedByBirthday() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
	Criteria crit = session.createCriteria(Person.class);
        crit.addOrder(Order.asc("birthday"));
        List<Person> persons = crit.list();
        session.getTransaction().commit();
	return persons;
    }
    public List<Person> getPersons() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
	Criteria crit = session.createCriteria(Person.class, "person");
        session.getTransaction().commit();
        List<Person> persons = new ArrayList<Person>();
	return persons;
    }


   /* public List<Person> getPersons() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
	Criteria crit = session.createCriteria(Person.class);
        List<Person> persons = crit.list();
        session.getTransaction().commit();
	return persons;
    }*/
}
