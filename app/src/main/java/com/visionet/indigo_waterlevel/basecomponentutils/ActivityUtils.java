package com.visionet.indigo_waterlevel.basecomponentutils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.Objects;

public class ActivityUtils {

    /**
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void addFragment(@NonNull FragmentManager fragmentManager,
                                   @NonNull Fragment fragment,
                                   int frameId) {
        Objects.requireNonNull(fragmentManager);
        Objects.requireNonNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
}