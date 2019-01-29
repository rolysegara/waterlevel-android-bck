package com.visionet.indigo_waterlevel.modulregister.view;

import com.visionet.indigo_waterlevel.modulregister.model.ModelResponseRegister;

public interface RegisterView {
    void showLoading();

    void hideLoading();

    void getResponseRegisterSuccess(ModelResponseRegister modelResponseRegister);

    void getResponseRegisterFailed(String message);
}
