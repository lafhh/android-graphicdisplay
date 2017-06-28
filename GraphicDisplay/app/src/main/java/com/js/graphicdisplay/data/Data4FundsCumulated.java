package com.js.graphicdisplay.data;

import java.math.BigDecimal;

/**
 * Created by apple on 2017/6/28.
 */

public class Data4FundsCumulated {

    private BigDecimal indicatrixCumulated; //累计指标

    private BigDecimal completionCumulated; //累计完成

    private BigDecimal rateCompletedCumulated; //累计完成比率











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
}
