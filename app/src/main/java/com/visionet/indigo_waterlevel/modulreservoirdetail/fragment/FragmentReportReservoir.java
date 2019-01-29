package com.visionet.indigo_waterlevel.modulreservoirdetail.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.thunder413.datetimeutils.DateTimeUtils;
import com.google.gson.Gson;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baseapp.BaseFragment;
import com.visionet.indigo_waterlevel.baseapp.MvpFragment;
import com.visionet.indigo_waterlevel.basecomponentutils.DialogBuildersUtils;
import com.visionet.indigo_waterlevel.basecomponentutils.chart.ChartTypeSimpleLineAdapter;
import com.visionet.indigo_waterlevel.basecomponentutils.multistatetoggle.ToggleHalfDayType;
import com.visionet.indigo_waterlevel.basecomponentutils.multistatetoggle.TogglePeriodType;
import com.visionet.indigo_waterlevel.basehelper.DateTimeFormat;
import com.visionet.indigo_waterlevel.baserealm.reservoir.model.RealmModelReservoir;
import com.visionet.indigo_waterlevel.baserealm.reservoirs_level.model.RealmModelReservoirsLevel;
import com.visionet.indigo_waterlevel.baseutils.LocationUtils;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsUtils;
import com.visionet.indigo_waterlevel.modulreservoirdetail.adapter.StatusLevelAdapter;
import com.visionet.indigo_waterlevel.modulreservoirdetail.model.ModelResponseGetReservoirReport;
import com.visionet.indigo_waterlevel.modulreservoirdetail.model.ModelResponseGetReservoirReportFailed;
import com.visionet.indigo_waterlevel.modulreservoirdetail.presenter.ReservoirReportPresenter;
import com.visionet.indigo_waterlevel.modulreservoirdetail.view.ReservoirReportView;

import java.util.Date;

import io.realm.RealmList;

import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.DAILY;
import static com.visionet.indigo_waterlevel.basehelper.AppData.ID_RESERVOIR;
import static com.visionet.indigo_waterlevel.basehelper.DateTimeFormat.FORMAT_MYSQL_DATE_TIME;
import static com.visionet.indigo_waterlevel.baseutils.NetworkExceptionUtils.getErrorMessage;
import static com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils.getReservoirLevelListByLevelMinDescending;

