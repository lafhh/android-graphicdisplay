package com.js.graphicdisplay.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.*;
import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.base.BaseActivity;
import com.js.graphicdisplay.adapter.FundsTableAdapter;
import com.js.graphicdisplay.adapter.SpinnerAdapter;
import com.js.graphicdisplay.api.Infermation;
import com.js.graphicdisplay.data.*;
import com.js.graphicdisplay.jsonutil.GroupJsonParser;
import com.js.graphicdisplay.mpchart.customization.BarChartCustomization;
import com.js.graphicdisplay.mpchart.customization.LineChartCustomization;
import com.js.graphicdisplay.net.HttpManager;
import com.js.graphicdisplay.net.NetUtil;
import com.js.graphicdisplay.net.Request;
import com.js.graphicdisplay.util.ColorTemplate;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/17.
 */

public class GraphicActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "GraphicActivity";
    private static final int BAR_CHART_MAX_VALUE_COUNT = 60;

    private Spinner spinnerGroup;
//    private Spinner spinnerCompany;
//    private Spinner spinnerDate;

    private BarChart mBarChart;
    private LineChart mLineChart;

    private TableFixHeaders table;

    private SpinnerAdapter<Group> groupAdapter;
    private SpinnerAdapter<Company> companyAdapter;
    private SpinnerAdapter<SpinnerDate> dateAdapter;
    private FundsTableAdapter tableAdapter;

    private ArrayList<Group> chartData = new ArrayList<>();
    private ArrayList<Group> tableData = new ArrayList<>();
//    private HashMap<String, ArrayList<Company>> companies = new HashMap<>();

    private int totalRows;
    private int page;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

//        File file = Environment.getExternalStorageDirectory();
//        String path = file.getAbsolutePath() + "/Download/newchart.txt";
//        String json = FileUtil.readToString(path);
//        groupChartFromJson(json);

        spinnerGroup = (Spinner) findViewById(R.id.spinner_group);
        mLineChart = (LineChart) findViewById(R.id.linechart);
        mBarChart = (BarChart) findViewById(R.id.barchart);
        table = (TableFixHeaders) findViewById(R.id.table_funds);

        //spinner //interface
        groupAdapter = new SpinnerAdapter<>(this, chartData);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(groupAdapter);
        spinnerGroup.setOnItemSelectedListener(this);

//        ArrayList<Company> companies = groups.get(0).getChild();
//        spinnerCompany = (Spinner) findViewById(R.id.spinner_company);
//        companyAdapter = new SpinnerAdapter<>(this, companies);
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCompany.setAdapter(companyAdapter);
//        spinnerCompany.setOnItemSelectedListener(this);

        //line chart
        LineChartCustomization.customLineChart(mLineChart, mTfLight);
//        mLineChart.setOnChartGestureListener(new OnChartGestureImpl());

        //bar chart
        BarChartCustomization.customBarChart(mBarChart, mTfLight);


        ArrayList<NameValuePair<String, String>> list = new ArrayList<>();
        list.add(new NameValuePair<>(NetUtil.KEY_LIMIT, String.valueOf(10)));
        list.add(new NameValuePair<>(NetUtil.KEY_OFFSET, String.valueOf(0)));
        list.add(new NameValuePair<>(NetUtil.KEY_ORDER, "asc"));
        list.add(new NameValuePair<>(NetUtil.KEY_SORT, NetUtil.GROUPNAME));
        HttpManager.doPost(
                NetUtil.URL_FUNDSTURNEDOVER_GROUP_TABLE,
                list,
                Request.ContentType.KVP,
                new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        sendEmptyMessage(MESSAGE_ERROR);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String body = response.body().string();
                        if (response.isSuccessful()) {
                            totalRows = GroupJsonParser.tableFromJson(body, tableData);
                            sendMessage(MESSAGE_TABLE, body);
                            //再发网络请求

                        } else {
                            sendMessage(MESSAGE_FAILED, body);
                            throw new IOException("Unexpected code " + response);
                        }
                    }
                });

        /*** test ***/
