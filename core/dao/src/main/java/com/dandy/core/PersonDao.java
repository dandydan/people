package com.dandy.core;

import org.hibernate.HibernateException; 
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import com.dandy.core.Person;
import com.dandy.core.Name;
import com.dandy.core.Address;
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
        Query query = session.createQuery("SELECT person from Person person "
                                           + " where person.name.firstName = :firstName "
                                           + " AND person.name.lastName = :lastName " 
                                           + " AND person.name.middleName = :middleName");
        query.setParameter("firstName", firstName);
        query.setParameter("middleName", middleName);
        query.setParameter("lastName", lastName);
        query.setMaxResults(1);
        Person person = (Person) query.uniqueResult();
        session.close(); 
        return person;
    }
    
    public void updatePerson(Person person){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(person);
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

}
