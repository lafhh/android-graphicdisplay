package com.js.graphicdisplay.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.*;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.*;
import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.base.BaseActivity;
import com.js.graphicdisplay.adapter.SpinnerAdapter;
import com.js.graphicdisplay.adapter.SpinnerPagingAdapter;
import com.js.graphicdisplay.adapter.TableAdapter;
import com.js.graphicdisplay.api.Infermation;
import com.js.graphicdisplay.data.*;
import com.js.graphicdisplay.jsonutil.CompanyJsonParser;
import com.js.graphicdisplay.jsonutil.GroupJsonParser;
import com.js.graphicdisplay.jsonutil.ItemJsonParser;
import com.js.graphicdisplay.mpchart.components.SalesMarkerView;
import com.js.graphicdisplay.mpchart.customization.BarChartCustomization;
import com.js.graphicdisplay.mpchart.customization.LineChartCustomization;
import com.js.graphicdisplay.mpchart.formatter.XAxisValueFormatter;
import com.js.graphicdisplay.net.HttpManager;
import com.js.graphicdisplay.net.NetUtil;
import com.js.graphicdisplay.net.Request;
import com.js.graphicdisplay.util.ColorTemplate;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by js_gg on 2017/6/17.
 * 销售情况表
 */
public class SalesGraphicActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "GraphicActivity";
    private static final int BAR_CHART_MAX_VALUE_COUNT = 60;

    private Spinner spinnerGroup;
//    private Spinner spinnerCompany;
//    private Spinner spinnerDate;

    private TextView labelLineChart;
    private TextView labelBarChart;

    private TextView txtLabel;
    private Spinner spinnerPageNum;

    private BarChart mBarChart;
    private LineChart mLineChart;

    private TableFixHeaders table;

    private SpinnerAdapter<Group> groupAdapter;
    private SpinnerAdapter<Company> companyAdapter;
    private SpinnerAdapter<SpinnerDate> dateAdapter;
    private TableAdapter tableAdapter;

    private ArrayList<Group> chartData = new ArrayList<>();
    private HashMap<String, Object> groups = new HashMap<>();
    private HashMap<String, Object> companies = new HashMap<>();
    private HashMap<String, Object> items;
    private Tuple2<String, Integer> groupT2;
    private Tuple2<String, Integer> companyT2;

    private int flag = FLOOR_GROUP;

    private int totalRows;
    private final int pageSize = 10;
    private int pageIndex = 1;
    private int offset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        labelLineChart = (TextView) findViewById(R.id.label_linechart);
        labelBarChart = (TextView) findViewById(R.id.label_barchart);
        spinnerGroup = (Spinner) findViewById(R.id.spinner_group);
        txtLabel = (TextView) findViewById(R.id.txt_name);
        spinnerPageNum = (Spinner) findViewById(R.id.spinner_pagenum);
        mLineChart = (LineChart) findViewById(R.id.linechart);
        mBarChart = (BarChart) findViewById(R.id.barchart);
        table = (TableFixHeaders) findViewById(R.id.table_funds);

        txtLabel.setOnClickListener(this);

        labelLineChart.setText("日签约套数展示--2017");
        labelBarChart.setText("日签约金额与回款金额展示--2017");

//        groupAdapter = new SpinnerAdapter<>(this, chartData);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerGroup.setAdapter(groupAdapter);
//        spinnerGroup.setOnItemSelectedListener(this);

//        ArrayList<Company> companies = groups.get(0).getChild();
//        spinnerCompany = (Spinner) findViewById(R.id.spinner_company);
//        companyAdapter = new SpinnerAdapter<>(this, companies);
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerCompany.setAdapter(companyAdapter);
//        spinnerCompany.setOnItemSelectedListener(this);

        //line chart
        LineChartCustomization.customLineChart(mLineChart, mTfLight, 2);
