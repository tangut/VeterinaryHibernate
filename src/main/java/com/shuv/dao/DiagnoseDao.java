package com.shuv.dao;

import com.shuv.HibernateUtil;
import com.shuv.model.Diagnose;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class DiagnoseDao {
    public Diagnose findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Diagnose.class, id);
    }

    void save(Diagnose diagnose) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(diagnose);
        tx1.commit();
        session.close();
    }

    public void update(Diagnose diagnose) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(diagnose);
        tx1.commit();
        session.close();
    }

    public void delete(Diagnose diagnose) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(diagnose);
        tx1.commit();
        session.close();
    }

    public ArrayList<Diagnose> findAll() {
        return (ArrayList<Diagnose>)  HibernateUtil.getSessionFactory().openSession().createQuery("From diagnoses").list();
    }
}
