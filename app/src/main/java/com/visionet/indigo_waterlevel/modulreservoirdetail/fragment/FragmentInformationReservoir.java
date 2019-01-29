package com.visionet.indigo_waterlevel.modulreservoirdetail.fragment;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.github.mikephil.charting.charts.LineChart;
import com.github.thunder413.datetimeutils.DateTimeUtils;
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
import com.visionet.indigo_waterlevel.basecomponentutils.chart.ChartTypeSimpleLineAdapter;
import com.visionet.indigo_waterlevel.basecomponentutils.datedialog.DateDialog;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.GoogleMapsLocationUtils;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.constan.CameraZoomMode;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.directionhelpers.FetchURL;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.directionhelpers.TaskLoadedCallback;
import com.visionet.indigo_waterlevel.basecomponentutils.googlemap.markerinfowindow.BottomSheetMarkerInfo;
import com.visionet.indigo_waterlevel.basecomponentutils.multistatetoggle.TogglePeriodType;
import com.visionet.indigo_waterlevel.basehelper.DateTimeFormat;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.baserealm.reservoirs_level.model.RealmModelReservoirsLevel;
import com.visionet.indigo_waterlevel.baseutils.LocationUtils;
import com.visionet.indigo_waterlevel.modulhome.listener.OnHomeLocationReceivedListener;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils;
import com.visionet.indigo_waterlevel.modulreservoirdetail.DetailReservoirActivity;
import com.visionet.indigo_waterlevel.modulreservoirdetail.adapter.PhotoReservoirAdapter;
import com.visionet.indigo_waterlevel.modulreservoirdetail.adapter.SensorsAdapter;
import com.visionet.indigo_waterlevel.modulreservoirdetail.model.ModelResponseGetReservoirReport;
import com.visionet.indigo_waterlevel.modulreservoirdetail.model.ModelResponseGetReservoirReportFailed;
import com.visionet.indigo_waterlevel.modulreservoirdetail.presenter.ReservoirReportPresenter;
import com.visionet.indigo_waterlevel.modulreservoirdetail.view.ReservoirReportView;

import java.util.Date;
import java.util.TimeZone;

import io.realm.RealmList;
import me.itangqi.waveloadingview.WaveLoadingView;

import static com.visionet.indigo_waterlevel.basecomponentutils.AnimationUtils.createRotateAnimator;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.DAILY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.MONTHLY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.WEEKLY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.YEARLY;
import static com.visionet.indigo_waterlevel.basehelper.AppData.DIRECTION_KEY;
import static com.visionet.indigo_waterlevel.basehelper.AppData.DRIVING_MODE;
import static com.visionet.indigo_waterlevel.basehelper.AppData.ID_RESERVOIR;
import static com.visionet.indigo_waterlevel.basehelper.AppData.KEY_PREF_MAP_DETAIL_TRAFFIC_DETAIL_RESERVOIR;
import static com.visionet.indigo_waterlevel.basehelper.AppData.KEY_PREF_MAP_TYPE_DETAIL_RESERVOIR;
import static com.visionet.indigo_waterlevel.basehelper.AppData.WALKING_MODE;
import static com.visionet.indigo_waterlevel.basehelper.DateTimeFormat.FORMAT_MYSQL_DATE_TIME;
import static com.visionet.indigo_waterlevel.basehelper.DateTimeFormat.FORMAT_WITH_DAY_FULL_DATE_TIME;
import static com.visionet.indigo_waterlevel.baseutils.LocationUtils.getLatLngLocationPreferences;
import static com.visionet.indigo_waterlevel.baseutils.NetworkExceptionUtils.getErrorMessage;
import static com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils.getReservoirLevelListByLevelMinDescending;
import static com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils.getReservoirLevelPercentageLevel;

