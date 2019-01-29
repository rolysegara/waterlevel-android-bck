package com.visionet.indigo_waterlevel.baseutils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

public class ScreenSizeAndOientation {

    // deprecated
    //int screenHeight = display.getHeight();
    //int screenWidth = display.getWidth();

    public static int getOrientation(Context context) {
        // orientation (either ORIENTATION_LANDSCAPE, ORIENTATION_PORTRAIT)
        return context.getResources().getConfiguration().orientation;
    }

    public static int getWidthSizeInPixel(Context context) {
        // display size in pixels
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        Log.i(context.getPackageName(), "width = " + width);

        return width;
    }

    public static int getHeightSizeInPixel(Context context) {
        // display size in pixels
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;
        Log.i(context.getPackageName(), "height = " + height);

        return height;
    }

    public static String getDisplayName(Context context) {
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return display.getName();  // minSdkVersion=17+
        } else {
            return "minSdkVersion=17+";
        }
    }

    public static int getWidthPixels(Context context) {
        // pixels, dpi
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        Log.i(context.getPackageName(), "widthPixels  = " + widthPixels);

        return widthPixels;
    }

    public static int getHeightPixels(Context context) {
        // pixels, dpi
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int heightPixels = metrics.heightPixels;
        Log.i(context.getPackageName(), "heightPixels = " + heightPixels);

        return heightPixels;
    }

    public static int getDensity(Context context) {
        // pixels, dpi
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int densityDpi = metrics.densityDpi;
        Log.i(context.getPackageName(), "densityDpi   = " + densityDpi);

        return densityDpi;
    }

    public static float getXdpi(Context context) {
        // pixels, dpi
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float xdpi = metrics.xdpi;
        Log.i(context.getPackageName(), "xdpi         = " + xdpi);

        return xdpi;
    }

    public static float getYdpi(Context context) {
        // pixels, dpi
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float ydpi = metrics.ydpi;
        Log.i(context.getPackageName(), "ydpi         = " + ydpi);

        return ydpi;
    }

}
