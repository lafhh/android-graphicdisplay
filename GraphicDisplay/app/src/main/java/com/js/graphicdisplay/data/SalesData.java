package com.js.graphicdisplay.data;

/**
 * Created by apple on 2017/8/1.
 */

public class SalesData {

    /**
     * 日期
     */
    private String date;

    /**
     * 日签约套数(套)
     */
    private float dayContractedQuantity;

    /**
     * 日签约金额(万元)
     */
    private float dayContractedAmount;

    /**
     * 日回款金额(万元)
     */
    private float dayIncome;













    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getDayContractedQuantity() {
        return dayContractedQuantity;
    }

    public void setDayContractedQuantity(float dayContractedQuantity) {
        this.dayContractedQuantity = dayContractedQuantity;
    }

    public float getDayContractedAmount() {
        return dayContractedAmount;
    }

    public void setDayContractedAmount(float dayContractedAmount) {
        this.dayContractedAmount = dayContractedAmount;
    }

    public float getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(float dayIncome) {
        this.dayIncome = dayIncome;
    }
}
