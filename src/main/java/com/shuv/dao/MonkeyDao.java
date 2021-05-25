package com.shuv.dao;

import com.shuv.Utils.HibernateUtil;
import com.shuv.model.Monkey;
import com.shuv.model.Pet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class MonkeyDao {
    public Monkey findById(int id) {
        Monkey pet = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        pet = session.get(Monkey.class, id);
        if (session != null){
            session.close();
        }
        return pet;
    }

    public void save(Monkey monkey) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(monkey);
        tx1.commit();
        session.close();
    }

    public void update(Monkey monkey) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(monkey);
        tx1.commit();
        session.close();
    }

    public void delete(Monkey monkey) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(monkey);
        tx1.commit();
        session.close();
    }

    public ArrayList<Monkey> findAll() {
        return (ArrayList<Monkey>)  HibernateUtil.getSessionFactory().openSession().createQuery("From Monkey").list();
    }
}
