package com.visionet.indigo_waterlevel.modulreservoirdetail.presenter;

import android.util.Log;

import com.visionet.indigo_waterlevel.baseapp.BasePresenter;
import com.visionet.indigo_waterlevel.basenetwork.NetworkCallback;
import com.visionet.indigo_waterlevel.modulreservoirdetail.model.ModelResponseGetProgressAllReservoirs;
import com.visionet.indigo_waterlevel.modulreservoirdetail.view.ProgressReservoirView;

public class ProgressReservoirPresenter extends BasePresenter<ProgressReservoirView> {

    public ProgressReservoirPresenter(ProgressReservoirView view) {
        super.attachView(view);
    }

    public void loadDataAllProgressReservoir(
            String filter, String sorting, int maxResultCount, int skipCount
    ) {
        view.showLoadingReservoir();
        addSubscribe(apiStores.getProgressAllReservoirs(
                filter, sorting, maxResultCount, skipCount
        ), new NetworkCallback<ModelResponseGetProgressAllReservoirs>() {
            @Override
            public void onSuccess(ModelResponseGetProgressAllReservoirs modelResponseGetProgressAllReservoirs) {
                view.getResponseAllProgressReservoirs(modelResponseGetProgressAllReservoirs);
                //view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                Log.d("ErrorBodyProgressReserv", message);
                view.hideLoadingReservoir();
                view.getResponseAllProgressReservoirsFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoadingReservoir();
            }
        });
    }
}