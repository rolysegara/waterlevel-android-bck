package com.visionet.indigo_waterlevel.baseapp;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.visionet.indigo_waterlevel.basebroadcastreceiver.LocationReceiver;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

import static com.visionet.indigo_waterlevel.basehelper.AppData.BROADCAST_LOCATION;

public class BaseFragment extends Fragment {
    public Activity activity;
    private CompositeSubscription compositeSubscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        ButterKnife.bind(activity, view);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    public void onUnsubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }

}