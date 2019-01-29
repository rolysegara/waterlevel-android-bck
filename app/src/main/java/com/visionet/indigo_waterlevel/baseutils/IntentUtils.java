package com.visionet.indigo_waterlevel.baseutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;

import com.visionet.indigo_waterlevel.BuildConfig;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.basecomponentutils.DialogBuildersUtils;

public class IntentUtils {
    public static void openSettings(final Context context) {

        DialogBuildersUtils dialogBuildersUtils = new DialogBuildersUtils(context);
        dialogBuildersUtils.questionDialogButton("", context.getString(R.string.need_location_access))
        .setPositiveButton(context.getString(R.string.yes_go_to_setting), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction( Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        })
        .setNegativeButton(context.getString(R.string.deny), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        })
        .show();


    }
}
