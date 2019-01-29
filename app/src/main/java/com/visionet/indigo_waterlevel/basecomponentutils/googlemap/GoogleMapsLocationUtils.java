package com.visionet.indigo_waterlevel.basecomponentutils.googlemap;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.basecomponentutils.CircleTransform;
import com.visionet.indigo_waterlevel.basecomponentutils.DialogBuildersUtils;
import com.visionet.indigo_waterlevel.basehelper.AppData;
import com.visionet.indigo_waterlevel.baseutils.IntentUtils;
import com.visionet.indigo_waterlevel.baseutils.LocationUtils;
import com.visionet.indigo_waterlevel.baseutils.PreferenceUtils;

import static com.visionet.indigo_waterlevel.basehelper.AppData.DRIVING_MODE;
import static com.visionet.indigo_waterlevel.basehelper.AppData.WALKING_MODE;

public class GoogleMapsLocationUtils implements
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private Context context;
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location location;
    private DialogBuildersUtils dialogBuildersUtils;

    private String preferenceKeyMapType = "";
    private String preferenceKeyMapTrafficLightEnabled = "";

    PopupLayersMap popupLayersMap;

    private GoogleMapsLocationListener listener;

    public GoogleMapsLocationUtils(Context context, SupportMapFragment mapFragment,
                                   String preferenceKeyMapType, String preferenceKeyMapTrafficLightEnabled){
        this.context = context;
        this.listener = null;
        this.preferenceKeyMapType = preferenceKeyMapType;
        this.preferenceKeyMapTrafficLightEnabled = preferenceKeyMapTrafficLightEnabled;
        this.dialogBuildersUtils = new DialogBuildersUtils(this.context);

        setupMapFragment(mapFragment);
        setupGoogleAPI(mGoogleApiClient);
        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

    }

    public void setGoogleMapsLocationListener(GoogleMapsLocationListener listener) {
        this.listener = listener;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("MyMap", "onMapReady");
        this.mMap = googleMap;
        popupLayersMap = new PopupLayersMap(context, mMap);
        setupMap();
        listener.setupMap(mMap);
    }

    public void onResume() {
        Log.d("MyMap", "onResume");
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {

        if (mMap == null) {

            Log.d("MyMap", "setUpMapIfNeeded");

            mapFragment.getMapAsync(this);
        }
    }

    private void setupMapFragment(SupportMapFragment mapFragment){
        this.mapFragment = mapFragment;
        this.mapFragment.getMapAsync(this);
    }

    private void setupMap(){
        if (ActivityCompat.checkSelfPermission((Activity)context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
        }else {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMapToolbarEnabled(false);

            mMap.getUiSettings().setRotateGesturesEnabled(true);
            mMap.getUiSettings().setAllGesturesEnabled(true);
            mMap.getUiSettings().setTiltGesturesEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);

            popupLayersMap = new PopupLayersMap(context, mMap);
            popupLayersMap.setupPopup();
            popupLayersMap.setMapType(popupLayersMap.loadMapType(preferenceKeyMapType));
            popupLayersMap.setTrafficLightMode(popupLayersMap.isMapTrafficLightEnabled(preferenceKeyMapTrafficLightEnabled));
        }
    }

    private void setupGoogleAPI(GoogleApiClient googleApiClient) {
        this.mGoogleApiClient = googleApiClient;
        mGoogleApiClient = new GoogleApiClient
                .Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    public void onStart() {
        mGoogleApiClient.connect();
    }

    public void onStop() {
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        getLocation();
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        listener.getLastLocation(location);
    }

    public Location getLocation(){
        if (ActivityCompat.checkSelfPermission((Activity)context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            IntentUtils.openSettings(context);
        }else {
            mFusedLocationClient.getLastLocation()
                .addOnSuccessListener((Activity)context, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location lastLocation) {
                        if (lastLocation != null) {
                            location = lastLocation;
                        }else{
                            location = null;
                        }
                    }
                });
        }
        listener.getLastLocation(location);

        return location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void viewAllMarker(LatLngBounds.Builder builder){
        LatLngBounds bounds = builder.build();

        int width = context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.10); // offset from edges of the map 10% of screen

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);

        mMap.animateCamera(cu);
    }

    public void viewMarkerByRadius(double radiusInMeter, LatLng center){

        Circle circle = mMap.addCircle(new CircleOptions()
                .center(center)
                .radius(radiusInMeter)
                .strokeColor(Color.parseColor("#00FFFFFF"))
                .strokeWidth(5));
        circle.isVisible();
        float currentZoomLevel = getZoomLevel(circle);
        float animateZomm = currentZoomLevel + 5;

        Log.e("Zoom Level:", currentZoomLevel + "");
        Log.e("Zoom Level Animate:", animateZomm + "");

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, animateZomm));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(currentZoomLevel), 2000, null);
        Log.e("Circle Lat Long:", String.valueOf(center.latitude) + ", " + String.valueOf(center.longitude));
    }

    public void viewMarkerByRadiusWithoutAnimateCamera(double radiusInMeter, LatLng center){

        Circle circle = mMap.addCircle(new CircleOptions()
                .center(center)
                .radius(radiusInMeter)
                .strokeColor(Color.parseColor("#00FFFFFF"))
                .strokeWidth(5));
        circle.isVisible();
        float currentZoomLevel = getZoomLevel(circle);
        float animateZomm = currentZoomLevel + 5;

        Log.e("Zoom Level:", currentZoomLevel + "");
        Log.e("Zoom Level Animate:", animateZomm + "");

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, animateZomm));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(currentZoomLevel), 2000, null);
        Log.e("Circle Lat Long:", String.valueOf(center.latitude) + ", " + String.valueOf(center.longitude));
    }

    public float getZoomLevel(Circle circle) {
        float zoomLevel=0;
        if (circle != null){
            double radius = circle.getRadius();
            double scale = radius / 500;
            zoomLevel =(int) (16 - Math.log(scale) / Math.log(2));
        }
        return zoomLevel +.5f;
    }

    public void setButtonMyLocationMargin(int left, int top, int right, int bottom){
        View locationButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).
                getParent()).findViewById(Integer.parseInt("2"));

        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        rlp.setMargins(left, top, right, bottom);

        View compassButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).
                getParent()).findViewWithTag("GoogleMapCompass");

        RelativeLayout.LayoutParams rlpCompass = (RelativeLayout.LayoutParams) compassButton.getLayoutParams();
        rlpCompass.setMargins(left, top, right, bottom);
    }

    public RelativeLayout.LayoutParams getButtonMyLocationParams(){
        View locationButton = ((View) mapFragment.getView().findViewById(Integer.parseInt("1")).
                getParent()).findViewById(Integer.parseInt("2"));

        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        //rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        //rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        //rlp.setMargins(left, top, right, bottom);

        return rlp;
    }

    public void getNavigationWithGoogleMap(String latitude, String longitude){
        Uri navigationUri = Uri.parse("google.navigation:q=" + latitude + ", " + longitude);
        Intent googleMapIntent = new Intent(Intent.ACTION_VIEW, navigationUri);
        googleMapIntent.setPackage(AppData.PACKAGE_GOOGLE_MAPS);

        if (googleMapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(googleMapIntent);
        } else {
            dialogBuildersUtils.errorDialog(context.getString(R.string.google_maps_not_installed));
        }
    }

    public void showPopupLayers(){
        popupLayersMap.showPopup();
    }

    public interface GoogleMapsLocationListener {
        void setupMap(GoogleMap googleMap);
        void getLastLocation(Location location);
    }


    private class PopupLayersMap{
        private Dialog dialog;
        private Context context;
        private GoogleMap mMap;

        private TextView tvClose;

        private RelativeLayout rlMapDefault;
        private ImageView ivMapDefault;
        private View strokeDefault;
        private TextView tvMapDefault;

        private RelativeLayout rlMapSatellite;
        private ImageView ivMapSatellite;
        private View strokeSatellite;
        private TextView tvMapSatellite;

        private RelativeLayout rlMapHybrid;
        private ImageView ivMapHybrid;
        private View strokeHybrid;
        private TextView tvMapHybrid;

        private RelativeLayout rlMapTerrain;
        private ImageView ivMapTerrain;
        private View strokeTerrain;
        private TextView tvMapTerrain;

        private LinearLayout llTrafficLight;
        private ImageView ivTrafficLight;
        private TextView tvTrafficLight;

        public PopupLayersMap(Context context, GoogleMap mMap){
            this.context = context;
            this.mMap = mMap;
        }

        private Dialog setupPopup(){
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.popup_map_type);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);

            tvClose = (TextView) dialog.findViewById(R.id.tv_close);

            rlMapDefault = (RelativeLayout) dialog.findViewById(R.id.rl_map_default);
            ivMapDefault = (ImageView) dialog.findViewById(R.id.iv_map_default);
            strokeDefault = (View) dialog.findViewById(R.id.stroke_default);
            tvMapDefault = (TextView) dialog.findViewById(R.id.tv_map_default);

            rlMapSatellite = (RelativeLayout) dialog.findViewById(R.id.rl_map_satellite);
            ivMapSatellite = (ImageView) dialog.findViewById(R.id.iv_map_satellite);
            strokeSatellite = (View) dialog.findViewById(R.id.stroke_satellite);
            tvMapSatellite = (TextView) dialog.findViewById(R.id.tv_map_satellite);

            rlMapHybrid = (RelativeLayout) dialog.findViewById(R.id.rl_map_hybrid);
            ivMapHybrid = (ImageView) dialog.findViewById(R.id.iv_map_hybrid);
            strokeHybrid = (View) dialog.findViewById(R.id.stroke_hybrid);
            tvMapHybrid = (TextView) dialog.findViewById(R.id.tv_map_hybrid);

            rlMapTerrain = (RelativeLayout) dialog.findViewById(R.id.rl_map_terrain);
            ivMapTerrain = (ImageView) dialog.findViewById(R.id.iv_map_terrain);
            strokeTerrain = (View) dialog.findViewById(R.id.stroke_terrain);
            tvMapTerrain = (TextView) dialog.findViewById(R.id.tv_map_terrain);

            llTrafficLight = (LinearLayout) dialog.findViewById(R.id.ll_traffic_light);
            ivTrafficLight = (ImageView) dialog.findViewById(R.id.iv_traffic_light);
            tvTrafficLight = (TextView) dialog.findViewById(R.id.tv_traffic_light);

            tvClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            Glide.with(context).load(R.drawable.ic_map_type_normal_72)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .apply(RequestOptions.bitmapTransform(new CircleTransform()))
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(ivMapDefault);

            Glide.with(context).load(R.drawable.ic_map_type_satellite_72)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .apply(RequestOptions.bitmapTransform(new CircleTransform()))
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(ivMapSatellite);

            Glide.with(context).load(R.drawable.ic_map_type_hybrid_72)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .apply(RequestOptions.bitmapTransform(new CircleTransform()))
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(ivMapHybrid);

            Glide.with(context).load(R.drawable.ic_map_type_terrain_72)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .apply(RequestOptions.bitmapTransform(new CircleTransform()))
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(ivMapTerrain);


            setMapType(popupLayersMap.loadMapType(preferenceKeyMapType));
            setTrafficLightMode(popupLayersMap.isMapTrafficLightEnabled(preferenceKeyMapTrafficLightEnabled));

            rlMapDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            });

            rlMapSatellite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            });

            rlMapHybrid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            });

            rlMapTerrain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                }
            });

            llTrafficLight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isTrafficEnabled = !mMap.isTrafficEnabled();

                    setTrafficLightMode(isTrafficEnabled);
                }
            });

            return dialog;
        }

        private void setMapType(final int googleMapType){
            mMap.setMapType(googleMapType);
            setIconState(mMap.getMapType());
        }

        private void setTrafficLightMode(boolean isTrafficEnabled){
            if(isTrafficEnabled){
                ivTrafficLight.setImageResource(R.drawable.ic_traffic_light_1);
                tvTrafficLight.setTextColor(Color.parseColor("#2bccfe"));
            }else{
                ivTrafficLight.setImageResource(R.drawable.ic_traffic_light_2);
                tvTrafficLight.setTextColor(Color.parseColor("#7b7b7b"));
            }

            mMap.setTrafficEnabled(isTrafficEnabled);
            setIsMapTrafficLightEnabled(isTrafficEnabled, preferenceKeyMapTrafficLightEnabled);
        }

        private void setIconState(int mapType){
            clearIconState();

            switch (mapType){
                case GoogleMap.MAP_TYPE_NORMAL:{
                    strokeDefault.setVisibility(View.VISIBLE);
                    tvMapDefault.setTextColor(Color.parseColor("#2bccfe"));
                    saveMapType(GoogleMap.MAP_TYPE_NORMAL, preferenceKeyMapType);
                }
                break;
                case GoogleMap.MAP_TYPE_SATELLITE:{
                    strokeSatellite.setVisibility(View.VISIBLE);
                    tvMapSatellite.setTextColor(Color.parseColor("#2bccfe"));
                    saveMapType(GoogleMap.MAP_TYPE_SATELLITE, preferenceKeyMapType);
                }
                break;
                case GoogleMap.MAP_TYPE_HYBRID:{
                    strokeHybrid.setVisibility(View.VISIBLE);
                    tvMapHybrid.setTextColor(Color.parseColor("#2bccfe"));
                    saveMapType(GoogleMap.MAP_TYPE_HYBRID, preferenceKeyMapType);
                }
                break;
                case GoogleMap.MAP_TYPE_TERRAIN:{
                    strokeTerrain.setVisibility(View.VISIBLE);
                    tvMapTerrain.setTextColor(Color.parseColor("#2bccfe"));
                    saveMapType(GoogleMap.MAP_TYPE_TERRAIN, preferenceKeyMapType);
                }
                break;
            }
        }

        private void clearIconState(){
            strokeDefault.setVisibility(View.GONE);
            tvMapDefault.setTextColor(Color.parseColor("#7b7b7b"));

            strokeSatellite.setVisibility(View.GONE);
            tvMapSatellite.setTextColor(Color.parseColor("#7b7b7b"));

            strokeHybrid.setVisibility(View.GONE);
            tvMapHybrid.setTextColor(Color.parseColor("#7b7b7b"));

            strokeTerrain.setVisibility(View.GONE);
            tvMapTerrain.setTextColor(Color.parseColor("#7b7b7b"));
        }

        private void saveMapType(int mapType, String preferenceKey){
            PreferenceUtils.writePreferenceValue(context, preferenceKey, mapType);
        }

        private int loadMapType(String preferenceKey){
            return PreferenceUtils.readPreferenceValue(context, preferenceKey, GoogleMap.MAP_TYPE_NORMAL);
        }

        private void setIsMapTrafficLightEnabled(boolean isEnabled, String preferenceKey){
            PreferenceUtils.writePreferenceValue(context, preferenceKey, isEnabled);
        }

        private boolean isMapTrafficLightEnabled(String preferenceKey){
            return PreferenceUtils.readPreferenceValue(context, preferenceKey, false);
        }

        public void showPopup() {
            setupPopup().show();
        }


    }

    public String getUrlDirection(LatLng origin, LatLng dest, String directionMode, String apiKey) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String mode = "mode=" + directionMode;
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + apiKey;
        return url;
    }

}
