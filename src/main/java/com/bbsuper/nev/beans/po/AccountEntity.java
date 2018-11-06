package com.bbsuper.nev.beans.po;

import com.bbsuper.nev.beans.po.common.Entity;
import com.bbsuper.nev.beans.vo.user.UserInfo;
/**
 * 账户信息
 * @author liwei
 * @date: 2018年11月1日 下午2:57:45
 *
 */
public class AccountEntity extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9047438461996662219L;

	/**
	 * 账号
	 */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 角色id
     */
    private Long roleId;
    
    /**
     * 城市id
     */
    private Long cityId;

    /**
     * 职位
     */
    private String position;

    /**
     * 状态
     */
    private Status status;
    
    public enum Status{
    	NORMAL,DELETE,DISABLED;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
    
    public UserInfo toInfo(){
    	UserInfo userInfo = new UserInfo();
    	userInfo.setId(getId());
    	userInfo.setAccount(account);
    	userInfo.setPassword(password);
    	userInfo.setName(name);
    	userInfo.setCityId(cityId);
    	userInfo.setMobile(mobile);
    	userInfo.setStatus(status);
    	userInfo.setPosition(position);
    	return userInfo;
    }

}