package com.bbsuper.nev.beans.dto.user;

import com.bbsuper.nev.beans.po.AccountRoleEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 账号查询条件
 * @author liwei
 * @date: 2018年10月29日 上午11:13:40
 *
 */
@ApiModel(description = "账号查询条件")
public class AccountCondition {
	
	@ApiModelProperty("状态NORMAL,DISABLED")
	private AccountRoleEntity.Status status;
	
	@ApiModelProperty("角色id")
	private String roleId;
	
	@ApiModelProperty("账号、姓名、手机号")
	private String matching;
	
	

	public String getMatching() {
		return matching;
	}

	public void setMatching(String matching) {
		this.matching = matching;
	}

	public AccountRoleEntity.Status getStatus() {
		return status;
	}

	public void setStatus(AccountRoleEntity.Status status) {
		this.status = status;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	

}
