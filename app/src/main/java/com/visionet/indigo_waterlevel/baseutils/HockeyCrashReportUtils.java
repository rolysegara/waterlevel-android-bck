package com.visionet.indigo_waterlevel.baseutils;

import android.app.Activity;
import android.content.Context;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

public class HockeyCrashReportUtils {
    public static void checkForCrashes(Context context) {
        CrashManager.register(context);
    }

    public static void checkForUpdates(Context context) {
        // Remove this for store builds!
        UpdateManager.register((Activity) context);
    }

    public static void unregisterManagers() {
        UpdateManager.unregister();
    }
}
