package com.visionet.indigo_waterlevel.baseapp;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.basecomponentutils.BottomNavigationBehavior;
import com.visionet.indigo_waterlevel.basecomponentutils.CircleTransform;
import com.visionet.indigo_waterlevel.basecomponentutils.DialogBuildersUtils;
import com.visionet.indigo_waterlevel.basecomponentutils.ToolbarManagement;
import com.visionet.indigo_waterlevel.basehelper.AppData;
import com.visionet.indigo_waterlevel.fragment.DataReportFragment;
import com.visionet.indigo_waterlevel.fragment.HelpCenterFragment;
import com.visionet.indigo_waterlevel.modullogin.utils.LoginUtils;
import com.visionet.indigo_waterlevel.modulmap.fragment.MapFragment;
import com.visionet.indigo_waterlevel.fragment.MyReportFragment;
import com.visionet.indigo_waterlevel.modulprofile.fragment.ProfileFragment;
import com.visionet.indigo_waterlevel.modulreservoir.fragment.ReservoirFragment;
import com.visionet.indigo_waterlevel.fragment.SettingsFragment;

import static com.visionet.indigo_waterlevel.baseutils.BitmapUtils.convertBase64StringToBitmap;
import static com.visionet.indigo_waterlevel.modullogin.utils.LogoutUtils.setToLogout;

public class BaseActivityNavigation extends BaseActivity implements View.OnClickListener {

    protected Toolbar toolbar;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    //private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    protected TextView txtTitleToolbar, txtSubtitleToolbar;
    private LinearLayout bottomLayout;
    protected ImageView ivBackgroundToolbar, ivBackgroundToolbarWithTab;
    private BottomNavigationBehavior bottomNavigationBehavior;

    // urls to load navigation header background image
    // and profile image
    private static final String urlNavHeaderBg = "http://api.androidhive.info/images/nav-menu-header-bg.jpg";
    private static final String urlProfileImg = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    //private static final String TAG_HOME = "home";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_MY_REPORT = "my_report";
    private static final String TAG_HELP_CENTER = "help_center";
    private static final String TAG_SETTINGS = "settings";

    private static final String TAG_MAP = "map";
    private static final String TAG_RESERVOIR = "reservoir";
    private static final String TAG_REPORT = "report";
    public static String CURRENT_TAG = TAG_MAP;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    BottomNavigationView bottomNavigationView;

    FrameLayout frame/*, frameBottomNavigation*/;

    TextView nav_dashboard, nav_profile, nav_my_report, nav_help_center, nav_settings, nav_logout;

    ToolbarManagement toolbarManagement;

    protected void onCreateToolbar() {
        ivBackgroundToolbar = (ImageView) findViewById(R.id.iv_background_toolbar);
        ivBackgroundToolbarWithTab = (ImageView) findViewById(R.id.iv_background_toolbar_with_tab);
        txtTitleToolbar = (TextView) findViewById(R.id.toolbar_title);
        txtSubtitleToolbar = (TextView) findViewById(R.id.toolbar_subtitle);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbarManagement = new ToolbarManagement(this, toolbar, txtTitleToolbar, txtSubtitleToolbar, ivBackgroundToolbar, ivBackgroundToolbarWithTab);
        toolbarManagement.setCenterTitleToolbar(1);
        toolbarManagement.setTranslucentToolbar();

    }

    protected void onCreateNavigationDrawer(){
        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setScrimColor(ContextCompat.getColor(activity, android.R.color.transparent));
        drawer.setDrawerShadow(R.drawable.bg_nav_bar_vertical_shadow, GravityCompat.START);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        disableNavigationViewScrollbars(navigationView);

        // Navigation view header
        frame = (FrameLayout) findViewById(R.id.frame);
        //navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) findViewById(R.id.txt_drawer_user_name);
        txtWebsite = (TextView) findViewById(R.id.txt_drawer_rule);
        imgNavHeaderBg = (ImageView) findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) findViewById(R.id.iv_profile);

