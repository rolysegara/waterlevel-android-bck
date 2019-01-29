package com.visionet.indigo_waterlevel.baseutils;

import android.content.Context;

import com.visionet.indigo_waterlevel.R;

public class NetworkExceptionUtils {
    public static String getErrorMessage(Context context, String originalMessage){
        if(originalMessage.contains("after")){
            return context.getString(R.string.server_down);
        }else if(originalMessage.contains("connect")){
            return context.getString(R.string.connection_failed);
        }else{
            return originalMessage;
        }
    }
}
