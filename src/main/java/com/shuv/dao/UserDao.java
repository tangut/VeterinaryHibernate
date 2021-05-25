package com.shuv.dao;

import com.shuv.HibernateUtil;
import com.shuv.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {

    private static Session session;

    public void addUser(User user) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch(Exception sqlException) {
            if(session.getTransaction() != null) {
                System.out.println("\nTransaction is being rolled back\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public void updateUser(User user) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch(Exception sqlException) {
            if(session.getTransaction() != null) {
                System.out.println("\nTransaction is being rolled back\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public void removeUserById(int id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(getUserById(id));
            transaction.commit();
        } catch(Exception sqlException) {
            if(session.getTransaction() != null) {
                System.out.println("\nTransaction is being rolled back\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public void removeUserByName(String name) {
        getUserByName(name);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User WHERE name = :name").setParameter("name", name);
            query.executeUpdate();
            transaction.commit();
        } catch(Exception sqlException) {
            if(session.getTransaction() != null) {
                System.out.println("\nTransaction is being rolled back\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public void removeAll() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User");
            query.executeUpdate();
            transaction.commit();
        } catch(Exception sqlException) {
            if(session.getTransaction() != null) {
                System.out.println("\nTransaction is being rolled back\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    public User getUserById(int id) {
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch(Exception sqlException) {
            if(session.getTransaction() != null) {
                System.out.println("\nTransaction is being rolled back\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return user;
    }

    public List<User> getUserByName(String name) {
        List<User> usersList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            usersList = session.createQuery("FROM User WHERE name = :name", User.class).setParameter("name", name).list();
            transaction.commit();
        } catch(Exception sqlException) {
            if(session.getTransaction() != null) {
                System.out.println("\nTransaction is being rolled back\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return usersList;
    }

    public List<User> getAllUsers() {
        List<User> usersList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            usersList = session.createQuery("FROM User", User.class).list();
            transaction.commit();
        } catch(Exception sqlException) {
            if(session.getTransaction() != null) {
                System.out.println("\nTransaction is being rolled back\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return usersList;
    }


    public User getUserByLogin(String login){
        User user = null;
        List<User> userList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            userList = session.createQuery("FROM User WHERE login = :login", User.class).setParameter("login", login).list();
            transaction.commit();
        } catch(Exception sqlException) {
            if(session.getTransaction() != null) {
                System.out.println("\nTransaction is being rolled back\n");
                session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        if (!userList.isEmpty()){
            user = userList.get(0);
        }
        return user;
    }
}