package com.js.graphicdisplay.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.base.BaseActivity;
import com.js.graphicdisplay.adapter.SpinnerAdapter;
import com.js.graphicdisplay.api.Infermation;
import com.js.graphicdisplay.data.ChartBean;
import com.js.graphicdisplay.data.Company;
import com.js.graphicdisplay.data.Group;
import com.js.graphicdisplay.data.NameValuePair;
import com.js.graphicdisplay.data.Project;
import com.js.graphicdisplay.mpchart.DayAxisValueFormatter;
import com.js.graphicdisplay.mpchart.MyAxisValueFormatter;
import com.js.graphicdisplay.net.HttpManager;
import com.js.graphicdisplay.net.NetUtil;
import com.js.graphicdisplay.net.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by js_gg on 2017/6/17.
 */

public class GraphicActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "GraphicActivity";
    private static final int BAR_CHART_MAX_VALUE_COUNT = 60;

    private Spinner spinnerGroup;
    private Spinner spinnerCompany;
    private Spinner spinnerProject;

    private BarChart barChart;

    private SpinnerAdapter<Group> groupAdapter;
    private SpinnerAdapter<Company> companyAdapter;
    private SpinnerAdapter<Project> projectAdapter;

    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<ChartBean> chartData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        //spinner
        initialSpinnerData();
        spinnerGroup = (Spinner) findViewById(R.id.spinner_group);
        groupAdapter = new SpinnerAdapter<>(this, groups);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(groupAdapter);
        spinnerGroup.setOnItemSelectedListener(this);

        ArrayList<Company> companies = groups.get(0).getChild();
        spinnerCompany = (Spinner) findViewById(R.id.spinner_company);
        companyAdapter = new SpinnerAdapter<>(this, companies);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCompany.setAdapter(companyAdapter);
        spinnerCompany.setOnItemSelectedListener(this);

        ArrayList<Project> projects = companies.get(0).getChild();
        spinnerProject = (Spinner) findViewById(R.id.spinner_project);
        projectAdapter = new SpinnerAdapter<>(this, projects);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProject.setAdapter(projectAdapter);
        spinnerProject.setOnItemSelectedListener(this);

        /******** chart start **********/
        barChart = (BarChart) findViewById(R.id.chart);
        barChart.setDrawBarShadow(false);
//        barChart.setDrawValueAboveBar(true);

        barChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        barChart.setMaxVisibleValueCount(BAR_CHART_MAX_VALUE_COUNT);

        // scaling can now only be done on x- and y-axis separately
        barChart.setPinchZoom(false);

        barChart.setDrawGridBackground(false);

        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(barChart);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawGridLines(false);

        //Set a minimum interval for the axis when zooming in.
        // The axis is not allowed to go below that limit.
        // This can be used to avoid label duplicating when zooming in.
        xAxis.setGranularity(1f); // only intervals of 1 day

        xAxis.setLabelCount(6);
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(6, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        leftAxis.setAxisMaximum(60f);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setTypeface(mTfLight);
        rightAxis.setLabelCount(6, false); //znn
        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
//        rightAxis.setAxisMaximum(60f);

        Legend l = barChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

//        initialChartData();
        int valueCount = chartData.size() - 1;
        int range = getMaxValue();
        setData();
        /******** chart end **********/

        ArrayList<NameValuePair<String, String>> list = new ArrayList<>();
//        list.add(new NameValuePair<>(NetUtil.POST_ORGID, "4"));
//        list.add(new NameValuePair<>(NetUtil.POST_DATE, "201701"));
        HttpManager.doPost(
                NetUtil.URL_FUNDSTURNEDOVER_ALL_CHART,
                list,
                Request.ContentType.KVP,
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "response code : " + response.code());
                            Log.d(TAG, "body = " + response.body().string());

                        } else {
                            throw new IOException("Unexpected code " + response);
                        }
                    }
                });
    }

    private void initialSpinnerData() {
        for (int i = 0; i < 5; i++) {
            Group group = new Group();
            group.setGroupName("g" + (i + 1));
            groups.add(group);
        }
        String group = groups.get(0).getName();
        ArrayList<Company> companies = new ArrayList<>();
        groups.get(0).setChild(companies);
        for (int i = 0; i < 10; i++) {
            Company company = new Company();
            company.setCompanyName(group + "c" + (i + 1));
            companies.add(company);
        }
        String company = companies.get(0).getName();
        ArrayList<Project> projects = new ArrayList<>();
        companies.get(0).setChild(projects);
        for (int i = 0; i < 10; i++) {
            Project project = new Project();
            project.setProjectName(company + "p" + (i + 1));
            projects.add(project);
        }
    }

    private void getTreeFromGroup(Group group) {
        ArrayList<Company> companies = new ArrayList<>();
        String name = group.getName();
        group.setChild(companies);
        for (int i = 0; i < 10; i++) {
            Company company = new Company();
            company.setCompanyName(name + "c" + (i + 1));
            companies.add(company);
        }
        ArrayList<Project> projects = new ArrayList<>();
        companies.get(0).setChild(projects);
        name = companies.get(0).getName();
        for (int i = 0; i < 10; i++) {
            Project project = new Project();
            project.setProjectName(name + "p" + (i + 1));
            projects.add(project);
        }

    }

    private void getChildFromCompany(Company company) {
        String name = company.getName();
        ArrayList<Project> projects = new ArrayList<>();
        company.setChild(projects);
        for (int i = 0; i < 10; i++) {
            Project project = new Project();
            project.setProjectName(name + "p" + (i + 1));
            projects.add(project);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Infermation o = (Infermation) parent.getItemAtPosition(position);

        if (o instanceof Group) {
            getTreeFromGroup((Group) o);
            ArrayList<Company> c = ((Group) o).getChild();
            companyAdapter.setData(c);
            companyAdapter.notifyDataSetChanged();
            spinnerCompany.setSelection(0);
            ArrayList<Project> p = c.get(0).getChild();
            projectAdapter.setData(p);
            projectAdapter.notifyDataSetChanged();
            spinnerProject.setSelection(0);

        } else if (o instanceof Company) {
            getChildFromCompany((Company) o);
            ArrayList<Project> p = ((Company) o).getChild();
            projectAdapter.setData(p);
            projectAdapter.notifyDataSetChanged();
            spinnerProject.setSelection(0);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private int getMaxValue() {
        int max = 0;
        for (Iterator<ChartBean> i = chartData.iterator(); i.hasNext(); ) {
            ChartBean bean = i.next();
            int value = bean.getValue();
            if (value > max) max = value;
        }
        return max;
    }

    private ArrayList<BarEntry> setBarEntryForBarChart() {
        ArrayList<BarEntry> yvals = new ArrayList<>();

        for (int i = 0; i < chartData.size(); i++) {
            int value = chartData.get(i).getValue();
            yvals.add(new BarEntry(i + 1, value));
        }
        return yvals;
    }

    private void setData() {
        ArrayList<BarEntry> entries = setBarEntryForBarChart();

        BarDataSet barDataSet = new BarDataSet(entries, "The year 2017");
        barDataSet.setDrawIcons(false);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        ArrayList<IBarDataSet> dataSet = new ArrayList<>();
        dataSet.add(barDataSet);

        BarData data = new BarData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTypeface(mTfLight);
        data.setBarWidth(0.9f);

        //Enables / disables drawing values (value-text) for all DataSets this data object contains.
        data.setDrawValues(false);

        barChart.setData(data);
    }

    protected void handleUIMessage(Message msg) {
        String json = msg.obj.toString();
        try {
            JSONArray jsonArray = new JSONArray(json);
            Group group = new Group();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
