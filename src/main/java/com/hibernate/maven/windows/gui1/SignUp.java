//Helpful resources:
//
//Encoding: http://www.baeldung.com/java-base64-encode-and-decode

package com.hibernate.maven.windows.gui1;

import com.hibernate.maven.Encode;
import com.hibernate.maven.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUp {

    static User userObj;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;
    static ServiceRegistry serviceRegistry;
    JFrame frame = new JFrame();
    JButton acceptbtn = new JButton("Enter");
    JTextField userName = new JTextField();
    JTextField password = new JTextField();

    private static SessionFactory buildSessionFactory() {
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");

        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistry);

        return sessionFactoryObj;
    }

    private void btnPress() {
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();


            userObj = new User();

            userObj.setUserName(userName.getText());
            userObj.setUserPassword(Encode.encode(password.getText()));
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date a = new Date();
            Date dateWithoutTime = formatter.parse(formatter.format(a));
            userObj.setUserCreatedDate(dateWithoutTime);

            sessionObj.save(userObj);

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

    public void initWindow() {

        frame.getContentPane().setLayout(null);

        acceptbtn.setBounds(200, 100, 150, 20);

        userName.setBounds(100, 300, 150, 20);
        password.setBounds(300, 300, 150, 20);

        acceptbtn.isVisible();
        userName.isVisible();
        password.isVisible();

        frame.add(acceptbtn);
        frame.add(userName);
        frame.add(password);

        acceptbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                btnPress();
            }
        });

        frame.setVisible(true);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
