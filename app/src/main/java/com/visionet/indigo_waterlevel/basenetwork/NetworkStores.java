package com.visionet.indigo_waterlevel.basenetwork;

import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseGetAllReservoirsLevel;
import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseReservoirWithSensorsAndDistance;
import com.visionet.indigo_waterlevel.modullogin.model.ModelRequestAuthentication;
import com.visionet.indigo_waterlevel.modullogin.model.ModelRequestResetPasword;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseAuthentication;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseProfilePicture;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseResetPassword;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseUserProfile;
import com.visionet.indigo_waterlevel.modulprofile.model.ModelRequestChangePassword;
import com.visionet.indigo_waterlevel.modulprofile.model.ModelResponseChangePassword;
import com.visionet.indigo_waterlevel.modulregister.model.ModelRequestRegister;
import com.visionet.indigo_waterlevel.modulregister.model.ModelResponseRegister;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelRequestBookmarkReservoirOrClear;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelResponseBookmarkReservoirOrClear;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelResponseGetBookmarkedReservoirs;
import com.visionet.indigo_waterlevel.modulreservoirdetail.model.ModelResponseGetProgressAllReservoirs;
import com.visionet.indigo_waterlevel.modulreservoirdetail.model.ModelResponseGetReservoirReport;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkStores {
    @POST("api/TokenAuth/Authenticate")
    Observable<ModelResponseAuthentication> getAuthentication(@Body ModelRequestAuthentication modelRequestAuthentication);

    @GET("api/services/app/Profile/GetCurrentUserProfileForEdit")
    Observable<ModelResponseUserProfile> getUserProfile(
            @Header("Authorization") String authorization);

    @GET("api/services/app/Profile/GetProfilePicture")
    Observable<ModelResponseProfilePicture> getProfilePicture(
            @Header("Authorization") String authorization);

    @POST("api/services/app/Account/SendPasswordResetCode")
    Observable<ModelResponseResetPassword> getResetPassowrd(@Body ModelRequestResetPasword modelRequestResetPasword);

    @POST("api/services/app/Account/RegisterWithoutCaptcha")
    Observable<ModelResponseRegister> getRegister(@Body ModelRequestRegister modelRequestRegister);

    @GET("api/services/app/Reservoir/GetAllReservoirsWithSensorAndDistance?")
    Observable<ModelResponseReservoirWithSensorsAndDistance> getReservoirsWithSensorAndDistance(
            @Query("CoverageId") int coverageId,
            @Query("Distance") int distance,
            @Query("Level") int level,
            @Query("StatusLike") String statusLike,
            @Query("Filter") String filter,
            @Query("Sorting") String sorting,
            @Query("MaxResultCount") int maxResultCount,
            @Query("SkipCount") int skipCount,
            @Query("latitude") double latitude,
            @Query("longitude") double longitude
    );

    @GET("api/services/app/Reservoir/GetAllReservoirsWithSensorAndDistance?")
    Observable<ModelResponseReservoirWithSensorsAndDistance> getReservoirsWithSensorAndDistanceWithSort(
            @Query("Sorting") String sorting,
            @Query("MaxResultCount") int maxResultCount,
            @Query("SkipCount") int skipCount,
            @Query("latitude") double latitude,
            @Query("longitude") double longitude
    );

    @GET("api/services/app/Reservoir/GetProgressAllReservoirs?")
    Observable<ModelResponseGetProgressAllReservoirs> getProgressAllReservoirs(
            @Query("Filter") String filter,
            @Query("Sorting") String sorting,
            @Query("MaxResultCount") int maxResultCount,
            @Query("SkipCount") int skipCount
    );

    @GET("api/services/app/Reservoir/GetAllReservoirsLevel")
    Observable<ModelResponseGetAllReservoirsLevel> getAllReservoirsLevel();

    @GET("api/services/app/Reporting/GetReservoirReportWithColorDaily?")
    Observable<ModelResponseGetReservoirReport> getReservoirReportDaily(
            @Query("reservoirId") int reservoirId,
            @Query("date") String date
    );

    @GET("api/services/app/Reporting/GetReservoirReportWithColorWeekly?")
    Observable<ModelResponseGetReservoirReport> getReservoirReportWeekly(
            @Query("reservoirId") int reservoirId,
            @Query("date") String date
    );

    @GET("api/services/app/Reporting/GetReservoirReportWithColorMonthly?")
    Observable<ModelResponseGetReservoirReport> getReservoirReportMonthly(
            @Query("reservoirId") int reservoirId,
            @Query("date") String date
    );

    @GET("api/services/app/Reporting/GetReservoirReportWithColorYearly?")
    Observable<ModelResponseGetReservoirReport> getReservoirReportYearly(
            @Query("reservoirId") int reservoirId,
            @Query("date") String date
    );

    @POST("api/services/app/Bookmark/BookmarkReservoirOrClear")
    Observable<ModelResponseBookmarkReservoirOrClear> bookmarkReservoirOrClear(
            @Body ModelRequestBookmarkReservoirOrClear modelRequestBookmarkReservoirOrClear);

    @GET("api/services/app/Bookmark/GetBookmarkedReservoirs?")
    Observable<ModelResponseGetBookmarkedReservoirs> getBookmarkedReservoirs(
            @Query("UserId") int userId,
            @Query("Filter") String filter,
            @Query("Sorting") String sorting,
            @Query("MaxResultCount") int maxResultCount,
            @Query("SkipCount") int skipCount
    );

    @POST("api/services/app/Profile/ChangePassword")
    Observable<ModelResponseChangePassword> changePassword(
            @Header("Authorization") String authorization,
            @Body ModelRequestChangePassword modelRequestChangePassword);

}
