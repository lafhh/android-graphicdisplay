package com.js.graphicdisplay.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.*;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.base.BaseActivity;
import com.js.graphicdisplay.adapter.SpinnerAdapter;
import com.js.graphicdisplay.api.Infermation;
import com.js.graphicdisplay.data.*;
import com.js.graphicdisplay.mpchart.customization.BarChartCustomization;
import com.js.graphicdisplay.mpchart.customization.LineChartCustomization;
import com.js.graphicdisplay.net.HttpManager;
import com.js.graphicdisplay.net.NetUtil;
import com.js.graphicdisplay.net.Request;
import com.js.graphicdisplay.util.ColorTemplate;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/17.
 */

public class GraphicActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "GraphicActivity";
    private static final int BAR_CHART_MAX_VALUE_COUNT = 60;

    private Spinner spinnerGroup;
    private Spinner spinnerCompany;
    private Spinner spinnerProject;

    private BarChart mBarChart;
    private LineChart mLineChart;

    private SpinnerAdapter<Group> groupAdapter;
    private SpinnerAdapter<Company> companyAdapter;
    private SpinnerAdapter<Project> projectAdapter;


    private ArrayList<Group> groups = new ArrayList<>();
    private ArrayList<Group> groups1 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        //spinner
        initialSpinnerData();
        spinnerGroup = (Spinner) findViewById(R.id.spinner_group);
        groupAdapter = new SpinnerAdapter<>(this, groups1);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(groupAdapter);
        spinnerGroup.setOnItemSelectedListener(this);

        ArrayList<Company> companies = groups1.get(0).getChild();
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

        //line chart
        mLineChart = (LineChart) findViewById(R.id.linechart);
        LineChartCustomization.customLineChart(mLineChart, mTfLight);
//        mLineChart.setOnChartGestureListener(new OnChartGestureImpl());

        //bar chart
        mBarChart = (BarChart) findViewById(R.id.barchart);
        BarChartCustomization.customBarChart(mBarChart, mTfLight);

        ArrayList<NameValuePair<String, String>> list = new ArrayList<>();
//        list.add(new NameValuePair<>(NetUtil.POST_ORGID, "4"));
//        list.add(new NameValuePair<>(NetUtil.POST_DATE, "201701"));
        HttpManager.doPost(
                NetUtil.URL_FUNDSTURNEDOVER_ALL_CHART,
                list,
                Request.ContentType.KVP,
                new Callback() {
                    Message msg;

                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        msg = Message.obtain();
                        msg.what = MESSAGE_ERROR;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String body = response.body().string();
                            Log.d(TAG, "response code : " + response.code());
                            Log.d(TAG, "body = " + body);
                            msg = Message.obtain();
                            msg.what = MESSAGE_SUCCESS;
                            msg.obj = body;
                            mHandler.sendMessage(msg);

                        } else {
                            msg = Message.obtain();
                            msg.what = MESSAGE_FAILED;
                            msg.obj = response.body().string();
                            mHandler.sendMessage(msg);
                            throw new IOException("Unexpected code " + response);
                        }
                    }
                });
        /*** test ***/
