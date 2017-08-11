package com.huier.fw_retrofit.beans;

import java.io.Serializable;

/**
 * 作者：张玉辉
 * 时间：2017/8/11.
 */

public class TranslateResultBean implements Serializable{
    public String src;
    public String tgt;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTgt() {
        return tgt;
    }

    public void setTgt(String tgt) {
        this.tgt = tgt;
    }
}
