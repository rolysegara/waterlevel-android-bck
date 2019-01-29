package com.visionet.indigo_waterlevel.baserealm.user_profile.controller;

import android.app.Application;

import com.visionet.indigo_waterlevel.baserealm.RealmController;
import com.visionet.indigo_waterlevel.baserealm.user_profile.model.RealmModelUserProfile;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmControllerUserProfile extends RealmController{
    public RealmControllerUserProfile(Application application) {
        super(application);
    }

    //find all objects in the Book.class
    public RealmResults<RealmModelUserProfile> getUserProfiles() {

        return realm.where(RealmModelUserProfile.class).findAll();
    }

    //query a single item with the given id
    public RealmModelUserProfile getUserProfile(String name) {

        return realm.where(RealmModelUserProfile.class).equalTo("name", name).findFirst();
    }

    //check if Book.class is empty
    public boolean hasUserProfile() {

        return !realm.where(RealmModelUserProfile.class).findAll().isEmpty();
    }

    public void insertUserProfile(
            final RealmModelUserProfile realmModelUserProfile){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmModelUserProfile realmModelUserProfile1 =
                        realm
                                .where(RealmModelUserProfile.class)
                                .equalTo("name", realmModelUserProfile.getName())
                                .findFirst();
                if(realmModelUserProfile1 == null){
                    realmModelUserProfile1 =
                            realm.createObject(RealmModelUserProfile.class, realmModelUserProfile.getName());
                }
                realmModelUserProfile1.setSurname(realmModelUserProfile.getSurname());
                realmModelUserProfile1.setUserName(realmModelUserProfile.getUserName());
                realmModelUserProfile1.setEmailAddress(realmModelUserProfile.getEmailAddress());
                realmModelUserProfile1.setPhoneNumber(realmModelUserProfile.getPhoneNumber());
                realmModelUserProfile1.setIsPhoneNumberConfirmed(realmModelUserProfile.getIsPhoneNumberConfirmed());
                realmModelUserProfile1.setProfilePicture(realmModelUserProfile.getProfilePicture());
            }
        });

    }
}
