package com.visionet.indigo_waterlevel.modulmap.presenter;

import android.util.Log;

import com.visionet.indigo_waterlevel.baseapp.BasePresenter;
import com.visionet.indigo_waterlevel.basenetwork.NetworkCallback;
import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseGetAllReservoirsLevel;
import com.visionet.indigo_waterlevel.modulmap.view.ReservoirsLevelView;

public class ReservoirsLevelPresenter extends BasePresenter<ReservoirsLevelView> {

    public ReservoirsLevelPresenter(ReservoirsLevelView view) {
        super.attachView(view);
    }

    public void loadDataReservoirsLevel() {
        view.showLoadingReservoirLevel();
        addSubscribe(apiStores.getAllReservoirsLevel(
        ), new NetworkCallback<ModelResponseGetAllReservoirsLevel>() {
            @Override
            public void onSuccess(ModelResponseGetAllReservoirsLevel modelResponseGetAllReservoirsLevel) {
                view.getReservoirsLevel(modelResponseGetAllReservoirsLevel);
                //view.hideLoading();
            }

            @Override
            public void onFailure(String message) {
                Log.d("ErrorBodyReservoirLevel", message);
                view.hideLoadingReservoirLevel();
                view.getReservoirsLevelFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoadingReservoirLevel();
            }
        });
    }
}