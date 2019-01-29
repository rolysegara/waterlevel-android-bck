package com.visionet.indigo_waterlevel.baserealm.reservoirs_level.controller;

import android.app.Application;

import com.visionet.indigo_waterlevel.baserealm.RealmController;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.baserealm.reservoirs_level.model.RealmModelReservoirsLevel;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

public class RealmControllerReservoirsLevel extends RealmController {
    public RealmControllerReservoirsLevel(Application application) {
        super(application);
    }

    //find all objects in the Book.class
    public RealmResults<RealmModelReservoirsLevel> getReservoirsLevel() {

        return realm.where(RealmModelReservoirsLevel.class).findAll();
    }

    //query a single item with the given id
    public RealmResults<RealmModelReservoirsLevel> getReservoirsLevelByReservoirId(int reservoirId) {

        return realm.where(RealmModelReservoirsLevel.class).equalTo("reservoirId", reservoirId).findAll();
    }

    public RealmResults<RealmModelReservoirsLevel> getReservoirsLevelByLevelMinDescending(int reservoirId) {

        return realm.where(RealmModelReservoirsLevel.class)
                .equalTo("reservoirId", reservoirId)
                .findAll().sort("levelMin", Sort.DESCENDING);
    }

    public RealmModelReservoirsLevel getReservoirsLevelByLevelAverage(int reservoirId, double levelAverage) {

        return realm.where(RealmModelReservoirsLevel.class)
                .equalTo("reservoirId", reservoirId).sort("levelMin", Sort.DESCENDING)
                .lessThanOrEqualTo("levelMin", levelAverage)
                .and()
                .greaterThanOrEqualTo("levelMax", levelAverage)
                .findFirst();
    }

    //check if Book.class is empty
    public boolean hasReservoirsLevel() {

        return !realm.where(RealmModelReservoirsLevel.class).findAll().isEmpty();
    }

    public void insertReservoirsLevel(
        final RealmModelReservoirsLevel realmModelReservoirsLevel){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmModelReservoirsLevel realmModelReservoirsLevel1 =
                        realm
                                .where(RealmModelReservoirsLevel.class)
                                .equalTo("id", realmModelReservoirsLevel.getId())
                                .findFirst();
                if (realmModelReservoirsLevel1 == null) {
                    realmModelReservoirsLevel1 =
                            realm.createObject(RealmModelReservoirsLevel.class, realmModelReservoirsLevel.getId());
                }
                realmModelReservoirsLevel1.setName(realmModelReservoirsLevel.getName());
                realmModelReservoirsLevel1.setDescription(realmModelReservoirsLevel.getDescription());
                realmModelReservoirsLevel1.setReservoirId(realmModelReservoirsLevel.getReservoirId());
                realmModelReservoirsLevel1.setReservoirName(realmModelReservoirsLevel.getReservoirName());
                realmModelReservoirsLevel1.setLevelMin(realmModelReservoirsLevel.getLevelMin());
                realmModelReservoirsLevel1.setLevelMax(realmModelReservoirsLevel.getLevelMax());
                realmModelReservoirsLevel1.setCreationTime(realmModelReservoirsLevel.getCreationTime());
                realmModelReservoirsLevel1.setModificationTime(realmModelReservoirsLevel.getModificationTime());
            }
        });
    }

    public void insertReservoirsLevel(
            final RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels){

        for (final RealmModelReservoirsLevel realmModelReservoirsLevel: realmModelReservoirsLevels) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmModelReservoirsLevel realmModelReservoirsLevel1 =
                            realm
                                    .where(RealmModelReservoirsLevel.class)
                                    .equalTo("id", realmModelReservoirsLevel.getId())
                                    .findFirst();
                    if(realmModelReservoirsLevel1 == null){
                        realmModelReservoirsLevel1 =
                                realm.createObject(RealmModelReservoirsLevel.class, realmModelReservoirsLevel.getId());
                    }
                    realmModelReservoirsLevel1.setName(realmModelReservoirsLevel.getName());
                    realmModelReservoirsLevel1.setDescription(realmModelReservoirsLevel.getDescription());
                    realmModelReservoirsLevel1.setReservoirId(realmModelReservoirsLevel.getReservoirId());
                    realmModelReservoirsLevel1.setReservoirName(realmModelReservoirsLevel.getReservoirName());
                    realmModelReservoirsLevel1.setLevelMin(realmModelReservoirsLevel.getLevelMin());
                    realmModelReservoirsLevel1.setLevelMax(realmModelReservoirsLevel.getLevelMax());
                    realmModelReservoirsLevel1.setCreationTime(realmModelReservoirsLevel.getCreationTime());
                    realmModelReservoirsLevel1.setModificationTime(realmModelReservoirsLevel.getModificationTime());

                }
            });
        }
    }

    public void insertReservoirsLevelWithoutRealmTransaction(
            final RealmModelReservoirsLevel realmModelReservoirsLevel){

        RealmModelReservoirsLevel realmModelReservoirsLevel1 =
                realm
                        .where(RealmModelReservoirsLevel.class)
                        .equalTo("id", realmModelReservoirsLevel.getId())
                        .findFirst();
        if (realmModelReservoirsLevel1 == null) {
            realmModelReservoirsLevel1 =
                    realm.createObject(RealmModelReservoirsLevel.class, realmModelReservoirsLevel.getId());
        }
        realmModelReservoirsLevel1.setName(realmModelReservoirsLevel.getName());
        realmModelReservoirsLevel1.setDescription(realmModelReservoirsLevel.getDescription());
        realmModelReservoirsLevel1.setReservoirId(realmModelReservoirsLevel.getReservoirId());
        realmModelReservoirsLevel1.setReservoirName(realmModelReservoirsLevel.getReservoirName());
        realmModelReservoirsLevel1.setLevelMin(realmModelReservoirsLevel.getLevelMin());
        realmModelReservoirsLevel1.setLevelMax(realmModelReservoirsLevel.getLevelMax());
        realmModelReservoirsLevel1.setCreationTime(realmModelReservoirsLevel.getCreationTime());
        realmModelReservoirsLevel1.setModificationTime(realmModelReservoirsLevel.getModificationTime());

    }

    public void insertReservoirsLevelWithoutRealmTransaction(
            final RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels){

        for (final RealmModelReservoirsLevel realmModelReservoirsLevel: realmModelReservoirsLevels) {
            RealmModelReservoirsLevel realmModelReservoirsLevel1 =
                    realm
                            .where(RealmModelReservoirsLevel.class)
                            .equalTo("id", realmModelReservoirsLevel.getId())
                            .findFirst();
            if(realmModelReservoirsLevel1 == null){
                realmModelReservoirsLevel1 =
                        realm.createObject(RealmModelReservoirsLevel.class, realmModelReservoirsLevel.getId());
            }
            realmModelReservoirsLevel1.setName(realmModelReservoirsLevel.getName());
            realmModelReservoirsLevel1.setDescription(realmModelReservoirsLevel.getDescription());
            realmModelReservoirsLevel1.setReservoirId(realmModelReservoirsLevel.getReservoirId());
            realmModelReservoirsLevel1.setReservoirName(realmModelReservoirsLevel.getReservoirName());
            realmModelReservoirsLevel1.setLevelMin(realmModelReservoirsLevel.getLevelMin());
            realmModelReservoirsLevel1.setLevelMax(realmModelReservoirsLevel.getLevelMax());
            realmModelReservoirsLevel1.setCreationTime(realmModelReservoirsLevel.getCreationTime());
            realmModelReservoirsLevel1.setModificationTime(realmModelReservoirsLevel.getModificationTime());

        }
    }
}