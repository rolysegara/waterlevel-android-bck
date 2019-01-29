package com.visionet.indigo_waterlevel.baseutils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Dimens {
    /**
     * Converting dp to pixel
     */
    private int dpToPx(Context context, int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static int getStatusBarHeightInPixels(final Context context) {
        final Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            return resources.getDimensionPixelSize(resourceId);
        else
            return (int) Math.ceil((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? 24 : 25) * resources.getDisplayMetrics().density);
    }

    public static int getActionBarHeightInPixels(Activity activity){
        int actionBarHeight = 0;
        final TypedArray styledAttributes = activity.getTheme().obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize }
        );
        actionBarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return actionBarHeight;
    }

    public static int getNavigationBarHeightInPixels(Context context){
        // navigation bar height
        int navigationBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }

        return navigationBarHeight;
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getPixels(Context context){
        final float scale = context.getResources().getDisplayMetrics().density;
        final int pixels = (int) (10 * scale + 0.5f);

        return pixels;
    }

    public static int getPixelsFromDp(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }

    public static String getSizeName(Context context) {
        int screenLayout = context.getResources().getConfiguration().screenLayout;
        screenLayout &= Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenLayout) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return "large";
            case 4: // Configuration.SCREENLAYOUT_SIZE_XLARGE is API >= 9
                return "xlarge";
            default:
                return "undefined";
        }
    }

    public static float getScreenDensity(Context context){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return context.getResources().getDisplayMetrics().density;
    }
}
