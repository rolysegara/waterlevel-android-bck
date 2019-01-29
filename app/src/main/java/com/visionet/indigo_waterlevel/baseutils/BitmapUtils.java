package com.visionet.indigo_waterlevel.baseutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class BitmapUtils {
    public static Bitmap convertBase64StringToBitmap(String base64String){
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
