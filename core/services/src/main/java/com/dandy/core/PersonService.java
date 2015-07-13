package com.dandy.core;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dandy.core.PersonDao;
public class PersonService {

    

    public void addPerson(Person person, Name name, Address address) {
        PersonDao personDao = new PersonDao();
        personDao.addPerson(person, name, address);
    }
    public java.sql.Date dateFormatter(String date) throws ParseException {
		java.sql.Date sql = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = format.parse(date);
		sql = new java.sql.Date(parsed.getTime());
		return sql;
	}
}
