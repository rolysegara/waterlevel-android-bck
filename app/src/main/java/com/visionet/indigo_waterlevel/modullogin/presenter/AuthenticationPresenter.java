package com.visionet.indigo_waterlevel.modullogin.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.visionet.indigo_waterlevel.baseapp.BasePresenter;
import com.visionet.indigo_waterlevel.modullogin.model.ModelRequestAuthentication;
import com.visionet.indigo_waterlevel.modullogin.model.ModelRequestResetPasword;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseAuthentication;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseAuthenticationFailed;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseProfilePicture;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseResetPassword;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseUserProfile;
import com.visionet.indigo_waterlevel.modullogin.view.LoginView;
import com.visionet.indigo_waterlevel.basenetwork.NetworkCallback;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class AuthenticationPresenter extends BasePresenter<LoginView> {

    public AuthenticationPresenter(LoginView view) {
        super.attachView(view);
    }

    public void loadDataLogin(ModelRequestAuthentication modelRequestAuthentication) {
        view.showLoading();
        addSubscribe(apiStores.getAuthentication(modelRequestAuthentication), new NetworkCallback<ModelResponseAuthentication>() {
            @Override
            public void onSuccess(ModelResponseAuthentication modelResponseAuthentication) {
                loadDataUserProfile("Bearer " + modelResponseAuthentication.getResult().getAccessToken());
                view.getResponseAuthenticationSuccess(modelResponseAuthentication);
            }

            @Override
            public void onFailure(String message) {
                Log.d("ErrorBodyLogin", message);
                view.getResponseAuthenticationFailed(message);
                view.hideLoading();
            }

            @Override
            public void onFinish() {
                //view.hideLoading();
            }
        });
    }

    public void loadDataUserProfile(final String bearerToken) {
        //view.showLoading();
        addSubscribe(apiStores.getUserProfile(bearerToken), new NetworkCallback<ModelResponseUserProfile>() {
            @Override
            public void onSuccess(ModelResponseUserProfile modelResponseUserProfile) {
                loadDataProfilePicture(bearerToken, modelResponseUserProfile);
                view.getResponseUserProfileSuccess(modelResponseUserProfile);
            }

            @Override
            public void onFailure(String message) {
                view.getResponseUserProfileFailed(message);
                view.hideLoading();
            }

            @Override
            public void onFinish() {
                //view.hideLoading();
            }
        });
    }

    public void loadDataProfilePicture(String bearerToken, final ModelResponseUserProfile modelResponseUserProfile) {
        //view.showLoading();
        addSubscribe(apiStores.getProfilePicture(bearerToken), new NetworkCallback<ModelResponseProfilePicture>() {
            @Override
            public void onSuccess(ModelResponseProfilePicture modelResponseProfilePicture) {
                view.getResponseProfilePicture(modelResponseUserProfile, modelResponseProfilePicture);
            }

            @Override
            public void onFailure(String message) {
                view.getResponseProfilePictureFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    public void loadDataResetPassword(ModelRequestResetPasword modelRequestResetPasword) {
        view.showLoading();
        addSubscribe(apiStores.getResetPassowrd(modelRequestResetPasword), new NetworkCallback<ModelResponseResetPassword>() {
            @Override
            public void onSuccess(ModelResponseResetPassword modelResponseResetPassword) {
                view.getResetPassword(modelResponseResetPassword);
            }

            @Override
            public void onFailure(String message) {
                view.getResetPasswordFailed(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }
}
