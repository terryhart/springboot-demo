package com.bbsuper.nev.beans.dto.customer;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.CustomerEntity.Status;
import com.bbsuper.nev.beans.po.ReceiptEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 跟进客户
 * @author liwei
 * @date: 2018年10月31日 下午1:32:02
 *
 */
@ApiModel(description = "跟进客户")
public class FollowCustomerDto {
	
	@NotNull
	@ApiModelProperty("客户id")
	private Long id;
	
	@NotNull
	@ApiModelProperty("跟进状态")
	private Status status;
	
	@ApiModelProperty("意向车型id")
	private Long intentionVehicleTypeId;
	
	@ApiModelProperty("关键词")
	private String keyword;
	
	@ApiModelProperty("跟进备注")
	private String remark;
	
	@ApiModelProperty("车辆成交价")
	private BigDecimal closingCost;
	
	@ApiModelProperty("金额，定金或者全款或者首付")
	private BigDecimal money;
	
	@ApiModelProperty("金额类型，定金或者全款或者首付")
	private ReceiptEntity.Type moneyType;
	
	@ApiModelProperty("车牌号")
	private String plateNumbers;
	
	@ApiModelProperty("保险日期，格式：yyyy-MM-dd")
	private String insureDate;
	
	@ApiModelProperty("合同链接，多个用逗号隔开")
	private String contractLink;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	public Long getIntentionVehicleTypeId() {
		return intentionVehicleTypeId;
	}

	public void setIntentionVehicleTypeId(Long intentionVehicleTypeId) {
		this.intentionVehicleTypeId = intentionVehicleTypeId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getClosingCost() {
		return closingCost;
	}

	public void setClosingCost(BigDecimal closingCost) {
		this.closingCost = closingCost;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public ReceiptEntity.Type getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(ReceiptEntity.Type moneyType) {
		this.moneyType = moneyType;
	}

	public String getPlateNumbers() {
		return plateNumbers;
	}

	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}

	public String getInsureDate() {
		return insureDate;
	}

	public void setInsureDate(String insureDate) {
		this.insureDate = insureDate;
	}

	public String getContractLink() {
		return contractLink;
	}

	public void setContractLink(String contractLink) {
		this.contractLink = contractLink;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
