package com.hibernate.maven;

import com.hibernate.maven.windows.gui1.SignUp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    static User userObj;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
//        configObj.configure();

//        ServiceRegistry serviceRegistryObj = (ServiceRegistry) new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
//        sessionFactoryObj = configObj.buildSessionFactory((org.hibernate.service.ServiceRegistry) serviceRegistryObj);


        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistry);


        return sessionFactoryObj;
    }

    public static void auto() {
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            for(int i = 109; i <= 112; i++) {
                userObj = new User();
                userObj.setUserid(i);
                userObj.setUserName("NUMBER " + i);
                userObj.setUserPassword("potatoes");
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date a = new Date();
                Date dateWithoutTime = formatter.parse(formatter.format(a));
                userObj.setUserCreatedDate(dateWithoutTime);

                sessionObj.save(userObj);
            }

            System.out.println("Records saved correctly!");

            sessionObj.getTransaction().commit();

        } catch (Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("Transaction being rolled back");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj!=null) {
                sessionObj.close();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hibernate Maven");

        SignUp gui = new SignUp();
        gui.initWindow();
    }
}
