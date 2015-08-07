package com.dandy.core;

import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import com.dandy.infra.HibernateUtil;

public class SaveCommand<T> implements DbCommand {
    T t;

    SaveCommand(T t) {
      this.t = t;
    }

    @Override
    public void execute (Session session) {
        session.save(t);
    }
}
