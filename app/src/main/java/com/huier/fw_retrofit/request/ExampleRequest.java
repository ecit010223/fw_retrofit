package com.huier.fw_retrofit.request;

import com.huier.fw_retrofit.beans.Translation;
import com.huier.fw_retrofit.beans.YouDaoTranslation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 */
public interface ExampleRequest {

    @GET("{api}")
    Call<ResponseBody> getRxJava(@Path("api") String api);

    /**
     * 金山词霸翻译
     * 注解里传入网络请求的部分URL地址;
     * Retrofit把网络请求的URL分成了两部分：一部分放在Retrofit对象里，另一部分放在网络请求接口里;
     * 如果接口里的url是一个完整的网址，那么放在Retrofit对象里的URL可以忽略；
     * askCIBA()是接受网络请求数据的方法。
     * a：固定值fy
     * f：原文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
     * t：译文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
     * w：查询内容
     */
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> askCIBA();

    /**
     * 有道词典翻译
     * 采用@FormUrlEncoded注解的原因:API规定采用请求格式x-www-form-urlencoded,即表单形式
     * 需要配合@Field 向服务器提交需要的字段
     * doctype：json或xml;
     * jsonversion：如果doctype值是xml,则去除该值,若doctype值是json,该值为空即可;
     * xmlVersion：如果doctype值是json,则去除该值,若doctype值是xml,该值为空即可;
     * type：语言自动检测时为null,为null时可为空。英译中为EN2ZH_CN,中译英为ZH_CN2EN,日译中为JA2ZH_CN,韩译中为KR2ZH_CN,中译法为ZH_CN2FR
     * keyform：mdict.+版本号+.手机平台,可为空
     * model：手机型号，可为空
     * mid：平台版本，可为空
     * imei：???，可为空
     * vendor：应用下载平台，可为空
     * screen：屏幕宽高，可为空
     * ssid：用户名，可为空
     * abtest：???，可为空
     */
    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<YouDaoTranslation> askYouDao(@Field("i") String targetSentence);
}
