package com.visionet.indigo_waterlevel.modulprofile.presenter;

import com.visionet.indigo_waterlevel.baseapp.BasePresenter;
import com.visionet.indigo_waterlevel.basenetwork.NetworkCallback;
import com.visionet.indigo_waterlevel.modulprofile.model.ModelRequestChangePassword;
import com.visionet.indigo_waterlevel.modulprofile.model.ModelResponseChangePassword;
import com.visionet.indigo_waterlevel.modulprofile.view.ProfileView;

public class ProfilePresenter extends BasePresenter<ProfileView> {
    public ProfilePresenter(ProfileView view) {
        super.attachView(view);
    }

    public void loadDataChangePassword(final String bearerToken, ModelRequestChangePassword modelRequestChangePassword) {
        view.showLoading();
        addSubscribe(apiStores.changePassword(bearerToken, modelRequestChangePassword), new NetworkCallback<ModelResponseChangePassword>() {
            @Override
            public void onSuccess(ModelResponseChangePassword modelResponseChangePassword) {
                view.getResponseChangePassword(modelResponseChangePassword);
            }

            @Override
            public void onFailure(String message) {
                view.getResponseChangePaswwordFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
}
