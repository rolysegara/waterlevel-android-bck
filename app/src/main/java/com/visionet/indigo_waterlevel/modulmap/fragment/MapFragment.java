package com.visionet.indigo_waterlevel.modulmap.fragment;


import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.MvpFragment;
import com.visionet.indigo_waterlevel.basecomponentutils.DialogBuildersUtils;
import com.visionet.indigo_waterlevel.basecomponentutils.SearchEditTextComponent;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.constan.CameraZoomMode;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.directionhelpers.FetchURL;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.directionhelpers.TaskLoadedCallback;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.markerinfowindow.BottomSheetMarkerInfo;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.markerinfowindow.CustomMarkerInfoWindow;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.GoogleMapsLocationUtils;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.markerinfowindow.MapWrapperLayout;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.markerinfowindow.OnInfoWindowElemTouchListener;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoirPhoto;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoirSensor;
import com.visionet.indigo_waterlevel.baseutils.Dimens;
import com.visionet.indigo_waterlevel.baseutils.LocationUtils;
import com.visionet.indigo_waterlevel.modulhome.HomeActivity;
import com.visionet.indigo_waterlevel.modulhome.listener.OnHomeLocationReceivedListener;
import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseReservoirWithSensorsAndDistance;
import com.visionet.indigo_waterlevel.modulmap.model.ModelResponseReservoirWithSensorsAndDistanceFailed;
import com.visionet.indigo_waterlevel.modulmap.presenter.ReservoirPresenter;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils;
import com.visionet.indigo_waterlevel.modulmap.view.ReservoirView;
import com.visionet.indigo_waterlevel.modulreservoirdetail.DetailReservoirActivity;
import com.visionet.indigo_waterlevel.modulreservoirdetail.fragment.FragmentInformationReservoir;

import io.realm.RealmList;
import io.realm.RealmResults;

import static com.visionet.indigo_waterlevel.basehelper.AppData.DIRECTION_KEY;
import static com.visionet.indigo_waterlevel.basehelper.AppData.DRIVING_MODE;
import static com.visionet.indigo_waterlevel.basehelper.AppData.ID_RESERVOIR;
import static com.visionet.indigo_waterlevel.basehelper.AppData.KEY_PREF_MAP_DETAIL_TRAFFIC;
import static com.visionet.indigo_waterlevel.basehelper.AppData.KEY_PREF_MAP_TYPE;
import static com.visionet.indigo_waterlevel.basehelper.AppData.WALKING_MODE;
import static com.visionet.indigo_waterlevel.baseutils.Dimens.getScreenDensity;
import static com.visionet.indigo_waterlevel.baseutils.LocationUtils.getLatLngLocationPreferences;
import static com.visionet.indigo_waterlevel.baseutils.NetworkExceptionUtils.getErrorMessage;
import static com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils.saveReservoirs;

