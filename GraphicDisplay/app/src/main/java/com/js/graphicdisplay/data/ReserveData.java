package com.js.graphicdisplay.data;

/**
 * Created by apple on 2017/7/31.
 */

public class ReserveData {

    /**
     * 年月
     */
    private String date;

    /**
     * 亩数(亩)
      */
    private float acre;

    /**
     * 总价(万)
     */
    private float totalPrice;

    /**
     * 已付款(万)
     */
    private float paid;

    /**
     * 可建总面积(m²)
     */
    private float buildableArea;

    /**
     * 储备建筑面积(m²)
     */
    private float reserveBuildingArea;

















    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAcre() {
        return acre;
    }

    public void setAcre(float acre) {
        this.acre = acre;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }

    public float getBuildableArea() {
        return buildableArea;
    }

    public void setBuildableArea(float buildableArea) {
        this.buildableArea = buildableArea;
    }

    public float getReserveBuildingArea() {
        return reserveBuildingArea;
    }

    public void setReserveBuildingArea(float reserveBuildingArea) {
        this.reserveBuildingArea = reserveBuildingArea;
    }
}
