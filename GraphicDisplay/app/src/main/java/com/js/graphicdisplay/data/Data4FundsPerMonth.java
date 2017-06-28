package com.js.graphicdisplay.data;

import java.math.BigDecimal;

/**
 * Created by apple on 2017/6/28.
 */

public class Data4FundsPerMonth {

    private BigDecimal indicatrixPerMonth; //每月指标

    private BigDecimal completionPerMonth; //每月完成量

    private BigDecimal rateCompletedPerMonth; //每月完成比率













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
}
