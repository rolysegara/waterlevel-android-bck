package com.visionet.indigo_waterlevel.baseutils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Size;

import static android.icu.lang.UProperty.INT_START;

public class TextUtilities {
    public static SpannableStringBuilder textBold(String text){
        SpannableStringBuilder str = new SpannableStringBuilder(text);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return str;
    }

    public static SpannableString textItalic(String text){
        SpannableString ss1 =  new SpannableString(text);
        ss1.setSpan(new StyleSpan(Typeface.ITALIC), 0, ss1.length(), 0);

        return ss1;
    }

    public static SpannableString textBoldItalic(String text){
        SpannableString ss1 =  new SpannableString(text);
        ss1.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, ss1.length(), 0);

        return ss1;
    }

    public static SpannableString textColor(String text, String color){
        SpannableString ss1 =  new SpannableString(text);
        ss1.setSpan(new StyleSpan(Color.parseColor(color)), 0, ss1.length(), 0);

        return ss1;
    }

}
