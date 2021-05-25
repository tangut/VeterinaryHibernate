package com.shuv.dao;

import com.shuv.HibernateUtil;
import com.shuv.model.Medicines;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class MedicinesDao {
    public Medicines findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Medicines.class, id);
    }

    void save(Medicines medicines) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(medicines);
        tx1.commit();
        session.close();
    }

    public void update(Medicines medicines) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(medicines);
        tx1.commit();
        session.close();
    }

    public void delete(Medicines medicines) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(medicines);
        tx1.commit();
        session.close();
    }

    public ArrayList<Medicines> findAll() {
        return (ArrayList<Medicines>)  HibernateUtil.getSessionFactory().openSession().createQuery("From medicines").list();
    }
}
