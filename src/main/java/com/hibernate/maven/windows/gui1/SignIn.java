package com.hibernate.maven.windows.gui1;

import com.hibernate.maven.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignIn {

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

    public void btnPress() {
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
