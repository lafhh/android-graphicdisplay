package com.js.graphicdisplay.jsonutil;

import com.js.graphicdisplay.data.Data4FundsPerMonth;
import com.js.graphicdisplay.data.FundsData;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by apple on 2017/7/26.
 */

public class FundsDataJsonParser {

    public static void FundsDataFromJson(JSONObject json, FundsData data) throws JSONException {

        ArrayList<String> months = data.getMonths();
        if (months == null) {
            months = new ArrayList<>();
            data.setMonths(months);
        }
        months.add(json.getString("ym"));

        ArrayList<Data4FundsPerMonth> fundsPerMonths = data.getFundsPerMonth();
        if (fundsPerMonths == null) {
            fundsPerMonths = new ArrayList<>();
            data.setFundsPerMonth(fundsPerMonths);
        }
        double monthIndex = json.getDouble("monthIndex");
        double monthfulfilQuantity = json.getDouble("monthFulfilQuantity");
        double monthAch = json.getDouble("monthAch");
        double cumulativeIndex = json.getDouble("cumulativeIndex");
        double cumulativeFulfilQuantity = json.getDouble("cumulativeFulfilQuantity");
        double cumulativeAch = json.getDouble("cumulativeAch");

        String descUnfinished = "";
        if (json.get("incompleteDescription") != null) {
            descUnfinished = json.getString("incompleteDescription");
        }

        Data4FundsPerMonth fundsPerMonth = new Data4FundsPerMonth(
                monthIndex, monthfulfilQuantity, monthAch, cumulativeIndex, cumulativeFulfilQuantity, cumulativeAch, descUnfinished
        );
        fundsPerMonth.setIndicatrixPerMonth(
                new BigDecimal(monthIndex));
        fundsPerMonth.setCompletionPerMonth(
                new BigDecimal(monthfulfilQuantity));
        fundsPerMonth.setRateCompletedPerMonth(
                new BigDecimal(monthAch));
        fundsPerMonth.setIndicatrixCumulated(
                new BigDecimal(cumulativeIndex));
        fundsPerMonth.setCompletionCumulated(
                new BigDecimal(cumulativeFulfilQuantity));
        fundsPerMonth.setRateCompletedCumulated(
                new BigDecimal(cumulativeAch));
        fundsPerMonth.setDescUnfinished(descUnfinished);
        fundsPerMonths.add(fundsPerMonth);
    }
}
