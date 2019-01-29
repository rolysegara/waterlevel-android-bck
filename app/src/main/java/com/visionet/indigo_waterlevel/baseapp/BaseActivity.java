package com.visionet.indigo_waterlevel.baseapp;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.visionet.indigo_waterlevel.BuildConfig;
import com.visionet.indigo_waterlevel.basebroadcastreceiver.LocationReceiver;
import com.visionet.indigo_waterlevel.baserealm.user_profile.controller.RealmControllerUserProfile;
import com.visionet.indigo_waterlevel.baserealm.user_profile.model.RealmModelUserProfile;
import com.visionet.indigo_waterlevel.baseservices.LocationService;
import com.visionet.indigo_waterlevel.baseutils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import rx.subscriptions.CompositeSubscription;

import static com.visionet.indigo_waterlevel.basehelper.AppData.BROADCAST_LOCATION;

public class BaseActivity extends AppCompatActivity implements LocationReceiver.OnLocationReceivedListener {

    CompositeSubscription compositeSubscription;
    List<Call> calls;
    public Activity activity;
    public Context context;

    public LocationService gpsService;
    public boolean mTracking = false;

    private LocationReceiver locationReceiver;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        activity = this;
        context = this;
        ButterKnife.bind(this);

        initLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onCancelled();
        onUnsubscribe();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerLocationReceiver();
    }

    @Override
    protected void onPause(){
        super.onPause();
        unregisterReceiver(locationReceiver);
    }

    private void onCancelled() {
        if (calls != null && calls.size() > 0) {
            for (Call call : calls) {
                if (!call.isCanceled()) {
                    call.cancel();
                }
            }
        }
    }

    public void addCalls(Call call) {
        if (calls == null) {
            calls = new ArrayList<>();
        }
        calls.add(call);
    }

    private void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }

    // Location Service
    public void initLocation(){
        final Intent intent = new Intent(this.getApplication(), LocationService.class);
        this.getApplication().startService(intent);
//        this.getApplication().startForegroundService(intent);
        this.getApplication().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        locationReceiver = new LocationReceiver();
        locationReceiver.setOnLocationReceivedListener(this);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            String name = className.getClassName();
            if (name.endsWith("LocationService")) {
                gpsService = ((LocationService.LocationServiceBinder) service).getService();
                //btnStartTracking.setEnabled(true);
                //txtStatus.setText("GPS Ready");
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            if (className.getClassName().equals("LocationService")) {
                gpsService = null;
            }
        }
    };

    public void startLocationTracking() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if(gpsService != null) {
                            gpsService.startTracking();
                            mTracking = true;
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {



                        if (response.isPermanentlyDenied()) {
                            IntentUtils.openSettings(context);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    public void startLocationTracking(Activity activity) {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if(gpsService != null) {
                            gpsService.startTracking();
                            mTracking = true;
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            IntentUtils.openSettings(context);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    public void stopLocationTracking() {
        if(gpsService != null) {
            mTracking = false;
            gpsService.stopTracking();
        }
    }

    /**
     * This method is responsible to register an action to BroadCastReceiver
     * */
    private void registerLocationReceiver() {

        try
        {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(BROADCAST_LOCATION);
            registerReceiver(locationReceiver, intentFilter);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @Override
    public void onLocationReceived(String latitude, String longitude) {

    }
}