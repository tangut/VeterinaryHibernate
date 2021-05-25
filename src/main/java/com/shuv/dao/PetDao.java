package com.shuv.dao;

import com.shuv.HibernateUtil;
import com.shuv.model.Pet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class PetDao {
    public Pet findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Pet.class, id);
    }

    void save(Pet pet) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(pet);
        tx1.commit();
        session.close();
    }

    public void update(Pet pet) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(pet);
        tx1.commit();
        session.close();
    }

    public void delete(Pet pet) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(pet);
        tx1.commit();
        session.close();
    }

    public ArrayList<Pet> findAll() {
        return (ArrayList<Pet>)  HibernateUtil.getSessionFactory().openSession().createQuery("From pets").list();
    }
}
