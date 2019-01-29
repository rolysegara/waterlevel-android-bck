package com.visionet.indigo_waterlevel.baseutils;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.visionet.indigo_waterlevel.basehelper.AppData;

public class LocationUtils {
    public static void setLocationPreferences(Context context, double latitude, double longitude){
        try{
            String stringLatitude = Double.toString(latitude);
            String stringLongitude = Double.toString(longitude);

            PreferenceUtils.writePreferenceValue(context, AppData.KEY_PREF_LOCATION_LATITUDE, stringLatitude);
            PreferenceUtils.writePreferenceValue(context, AppData.KEY_PREF_LOCATION_LONGITUDE, stringLongitude);
        }catch (NumberFormatException nfe){

        }
    }

    public static void setLocationPreferences(Context context, String stringLatitude, String stringLongitude){
        try{
            PreferenceUtils.writePreferenceValue(context, AppData.KEY_PREF_LOCATION_LATITUDE, stringLatitude);
            PreferenceUtils.writePreferenceValue(context, AppData.KEY_PREF_LOCATION_LONGITUDE, stringLongitude);
        }catch (NumberFormatException nfe){

        }
    }

    public static String[] getStringLocationPreferences(Context context){
        String stringLatitude = PreferenceUtils.readPreferenceValue(context, AppData.KEY_PREF_LOCATION_LATITUDE, "0.0");
        String stringLongitude = PreferenceUtils.readPreferenceValue(context, AppData.KEY_PREF_LOCATION_LONGITUDE, "0.0");

        String location[] = {stringLatitude, stringLongitude};

        return location;
    }

    public static double[] getDoubleLocationPreferences(Context context){
        String stringLatitude = PreferenceUtils.readPreferenceValue(context, AppData.KEY_PREF_LOCATION_LATITUDE, "0.0");
        String stringLongitude = PreferenceUtils.readPreferenceValue(context, AppData.KEY_PREF_LOCATION_LONGITUDE, "0.0");

        double location[] = {0.0, 0.0};
        try{
            location[0] = Double.valueOf(stringLatitude);
            location[1] = Double.valueOf(stringLongitude);

            return location;
        }catch (NumberFormatException nfe){
            return location;
        }
    }

    public static LatLng getLatLngLocationPreferences(Context context){
        return new LatLng(getDoubleLocationPreferences(context)[0],
                getDoubleLocationPreferences(context)[1]);
    }
}
