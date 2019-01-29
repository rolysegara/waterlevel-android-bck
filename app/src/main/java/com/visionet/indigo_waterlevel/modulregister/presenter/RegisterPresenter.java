package com.visionet.indigo_waterlevel.modulregister.presenter;

import com.visionet.indigo_waterlevel.baseapp.BasePresenter;
import com.visionet.indigo_waterlevel.basenetwork.NetworkCallback;
import com.visionet.indigo_waterlevel.modulregister.model.ModelRequestRegister;
import com.visionet.indigo_waterlevel.modulregister.model.ModelResponseRegister;
import com.visionet.indigo_waterlevel.modulregister.view.RegisterView;

import okhttp3.ResponseBody;

public class RegisterPresenter extends BasePresenter<RegisterView> {

    public RegisterPresenter(RegisterView view) {
        super.attachView(view);
    }

    public void loadDataRegister(ModelRequestRegister modelRequestRegister) {
        view.showLoading();
        addSubscribe(apiStores.getRegister(modelRequestRegister), new NetworkCallback<ModelResponseRegister>() {
            @Override
            public void onSuccess(ModelResponseRegister modelResponseRegister) {
                view.getResponseRegisterSuccess(modelResponseRegister);
            }

            @Override
            public void onFailure(String message) {
                view.getResponseRegisterFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
}