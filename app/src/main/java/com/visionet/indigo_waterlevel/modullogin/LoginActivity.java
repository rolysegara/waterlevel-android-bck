package com.visionet.indigo_waterlevel.modullogin;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.LayoutInflaterCompat;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.google.gson.Gson;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.MvpActivity;
import com.visionet.indigo_waterlevel.basehelper.AppData;
import com.visionet.indigo_waterlevel.basecustomfonts.EditText_Roboto_Regular;
import com.visionet.indigo_waterlevel.basecustomfonts.MyTextView_Roboto_Medium;
import com.visionet.indigo_waterlevel.basecustomfonts.MyTextView_Roboto_Regular;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoirPhoto;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoirSensor;
import com.visionet.indigo_waterlevel.baserealm.reservoirs_level.model.RealmModelReservoirsLevel;
import com.visionet.indigo_waterlevel.baseutils.LocationUtils;
import com.visionet.indigo_waterlevel.modulhome.HomeActivity;
import com.visionet.indigo_waterlevel.modullogin.model.ModelRequestAuthentication;
import com.visionet.indigo_waterlevel.modullogin.model.ModelRequestResetPasword;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseAuthentication;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseAuthenticationFailed;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseProfilePicture;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseResetPassword;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseUserProfile;
import com.visionet.indigo_waterlevel.modullogin.model.ModelResponseUserProfileFailed;
import com.visionet.indigo_waterlevel.modullogin.presenter.AuthenticationPresenter;
import com.visionet.indigo_waterlevel.modullogin.utils.LoginUtils;
import com.visionet.indigo_waterlevel.modullogin.view.LoginView;
import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseGetAllReservoirsLevel;
import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseGetAllReservoirsLevelFailed;
import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseReservoirWithSensorsAndDistance;
import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseReservoirWithSensorsAndDistanceFailed;
import com.visionet.indigo_waterlevel.modulmap.presenter.ReservoirPresenter;
import com.visionet.indigo_waterlevel.modulmap.presenter.ReservoirsLevelPresenter;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils;
import com.visionet.indigo_waterlevel.modulmap.view.ReservoirView;
import com.visionet.indigo_waterlevel.modulmap.view.ReservoirsLevelView;
import com.visionet.indigo_waterlevel.modulregister.RegistrationActivity;
import com.visionet.indigo_waterlevel.baserealm.user_profile.model.RealmModelUserProfile;
import com.visionet.indigo_waterlevel.basecomponentutils.DialogBuildersUtils;
import com.visionet.indigo_waterlevel.baseutils.FieldValidator;
import com.visionet.indigo_waterlevel.baseutils.PreferenceUtils;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelResponseBookmarkReservoirOrClear;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelResponseGetBookmarkedReservoirs;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelResponseGetBookmarkedReservoirsFailed;
import com.visionet.indigo_waterlevel.modulreservoir.presenter.BookmarkReservoirPresenter;
import com.visionet.indigo_waterlevel.modulreservoir.view.BookmarkReservoirView;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import net.hockeyapp.android.Tracking;
import net.hockeyapp.android.metrics.MetricsManager;

import java.util.regex.Matcher;

import io.realm.RealmList;

import static com.visionet.indigo_waterlevel.basehelper.AppData.BY_DISTANCE_ASCENDING;
import static com.visionet.indigo_waterlevel.basehelper.AppData.DEFAULT_MAX_RESULT_COUNT;
import static com.visionet.indigo_waterlevel.basehelper.AppData.DEFAULT_SKIP_COUNT;
import static com.visionet.indigo_waterlevel.baseutils.HockeyCrashReportUtils.checkForCrashes;
import static com.visionet.indigo_waterlevel.baseutils.HockeyCrashReportUtils.checkForUpdates;
import static com.visionet.indigo_waterlevel.baseutils.HockeyCrashReportUtils.unregisterManagers;
import static com.visionet.indigo_waterlevel.baseutils.NetworkExceptionUtils.getErrorMessage;
import static com.visionet.indigo_waterlevel.modullogin.utils.LoginUtils.saveUserProfile;
import static com.visionet.indigo_waterlevel.modullogin.utils.LoginUtils.setLoginPreferrences;
import static com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils.saveReservoirs;

