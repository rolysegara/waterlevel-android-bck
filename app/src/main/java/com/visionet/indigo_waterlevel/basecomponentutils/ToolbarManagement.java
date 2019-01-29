package com.visionet.indigo_waterlevel.basecomponentutils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visionet.indigo_waterlevel.R;

import static com.visionet.indigo_waterlevel.baseutils.Dimens.getActionBarHeightInPixels;
import static com.visionet.indigo_waterlevel.baseutils.WindowUtils.setTransparentStatusBar;

public class ToolbarManagement {
    Toolbar toolbar;
    AppCompatActivity appCompatActivity;
    ImageView ivBackgroundToolbar;
    ImageView ivBackgroundToolbarWithTab;
    TextView txtTitle;
    TextView txtSubTitle;
    LinearLayout.LayoutParams linearLayoutParam;

    public ToolbarManagement(AppCompatActivity appCompatActivity, Toolbar toolbar, TextView txtTitle){
        this.appCompatActivity = appCompatActivity;
        this.toolbar = toolbar;
        this.txtTitle = txtTitle;

        appCompatActivity.setSupportActionBar(toolbar);
    }

    public ToolbarManagement(AppCompatActivity appCompatActivity, Toolbar toolbar, TextView txtTitle, TextView txtSubTitle){
        this.appCompatActivity = appCompatActivity;
        this.toolbar = toolbar;
        this.txtTitle = txtTitle;
        this.txtSubTitle = txtSubTitle;

        appCompatActivity.setSupportActionBar(toolbar);
    }

    public ToolbarManagement(AppCompatActivity appCompatActivity, Toolbar toolbar, TextView txtTitle, TextView txtSubTitle, ImageView ivBackgroundToolbar, ImageView ivBackgroundToolbarWithTab){
        this.appCompatActivity = appCompatActivity;
        this.toolbar = toolbar;
        this.txtTitle = txtTitle;
        this.txtSubTitle = txtSubTitle;
        this.ivBackgroundToolbar = ivBackgroundToolbar;
        this.ivBackgroundToolbarWithTab = ivBackgroundToolbarWithTab;

        appCompatActivity.setSupportActionBar(toolbar);
    }

    public ToolbarManagement(AppCompatActivity appCompatActivity, Toolbar toolbar, TextView txtTitle, TextView txtSubTitle, ImageView ivBackgroundToolbar, ImageView ivBackgroundToolbarWithTab, LinearLayout.LayoutParams linearLayoutParam){
        this.appCompatActivity = appCompatActivity;
        this.toolbar = toolbar;
        this.txtTitle = txtTitle;
        this.txtSubTitle = txtSubTitle;
        this.ivBackgroundToolbar = ivBackgroundToolbar;
        this.ivBackgroundToolbarWithTab = ivBackgroundToolbarWithTab;
        this.linearLayoutParam = linearLayoutParam;

        appCompatActivity.setSupportActionBar(toolbar);
    }

    public void setCenterTitleToolbar(int rightMenuCount){
        final ActionBar ab = appCompatActivity.getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
        //ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
        //ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(false);

        int actionBarHeight = getActionBarHeightInPixels(appCompatActivity);

        switch (rightMenuCount){
            case 0: {
                linearLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayoutParam.rightMargin = (int) (actionBarHeight * 0.75);
                linearLayoutParam.gravity = Gravity.CENTER;
                if(txtTitle != null) {
                    txtTitle.setLayoutParams(linearLayoutParam);
                }
                if(txtSubTitle != null) {
                    txtSubTitle.setLayoutParams(linearLayoutParam);
                }
            }
            break;
            case 1: {
                linearLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                linearLayoutParam.rightMargin = (int) (actionBarHeight * 0.3);
                linearLayoutParam.gravity = Gravity.CENTER;
                if(txtTitle != null) {
                    txtTitle.setLayoutParams(linearLayoutParam);
                }
                if(txtSubTitle != null) {
                    txtSubTitle.setLayoutParams(linearLayoutParam);
                }
            }
            break;
            case 2: {
            }
            break;
            case 3: {
            }
            break;
            default:
                break;
        }
    }

    public void setLeftTitleToolbar(){
        final ActionBar ab = appCompatActivity.getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
        //ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
        //ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(false);

        linearLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayoutParam.gravity = Gravity.LEFT;
        if(txtTitle != null) {
            txtTitle.setLayoutParams(linearLayoutParam);
        }
        if(txtSubTitle != null) {
            txtSubTitle.setLayoutParams(linearLayoutParam);
        }
    }

    public void setTranslucentToolbar(){
        setTransparentStatusBar(appCompatActivity, toolbar);
    }

    public void setToolbarBackground(int imageResId) {
        if (ivBackgroundToolbar != null) {
            ivBackgroundToolbar.setVisibility(View.VISIBLE);
            ivBackgroundToolbar.setImageResource(imageResId);
        }
        if (ivBackgroundToolbarWithTab != null) {
            ivBackgroundToolbarWithTab.setVisibility(View.GONE);
            ivBackgroundToolbarWithTab.setImageResource(imageResId);
        }
    }

    public void setToolbarBackground(int imageResId, boolean withTab) {
        if (ivBackgroundToolbar != null) {
            ivBackgroundToolbar.setVisibility(View.GONE);
            ivBackgroundToolbar.setImageResource(imageResId);
        }
        if (ivBackgroundToolbarWithTab != null) {
            ivBackgroundToolbarWithTab.setVisibility(View.VISIBLE);
            ivBackgroundToolbarWithTab.setImageResource(imageResId);
        }
    }

    public void setTitleBarOnly(){
        if(txtTitle != null) {
            txtTitle.setVisibility(View.VISIBLE);
        }
        if(txtSubTitle != null) {
            txtSubTitle.setVisibility(View.GONE);
        }
    }

    public void setTitleBarOnly(String title){
        if(txtTitle != null) {
            txtTitle.setText(title);
            txtTitle.setVisibility(View.VISIBLE);
        }

        if(txtSubTitle != null) {
            txtSubTitle.setVisibility(View.GONE);
        }
    }

    public void setTitleBarWithSubtitle(){
        if(txtTitle != null) {
            txtTitle.setVisibility(View.VISIBLE);
        }
        if(txtSubTitle != null) {
            txtSubTitle.setVisibility(View.VISIBLE);
        }
    }

    public void setTitleBarWithSubtitle(String title, String subtitle){
        if(txtTitle != null) {
            txtTitle.setText(title);
            txtTitle.setVisibility(View.VISIBLE);
        }
        if(txtSubTitle != null) {
            txtSubTitle.setText(subtitle);
            txtSubTitle.setVisibility(View.VISIBLE);
        }
    }

    public void hideToolbarTitle(){
        if(txtTitle != null) {
            txtTitle.setVisibility(View.GONE);
        }
        if(txtSubTitle != null) {
            txtSubTitle.setVisibility(View.GONE);
        }
    }
}
