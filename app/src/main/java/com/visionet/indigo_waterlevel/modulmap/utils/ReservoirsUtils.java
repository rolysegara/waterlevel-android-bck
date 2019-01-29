package com.visionet.indigo_waterlevel.modulmap.utils;

import com.visionet.indigo_waterlevel.baseapp.BaseApplication;
import com.visionet.indigo_waterlevel.baserealm.reservoir.controller.RealmControllerReservoir;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoirPhoto;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoirSensor;
import com.visionet.indigo_waterlevel.baserealm.reservoirs_level.model.RealmModelReservoirsLevel;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

public class ReservoirsUtils {
    public static void saveReservoir(
            RealmModelReservoir realmModelReservoir){
        RealmControllerReservoir realmControllerReservoir =
                new RealmControllerReservoir(new BaseApplication());
        realmControllerReservoir.insertReservoir(realmModelReservoir);
    }

    public static RealmResults<RealmModelReservoir> getReservoirs(){
        RealmControllerReservoir realmControllerReservoir =
                new RealmControllerReservoir(new BaseApplication());
        return realmControllerReservoir.getReservoirs();
    }

    public static RealmResults<RealmModelReservoir> getReservoirsByName(String name){
        RealmControllerReservoir realmControllerReservoir =
                new RealmControllerReservoir(new BaseApplication());
        return realmControllerReservoir.getReservoirsByName(name);
    }

    public static RealmResults<RealmModelReservoir> getReservoirsBookmarked(boolean isBookmarked){

        RealmControllerReservoir realmControllerReservoir =
                new RealmControllerReservoir(new BaseApplication());
        return realmControllerReservoir.getReservoirsBookmarked(isBookmarked);
    }

    public static RealmModelReservoir getReservoirsById(int id){

        RealmControllerReservoir realmControllerReservoir =
                new RealmControllerReservoir(new BaseApplication());
        return realmControllerReservoir.getReservoirById(id);
    }

    public static RealmResults<RealmModelReservoirSensor> getReservoirSensors(int idResevoir){

        RealmControllerReservoir realmControllerReservoir =
                new RealmControllerReservoir(new BaseApplication());
        return realmControllerReservoir.getReservoirSensors(idResevoir);
    }

    public static RealmResults<RealmModelReservoirPhoto> getReservoirPhotos(int idResevoir){

        RealmControllerReservoir realmControllerReservoir =
                new RealmControllerReservoir(new BaseApplication());
        return realmControllerReservoir.getReservoirPhotos(idResevoir);
    }

    public static void saveReservoirs(
            RealmList<RealmModelReservoir> realmModelReservoirs){

        RealmControllerReservoir realmControllerReservoir =
                new RealmControllerReservoir(new BaseApplication());
        realmControllerReservoir.insertReservoir(realmModelReservoirs);
    }

    public static void saveReservoirs(
            RealmList<RealmModelReservoir> realmModelReservoirs, RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels){

        RealmControllerReservoir realmControllerReservoir =
                new RealmControllerReservoir(new BaseApplication());
        realmControllerReservoir.insertReservoir(realmModelReservoirs, realmModelReservoirsLevels);
    }

    public static void updateBookmarkReservoir(int reservoirId, boolean isBookmark){
        RealmControllerReservoir realmControllerReservoir =
                new RealmControllerReservoir(new BaseApplication());
        realmControllerReservoir.updateBookmarkReservoir(reservoirId, isBookmark);
    }

    public static void clearReservoir(){
        RealmControllerReservoir realmControllerReservoir =
                new RealmControllerReservoir(new BaseApplication());
        realmControllerReservoir.clearAll(RealmModelReservoir.class);
        realmControllerReservoir.clearAll(RealmModelReservoirPhoto.class);
        realmControllerReservoir.clearAll(RealmModelReservoirSensor.class);
    }
}
