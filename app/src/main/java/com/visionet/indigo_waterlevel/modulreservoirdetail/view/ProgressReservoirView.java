package com.visionet.indigo_waterlevel.modulreservoirdetail.view;

import com.visionet.indigo_waterlevel.modulreservoirdetail.model.ModelResponseGetProgressAllReservoirs;

public interface ProgressReservoirView {
    void showLoadingReservoir();

    void hideLoadingReservoir();

    void getResponseAllProgressReservoirs(
            ModelResponseGetProgressAllReservoirs modelResponseGetProgressAllReservoirs);

    void getResponseAllProgressReservoirsFailed(String message);
}