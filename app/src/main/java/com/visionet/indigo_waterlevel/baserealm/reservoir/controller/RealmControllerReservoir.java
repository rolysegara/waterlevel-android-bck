package com.visionet.indigo_waterlevel.baserealm.reservoir.controller;

import android.app.Application;
import android.util.Log;

import com.visionet.indigo_waterlevel.baserealm.RealmController;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoirPhoto;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoirSensor;
import com.visionet.indigo_waterlevel.baserealm.reservoirs_level.model.RealmModelReservoirsLevel;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils;

import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmControllerReservoir extends RealmController {
    public RealmControllerReservoir(Application application) {
        super(application);
    }

    //find all objects in the Book.class
    public RealmResults<RealmModelReservoir> getReservoirs() {

        return realm.where(RealmModelReservoir.class).findAll().sort("distance", Sort.ASCENDING);
    }

    public RealmResults<RealmModelReservoir> getReservoirsByName(String name) {
        return realm.where(RealmModelReservoir.class)
                .contains("name", name, Case.INSENSITIVE)
                .findAll().sort("distance", Sort.ASCENDING);
    }

    public RealmResults<RealmModelReservoir> getReservoirsBookmarked(boolean isBookmarked) {

        return realm.where(RealmModelReservoir.class).equalTo("isBookmarked", isBookmarked).findAll().sort("distance", Sort.ASCENDING);
    }

    //query a single item with the given id
    public RealmModelReservoir getReservoirById(int id) {

        return realm.where(RealmModelReservoir.class).equalTo("id", id).findFirst();
    }

    public RealmResults<RealmModelReservoirSensor> getReservoirSensors(int idReservoir){
        return realm.where(RealmModelReservoirSensor.class)
                .equalTo("reservoirs.id", idReservoir)
                .findAll();
    }

    public RealmResults<RealmModelReservoirPhoto> getReservoirPhotos(int idReservoir){
        return realm.where(RealmModelReservoirPhoto.class)
                .equalTo("reservoirs.id", idReservoir)
                .findAll();
    }

    //check if Book.class is empty
    public boolean hasReservoir() {

        return !realm.where(RealmModelReservoir.class).findAll().isEmpty();
    }

    public void insertReservoir(
            final RealmModelReservoir realmModelReservoir){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmModelReservoir realmModelReservoir1 =
                        realm
                                .where(RealmModelReservoir.class)
                                .equalTo("id", realmModelReservoir.getId())
                                .findFirst();
                if(realmModelReservoir1 == null){
                    realmModelReservoir1 =
                            realm.createObject(RealmModelReservoir.class, realmModelReservoir.getId());
                }
                realmModelReservoir1.setCode(realmModelReservoir.getCode());
                realmModelReservoir1.setName(realmModelReservoir.getName());
                realmModelReservoir1.setAddress(realmModelReservoir.getAddress());
                realmModelReservoir1.setDescription(realmModelReservoir.getDescription());
                realmModelReservoir1.setDepth(realmModelReservoir.getDepth());
                realmModelReservoir1.setVolume(realmModelReservoir.getVolume());
                realmModelReservoir1.setAreaId(realmModelReservoir.getAreaId());
                realmModelReservoir1.setAreaName(realmModelReservoir.getAreaName());
                realmModelReservoir1.setLatitude(realmModelReservoir.getLatitude());
                realmModelReservoir1.setLongitude(realmModelReservoir.getLongitude());
                realmModelReservoir1.setDistance(realmModelReservoir.getDistance());
                realmModelReservoir1.setDistanceDescription(realmModelReservoir.getDistanceDescription());
                realmModelReservoir1.setLastLevelAverage(realmModelReservoir.getLastLevelAverage());
                realmModelReservoir1.setLastStatusAverage(realmModelReservoir.getLastStatusAverage());
                realmModelReservoir1.setCreationTime(realmModelReservoir.getCreationTime());
                realmModelReservoir1.setModificationTime(realmModelReservoir.getModificationTime());
                realmModelReservoir1.setBookmarked(realmModelReservoir.isBookmarked());
                realmModelReservoir1.setRealmModelReservoirPhotos(realmModelReservoir.getRealmModelReservoirPhotos());
                realmModelReservoir1.setRealmModelReservoirSensors(realmModelReservoir.getRealmModelReservoirSensors());
            }
        });

    }

    public void insertReservoir(
            final RealmList<RealmModelReservoir> realmModelReservoirs){

        for (final RealmModelReservoir realmModelReservoir: realmModelReservoirs) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmModelReservoir realmModelReservoir1 =
                            realm
                                    .where(RealmModelReservoir.class)
                                    .equalTo("id", realmModelReservoir.getId())
                                    .findFirst();
                    if(realmModelReservoir1 == null){
                        realmModelReservoir1 =
                                realm.createObject(RealmModelReservoir.class, realmModelReservoir.getId());
                    }
                    realmModelReservoir1.setCode(realmModelReservoir.getCode());
                    realmModelReservoir1.setName(realmModelReservoir.getName());
                    realmModelReservoir1.setAddress(realmModelReservoir.getAddress());
                    realmModelReservoir1.setDescription(realmModelReservoir.getDescription());
                    realmModelReservoir1.setDepth(realmModelReservoir.getDepth());
                    realmModelReservoir1.setVolume(realmModelReservoir.getVolume());
                    realmModelReservoir1.setAreaId(realmModelReservoir.getAreaId());
                    realmModelReservoir1.setAreaName(realmModelReservoir.getAreaName());
                    realmModelReservoir1.setLatitude(realmModelReservoir.getLatitude());
                    realmModelReservoir1.setLongitude(realmModelReservoir.getLongitude());
                    realmModelReservoir1.setDistance(realmModelReservoir.getDistance());
                    realmModelReservoir1.setDistanceDescription(realmModelReservoir.getDistanceDescription());
                    realmModelReservoir1.setLastLevelAverage(realmModelReservoir.getLastLevelAverage());
                    realmModelReservoir1.setLastStatusAverage(realmModelReservoir.getLastStatusAverage());
                    realmModelReservoir1.setCreationTime(realmModelReservoir.getCreationTime());
                    realmModelReservoir1.setModificationTime(realmModelReservoir.getModificationTime());
                    realmModelReservoir1.setBookmarked(realmModelReservoir.isBookmarked());

                    //realmModelReservoir1.setRealmModelReservoirPhotos(realmModelReservoir.getRealmModelReservoirPhotos());
                    //realmModelReservoir1.setRealmModelReservoirSensors(realmModelReservoir.getRealmModelReservoirSensors());

                    if(realmModelReservoir.getRealmModelReservoirPhotos() != null) {
                        for (final RealmModelReservoirPhoto realmModelReservoirPhoto : realmModelReservoir.getRealmModelReservoirPhotos()) {

                            RealmModelReservoirPhoto realmModelReservoirPhoto1 =
                                    realm
                                            .where(RealmModelReservoirPhoto.class)
                                            .equalTo("photoId", realmModelReservoirPhoto.getPhotoId())
                                            .findFirst();
                            if (realmModelReservoirPhoto1 == null) {
                                realmModelReservoirPhoto1 =
                                        realm.createObject(RealmModelReservoirPhoto.class, realmModelReservoirPhoto.getPhotoId());
                            }

                            realmModelReservoirPhoto1.setPhoto(realmModelReservoirPhoto.getPhoto());

                            realmModelReservoir1.getRealmModelReservoirPhotos().add(realmModelReservoirPhoto1);

                        }
                    }

                    if(realmModelReservoir.getRealmModelReservoirSensors() != null) {
                        for (final RealmModelReservoirSensor realmModelReservoirSensor : realmModelReservoir.getRealmModelReservoirSensors()) {

                            RealmModelReservoirSensor realmModelReservoirSensor1 =
                                    realm
                                            .where(RealmModelReservoirSensor.class)
                                            .equalTo("sensorId", realmModelReservoirSensor.getSensorId())
                                            .findFirst();
                            if (realmModelReservoirSensor1 == null) {
                                realmModelReservoirSensor1 =
                                        realm.createObject(RealmModelReservoirSensor.class, realmModelReservoirSensor.getSensorId());
                            }

                            realmModelReservoirSensor1.setSensorCode(realmModelReservoirSensor.getSensorCode());
                            realmModelReservoirSensor1.setSensorName(realmModelReservoirSensor.getSensorName());
                            realmModelReservoirSensor1.setLastLevel(realmModelReservoirSensor.getLastLevel());
                            realmModelReservoirSensor1.setLastStatus(realmModelReservoirSensor.getLastStatus());

                            realmModelReservoir1.getRealmModelReservoirSensors().add(realmModelReservoirSensor1);

                        }
                    }

                }
            });
        }
    }

    public void insertReservoir(
            final RealmList<RealmModelReservoir> realmModelReservoirs,
            final RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels){

        for (final RealmModelReservoir realmModelReservoir: realmModelReservoirs) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmModelReservoir realmModelReservoir1 =
                            realm
                                    .where(RealmModelReservoir.class)
                                    .equalTo("id", realmModelReservoir.getId())
                                    .findFirst();
                    if(realmModelReservoir1 == null){
                        realmModelReservoir1 =
                                realm.createObject(RealmModelReservoir.class, realmModelReservoir.getId());
                    }
                    realmModelReservoir1.setCode(realmModelReservoir.getCode());
                    realmModelReservoir1.setName(realmModelReservoir.getName());
                    realmModelReservoir1.setAddress(realmModelReservoir.getAddress());
                    realmModelReservoir1.setDescription(realmModelReservoir.getDescription());
                    realmModelReservoir1.setDepth(realmModelReservoir.getDepth());
                    realmModelReservoir1.setVolume(realmModelReservoir.getVolume());
                    realmModelReservoir1.setAreaId(realmModelReservoir.getAreaId());
                    realmModelReservoir1.setAreaName(realmModelReservoir.getAreaName());
                    realmModelReservoir1.setLatitude(realmModelReservoir.getLatitude());
                    realmModelReservoir1.setLongitude(realmModelReservoir.getLongitude());
                    realmModelReservoir1.setDistance(realmModelReservoir.getDistance());
                    realmModelReservoir1.setDistanceDescription(realmModelReservoir.getDistanceDescription());
                    realmModelReservoir1.setLastLevelAverage(realmModelReservoir.getLastLevelAverage());
                    realmModelReservoir1.setLastStatusAverage(realmModelReservoir.getLastStatusAverage());
                    realmModelReservoir1.setCreationTime(realmModelReservoir.getCreationTime());
                    realmModelReservoir1.setModificationTime(realmModelReservoir.getModificationTime());
                    realmModelReservoir1.setBookmarked(realmModelReservoir.isBookmarked());

                    //realmModelReservoir1.setRealmModelReservoirPhotos(realmModelReservoir.getRealmModelReservoirPhotos());
                    //realmModelReservoir1.setRealmModelReservoirSensors(realmModelReservoir.getRealmModelReservoirSensors());

                    if(realmModelReservoir.getRealmModelReservoirPhotos() != null) {
                        for (final RealmModelReservoirPhoto realmModelReservoirPhoto : realmModelReservoir.getRealmModelReservoirPhotos()) {

                            RealmModelReservoirPhoto realmModelReservoirPhoto1 =
                                    realm
                                            .where(RealmModelReservoirPhoto.class)
                                            .equalTo("photoId", realmModelReservoirPhoto.getPhotoId())
                                            .findFirst();
                            if (realmModelReservoirPhoto1 == null) {
                                realmModelReservoirPhoto1 =
                                        realm.createObject(RealmModelReservoirPhoto.class, realmModelReservoirPhoto.getPhotoId());
                            }

                            realmModelReservoirPhoto1.setPhoto(realmModelReservoirPhoto.getPhoto());

                            realmModelReservoir1.getRealmModelReservoirPhotos().add(realmModelReservoirPhoto1);

                        }
                    }

                    if(realmModelReservoir.getRealmModelReservoirSensors() != null) {
                        for (final RealmModelReservoirSensor realmModelReservoirSensor : realmModelReservoir.getRealmModelReservoirSensors()) {

                            RealmModelReservoirSensor realmModelReservoirSensor1 =
                                    realm
                                            .where(RealmModelReservoirSensor.class)
                                            .equalTo("sensorId", realmModelReservoirSensor.getSensorId())
                                            .findFirst();
                            if (realmModelReservoirSensor1 == null) {
                                realmModelReservoirSensor1 =
                                        realm.createObject(RealmModelReservoirSensor.class, realmModelReservoirSensor.getSensorId());
                            }

                            realmModelReservoirSensor1.setSensorCode(realmModelReservoirSensor.getSensorCode());
                            realmModelReservoirSensor1.setSensorName(realmModelReservoirSensor.getSensorName());
                            realmModelReservoirSensor1.setLastLevel(realmModelReservoirSensor.getLastLevel());
                            realmModelReservoirSensor1.setLastStatus(realmModelReservoirSensor.getLastStatus());

                            realmModelReservoir1.getRealmModelReservoirSensors().add(realmModelReservoirSensor1);

                        }
                    }

                    if(realmModelReservoirsLevels != null){
                        for (final RealmModelReservoirsLevel realmModelReservoirsLevel : realmModelReservoirsLevels) {

                            if(realmModelReservoirsLevel.getReservoirId() == realmModelReservoir.getId()) {

                                ReservoirsLevelUtils.saveReservoirsLevelWithoutRealmTransaction(realmModelReservoirsLevel);

                                realmModelReservoir1.getRealmModelReservoirsLevels().add(realmModelReservoirsLevel);
                            }
                        }
                    }

                }
            });
        }
    }

    public void updateBookmarkReservoir(
            final int reservoirId,
            final boolean isBookmark){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmModelReservoir realmModelReservoir1 =
                        realm
                                .where(RealmModelReservoir.class)
                                .equalTo("id", reservoirId)
                                .findFirst();
                if(realmModelReservoir1 == null){
                    realmModelReservoir1 =
                            realm.createObject(RealmModelReservoir.class, reservoirId);
                }
                realmModelReservoir1.setBookmarked(isBookmark);
            }
        });
    }
}
