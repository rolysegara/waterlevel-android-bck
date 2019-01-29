package com.visionet.indigo_waterlevel.modulprofile.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.MvpFragment;
import com.visionet.indigo_waterlevel.basecomponentutils.CircleTransform;
import com.visionet.indigo_waterlevel.basecomponentutils.DialogBuildersUtils;
import com.visionet.indigo_waterlevel.baseutils.FieldValidator;
import com.visionet.indigo_waterlevel.modullogin.LoginActivity;
import com.visionet.indigo_waterlevel.modullogin.utils.LoginUtils;
import com.visionet.indigo_waterlevel.modulprofile.model.ModelRequestChangePassword;
import com.visionet.indigo_waterlevel.modulprofile.model.ModelResponseChangePassword;
import com.visionet.indigo_waterlevel.modulprofile.model.ModelResponseChangePasswordFailed;
import com.visionet.indigo_waterlevel.modulprofile.presenter.ProfilePresenter;
import com.visionet.indigo_waterlevel.modulprofile.view.ProfileView;
import com.visionet.indigo_waterlevel.modulregister.RegistrationActivity;

import java.io.File;

import static com.visionet.indigo_waterlevel.baseutils.BitmapUtils.convertBase64StringToBitmap;
import static com.visionet.indigo_waterlevel.baseutils.NetworkExceptionUtils.getErrorMessage;
import static com.visionet.indigo_waterlevel.modullogin.utils.LogoutUtils.setToLogout;

public class ProfileFragment extends MvpFragment<ProfilePresenter> implements ProfileView {

    View rootView;
    ImageView imgProfile;
    TextView tvUsername;
    TextView tvRule;
    TextView tvChangePhoto;
    TextView tvEmailAddress;
    TextView tvPhone;

    EditText edCurrentPassword;
    EditText edNewPassword;
    EditText edRetypePassword;
    TextView tvCurrentPassword;
    TextView tvNewPassword;
    TextView tvRetypePassword;

    DialogBuildersUtils dialogBuildersUtils;

    boolean isChangePassword = false;
    boolean isUpdatePhoto = false;

