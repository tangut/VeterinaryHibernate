package com.shuv.dao;

import com.shuv.Utils.HibernateUtil;
import com.shuv.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;



import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public User findById(int id) {
        User user = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        user = session.get(User.class, id);
        if (session != null){
            session.close();
        }
        return user;
    }

    public User findByLogin(String login){
        User user = null;
        List<User> list = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        list = session.createQuery("FROM User WHERE login = :login", User.class).setParameter("login", login).list();
        if (!list.isEmpty()){
            user = list.get(0);
        }
        if (session != null){
            session.close();
        }
        return user;
    }

    public void save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

   public void update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public ArrayList<User> findAll() {
        return (ArrayList<User>)  HibernateUtil.getSessionFactory().openSession().createQuery("From User").list();
    }
}