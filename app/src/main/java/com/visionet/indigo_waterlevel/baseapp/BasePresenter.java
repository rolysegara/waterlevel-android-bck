package com.visionet.indigo_waterlevel.baseapp;

import android.util.Log;

import com.visionet.indigo_waterlevel.basenetwork.NetworkClient;
import com.visionet.indigo_waterlevel.basenetwork.NetworkStores;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<V> {
    public V view;
    protected NetworkStores apiStores;
    private CompositeSubscription compositeSubscription;
    private Subscriber subscriber;

    public void attachView(V view) {
        this.view = view;
        apiStores = NetworkClient.getRetrofit().create(NetworkStores.class);
    }

    public void dettachView() {
        this.view = null;
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
            Log.e("TAG", "onUnsubscribe: ");
        }
    }

    protected void addSubscribe(Observable observable, Subscriber subscriber) {
        this.subscriber = subscriber;
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(subscriber));
    }

    public void stop() {
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}