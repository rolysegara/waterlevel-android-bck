package com.visionet.indigo_waterlevel.basecomponentutils.googlemap.markerinfowindow;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;

public class CustomMarkerInfoWindow implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private View rootView;
    private MapWrapperLayout mapWrapperLayout;
    private GoogleMap mMap;
    LinearLayout llNavigation;
    Button btMapDirectionModeWalking;
    Button btMapDirectionModeDriving;
    private OnInfoWindowElemTouchListener btMapDirectionModeWalkingListener;
    private OnInfoWindowElemTouchListener btMapDirectionModeDrivingListener;
    private OnInfoWindowElemTouchListener btMapNavigationListener;
    RealmModelReservoir realmModelReservoir;
    private CustomMarkerInfoWindowListener listener;
    private boolean isWalkingModeOn = false;
    private boolean isDrivingModeOn = false;
    private Marker markerInfoWindow;

    public CustomMarkerInfoWindow(Context ctx, GoogleMap mMap, MapWrapperLayout mapWrapperLayout) {
        context = ctx;
        this.mMap = mMap;
        this.mapWrapperLayout = mapWrapperLayout;
        this.listener = null;

    }

    public void setCustomMarkerInfoWindowListener(CustomMarkerInfoWindowListener listener) {
        this.listener = listener;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        this.markerInfoWindow = marker;
        rootView = ((Activity) context).getLayoutInflater()
                .inflate(R.layout.layout_marker_info_window, null);

        TextView tvTitle = rootView.findViewById(R.id.tv_title);
        TextView tvSnippet = rootView.findViewById(R.id.tv_snippet);
        llNavigation = rootView.findViewById(R.id.ll_navigation);


        tvTitle.setText(marker.getTitle());
        tvSnippet.setText(marker.getSnippet());

        realmModelReservoir = (RealmModelReservoir) marker.getTag();

        if(marker.getTitle().equalsIgnoreCase("My Location")){
            llNavigation.setVisibility(View.GONE);
        }

        setBtMapDirectionModeWalking(context, rootView, btMapDirectionModeWalkingListener);
        setBtMapDirectionModeDriving(context, rootView, btMapDirectionModeDrivingListener);
        setBtMapNavigation(context, rootView, btMapNavigationListener);

        mMap.setOnInfoWindowCloseListener(new GoogleMap.OnInfoWindowCloseListener() {
            @Override
            public void onInfoWindowClose(Marker marker) {
                if (realmModelReservoir != null) {
                    listener.onRemoveDirectionPath();
                }
            }
        });

        mapWrapperLayout.setMarkerWithInfoWindow(marker, rootView);

        return rootView;
    }

    private void setBtMapDirectionModeWalking(final Context context, View rootView, OnInfoWindowElemTouchListener onInfoWindowElemTouchListener) {
        btMapDirectionModeWalking = rootView.findViewById(R.id.bt_map_direction_mode_walking);
        onInfoWindowElemTouchListener = new OnInfoWindowElemTouchListener(btMapDirectionModeWalking, context.getResources().getDrawable(R.drawable.ic_map_direction_walking_off), context.getResources().getDrawable(R.drawable.ic_map_direction_walking)) {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                if (!isWalkingModeOn) {
                    if (listener != null) {
                        if (realmModelReservoir != null) {
                            markerInfoWindow.hideInfoWindow();
                            listener.onDirectionModeWalkingClick(realmModelReservoir);
                        }
                    }

                    btMapDirectionModeWalking.setBackgroundResource(R.drawable.ic_map_direction_walking);
                    if(isDrivingModeOn){
                        btMapDirectionModeDriving.setBackgroundResource(R.drawable.ic_map_direction_driving_off);
                        isDrivingModeOn = false;
                    }

                    isWalkingModeOn = true;
                } else {
                    if (realmModelReservoir != null) {
                        listener.onRemoveDirectionPath();
                    }

                    btMapDirectionModeWalking.setBackgroundResource(R.drawable.ic_map_direction_walking_off);

                    isWalkingModeOn = false;
                }
            }
        };
        btMapDirectionModeWalking.setOnTouchListener(onInfoWindowElemTouchListener);
    }

    private void setBtMapDirectionModeDriving(final Context context, View rootView, OnInfoWindowElemTouchListener onInfoWindowElemTouchListener) {
        btMapDirectionModeDriving = rootView.findViewById(R.id.bt_map_direction_mode_driving);
        onInfoWindowElemTouchListener = new OnInfoWindowElemTouchListener(btMapDirectionModeDriving, context.getResources().getDrawable(R.drawable.ic_map_direction_walking_off), context.getResources().getDrawable(R.drawable.ic_map_direction_walking)) {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                if (!isDrivingModeOn) {
                    if (listener != null) {
                        if (realmModelReservoir != null) {
                            markerInfoWindow.hideInfoWindow();
                            listener.onDirectionModeDrivingClick(realmModelReservoir);
                        }
                    }

                    btMapDirectionModeDriving.setBackgroundResource(R.drawable.ic_map_direction_driving);
                    if(isWalkingModeOn){
                        btMapDirectionModeDriving.setBackgroundResource(R.drawable.ic_map_direction_walking_off);
                        isWalkingModeOn = false;
                    }

                    isDrivingModeOn = true;
                } else {
                    if (realmModelReservoir != null) {
                        listener.onRemoveDirectionPath();
                    }

                    btMapDirectionModeDriving.setBackgroundResource(R.drawable.ic_map_direction_driving_off);

                    isDrivingModeOn = false;
                }
            }
        };
        btMapDirectionModeDriving.setOnTouchListener(onInfoWindowElemTouchListener);
    }

    private void setBtMapNavigation(final Context context, View rootView, OnInfoWindowElemTouchListener onInfoWindowElemTouchListener) {
        Button btMapNavigation = rootView.findViewById(R.id.bt_map_navigation);
        onInfoWindowElemTouchListener = new OnInfoWindowElemTouchListener(btMapNavigation, context.getResources().getDrawable(R.drawable.ic_map_navigation_1), context.getResources().getDrawable(R.drawable.ic_map_navigation_2)) {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                if (listener != null) {
                    if (realmModelReservoir != null) {
                        listener.onMapNavigationClick(realmModelReservoir);
                    }
                }
            }
        };
        btMapNavigation.setOnTouchListener(onInfoWindowElemTouchListener);
    }

    public interface CustomMarkerInfoWindowListener {
        public void onDirectionModeWalkingClick(RealmModelReservoir realmModelReservoir);

        public void onDirectionModeDrivingClick(RealmModelReservoir realmModelReservoir);

        public void onMapNavigationClick(RealmModelReservoir realmModelReservoir);

        public void onRemoveDirectionPath();
    }
}
