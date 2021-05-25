package com.shuv.dao;

import com.shuv.HibernateUtil;
import com.shuv.model.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class AddressDao {
    public Address findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Address.class, id);
    }

    void save(Address address) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(address);
        tx1.commit();
        session.close();
    }

    public void update(Address address) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.merge(address);
        tx1.commit();
        session.close();
    }

    public void delete(Address address) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(address);
        tx1.commit();
        session.close();
    }

    public ArrayList<Address> findAll() {
        return (ArrayList<Address>)  HibernateUtil.getSessionFactory().openSession().createQuery("From Address").list();
    }
}
