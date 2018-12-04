package com.laiba.wash.mobile.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class HttpClientUtils {

    protected static Logger logger = (Logger.getLogger(HttpClientUtils.class));

    private static PoolingClientConnectionManager clientConnectionManager;
    /**
     * OK: Success!
     */
    public static final int OK = 200;
    /**
     * Not Modified: There was no new data to return.
     */
    public static final int NOT_MODIFIED = 304;
    /**
     * Bad Request: The request was invalid. An accompanying error message will
     * explain why. This is the status code will be returned during rate
     * limiting.
     */
    public static final int BAD_REQUEST = 400;
    /**
     * Not Authorized: Authentication credentials were missing or incorrect.
     */
    public static final int NOT_AUTHORIZED = 401;
    /**
     * Forbidden: The request is understood, but it has been refused. An
     * accompanying error message will explain why.
     */
    public static final int FORBIDDEN = 403;
    /**
     * Not Found: The URI requested is invalid or the resource requested, such
     * as a user, does not exists.
     */
    public static final int NOT_FOUND = 404;
    /**
     * Not Acceptable: Returned by the Search API when an invalid format is
     * specified in the request.
     */
    public static final int NOT_ACCEPTABLE = 406;
    /**
     * Internal Server Error: Something is broken. Please post to the group so
     * the Weibo team can investigate.
     */
    public static final int INTERNAL_SERVER_ERROR = 500;
    
    public static final int OTHER_IO_EXCEPTION = 498;
    
    public static final int READ_TIMEOUT = 499;
    
    
    /**
     * Bad Gateway: Weibo is down or being upgraded.
     */
    public static final int BAD_GATEWAY = 502;
    /**
     * Service Unavailable: The Weibo servers are up, but overloaded with
     * requests. Try again later. The search and trend methods use this to
     * indicate when you are being rate limited.
     */
    public static final int SERVICE_UNAVAILABLE = 503;
    
    public static final int TIME_OUT = 25000;

    public static final String SOCKET_TIMEOUT = "http.socket.timeout";
    public static final String COLLECTION_TIMEOUT = "http.connection.timeout";
    public static final String COLLECTION_MANAGER_TIMEOUT = "http.connection-manager.timeout";

    private static List<String> monitorHostList = new ArrayList<String>();

    static {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));

        clientConnectionManager = new PoolingClientConnectionManager(schemeRegistry);
        clientConnectionManager.setMaxTotal(200);
        clientConnectionManager.setDefaultMaxPerRoute(200);

        monitorHostList.add("58.211.5.54");
        monitorHostList.add("yjdg.21cn.com");
        monitorHostList.add("ztapi.51wcity.com");
        monitorHostList.add("112.64.17.26");
        monitorHostList.add("218.4.155.113");
        monitorHostList.add("58.211.5.58");
    }


    @SuppressWarnings("unused")
	private static String getCause(int statusCode) {
        String cause = null;
        switch (statusCode) {
            case NOT_MODIFIED:
                break;
            case BAD_REQUEST:
                cause = "The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.";
                break;
            case NOT_AUTHORIZED:
                cause = "Authentication credentials were missing or incorrect.";
                break;
            case FORBIDDEN:
                cause = "The request is understood, but it has been refused.  An accompanying error message will explain why.";
                break;
            case NOT_FOUND:
                cause = "The URI requested is invalid or the resource requested, such as a user, does not exists.";
                break;
            case NOT_ACCEPTABLE:
                cause = "Returned by the Search API when an invalid format is specified in the request.";
                break;
            case INTERNAL_SERVER_ERROR:
                cause = "Something is broken.  Please post to the group so the liushijie can investigate.";
                break;
            case BAD_GATEWAY:
                cause = "image server is down or being upgraded.";
                break;
            case SERVICE_UNAVAILABLE:
                cause = "Service Unavailable: img servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.";
                break;
            default:
                cause = "";
        }

        return statusCode + ":" + cause;
    }

    /**
     * 根据URL发送get请求获取数据
     *
     * @param url
     * @return
     */
    public static HttpResult doGet(String url,Map<String,String> headerMap,int... timeout) {
    	HttpResult httpResult = new HttpResult();
        long currentTime = System.currentTimeMillis();
        String result = null;
        HttpGet get = new HttpGet(url);
        String host = null;
        try {
            host = new URL(url).getHost();
            if(headerMap!=null && !headerMap.isEmpty()){
                for(Map.Entry<String,String> entry:headerMap.entrySet()){
                    get.setHeader(entry.getKey(),entry.getValue());
                }
            }
            
            HttpResponse response = getHttpClient(timeout).execute(get);
            HttpEntity resEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            httpResult.setCode(statusCode);
            if (resEntity != null) {
                try {
                    result = EntityUtils.toString(resEntity);
                } catch (Exception e) {
                    logger.error("+++++==> EntityUtils.toString error<==+++++",e);
                }
                httpResult.setBody(result);
            }
            if (statusCode != OK) {
                logger.error("+++++==>statusCode:["+statusCode+"],url:"+url+" <==+++++");
                get.abort();
                return httpResult;
            }
        }
        catch(SocketTimeoutException e)
        {
        	httpResult.setCode(READ_TIMEOUT);
        	return httpResult;
        }catch (IOException e) {
            logger.error("++++ doGet:" + url + " ++++++", e);
            httpResult.setCode(OTHER_IO_EXCEPTION);
        	return httpResult;
        } finally {
            get.releaseConnection();
            if(monitorHostList.contains(host)){
                logger.warn("============>["+url+"]"+" use time:["+(System.currentTimeMillis()-currentTime)+" ms]<============");
            }
        }
        return httpResult;
    }

    /**
     * 根据URL发送get请求获取数据
     *
     * @param url
     * @return
     */
    public static HttpResult doGetWithParams(String url,Map<String,String> paramsMap,int... timeout) {
    	if (paramsMap != null && paramsMap.size() > 0) {
    		url += "?";
            for (Map.Entry<String, String> m : paramsMap.entrySet()) {
                url += m.getKey()+"=" +m.getValue()+"&";
            }
            System.out.println(url);
            url = url.substring(0,url.length()-1);
            System.out.println(url);
        }
    	return doGet(url,null,timeout);
//    	HttpResult httpResult = new HttpResult();
//        long currentTime = System.currentTimeMillis();
//        String result = null;
//        HttpGet get = new HttpGet(url);
//        String host = null;
//        try {
//            host = new URL(url).getHost();
//            
//            HttpResponse response = getHttpClient(timeout).execute(get);
//            HttpEntity resEntity = response.getEntity();
//            int statusCode = response.getStatusLine().getStatusCode();
//            httpResult.setCode(statusCode);
//            if (statusCode != OK) {
//                logger.error("+++++==>statusCode:["+statusCode+"],url:"+url+" <==+++++");
//                get.abort();
//                return httpResult;
//            }
//            if (resEntity != null) {
//                String respBody = EntityUtils.toString(resEntity);
//                try {
//                    result = respBody;
//                } catch (Exception e) {
//                    logger.error("+++++==> respBody:" + respBody + " <==+++++",e);
//                }
//            }
//        }
//        catch(SocketTimeoutException e)
//        {
//        	httpResult.setCode(READ_TIMEOUT);
//        	return httpResult;
//        } catch (IOException e) {
//            logger.error("++++ doGet:" + url + " ++++++", e);
//            httpResult.setCode(OTHER_IO_EXCEPTION);
//        	return httpResult;
//        } finally {
//            get.releaseConnection();
//            if(monitorHostList.contains(host)){
//                logger.warn("============>["+url+"]"+" use time:["+(System.currentTimeMillis()-currentTime)+" ms]<============");
//            }
//        }
//        httpResult.setBody(result);
//        return httpResult;
    }
    
    /**
     * 根据URL发送get请求获取数据
     *
     * @param url
     * @return
     */
    public static HttpResult doGet(String url,int... timeout) {
    	return doGet(url,null,timeout);
//    	HttpResult httpResult = new HttpResult();
//        long currentTime = System.currentTimeMillis();
//        String result = null;
//        HttpGet get = new HttpGet(url);
//        String host = null;
//        HttpClient client = null;
//        HttpResponse response = null;
//        try {
//            host = new URL(url).getHost();
//            client = getHttpClient(timeout);
//            response = client.execute(get);
//            HttpEntity resEntity = response.getEntity();
//            int statusCode = response.getStatusLine().getStatusCode();
//            httpResult.setCode(statusCode);
//            if (statusCode != OK) {
//                logger.error("+++++==>statusCode:["+statusCode+"],url:"+url+" <==+++++");
//                get.abort();
//                return httpResult;
//            }
//            if (resEntity != null) {
//                try {
//                    result = EntityUtils.toString(resEntity);
//                } catch (Exception e) {
//                    logger.error("+++++==> respBody:" + result + " <==+++++",e);
//                }
//                resEntity = null;
//            }
//        } 
//        catch(SocketTimeoutException e)
//        {
//        	httpResult.setCode(READ_TIMEOUT);
//        	return httpResult;
//        }catch (IOException e) {
//            logger.error("++++ doGet:" + url + " ++++++", e);
//            httpResult.setCode(OTHER_IO_EXCEPTION);
//        	return httpResult;
//        } finally {
//            get.releaseConnection();
//            response=null;
//            client=null;
//            if(monitorHostList.contains(host)){
//                logger.warn("============>["+url+"]"+" use time:["+(System.currentTimeMillis()-currentTime)+" ms]<============");
//            }
//        }
//        httpResult.setBody(result);
//        return httpResult;
    }

    public static HttpResult doPostCharSet(String url, Map<String, String> paramsMap,String charSet,int... timeout)
    {
    	return doPostCharSet(url,paramsMap,null,charSet,timeout);
    }
    
    /**
     * 根据URL发送post请求获取数据,支持传字符集
     * @param url
     * @param paramsMap
     * @return
     */
    public static HttpResult doPostCharSet(String url, Map<String, String> paramsMap, Map<String, String> headerMap,String charSet,int... timeout) {
    	HttpResult httpResult = new HttpResult();
        long currentTime = System.currentTimeMillis();
        String result = null;
        HttpPost post = new HttpPost(url);
        String host = null;
        try {
            host = new URL(url).getHost();
            
//            post.addHeader("Cookie", "JSESSIONID=DCD7BFE23126E4B69ABCC415A6D688AF; Path=/");
            charSet = charSet==null||charSet.trim().equals("")?"UTF-8":charSet;
            if (paramsMap != null && paramsMap.size() > 0) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> m : paramsMap.entrySet()) {
                    params.add(new BasicNameValuePair(m.getKey(), m.getValue()));
                }
                UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(params,
                		charSet );
                post.setEntity(reqEntity);
            }
            
            //设置Header
            if(headerMap!=null && !headerMap.isEmpty()){
                for(Map.Entry<String,String> entry:headerMap.entrySet()){
                	post.setHeader(entry.getKey(),entry.getValue());
                }
            }
            
            HttpResponse response = getHttpClient(timeout).execute(post);

            HttpEntity resEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            httpResult.setCode(statusCode);
            if (resEntity != null) {
                try {
                    result = EntityUtils.toString(resEntity, charSet);
                } catch (Exception e) {
                    logger.error("+++++==> EntityUtils.toString <==+++++",
                            e);
                }
                httpResult.setBody(result);
            }
            if (statusCode != OK) {
                logger.error("+++++==>statusCode:["+statusCode+"],url:"+url+" <==+++++");
                post.abort();
                return httpResult;
            }
        }
        catch(SocketTimeoutException e)
        {
        	httpResult.setCode(READ_TIMEOUT);
        	return httpResult;
        }catch (IOException e) {
            logger.error("+++++==> doPost:" + url + " <==+++++", e);
            httpResult.setCode(OTHER_IO_EXCEPTION);
        	return httpResult;
        } finally {
            post.releaseConnection();
            if(monitorHostList.contains(host)){
                logger.warn("============>["+url+"]"+" use time:["+(System.currentTimeMillis()-currentTime)+" ms]<============");
            }
        }
        return httpResult;
    }
    
    public static HttpResult doPost(String url, Map<String, String> paramsMap,int... timeout)
    {
    	return doPost(url,paramsMap,null,timeout);
    }
    /**
     * 根据URL发送post请求获取数据
     *
     * @param url
     * @param paramsMap
     * @return
     */
    public static HttpResult doPost(String url, Map<String, String> paramsMap, Map<String, String> headerMap,int... timeout) {
    	HttpResult httpResult = new HttpResult();
        long currentTime = System.currentTimeMillis();
        String result = null;
        HttpPost post = new HttpPost(url);
        String host = null;
        try {
            host = new URL(url).getHost();
            
//            post.addHeader("Cookie", "JSESSIONID=DCD7BFE23126E4B69ABCC415A6D688AF; Path=/");
            
            if (paramsMap != null && paramsMap.size() > 0) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> m : paramsMap.entrySet()) {
                    params.add(new BasicNameValuePair(m.getKey(), m.getValue()));
                }
                UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(params,
                        "UTF-8");
                post.setEntity(reqEntity);
            }
            
            //设置Header
            if(headerMap!=null && !headerMap.isEmpty()){
                for(Map.Entry<String,String> entry:headerMap.entrySet()){
                	post.setHeader(entry.getKey(),entry.getValue());
                }
            }
            
            HttpResponse response = getHttpClient(timeout).execute(post);

            HttpEntity resEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            httpResult.setCode(statusCode);
            if (resEntity != null) {
                try {
                    result = EntityUtils.toString(resEntity , "utf-8");
                } catch (Exception e) {
                    logger.error("+++++==> EntityUtils.toString <==+++++",
                            e);
                }
                httpResult.setBody(result);
            }
            if (statusCode != OK) {
                logger.error("+++++==>statusCode:["+statusCode+"],url:"+url+" <==+++++");
                post.abort();
                return httpResult;
            }
        }
        catch(SocketTimeoutException e)
        {
        	httpResult.setCode(READ_TIMEOUT);
        	return httpResult;
        }catch (IOException e) {
            logger.error("+++++==> doPost:" + url + " <==+++++", e);
            httpResult.setCode(OTHER_IO_EXCEPTION);
        	return httpResult;
        } finally {
            post.releaseConnection();
            if(monitorHostList.contains(host)){
                logger.warn("============>["+url+"]"+" use time:["+(System.currentTimeMillis()-currentTime)+" ms]<============");
            }
        }
        return httpResult;
    }

    /**
     * 根据URL发送post请求获取数据
     * @param url
     * @param paramsMap
     * @return
     */
    public static HttpResult doPostGetCookie(String url, Map<String, String> paramsMap,int... timeout) {
    	HttpResult httpResult = new HttpResult();
        long currentTime = System.currentTimeMillis();
        String result = null;
        HttpPost post = new HttpPost(url);
        String host = null;
        try {
            host = new URL(url).getHost();
            if (paramsMap != null && paramsMap.size() > 0) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> m : paramsMap.entrySet()) {
                    params.add(new BasicNameValuePair(m.getKey(), m.getValue()));
                }
                UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(params,
                        "UTF-8");
                post.setEntity(reqEntity);
            }
            HttpResponse response = getHttpClient(timeout).execute(post);
            Header[] cookie = response.getHeaders("Set-Cookie");
            
            int statusCode = response.getStatusLine().getStatusCode();
            httpResult.setCode(statusCode);
            if (statusCode != OK) {
                logger.error("+++++==>statusCode:["+statusCode+"],url:"+url+" <==+++++");
                post.abort();
                return httpResult;
            }
            if (cookie != null) {
                result = cookie[0].toString();
                result = result.split("Set-Cookie: ")[1];
            }
            
        }
        catch(SocketTimeoutException e)
        {
        	httpResult.setCode(READ_TIMEOUT);
        	return httpResult;
        }catch (IOException e) {
            logger.error("+++++==> doPost:" + url + " <==+++++", e);
            httpResult.setCode(OTHER_IO_EXCEPTION);
        	return httpResult;
        } finally {
            post.releaseConnection();
            if(monitorHostList.contains(host)){
                logger.warn("============>["+url+"]"+" use time:["+(System.currentTimeMillis()-currentTime)+" ms]<============");
            }
        }
        httpResult.setBody(result);
        return httpResult;
    }
    
    public static HttpResult doPostUseCookie(String url, Map<String, String> paramsMap,String cookie,int... timeout) {
    	HttpResult httpResult = new HttpResult();
        long currentTime = System.currentTimeMillis();
        String result = null;
        HttpPost post = new HttpPost(url);
        String host = null;
        try {
            host = new URL(url).getHost();
            post.addHeader("Cookie", cookie);
            if (paramsMap != null && paramsMap.size() > 0) {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> m : paramsMap.entrySet()) {
                    params.add(new BasicNameValuePair(m.getKey(), m.getValue()));
                }
                UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(params,
                        "UTF-8");
                post.setEntity(reqEntity);
            }
            HttpResponse response = getHttpClient(timeout).execute(post);

            HttpEntity resEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            httpResult.setCode(statusCode);
            if (statusCode != OK) {
                logger.error("+++++==>statusCode:["+statusCode+"],url:"+url+" <==+++++");
                post.abort();
                return httpResult;
            }
            if (resEntity != null) {
                String respBody = EntityUtils.toString(resEntity);
                try {
                    result = respBody;
                } catch (Exception e) {
                    logger.error("+++++==> respBody:" + respBody + " <==+++++",
                            e);
                }
            }
        }catch(SocketTimeoutException e)
        {
        	httpResult.setCode(READ_TIMEOUT);
        	return httpResult;
        } catch (IOException e) {
            logger.error("+++++==> doPost:" + url + " <==+++++", e);
            httpResult.setCode(OTHER_IO_EXCEPTION);
        	return httpResult;
        } finally {
            post.releaseConnection();
            if(monitorHostList.contains(host)){
                logger.warn("============>["+url+"]"+" use time:["+(System.currentTimeMillis()-currentTime)+" ms]<============");
            }
        }
        httpResult.setBody(result);
        return httpResult;
    }
    /**
     * 访问服务
     * @param url 地址
     * @param xml
     * @return
     * @throws Exception
     */
    public static HttpResult doPostXml(String url, String xml,int... timeout){
        return doPostXml(url,"UTF-8",xml,null,timeout);
    }
    
    public static HttpResult doPostXml(String url, String xml,Map<String, String> headerMap,int... timeout){
        return doPostXml(url,"UTF-8",xml,headerMap,timeout);
    }
    
    public static HttpResult doPostXml(String url,String charSet, String xml,int... timeout) {
    	return doPostXml(url,charSet,xml,null,timeout);
    }

    public static HttpResult doPostXml(String url,String charSet, String xml,Map<String, String> headerMap,int... timeout) {
    	HttpResult httpResult = new HttpResult();
        long currentTime = System.currentTimeMillis();
        String result = null, host = null;
        HttpPost post = new HttpPost(url);
        // 然后把Soap请求数据添加到PostMethod中
        byte[] b = null;
        InputStream is = null;
        HttpResponse response = null;
        HttpClient httpClient = null;
        try {
            host = new URL(url).getHost();
            if(headerMap!=null && !headerMap.isEmpty()){
                for(Map.Entry<String,String> entry:headerMap.entrySet()){
                	post.setHeader(entry.getKey(),entry.getValue());
                }
            }
            charSet = charSet == null ? "UTF-8" : charSet;
            b = xml.getBytes(charSet);
            is = new ByteArrayInputStream(b, 0, b.length);
            HttpEntity reqEntity = new InputStreamEntity(is, b.length, ContentType.create(ContentType.TEXT_XML.getMimeType(), Charset.forName(charSet)));
            post.setEntity(reqEntity);
            httpClient = getHttpClient(timeout);
            response = httpClient.execute(post);
            HttpEntity resEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            httpResult.setCode(statusCode);
            if (resEntity != null) {
                try {
                    result = EntityUtils.toString(resEntity, charSet);
                    httpResult.setBody(result);
                } catch (Exception e) {
                    logger.error("+++++==> EntityUtils.toString error <==+++++", e);
                }
            }
            if (statusCode != OK) {
                logger.error("+++++==>statusCode:[" + statusCode + "],url:"+url+" <==+++++");
                post.abort();
                return httpResult;
            }
 
        } 
        catch(SocketTimeoutException e)
        {
        	httpResult.setCode(READ_TIMEOUT);
        	return httpResult;
        }catch (Exception e) {
            logger.error("+++++==> doPostXml:" + url + " <==+++++", e);
            httpResult.setCode(OTHER_IO_EXCEPTION);
        	return httpResult;
        } finally {
            post.releaseConnection();
            response = null;
            httpClient = null;
            if (is != null) {
                try {
					is.close();
				} catch (IOException e) {
					 logger.error("HttpClientUtil doPostXml error", e);
				}
            }
            if (monitorHostList.contains(host)) {
                logger.warn("============>[" + url + "]" + " use time:[" + (System.currentTimeMillis() - currentTime) + " ms]<============");
            }
        }
       
        return httpResult;
    }

    /**
     * 根据URL发送post请求获取数据
     * Rest
     * @param url
     * @return
     */
    public static HttpResult doPostJson(String url, String json,int... timeout) {
    	HttpResult httpResult = new HttpResult();
        String result = null;
        HttpPost post = new HttpPost(url);
        try {
            post.addHeader("content-type", "application/json");
            if (StringUtils.isNotEmpty(json)) {
                StringEntity myEntity = new StringEntity(json,"UTF-8");
                post.setEntity(myEntity);
            }
            HttpResponse response = getHttpClient(timeout).execute(post);

            HttpEntity resEntity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            httpResult.setCode(statusCode);
            Map<String,String> headerMap = new HashMap<String,String>();
            for(Header header : response.getAllHeaders())
            {
            	headerMap.put(header.getName(), header.getValue());
            }
            httpResult.setHeaderMap(headerMap);
            if (resEntity != null) {
                try {
                    result =  EntityUtils.toString(resEntity);
                } catch (Exception e) {
                    logger.error("+++++==> EntityUtils.toString <==+++++",e);
                }
                httpResult.setBody(result);
            }
            
            if (statusCode != OK) {
                logger.error("+++++==>statusCode:[" + statusCode + "],url:"+url+" <==+++++");
                post.abort();
                return httpResult;
            }
      
        } 
        catch(SocketTimeoutException e)
        {
        	httpResult.setCode(READ_TIMEOUT);
        	return httpResult;
        }catch (IOException e) {
            logger.error("+++++==> doPostJson:" + url + " <==+++++", e);
            httpResult.setCode(OTHER_IO_EXCEPTION);
        	return httpResult;
        } finally {
            post.releaseConnection();
        }
        return httpResult;
    }


    /**
     * 图片文件
     */
    public static final String FILE_TYPE_IMAGE = "1";
    /**
     * 视频文件
     */
    public static final String FILE_TYPE_VEDIO = "2";
    /**
     * 音频文件
     */
    public static final String FILE_TYPE_AUDIO = "3";
    /**
     * 安装文件
     */
    public static final String FILE_TYPE_SETUP = "4";
    /**
     * 压缩文件
     */
    public static final String FILE_TYPE_COMPRESS = "5";

    /**
     * 发送文件到文件服务器
     * @param filetype 文件类型 1图片 2音频 3视频 4安装文件 5以上自定义
     * @param data
     * @param typelimit 文件类型 填写允许的文件类型后缀,多个则逗号隔开
     * @param sizelimit 文件大小 单位byte,默认无限制
     * @return
     */
    public static HttpResult doMultipartPost(String url, byte[] data,String fileName ,String fileParamName,int... timeout) {
    	HttpResult httpResult = new HttpResult();
        String result = null;
        File tmpFile = null;
        HttpPost post = new HttpPost(url);
        try {
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,null,Charset.forName("UTF-8"));
            if(data!=null){
            	tmpFile = getFileFromBytes(data,"D:\\test\\tmp\\"+fileName);
                if (tmpFile != null && tmpFile.length()>0)
                	if(fileParamName == null){
                		fileParamName = "image";
                	}
//                    reqEntity.addPart(fileParamName, new FileBody(tmpFile));
                    reqEntity.addPart("image", new FileBody(tmpFile));
            }

            post.setEntity(reqEntity);

            HttpResponse response = getHttpClient(timeout).execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            httpResult.setCode(statusCode);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String respBody = EntityUtils.toString(resEntity);
                try {
                    result = respBody;
                } catch (Exception e) {
                    logger.error("+++++==> respBody:" + respBody + " <==+++++",e);
                }
            }
        }
        catch(SocketTimeoutException e)
        {
        	httpResult.setCode(READ_TIMEOUT);
        	return httpResult;
        }catch (IOException e) {
            logger.error("+++++==> uploadToFileStore:" + url + " <==+++++", e);
            httpResult.setCode(OTHER_IO_EXCEPTION);
        	return httpResult;
        } finally {
            post.releaseConnection();
            if (tmpFile != null){
                tmpFile.delete();
            }
        }
        httpResult.setBody(result);
        return httpResult;
    }
    
    
    /**
     * 发送文件到文件服务器
     * @param filetype 文件类型 1图片 2音频 3视频 4安装文件 5以上自定义
     * @param data
     * @param typelimit 文件类型 填写允许的文件类型后缀,多个则逗号隔开
     * @param sizelimit 文件大小 单位byte,默认无限制
     * @return
     */
    public static HttpResult doMultipartPost(String url, List<MultipartData> datas,int... timeout) {
    	HttpResult httpResult = new HttpResult();
        String result = null;
        List<File> tmpFileList = new ArrayList<File>();
        HttpPost post = new HttpPost(url);
        try {
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,null,Charset.forName("UTF-8"));
            if(datas!=null){
            	for(MultipartData data : datas){
            		File tmpFile = null;
            		try {
            			String fileName = data.getFileName();
            			if(StringUtils.isBlank(fileName)){
            				fileName = UUID.randomUUID().toString();
            			}
            			tmpFile = getFileFromBytes(data.getData(),"C:\\Users\\WMW\\Pictures\\test\\lixin\\" + fileName);
                		if (tmpFile != null && tmpFile.length()>0){
                			String paramName = data.getParamName();
                        	if(StringUtils.isBlank(paramName)){
                        		paramName = "image";
                        	}
                         reqEntity.addPart(paramName, new FileBody(tmpFile));
                		}
					} catch (Exception e) {
					} finally {
						if (tmpFile != null){
							tmpFileList.add(tmpFile);
                        }
					}
            	}
            }

            post.setEntity(reqEntity);

            HttpResponse response = getHttpClient(timeout).execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            httpResult.setCode(statusCode);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                String respBody = EntityUtils.toString(resEntity);
                try {
                    result = respBody;
                } catch (Exception e) {
                    logger.error("+++++==> respBody:" + respBody + " <==+++++",e);
                }
            }
        }
        catch(SocketTimeoutException e)
        {
        	httpResult.setCode(READ_TIMEOUT);
        	return httpResult;
        }catch (IOException e) {
            logger.error("+++++==> uploadToFileStore:" + url + " <==+++++", e);
            httpResult.setCode(OTHER_IO_EXCEPTION);
        	return httpResult;
        } finally {
            post.releaseConnection();
            if(tmpFileList!=null){
            	for(File tmpFile : tmpFileList){
            		if (tmpFile != null){
                        tmpFile.delete();
                    }
            	}
            }
        }
        httpResult.setBody(result);
        return httpResult;
    }

    /**
     * 流转文件
     * @param b
     * @param outputFile
     * @return
     */
    private static File getFileFromBytes(byte[] b, String outputFile) {
        File ret = null;
        if(null == b || StringUtils.isEmpty(outputFile))
            return null;

        BufferedOutputStream stream = null;
        try {
            ret = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(ret);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e) {
            logger.error("~~~", e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    logger.error("~~~~", e);
                }
            }
        }
        return ret;
    }
	
	 public static String generateUrl(Map<String, String> params){
       StringBuffer geturl = new StringBuffer("");
       List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			 geturl.append(key+"="+ WebUtil.UrlEncoder(value)+"&");
		}
       return geturl.toString();
    }
	 
    private static HttpClient getHttpClient(int... timeout) {
    	
        HttpClient httpClient = new DefaultHttpClient(clientConnectionManager);
        
        boolean hasSet = false;
        
        if (ArrayUtils.isNotEmpty(timeout)) {
        	int out = timeout[0];
        	if(out>0){
        		out = out * 1000;//秒转为毫秒
        		httpClient.getParams().setParameter(SOCKET_TIMEOUT,out);
                httpClient.getParams().setParameter(COLLECTION_TIMEOUT,out);
                hasSet = true;
        	}
        }
        if(!hasSet){
        	httpClient.getParams().setParameter(SOCKET_TIMEOUT,TIME_OUT);
            httpClient.getParams().setParameter(COLLECTION_TIMEOUT,TIME_OUT);
        }
        
        httpClient.getParams().setParameter(COLLECTION_MANAGER_TIMEOUT,10000000l);
        httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.IGNORE_COOKIES);
        return httpClient;
    }
    
    /**
     * 获取客户端请求ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {     
        String ip = request.getHeader("x-forwarded-for");     
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
           ip = request.getHeader("Proxy-Client-IP");     
       }     
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
           ip = request.getHeader("WL-Proxy-Client-IP");     
        }     
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getRemoteAddr();     
       }     
       return ip;     
    }    
}