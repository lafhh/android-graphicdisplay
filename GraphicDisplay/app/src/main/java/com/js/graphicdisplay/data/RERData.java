package com.js.graphicdisplay.data;

/**
 * Created by apple on 2017/8/1.
 */

public class RERData {

    /**
     * 年月
     */
    private String date;

    /**
     * 总建筑面积(㎡)
     */
    private float totalConstructionArea;

    /**
     * 可出租面积(㎡)
     */
    private float leasableArea;
    /**
     * 本月出租面积(㎡)
     */
    private float monthlyRentalArea;
    /**
     * 累计已出租面积(㎡)
     */
    private float leasedArea;
    /**
     * 出租率(%)
     */
    private float lettingRate;
    /**
     * 合同总额(元)
     */
    private float totalContract;









    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotalConstructionArea() {
        return totalConstructionArea;
    }

    public void setTotalConstructionArea(float totalConstructionArea) {
        this.totalConstructionArea = totalConstructionArea;
    }

    public float getLeasableArea() {
        return leasableArea;
    }

    public void setLeasableArea(float leasableArea) {
        this.leasableArea = leasableArea;
    }

    public float getMonthlyRentalArea() {
        return monthlyRentalArea;
    }

    public void setMonthlyRentalArea(float monthlyRentalArea) {
        this.monthlyRentalArea = monthlyRentalArea;
    }

    public float getLeasedArea() {
        return leasedArea;
    }

    public void setLeasedArea(float leasedArea) {
        this.leasedArea = leasedArea;
    }

    public float getLettingRate() {
        return lettingRate;
    }

    public void setLettingRate(float lettingRate) {
        this.lettingRate = lettingRate;
    }

    public float getTotalContract() {
        return totalContract;
    }

    public void setTotalContract(float totalContract) {
        this.totalContract = totalContract;
    }
}
