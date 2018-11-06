package com.bbsuper.nev.utils;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.CharEncoding;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.collect.Maps;
/**
 * 新版 Http工具类
 * @author liwei
 * @date: 2018年8月31日 下午4:53:00
 *
 */
public class HttpClientUtils {
	
	private static  Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
		
	
	/**
	 * get请求
	 * @param url
	 * @param params
	 * @param headers
	 * @return
	 */
	public static String get(String url, Map<String,String> headers, Map<String,String> params ){
		CloseableHttpClient httpClient = createCookiesHttpClient();
    	CloseableHttpResponse response=null;
		try{
    		HttpGet httpGet = new HttpGet(url+builderUrlParams(params));
    		//头部
			setHeaders(httpGet,headers);
			
            response = httpClient.execute(httpGet);
            return toString(response);
            
		}catch(Exception e){
			logger.error("链接:{}异常",url,e);
			return null;
    	}finally{
    		closeStream(response,httpClient);
    	}
	}
	

	/**
	 * get请求
	 * @param url
	 * @return
	 */
	public static String get(String url){
		return get(url,Maps.newHashMap(),Maps.newHashMap());
	}
	
	/**
	 * get请求
	 * @param url
	 * @param headers
	 * @return
	 */
	public static String get(String url,Map<String,String> headers){
		return get(url,headers,Maps.newHashMap());
	}
	/**
	 * 创建url格式的参数
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String builderUrlParams(Map<String, String> params) throws Exception {
		if (params != null && !params.isEmpty()) {
			List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>(params.size());
			params.forEach((key, value) -> {
				pairs.add(new BasicNameValuePair(key, value));
			});
			return "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CharEncoding.UTF_8));
		}
		return "";
	}
	/**
	 * post提交json或者xml、文本信息
	 * @param url
	 * @param data
	 * @param headers
	 * @return
	 */
	public static String postData(String url,String data, Map<String,String> headers){
		CloseableHttpClient httpClient = createCookiesHttpClient();
    	CloseableHttpResponse response=null;
		try{
    		HttpPost httpPost = new HttpPost(url);
			//头部
			setHeaders(httpPost,headers);
			
    		httpPost.setEntity(new StringEntity(data, CharEncoding.UTF_8));
            response = httpClient.execute(httpPost);
            return toString(response);
            
		}catch(Exception e){
			logger.error("链接:{}异常",url,e);
			return null;
    	}finally{
    		closeStream(response, httpClient);
    	}
	}
	/**
	 * post提交json或者xml、文本信息
	 * @param url
	 * @param data
	 * @return
	 */
	public static String postData(String url,String data){
		return postData(url, data, Maps.newHashMap());
	}
	
	/**
	 * 提交表单数据
	 * @param url
	 * @param from
	 * @param headers
	 * @return
	 */
	public static String postForms(String url, Map<String, String> from, Map<String,String> headers) {
		CloseableHttpClient httpClient = createCookiesHttpClient();
		CloseableHttpResponse response = null;

		try {
			HttpPost httpPost = new HttpPost(url);
			//头部
			setHeaders(httpPost,headers);
			//表单
			setFormEntity(httpPost,from);
			
			response = httpClient.execute(httpPost);
			return toString(response);
		} catch (Exception e) {
			logger.error("链接:{}异常",url,e);
			return null;
		} finally {
			closeStream(response,httpClient);
		}

	}
	/**
	 * 设置表单格式数据
	 * @param httpPost
	 * @param from
	 * @throws Exception
	 */
	private static void setFormEntity(HttpPost httpPost,Map<String, String> from) throws Exception {
		List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();// 用于存放表单数据.
		for (Map.Entry<String, String> entry : from.entrySet()) {
			pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(pairs, CharEncoding.UTF_8));
		
	}
	/**
	 * 设置请求头
	 * @param httpRequest
	 * @param headers
	 */
	private static void setHeaders(HttpRequestBase httpRequest, Map<String, String> headers) {
		if (headers != null && headers.size() > 0) {
			headers.forEach((key, value) -> {
				httpRequest.addHeader(key, value);
			});
		}
	}
	/**
	 * 提交表单数据
	 * @param url
	 * @param from
	 * @return
	 */
	public static String postForms(String url, Map<String, String> from) {
		return postForms(url, from, Maps.newHashMap());
	}
	
	/**
	 * 创建HttpClient
	 * 
	 * @return
	 */
	private static CloseableHttpClient createCookiesHttpClient() {

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(10000)  
                       .setConnectionRequestTimeout(10000).build();
        // 设置默认跳转以及存储cookie  
        CloseableHttpClient httpClient = HttpClientBuilder.create()  
                     .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())  
                     .setRedirectStrategy(new DefaultRedirectStrategy()).setDefaultRequestConfig(requestConfig)  
                     .setDefaultCookieStore(new BasicCookieStore()).build(); 
		return httpClient;
	}
	
	
	
	/**
	 * 关闭资源
	 * @param streams      
	 * void
	 */
	private static void closeStream(Closeable... streams) {
		for(Closeable stream:streams){
			if(stream!=null){
				try {
					stream.close();
				} catch (IOException e) {
					logger.error("资源关闭异常",e);
				}
			}
			
		}
	}
	/**
	 * 获取响应消息实体  
	 * @param httpResponse
	 * @return
	 */
	private static String toString(CloseableHttpResponse httpResponse){  
    	try{
        	int statusCode = httpResponse.getStatusLine().getStatusCode();
        	if(statusCode!=HttpStatus.SC_OK){
	        	logger.error("状态值:{}",statusCode); 
        		return null;
        	}
    		HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity,CharEncoding.UTF_8);  
    	}catch(Exception e){
    		logger.error("http返回数据转字符出现异常");  
    	}
    	return null;
        
    }  
}
