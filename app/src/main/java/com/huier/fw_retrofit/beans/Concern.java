package com.huier.fw_retrofit.beans;

import java.io.Serializable;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 */

public class Concern implements Serializable {
    private int id;
    private String province;
    private String city;
    private String district;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
