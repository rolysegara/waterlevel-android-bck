package com.visionet.indigo_waterlevel.baserealm.reservoirs_level.model;

import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class RealmModelReservoirsLevel extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private int reservoirId;
    private String reservoirName;
    private double levelMin;
    private double levelMax;
    private String creationTime;
    private String modificationTime;

    @LinkingObjects("realmModelReservoirsLevels")
    private final RealmResults<RealmModelReservoir> reservoirs = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReservoirId() {
        return reservoirId;
    }

    public void setReservoirId(int reservoirId) {
        this.reservoirId = reservoirId;
    }

    public String getReservoirName() {
        return reservoirName;
    }

    public void setReservoirName(String reservoirName) {
        this.reservoirName = reservoirName;
    }

    public double getLevelMin() {
        return levelMin;
    }

    public void setLevelMin(double levelMin) {
        this.levelMin = levelMin;
    }

    public double getLevelMax() {
        return levelMax;
    }

    public void setLevelMax(double levelMax) {
        this.levelMax = levelMax;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(String modificationTime) {
        this.modificationTime = modificationTime;
    }

    public RealmResults<RealmModelReservoir> getReservoirs() {
        return reservoirs;
    }
}
