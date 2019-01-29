package com.visionet.indigo_waterlevel.baseutils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SoftKeyboardUtils {
    public static void hideSoftKeyboard(View view, Context context){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftKeyboard(View view, Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
}
