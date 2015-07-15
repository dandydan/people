package com.dandy.core;

import org.hibernate.HibernateException; 
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import java.util.List;
import java.util.Set;

import com.dandy.core.Person;
import com.dandy.core.Name;
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.infra.HibernateUtil;
class PersonDao {
    public void addPerson(Person person){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(person);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally {
            session.close(); 
        }
    }

    public Person getPerson(String firstName, String middleName, String lastName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Person person = new Person();
        Query query = session.createQuery("SELECT person from Person person "
                                           + " where person.name.firstName = :firstName "
                                           + " AND person.name.lastName = :lastName " 
                                           + " AND person.name.middleName = :middleName");
        query.setParameter("firstName", firstName);
        query.setParameter("middleName", middleName);
        query.setParameter("lastName", lastName);
        query.setMaxResults(1);
        if(query.list().size()!=0) {
            person = (Person) query.uniqueResult();
        }
        session.close(); 
        return person;
    }
    
    public void updatePerson(Person person){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.merge(person);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally {
            session.close(); 
        }
    }

    public void removePerson(Person person){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(person);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally {
            session.close(); 
        }
    }

    public void addContactsDao( Set<Contact> contacts) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int i = 0;
        try{
            tx = session.beginTransaction();
            for (Contact contact : contacts) {
                session.save(contact);
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally {
            session.close(); 
        }
    }

    public void deleteContactsDao(Person person) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int i = 0;
        try{
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Contact "
                                              + " where personId = :personId ");
            query.setParameter("personId", person.getPersonId());
            query.executeUpdate();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally {
            session.close(); 
        }
    }

    public List<Person> getPersonSortedByName() {
        Session session = HibernateUtil.getSessionFactory().openSession();
	Criteria crit = session.createCriteria(Person.class);
        crit.createAlias("name", "name");
	crit.addOrder(Order.asc("name.lastName"));
        crit.addOrder(Order.asc("name.firstName"));
	return crit.list();
    }

    public List<Person> getPersonSortedByBirthday() {
        Session session = HibernateUtil.getSessionFactory().openSession();
	Criteria crit = session.createCriteria(Person.class);
        crit.addOrder(Order.asc("birthday"));
	return crit.list();
    }
    public List<Person> getPersons() {
        Session session = HibernateUtil.getSessionFactory().openSession();
	Criteria crit = session.createCriteria(Person.class);
	return crit.list();
    }
}
