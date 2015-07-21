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
import com.dandy.core.Address;
import com.dandy.core.Contact;
import com.dandy.infra.HibernateUtil;
class PersonDao {
    public void addPerson(Person person){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery("SELECT person from Person person "
                                           + " where person.firstName = :firstName "
                                           + " AND person.lastName = :lastName " 
                                           + " AND person.middleName = :middleName");
            query.setParameter("firstName", person.getFirstName());
            query.setParameter("middleName", person.getMiddleName());
            query.setParameter("lastName", person.getLastName());
            if (query.list().size()==0) {
                session.save(person);
            } else {
                System.out.println("Person already exist, edit or add another person");
            }
        session.getTransaction().commit();
    }

    public Person getPerson(String firstName, String middleName, String lastName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Person person = new Person();
        session.beginTransaction();
        Query query = session.createQuery("SELECT person from Person person "
                                           + " where person.firstName = :firstName "
                                           + " AND person.lastName = :lastName " 
                                           + " AND person.middleName = :middleName");
        query.setParameter("firstName", firstName);
        query.setParameter("middleName", middleName);
        query.setParameter("lastName", lastName);
        query.setMaxResults(1);
        if(query.list().size()!=0) {
            person = (Person) query.uniqueResult();
        }else{
            session.getTransaction().commit();
        }
        return person;
    }
    
    public void updatePerson(Person person){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.update(person);
        session.getTransaction().commit();
    }

    public void removePerson(Person person){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(person);
        session.getTransaction().commit();
    }

    public void removeContacts(Person person) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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
	Criteria crit = session.createCriteria(Person.class);
        List<Person> persons = crit.list();
        session.getTransaction().commit();
	return persons;
    }
}
