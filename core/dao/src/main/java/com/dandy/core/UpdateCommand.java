package com.dandy.core;

import org.hibernate.Session;

public class UpdateCommand implements DbCommand {

    Person person;
    Session session;
    int personId;
    int roleId;
    boolean add;

    UpdateCommand(Person person) {
        this.person = person;
    }

    UpdateCommand(int personId, int roleId, boolean add) {
        this.personId = personId;
        this.roleId = roleId;
        this.add = add;
    }

    @Override
    public void execute (Session session){
        this.session = session;
        if(this.personId != 0){
            updateRole();
        }
        this.session.update(this.person);
    }

    public void updateRole() {
        this.person = (Person) this.session.get(Person.class, this.personId);
        Role role = (Role) session.get(Role.class, this.roleId);

        if (this.add) {
            this.person.getRoles().add(role);
        } else {
            this.person.getRoles().remove(role);
        }
    }
}