    public static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 3000;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected ProfilePresenter createPresenter() {
        return new ProfilePresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        dialogBuildersUtils = new DialogBuildersUtils(getContext());

        imgProfile = rootView.findViewById(R.id.iv_profile);
        tvUsername = rootView.findViewById(R.id.txt_user_name);
        tvRule = rootView.findViewById(R.id.txt_rule);
        tvChangePhoto = rootView.findViewById(R.id.txt_change_photo);
        tvEmailAddress = rootView.findViewById(R.id.tv_email_address);
        tvPhone = rootView.findViewById(R.id.tv_phone);

        edCurrentPassword = rootView.findViewById(R.id.ed_current_password);
        edNewPassword = rootView.findViewById(R.id.ed_new_password);
        edRetypePassword = rootView.findViewById(R.id.ed_retype_password);
        tvCurrentPassword = rootView.findViewById(R.id.tv_current_password);
        tvNewPassword = rootView.findViewById(R.id.tv_new_password);
        tvRetypePassword = rootView.findViewById(R.id.tv_retype_password);

        initPhotoProfile();
        initChangePassword();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_save:

                if (!isUpdatePhoto && !isChangePassword) {
                    updateProfile();
                } else if (!isUpdatePhoto && isChangePassword) {
                    if (FieldValidator.validateFields(edNewPassword, edRetypePassword)) {

                        if (edNewPassword.getText().toString().trim().equals(
                                edRetypePassword.getText().toString().trim()
                        )) {
                            updateProfile();
                        } else {
                            dialogBuildersUtils.errorDialog(getString(R.string.password_not_same));
                        }

                        //dialogBuildersUtils.questionDialogButton()

                    }
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initPhotoProfile() {
        if (LoginUtils.getUserProfile() != null && !LoginUtils.getUserProfile().getProfilePicture().isEmpty()) {
            Glide.with(this).load(convertBase64StringToBitmap(LoginUtils.getUserProfile().getProfilePicture()))
                    .transition(new DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .apply(RequestOptions.bitmapTransform(new CircleTransform()))
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(imgProfile);
        } else {
            Glide.with(this).load(R.drawable.ic_user_default)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .apply(RequestOptions.bitmapTransform(new CircleTransform()))
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(imgProfile);
        }

        if (LoginUtils.getUserProfile() != null) {
            tvUsername.setText(LoginUtils.getUserProfile().getName());
            tvRule.setText("(Petugas Waduk)");
            tvEmailAddress.setText(LoginUtils.getUserProfile().getEmailAddress());
            tvPhone.setText(LoginUtils.getUserProfile().getPhoneNumber());
        } else {
            tvUsername.setText("");
            tvRule.setText("");
            tvEmailAddress.setText("");
            tvPhone.setText("");
        }

        tvChangePhoto.setText(Html.fromHtml("<u>" + getString(R.string.change_photo) + "</u>"));
    }

    private void initChangePassword() {

        edCurrentPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edCurrentPassword.getText().toString().trim().length() > 0) {
                    tvNewPassword.setTextColor(Color.parseColor("#555555"));
                    tvRetypePassword.setTextColor(Color.parseColor("#555555"));

                    edNewPassword.setHintTextColor(Color.parseColor("#4c4c4c"));
                    edRetypePassword.setHintTextColor(Color.parseColor("#4c4c4c"));

                    edNewPassword.setEnabled(true);
                    edRetypePassword.setEnabled(true);

                    isChangePassword = true;
                } else {
                    tvNewPassword.setTextColor(Color.parseColor("#ececec"));
                    tvRetypePassword.setTextColor(Color.parseColor("#ececec"));

                    edNewPassword.setHintTextColor(Color.parseColor("#ececec"));
                    edRetypePassword.setHintTextColor(Color.parseColor("#ececec"));

                    edNewPassword.setEnabled(false);
                    edRetypePassword.setEnabled(false);

                    edNewPassword.setError(null);
                    edRetypePassword.setError(null);

                    isChangePassword = false;
                }
            }
        });

    }

    private void updateProfile() {
        String message = "";

        if (!isChangePassword && !isUpdatePhoto) {
            message = getString(R.string.no_data_change);
            dialogBuildersUtils.informationDialog(message);
        } else if (isUpdatePhoto && !isChangePassword) {
            message = getString(R.string.ask_update_profile) + " " + getString(R.string.update_photo) + "?";
        } else if (!isUpdatePhoto && isChangePassword) {
            message = getString(R.string.ask_update_profile) + " " + getString(R.string.change_password) + "?";
            dialogBuildersUtils.questionDialogButton("Hai " + LoginUtils.getUserProfile().getName() + "...", message)
                .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ModelRequestChangePassword modelRequestChangePassword = new ModelRequestChangePassword();
                        modelRequestChangePassword.setCurrentPassword(edCurrentPassword.getText().toString().trim());
                        modelRequestChangePassword.setNewPassword(edNewPassword.getText().toString().trim());
                        presenter.loadDataChangePassword("Bearer " + LoginUtils.getAccessToken(getContext()), modelRequestChangePassword);
                    }
                }).setNegativeButton(android.R.string.no, null)
                .setCancelable(false)
                .show();
        } else if (isUpdatePhoto && isChangePassword) {
            message = getString(R.string.ask_update_profile) + " " +
                    getString(R.string.update_photo) + " " +
                    getString(R.string.and) + " " +
                    getString(R.string.change_password) + "?";


        }

    }

    @Override
    public void showLoading() {
        ((Activity)getContext()).runOnUiThread(new Runnable() {
            public void run() {
                dialogBuildersUtils.progressDialog().show();
            }
        });
    }

    @Override
    public void hideLoading() {
        ((Activity)getContext()).runOnUiThread(new Runnable() {
            public void run() {
                dialogBuildersUtils.progressDialog().dismiss();
            }
        });
    }

    @Override
    public void getResponseUpdatePhoto() {

    }

    @Override
    public void getResponseUpdatePhotoFailed(String message) {

    }

    @Override
    public void getResponseChangePassword(final ModelResponseChangePassword modelResponseChangePassword) {
        ((Activity)getContext()).runOnUiThread(new Runnable() {
            public void run() {
                if (modelResponseChangePassword.isSuccess()) {
                    dialogBuildersUtils.informationDialogButton(getString(R.string.change_password_success))
                            .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    setToLogout(getContext());
                                }
                            }).show();
                } else {
                    dialogBuildersUtils.informationDialog(getString(R.string.change_password_failed));
                    clearFormChangePassword();
                }
            }
        });
    }

    @Override
    public void getResponseChangePaswwordFailed(final String message) {
        ((Activity)getContext()).runOnUiThread(new Runnable() {
            public void run() {
                try {
                    ModelResponseChangePasswordFailed modelResponseChangePasswordFailed =
                            new Gson().fromJson(message, ModelResponseChangePasswordFailed.class);
                    dialogBuildersUtils.errorDialog(
                            modelResponseChangePasswordFailed.getError().getMessage());

                    clearFormChangePassword();
                }catch (Exception e){
                    dialogBuildersUtils.errorDialog(getErrorMessage(getContext(), message));
                    clearFormChangePassword();
                }
            }
        });
    }

    private void clearFormChangePassword(){
        edNewPassword.setText("");
        edRetypePassword.setText("");
        edCurrentPassword.setText("");
    }

}
