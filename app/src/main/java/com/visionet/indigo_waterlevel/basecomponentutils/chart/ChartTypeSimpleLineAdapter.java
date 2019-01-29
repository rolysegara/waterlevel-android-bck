package com.visionet.indigo_waterlevel.basecomponentutils.chart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.visionet.indigo_waterlevel.R;
import com.visionet.indigo_waterlevel.baserealm.reservoirs_level.model.RealmModelReservoirsLevel;
import com.visionet.indigo_waterlevel.modulmap.utils.ReservoirsLevelUtils;
import com.visionet.indigo_waterlevel.modulreservoirdetail.model.ModelResponseGetReservoirReport;

import java.util.ArrayList;

import io.realm.RealmList;

import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.DAILY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.MONTHLY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.WEEKLY;
import static com.visionet.indigo_waterlevel.basecomponentutils.chart.constant.ConstantValue.YEARLY;

public class ChartTypeSimpleLineAdapter implements OnChartValueSelectedListener {
    private Context context;
    private LineChart chart;

    private RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels;
    private ModelResponseGetReservoirReport modelResponseGetReservoirReport;

    private XAxis xAxis;
    private YAxis yAxis;

    private Typeface tfRegular;
    private Typeface tfLight;

    private int fromIndex = 0;
    private int toIndex = 0;
    private boolean hasRange;

    public ChartTypeSimpleLineAdapter(
            Context context, LineChart chart,
            RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels,
            ModelResponseGetReservoirReport modelResponseGetReservoirReport, String periodType) {

        this.hasRange = false;
        onCreateChart(context, chart, realmModelReservoirsLevels, modelResponseGetReservoirReport, periodType);
    }

    public ChartTypeSimpleLineAdapter(
            Context context, LineChart chart,
            RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels,
            ModelResponseGetReservoirReport modelResponseGetReservoirReport, String periodType, int fromIndex, int toIndex) {

        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.hasRange = true;
        onCreateChart(context, chart, realmModelReservoirsLevels, modelResponseGetReservoirReport, periodType);

    }

