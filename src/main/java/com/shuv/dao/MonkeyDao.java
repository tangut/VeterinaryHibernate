package com.shuv.dao;

import com.shuv.HibernateUtil;
import com.shuv.model.Monkey;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class MonkeyDao {
    public Monkey findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Monkey.class, id);
    }

    void save(Monkey monkey) {
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
        return (ArrayList<Monkey>)  HibernateUtil.getSessionFactory().openSession().createQuery("From monkey").list();
    }
}
