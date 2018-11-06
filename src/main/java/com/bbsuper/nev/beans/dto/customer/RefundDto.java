package com.bbsuper.nev.beans.dto.customer;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 退款
 * @author liwei
 * @date: 2018年10月31日 下午5:36:15
 *
 */
@ApiModel(description = "收车退款")
public class RefundDto {
	
	@NotNull
	@ApiModelProperty("客户id")
	private Long id;
	
	@NotEmpty
	@ApiModelProperty("关键词")
	private String keyword;
	
	@NotNull
	@ApiModelProperty("金额")
	private BigDecimal money;
	
	@ApiModelProperty("跟进备注")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
