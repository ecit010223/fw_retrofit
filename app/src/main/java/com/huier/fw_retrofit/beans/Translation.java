package com.huier.fw_retrofit.beans;

import java.io.Serializable;

/**
 * 作者：张玉辉
 * 时间：2017/8/10.
 * json数据格式：
 * {
 *     "status":1,
 *     "content":{
 *         "from":"en-EU",
 *         "to":"zh-CN",
 *         "vendor":"baidu",
 *         "out":"你好世界<br/>",
 *         "err_no":0
 *     }
 * }
 */

public class Translation implements Serializable {
    public int status;
    public Content content;

    //定义 输出返回数据 的方法
    public String show() {
        return "status:"+status+",content.from:"+content.from+",content.to:"+content.to+",content.vendor:"+content.vendor
                +",content.out:"+content.out+",content.err_no:"+content.errNo;
    }
}
