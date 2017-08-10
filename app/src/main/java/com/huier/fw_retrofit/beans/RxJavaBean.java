package com.huier.fw_retrofit.beans;

import java.util.ArrayList;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 */

public class RxJavaBean {
    private int error_code;
    private String reason;
    private ArrayList<Concern> concerns;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ArrayList<Concern> getConcerns() {
        return concerns;
    }

    public void setConcerns(ArrayList<Concern> concerns) {
        this.concerns = concerns;
    }
}
