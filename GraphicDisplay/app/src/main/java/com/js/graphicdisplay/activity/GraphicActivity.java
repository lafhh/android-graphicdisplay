package com.js.graphicdisplay.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.*;
import com.inqbarna.tablefixheaders.TableFixHeaders;
import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.base.BaseActivity;
import com.js.graphicdisplay.adapter.TableAdapter;
import com.js.graphicdisplay.adapter.SpinnerAdapter;
import com.js.graphicdisplay.api.Infermation;
import com.js.graphicdisplay.data.*;
import com.js.graphicdisplay.jsonutil.CompanyJsonParser;
import com.js.graphicdisplay.jsonutil.GroupJsonParser;
import com.js.graphicdisplay.mpchart.components.FundsMarkerView;
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
import java.util.HashMap;

/**
 * Created by js_gg on 2017/6/17.
 */

public class GraphicActivity extends BaseActivity {

    private static final String TAG = "GraphicActivity";
    private static final int BAR_CHART_MAX_VALUE_COUNT = 60;

    private Spinner spinnerGroup;
//    private Spinner spinnerCompany;
//    private Spinner spinnerDate;

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
    //    private ArrayList<Group> tableData = new ArrayList<>();
    private HashMap<String, Object> groups = new HashMap<>();
    private HashMap<String, Object> companies = new HashMap<>();
    private Tuple2<String, Integer> t2;

    private int totalRows;
    private final int pageSize = 10;
    private int pageIndex = 1;
    private int offset;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        spinnerGroup = (Spinner) findViewById(R.id.spinner_group);
        txtLabel = (TextView) findViewById(R.id.txt_name);
        spinnerPageNum = (Spinner) findViewById(R.id.spinner_pagenum);
        mLineChart = (LineChart) findViewById(R.id.linechart);
        mBarChart = (BarChart) findViewById(R.id.barchart);
        table = (TableFixHeaders) findViewById(R.id.table_funds);
        setTableOnTouchLisenter();

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
        LineChartCustomization.customLineChart(mLineChart, mTfLight, 1);
//        mLineChart.setOnChartGestureListener(new OnChartGestureImpl());

        //bar chart
        FundsMarkerView mv = new FundsMarkerView(this, R.layout.markerview_funds);
        BarChartCustomization.customBarChart(mBarChart, mTfLight, mv);

        //请求表格接口，拿到总记录数后，再次请求获取全部数据
        ArrayList<NameValuePair<String, String>> list = new ArrayList<>();
        list.add(new NameValuePair<>(NetUtil.KEY_LIMIT, String.valueOf(pageSize)));
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
                            totalRows = GroupJsonParser.fundsJson2HashMap(body, groups);
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

//        xGroup = new XGroup();
//        HashMap<String, Object> hashMap = new HashMap<>();
//
//        String[] titles = {"title1", "title2", "title2"};
//        int[] state = {1, 0, 0};
//        int[] widths = {22, 33, 44};
//        ArrayList<ArrayList<String>> glist = new ArrayList<>();
//        ArrayList<String> g1 = new ArrayList<>();
//        g1.add("01");
//        g1.add("01t2");
//        g1.add("01t3");
//        g1.add("1");
//        ArrayList<String> g2 = new ArrayList<>();
//        g2.add("02");
//        g2.add("02t2");
//        g2.add("02t3");
//        g2.add("2");
//        ArrayList<String> g3 = new ArrayList<>();
//        g3.add("03");
//        g3.add("03t2");
//        g3.add("03t3");
//        g3.add("3");
//        glist.add(g1);
//        glist.add(g2);
//        glist.add(g3);
//        hashMap.put("title", titles);
//        hashMap.put("state", state);
//        hashMap.put("width", widths);
//        hashMap.put("data", glist);
//        setSpinnerPagingInfo(); //绘制分页
//        setTableData(hashMap);  //绘制表格
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
                                        totalRows = GroupJsonParser.tableFromJson(body, chartData, 0);
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
                setChartData(chartData);
                setSpinnerGroupData();
                break;

            case MESSAGE_ERROR:
                break;

            case MESSAGE_FAILED:
                break;

