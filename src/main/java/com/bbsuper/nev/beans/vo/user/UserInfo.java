package com.bbsuper.nev.beans.vo.user;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.AccountEntity;
import com.bbsuper.nev.beans.po.AccountRoleEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户详情
 * @author liwei
 * @date: 2018年10月25日 上午11:53:28
 *
 */
@ApiModel("用户详情")
public class UserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4855576466108473998L;
	
	@ApiModelProperty("id")
	private Long id;

	@ApiModelProperty("账号")
	private String account;

	@ApiModelProperty(value = "密码，不返回到前端",hidden = true)
    private String password;
	
	@ApiModelProperty("权限code，多个用逗号分隔")
	private String privilegeCode;

	@ApiModelProperty("姓名")
    private String name;

	@ApiModelProperty("手机号")
    private String mobile;
	
	@ApiModelProperty("角色id")
    private String roleId;
	
	@ApiModelProperty("角色")
    private String roleName;
	
	@ApiModelProperty("角色状态")
    private AccountRoleEntity.Status roleStatus;
	
	@ApiModelProperty("城市id")
    private Long cityId;
	
	/**
	 * 缓存字段，按需要获取
	 */
	@ApiModelProperty("城市名")
    private String cityName;

	@ApiModelProperty("职位")
    private String position;

	@ApiModelProperty("状态 NORMAL,DISABLED")
    private AccountEntity.Status status;
	
	
    
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
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
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public AccountEntity.Status getStatus() {
		return status;
	}
	public void setStatus(AccountEntity.Status status) {
		this.status = status;
	}
	
	public String getPrivilegeCode() {
		return privilegeCode;
	}
	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public AccountRoleEntity.Status getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(AccountRoleEntity.Status roleStatus) {
		this.roleStatus = roleStatus;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
    
    

}
