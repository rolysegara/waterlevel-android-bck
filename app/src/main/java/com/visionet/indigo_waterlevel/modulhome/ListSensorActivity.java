package com.visionet.indigo_waterlevel.modulhome;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.BaseApplication;
import com.visionet.indigo_waterlevel.modulhome.adapter.ListSensorAdapter;
import com.visionet.indigo_waterlevel.baserealm.user_profile.controller.RealmControllerUserProfile;

import java.util.ArrayList;
import java.util.List;

public class ListSensorActivity extends AppCompatActivity implements OnMapReadyCallback {

    //private List<DataSensor> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayout mapLayout;
    private FloatingActionButton fab;
    private ListSensorAdapter mAdapter;
    //private MapRipple mapRipple;

    private boolean viewMap = false;
    SupportMapFragment mapFragment;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sensor);

        context = ListSensorActivity.this;

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*new GetReservoirWithSensorAndDistance(context,
                AppData.DEFAULT_MAX_RESULT_COUNT, AppData.DEFAULT_SKIP_COUNT,
                AppData.DEFAULT_LATITUDE, AppData.DEFAULT_LONGITUDE)
                .sentRequestReservoirWithSensorAndDistance();*/

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //mAdapter = new ListSensorAdapter(ListSensorActivity.this, movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        /*mapLayout = (LinearLayout) findViewById(R.id.map_layout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewMap == false) {
                    mapLayout.setVisibility(View.VISIBLE);
                    viewMap = true;
                } else {
                    mapLayout.setVisibility(View.GONE);
                    viewMap = false;
                }
            }
        });*/

        prepareSensorData();

        RealmControllerUserProfile realmControllerUserProfile = new RealmControllerUserProfile(new BaseApplication());
        //RealmModelUserProfile realmModelUserProfile = realmControllerUserProfile.getUserProfile(PreferenceUtils.readPreferenceValue(ListSensorActivity.this, AppData.KEY_PREF_USER_ID, 0));
        /*Toast.makeText(ListSensorActivity.this,
                realmModelUserProfile.getProfilePictureId() + " - " + realmModelUserProfile.getRealmModelRoles().get(0).getRoleDisplayName() + " - " +String.valueOf(realmModelUserProfile.getRealmModelRoles().size()), Toast.LENGTH_LONG
                ).show();*/
    }

    private void prepareSensorData() {
        /*DataSensor movie = new DataSensor(
                    "Sensor A",
                    "Lokasi 1",
                    "20,5 m",
                    "SIAGA",
                    R.drawable.s1);
        movieList.add(movie);

        movie = new DataSensor(
                    "Sensor B",
                    "Lokasi 2",
                    "15,3 m",
                    "AMAN",
                R.drawable.s2);
        movieList.add(movie);

        movie = new DataSensor(
                "Sensor C",
                "Lokasi 3",
                "35,3 m",
                "BAHAYA",
                R.drawable.s3);
        movieList.add(movie);

        movie = new DataSensor(
                "Sensor D",
                "Lokasi 4",
                "23,7 m",
                "SIAGA",
                R.drawable.s4);
        movieList.add(movie);

        movie = new DataSensor(
                "Sensor E",
                "Lokasi 5",
                "8,2 m",
                "AMAN",
                R.drawable.s5);
        movieList.add(movie);*/

        //mAdapter.notifyDataSetChanged();
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(LocationTracker.is_requesting_updates, locationTracker.isRequistingLocationUpdates());
        outState.putParcelable(LocationTracker.last_known_location, locationTracker.getCurrentLocation());
        outState.putString(LocationTracker.last_updated_on, locationTracker.getLastUpdateTime());

    }*/

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        runOnUiThread(new Runnable() {
            public void run() {
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.4233438, -122.0728817))
                        .title("LinkedIn")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                final Marker marker = googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.4629101, -122.2449094))
                        .title("Facebook")
                        .snippet("Facebook HQ: Menlo Park")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.3092293, -122.1136845))
                        .title("Apple")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                /*mapRipple = new MapRipple(googleMap, new LatLng(37.4629101, -122.2449094), context);
                mapRipple.withNumberOfRipples(3);
                mapRipple.withFillColor(Color.parseColor("#66F44336"));
                mapRipple.withStrokewidth(0);
                mapRipple.withStrokeColor(Color.parseColor("#0000FF"));
                mapRipple.withDistance(2000);
                mapRipple.startRippleMapAnimation();*/

                googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
                    @Override
                    public void onCameraMove() {
                        //mapRipple.withDistance(500);
                    }
                });

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.4233438, -122.0728817), 10));
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