    private void onCreateChart(
            Context context, LineChart chart,
            RealmList<RealmModelReservoirsLevel> realmModelReservoirsLevels,
            ModelResponseGetReservoirReport modelResponseGetReservoirReport, String periodType){
        this.context = context;
        this.chart = chart;
        this.modelResponseGetReservoirReport = modelResponseGetReservoirReport;

        this.realmModelReservoirsLevels = realmModelReservoirsLevels;

        tfRegular = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Regular.ttf");
        tfLight = Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Light.ttf");

        initChartStyle();
        initXAxis();
        initYAxis();

        setLimitLines();

        LinearLayout.LayoutParams layoutParams;
        switch (periodType) {
            case DAILY: {
                setXAxisLimitDaily();

                layoutParams = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen.report_daily_width), ViewGroup.LayoutParams.MATCH_PARENT);
                chart.setLayoutParams(layoutParams);
            }
            break;
            case WEEKLY: {
                layoutParams = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen.report_weekly_width), ViewGroup.LayoutParams.MATCH_PARENT);
                chart.setLayoutParams(layoutParams);
            }
            break;
            case MONTHLY: {
                layoutParams = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen.report_monthly_width), ViewGroup.LayoutParams.MATCH_PARENT);
                chart.setLayoutParams(layoutParams);
            }
            break;
            case YEARLY: {
                layoutParams = new LinearLayout.LayoutParams((int) context.getResources().getDimension(R.dimen.report_yearly_width), ViewGroup.LayoutParams.MATCH_PARENT);
                chart.setLayoutParams(layoutParams);
            }
            break;
        }

        setData();
        chart.animateXY(2000, 2000);

        initLegend();
    }

    private void initChartStyle() {
        {   // // Chart Style // //

            // background color
            chart.setBackgroundColor(Color.WHITE);

            // disable description text
            chart.getDescription().setEnabled(false);

            // enable touch gestures
            chart.setTouchEnabled(true);

            // set listeners
            chart.setOnChartValueSelectedListener(this);
            chart.setDrawGridBackground(false);

            // create marker to display box when values are selected
            MyMarkerView mv = new MyMarkerView(context, R.layout.custom_marker_view);

            // Set the marker to the chart
            mv.setChartView(chart);
            chart.setMarker(mv);

            // enable scaling and dragging
            chart.setDragEnabled(true);
            chart.setScaleEnabled(false);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chart.setPinchZoom(true);

        }
    }

    private void initXAxis() {
        {   // // X-Axis Style // //
            xAxis = chart.getXAxis();

            // vertical grid lines
            //xAxis.enableGridDashedLine(10f, 10f, 0f);


            xAxis.setGranularityEnabled(true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);

            final ArrayList<String> xLabel = new ArrayList<>();

            if(hasRange){
                xAxis.setLabelCount(Math.abs((toIndex - fromIndex) + 1));
                if(toIndex >= fromIndex) {
                    for (int i = fromIndex; i <= toIndex; i++) {
                        ModelResponseGetReservoirReport.ResultBean.ReservoirDataBean reservoirDataBean =
                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(i);
                        xLabel.add(reservoirDataBean.getLabel());
                    }
                }else{
                    for (int i = toIndex; i <= fromIndex; i++) {
                        ModelResponseGetReservoirReport.ResultBean.ReservoirDataBean reservoirDataBean =
                                modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(i);
                        xLabel.add(reservoirDataBean.getLabel());
                    }
                }
            }else {
                xAxis.setLabelCount(modelResponseGetReservoirReport.getResult().get(0).getReservoirData().size());
                for (ModelResponseGetReservoirReport.ResultBean.ReservoirDataBean reservoirDataBean : modelResponseGetReservoirReport.getResult().get(0).getReservoirData()) {
                    xLabel.add(reservoirDataBean.getLabel());
                }
            }

            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    if (value >= 0) {
                        if (xLabel.size() > (int) value) {
                            return xLabel.get((int) value);
                        } else return "";
                    } else {
                        return "";
                    }
                }
            });

        }
    }

    private void setXAxisLimitDaily() {
        LimitLine llXAxis = new LimitLine(12f, "");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);
        llXAxis.setLineColor(Color.parseColor("#999999"));
        llXAxis.setTypeface(tfRegular);

        xAxis.setDrawLimitLinesBehindData(true);
        xAxis.addLimitLine(llXAxis);
    }

    private void initYAxis() {
        {   // // Y-Axis Style // //
            yAxis = chart.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chart.getAxisRight().setEnabled(false);
            yAxis.setDrawGridLines(false);

            // horizontal grid lines
            //yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMinimum(0f);
            yAxis.setAxisMaximum(100f);
            try {
                yAxis.setAxisMaximum((float) realmModelReservoirsLevels.get(0).getLevelMin() + 100f);
            } catch (Exception e) {

            }
        }
    }

    private void setLimitLines() {
        {   // // Create Limit Lines // //

            for (int i = 0; i < realmModelReservoirsLevels.size(); i++) {
                if (i < realmModelReservoirsLevels.size() - 1) {
                    LimitLine llYAxis = new LimitLine(
                            (float) realmModelReservoirsLevels.get(i).getLevelMin(),
                            realmModelReservoirsLevels.get(i).getName());
                    llYAxis.setLineWidth(4f);
                    llYAxis.enableDashedLine(10f, 10f, 0f);
                    llYAxis.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
                    llYAxis.setLineColor(ReservoirsLevelUtils.getReservoirLevelColor(realmModelReservoirsLevels.get(i).getName()));
                    llYAxis.setTextSize(10f);
                    llYAxis.setTypeface(tfRegular);

                    yAxis.setDrawLimitLinesBehindData(true);
                    yAxis.addLimitLine(llYAxis);
                }
            }
        }
    }

    private void initLegend() {
        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);
        chart.setExtraBottomOffset(10);

    }

    private void setData() {

        ArrayList<Entry> values = new ArrayList<>();

        float i = 0;
        if(hasRange){
            if(toIndex >= fromIndex) {
                for (int x = fromIndex; x <= toIndex; x++) {
                    ModelResponseGetReservoirReport.ResultBean.ReservoirDataBean reservoirDataBean =
                            modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(x);
                    values.add(new Entry(i++, (float) reservoirDataBean.getDataLevel(), context.getResources().getDrawable(R.drawable.star)));
                }
                Log.d("FromIndex", "Tess");
            }else{
                for (int x = toIndex; x <= fromIndex; x++) {
                    ModelResponseGetReservoirReport.ResultBean.ReservoirDataBean reservoirDataBean =
                            modelResponseGetReservoirReport.getResult().get(0).getReservoirData().get(x);
                    values.add(new Entry(i++, (float) reservoirDataBean.getDataLevel(), context.getResources().getDrawable(R.drawable.star)));
                }
                Log.d("FromIndex", "Tess1");
            }
        }else {
            for (ModelResponseGetReservoirReport.ResultBean.ReservoirDataBean reservoirDataBean : modelResponseGetReservoirReport.getResult().get(0).getReservoirData()) {
                values.add(new Entry(i++, (float) reservoirDataBean.getDataLevel(), context.getResources().getDrawable(R.drawable.star)));
            }
            Log.d("FromIndex", "Tess2");
        }

        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "Ketinggian Air");

            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.parseColor("#008DBB"));
            set1.setCircleColor(Color.parseColor("#DB0025"));

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.fade_light_blue);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chart.setData(data);

        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
