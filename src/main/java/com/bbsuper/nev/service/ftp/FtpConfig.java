package com.bbsuper.nev.service.ftp;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * ftp配置参数
 * @author liwei
 * @date: 2018年11月6日 下午1:11:19
 *
 */
@Component
public class FtpConfig {
	
	@Value("${ftp.host}")
	private String host;
	
	@Value("${ftp.port}")
	private int port;
	
	@Value("${ftp.user}")
	private String user;
	
	@Value("${ftp.password}")
	private String password;
	
	@Value("${ftp.maxTotal}")
	private int maxTotal;
	
	@Value("${ftp.accessServer}")
	private String accessServer;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public String getAccessServer() {
		return accessServer;
	}

	public void setAccessServer(String accessServer) {
		this.accessServer = accessServer;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	} 
	
	public GenericObjectPoolConfig toObjectPoolConfig(){
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		//最大数量
		config.setMaxTotal(maxTotal);
		//最大空闲数
		config.setMaxIdle(5);
		//最少空闲数
		config.setMaxIdle(5);
		//池无空闲资源时，最大等待时间
		config.setMaxWaitMillis(30*1000);
		//池无空闲资源时，是否阻塞等待
		config.setBlockWhenExhausted(true);
		//取出对象时验证连接可用性
		config.setTestOnBorrow(true);
		//创建对象时验证连接可用性
		config.setTestOnCreate(true);
		//归还对象时验证连接可用性
		config.setTestOnReturn(true);
		return config;
	} 
	

}
