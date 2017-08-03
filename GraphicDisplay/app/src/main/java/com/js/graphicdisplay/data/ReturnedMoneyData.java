package com.js.graphicdisplay.data;

/**
 * Created by apple on 2017/8/1.
 *
 * 回款达成率数据
 */

public class ReturnedMoneyData {

    /**
     * 年月
     */
    private String date;

    /**
     * 本月计划回款额(万元)
     */
    private float monthIncomePlan;

    /**
     * 本月实际回款额(万元)
     */
    private float monthIncomeReal;

    /**
     * 本月计划达成率
     */
    private float monthIncomeAch;

    /**
     * 累计计划回款额(万元)
     */
    private float cumulativeIncomePlan;

    /**
     * 累计实际回款额(万元)
     */
    private float cumulativeIncomeReal;

    /**
     * 累计计划达成率
     */
    private float cumulativeIncomeAch;













    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getMonthIncomePlan() {
        return monthIncomePlan;
    }

    public void setMonthIncomePlan(float monthIncomePlan) {
        this.monthIncomePlan = monthIncomePlan;
    }

    public float getMonthIncomeReal() {
        return monthIncomeReal;
    }

    public void setMonthIncomeReal(float monthIncomeReal) {
        this.monthIncomeReal = monthIncomeReal;
    }

    public float getMonthIncomeAch() {
        return monthIncomeAch;
    }

    public void setMonthIncomeAch(float monthIncomeAch) {
        this.monthIncomeAch = monthIncomeAch;
    }

    public float getCumulativeIncomePlan() {
        return cumulativeIncomePlan;
    }

    public void setCumulativeIncomePlan(float cumulativeIncomePlan) {
        this.cumulativeIncomePlan = cumulativeIncomePlan;
    }

    public float getCumulativeIncomeReal() {
        return cumulativeIncomeReal;
    }

    public void setCumulativeIncomeReal(float cumulativeIncomeReal) {
        this.cumulativeIncomeReal = cumulativeIncomeReal;
    }

    public float getCumulativeIncomeAch() {
        return cumulativeIncomeAch;
    }

    public void setCumulativeIncomeAch(float cumulativeIncomeAch) {
        this.cumulativeIncomeAch = cumulativeIncomeAch;
    }
}