//        String file = "chart";
//        String json = FileUtil.readToString(file);
//        Log.d(TAG, "test json from file================" + json);
//        groupChartFromJson(json);
//        setChartData();
        /*** test ***/
    }

    private void initialSpinnerData() {
        for (int i = 0; i < 5; i++) {
            Group group = new Group();
            group.setGroupName("g" + (i + 1));
            groups1.add(group);
        }
        String group = groups1.get(0).getName();
        ArrayList<Company> companies = new ArrayList<>();
        groups1.get(0).setChild(companies);
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

    protected void handleUIMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_SUCCESS:
                String json = msg.obj.toString();
                groupChartFromJson(json);
                setChartData();
                break;

            case MESSAGE_ERROR:
                break;

            case MESSAGE_FAILED:
                break;
        }
    }

    private void groupChartFromJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            Group group = null;

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String groupCode = jsonObject.getString("groupCode");

                if (group == null || !group.getGroupCode().equals(groupCode)) {
                    group = new Group();
                    groups.add(group);
                }

                Group.fromJson(jsonObject, group);
            }

            Log.d("laf", "end"); //检查数据结构
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //group: a group of bars
    private void setChartData() {
        //indicatrixPerMonth & completionPerMonth, two bar per Group(集团)
        int barCountPerGroup = groups.size();

        float groupSpace = 0.08f;
        float perBarSpaceWidth = (1.00f - groupSpace) / barCountPerGroup;
        float barSpace = 0.00f;
        float barWidth = perBarSpaceWidth - barSpace;
//        float groupSpace = 0.08f;
//        float barSpace = 0.00f; // x4 DataSet
//        float barWidth = 0.23f; // x4 DataSet
        BarData barData = new BarData();
        LineData lineData = new LineData();

        ArrayList<String> months = groups.get(0).getMonths();
        int startMonth = Integer.parseInt(months.get(0));

//        for (int i = 0; i < months.size(); i++) {
//            int month = Integer.parseInt(months.get(i));
//
//            for (int j = 0; j < groups.size(); j++) {
//                Group group = groups.get(j);
//                Data4FundsPerMonth data = group.getFundsPerMonth().get(i);
//                String groupName = group.getName();
//
//                if (group.getTag() == null) {
//                    group.setTag(new ArrayList<BarEntry>());
//                }
//                ArrayList<BarEntry> barEntries = (ArrayList<BarEntry>) group.getTag();
//
//                //stack bar entry.y = val1 + val2, 每月指标vs每月完成的最大值
//                float val1 = data.getCompletionPerMonth().floatValue();
//                float val2 = data.getIndicatrixPerMonth().floatValue();
//                if (val1 < val2) { //
//                    val2 = val2 - val1;
//                    barEntries.add(new BarEntry(month, new float[]{val1, val2}));
////                    BarEntry en = new BarEntry(1, 2);
//                } else {
//                    float tmp = val1;
//                    val1 = val2;
//                    val2 = tmp - val2;
//                    barEntries.add(new BarEntry(month, tmp)); //没有测试
//                }
//
//                BarDataSet set;
//                if (i == months.size() - 1) {
//                    group.setKeyColor((j + 1) % ColorTemplate.TEMPLETE_COLOR.length);
//
//                    group.setTag(null);
//                    set = new BarDataSet(barEntries, groupName);
////                    set.setBarBorderColor(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()]);
////                    set.setBarBorderWidth(0.5f);
//                    set.setColors(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()], ColorTemplate.TEMPLETE_COLOR[0]);
//                    set.setStackLabels(new String[]{"已完成", "未完成",});
//
//                    barData.addDataSet(set);
////                    set.setDrawValues(false);
//                }
//            }
//        }

        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            String name = group.getName();
            group.setKeyColor((i + 1) % ColorTemplate.TEMPLETE_COLOR.length);
            ArrayList<Data4FundsPerMonth> datas = group.getFundsPerMonth();

            ArrayList<BarEntry> barEntries = new ArrayList<>();
            ArrayList<Entry> lineEntries = new ArrayList<>();

            for (int j = 0; j < months.size(); j++) {
                int month = Integer.parseInt(months.get(j));

                //stack bar entry.y = val1 + val2,
                // y应该是每月指标vs每月完成的最大值
                float val1 = datas.get(j).getCompletionPerMonth().floatValue();
                float val2 = datas.get(j).getIndicatrixPerMonth().floatValue();
                if (val1 < val2) { //
                    val2 = val2 - val1;
                    barEntries.add(new BarEntry(month, new float[]{val1, val2}));
                } else {
                    float tmp = val1;
                    val1 = val2;
                    val2 = tmp - val2;
                    barEntries.add(new BarEntry(month, tmp)); //没有测试
                }

                //line entry
                lineEntries.add(new Entry(month,
                        datas.get(j).getRateCompletedPerMonth().floatValue()));
            }

            BarDataSet barSet = new BarDataSet(barEntries, name);
//            set.setBarBorderColor(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()]);
//            set.setBarBorderWidth(0.5f);
            barSet.setColors(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()], ColorTemplate.TEMPLETE_COLOR[0]);
            barSet.setStackLabels(new String[]{"已完成", "未完成",});
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
        mBarChart.getXAxis().setAxisMaximum(startMonth + groupWidth * months.size());

        mBarChart.groupBars(startMonth, groupSpace, barSpace);
        mBarChart.setFitBars(true);
        mBarChart.animateXY(2500, 2500);
        mBarChart.invalidate();

        mLineChart.setData(lineData);
        int width = mLineChart.getMeasuredWidth();
        Log.d(TAG, "linechart width======" + width);

        lineData.setValueTypeface(mTfLight);
//        mLineChart.getXAxis().setAxisMinimum(startMonth);
//        mLineChart.getXAxis().setAxisMaximum(startMonth + mBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * months.size() - 1);
//        mLineChart.getXAxis().setSpaceMax(22f);

        mLineChart.animateX(300);
        mLineChart.invalidate();
    }
}
