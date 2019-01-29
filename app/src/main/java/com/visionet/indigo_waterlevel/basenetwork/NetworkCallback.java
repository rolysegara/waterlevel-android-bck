package com.visionet.indigo_waterlevel.basenetwork;

import android.util.Log;

import com.google.gson.Gson;
import com.visionet.indigo_waterlevel.basehelper.AppData;
import com.visionet.indigo_waterlevel.baseutils.PreferenceUtils;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseAuthenticationFailed;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public abstract class NetworkCallback<M> extends Subscriber<M> {

    private static final String TAG = NetworkCallback.class.getName();

    public abstract void onSuccess(M model);

    public abstract void onFailure(String message);

    public abstract void onFinish();

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String message = httpException.getMessage();
            Log.i(TAG, "code : " + code);
            String errorBody = message;

            ResponseBody body = ((HttpException) e).response().errorBody();
            try {
                onFailure(body.string());
            } catch (IOException e1) {
                //Timber.e(e1.getMessage());
            } finally {
                if (body != null) {
                    body.close();
                }
            }

        } else {
            onFailure(e.getMessage());
            Log.d("ErrorResponse1", e.getMessage());

        }
        onFinish();
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}