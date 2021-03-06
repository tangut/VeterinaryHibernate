package com.shuv.dao;

import com.shuv.Utils.HibernateUtil;
import com.shuv.model.Medicines;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MedicinesDao {

    public Medicines findByDescription(String description){
        Medicines medicines = null;
        List<Medicines> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        list = session.createQuery("FROM Medicines WHERE description = :description", Medicines.class).setParameter("description", description).list();
        if (!list.isEmpty()){
            medicines = list.get(0);
        }
        if (session != null){
            session.close();
        }
        return medicines;
    }


    public Medicines findById(int id) {
        Medicines medicines = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        medicines = session.get(Medicines.class, id);
        if (session != null){
            session.close();
        }
        return medicines;
    }

    public void save(Medicines medicines) {
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
        return (ArrayList<Medicines>)  HibernateUtil.getSessionFactory().openSession().createQuery("From Medicines").list();
    }
}