public class FragmentReportReservoir extends MvpFragment<ReservoirReportPresenter> implements ReservoirReportView,
        ToggleHalfDayType.ToggleListener{

    Button btBeforeNoon;
    Button btAfterNoon;

    TextView tvAverage;
    TextView tvDate;
    TextView tvDate2;

    RecyclerView rvListLevel;
    LinearLayout emptyLevelStatus;

    View rootView;

    DialogBuildersUtils dialogBuildersUtils;
    ToggleHalfDayType toggleHalfDayType;

    private LineChart chart;

    int idReservoir;


    RealmModelReservoir realmModelReservoir;
    RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevel;

    public FragmentReportReservoir() {
        // Required empty public constructor
    }

    public static com.visionet.indigo_waterlevel.modulreservoir.fragment.FragmentWatchedReservoir newInstance(String param1, String param2) {
        com.visionet.indigo_waterlevel.modulreservoir.fragment.FragmentWatchedReservoir fragment = new com.visionet.indigo_waterlevel.modulreservoir.fragment.FragmentWatchedReservoir();
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

        realmModelReservoir = new RealmModelReservoir();
        realmModelReservoirsLevel = new RealmList<>();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            idReservoir = bundle.getInt(ID_RESERVOIR, 0);
            realmModelReservoir = ReservoirsUtils.getReservoirsById(idReservoir);

            if (getReservoirLevelListByLevelMinDescending(idReservoir) != null) {
                realmModelReservoirsLevel = getReservoirLevelListByLevelMinDescending(idReservoir);
            }

        } else {
            realmModelReservoir = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_reservoir_report, container, false);

        dialogBuildersUtils = new DialogBuildersUtils(getContext());
        presenter.loadDataReservoirReportDaily(idReservoir, DateTimeUtils.formatWithPattern(new Date(), FORMAT_MYSQL_DATE_TIME));

        chart = rootView.findViewById(R.id.chart_statistics);
        btBeforeNoon = rootView.findViewById(R.id.bt_before_noon);
        btAfterNoon = rootView.findViewById(R.id.bt_after_noon);
        tvAverage = rootView.findViewById(R.id.tv_average);
        tvDate = rootView.findViewById(R.id.tv_date);
        tvDate2 = rootView.findViewById(R.id.tv_date_2);
        rvListLevel = rootView.findViewById(R.id.rv_list_level);
        emptyLevelStatus = rootView.findViewById(R.id.empty_level_status);

        initToggleHalfDayType();
        initRecyclerViewSensors();

        return rootView;
    }

    private void initToggleHalfDayType(){
        Button buttons[] = {btBeforeNoon, btAfterNoon};
        toggleHalfDayType =
                new ToggleHalfDayType(getContext(), buttons);
        toggleHalfDayType.setToggleListener(this);
    }

    private void initRecyclerViewSensors(){
        if(realmModelReservoir.getRealmModelReservoirSensors().isEmpty()){
            emptyLevelStatus.setVisibility(View.VISIBLE);
            rvListLevel.setVisibility(View.GONE);
        }else{
            emptyLevelStatus.setVisibility(View.GONE);
            rvListLevel.setVisibility(View.VISIBLE);
        }
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvListLevel.setLayoutManager(mLayoutManager);
        StatusLevelAdapter statusLevelAdapter =
                new StatusLevelAdapter(realmModelReservoir.getRealmModelReservoirsLevels(), getContext());
        rvListLevel.setAdapter(statusLevelAdapter);
        statusLevelAdapter.notifyDataSetChanged();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            //tvKoordinat.setText(LocationUtils.getStringLocationPreferences(getContext())[0] +","+
            //        LocationUtils.getStringLocationPreferences(getContext())[1]);
            Log.d("VisibleFragment", "Visible..");
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("ResumeFragment", "Resume..");
    }

    @Override
    public void onToggleSwitched(String periodType) {
        presenter.loadDataReservoirReportDaily(idReservoir, DateTimeUtils.formatWithPattern(new Date(), FORMAT_MYSQL_DATE_TIME));
    }

    @Override
    public void showLoadingReservoirReport() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    //dialogBuildersUtils.setProgressDialog("Load Data Report..");
                    //dialogBuildersUtils.progressDialog().show();
                } catch (Exception e) {
                    Log.d("ProgressReservoirInform", e.getMessage());
                }
            }
        });
    }

    @Override
    public void hideLoadingReservoirReport() {
        //dialogBuildersUtils.progressDialog().dismiss();
    }

    @Override
    public void getResponseReservoirReport(final ModelResponseGetReservoirReport modelResponseGetReservoirReport, final String periodType) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {

                ChartTypeSimpleLineAdapter chartTypeSimpleLineAdapter;

                tvAverage.setText(" "+String.format("%.1f",modelResponseGetReservoirReport.getResult().get(0).getLevelAverage()) + " cm");

                tvDate.setText(getString(R.string.today) + ", "+DateTimeUtils.formatWithPattern(
                        modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(0).getTimestamp(),
                        DateTimeFormat.FORMAT_DAY_SHORT_MONTH_YEAR));
                tvDate2.setText(getString(R.string.last_updated) + " " + DateTimeUtils.formatWithPattern(
                        new Date(),
                        DateTimeFormat.FORMAT_WITH_FULL_DATE_TIME));

                switch (toggleHalfDayType.getPosition()){
                    case 0: {
                        chartTypeSimpleLineAdapter =
                                new ChartTypeSimpleLineAdapter(getContext(), chart,
                                        realmModelReservoirsLevel, modelResponseGetReservoirReport, periodType, 0, 11);
                    }
                    break;
                    case 1: {
                        chartTypeSimpleLineAdapter =
                                new ChartTypeSimpleLineAdapter(getContext(), chart,
                                        realmModelReservoirsLevel, modelResponseGetReservoirReport, periodType, 12, 23);
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
}