//        mLineChart.setOnChartGestureListener(new OnChartGestureImpl());

        //bar chart
        SalesMarkerView mv = new SalesMarkerView(this, R.layout.markerview_funds);
        BarChartCustomization.customBarChart(mBarChart, mTfLight, mv);

        //请求表格接口，拿到总记录数后，再次请求获取全部数据
        ArrayList<NameValuePair<String, String>> list = new ArrayList<>();
        list.add(new NameValuePair<>(NetUtil.KEY_LIMIT, String.valueOf(pageSize)));
        list.add(new NameValuePair<>(NetUtil.KEY_OFFSET, String.valueOf(0)));
        list.add(new NameValuePair<>(NetUtil.KEY_ORDER, "asc"));
        list.add(new NameValuePair<>(NetUtil.KEY_SORT, NetUtil.GROUPNAME));
        HttpManager.doPost(
                NetUtil.URL_SALES_TABLE_GROUP,
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
                            totalRows = GroupJsonParser.salesJson2HashMap(body, groups);
                            sendEmptyMessage(MESSAGE_TABLE);

                        } else {
                            sendMessage(MESSAGE_FAILED, body);
                            throw new IOException("Unexpected code " + response);
                        }
                    }
                });

        /*** test ***/
//        File file = Environment.getExternalStorageDirectory();
//        String path = file.getAbsolutePath() + "/Download/newTable.txt";
//        String json = FileUtil.readToString(path);
//        GroupJsonParser.tableFromJson(json, tableData);
//        totalRows = GroupJsonParser.tableFromJson(json, chartData);
//        setSpinnerPagingInfo(); //绘制分页
//        setTableData();  //绘制表格
////        setChartData(chartData);
//        setSpinnerGroupData();
        /*** test ***/
    }

    protected void handleUIMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_TABLE:

                if (totalRows == 0) {
                    Toast.makeText(this, "没有任何数据", Toast.LENGTH_SHORT).show();

                } else {
                    //拿到totalrow，再次通过table接口获取全部数据，供chart使用
                    ArrayList<NameValuePair<String, String>> list = new ArrayList<>();
                    list.add(new NameValuePair<>(NetUtil.KEY_LIMIT, String.valueOf(totalRows)));
                    list.add(new NameValuePair<>(NetUtil.KEY_OFFSET, String.valueOf(0)));
                    HttpManager.doPost(
                            NetUtil.URL_SALES_TABLE_GROUP,
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
                                        totalRows = GroupJsonParser.parseSalesFromJson(body, chartData);
                                        sendEmptyMessage(MESSAGE_CHART);

                                    } else {
                                        sendMessage(MESSAGE_FAILED, body);
                                        throw new IOException("Unexpected code " + response);
                                    }
                                }
                            });

                    setSpinnerPagingInfo(); //绘制分页
                    setTableData(groups);  //绘制表格
                }
                break;

            case MESSAGE_GROUP_PAGING:
                if (totalRows == 0) {
                    Toast.makeText(this, "已经到最后一页了", Toast.LENGTH_SHORT).show();
                } else {
                    groups = (HashMap<String, Object>) msg.obj;
                    setTableData(groups);
                }
                break;

            case MESSAGE_CHART:
                if (chartData.size() > 1) setChartData(chartData);
                else setChartData(chartData.get(0)); //znn 重新整理
                setSpinnerGroupData();
                break;

            case MESSAGE_ERROR:
                break;

            case MESSAGE_FAILED:
                break;

            case 6: //请求集团下一级数据
                groupT2 = (Tuple2<String, Integer>) msg.obj;
                int groupId = groupT2._2;
                ArrayList<NameValuePair<String, String>> list = new ArrayList<>();
                list.add(new NameValuePair<>("groupId", String.valueOf(groupId)));
                list.add(new NameValuePair<>(NetUtil.KEY_LIMIT, String.valueOf(pageSize)));
                list.add(new NameValuePair<>(NetUtil.KEY_OFFSET, String.valueOf(0)));
                list.add(new NameValuePair<>(NetUtil.KEY_ORDER, "asc"));
                list.add(new NameValuePair<>(NetUtil.KEY_SORT, "compName"));
                HttpManager.doPost(NetUtil.URL_SALES_TABLE_COMP,
                        list,
                        Request.ContentType.KVP,
                        new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String body = response.body().string();

                                if (response.isSuccessful()) {
                                    totalRows = CompanyJsonParser.salesJson2HashMap(body, companies);
                                    sendEmptyMessage(7);

                                } else {
                                    throw new IOException("Unexpected code " + response);
                                }
                            }
                        });
                break;

            case 7: //展示集团下一级数据
                if (totalRows == 0) {
                    Toast.makeText(this, "已经到最后一页了", Toast.LENGTH_SHORT).show();
                } else {
                    flag = FLOOR_COMPANY;
                    setTableData(companies);
                    String name = groupT2._1;
                    txtLabel.setVisibility(View.VISIBLE);
                    txtLabel.setText(name);
                }
                break;

            case 8: //请求公司下一级数据
                companyT2 = (Tuple2<String, Integer>) msg.obj;
                final int compId = companyT2._2;
                ArrayList<NameValuePair<String, String>> list1 = new ArrayList<>();
                list1.add(new NameValuePair<>("compId", String.valueOf(compId)));
                HttpManager.doPost(NetUtil.URL_QUERYITEMBYCOMPID, //请求公司下所有项目
                        list1,
                        Request.ContentType.KVP,
                        new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String body = response.body().string();

                                if (response.isSuccessful()) {
                                    items = (HashMap<String, Object>) companies.clone();
                                    totalRows = ItemJsonParser.salesJson2HashMap(body, items);
                                    sendEmptyMessage(9);

                                } else {
                                    throw new IOException("Unexpected code " + response);
                                }
                            }
                        });
                break;

            case 9: //展示公司下一级数据
                if (totalRows == 0) {
                    Toast.makeText(this, "已经到最后一页了", Toast.LENGTH_SHORT).show();
                } else {
                    flag = FLOOR_ITEM;
                    setTableData(items);
                    String name = companyT2._1;
                    txtLabel.setText(name);
                }
                break;
        }
    }

    private void setSpinnerGroupData() {
        if (groupAdapter == null) {
            groupAdapter = new SpinnerAdapter<>(this, chartData);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerGroup.setAdapter(groupAdapter);
            spinnerGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
//            ArrayList<Company> c = companies.get(((Group) o).getGroupCode());
//            companyAdapter.setData(c);
//            companyAdapter.notifyDataSetChanged();
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
            });
        }
    }

    private void setTableData(HashMap<String, Object> map) {
        if (tableAdapter == null) {
            tableAdapter = new TableAdapter(this, map);
            table.setAdapter(tableAdapter);
        } else {
            tableAdapter.setData(map);
            tableAdapter.notifyDataSetChanged();
        }
    }

    private void setSpinnerPagingInfo() {
        int totalPages = totalRows / pageSize + 1;
        Integer[] pageIndexs = new Integer[totalPages];
        for (int i = 0; i < totalPages; i++) {
            pageIndexs[i] = i + 1;
        }
        SpinnerPagingAdapter adapter = new SpinnerPagingAdapter(this, pageIndexs);
        spinnerPageNum.setAdapter(adapter);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPageNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int curr = (int) parent.getItemAtPosition(position);
                if (pageIndex == curr) return;

                pageIndex = curr;
                offset = pageSize * (pageIndex - 1);

                //集团分页，只要不是当前页，每次都进行网络请求
                ArrayList<NameValuePair<String, String>> list = new ArrayList<>();
                list.add(new NameValuePair<>(NetUtil.KEY_LIMIT, String.valueOf(pageSize)));
                list.add(new NameValuePair<>(NetUtil.KEY_OFFSET, String.valueOf(offset)));
                list.add(new NameValuePair<>(NetUtil.KEY_ORDER, "asc"));
                list.add(new NameValuePair<>(NetUtil.KEY_SORT, NetUtil.GROUPNAME));
                HttpManager.doPost(
                        NetUtil.URL_SALES_TABLE_GROUP,
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
                                    HashMap<String, Object> map = new HashMap<>();
                                    totalRows = GroupJsonParser.salesJson2HashMap(body, map);
                                    sendMessage(MESSAGE_GROUP_PAGING, map);

                                } else {
                                    sendMessage(MESSAGE_FAILED, body);
                                    throw new IOException("Unexpected code " + response);
                                }
                            }
                        });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    String[] date = {"20170701", "20170702", "20170801"};
    //group: a group of bars
    private void setChartData(ArrayList<Group> chartData) {
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

        int startMonth = Integer.parseInt(chartData.get(0).getSalesData().get(0).getDate());
        int maxSize = 0;

        for (int i = 0; i < chartData.size(); i++) {
            Group group = chartData.get(i);
            String name = group.getName();
            group.setKeyColor((i + 1) % ColorTemplate.TEMPLETE_COLOR.length);
            ArrayList<SalesData> list = group.getSalesData();

            ArrayList<BarEntry> barEntries = new ArrayList<>();
            ArrayList<Entry> lineEntries = new ArrayList<>();

            if (maxSize < list.size()) maxSize = list.size();
            for (int j = 0; j < list.size(); j++) {
                int month = Integer.parseInt(list.get(j).getDate());
                if (startMonth > month) startMonth = month;

                //stack bar entry.y = val1 + val2,
                float val1 = list.get(j).getDayIncome(); //日回款金额
                float val2 = list.get(j).getDayContractedAmount(); //日签约金额
                if (val1 < val2) {
                    val2 = val2 - val1;

                } else if (val1 == val2) {
                    val2 = 0;
                }
                barEntries.add(new BarEntry(month, new float[]{val1, val2}));

                //line entry
                lineEntries.add(new Entry(month,
                        list.get(j).getDayContractedQuantity()));
            }

            BarDataSet barSet = new BarDataSet(barEntries, name);
//            set.setBarBorderColor(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()]);
//            set.setBarBorderWidth(0.5f);
            barSet.setColors(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()], ColorTemplate.TEMPLETE_COLOR[0]);
            barSet.setStackLabels(new String[]{"日回款金额", "回款与签约的差额",});
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

    private void setChartData(Group group) {
        BarData barData = new BarData();
        LineData lineData = new LineData();

//        int startMonth = Integer.parseInt(group.getSalesData().get(0).getDate()); 修改x轴的分段
        int start = 0;

        String name = group.getName();
        group.setKeyColor(1);
        ArrayList<SalesData> list = group.getSalesData();

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<Entry> lineEntries = new ArrayList<>();

        for (int j = 0; j < list.size(); j++) {
            int month = Integer.parseInt(list.get(j).getDate());
//            if (startMonth > month) startMonth = month;
            //stack bar entry.y = val1 + val2,
            float val1 = list.get(j).getDayIncome(); //日回款金额
            float val2 = list.get(j).getDayContractedAmount(); //日签约金额
            if (val1 < val2) {
                val2 = val2 - val1;

            } else if (val1 == val2) {
                val2 = 0;
            }
            barEntries.add(new BarEntry(j, new float[]{val1, val2}));

            //line entry
            lineEntries.add(new Entry(j,
                    list.get(j).getDayContractedQuantity()));
        }

        //bar dataset
        BarDataSet barSet = new BarDataSet(barEntries, name);
        barSet.setColors(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()], ColorTemplate.TEMPLETE_COLOR[0]);
        barSet.setStackLabels(new String[]{"实际回款", "实际与签约的差额",});
        barData.addDataSet(barSet);

        //line dataset
        LineDataSet lineSet = new LineDataSet(lineEntries, name);
        lineSet.setLineWidth(2.5f);
        lineSet.setCircleRadius(4f);
        lineSet.setColor(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()]);
        lineSet.setCircleColor(ColorTemplate.TEMPLETE_COLOR[group.getKeyColor()]);
        lineData.addDataSet(lineSet);

        mBarChart.setData(barData);

