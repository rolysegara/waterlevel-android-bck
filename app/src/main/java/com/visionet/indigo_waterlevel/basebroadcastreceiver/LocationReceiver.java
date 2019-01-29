package com.visionet.indigo_waterlevel.basebroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * MyBroadCastReceiver is responsible to receive broadCast from register action
 * */
public class LocationReceiver extends BroadcastReceiver {
    private final String TAG = "LocationReceiver";
    private OnLocationReceivedListener listener = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Log.d(TAG, "onReceive() called");

            // uncomment this line if you had sent some data
            String latitude = intent.getStringExtra("latitude"); // data is a key specified to intent while sending broadcast
            String longitude = intent.getStringExtra("longitude");
            Log.e(TAG, "latitude=="+latitude + ", longitude=="+ longitude);

            if (listener != null) {
                listener.onLocationReceived(latitude, longitude);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void setOnLocationReceivedListener(Context context) {
        this.listener = (OnLocationReceivedListener) context;
    }

    public interface OnLocationReceivedListener {
        public void onLocationReceived(String latitude, String longitude);
    }
}
