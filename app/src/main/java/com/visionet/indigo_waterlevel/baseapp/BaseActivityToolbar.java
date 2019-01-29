package com.visionet.indigo_waterlevel.baseapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.basecomponentutils.ToolbarManagement;

public class BaseActivityToolbar extends BaseActivity {
    protected Toolbar toolbar;
    protected TextView txtTitleToolbar, txtSubtitleToolbar;
    protected ImageView ivBackgroundToolbar, ivBackgroundToolbarWithTab;

    protected void onCreateToolbar() {
        ivBackgroundToolbar = (ImageView) findViewById(R.id.iv_background_toolbar);
        ivBackgroundToolbarWithTab = (ImageView) findViewById(R.id.iv_background_toolbar_with_tab);
        txtTitleToolbar = (TextView) findViewById(R.id.toolbar_title);
        txtSubtitleToolbar = (TextView) findViewById(R.id.toolbar_subtitle);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        ToolbarManagement toolbarManagement = new ToolbarManagement(this, toolbar, txtTitleToolbar, txtSubtitleToolbar, ivBackgroundToolbar, ivBackgroundToolbarWithTab);
        toolbarManagement.setCenterTitleToolbar(1);
        toolbarManagement.setTranslucentToolbar();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
