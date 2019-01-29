package com.visionet.indigo_waterlevel.basehelper;

public class AppData {

    //API KEY GMAPS For Direction
    public static final String DIRECTION_KEY = "AIzaSyCFCGi1l3hFxtW6edcM_WYTIGc9zPkjgWI";
    public static final String DRIVING_MODE = "driving";
    public static final String WALKING_MODE = "walking";

    //BROADCAST RECEIVER
    public static final String BROADCAST_LOCATION = "com.visionet.indigo_waterlevel.basebroadcastreceiver.LocationReceiver";

    //KEY PREFERENCES LOGIN
    public static final String KEY_PREF_IS_LOGIN = "KEY_PREF_IS_LOGIN";
    public static final String KEY_PREF_ACCESS_TOKEN = "KEY_PREF_ACCESS_TOKEN";
    public static final String KEY_PREF_USER_ID = "KEY_PREF_USER_ID";

    public static final String KEY_PREF_LOCATION_LATITUDE = "KEY_PREF_LOCATION_LATITUDE";
    public static final String KEY_PREF_LOCATION_LONGITUDE = "KEY_PREF_LOCATION_LONGITUDE";

    public static final String KEY_PREF_MAP_TYPE = "KEY_PREF_MAP_TYPE";
    public static final String KEY_PREF_MAP_DETAIL_TRAFFIC = "KEY_PREF_MAP_DETAIL_TRAFFIC";

    public static final String KEY_PREF_MAP_TYPE_DETAIL_RESERVOIR = "KEY_PREF_MAP_TYPE_DETAIL_RESERVOIR";
    public static final String KEY_PREF_MAP_DETAIL_TRAFFIC_DETAIL_RESERVOIR = "KEY_PREF_MAP_DETAIL_TRAFFIC_DETAIL_RESERVOIR";

    //KEY PARAMETER REQUEST API
    public static final String Id = "Id=";
    public static final String MaxResultCount = "MaxResultCount=";
    public static final String SkipCount = "SkipCount=";
    public static final String latitude = "latitude=";
    public static final String longitude = "longitude=";

    //KEY PARAMETER REGISTER
    public static final String REG_NAME = "name";
    public static final String REG_SURNAME = "surname";
    public static final String REG_USERNAME = "userName";
    public static final String REG_EMAIL_ADDRESS = "emailAddress";
    public static final String REG_PASSWORD = "password";
    public static final String REG_CAPTCHA_RESPONSE = "captchaResponse";

    public static final int DEFAULT_MAX_RESULT_COUNT = 1000;
    public static final int DEFAULT_SKIP_COUNT = 0;
    public static final double DEFAULT_LATITUDE = -7.9345427;
    public static final double DEFAULT_LONGITUDE = 112.6528058;

    public static final String PACKAGE_GOOGLE_MAPS = "com.google.android.apps.maps";

    public static int heightBottomNaviagtion = 0;

    //SORTING
    public static final String BY_DISTANCE_ASCENDING = "distance asc";

    //INTENT RESERVOIR
    public static final String ID_RESERVOIR = "id_reservoir";

    //INTENT LIST BOOKMARK
    public static final String FROM_BOOKMARK = "from_bookmark";

}
