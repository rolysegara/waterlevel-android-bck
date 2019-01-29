package com.visionet.indigo_waterlevel.modulregister.model;

public class ModelRequestRegister {

    /**
     * name : klenting
     * surname : klenting
     * userName : klenting
     * emailAddress : klentingr@gmail.com
     * password : 12345678
     */

    private String name;
    private String surname;
    private String userName;
    private String emailAddress;
    private String password;

    public ModelRequestRegister(String name, String surname, String userName, String emailAddress, String password) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
