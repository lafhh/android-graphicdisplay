package com.js.graphicdisplay.data;

/**
 * Created by apple on 2017/8/1.
 */

public class RealEstate {

    /**
     * 物业类型 0:自有物业;1:酒店
     */
    private int propertyType;

    /**
     * 物业业态 code
     * 自有物业: 住宅(house)、会所(club)、商业(business)、办公用房(office_room)、厂房(workshop)、车位(parking_lot)
     * 酒店: 酒店物业为该集团下的项目，对应为项目的code
     */
    private String fmt;

    /**
     * 业态的名称
     */
    private String fmtName;













    public int getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(int propertyType) {
        this.propertyType = propertyType;
    }

    public String getFmt() {
        return fmt;
    }

    public void setFmt(String fmt) {
        this.fmt = fmt;
    }

    public String getFmtName() {
        return fmtName;
    }

    public void setFmtName(String fmtName) {
        this.fmtName = fmtName;
    }
}
