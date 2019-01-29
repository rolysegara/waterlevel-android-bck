package com.visionet.indigo_waterlevel.baserealm.user_profile.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmModelUserProfile extends RealmObject{

    @PrimaryKey
    private String name = "";
    private String surname = "";
    private String userName = "";
    private String emailAddress = "";
    private String phoneNumber = "";
    private boolean isPhoneNumberConfirmed;
    private String profilePicture = "";

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getIsPhoneNumberConfirmed() {
        return isPhoneNumberConfirmed;
    }

    public void setIsPhoneNumberConfirmed(boolean isPhoneNumberConfirmed) {
        this.isPhoneNumberConfirmed = isPhoneNumberConfirmed;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
