package com.visionet.indigo_waterlevel.modullogin.utils;

import android.content.Context;

import com.visionet.indigo_waterlevel.baseapp.BaseApplication;
import com.visionet.indigo_waterlevel.basehelper.AppData;
import com.visionet.indigo_waterlevel.baserealm.user_profile.controller.RealmControllerUserProfile;
import com.visionet.indigo_waterlevel.baserealm.user_profile.model.RealmModelUserProfile;
import com.visionet.indigo_waterlevel.baseutils.PreferenceUtils;

import io.realm.RealmResults;

public class LoginUtils {
    public static void setLoginPreferrences(Context context, boolean isLogin, String accessToken, int userId){
        PreferenceUtils.writePreferenceValue(context, AppData.KEY_PREF_IS_LOGIN, isLogin);
        PreferenceUtils.writePreferenceValue(context, AppData.KEY_PREF_ACCESS_TOKEN, accessToken);
        PreferenceUtils.writePreferenceValue(context, AppData.KEY_PREF_USER_ID, userId);
    }

    public static void saveUserProfile(
            RealmModelUserProfile realmModelUserProfile){

        RealmControllerUserProfile realmControllerUserProfile =
                new RealmControllerUserProfile(new BaseApplication());
        realmControllerUserProfile.insertUserProfile(realmModelUserProfile);

    }

    public static void clearUserProfile(){
        RealmControllerUserProfile realmControllerUserProfile =
                new RealmControllerUserProfile(new BaseApplication());
        realmControllerUserProfile.clearAll(RealmModelUserProfile.class);
    }

    public static RealmModelUserProfile getUserProfile(){
        RealmControllerUserProfile realmControllerUserProfile =
                new RealmControllerUserProfile(new BaseApplication());
        if(realmControllerUserProfile.getUserProfiles().size() > 0) {
            return realmControllerUserProfile.getUserProfiles().first();
        }else{
            return null;
        }
    }

    public static boolean isLogin(Context context){
        return PreferenceUtils.readPreferenceValue(context, AppData.KEY_PREF_IS_LOGIN, false);
    }

    public static String getAccessToken(Context context){
        return PreferenceUtils.readPreferenceValue(context, AppData.KEY_PREF_ACCESS_TOKEN, "");
    }

    public static int getUserId(Context context){
        return PreferenceUtils.readPreferenceValue(context, AppData.KEY_PREF_USER_ID, -1);
    }
}
