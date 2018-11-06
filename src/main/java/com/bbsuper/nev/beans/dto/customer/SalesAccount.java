package com.bbsuper.nev.beans.dto.customer;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 可分配的销售账号
 * @author liwei
 * @date: 2018年10月31日 上午9:52:14
 *
 */
@ApiModel(description = "可分配的销售账号")
public class SalesAccount {
	
	@ApiModelProperty("id")
	private Long id;
	
	@ApiModelProperty("姓名")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