//        Log.d(TAG, json);
        setChartData();
        /*** test ***/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Infermation o = (Infermation) parent.getItemAtPosition(position);

        if (o instanceof Group) {
//            //month
//            ArrayList<SpinnerDate> sdate = dateAdapter.getData();
//            SpinnerDate.replace(((Group) o).getMonths(), sdate);
//            dateAdapter.notifyDataSetChanged();
            //month selection

            //company
            ArrayList<Company> c = companies.get(((Group) o).getGroupCode());
            companyAdapter.setData(c);
            companyAdapter.notifyDataSetChanged();
            //company selection
//            spinnerCompany.setSelection(0);

            //通知table,chart

        } else if (o instanceof Company) {
//            getChildFromCompany((Company) o);
//            ArrayList<Project> p = ((Company) o).getChild();
//            projectAdapter.setData(p);
//            projectAdapter.notifyDataSetChanged();
//            spinnerProject.setSelection(0);
        } else if (o instanceof SpinnerDate) {

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    protected void handleUIMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_TABLE:
                setTableData();
                break;

            case MESSAGE_ERROR:
                break;

            case MESSAGE_FAILED:
                break;
        }
    }

    private void setData() {
        if (groupAdapter == null) {
            groupAdapter = new SpinnerAdapter<>(this, chartData);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerGroup.setAdapter(groupAdapter);
            spinnerGroup.setOnItemSelectedListener(this);
        }


    }

    private void setTableData() {
        if (tableAdapter == null) {
            tableAdapter = new FundsTableAdapter(this, tableData);
            table.setAdapter(tableAdapter);
        } else {

        }
    }

    //group: a group of bars
    private void setChartData(ArrayList<Group> chartData) {
        //indicatrixPerMonth & completionPerMonth, two bar per Group(集团)
        int barCountPerGroup = chartData.size();

        float groupSpace = 0.08f;
        float perBarSpaceWidth = (1.00f - groupSpace) / barCountPerGroup;
        float barSpace = 0.00f;
        float barWidth = perBarSpaceWidth - barSpace;
//        float groupSpace = 0.08f;
//        float barSpace = 0.00f; // x4 DataSet
//        float barWidth = 0.23f; // x4 DataSet
        BarData barData = new BarData();
        LineData lineData = new LineData();

        int startMonth = 201701;
        int maxSize = 0;

        for (int i = 0; i < chartData.size(); i++) {
            Group group = chartData.get(i);
            String name = group.getName();
            group.setKeyColor((i + 1) % ColorTemplate.TEMPLETE_COLOR.length);
            FundsData fundsData = group.getFundsData();
            ArrayList<Data4FundsPerMonth> datas = fundsData.getFundsPerMonth();

            ArrayList<BarEntry> barEntries = new ArrayList<>();
            ArrayList<Entry> lineEntries = new ArrayList<>();

            ArrayList<String> months = fundsData.getMonths();
            if (maxSize < months.size()) maxSize = months.size();
            for (int j = 0; j < months.size(); j++) {
                int month = Integer.parseInt(months.get(j));

                //stack bar entry.y = val1 + val2,
                // y 每月指标vs每月完成量的最大值
                float val1 = datas.get(j).getCompletionPerMonth().floatValue();
                float val2 = datas.get(j).getIndicatrixPerMonth().floatValue();
                if (val1 < val2) {
                    val2 = val2 - val1;
                    barEntries.add(new BarEntry(month, new float[]{val1, val2}));

                } else if (val1 == val2) {
                    val2 = 0;
                    barEntries.add(new BarEntry(month, new float[]{val1, val2}));
                } else {
//                    float tmp = val1;
//                    val1 = val2;
//                    val2 = tmp - val2;
//                    barEntries.add(new BarEntry(month, tmp));
                }

                //line entry
                lineEntries.add(new Entry(month,
                        datas.get(j).getRateCompletedPerMonth().floatValue()));
            }

            BarDataSet barSet = new BarDataSet(barEntries, name);
//            set.setBarBorderColor(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()]);
//            set.setBarBorderWidth(0.5f);
            barSet.setColors(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()], ColorTemplate.TEMPLETE_COLOR[0]);
            barSet.setStackLabels(new String[]{"已完成数额", "未完成数额",});
            barData.addDataSet(barSet);

            LineDataSet lineSet = new LineDataSet(lineEntries, name);
            lineSet.setLineWidth(2.5f);
            lineSet.setCircleRadius(4f);
            lineSet.setColor(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()]);
            lineSet.setCircleColor(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()]);
            lineData.addDataSet(lineSet);
        }

        mBarChart.setData(barData);

        barData.setBarWidth(barWidth);
        barData.setValueTypeface(mTfLight);
        barData.setDrawValues(false); //不显示y轴的值

//        mBarChart.setDrawGridBackground(true); //设置网格线的背景，好像不能按照分组设置不同颜色
//        mBarChart.setDrawValueAboveBar(false);

        float groupWidth = mBarChart.getBarData().getGroupWidth(groupSpace, barSpace); //groupwidth = 1
        mBarChart.getXAxis().setAxisMinimum(startMonth);
        mBarChart.getXAxis().setAxisMaximum(startMonth + groupWidth * maxSize);

        mBarChart.groupBars(startMonth, groupSpace, barSpace);
        mBarChart.setFitBars(true);
        mBarChart.animateXY(2500, 2500);
        mBarChart.invalidate();

        mLineChart.setData(lineData);
        int width = mLineChart.getMeasuredWidth();
//        Log.d(TAG, "linechart width======" + width);

        lineData.setValueTypeface(mTfLight);
//        mLineChart.getXAxis().setAxisMinimum(startMonth);
//        mLineChart.getXAxis().setAxisMaximum(startMonth + mBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * months.size() - 1);
//        mLineChart.getXAxis().setSpaceMax(22f);

        mLineChart.animateX(300);
        mLineChart.invalidate();
    }


}
