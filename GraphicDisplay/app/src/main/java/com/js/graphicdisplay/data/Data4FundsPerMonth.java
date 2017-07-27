package com.js.graphicdisplay.data;

import java.math.BigDecimal;

/**
 * Created by apple on 2017/6/28.
 */

public class Data4FundsPerMonth {

    private BigDecimal indicatrixPerMonth; //每月指标

    private BigDecimal completionPerMonth; //每月完成量

    private BigDecimal rateCompletedPerMonth; //每月完成比率

    private BigDecimal indicatrixCumulated; //当月累计指标

    private BigDecimal completionCumulated; //当月累计完成

    private BigDecimal rateCompletedCumulated; //当月累计完成比率

    private String descUnfinished; //未完成情况说明

    public final String[] data;

    public Data4FundsPerMonth(double indicatrixPerMonth, double completionPerMonth, double rateCompletedPerMonth, double indicatrixCumulated, double completionCumulated, double rateCompletedCumulated, String descUnfinished) {
        data = new String[]{
                String.valueOf(indicatrixPerMonth),
                String.valueOf(completionPerMonth),
                String.valueOf(rateCompletedPerMonth),
                String.valueOf(indicatrixCumulated),
                String.valueOf(completionCumulated),
                String.valueOf(rateCompletedCumulated),
                descUnfinished
        };
    }


    public BigDecimal getIndicatrixCumulated() {
        return indicatrixCumulated;
    }

    public void setIndicatrixCumulated(BigDecimal indicatrixCumulated) {
        this.indicatrixCumulated = indicatrixCumulated;
    }

    public BigDecimal getCompletionCumulated() {
        return completionCumulated;
    }

    public void setCompletionCumulated(BigDecimal completionCumulated) {
        this.completionCumulated = completionCumulated;
    }

    public BigDecimal getRateCompletedCumulated() {
        return rateCompletedCumulated;
    }

    public void setRateCompletedCumulated(BigDecimal rateCompletedCumulated) {
        this.rateCompletedCumulated = rateCompletedCumulated;
    }

    public BigDecimal getIndicatrixPerMonth() {
        return indicatrixPerMonth;
    }

    public void setIndicatrixPerMonth(BigDecimal indicatrixPerMonth) {
        this.indicatrixPerMonth = indicatrixPerMonth;
    }

    public BigDecimal getCompletionPerMonth() {
        return completionPerMonth;
    }

    public void setCompletionPerMonth(BigDecimal completionPerMonth) {
        this.completionPerMonth = completionPerMonth;
    }

    public BigDecimal getRateCompletedPerMonth() {
        return rateCompletedPerMonth;
    }

    public void setRateCompletedPerMonth(BigDecimal rateCompletedPerMonth) {
        this.rateCompletedPerMonth = rateCompletedPerMonth;
    }
    public String getDescUnfinished() {
        return descUnfinished;
    }

    public void setDescUnfinished(String descUnfinished) {
        this.descUnfinished = descUnfinished;
    }
}
