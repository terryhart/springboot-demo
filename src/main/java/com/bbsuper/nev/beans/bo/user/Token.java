package com.bbsuper.nev.beans.bo.user;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 用户token信息
 * @author liwei
 * @date: 2018年10月26日 上午9:07:42
 *
 */
public class Token implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7233326978782433958L;
	
	private Long id;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
    private String password;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
    
    
}
