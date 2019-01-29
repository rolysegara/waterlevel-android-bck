package com.visionet.indigo_waterlevel.modulmap.view;

import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseGetAllReservoirsLevel;

public interface ReservoirsLevelView {
    void showLoadingReservoirLevel();

    void hideLoadingReservoirLevel();

    void getReservoirsLevel(
            ModelResponseGetAllReservoirsLevel modelResponseGetAllReservoirsLevel);

    void getReservoirsLevelFailed(String message);
}