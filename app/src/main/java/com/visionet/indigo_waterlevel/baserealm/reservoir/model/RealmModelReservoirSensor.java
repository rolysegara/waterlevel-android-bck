package com.visionet.indigo_waterlevel.baserealm.reservoir.model;

import android.support.annotation.NonNull;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class RealmModelReservoirSensor extends RealmObject {
    @PrimaryKey
    private int sensorId;
    private String sensorCode;
    private String sensorName;
    private double lastLevel;
    private String lastStatus;

    @LinkingObjects("realmModelReservoirSensors")
    private final RealmResults<RealmModelReservoir> reservoirs = null;

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public double getLastLevel() {
        return lastLevel;
    }

    public void setLastLevel(double lastLevel) {
        this.lastLevel = lastLevel;
    }

    public String getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus = lastStatus;
    }

    public RealmResults<RealmModelReservoir> getReservoirs() {
        return reservoirs;
    }
}
