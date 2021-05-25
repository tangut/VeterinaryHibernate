package com.shuv.Utils;

import com.shuv.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Pet.class);
                configuration.addAnnotatedClass(Diagnose.class);
                configuration.addAnnotatedClass(Address.class);
                configuration.addAnnotatedClass(Medicines.class);
                configuration.addAnnotatedClass(Monkey.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Enitial SessionFactory creation failed" + e);
            }
        }
        return sessionFactory;
    }
}