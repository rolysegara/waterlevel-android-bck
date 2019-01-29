package com.visionet.indigo_waterlevel.modulhome;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.BaseActivityNavigation;
import com.visionet.indigo_waterlevel.baseutils.LocationUtils;
import com.visionet.indigo_waterlevel.modulhome.listener.OnHomeLocationReceivedListener;

import static com.visionet.indigo_waterlevel.baseutils.HockeyCrashReportUtils.checkForCrashes;
import static com.visionet.indigo_waterlevel.baseutils.HockeyCrashReportUtils.checkForUpdates;
import static com.visionet.indigo_waterlevel.baseutils.HockeyCrashReportUtils.unregisterManagers;

public class HomeActivity extends BaseActivityNavigation {

    private OnHomeLocationReceivedListener listener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        super.onCreateToolbar();
        super.onCreateNavigationDrawer();
        super.onCreateBottomNavigation();

        /*Log.d("GPSLocation_", LocationUtils.getStringLocationPreferences(this)[0]+","+
                LocationUtils.getStringLocationPreferences(this)[0]);*/

        checkForUpdates(HomeActivity.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //startLocationTracking();
        checkForCrashes(HomeActivity.this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationTracking();
        unregisterManagers();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterManagers();
    }

    @Override
    public void onLocationReceived(String latitude, String longitude) {
        super.onLocationReceived(latitude, longitude);

        if (listener != null) {
            listener.onHomeLocationReceived(latitude, longitude);
        }

        Log.d("GPSLocation_activity", latitude + "," +
                longitude);
    }

    public void setOnHomeLocationReceivedListener(OnHomeLocationReceivedListener listener) {
        this.listener = listener;
    }
}
