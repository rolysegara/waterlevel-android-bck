package com.visionet.indigo_waterlevel.modulmap.view;

import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseReservoirWithSensorsAndDistance;

public interface ReservoirView {
    void showLoadingReservoir();

    void hideLoadingReservoir();

    void getResponseReservoirsWithSensorAndDistanceSuccess(
            ModelResponseReservoirWithSensorsAndDistance modelResponseReservoirWithSensorsAndDistance);

    void getResponseReservoirsWithSensorAndDistanceFailed(String message);
}
