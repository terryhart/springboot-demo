package com.bbsuper.nev.beans.dto.user;

import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 修改密码
 * @author liwei
 * @date: 2018年10月29日 下午1:46:44
 *
 */
@ApiModel(description = "修改密码")
public class ChangePasswordDto {
	
	@NotEmpty
	@ApiModelProperty("原密码")
	private String oldPwd;
	
	@NotEmpty
	@ApiModelProperty("新密码")
	private String newPwd;


	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	
	

}
