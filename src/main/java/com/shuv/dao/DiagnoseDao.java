package com.shuv.dao;

import com.shuv.Utils.HibernateUtil;
import com.shuv.model.Diagnose;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DiagnoseDao {
    public Diagnose findById(int id) {
        Diagnose diagnose = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        diagnose = session.get(Diagnose.class, id);
        if (session != null){
            session.close();
        }
        return diagnose;
    }

    public Diagnose findByNameSimptome(String name, String simptome){
        Diagnose diagnose = null;
        List<Diagnose> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        list = session.createQuery("FROM Diagnose WHERE name = :name and simptome = :simptome", Diagnose.class).
                setParameter("name", name).setParameter("simptome", simptome).list();
        if (!list.isEmpty()){
            diagnose = list.get(0);
        }
        if (session != null){
            session.close();
        }
        return diagnose;
    }

    public void save(Diagnose diagnose) {
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
        return (ArrayList<Diagnose>)  HibernateUtil.getSessionFactory().openSession().createQuery("From Diagnose").list();
    }
}
