package com.ktpm.BankingDomainApplicationTesting.entity;

import javax.persistence.Entity;


public class LoginEntity {
    private String userName;
    private String userPassword;
    private String role;

    public LoginEntity() {
    }

    public LoginEntity(String userName, String userPassword, String role) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
