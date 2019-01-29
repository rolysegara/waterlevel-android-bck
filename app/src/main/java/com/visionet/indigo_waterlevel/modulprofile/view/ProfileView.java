package com.visionet.indigo_waterlevel.modulprofile.view;

import com.visionet.indigo_waterlevel.modulprofile.model.ModelResponseChangePassword;

public interface ProfileView {
    void showLoading();

    void hideLoading();

    void getResponseUpdatePhoto();

    void getResponseUpdatePhotoFailed(String message);

    void getResponseChangePassword(ModelResponseChangePassword modelResponseChangePassword);

    void getResponseChangePaswwordFailed(String message);
}
