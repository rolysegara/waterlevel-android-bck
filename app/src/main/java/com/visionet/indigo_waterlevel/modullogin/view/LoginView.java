package com.visionet.indigo_waterlevel.modullogin.view;

import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseAuthentication;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseProfilePicture;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseResetPassword;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseUserProfile;

public interface LoginView {
    void showLoading();

    void hideLoading();

    void getResponseAuthenticationSuccess(ModelResponseAuthentication modelResponseAuthentication);

    void getResponseAuthenticationFailed(String message);

    void getResponseUserProfileSuccess(ModelResponseUserProfile modelResponseUserProfile);

    void getResponseUserProfileFailed(String message);

    void getResponseProfilePicture(ModelResponseUserProfile modelResponseUserProfile,
                                   ModelResponseProfilePicture modelResponseProfilePicture);

    void getResponseProfilePictureFailed(String message);

    void getResetPassword(ModelResponseResetPassword modelResponseResetPassword);

    void getResetPasswordFailed(String message);
}
