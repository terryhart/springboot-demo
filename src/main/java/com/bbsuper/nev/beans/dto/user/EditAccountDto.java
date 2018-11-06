package com.bbsuper.nev.beans.dto.user;

import javax.validation.constraints.NotNull;

import com.bbsuper.nev.beans.po.AccountEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 编辑账号
 * @author liwei
 * @date: 2018年10月29日 下午12:42:36
 *
 */
@ApiModel(description = "编辑账号")
public class EditAccountDto extends AccountDto{
	
	@NotNull
	@ApiModelProperty("id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AccountEntity toEntity() {
		AccountEntity entity = new AccountEntity();
		entity.setId(id);
		entity.setAccount(getAccount());
		entity.setName(getName());
		entity.setMobile(getMobile());
		entity.setPosition(getPosition());
		entity.setRoleId(getRoleId());
		entity.setCityId(getCityId());
		return entity;
	}

}
