package com.visionet.indigo_waterlevel.modulmap.presenter;

import android.util.Log;

import com.visionet.indigo_waterlevel.baseapp.BasePresenter;
import com.visionet.indigo_waterlevel.basenetwork.NetworkCallback;
import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseReservoirWithSensorsAndDistance;
import com.visionet.indigo_waterlevel.modulmap.view.ReservoirView;

public class ReservoirPresenter extends BasePresenter<ReservoirView> {

    public ReservoirPresenter(ReservoirView view) {
        super.attachView(view);
    }

    public void loadDataReservoirWithSort(
            String sorting, int maxResultCount, int skipCount, double latitude, double longitude
    ) {
        view.showLoadingReservoir();
        addSubscribe(apiStores.getReservoirsWithSensorAndDistanceWithSort(
            sorting, maxResultCount, skipCount, latitude, longitude
        ), new NetworkCallback<ModelResponseReservoirWithSensorsAndDistance>() {
            @Override
            public void onSuccess(ModelResponseReservoirWithSensorsAndDistance modelResponseReservoirWithSensorsAndDistance) {
                view.getResponseReservoirsWithSensorAndDistanceSuccess(modelResponseReservoirWithSensorsAndDistance);
                //view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                Log.d("ErrorBodyReservoir", message);
                view.hideLoadingReservoir();
                view.getResponseReservoirsWithSensorAndDistanceFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoadingReservoir();
            }
        });
    }
}
