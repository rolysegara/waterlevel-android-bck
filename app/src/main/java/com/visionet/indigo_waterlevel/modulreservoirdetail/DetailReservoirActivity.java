package com.visionet.indigo_waterlevel.modulreservoirdetail;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.BaseActivityToolbar;
import com.visionet.indigo_waterlevel.basecomponentutils.DialogBuildersUtils;
import com.visionet.indigo_waterlevel.basecomponentutils.ToolbarManagement;
import com.visionet.indigo_waterlevel.basefragmentadapter.FragmentAdapter;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.modulhome.HomeActivity;
import com.visionet.indigo_waterlevel.modulhome.listener.OnHomeLocationReceivedListener;
import com.visionet.indigo_waterlevel.modullogin.utils.LoginUtils;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelRequestBookmarkReservoirOrClear;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelResponseBookmarkReservoirOrClear;
import com.visionet.indigo_waterlevel.modulreservoir.model.ModelResponseGetBookmarkedReservoirs;
import com.visionet.indigo_waterlevel.modulreservoir.presenter.BookmarkReservoirPresenter;
import com.visionet.indigo_waterlevel.modulreservoir.view.BookmarkReservoirView;
import com.visionet.indigo_waterlevel.modulreservoirdetail.fragment.FragmentInformationReservoir;
import com.visionet.indigo_waterlevel.modulreservoirdetail.fragment.FragmentReportReservoir;

import static com.visionet.indigo_waterlevel.basehelper.AppData.ID_RESERVOIR;
import static com.visionet.indigo_waterlevel.baseutils.NetworkExceptionUtils.getErrorMessage;

public class DetailReservoirActivity extends BaseActivityToolbar implements BookmarkReservoirView {

    private Menu menu;

    RealmModelReservoir realmModelReservoir;
    BookmarkReservoirPresenter bookmarkReservoirPresenter;

    DialogBuildersUtils dialogBuildersUtils;

    int id;
    boolean isSelected = false;

    private OnHomeLocationReceivedListener listener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reservoir);
        super.onCreateToolbar();

        dialogBuildersUtils = new DialogBuildersUtils(context);
        realmModelReservoir = new RealmModelReservoir();
        bookmarkReservoirPresenter = new BookmarkReservoirPresenter(this);

        initValue();
        initToolbar();


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private void initToolbar(){
        ToolbarManagement toolbarManagement = new ToolbarManagement(
                this, toolbar,
                txtTitleToolbar,
                txtSubtitleToolbar,
                ivBackgroundToolbar,
                ivBackgroundToolbarWithTab);
        toolbarManagement.setLeftTitleToolbar();
        toolbarManagement.setTranslucentToolbar();
        toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_2, true);
        toolbarManagement.setTitleBarOnly(realmModelReservoir.getName());
    }

    private void initValue(){
        id = getIntent().getIntExtra(ID_RESERVOIR, 0);
        if(id != 0) {
            realmModelReservoir = ReservoirsUtils.getReservoirsById(id);
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        Bundle args = new Bundle();
        args.putInt(ID_RESERVOIR, id);

        FragmentInformationReservoir fragmentInformationReservoir = new FragmentInformationReservoir();
        FragmentReportReservoir fragmentReportReservoir = new FragmentReportReservoir();

        fragmentInformationReservoir.setArguments(args);
        fragmentReportReservoir.setArguments(args);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(fragmentInformationReservoir, getString(R.string.information));
        adapter.addFragment(fragmentReportReservoir, getString(R.string.report));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_detail_reservoir, menu);
        this.menu = menu;

        if(realmModelReservoir.isBookmarked()){
            menu.getItem(1).setChecked(true);
            menu.getItem(1).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_eye_bookmarked));
        }else{
            menu.getItem(1).setChecked(false);
            menu.getItem(1).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_eye_unbookmaked));
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.reservoir_report) {
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.reservoir_watched) {
            /*if(menu.getItem(1).isChecked()){
                menu.getItem(1).setChecked(false);
                menu.getItem(1).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_eye_unbookmaked));
            }else{
                menu.getItem(1).setChecked(true);
                menu.getItem(1).setIcon(ContextCompat.getDrawable(this, R.drawable.ic_eye_bookmarked));
                DialogBuildersUtils dialogBuildersUtils = new DialogBuildersUtils(this);
                dialogBuildersUtils.informationDialog("Test");
            }*/
            ModelRequestBookmarkReservoirOrClear modelRequestBookmarkReservoirOrClear =
                    new ModelRequestBookmarkReservoirOrClear();
            modelRequestBookmarkReservoirOrClear.setReservoirId(realmModelReservoir.getId());
            modelRequestBookmarkReservoirOrClear.setUserId(LoginUtils.getUserId(this));
            bookmarkReservoirPresenter.loadBookmarkReservoirOrClear(modelRequestBookmarkReservoirOrClear);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoadingBookmark() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    dialogBuildersUtils.setProgressDialog("Load Bookmark..");
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
    public void getResponseBookmarkReservoirOrClear(final ModelResponseBookmarkReservoirOrClear modelResponseBookmarkReservoirOrClear) {
        runOnUiThread(new Runnable() {
            public void run() {
                if(modelResponseBookmarkReservoirOrClear.isSuccess()) {
                    try{
                        if(modelResponseBookmarkReservoirOrClear.getResult().isIsBookmarked()){
                            menu.getItem(1).setChecked(true);
                            menu.getItem(1).setIcon(ContextCompat.getDrawable(context, R.drawable.ic_eye_bookmarked));
                            dialogBuildersUtils.watchedDialog(
                                    context,
                                    modelResponseBookmarkReservoirOrClear.getResult().getMessage(),
                                    HomeActivity.class
                            ).show();
                        }else{
                            menu.getItem(1).setChecked(false);
                            menu.getItem(1).setIcon(ContextCompat.getDrawable(context, R.drawable.ic_eye_unbookmaked));
                            dialogBuildersUtils.unwatchedDialog(
                                    context,
                                    modelResponseBookmarkReservoirOrClear.getResult().getMessage()
                            ).show();
                        }

                        ReservoirsUtils.updateBookmarkReservoir(
                                realmModelReservoir.getId(), modelResponseBookmarkReservoirOrClear.getResult().isIsBookmarked());

                    }catch (final Exception e){
                        dialogBuildersUtils.errorDialog(getErrorMessage(context, e.getMessage()));
                    }
                }else{

                }
            }
        });
    }

    @Override
    public void getResponseBookmarkReservoirOrClearFailed(String message) {
//,m,m
    }

    @Override
    public void getResponseBookmarkedReservoirs(ModelResponseGetBookmarkedReservoirs modelResponseGetBookmarkedReservoirs) {

    }

    @Override
    public void getResponseBookmarkedReservoirsFailed(String message) {

    }

    @Override
    public void onLocationReceived(String latitude, String longitude) {
        super.onLocationReceived(latitude, longitude);

        if (listener != null) {
            listener.onHomeLocationReceived(latitude, longitude);
        }

        Log.d("GPSLocation_activity", latitude + "," +
                longitude);
    }

    public void setOnHomeLocationReceivedListener(OnHomeLocationReceivedListener listener) {
        this.listener = listener;
    }
}
