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
    public void addPerson(Person person, Name name, Address address){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        person.setName(name);
        person.setAddress(address);
        name.setPerson(person);
        address.setPerson(person);
        
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
}
