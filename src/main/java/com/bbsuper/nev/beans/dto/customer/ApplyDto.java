package com.bbsuper.nev.beans.dto.customer;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.CustomerEntity;
import com.bbsuper.nev.beans.po.CustomerEntity.Source;
import com.bbsuper.nev.beans.po.CustomerEntity.Status;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 客户报名请求
 * @author liwei
 * @date: 2018年11月1日 下午2:24:40
 *
 */
@ApiModel(description = "客户报名")
public class ApplyDto {
	
	@NotEmpty
	@ApiModelProperty("姓名")
	private String name;
	
	@NotEmpty
	@ApiModelProperty("手机号")
	private String mobile;
	
	@ApiModelProperty("来源")
	private String source;
	
	@ApiModelProperty("留言")
	private String message;

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public CustomerEntity toEntity() {
		CustomerEntity entity = new CustomerEntity();
		entity.setName(name);
		entity.setMobile(mobile);
		entity.setCityId(0L);
		entity.setMessage(message);
		if(StringUtils.isEmpty(source)){
			entity.setSource(Source.PC);
		}else{
			entity.setSource(Source.getByDesc(source));
		}
		entity.setStatus(Status.INTERESTING);
		return entity;
	}
	
	

}