        nav_dashboard = (TextView) findViewById(R.id.nav_dashboard);
        nav_profile = (TextView) findViewById(R.id.nav_profile);
        nav_my_report = (TextView) findViewById(R.id.nav_my_report);
        nav_help_center = (TextView) findViewById(R.id.nav_help_center);
        nav_settings = (TextView) findViewById(R.id.nav_settings);
        nav_logout = (TextView) findViewById(R.id.nav_logout);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

    }

    protected void onCreateBottomNavigation(){
        //frameBottomNavigation = (FrameLayout) findViewById(R.id.frame_bottom_navigation);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomLayout = (LinearLayout) findViewById(R.id.bottom_layout);

        AppData.heightBottomNaviagtion = bottomNavigationView.getHeight();

        // attaching bottom sheet behaviour - hide / show on scroll
        bottomNavigationBehavior = new BottomNavigationBehavior();
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomLayout.getLayoutParams();
        layoutParams.setBehavior(bottomNavigationBehavior);

        // load the store fragment by default
        toolbar.setTitle("Map");
        CURRENT_TAG = TAG_MAP;
        clearSelection();
        nav_dashboard.setTypeface(null, Typeface.BOLD);
        bottomNavigationView.setVisibility(View.VISIBLE);
        bottomNavigationView.getMenu().getItem(0).setCheckable(true);
        toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_1);
        if(LoginUtils.getUserProfile() != null) {
            toolbarManagement.setTitleBarWithSubtitle("Hei, " + LoginUtils.getUserProfile().getName() + "!", "you don't have any task today");
        }else{
            setToLogout(context);
        }

        loadFragment(new MapFragment());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (savedInstanceState == null) {
            //navItemIndex = 0;
            //CURRENT_TAG = TAG_HOME;
            //loadHomeFragment();
            toolbar.setTitle("Map");
            CURRENT_TAG = TAG_MAP;
            clearSelection();
            nav_dashboard.setTypeface(null, Typeface.BOLD);
            bottomNavigationView.setVisibility(View.VISIBLE);
            bottomNavigationView.getMenu().getItem(0).setCheckable(true);
            bottomNavigationView.setSelectedItemId(R.id.navigation_map);
            toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_1);
            toolbarManagement.setTitleBarWithSubtitle("Hei " + LoginUtils.getUserProfile().getName() + "!", "you don't have any task today");

            Bundle bundleExtra = getIntent().getExtras();
            if(bundleExtra != null){
                if(bundleExtra.getBoolean(AppData.FROM_BOOKMARK, false)) {

                    bottomNavigationView.setSelectedItemId(R.id.navigation_list_reservoir);

                    toolbar.setTitle("Reservoir");
                    CURRENT_TAG = TAG_RESERVOIR;
                    clearSelection();
                    nav_dashboard.setTypeface(null, Typeface.BOLD);
                    toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_2, true);
                    toolbarManagement.setTitleBarOnly("RESERVOIR");
                    Fragment fragment = ReservoirFragment.newInstance(bundleExtra.getBoolean(AppData.FROM_BOOKMARK, false));
                    loadFragment(fragment);
                }else{
                    loadFragment(new MapFragment());
                    //Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
                }
            }else {
                loadFragment(new MapFragment());
                //Toast.makeText(context, "false", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void setCheckable(BottomNavigationView view, boolean checkable) {
        final Menu menu = view.getMenu();
        for(int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setCheckable(checkable);
        }
    }

    private void unCheckAllMenuItems(@NonNull final Menu menu) {
        int size = menu.size();
        for (int i = 0; i < size; i++) {
            final MenuItem item = menu.getItem(i);
            if(item.hasSubMenu()) {
                // Un check sub menu items
                unCheckAllMenuItems(item.getSubMenu());
            } else {
                item.setChecked(false);
            }
        }
    }

    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {

        if(LoginUtils.getUserProfile() != null) {
            txtName.setText(LoginUtils.getUserProfile().getName());
            txtWebsite.setText("(Petugas Waduk)");
        }else{
            txtName.setText("");
            txtWebsite.setText("");
        }

        if(LoginUtils.getUserProfile() != null && !LoginUtils.getUserProfile().getProfilePicture().isEmpty()){
            Glide.with(this).load(convertBase64StringToBitmap(LoginUtils.getUserProfile().getProfilePicture()))
                    .transition(new DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .apply(RequestOptions.bitmapTransform(new CircleTransform()))
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(imgProfile);
        }else{
            Glide.with(this).load(R.drawable.ic_user_default)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .apply(RequestOptions.bitmapTransform(new CircleTransform()))
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(imgProfile);
        }

        // loading header background image
        Glide.with(this).load(urlNavHeaderBg)
                .transition(new DrawableTransitionOptions().crossFade())
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imgNavHeaderBg);

        // showing dot next to notifications label
        //navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                frame.setVisibility(View.VISIBLE);
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                MapFragment mapFragment = new MapFragment();
                return mapFragment;
            case 1:
                // photos
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            case 2:
                // movies fragment
                MyReportFragment myReportFragment = new MyReportFragment();
                return myReportFragment;
            case 3:
                // notifications fragment
                HelpCenterFragment helpCenterFragment = new HelpCenterFragment();
                return helpCenterFragment;

            case 4:
                // settings fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return new MapFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        //navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        /*navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_SHOP;
                        bottomNavigationView.setVisibility(View.VISIBLE);
                        bottomNavigationView.setSelectedItemId(R.id.navigation_map);
                        break;
                    case R.id.nav_photos:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PHOTOS;
                        bottomNavigationView.setVisibility(View.GONE);
                        break;
                    case R.id.nav_movies:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MOVIES;
                        bottomNavigationView.setVisibility(View.GONE);
                        break;
                    case R.id.nav_notifications:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        bottomNavigationView.setVisibility(View.GONE);
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SETTINGS;
                        bottomNavigationView.setVisibility(View.GONE);
                        break;
                    default:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_SHOP;
                        bottomNavigationView.setVisibility(View.VISIBLE);
                        bottomNavigationView.setSelectedItemId(R.id.navigation_map);
                        break;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });*/


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            /*if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }*/

            if(!CURRENT_TAG.equals(TAG_MAP)) {
                toolbar.setTitle("Map");
                CURRENT_TAG = TAG_MAP;
                clearSelection();
                nav_dashboard.setTypeface(null, Typeface.BOLD);
                bottomNavigationView.setVisibility(View.VISIBLE);
                bottomNavigationView.getMenu().getItem(0).setCheckable(true);
                bottomNavigationView.setSelectedItemId(R.id.navigation_map);
                toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_1);
                toolbarManagement.setTitleBarWithSubtitle("Hei " + LoginUtils.getUserProfile().getName() + "!", "you don't have any task today");
                Fragment fragment = new MapFragment();
                loadFragment(fragment);
                return;
            }

        }

        super.onBackPressed();
    }

    // show or hide the fab
    private void toggleFab() {
        if (navItemIndex == 0){

        }
        else{

        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_map:
                    toolbar.setTitle("Map");
                    CURRENT_TAG = TAG_MAP;
                    clearSelection();
                    bottomNavigationView.getMenu().getItem(0).setCheckable(true);
                    nav_dashboard.setTypeface(null, Typeface.BOLD);
                    toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_1);
                    toolbarManagement.setTitleBarWithSubtitle("Hei " + LoginUtils.getUserProfile().getName() + "!", "you don't have any task today");
                    fragment = new MapFragment();
                    loadFragment(fragment);
                    bottomNavigationBehavior.showBottomNavigationView(bottomLayout);
                    return true;
                case R.id.navigation_list_reservoir:
                    toolbar.setTitle("Reservoir");
                    CURRENT_TAG = TAG_RESERVOIR;
                    clearSelection();
                    bottomNavigationView.getMenu().getItem(0).setCheckable(true);
                    nav_dashboard.setTypeface(null, Typeface.BOLD);
                    toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_2, true);
                    toolbarManagement.setTitleBarOnly("RESERVOIR");
                    fragment = new ReservoirFragment();
                    loadFragment(fragment);

                    startLocationTracking();
                    return true;
                case R.id.navigation_report:
                    toolbar.setTitle("Report");
                    CURRENT_TAG = TAG_REPORT;
                    clearSelection();
                    bottomNavigationView.getMenu().getItem(0).setCheckable(true);
                    nav_dashboard.setTypeface(null, Typeface.BOLD);
                    toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_2);
                    toolbarManagement.setTitleBarOnly("REPORT");
                    fragment = new DataReportFragment();
                    loadFragment(fragment);
                    return true;

            }

            return false;
        }
    };

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        frame.setVisibility(View.VISIBLE);
        //frameBottomNavigation.setVisibility(View.VISIBLE);
        unCheckAllMenuItems(navigationView.getMenu());
        // load fragment

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        if (navigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId() != R.id.rl_logout) {
            setSelectionItem(view);
        }

        switch (view.getId()) {
            case R.id.rl_dashboard: {
                navItemIndex = 0;
                CURRENT_TAG = TAG_MAP;
                bottomNavigationView.setVisibility(View.VISIBLE);
                bottomNavigationView.getMenu().getItem(0).setCheckable(true);
                bottomNavigationView.setSelectedItemId(R.id.navigation_map);
                toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_1);
                toolbarManagement.setTitleBarWithSubtitle("Hei " + LoginUtils.getUserProfile().getName() + "!", "you don't have any task today");
            }
            break;
            case R.id.rl_profile: {
                navItemIndex = 1;
                CURRENT_TAG = TAG_PROFILE;
                bottomNavigationView.setVisibility(View.VISIBLE);
                bottomNavigationView.getMenu().getItem(0).setCheckable(false);
                toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_2);
                toolbarManagement.setTitleBarOnly("PROFILE");
            }
            break;
            case R.id.rl_my_report: {
                navItemIndex = 2;
                CURRENT_TAG = TAG_MY_REPORT;
                bottomNavigationView.setVisibility(View.GONE);
                toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_2);
                toolbarManagement.setTitleBarOnly("MY REPORT");

            }
            break;
            case R.id.rl_help_center: {
                navItemIndex = 3;
                CURRENT_TAG = TAG_HELP_CENTER;
                bottomNavigationView.setVisibility(View.GONE);
                toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_2);
                toolbarManagement.setTitleBarOnly("HELP CENTER");

            }
            break;
            case R.id.rl_setting: {
                navItemIndex = 4;
                CURRENT_TAG = TAG_SETTINGS;
                bottomNavigationView.setVisibility(View.GONE);
                toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_2);
                toolbarManagement.setTitleBarOnly("SETTING");

            }
            break;
            case R.id.rl_logout: {
                DialogBuildersUtils dialogBuildersUtils = new DialogBuildersUtils(context);
                dialogBuildersUtils
                        .informationDialogButton(getString(R.string.sure_to_logout))
                        .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setToLogout(context);
                            }
                        }).setNegativeButton(android.R.string.no, null)
                        .show();

                drawer.closeDrawers();
            }
            break;
            default:
                navItemIndex = 0;
                CURRENT_TAG = TAG_MAP;
                bottomNavigationView.setVisibility(View.VISIBLE);
                bottomNavigationView.getMenu().getItem(0).setCheckable(true);
                bottomNavigationView.setSelectedItemId(R.id.navigation_map);
                toolbarManagement.setToolbarBackground(R.drawable.bg_toolbar_1);
                toolbarManagement.setTitleBarWithSubtitle("Hei " + LoginUtils.getUserProfile().getName() + "!", "you don't have any task today");
                break;

        }

        //Checking if the item is in checked state or not, if not make it in checked state
        //if (menuItem.isChecked()) {
        //    menuItem.setChecked(false);
        //} else {
        //    menuItem.setChecked(true);
        //}
        //menuItem.setChecked(true);

        if(view.getId() != R.id.rl_logout) {
            loadHomeFragment();
        }

        //return true;

    }

    private void setSelectionItem(View view){
        clearSelection();

        switch (view.getId()) {
            case R.id.rl_dashboard: {
                nav_dashboard.setTypeface(null, Typeface.BOLD);
            }
            break;
            case R.id.rl_profile: {
                nav_profile.setTypeface(null, Typeface.BOLD);
            }
            break;
            case R.id.rl_my_report: {
                nav_my_report.setTypeface(null, Typeface.BOLD);
            }
            break;
            case R.id.rl_help_center: {
                nav_help_center.setTypeface(null, Typeface.BOLD);
            }
            break;
            case R.id.rl_setting: {
                nav_settings.setTypeface(null, Typeface.BOLD);
            }
            break;
            case R.id.rl_logout: {
                nav_logout.setTypeface(null, Typeface.BOLD);
            }
            break;
        }
    }

    private void clearSelection(){
        nav_dashboard.setTypeface(null, Typeface.NORMAL);
        nav_profile.setTypeface(null, Typeface.NORMAL);
        nav_my_report.setTypeface(null, Typeface.NORMAL);
        nav_help_center.setTypeface(null, Typeface.NORMAL);
        nav_settings.setTypeface(null, Typeface.NORMAL);
        nav_logout.setTypeface(null, Typeface.NORMAL);
    }

}
