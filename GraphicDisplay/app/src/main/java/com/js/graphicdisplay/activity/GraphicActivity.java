package com.js.graphicdisplay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.js.graphicdisplay.R;
import com.js.graphicdisplay.activity.base.BaseActivity;
import com.js.graphicdisplay.adapter.SpinnerAdapter;
import com.js.graphicdisplay.api.Infermation;
import com.js.graphicdisplay.data.Company;
import com.js.graphicdisplay.data.Group;
import com.js.graphicdisplay.data.Project;

import java.util.ArrayList;

/**
 * Created by js_gg on 2017/6/17.
 */

public class GraphicActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "GraphicActivity";

    private Spinner spinnerGroup;
    private Spinner spinnerCompany;
    private Spinner spinnerProject;

    private SpinnerAdapter<Group> groupAdapter;
    private SpinnerAdapter<Company> companyAdapter;
    private SpinnerAdapter<Project> projectAdapter;

    private ArrayList<Group> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        list = initialData();
        //所有spinner的初始化
        spinnerGroup = (Spinner) findViewById(R.id.spinner_group);
        groupAdapter = new SpinnerAdapter<>(this, list);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(groupAdapter);
        spinnerGroup.setOnItemSelectedListener(this);

        ArrayList<Company> companies = list.get(0).getChild();
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
    }

    private ArrayList<Group> initialData() {
        ArrayList<Group> groups = new ArrayList<>();
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
        return groups;
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
        }
        if (o instanceof Company) {
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


}
