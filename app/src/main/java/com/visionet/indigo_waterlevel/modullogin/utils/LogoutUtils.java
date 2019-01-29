package com.visionet.indigo_waterlevel.modullogin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.visionet.indigo_waterlevel.baseutils.LocationUtils;
import com.visionet.indigo_waterlevel.modullogin.LoginActivity;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils;

import static com.visionet.indigo_waterlevel.modullogin.utils.LoginUtils.clearUserProfile;
import static com.visionet.indigo_waterlevel.modullogin.utils.LoginUtils.setLoginPreferrences;
import static com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils.clearReservoirsLevel;
import static com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils.clearReservoir;

public class LogoutUtils {
    public static void setToLogout(Context context){

        setLoginPreferrences(context, false, "", 0);
        LocationUtils.setLocationPreferences(context, 0, 0);
        clearUserProfile();
        clearReservoir();
        clearReservoirsLevel();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity)context).finish();
    }
}