public class FragmentInformationReservoir extends MvpFragment<ReservoirReportPresenter> implements
        OnHomeLocationReceivedListener, ReservoirReportView, TogglePeriodType.ToggleListener, TaskLoadedCallback {

    View rootView;

    WaveLoadingView waveView;
    TextView tvDateTime;
    TextView tvStatus;
    TextView tvHighestMinimumLevel;

    RelativeLayout rlLevel;
    ImageView ivMoreDetails;
    LinearLayout llMoreDetails;

    ExpandableRelativeLayout elDetails;
    ImageView ivCloseDetails;
    LinearLayout llCloseDetails;

    RelativeLayout rlStatistics;
    RelativeLayout rlReservoirSensor;
    RelativeLayout rlAdditionalInfo;
    RelativeLayout rlGallery;
    RelativeLayout rlTools;

    ImageView ivStatistics;
    ImageView ivReservoirSensor;
    ImageView ivAdditionalInfo;
    ImageView ivGallery;
    ImageView ivTools;


    RealmModelReservoir realmModelReservoir;
    RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevel;

    ExpandableRelativeLayout elStatistics;
    ExpandableRelativeLayout elReservoirSensor;
    ExpandableRelativeLayout elAdditionalInfo;
    ExpandableRelativeLayout elGallery;
    ExpandableRelativeLayout elTools;

    Button btDay;
    Button btWeek;
    Button btMonth;
    Button btYear;

    TextView tvDate;
    TextView tvAverage;
    TextView tvDate2;
    TogglePeriodType togglePeriodType;
    DateDialog dateDialog;

    RecyclerView rvListSensor;
    LinearLayout llHeaderListSensor;
    LinearLayout llEmptySensor;

    TextView tvDepth;
    TextView tvVolume;
    TextView tvAreaName;
    TextView tvAddress;
    TextView tvDescription;

    Button btViewMap;
    RelativeLayout elMap;
    Button btCloseMap;
    Button btMapLayers;
    Button btMapViewAll;
    Button btMapDirectionMode;
    Button btMapNavigation;

    RecyclerView rvListPhoto;
    LinearLayout llEmptyPhotos;

    SupportMapFragment mapFragment;

    GoogleMapsLocationUtils googleMapsLocationUtils;

    BottomSheetBehavior sheetBehavior;

    String periodTypeBefore = "";

    SparseBooleanArray expandState = new SparseBooleanArray();

    int reservoirId;

    private YoYo.YoYoString rope;

    DialogBuildersUtils dialogBuildersUtils;

    private LineChart chart;

    private String directionMode = "";
    private String distance = "";
    private String duration = "";

    public FragmentInformationReservoir() {
        // Required empty public constructor
    }

    public static FragmentInformationReservoir newInstance(String param1, String param2) {
        FragmentInformationReservoir fragment = new FragmentInformationReservoir();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected ReservoirReportPresenter createPresenter() {
        return new ReservoirReportPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DateTimeUtils.setTimeZone(TimeZone.getDefault().getID());

        realmModelReservoir = new RealmModelReservoir();
        realmModelReservoirsLevel = new RealmList<>();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            reservoirId = bundle.getInt(ID_RESERVOIR, 0);
            realmModelReservoir = ReservoirsUtils.getReservoirsById(reservoirId);

            if (getReservoirLevelListByLevelMinDescending(reservoirId) != null) {
                realmModelReservoirsLevel = getReservoirLevelListByLevelMinDescending(reservoirId);
            }

            //presenter.loadDataReservoirReportWeekly(reservoirId, DateTimeUtils.formatWithPattern(new Date(), FORMAT_MYSQL_DATE_TIME));
        } else {
            realmModelReservoir = null;
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_reservoir_information, container, false);

        dialogBuildersUtils = new DialogBuildersUtils(getContext());
        presenter.loadDataReservoirReportDaily(reservoirId, DateTimeUtils.formatWithPattern(new Date(), FORMAT_MYSQL_DATE_TIME));

        waveView = rootView.findViewById(R.id.waveView);
        tvDateTime = rootView.findViewById(R.id.tv_date_time);
        tvStatus = rootView.findViewById(R.id.tv_status);
        tvHighestMinimumLevel = rootView.findViewById(R.id.tv_highest_minimum_level);

        rlLevel = rootView.findViewById(R.id.rl_level);
        ivMoreDetails = rootView.findViewById(R.id.iv_more_details);
        llMoreDetails = rootView.findViewById(R.id.ll_more_details);

        ivCloseDetails = rootView.findViewById(R.id.iv_close_details);
        llCloseDetails = rootView.findViewById(R.id.ll_close_details);

        rlStatistics = rootView.findViewById(R.id.rl_statistics);
        rlReservoirSensor = rootView.findViewById(R.id.rl_reservoir_sensor);
        rlAdditionalInfo = rootView.findViewById(R.id.rl_additional_info);
        rlGallery = rootView.findViewById(R.id.rl_gallery);
        rlTools = rootView.findViewById(R.id.rl_tools);

        ivStatistics = rootView.findViewById(R.id.bt_statistics);
        ivReservoirSensor = rootView.findViewById(R.id.bt_reservoir_sensor);
        ivAdditionalInfo = rootView.findViewById(R.id.bt_additional_info);
        ivGallery = rootView.findViewById(R.id.bt_gallery);
        ivTools = rootView.findViewById(R.id.bt_tools);

        elStatistics = rootView.findViewById(R.id.el_statistics);
        elReservoirSensor = rootView.findViewById(R.id.el_reservoir_sensor);
        elAdditionalInfo = rootView.findViewById(R.id.el_additional_info);
        elGallery = rootView.findViewById(R.id.el_gallery);
        elTools = rootView.findViewById(R.id.el_tools);

        btDay = rootView.findViewById(R.id.bt_day);
        btWeek = rootView.findViewById(R.id.bt_week);
        btMonth = rootView.findViewById(R.id.bt_month);
        btYear = rootView.findViewById(R.id.bt_year);

        tvDate = rootView.findViewById(R.id.tv_date);
        tvAverage = rootView.findViewById(R.id.tv_average);
        tvDate2 = rootView.findViewById(R.id.tv_date_2);

        rvListSensor = rootView.findViewById(R.id.rv_list_sensor);
        llHeaderListSensor = rootView.findViewById(R.id.ll_header_list_sensor);
        llEmptySensor = rootView.findViewById(R.id.empty_sensor);

        tvDepth = rootView.findViewById(R.id.tv_depth);
        tvVolume = rootView.findViewById(R.id.tv_volume);
        tvAreaName = rootView.findViewById(R.id.tv_area_name);
        tvAddress = rootView.findViewById(R.id.tv_address);
        tvDescription = rootView.findViewById(R.id.tv_description);

        btViewMap = rootView.findViewById(R.id.bt_view_map);
        elMap = rootView.findViewById(R.id.el_map);
        btCloseMap = rootView.findViewById(R.id.bt_close_map);
        btMapLayers = rootView.findViewById(R.id.bt_map_layers);
        btMapViewAll = rootView.findViewById(R.id.bt_map_view_all);
        btMapDirectionMode = rootView.findViewById(R.id.bt_map_direction_mode);
        btMapNavigation = rootView.findViewById(R.id.bt_map_navigation);

        rvListPhoto = rootView.findViewById(R.id.rv_list_photo);
        llEmptyPhotos = rootView.findViewById(R.id.empty_photo);

        mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        initTogglePeriodType();
        initDateDialog();

        chart = rootView.findViewById(R.id.chart_statistics);

        for (int i = 0; i < 5; i++) {
            expandState.append(i, false);
        }

        tvDateTime.setText(DateTimeUtils.formatWithPattern(new Date(), FORMAT_WITH_DAY_FULL_DATE_TIME));

        tvStatus.setBackground(ContextCompat.getDrawable(getContext(),
                ReservoirsLevelUtils.getReservoirLevelColorResId(realmModelReservoir.getLastStatusAverage())));
        if (!realmModelReservoirsLevel.isEmpty()) {
            if (!ReservoirsLevelUtils.getReservoirLevelStatusName(reservoirId, realmModelReservoir.getLastLevelAverage()).isEmpty()) {
                tvStatus.setText(ReservoirsLevelUtils.getReservoirLevelStatusName(reservoirId, realmModelReservoir.getLastLevelAverage()));
            } else {
                tvStatus.setText(getString(R.string.not_available));
            }
            tvHighestMinimumLevel.setText(
                    "Max. Height: " + String.format("%.1f", realmModelReservoirsLevel.get(0).getLevelMin()) + " cm");
        } else {
            tvStatus.setText(getString(R.string.not_available));
            tvHighestMinimumLevel.setText(
                    "Max. Height: " + getString(R.string.no_description));
        }

        waveView.setCenterTitle(String.format("%.1f", realmModelReservoir.getLastLevelAverage()) + " cm");
        waveView.setProgressValue((int) getReservoirLevelPercentageLevel(reservoirId, realmModelReservoir.getLastLevelAverage()));

        rope = YoYo.with(Techniques.Bounce)
                .duration(1500)
                .repeat(-1)
                .playOn(ivMoreDetails);

        Activity activity = getActivity();
        if (activity instanceof DetailReservoirActivity) {
            ((DetailReservoirActivity) getActivity()).setOnHomeLocationReceivedListener(this);
        }

        rlStatistics.setOnClickListener(onClickExpand(ivStatistics, 0, elStatistics));
        rlReservoirSensor.setOnClickListener(onClickExpand(ivReservoirSensor, 1, elReservoirSensor));
        rlAdditionalInfo.setOnClickListener(onClickExpand(ivAdditionalInfo, 2, elAdditionalInfo));
        rlGallery.setOnClickListener(onClickExpand(ivGallery, 3, elGallery));
        rlTools.setOnClickListener(onClickExpand(ivTools, 4, elTools));

        llMoreDetails.setOnClickListener(onClickDetails());
        llCloseDetails.setOnClickListener(onClickDetails());

        initRecyclerViewSensors();
        initAdditionalInfo();
        initRecyclerViewPhotos();

        return rootView;
    }

    private void initTogglePeriodType() {
        Button buttons[] = {btDay, btWeek, btMonth, btYear};
        togglePeriodType =
                new TogglePeriodType(getContext(), buttons);
        togglePeriodType.setToggleListener(this);
    }

    private void initDateDialog() {
        dateDialog = new DateDialog(getContext(), DateTimeFormat.FORMAT_MYSQL_DATE_TIME);
    }

    private void initRecyclerViewSensors() {
        if (realmModelReservoir.getRealmModelReservoirSensors().isEmpty()) {
            llEmptySensor.setVisibility(View.VISIBLE);
            llHeaderListSensor.setVisibility(View.GONE);
            rvListSensor.setVisibility(View.GONE);
        } else {
            llEmptySensor.setVisibility(View.GONE);
            llHeaderListSensor.setVisibility(View.VISIBLE);
            rvListSensor.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvListSensor.setLayoutManager(mLayoutManager);
        SensorsAdapter sensorsAdapter =
                new SensorsAdapter(realmModelReservoir.getRealmModelReservoirSensors(), getContext());
        rvListSensor.setAdapter(sensorsAdapter);
        sensorsAdapter.notifyDataSetChanged();
    }

    private void initAdditionalInfo() {
        tvDepth.setText(Html.fromHtml("<u>" + String.format("%.1f", realmModelReservoir.getDepth()) + " cm</u>"));
        tvVolume.setText(Html.fromHtml("<u>" + String.format("%.1f", realmModelReservoir.getVolume()) + " liter</u>"));
        tvAreaName.setText(realmModelReservoir.getAreaName());
        tvAddress.setText(realmModelReservoir.getAddress());
        tvDescription.setText(realmModelReservoir.getDescription());

        tvDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuildersUtils.informationDialog(realmModelReservoir.getDescription());

            }
        });

        initViewMap();

    }

    private void initViewMap() {
        btViewMap.setOnClickListener(onClickExpandMap(elMap));
        btCloseMap.setOnClickListener(onClickExpandMap(elMap));

        sheetBehavior = BottomSheetMarkerInfo.initViewBottomSheetMarkerInfo(
                getContext(), rootView, realmModelReservoir);

        googleMapsLocationUtils = new GoogleMapsLocationUtils(getContext(), mapFragment,
                KEY_PREF_MAP_TYPE_DETAIL_RESERVOIR, KEY_PREF_MAP_DETAIL_TRAFFIC_DETAIL_RESERVOIR);
        googleMapsLocationUtils.setGoogleMapsLocationListener(new GoogleMapsLocationUtils.GoogleMapsLocationListener() {
            @Override
            public void setupMap(final GoogleMap googleMap) {
                setUpMap(googleMap, sheetBehavior, CameraZoomMode.VIEW_ALL_MARKER);

                btMapViewAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setUpMap(googleMap, sheetBehavior, CameraZoomMode.VIEW_ALL_MARKER);
                    }
                });

                btMapNavigation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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


    }

    private void initRecyclerViewPhotos() {
        new LinearSnapHelper().attachToRecyclerView(rvListPhoto);

        if (realmModelReservoir.getRealmModelReservoirPhotos().isEmpty()) {
            llEmptyPhotos.setVisibility(View.VISIBLE);
            rvListPhoto.setVisibility(View.GONE);
        } else {
            llEmptyPhotos.setVisibility(View.GONE);
            rvListPhoto.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvListPhoto.setLayoutManager(mLayoutManager);
        PhotoReservoirAdapter photoReservoirAdapter =
                new PhotoReservoirAdapter(realmModelReservoir, getContext());
        rvListPhoto.setAdapter(photoReservoirAdapter);
        photoReservoirAdapter.notifyDataSetChanged();
    }

    public View.OnClickListener onClickExpand(
            final ImageView btExpand,
            final int itemPosition,
            final ExpandableRelativeLayout expandableRelativeLayout) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableRelativeLayout.setListener(new ExpandableLayoutListenerAdapter() {
                    @Override
                    public void onPreOpen() {
                        super.onPreOpen();
                        createRotateAnimator(btExpand, 0f, 180f).start();
                        expandState.put(itemPosition, true);
                    }

                    @Override
                    public void onPreClose() {
                        super.onPreClose();
                        createRotateAnimator(btExpand, 180f, 0f).start();
                        expandState.put(itemPosition, false);
                    }
                });

                expandableRelativeLayout.toggle();
            }
        };
    }

    public View.OnClickListener onClickExpandMap(
            final RelativeLayout relativeLayout) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (relativeLayout.getVisibility() == View.VISIBLE) {
                    relativeLayout.setVisibility(View.GONE);
                } else {
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            }
        };
    }

    public View.OnClickListener onClickDetails() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elDetails = rootView.findViewById(R.id.el_details);
                elDetails.toggle();

                if (elDetails.isExpanded()) {
                    if (rope != null) {
                        rope.stop(true);
                    }
                    rope = YoYo.with(Techniques.ZoomOut)
                            .duration(700)
                            .repeat(1)
                            .playOn(llCloseDetails);

                    if (rope != null) {
                        rope.stop(true);
                    }
                    rlLevel.setVisibility(View.VISIBLE);
                    rope = YoYo.with(Techniques.Bounce)
                            .duration(1500)
                            .repeat(-1)
                            .playOn(ivMoreDetails);
                } else {
                    if (rope != null) {
                        rope.stop(true);
                    }
                    rope = YoYo.with(Techniques.ZoomOut)
                            .duration(700)
                            .repeat(1)
                            .playOn(llMoreDetails);
                    rlLevel.setVisibility(View.GONE);

                    if (rope != null) {
                        rope.stop(true);
                    }
                    rope = YoYo.with(Techniques.Bounce)
                            .duration(1500)
                            .repeat(-1)
                            .playOn(ivCloseDetails);
                }
            }
        };
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


    @Override
    public void showLoadingReservoirReport() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    dialogBuildersUtils.setProgressDialog("Load Data Report..");
                    dialogBuildersUtils.progressDialog().show();
                } catch (Exception e) {
                    Log.d("ProgressReservoirInform", e.getMessage());
                }
            }
        });
    }

    @Override
    public void hideLoadingReservoirReport() {
        dialogBuildersUtils.progressDialog().dismiss();
    }

    @Override
    public void getResponseReservoirReport(final ModelResponseGetReservoirReport modelResponseGetReservoirReport, final String periodType) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                ChartTypeSimpleLineAdapter chartTypeSimpleLineAdapter =
                        new ChartTypeSimpleLineAdapter(getContext(), chart,
                                realmModelReservoirsLevel, modelResponseGetReservoirReport, periodType);
                periodTypeBefore = periodType;

                tvAverage.setText(" " + String.format("%.1f", modelResponseGetReservoirReport.getResult().get(0).getLevelAverage()) + " cm");

                switch (periodType) {
                    case DAILY: {
                        tvDate.setText(DateTimeUtils.formatWithPattern(
                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(0).getTimestamp(),
                                DateTimeFormat.FORMAT_DAY_SHORT_MONTH_YEAR));
                        tvDate2.setText(DateTimeUtils.formatWithPattern(
                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(0).getTimestamp(),
                                DateTimeFormat.FORMAT_DAY_SHORT_MONTH_YEAR));
                    }
                    break;
                    case WEEKLY: {
                        tvDate.setText(DateTimeUtils.formatWithPattern(
                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(0).getTimestamp(),
                                DateTimeFormat.FORMAT_DAY_SHORT_MONTH_YEAR) + " - " +
                                DateTimeUtils.formatWithPattern(
                                        modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(
                                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().size() - 1
                                        ).getTimestamp(),
                                        DateTimeFormat.FORMAT_DAY_SHORT_MONTH_YEAR)
                        );
                        tvDate2.setText(DateTimeUtils.formatWithPattern(
                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(0).getTimestamp(),
                                DateTimeFormat.FORMAT_DAY_SHORT_MONTH_YEAR) + " - " +
                                DateTimeUtils.formatWithPattern(
                                        modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(
                                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().size() - 1
                                        ).getTimestamp(),
                                        DateTimeFormat.FORMAT_DAY_SHORT_MONTH_YEAR)
                        );
                    }
                    break;
                    case MONTHLY: {
                        tvDate.setText(DateTimeUtils.formatWithPattern(
                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(0).getTimestamp(),
                                DateTimeFormat.FORMAT_MONTH_YEAR));
                        tvDate2.setText(DateTimeUtils.formatWithPattern(
                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(0).getTimestamp(),
                                DateTimeFormat.FORMAT_MONTH_YEAR));
                    }
                    break;
                    case YEARLY: {
                        tvDate.setText(DateTimeUtils.formatWithPattern(
                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(0).getTimestamp(),
                                DateTimeFormat.FORMAT_YEAR));
                        tvDate2.setText(DateTimeUtils.formatWithPattern(
                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(0).getTimestamp(),
                                DateTimeFormat.FORMAT_YEAR));
                    }
                    break;
                }
            }
        });
    }

    @Override
    public void getResponseReservoirReportFailed(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    ModelResponseGetReservoirReportFailed modelResponseGetReservoirReportFailed =
                            new Gson().fromJson(message, ModelResponseGetReservoirReportFailed.class);
                    dialogBuildersUtils.errorDialog(
                            modelResponseGetReservoirReportFailed.getError().getMessage());
                } catch (Exception e) {
                    dialogBuildersUtils.errorDialog(getErrorMessage(getContext(), message));
                    Log.d("ResponseErrorBodyLogin", message);
                }
            }
        });
    }

    @Override
    public void onToggleSwitched(final String periodType) {
        if (periodType.equals(DAILY)) {
            dateDialog.showDateDialogFullDay();
        } else if (periodType.equals(WEEKLY)) {
            dateDialog.showDateDialogFullDay();
        } else if (periodType.equals(MONTHLY)) {
            dateDialog.showDateDialogMonthOnly();
        } else if (periodType.equals(YEARLY)) {
            dateDialog.showDateDialogYearOnly();
        }

        dateDialog.setDateDialogListener(new DateDialog.DateDialogListener() {
            @Override
            public void onDateSelected(String dateFormatted) {
                switch (periodType) {
                    case DAILY: {
                        presenter.loadDataReservoirReportDaily(reservoirId, dateFormatted);
                    }
                    break;
                    case WEEKLY: {
                        presenter.loadDataReservoirReportWeekly(reservoirId, dateFormatted);
                    }
                    break;
                    case MONTHLY: {
                        presenter.loadDataReservoirReportMonthly(reservoirId, dateFormatted);
                    }
                    break;
                    case YEARLY: {
                        presenter.loadDataReservoirReportYearly(reservoirId, dateFormatted);
                    }
                    break;
                }
            }

            @Override
            public void onCancelled() {
                togglePeriodType.setSelectedToggle(periodTypeBefore);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("MyMap", "onResume");
        googleMapsLocationUtils.onResume();
    }

    private GoogleMap mMap;
    private Polyline currentPolyline;

    private void setUpMap(final GoogleMap mMap, final BottomSheetBehavior sheetBehavior, int cameraZoomMode) {

        mMap.clear();

        this.mMap = mMap;

        final LatLng latLongReservoir = new LatLng(realmModelReservoir.getLatitude(), realmModelReservoir.getLongitude());

        final Marker markerReservoir = mMap.addMarker(new MarkerOptions()
                .position(latLongReservoir)
                .title(realmModelReservoir.getName())
                .snippet(realmModelReservoir.getAreaName())
                .icon(BitmapDescriptorFactory.
                        fromResource(ReservoirsLevelUtils.getReservoirLevelResourceMarker(getContext(),
                                realmModelReservoir.getLastStatusAverage()
                                )
                        )
                )
        );
        markerReservoir.setTag(realmModelReservoir.getName());

        this.mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        btMapDirectionMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (directionMode) {
                    case DRIVING_MODE: {
                        directionMode = "";
                        btMapDirectionMode.setBackgroundResource(R.drawable.ic_map_direction_walking_off);
                    }
                    break;
                    case WALKING_MODE: {
                        directionMode = DRIVING_MODE;
                        btMapDirectionMode.setBackgroundResource(R.drawable.ic_map_direction_driving);
                    }
                    break;
                    case "": {
                        directionMode = WALKING_MODE;
                        btMapDirectionMode.setBackgroundResource(R.drawable.ic_map_direction_walking);
                    }
                    break;
                }

                if(directionMode.equals("")){
                    if (currentPolyline != null)
                        currentPolyline.remove();
                } else {
                    new FetchURL(getContext(), FragmentInformationReservoir.this).execute(
                            googleMapsLocationUtils.getUrlDirection(
                                    getLatLngLocationPreferences(getContext()), latLongReservoir, directionMode, DIRECTION_KEY), directionMode);
                }

            }
        });

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

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                String markerTag = (String)(marker.getTag());

                if(markerTag != null) {
                    if (markerTag.equals(realmModelReservoir.getName())) {
                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        googleMapsLocationUtils.viewMarkerByRadius(15000, marker.getPosition());
                    }
                }

                return false;
            }
        });

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(getLatLngLocationPreferences(getContext()));
        builder.include(markerReservoir.getPosition());

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
    public void onStart() {
        super.onStart();
        // connect ke Google API Client ketika start
        googleMapsLocationUtils.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        // disconnect ke Google API Client ketika activity stopped
        googleMapsLocationUtils.onStop();
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);

        distance = (String) values[1];
        duration = (String) values[2];

    }
}