public class LoginActivity extends MvpActivity<AuthenticationPresenter>
        implements LoginView, ReservoirView, ReservoirsLevelView, BookmarkReservoirView {

    Context context;
    EditText_Roboto_Regular edUsername;
    EditText_Roboto_Regular edPassword;
    MyTextView_Roboto_Medium btnSignIn;
    MyTextView_Roboto_Medium btnSignUp;
    MyTextView_Roboto_Regular tvForgotPassword;
    MyTextView_Roboto_Regular tvNoAccount;

    DialogBuildersUtils dialogBuildersUtils;

    ReservoirPresenter reservoirPresenter;
    ReservoirsLevelPresenter reservoirsLevelPresenter;
    BookmarkReservoirPresenter bookmarkReservoirPresenter;

    String accessToken = "";
    int userId = 0;
    RealmModelUserProfile realmModelUserProfile;
    RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels;

    @Override
    protected AuthenticationPresenter createPresenter() {
        return new AuthenticationPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = LoginActivity.this;
        reservoirPresenter = new ReservoirPresenter(this);
        reservoirsLevelPresenter = new ReservoirsLevelPresenter(this);
        bookmarkReservoirPresenter = new BookmarkReservoirPresenter(this);

        MetricsManager.register(getApplication());
        MetricsManager.trackEvent("LOGIN");

        initView();

        if(PreferenceUtils.readPreferenceValue(context,
                AppData.KEY_PREF_IS_LOGIN, false)){
            Intent intent = new Intent(context, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        checkForUpdates(context);
    }

    private void initView(){
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvNoAccount = findViewById(R.id.tvNoAccount);
        dialogBuildersUtils = new DialogBuildersUtils(context);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString().trim();
                String password = edPassword.getText().toString().trim();

                if(FieldValidator.validateFields(edUsername, edPassword)) {
                    ModelRequestAuthentication modelRequestAuthentication =
                            new ModelRequestAuthentication(
                                    username, password, "", true,
                                    "", true, ""
                            );

                    presenter.loadDataLogin(modelRequestAuthentication);

                }
            }
        });

        tvNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnSignUp.getVisibility() == View.GONE) {
                    btnSignUp.animate()
                        .translationY(0)
                        .alpha(1.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                btnSignUp.setVisibility(View.VISIBLE);
                            }
                        });
                    tvNoAccount.setText(getResources().getString(R.string.have_account));
                }else{
                    btnSignUp.animate()
                            .translationY(btnSignUp.getHeight())
                            .alpha(0.0f)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    btnSignUp.setVisibility(View.GONE);
                                }
                            });
                    tvNoAccount.setText(getResources().getString(R.string.no_account));
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuildersUtils.textInputDialog(
                        getString(R.string.reset_password),
                        "",
                        dialogBuildersUtils.getIconic(FontAwesome.Icon.faw_key)
                ).setInputFilter(getString(R.string.insert_email), new LovelyTextInputDialog.TextFilter() {
                    @Override
                    public boolean check(String text) {
                        Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(text);
                        return matcher.matches();
                    }
                })
                .setHint(getString(R.string.hint_reg_email_address))
                .setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                .setConfirmButton(getString(R.string.ok), new LovelyTextInputDialog.OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(final String text) {
                        dialogBuildersUtils.informationDialogButton(
                                getString(R.string.reset_password_confirmation)
                                        + " "+text+"?")
                        .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                presenter.loadDataResetPassword(new ModelRequestResetPasword(text));
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
                    }
                }).show();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Tracking.startUsage(this);
        checkForCrashes(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        Tracking.stopUsage(this);
        unregisterManagers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterManagers();
    }

    @Override
    public void showLoading() {
        try {
            runOnUiThread(new Runnable() {
                public void run() {
                    dialogBuildersUtils.progressDialog().show();
                }
            });
        }catch (Exception e){

        }
    }

    @Override
    public void hideLoading() {
        runOnUiThread(new Runnable() {
            public void run() {
                dialogBuildersUtils.progressDialog().dismiss();
            }
        });
    }

    @Override
    public void showLoadingReservoir() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    dialogBuildersUtils.setProgressDialog("Load Data Reservoir..");
                    dialogBuildersUtils.progressDialog().show();
                }catch (Exception e){
                    Log.d("ProgressMapFragment", e.getMessage());
                }
            }
        });
    }

    @Override
    public void hideLoadingReservoir() {
        dialogBuildersUtils.progressDialog().dismiss();
    }

    @Override
    public void getResponseReservoirsWithSensorAndDistanceSuccess(ModelResponseReservoirWithSensorsAndDistance modelResponseReservoirWithSensorsAndDistance) {
        if(modelResponseReservoirWithSensorsAndDistance.isSuccess()) {
            try{
                RealmList<RealmModelReservoir> realmModelReservoirs = new RealmList<>();

                for (ModelResponseReservoirWithSensorsAndDistance.ResultBean.ItemsBean reservoir:
                        modelResponseReservoirWithSensorsAndDistance.getResult().getItems()
                        ) {
                    RealmModelReservoir realmModelReservoir = new RealmModelReservoir();
                    RealmList<RealmModelReservoirSensor> realmModelReservoirSensors = new RealmList<>();
                    RealmList<RealmModelReservoirPhoto> realmModelReservoirPhotos = new RealmList<>();

                    if(reservoir.getPhoto() != null) {
                        for (ModelResponseReservoirWithSensorsAndDistance.ResultBean.ItemsBean.PhotoBean photoReservoir :
                                reservoir.getPhoto()) {

                            RealmModelReservoirPhoto realmModelReservoirPhoto = new RealmModelReservoirPhoto();

                            realmModelReservoirPhoto.setPhotoId(photoReservoir.getPhotoId());
                            realmModelReservoirPhoto.setPhoto(photoReservoir.getPhoto());

                            realmModelReservoirPhotos.add(realmModelReservoirPhoto);
                        }
                    }else{
                        realmModelReservoirPhotos = null;
                    }

                    if(reservoir.getSensor() != null) {
                        for (ModelResponseReservoirWithSensorsAndDistance.ResultBean.ItemsBean.SensorBean sensorResevoir :
                                reservoir.getSensor()) {

                            RealmModelReservoirSensor realmModelReservoirSensor = new RealmModelReservoirSensor();

                            realmModelReservoirSensor.setSensorId(sensorResevoir.getSensorId());
                            realmModelReservoirSensor.setSensorCode(sensorResevoir.getSensorCode());
                            realmModelReservoirSensor.setSensorName(sensorResevoir.getSensorName());
                            realmModelReservoirSensor.setLastLevel(sensorResevoir.getLastLevel());
                            realmModelReservoirSensor.setLastStatus(sensorResevoir.getLastStatus());

                            realmModelReservoirSensors.add(realmModelReservoirSensor);
                        }
                    }else{
                        realmModelReservoirSensors = null;
                    }

                    realmModelReservoir.setId(reservoir.getId());
                    realmModelReservoir.setCode(reservoir.getCode());
                    realmModelReservoir.setName(reservoir.getName());
                    realmModelReservoir.setAddress(reservoir.getAddress());
                    realmModelReservoir.setDescription(reservoir.getDescription());
                    realmModelReservoir.setDepth(reservoir.getDepth());
                    realmModelReservoir.setVolume(reservoir.getVolume());
                    realmModelReservoir.setAreaId(reservoir.getAreaId());
                    realmModelReservoir.setAreaName(reservoir.getAreaName());
                    realmModelReservoir.setLatitude(reservoir.getLatitude());
                    realmModelReservoir.setLongitude(reservoir.getLongitude());
                    realmModelReservoir.setDistance(reservoir.getDistance());
                    realmModelReservoir.setDistanceDescription(reservoir.getDistanceDescription());
                    realmModelReservoir.setLastLevelAverage(reservoir.getLastLevelAverage());
                    realmModelReservoir.setLastStatusAverage(reservoir.getLastStatusAverage());
                    realmModelReservoir.setCreationTime(reservoir.getCreationTime());
                    realmModelReservoir.setModificationTime(reservoir.getModificationTime());
                    realmModelReservoir.setBookmarked(false);
                    realmModelReservoir.setRealmModelReservoirPhotos(realmModelReservoirPhotos);
                    realmModelReservoir.setRealmModelReservoirSensors(realmModelReservoirSensors);

                    realmModelReservoirs.add(realmModelReservoir);
                }

                saveReservoirs(realmModelReservoirs, realmModelReservoirsLevels);
                bookmarkReservoirPresenter.loadBookmarkedReservoirs(
                        userId,
                        "",
                        "",
                        DEFAULT_MAX_RESULT_COUNT,
                        DEFAULT_SKIP_COUNT);

            }catch (final Exception e){
                runOnUiThread(new Runnable() {
                    public void run() {
                        dialogBuildersUtils.errorDialog(getErrorMessage(context, e.getMessage()));
                    }
                });
            }
        }else{

        }

    }

    @Override
    public void getResponseReservoirsWithSensorAndDistanceFailed(final String message) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    ModelResponseReservoirWithSensorsAndDistanceFailed modelResponseReservoirWithSensorsAndDistanceFailed =
                            new Gson().fromJson(message, ModelResponseReservoirWithSensorsAndDistanceFailed.class);
                    dialogBuildersUtils.errorDialog(
                            modelResponseReservoirWithSensorsAndDistanceFailed.getError().getMessage());
                }catch (Exception e){
                    dialogBuildersUtils.errorDialog(getErrorMessage(context, message));
                    Log.d("ResponseErrorBodyLogin", message);
                }
            }
        });
    }

    @Override
    public void getResponseAuthenticationSuccess(ModelResponseAuthentication modelResponseAuthentication) {
        //try {
            if(modelResponseAuthentication.isSuccess()) {
                accessToken = modelResponseAuthentication.getResult().getAccessToken();
                userId = modelResponseAuthentication.getResult().getUserId();
                //presenter.loadDataUserProfile(accessToken, userId);
                Log.d("ResponseErrorBodyLogin", accessToken);
            }else{
                setLoginPreferrences(context, false, "", 0);
                dialogBuildersUtils.errorDialog(
                        modelResponseAuthentication.getError().getMessage(),
                        modelResponseAuthentication.getError().getDetails());
            }
        Log.d("ResponseErrorBodyLogin1", String.valueOf(modelResponseAuthentication.isSuccess()));
        /*}catch (Exception e){
            saveLoginPreferrences(false, "", 0);
            dialogBuildersUtils.errorDialog(e.getMessage());
        }*/
    }

    @Override
    public void getResponseAuthenticationFailed(final String message) {
        setLoginPreferrences(context, false, "", 0);
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    ModelResponseAuthenticationFailed modelResponseAuthenticationFailed =
                            new Gson().fromJson(message, ModelResponseAuthenticationFailed.class);
                    dialogBuildersUtils.errorDialog(
                            modelResponseAuthenticationFailed.getError().getMessage(),
                            modelResponseAuthenticationFailed.getError().getDetails());
                }catch (Exception e){
                    dialogBuildersUtils.errorDialog(getErrorMessage(context, message));
                    Log.d("ResponseErrorBodyLogin", message);
                }
            }
        });
        Log.d("ResponseErrorBodyLogin", message);
    }

    @Override
    public void getResponseUserProfileSuccess(ModelResponseUserProfile modelResponseUserProfile) {

    }

    @Override
    public void getResponseUserProfileFailed(final String message) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    ModelResponseUserProfileFailed modelResponseUserProfileFailed =
                            new Gson().fromJson(message, ModelResponseUserProfileFailed.class);
                    dialogBuildersUtils.errorDialog(
                            modelResponseUserProfileFailed.getError().getMessage());
                }catch (Exception e){
                    dialogBuildersUtils.errorDialog(getErrorMessage(context, message));
                    Log.d("ResponseErrorBodyLogin", message);
                }
            }
        });
        Log.d("ResponseErrorBodyProfil", message.toString());
    }

    @Override
    public void getResponseProfilePicture(ModelResponseUserProfile modelResponseUserProfile, ModelResponseProfilePicture modelResponseProfilePicture) {
        try{

            realmModelUserProfile = new RealmModelUserProfile();

            realmModelUserProfile.setName(modelResponseUserProfile.getResult().getName());
            realmModelUserProfile.setSurname(modelResponseUserProfile.getResult().getSurname());
            realmModelUserProfile.setUserName(modelResponseUserProfile.getResult().getUserName());
            realmModelUserProfile.setEmailAddress(modelResponseUserProfile.getResult().getEmailAddress());
            realmModelUserProfile.setPhoneNumber(modelResponseUserProfile.getResult().getPhoneNumber());
            realmModelUserProfile.setIsPhoneNumberConfirmed(modelResponseUserProfile.getResult().isIsPhoneNumberConfirmed());
            realmModelUserProfile.setProfilePicture(modelResponseProfilePicture.getResult().getProfilePicture());

            saveUserProfile(realmModelUserProfile);
            reservoirsLevelPresenter.loadDataReservoirsLevel();

        }catch (Exception e){
            dialogBuildersUtils.errorDialog(e.getMessage());
        }
    }

    @Override
    public void getResponseProfilePictureFailed(final String message) {
        setLoginPreferrences(context, false, "", 0);
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    ModelResponseAuthenticationFailed modelResponseAuthenticationFailed =
                            new Gson().fromJson(message, ModelResponseAuthenticationFailed.class);
                    dialogBuildersUtils.errorDialog(
                            modelResponseAuthenticationFailed.getError().getMessage(),
                            modelResponseAuthenticationFailed.getError().getDetails());
                }catch (Exception e){
                    dialogBuildersUtils.errorDialog(getErrorMessage(context, message));
                    Log.d("ResponseErrorBodyLogin", message);
                }
            }
        });
        Log.d("ResponseErrorBodyLogin", message);
    }

    @Override
    public void getResetPassword(final ModelResponseResetPassword modelResponseResetPassword) {
        runOnUiThread(new Runnable() {
            public void run() {
                if(modelResponseResetPassword.isSuccess()) {
                    dialogBuildersUtils.informationDialog(getString(R.string.reset_password_sent_to_email));
                }
            }
        });
    }

    @Override
    public void getResetPasswordFailed(final String message) {
        setLoginPreferrences(context, false, "", 0);
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    ModelResponseAuthenticationFailed modelResponseAuthenticationFailed =
                            new Gson().fromJson(message, ModelResponseAuthenticationFailed.class);
                    dialogBuildersUtils.errorDialog(
                            modelResponseAuthenticationFailed.getError().getMessage(),
                            modelResponseAuthenticationFailed.getError().getDetails());
                }catch (Exception e){
                    dialogBuildersUtils.errorDialog(getErrorMessage(context, message));
                    Log.d("ResponseErrorBodyLogin", message);
                }
            }
        });
        Log.d("ResponseErrorBodyLogin", message);
    }

    @Override
    public void showLoadingReservoirLevel() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    dialogBuildersUtils.setProgressDialog("Load Data Reservoir Level..");
                    dialogBuildersUtils.progressDialog().show();
                }catch (Exception e){
                    Log.d("ProgressGetAllReservoir", e.getMessage());
                }
            }
        });
    }

    @Override
    public void hideLoadingReservoirLevel() {
        dialogBuildersUtils.progressDialog().dismiss();
    }

    @Override
    public void getReservoirsLevel(ModelResponseGetAllReservoirsLevel modelResponseGetAllReservoirsLevel) {

        if(modelResponseGetAllReservoirsLevel.isSuccess()) {
            try {

                Log.d("ReservoirLevel", new Gson().toJson(modelResponseGetAllReservoirsLevel, ModelResponseGetAllReservoirsLevel.class));

                realmModelReservoirsLevels = new RealmList<RealmModelReservoirsLevel>();

                for(ModelResponseGetAllReservoirsLevel.ResultBean reservoirLevel : modelResponseGetAllReservoirsLevel.getResult()){
                    RealmModelReservoirsLevel realmModelReservoirsLevel = new RealmModelReservoirsLevel();

                    realmModelReservoirsLevel.setId(reservoirLevel.getId());
                    realmModelReservoirsLevel.setName(reservoirLevel.getName());
                    realmModelReservoirsLevel.setDescription(reservoirLevel.getDescription());
                    realmModelReservoirsLevel.setReservoirId(reservoirLevel.getReservoirId());
                    realmModelReservoirsLevel.setReservoirName(reservoirLevel.getReservoirName());
                    realmModelReservoirsLevel.setLevelMin(reservoirLevel.getLevelMin());
                    realmModelReservoirsLevel.setLevelMax(reservoirLevel.getLevelMax());
                    realmModelReservoirsLevel.setCreationTime(reservoirLevel.getCreationTime());
                    realmModelReservoirsLevel.setModificationTime(reservoirLevel.getModificationTime());

                    realmModelReservoirsLevels.add(realmModelReservoirsLevel);
                }


                reservoirPresenter.loadDataReservoirWithSort(
                        BY_DISTANCE_ASCENDING,
                        DEFAULT_MAX_RESULT_COUNT,
                        DEFAULT_SKIP_COUNT,
                        LocationUtils.getDoubleLocationPreferences(context)[0],
                        LocationUtils.getDoubleLocationPreferences(context)[1]
                );
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void getReservoirsLevelFailed(final String message) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    ModelResponseGetAllReservoirsLevelFailed modelResponseGetAllReservoirsLevelFailed =
                            new Gson().fromJson(message, ModelResponseGetAllReservoirsLevelFailed.class);
                    dialogBuildersUtils.errorDialog(
                            modelResponseGetAllReservoirsLevelFailed.getError().getMessage());
                }catch (Exception e){
                    dialogBuildersUtils.errorDialog(getErrorMessage(context, message));
                    Log.d("ResponseErrorBodyLogin", message);
                }
            }
        });
    }

    @Override
    public void showLoadingBookmark() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    dialogBuildersUtils.setProgressDialog("Load Reservoir Bookmarked..");
                    dialogBuildersUtils.progressDialog().show();
                }catch (Exception e){
                    Log.d("ProgressReservoirBookma", e.getMessage());
                }
            }
        });
    }

    @Override
    public void hideLoadingBookmark() {
        dialogBuildersUtils.progressDialog().dismiss();
    }

    @Override
    public void getResponseBookmarkReservoirOrClear(ModelResponseBookmarkReservoirOrClear modelResponseBookmarkReservoirOrClear) {

    }

    @Override
    public void getResponseBookmarkReservoirOrClearFailed(String message) {

    }

    @Override
    public void getResponseBookmarkedReservoirs(ModelResponseGetBookmarkedReservoirs modelResponseGetBookmarkedReservoirs) {
        if(modelResponseGetBookmarkedReservoirs.isSuccess()) {
            try{
                RealmList<RealmModelReservoir> realmModelReservoirs = new RealmList<>();

                if(modelResponseGetBookmarkedReservoirs.getResult().getTotalCount() > 0) {
                    for (ModelResponseGetBookmarkedReservoirs.ResultBean.ItemsBean reservoirBookmarked :
                            modelResponseGetBookmarkedReservoirs.getResult().getItems()
                            ) {
                        for (RealmModelReservoir realmModelReservoir : ReservoirsUtils.getReservoirs()) {
                            if (reservoirBookmarked.getId() == realmModelReservoir.getId()) {
                                Log.d("IdBookmarked", String.valueOf(reservoirBookmarked.getId()) + " - " + String.valueOf(userId));
                                ReservoirsUtils.updateBookmarkReservoir(realmModelReservoir.getId(), true);
                            }
                        }
                    }
                }

                setLoginPreferrences(context,true, accessToken, userId);

                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
                finish();

            }catch (final Exception e){
                runOnUiThread(new Runnable() {
                    public void run() {
                        dialogBuildersUtils.errorDialog(getErrorMessage(context, e.getMessage()));
                    }
                });
            }
        }else{

        }
    }

    @Override
    public void getResponseBookmarkedReservoirsFailed(final String message) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    ModelResponseGetBookmarkedReservoirsFailed modelResponseGetBookmarkedReservoirsFailed =
                            new Gson().fromJson(message, ModelResponseGetBookmarkedReservoirsFailed.class);
                    dialogBuildersUtils.errorDialog(
                            modelResponseGetBookmarkedReservoirsFailed.getError().getMessage());
                }catch (Exception e){
                    dialogBuildersUtils.errorDialog(getErrorMessage(context, message));
                    Log.d("ResponseErrorBodyLogin", message);
                }
            }
        });
    }
}
