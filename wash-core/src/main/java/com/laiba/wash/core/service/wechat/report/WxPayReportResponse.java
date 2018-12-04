package com.laiba.wash.core.service.wechat.report;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class WxPayReportResponse {
	
	private Map<String, Object> map ;
    private Map<String, String> errorMap;
    
    private String return_code;//返回状态码
    private String return_msg;//返回信息
    
    private String appid;//公众账号ID
    private String mch_id;//商户号
    private String device_info;//设备号
    private String nonce_str;//随机字符串
    private String sign;//签名
    private String sign_type;
    
    private String result_code;//业务结果
    private String err_code;//错误代码
    private String err_code_des;//错误代码描述
    
    private String openid;//用户标识
    private String is_subscribe;//是否关注公众账号
    private String trade_type;//交易类型
    private String bank_type;//付款银行
    private Integer total_fee;//订单金额
    private Integer settlement_total_fee;//应结订单金额
    private String fee_type;//货币种类
    private Integer cash_fee;//现金支付金额
    private String cash_fee_type;//现金支付货币类型
    private Integer coupon_fee;//总代金券金额
    private Integer coupon_count;//代金券使用数量
    private String coupon_type_$n ;//代金券类型 
    private String coupon_id_$n;//代金券ID
    private Integer coupon_fee_$n;//单个代金券支付金额
    private String transaction_id;//微信支付订单号
    private String out_trade_no;//商户订单号
    private String attach;//商家数据包
    private String time_end;//支付完成时间
    
    

    /**
     * 接口返回的InputStream流（XML流）
     * @param inputStream
     */
    public WxPayReportResponse(InputStream inputStream) throws DocumentException {
        super();
        this.map = getResultsMap(inputStream);
        this.errorMap = new HashMap<String, String>();
        //错误码
        this.errorMap.put("NOAUTH", "商户无此接口权限");
        this.errorMap.put("NOTENOUGH", " 余额不足");
        this.errorMap.put("ORDERPAID", " 商户订单已支付");
        this.errorMap.put("ORDERCLOSED", "订单已关闭");
        this.errorMap.put("SYSTEMERROR", "系统错误");
        this.errorMap.put("APPID_NOT_EXIST", "APPID不存在");
        this.errorMap.put("MCHID_NOT_EXIST", "MCHID不存在");
        this.errorMap.put("APPID_MCHID_NOT_MATCH", "appid和mch_id不匹配");
        this.errorMap.put("LACK_PARAMS", "缺少参数");
        this.errorMap.put("OUT_TRADE_NO_USED", "商户订单号重复");
        this.errorMap.put("SIGNERROR", "签名错误");
        this.errorMap.put("XML_FORMAT_ERROR", "XML格式错误");
        this.errorMap.put("REQUIRE_POST_METHOD", "请使用post方法");
        this.errorMap.put("POST_DATA_EMPTY", "post数据为空");
        this.errorMap.put("NOT_UTF8", "编码格式错误 ");
    }


    /**
     * 只针对没有重复节点的xml
     * @param in
     * @return
     */
    private Map<String, Object> getResultsMap(InputStream in) throws DocumentException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (in == null) {
            return map;
        }
        SAXReader reader = new SAXReader();
        Document document = reader.read(in);
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        for (Element child : childElements) {
            map.put(child.getName(), child.getStringValue());
        }
        return map;
    }

    public String responseString(){
    	String string = "";
    	for(Entry<String, Object> entry : map.entrySet()){
    		string += entry.getKey() + ":" + entry.getValue() + "\n";
    	}
    	return string;
    }

    /**
     * 返回状态码
     *
     * @return
     */
    public String getReturn_code() {
        return this.map.get("return_code").toString();
    }

    /**
     * 返回信息
     *
     * @return
     */
    public String getReturn_msg() {
        return this.map.get("return_msg").toString();
    }

    /**
     * 当return_code为SUCCESS时返回：公众账号ID
     *
     * @return
     */
    public String getAppid() {
        return this.map.get("appid").toString();
    }

    /**
     * 当return_code为SUCCESS时返回：商户号
     *
     * @return
     */
    public String getMch_id() {
        return this.map.get("mch_id").toString();
    }

    /**
     * 当return_code为SUCCESS时返回：设备号
     *
     * @return
     */
    public String getDevice_info() {
        return this.map.get("device_info").toString();
    }

    /**
     * 当return_code为SUCCESS时返回：随机字符串
     *
     * @return
     */
    public String getNonce_str() {
        return this.map.get("nonce_str").toString();
    }

    /**
     * 当return_code为SUCCESS时返回：签名
     *
     * @return
     */
    public String getSign() {
        return this.map.get("sign").toString();
    }

    /**
     * 当return_code为SUCCESS时返回：业务结果
     *
     * @return
     */
    public String getResult_code() {
        return this.map.get("result_code").toString();
    }

    /**
     * 当return_code为SUCCESS时返回：错误代码
     * 可以根据getErrorMsg(String errCode)  来获取错误信息。
     *
     * @return
     */
    public String getErr_code() {
        return this.map.get("err_code").toString();
    }

    /**
     * 当return_code为SUCCESS时返回：错误代码描述
     *
     * @return
     */
    public String getErr_code_des() {
        return this.map.get("err_code_des").toString();
    }

    /**
     * 当return_code 和result_code都为SUCCESS时返回：交易类型
     *
     * @return
     */
    public String getTrade_type() {
        return this.map.get("trade_type").toString();
    }

    /**
     * 当return_code 和result_code都为SUCCESS时返回：预支付交易会话标识
     *
     * @return
     */
    public String getPrepay_id() {
        return this.map.get("prepay_id").toString();
    }

    /**
     * 根据错误编码返回错误信息
     *
     * @param errCode
     * @return
     */
    public String getErrorMsg(String errCode) {
        return this.errorMap.get(errCode);
    }
    
    public String getOpenid() {
        return this.map.get("openid").toString();
    }
    
    public String getTransaction_id() {
        return this.map.get("transaction_id").toString();
    }
    
    public String getOut_trade_no() {
        return this.map.get("out_trade_no").toString();
    }

    //错误信息
    public final String NOAUTH = "商户无此接口权限";
    public final String NOTENOUGH = " 余额不足";
    public final String ORDERPAID = " 商户订单已支付";
    public final String ORDERCLOSED = "订单已关闭";
    public final String SYSTEMERROR = "系统错误";
    public final String APPID_NOT_EXIST = "APPID不存在";
    public final String MCHID_NOT_EXIST = "MCHID不存在";
    public final String APPID_MCHID_NOT_MATCH = "appid和mch_id不匹配";
    public final String LACK_PARAMS = "缺少参数";
    public final String OUT_TRADE_NO_USED = "商户订单号重复";
    public final String SIGNERROR = "签名错误";
    public final String XML_FORMAT_ERROR = "XML格式错误";
    public final String REQUIRE_POST_METHOD = "请使用post方法";
    public final String POST_DATA_EMPTY = "post数据为空";
    public final String NOT_UTF8 = "编码格式错误 ";


	@Override
	public String toString() {
		String s = "";
		for(Entry<String, Object> entry : this.map.entrySet()){
			s += entry.getKey() + "=" + entry.getValue() + ",";
		}
		return s;
	}
    
}
