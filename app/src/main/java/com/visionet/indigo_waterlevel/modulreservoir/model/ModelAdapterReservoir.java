package com.visionet.indigo_waterlevel.modulreservoir.model;

import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;

import io.realm.RealmList;

public class ModelAdapterReservoir {
    String title;
    RealmList<RealmModelReservoir> realmModelReservoirs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<RealmModelReservoir> getRealmModelReservoirs() {
        return realmModelReservoirs;
    }

    public void setRealmModelReservoirs(RealmList<RealmModelReservoir> realmModelReservoirs) {
        this.realmModelReservoirs = realmModelReservoirs;
    }
}
