package com.laiba.wash.mobile.util;


public class WeiXinConfig {

    /**
     * 获取微信access token
     */
    public static String WEIXIN_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";


    /**
     * 获取微信 jsapi token
     */
    public static String WEIXIN_JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";


    /**
     * 微信appid  正式 wxbb240a1154431897
     */
    public static String WEIXIN_APPID = "wxd3f8edaaba8f9d03";


    /**
     * 微信appsecret  正式  927140a95f439dcac70730c6347760a5
     */
    public static String WEIXIN_APPSECRET = "fab3a6ca7e24669fd5142626f190eec0";
 
    /**
     * 第一步:用户同意授权，获取code
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
     */
    public static String WEIXIN_OAUTH2_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize";


    /**
     * 第二步： 通过code换取网页授权access_token
     * 请求以下链接获取access_token：
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     */
    public static String WEIXIN_ACCESS_TOKEN_OAUTH2 = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * 第四步：获取微信用户信息
     * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     */
    public static String WEIXIN_USERINFO = "https://api.weixin.qq.com/cgi-bin/user/info";



    /**
     * 微信支付商户相关信息
     */
    // 微信支付商户号
    public static String WEIXIN_PAY_PARTER_ID = "1348318401";
    // 微信支付商户号_APP
    public static String WEIXIN_PAY_PARTER_ID_APP = "1348527401";
    // 微信商户账户key
    public static String WEIXIN_PAY_PARTER_KEY = "wmwbeautysalon201600000000000000";
    // 微信支付商户名称
    public static String WEIXIN_PAY_PARTER_NAME = "深圳微美薇健康美容科技有限公司";
    // 微信支付成功回调地址
    public static String WEIXIN_PAY_NOTIFY_URL = "http://test.dev.wmwbeautysalon.com/wap/store/pay/notify";


}