public class MapFragment extends MvpFragment<ReservoirPresenter> implements
        ReservoirView, TaskLoadedCallback, OnHomeLocationReceivedListener {

    View rootView;
    Button btMapLayers;
    Button btMapViewAll;

    SupportMapFragment mapFragment;
    GoogleMapsLocationUtils googleMapsLocationUtils;
    NestedScrollView nsvMap;
    ImageView ivTransparentImage;

    EditText edSearch;
    ImageView ivSearch;

    RealmResults<RealmModelReservoir> realmModelReservoirs;

    private final LatLng HAMBURG = new LatLng(53.558, 9.927);
    private final LatLng KIEL = new LatLng(53.551, 9.993);
    private final LatLng A = new LatLng(53.562737, 9.864143);
    private final LatLng B = new LatLng(53.746137, 9.984795);
    private final LatLng C = new LatLng(54.018442, 10.729618);

    private static final String TAG = MapFragment.class.getSimpleName();
    DialogBuildersUtils dialogBuildersUtils;

    private OnInfoWindowElemTouchListener infoButtonListener;
    MapWrapperLayout mapWrapperLayout;

    private Polyline currentPolyline;
    private GoogleMap mMap;
    private String distance = "";
    private String duration = "";

    BottomSheetBehavior sheetBehavior = null;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        //HomeActivity homeActivity = new HomeActivity();
        //homeActivity.startLocationTracking(getActivity());
        Activity activity = getActivity();
        if (activity instanceof HomeActivity) {
            HomeActivity homeActivity = (HomeActivity) activity;
            homeActivity.startLocationTracking();
        }

        realmModelReservoirs = ReservoirsUtils.getReservoirs();
        dialogBuildersUtils = new DialogBuildersUtils(getContext());
        /*if(ReservoirsUtils.getReservoirs().isEmpty()) {
            presenter.loadDataReservoirWithSort(
                    BY_DISTANCE_ASCENDING,
                    100,
                    0,
                    LocationUtils.getDoubleLocationPreferences(getContext())[0],
                    LocationUtils.getDoubleLocationPreferences(getContext())[1]
            );
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mapWrapperLayout = (MapWrapperLayout) rootView.findViewById(R.id.map_relative_layout);

        Activity activity = getActivity();
        if (activity instanceof HomeActivity) {
            ((HomeActivity) getActivity()).setOnHomeLocationReceivedListener(this);
        }

        initSearchView();
        initMap();

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_location, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_refresh_location:
                Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean hasReservoirData(){
        if(realmModelReservoirs.isEmpty()) {
            return false;
        }else{
            return true;
        }
    }

    private void initSearchView(){
        edSearch = rootView.findViewById(R.id.search_edittext);
        ivSearch = rootView.findViewById(R.id.iv_search);
    }

    private void initMap() {
        btMapLayers = (Button) rootView.findViewById(R.id.bt_map_layers);
        btMapViewAll = (Button) rootView.findViewById(R.id.bt_map_view_all);
        nsvMap = (NestedScrollView) rootView.findViewById(R.id.nsv_map);
        ivTransparentImage = (ImageView) rootView.findViewById(R.id.iv_transparent_image);

        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        googleMapsLocationUtils = new GoogleMapsLocationUtils(getContext(), mapFragment,
                KEY_PREF_MAP_TYPE, KEY_PREF_MAP_DETAIL_TRAFFIC);

        if(getScreenDensity(getContext()) > 3) {
            googleMapsLocationUtils.setButtonMyLocationMargin(0, 150, 0, 0);
        }else
        if(getScreenDensity(getContext()) > 2) {
            googleMapsLocationUtils.setButtonMyLocationMargin(0, 100, 0, 0);
        }else{
            googleMapsLocationUtils.setButtonMyLocationMargin(0, 65, 0, 0);
        }

        googleMapsLocationUtils.setGoogleMapsLocationListener(new GoogleMapsLocationUtils.GoogleMapsLocationListener() {
            @Override
            public void setupMap(final GoogleMap googleMap) {
                setUpMap(googleMap, CameraZoomMode.VIEW_MARKER_BY_RADIUS);

                btMapViewAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setUpMap(googleMap, CameraZoomMode.VIEW_ALL_MARKER);
                    }
                });

                SearchEditTextComponent searchEditTextComponent =
                        new SearchEditTextComponent(getContext(), edSearch, ivSearch);
                searchEditTextComponent.setSearchEditTextComponentListener(
                        new SearchEditTextComponent.SearchEditTextComponentListener() {
                    @Override
                    public void onClearKeyword() {
                        realmModelReservoirs = ReservoirsUtils.getReservoirs();
                        setUpMap(googleMap, CameraZoomMode.VIEW_MARKER_BY_RADIUS);
                    }

                    @Override
                    public void onSearch(String keyword) {
                        realmModelReservoirs = ReservoirsUtils.getReservoirsByName(keyword);
                        setUpMap(googleMap, CameraZoomMode.VIEW_ALL_MARKER);
                    }
                });
            }

            @Override
            public void getLastLocation(Location location) {

            }
        });

        btMapLayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleMapsLocationUtils.showPopupLayers();
            }
        });

        ivTransparentImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        nsvMap.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        nsvMap.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        nsvMap.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });

    }

    @Override
    protected ReservoirPresenter createPresenter() {
        return new ReservoirPresenter(this);
    }

    @Override
    public void showLoadingReservoir() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    dialogBuildersUtils.setProgressDialog("Load Data Reservoir..");
                    dialogBuildersUtils.progressDialog().show();
                } catch (Exception e) {
                    Log.d("ProgressMapFragment", e.getMessage());
                }
            }
        });
    }

    @Override
    public void hideLoadingReservoir() {
        //getActivity().runOnUiThread(new Runnable() {
        //public void run() {
        dialogBuildersUtils.progressDialog().dismiss();
        //}
        //});
    }

    @Override
    public void getResponseReservoirsWithSensorAndDistanceSuccess(
            ModelResponseReservoirWithSensorsAndDistance modelResponseReservoirWithSensorsAndDistance) {
        if (modelResponseReservoirWithSensorsAndDistance.isSuccess()) {
            try {
                RealmList<RealmModelReservoir> realmModelReservoirs = new RealmList<>();

                for (ModelResponseReservoirWithSensorsAndDistance.ResultBean.ItemsBean reservoir :
                        modelResponseReservoirWithSensorsAndDistance.getResult().getItems()
                        ) {
                    RealmModelReservoir realmModelReservoir = new RealmModelReservoir();
                    RealmList<RealmModelReservoirSensor> realmModelReservoirSensors = new RealmList<>();
                    RealmList<RealmModelReservoirPhoto> realmModelReservoirPhotos = new RealmList<>();

                    if (reservoir.getPhoto() != null) {
                        for (ModelResponseReservoirWithSensorsAndDistance.ResultBean.ItemsBean.PhotoBean photoReservoir :
                                reservoir.getPhoto()) {

                            RealmModelReservoirPhoto realmModelReservoirPhoto = new RealmModelReservoirPhoto();

                            realmModelReservoirPhoto.setPhotoId(photoReservoir.getPhotoId());
                            realmModelReservoirPhoto.setPhoto(photoReservoir.getPhoto());

                            realmModelReservoirPhotos.add(realmModelReservoirPhoto);
                        }
                    } else {
                        realmModelReservoirPhotos = null;
                    }

                    if (reservoir.getSensor() != null) {
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
                    } else {
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
                    realmModelReservoir.setRealmModelReservoirPhotos(realmModelReservoirPhotos);
                    realmModelReservoir.setRealmModelReservoirSensors(realmModelReservoirSensors);

                    realmModelReservoirs.add(realmModelReservoir);
                }

                saveReservoirs(realmModelReservoirs);

            } catch (final Exception e) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        dialogBuildersUtils.errorDialog(e.getMessage());
                    }
                });
            }
        } else {

        }
    }

    @Override
    public void getResponseReservoirsWithSensorAndDistanceFailed(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    ModelResponseReservoirWithSensorsAndDistanceFailed modelResponseReservoirWithSensorsAndDistanceFailed =
                            new Gson().fromJson(message, ModelResponseReservoirWithSensorsAndDistanceFailed.class);
                    dialogBuildersUtils.errorDialog(
                            modelResponseReservoirWithSensorsAndDistanceFailed.getError().getMessage());
                } catch (Exception e) {
                    dialogBuildersUtils.errorDialog(getErrorMessage(getContext(), message));
                    Log.d("ResponseErrorBodyLogin", message);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("MyMap", "onResume");
        googleMapsLocationUtils.onResume();
    }

    private void setUpMap(final GoogleMap mMap, int cameraZoomMode) {

        mMap.clear();

        this.mMap = mMap;

        mapWrapperLayout.init(mMap, Dimens.getPixelsFromDp(getContext(), 39 + 20));



        CustomMarkerInfoWindow customMarkerInfoWindow = new CustomMarkerInfoWindow(getContext(), mMap, mapWrapperLayout);
        mMap.setInfoWindowAdapter(customMarkerInfoWindow);
        customMarkerInfoWindow.setCustomMarkerInfoWindowListener(new CustomMarkerInfoWindow.CustomMarkerInfoWindowListener() {
            @Override
            public void onDirectionModeWalkingClick(RealmModelReservoir realmModelReservoir) {
                LatLng latLongReservoir = new LatLng(realmModelReservoir.getLatitude(), realmModelReservoir.getLongitude());
                new FetchURL(getContext(), MapFragment.this).execute(
                        googleMapsLocationUtils.getUrlDirection(
                                getLatLngLocationPreferences(getContext()), latLongReservoir, WALKING_MODE, DIRECTION_KEY), WALKING_MODE);
                if(sheetBehavior != null) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

            }

            @Override
            public void onDirectionModeDrivingClick(RealmModelReservoir realmModelReservoir) {
                LatLng latLongReservoir = new LatLng(realmModelReservoir.getLatitude(), realmModelReservoir.getLongitude());
                new FetchURL(getContext(), MapFragment.this).execute(
                        googleMapsLocationUtils.getUrlDirection(
                                getLatLngLocationPreferences(getContext()), latLongReservoir, DRIVING_MODE, DIRECTION_KEY), DRIVING_MODE);
                if(sheetBehavior != null) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onMapNavigationClick(final RealmModelReservoir realmModelReservoir) {
                dialogBuildersUtils.questionDialogButton(
                        getString(R.string.map_navigation),
                        getString(R.string.allow_open_navigation))
                        .setPositiveButton(getContext().getString(R.string.yes), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                googleMapsLocationUtils.getNavigationWithGoogleMap(
                                        String.valueOf(realmModelReservoir.getLatitude()),
                                        String.valueOf(realmModelReservoir.getLongitude()));
                            }
                        })
                        .setNegativeButton(getContext().getString(R.string.no), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }

            @Override
            public void onRemoveDirectionPath() {
                if (currentPolyline != null)
                    currentPolyline.remove();
            }
        });

        if(getScreenDensity(getContext()) > 3) {
            mMap.setPadding(0, 0, 0, 185);
        }else
        if(getScreenDensity(getContext()) > 2) {
            mMap.setPadding(0, 0, 0, 135);
        }else{
            mMap.setPadding(0, 0, 0, 80);
        }
        Log.d("Ratio", String.valueOf(getScreenDensity(getContext())));

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        if(hasReservoirData()) {
            for (RealmModelReservoir realmModelReservoir : realmModelReservoirs) {
                LatLng latLng = new LatLng(realmModelReservoir.getLatitude(), realmModelReservoir.getLongitude());

                Marker reservoirMarker = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(realmModelReservoir.getName())
                        .snippet(realmModelReservoir.getAreaName())
                        .icon(BitmapDescriptorFactory
                                .fromResource(ReservoirsLevelUtils.getReservoirLevelResourceMarker(
                                        getContext(), realmModelReservoir.getLastStatusAverage()))));
                reservoirMarker.setTag(realmModelReservoir);

                builder.include(reservoirMarker.getPosition());
            }
        }

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Location location = googleMapsLocationUtils.getLocation();
                if (location != null) {
                    LocationUtils.setLocationPreferences(getContext(), location.getLatitude(), location.getLongitude());
                    Log.d("GPSLocation_fragment_De", String.valueOf(location.getLatitude()) + "," +
                            String.valueOf(location.getLongitude()));
                    Toast.makeText(getContext(), LocationUtils.getStringLocationPreferences(getContext())[0] + ", " +
                    LocationUtils.getStringLocationPreferences(getContext())[1], Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(sheetBehavior != null) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                if (currentPolyline != null)
                    currentPolyline.remove();
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                final RealmModelReservoir realmModelReservoir = (RealmModelReservoir) marker.getTag();
                if(realmModelReservoir != null) {
                    if (marker.getTitle().equals(realmModelReservoir.getName())) {
                        sheetBehavior = BottomSheetMarkerInfo.initViewBottomSheetMarkerInfo(
                                getContext(), rootView, realmModelReservoir);
                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                        googleMapsLocationUtils.viewMarkerByRadius(15000, marker.getPosition());

                        RelativeLayout rlBottomSheet = rootView.findViewById(R.id.rl_bottom_sheet);
                        rlBottomSheet.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goToDetailReservoir(realmModelReservoir);
                            }
                        });

                        RelativeLayout rlBottomSheetMediumDensity = rootView.findViewById(R.id.rl_bottom_sheet_medium_density);
                        rlBottomSheetMediumDensity.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goToDetailReservoir(realmModelReservoir);
                            }
                        });

                        RelativeLayout rlBottomSheetLowDensity = rootView.findViewById(R.id.rl_bottom_sheet_low_density);
                        rlBottomSheetLowDensity.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                goToDetailReservoir(realmModelReservoir);
                            }
                        });
                    }
                }

                return false;
            }
        });

        switch (cameraZoomMode){
            case CameraZoomMode
                    .VIEW_ALL_MARKER: {
                        googleMapsLocationUtils.viewAllMarker(builder);
                    }
                    break;
            case CameraZoomMode.VIEW_MARKER_BY_RADIUS: {
                        googleMapsLocationUtils.viewMarkerByRadius(5000, getLatLngLocationPreferences(getContext()));
                    }
                    break;
        }

    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);

        distance = (String) values[1];
        duration = (String) values[2];
    }

    private void goToDetailReservoir(RealmModelReservoir realmModelReservoir){
        Intent i = new Intent(getContext(), DetailReservoirActivity.class);
        i.putExtra(ID_RESERVOIR, realmModelReservoir.getId());
        Log.d("ReservoirId", String.valueOf(realmModelReservoir.getId()) + " - ");
        Toast.makeText(getContext(), "Click", Toast.LENGTH_LONG).show();
        startActivity(i);
    }

    @Override
    public void onHomeLocationReceived(String latitude, String longitude) {
        Log.d("GPSLocation_fragment_D", latitude + "," +
                longitude);
        LatLng latLng = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
        LatLngBounds bounds = mMap.getProjection().getVisibleRegion().latLngBounds;
        //if(!bounds.contains(latLng)){
        googleMapsLocationUtils.viewMarkerByRadiusWithoutAnimateCamera(5000, latLng);
        //}
    }
}
