package com.bbsuper.nev.utils;

import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 加密工具类
 * @author liwei
 * @date: 2018年10月25日 上午10:46:16
 *
 */
public class EncryptionUtils {
	
	/**
	 * 随机字符串
	 * @param length
	 * @return
	 */
	
	public static String random(int length){
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
	/**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data){
    	try{
	        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(data.getBytes("UTF-8"));
	        StringBuilder sb = new StringBuilder();
	        for (byte item : array) {
	            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
	        }
	        return sb.toString().toUpperCase();
        }catch(Exception e){
        	e.printStackTrace();
        }
    	return "";
    }
    
    public static void main(String[] args) {
		System.out.println(MD5("123456"));
	}

    /**
     * 生成 HMACSHA256
     * @param data 待处理数据
     * @param key 密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String HMACSHA256(String data, String key){
    	try{
	        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
	        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
	        sha256_HMAC.init(secret_key);
	        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
	        StringBuilder sb = new StringBuilder();
	        for (byte item : array) {
	            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
	        }
	        return sb.toString().toUpperCase();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		return "";
	}

}
