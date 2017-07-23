package com.js.graphicdisplay.data;

import com.js.graphicdisplay.api.Infermation;

import java.util.ArrayList;

/**
 * Created by laf on 17-7-23.
 */

public class SpinnerDate implements Infermation {

    private String month;

    public static ArrayList<SpinnerDate> month2Infermation(ArrayList<String> months) {
        ArrayList<SpinnerDate> dates = new ArrayList<>();

        for (int i = 0; i < months.size(); i++) {
            SpinnerDate date = new SpinnerDate();
            date.setMonth(months.get(i));
            dates.add(date);
        }
        return dates;
    }

    @Override
    public String getName() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
