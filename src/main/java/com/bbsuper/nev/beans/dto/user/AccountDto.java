package com.bbsuper.nev.beans.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.AccountEntity;
import com.bbsuper.nev.utils.EncryptionUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 账号
 * @author liwei
 * @date: 2018年10月26日 下午2:04:22
 *
 */
@ApiModel(description = "账号")
public class AccountDto {
	
	@NotEmpty
	@ApiModelProperty("账号")
	private String account;

	@NotEmpty
    @ApiModelProperty("姓名")
    private String name;

	@NotEmpty
    @ApiModelProperty("手机号")
    private String mobile;

	@NotNull
    @ApiModelProperty("角色id")
    private Long roleId;
    
	@NotNull
    @ApiModelProperty("城市id")
    private Long cityId;

    @ApiModelProperty("职位")
    private String position;


	public String getAccount() {
		return account;
	}




	public void setAccount(String account) {
		this.account = account;
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




	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}




	public AccountEntity toAddEntity() {
		AccountEntity entity = new AccountEntity();
		entity.setAccount(getAccount());
		entity.setName(getName());
		entity.setMobile(getMobile());
		entity.setPosition(getPosition());
		entity.setRoleId(getRoleId());
		entity.setCityId(getCityId());
		entity.setPassword(EncryptionUtils.md5("123456"));
		entity.setStatus(AccountEntity.Status.NORMAL);
		return entity;
	}
    
    

}
