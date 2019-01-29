package com.visionet.indigo_waterlevel.modulregister;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.LayoutInflaterCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.MvpActivity;
import com.visionet.indigo_waterlevel.basecustomfonts.EditText_Roboto_Regular;
import com.visionet.indigo_waterlevel.basecustomfonts.MyTextView_Roboto_Medium;
import com.visionet.indigo_waterlevel.basecomponentutils.DialogBuildersUtils;
import com.visionet.indigo_waterlevel.baseutils.FieldValidator;
import com.visionet.indigo_waterlevel.baseutils.TextUtilities;
import com.visionet.indigo_waterlevel.modullogin.LoginActivity;
import com.visionet.indigo_waterlevel.modulregister.model.ModelRequestRegister;
import com.visionet.indigo_waterlevel.modulregister.model.ModelResponseRegister;
import com.visionet.indigo_waterlevel.modulregister.model.ModelResponseRegisterFailed;
import com.visionet.indigo_waterlevel.modulregister.presenter.RegisterPresenter;
import com.visionet.indigo_waterlevel.modulregister.view.RegisterView;

import static com.visionet.indigo_waterlevel.baseutils.NetworkExceptionUtils.getErrorMessage;

public class RegistrationActivity extends MvpActivity<RegisterPresenter> implements RegisterView {

    Context context;
    EditText_Roboto_Regular edName;
    EditText_Roboto_Regular edSurname;
    EditText_Roboto_Regular edUsername;
    EditText_Roboto_Regular edEmailAddress;
    EditText_Roboto_Regular edPassword;
    EditText_Roboto_Regular edPassword2;
    MyTextView_Roboto_Medium btnSignUp;
    DialogBuildersUtils dialogBuildersUtils;

    String name;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        context = RegistrationActivity.this;

        initView();
    }

    private void initView(){
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        dialogBuildersUtils = new DialogBuildersUtils(RegistrationActivity.this);
        edName = findViewById(R.id.edName);
        edSurname = findViewById(R.id.edSurname);
        edUsername = findViewById(R.id.edUsername);
        edEmailAddress = findViewById(R.id.edEmailAddress);
        edPassword = findViewById(R.id.edPassword);
        edPassword2 = findViewById(R.id.edPassword2);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edName.getText().toString().trim();
                String surname = edSurname.getText().toString().trim();
                String username = edUsername.getText().toString().trim();
                String emailAddress = edEmailAddress.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                String password2 = edPassword2.getText().toString().trim();

                if(FieldValidator.validateFields(edName, edSurname, edUsername, edEmailAddress, edPassword)) {
                    if(password.equals(password2)) {
                        final ModelRequestRegister modelRequestRegister = new ModelRequestRegister(
                                name,
                                surname,
                                username,
                                emailAddress,
                                password
                        );

                        dialogBuildersUtils.informationDialogButton(
                                getString(R.string.is_your_data_correct) +
                                        "\n\n" +
                                        getString(R.string.hint_reg_name) + ": \n\t\t" +
                                        TextUtilities.textColor(name, "#000000") + "\n" +
                                        getString(R.string.hint_reg_surname) + ": \n\t\t" +
                                        TextUtilities.textColor(surname, "#000000") + "\n" +
                                        getString(R.string.hint_reg_username) + ": \n\t\t" +
                                        TextUtilities.textColor(username, "#000000") + "\n" +
                                        getString(R.string.hint_reg_email_address) + ": \n\t\t" +
                                        TextUtilities.textColor(emailAddress, "#000000") + "\n" +
                                        getString(R.string.hint_reg_password) + ": \n\t\t" +
                                        TextUtilities.textColor(password, "#000000")
                        ).setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                presenter.loadDataRegister(modelRequestRegister);
                            }
                        }).setNegativeButton(android.R.string.no, null)
                        .show();
                    }else{
                        dialogBuildersUtils.errorDialog(getString(R.string.password_not_same));
                    }
                }
            }
        });

    }

    @Override
    public void showLoading() {
        runOnUiThread(new Runnable() {
            public void run() {
                dialogBuildersUtils.progressDialog().show();
            }
        });
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
    public void onBackPressed(){
        Intent i = new Intent(context, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                Intent i = new Intent(context, LoginActivity.class);
                startActivity(i);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void getResponseRegisterSuccess(final ModelResponseRegister modelResponseRegister) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (modelResponseRegister.getResult().isCanLogin()) {
                    dialogBuildersUtils.informationDialogButton(getString(R.string.hello)
                            + " " + name + ", " +
                            getString(R.string.check_for_activate))
                            .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    finish();
                            }
                    }).show();
                } else {
                    dialogBuildersUtils.informationDialog(getString(R.string.registration_failed));
                }
            }
        });
    }

    @Override
    public void getResponseRegisterFailed(final String message) {
        Log.d("ErrorMessage", message);
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    ModelResponseRegisterFailed modelResponseRegisterFailed =
                            new Gson().fromJson(message, ModelResponseRegisterFailed.class);
                    dialogBuildersUtils.errorDialog(
                            modelResponseRegisterFailed.getError().getMessage());
                }catch (Exception e){
                    dialogBuildersUtils.errorDialog(getErrorMessage(context, message));
                    Log.d("ResponseErrorBodyLogin", message);
                }
            }
        });
    }
}
