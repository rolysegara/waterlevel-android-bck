package com.visionet.indigo_waterlevel.baseutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceUtils {
    // string
    public static void writePreferenceValue(Context context, String prefsKey, String prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(context);
        editor.putString(prefsKey, prefsValue);
        editor.commit();
    }

    // int
    public static void writePreferenceValue(Context context, String prefsKey, int prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(context);
        editor.putInt(prefsKey, prefsValue);
        editor.commit();
    }

    // boolean
    public static void writePreferenceValue(Context context, String prefsKey, boolean prefsValue) {
        SharedPreferences.Editor editor = getPrefsEditor(context);
        editor.putBoolean(prefsKey, prefsValue);
        editor.commit();
    }

    public static String readPreferenceValue(Context context, String prefsKey, String defaultValue){
        return getPrefs(context).getString(prefsKey, defaultValue);
    }

    public static int readPreferenceValue(Context context, String prefsKey, int defaultValue){
        return getPrefs(context).getInt(prefsKey, defaultValue);
    }

    public static boolean readPreferenceValue(Context context, String prefsKey, boolean defaultValue){
        return getPrefs(context).getBoolean(prefsKey, defaultValue);
    }

    private static SharedPreferences.Editor getPrefsEditor(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.edit();
    }

    private static SharedPreferences getPrefs(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences;
    }
}
