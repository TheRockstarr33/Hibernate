package com.hibernate.maven;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userid;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass_word")
    private String passWord;

    @Column(name = "created_date")
    private Date createdDate;

    //Getters and setters for private variables used in this class
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return passWord;
    }

    public void setUserPassword(String userPassword) {this.passWord = userPassword;}

    public Date getUserCreatedDate() {
        return createdDate;
    }

    public void setUserCreatedDate(Date userCreatedDate) {
        this.createdDate = userCreatedDate;
    }
}
