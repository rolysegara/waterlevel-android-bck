package com.visionet.indigo_waterlevel.baserealm.reservoir.model;

import com.visionet.indigo_waterlevel.baserealm.reservoirs_level.model.RealmModelReservoirsLevel;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmModelReservoir extends RealmObject{
    @PrimaryKey
    private int id;
    private String code;
    private String name;
    private String address;
    private String description;
    private double depth;
    private double volume;
    private int areaId;
    private String areaName;
    private double latitude;
    private double longitude;
    private double distance;
    private String distanceDescription;
    private double lastLevelAverage;
    private String lastStatusAverage;
    private String creationTime;
    private String modificationTime;
    private boolean isBookmarked;
    private RealmList<RealmModelReservoirPhoto> realmModelReservoirPhotos;
    private RealmList<RealmModelReservoirSensor> realmModelReservoirSensors;
    private RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDistanceDescription() {
        return distanceDescription;
    }

    public void setDistanceDescription(String distanceDescription) {
        this.distanceDescription = distanceDescription;
    }

    public double getLastLevelAverage() {
        return lastLevelAverage;
    }

    public void setLastLevelAverage(double lastLevelAverage) {
        this.lastLevelAverage = lastLevelAverage;
    }

    public String getLastStatusAverage() {
        return lastStatusAverage;
    }

    public void setLastStatusAverage(String lastStatusAverage) {
        this.lastStatusAverage = lastStatusAverage;
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

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    public RealmList<RealmModelReservoirPhoto> getRealmModelReservoirPhotos() {
        return realmModelReservoirPhotos;
    }

    public void setRealmModelReservoirPhotos(RealmList<RealmModelReservoirPhoto> realmModelReservoirPhotos) {
        this.realmModelReservoirPhotos = realmModelReservoirPhotos;
    }

    public RealmList<RealmModelReservoirSensor> getRealmModelReservoirSensors() {
        return realmModelReservoirSensors;
    }

    public void setRealmModelReservoirSensors(RealmList<RealmModelReservoirSensor> realmModelReservoirSensors) {
        this.realmModelReservoirSensors = realmModelReservoirSensors;
    }

    public RealmList<RealmModelReservoirsLevel> getRealmModelReservoirsLevels() {
        return realmModelReservoirsLevels;
    }

    public void setRealmModelReservoirsLevels(RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels) {
        this.realmModelReservoirsLevels = realmModelReservoirsLevels;
    }
}
