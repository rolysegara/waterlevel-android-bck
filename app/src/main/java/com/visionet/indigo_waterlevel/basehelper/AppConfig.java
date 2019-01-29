package com.visionet.indigo_waterlevel.basehelper;

/**
 * Api Documentation: http://66.96.237.20:6017/swagger/
 */

public class AppConfig {
    private static final String SERVER = "api/";

    private static final String TOKEN_AUTH = SERVER + "TokenAuth/";
    private static final String URL_AUTHENTICATE = TOKEN_AUTH + "Authenticate";

    private static final String USER = SERVER + "services/app/User/";
    private static final String URL_GET_USER_FOR_EDIT = USER + "GetUserForEdit?";

    private static final String ACCOUNT = SERVER + "Account/";
    private static final String URL_REGISTEER = ACCOUNT + "RegisterWithoutCaptcha";
    private static final String URL_SEND_PASSWORD_RESET_CODE = ACCOUNT + "SendPasswordResetCode";

    private static final String RESERVOIR = SERVER + "services/app/Reservoir/";
    private static final String URL_GET_ALL_RESERVOIR_WITH_SENSOR_AND_DISTANCE = RESERVOIR + "GetAllReservoirsWithSensorAndDistance?";

    public static final String urlGetAllReservoirWithSensorAndDistance(
            int MaxResultCount, int SkipCount, double latitude, double longitude){
        return URL_GET_ALL_RESERVOIR_WITH_SENSOR_AND_DISTANCE
                + AppData.MaxResultCount + String.valueOf(MaxResultCount) + "&"
                + AppData.SkipCount + String.valueOf(SkipCount) + "&"
                + AppData.latitude + String.valueOf(latitude) + "&"
                + AppData.longitude + String.valueOf(longitude);
    }

    public static final String urlRegister(){
        return URL_REGISTEER;
    }

    public static final String urlSendPasswordResetCode(){
        return URL_SEND_PASSWORD_RESET_CODE;
    }
}