            case 6: //获取集团下一级数据
                t2 = (Tuple2<String, Integer>) msg.obj;
                int groupId = t2._2;
                ArrayList<NameValuePair<String, String>> list = new ArrayList<>();
                list.add(new NameValuePair<>("groupId", String.valueOf(groupId)));
                list.add(new NameValuePair<>(NetUtil.KEY_LIMIT, String.valueOf(pageSize)));
                list.add(new NameValuePair<>(NetUtil.KEY_OFFSET, String.valueOf(0)));
                list.add(new NameValuePair<>(NetUtil.KEY_ORDER, "asc"));
                list.add(new NameValuePair<>(NetUtil.KEY_SORT, NetUtil.COMPNAME));
                HttpManager.doPost(NetUtil.URL_FUNDSTURNEDOVER_COMP_TABLE,
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
                                    totalRows = CompanyJsonParser.fundsJson2HashMap(body, companies);
                                    sendEmptyMessage(7);

                                } else {
                                    throw new IOException("Unexpected code " + response);
                                }
                            }
                        });
                /*** test ***/
//                HashMap<String, Object> hashMap = new HashMap<>();
//                String[] titles = {"s1", "s2", "s3", "s4"};
//                int[] state = {1, 0, 0, 0};
//                int[] widths = {22, 33, 44, 33};
//                ArrayList<ArrayList<String>> glist = new ArrayList<>();
//                ArrayList<String> g1 = new ArrayList<>();
//                g1.add("01");
//                g1.add("01s2");
//                g1.add("01s3");
//                g1.add("01s4");
//                g1.add("1");
//                ArrayList<String> g2 = new ArrayList<>();
//                g2.add("02");
//                g2.add("02s2");
//                g2.add("02s3");
//                g2.add("02s4");
//                g2.add("2");
//                ArrayList<String> g3 = new ArrayList<>();
//                g3.add("03");
//                g3.add("03s2");
//                g3.add("03s3");
//                g3.add("03s4");
//                g3.add("3");
//                glist.add(g1);
//                glist.add(g2);
//                glist.add(g3);
//                hashMap.put("title", titles);
//                hashMap.put("state", state);
//                hashMap.put("width", widths);
//                hashMap.put("data", glist);
//                setSpinnerPagingInfo(); //绘制分页
//                setTableData(hashMap);  //绘制表格
                /*** test ***/

                break;

            case 7: //展示集团下一级数据
                if (totalRows == 0) {
                    Toast.makeText(this, "已经到最后一页了", Toast.LENGTH_SHORT).show();
                } else {
                    setTableData(companies);
                    String name = t2._1;
                    txtLabel.setVisibility(View.VISIBLE);
                    txtLabel.setText(name);
                    txtLabel.setOnClickListener(new View.OnClickListener() { //回退到上一级
                        @Override
                        public void onClick(View v) {
                            setTableData(groups);
                            txtLabel.setVisibility(View.GONE);
                        }
                    });
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

    private void setTableData(HashMap<String, Object> maps) {
        if (tableAdapter == null) {
//            tableAdapter = new TableAdapter(this, tableData);
            tableAdapter = new TableAdapter(this, maps);
            table.setAdapter(tableAdapter);
        } else {
            tableAdapter.setData(maps);
            tableAdapter.notifyDataSetChanged();
        }
    }

    private void setSpinnerPagingInfo() {
        int num = totalRows % pageSize;
        int totalPages = num == 0 ? totalRows / pageSize : totalRows / pageSize + 1;
        Integer[] pageIndexs = new Integer[totalPages];
        for (int i = 0; i < totalPages; i++) {
            pageIndexs[i] = i + 1;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pageIndexs);
        spinnerPageNum.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

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
                                    HashMap<String, Object> map = new HashMap<>();
                                    totalRows = GroupJsonParser.fundsJson2HashMap(body, map);
                                    sendMessage(MESSAGE_GROUP_PAGING, map);

                                } else {
                                    sendMessage(MESSAGE_FAILED, body);
                                    throw new IOException("Unexpected code " + response);
                                }
                            }
                        });
                /***text****/
//                setTableData();
                /***text****/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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

        int startMonth = Integer.parseInt(chartData.get(0).getFundsData().getMonths().get(0));
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
                if (startMonth > month) {
                    startMonth = month;
                }
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
            barSet.setStackLabels(new String[]{"已完成指标", "已完成与计划的差额",});
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


    private void setTableOnTouchLisenter() {
        table.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                ScrollView scrollView = (ScrollView) GraphicActivity.this.findViewById(R.id.scrollview);
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }
}
