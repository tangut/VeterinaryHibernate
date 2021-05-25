package com.shuv.dao;

import com.shuv.Utils.HibernateUtil;
import com.shuv.model.Pet;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PetDao {
    public Pet findById(int id) {
        Pet pet = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        pet = session.get(Pet.class, id);
        if (session != null){
            session.close();
        }
        return pet;
    }

    public ArrayList<Pet> findByMasterID(int masterID){
        List<Pet> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        list = session.createQuery("FROM Pet WHERE user_id = :masterID", Pet.class).setParameter("masterID", masterID).list();
        return (ArrayList<Pet>) list;
    }

    public void save(Pet pet) {
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
        return (ArrayList<Pet>)  HibernateUtil.getSessionFactory().openSession().createQuery("From Pet").list();
    }
}
