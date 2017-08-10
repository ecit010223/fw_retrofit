package com.huier.fw_retrofit;

import okhttp3.MediaType;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 */

public class Constants {
    public static final String TAG = "tag";
    /** 本地rxjava.json访问地址 **/
    public static final String URL_RXJAVA = "http://192.168.1.211:8080/";
    /** 有道翻译地址 **/
    public static final String URL_YOUDAO = "http://fanyi.youdao.com/";

    /** 文本格式 **/
    public static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain; charset=utf-8");
    /** markdown格式 **/
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");
    /** 流文件格式 **/
    public static final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");
}
