package com.huier.fw_retrofit.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：张玉辉
 * 时间：2017/8/11.
 * {
 *     "type":"EN2ZH_CN",  //翻译类型，2之前表示原文类型，2之后表示译文类型
 *     "errorCode":0,  //0表示成功
 *     "elapsedTime":0,
 *     "translateResult":[
 *          [
 *              {
 *                  "src":"merry_me",
 *                  "tgt":"我快乐"
 *              }
 *          ]
 *     ]
 * }
 */

public class YouDaoTranslation implements Serializable{
    private String type;
    private int errorCode;
    private int elapsedTime;
    private List<List<TranslateResultBean>> translateResult;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<List<TranslateResultBean>> getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(List<List<TranslateResultBean>> translateResult) {
        this.translateResult = translateResult;
    }
}