//        barData.setBarWidth(0.4f); //设bar的宽度并不会影响到x轴的分段
        barData.setValueTypeface(mTfLight);
        barData.setDrawValues(false); //不显示y轴的值

//        mBarChart.getXAxis().setAxisMinimum(0); // 修改x轴的分段,不使用startMonth
        mBarChart.getXAxis().setValueFormatter(new XAxisValueFormatter(date));
//        mBarChart.getXAxis().setAxisMaximum(startMonth + groupWidth * maxSize);

        mBarChart.setFitBars(true);
        mBarChart.animateXY(2500, 2500);
        mBarChart.invalidate();

        mLineChart.setData(lineData);
        mLineChart.getXAxis().setValueFormatter(new XAxisValueFormatter(date));
        int width = mLineChart.getMeasuredWidth();
//        Log.d(TAG, "linechart width======" + width);

        lineData.setValueTypeface(mTfLight);
//        mLineChart.getXAxis().setAxisMinimum(startMonth);
//        mLineChart.getXAxis().setAxisMaximum(startMonth + mBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * months.size() - 1);
//        mLineChart.getXAxis().setSpaceMax(22f);

        mLineChart.animateX(300);
        mLineChart.invalidate();
    }


    @Override
    public void onClick(View v) {
        switch(flag) {
            case FLOOR_COMPANY:
                //return group floor
                setTableData(groups);
                v.setVisibility(View.GONE);
                flag = FLOOR_GROUP;
                break;

            case FLOOR_ITEM:
                //return company floor
                setTableData(companies);
                ((TextView) v).setText(groupT2._1);
                flag = FLOOR_COMPANY;
                break;
        }
    }
}
