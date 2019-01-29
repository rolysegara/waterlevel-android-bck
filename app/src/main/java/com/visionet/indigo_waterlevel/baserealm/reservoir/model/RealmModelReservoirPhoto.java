package com.visionet.indigo_waterlevel.baserealm.reservoir.model;

import android.support.annotation.NonNull;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class RealmModelReservoirPhoto extends RealmObject{

    @PrimaryKey
    private int photoId;
    private String photo;

    @LinkingObjects("realmModelReservoirPhotos")
    private final RealmResults<RealmModelReservoir> reservoirs = null;

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public RealmResults<RealmModelReservoir> getReservoirs() {
        return reservoirs;
    }
}
