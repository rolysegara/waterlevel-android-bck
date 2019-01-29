package com.visionet.indigo_waterlevel.modulreservoirdetail.view;

import com.visionet.indigo_waterlevel.modulreservoirdetail.model.ModelResponseGetReservoirReport;

public interface ReservoirReportView {
    void showLoadingReservoirReport();

    void hideLoadingReservoirReport();

    void getResponseReservoirReport(
            ModelResponseGetReservoirReport modelResponseGetReservoirReport, String periodType);

    void getResponseReservoirReportFailed(String message);
}