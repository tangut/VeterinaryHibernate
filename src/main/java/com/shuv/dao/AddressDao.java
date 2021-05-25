package com.shuv.dao;

import com.shuv.Utils.HibernateUtil;
import com.shuv.model.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AddressDao {
    public Address findById(int id) {
        Address address = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        address = session.get(Address.class, id);
        if (session != null){
            session.close();
        }
        return address;
    }

    public Address findByFullAddres(String city, String street, String building){
        Address address = null;
        List<Address> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        list = session.createQuery("FROM Address WHERE city = :city and street = :street and building = :building", Address.class).
                setParameter("city", city).setParameter("street", street).setParameter("building", building).list();
        if (session != null){
            session.close();
        }
        if (!list.isEmpty()){
            address = list.get(0);
        }
        return address;
    }
    public void save(Address address) {
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
