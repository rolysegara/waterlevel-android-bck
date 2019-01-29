package com.visionet.indigo_waterlevel.modulreservoirdetail.presenter;

import android.util.Log;

import com.visionet.indigo_waterlevel.baseapp.BasePresenter;
import com.visionet.indigo_waterlevel.basenetwork.NetworkCallback;
import com.visionet.indigo_waterlevel.modulreservoirdetail.model.ModelResponseGetReservoirReport;
import com.visionet.indigo_waterlevel.modulreservoirdetail.view.ReservoirReportView;

import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.DAILY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.MONTHLY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.WEEKLY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.YEARLY;

public class ReservoirReportPresenter extends BasePresenter<ReservoirReportView> {

    public ReservoirReportPresenter(ReservoirReportView view) {
        super.attachView(view);
    }

    public void loadDataReservoirReportDaily(
            int reservoirId, String date
    ) {
        view.showLoadingReservoirReport();
        addSubscribe(apiStores.getReservoirReportDaily(
                reservoirId, date
        ), new NetworkCallback<ModelResponseGetReservoirReport>() {
            @Override
            public void onSuccess(ModelResponseGetReservoirReport modelResponseGetReservoirReport) {
                view.getResponseReservoirReport(modelResponseGetReservoirReport, DAILY);
                view.hideLoadingReservoirReport();
            }

            @Override
            public void onFailure(String message) {
                Log.d("ErrorBodyProgressReserv", message);
                view.hideLoadingReservoirReport();
                view.getResponseReservoirReportFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoadingReservoirReport();
            }
        });
    }

    public void loadDataReservoirReportWeekly(
            int reservoirId, String date
    ) {
        view.showLoadingReservoirReport();
        addSubscribe(apiStores.getReservoirReportWeekly(
                reservoirId, date
        ), new NetworkCallback<ModelResponseGetReservoirReport>() {
            @Override
            public void onSuccess(ModelResponseGetReservoirReport modelResponseGetReservoirReport) {
                view.getResponseReservoirReport(modelResponseGetReservoirReport, WEEKLY);
                //view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                Log.d("ErrorBodyProgressReserv", message);
                view.hideLoadingReservoirReport();
                view.getResponseReservoirReportFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoadingReservoirReport();
            }
        });
    }

    public void loadDataReservoirReportMonthly(
            int reservoirId, String date
    ) {
        view.showLoadingReservoirReport();
        addSubscribe(apiStores.getReservoirReportMonthly(
                reservoirId, date
        ), new NetworkCallback<ModelResponseGetReservoirReport>() {
            @Override
            public void onSuccess(ModelResponseGetReservoirReport modelResponseGetReservoirReport) {
                view.getResponseReservoirReport(modelResponseGetReservoirReport, MONTHLY);
                //view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                Log.d("ErrorBodyProgressReserv", message);
                view.hideLoadingReservoirReport();
                view.getResponseReservoirReportFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoadingReservoirReport();
            }
        });
    }

    public void loadDataReservoirReportYearly(
            int reservoirId, String date
    ) {
        view.showLoadingReservoirReport();
        addSubscribe(apiStores.getReservoirReportYearly(
                reservoirId, date
        ), new NetworkCallback<ModelResponseGetReservoirReport>() {
            @Override
            public void onSuccess(ModelResponseGetReservoirReport modelResponseGetReservoirReport) {
                view.getResponseReservoirReport(modelResponseGetReservoirReport, YEARLY);
                //view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                Log.d("ErrorBodyProgressReserv", message);
                view.hideLoadingReservoirReport();
                view.getResponseReservoirReportFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoadingReservoirReport();
            }
        });
    }
}