package com.dandy.core;

import org.hibernate.Session;

public class DeleteCommand implements DbCommand {

    Person person;

    DeleteCommand(Person person) {
      this.person = person;
    }
    @Override
    public void execute (Session session){
        session.delete(person);
    }

}
