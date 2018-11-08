package com.bbsuper.nev.beans.vo.finance;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.CustomerEntity;
import com.bbsuper.nev.beans.po.RefundRecordsEntity.Status;
import com.bbsuper.nev.beans.vo.vehicle.type.BaseVehicleType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 退款记录
 * @author liwei
 * @date: 2018年10月31日 下午6:45:25
 *
 */
@ApiModel(description = "退款记录")
public class RefundInfo extends BaseVehicleType{
	
	@ApiModelProperty("id")
	private Long id;
	
	@ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String mobile;
    
    @ApiModelProperty("客户状态")
    private CustomerEntity.Status customerStatus;
    
	@ApiModelProperty("车牌号")
    private String plateNumbers;
	
    @ApiModelProperty("退款金额")
    private BigDecimal amount;
	
    @ApiModelProperty("退款原因")
    private String reason;
    
    @ApiModelProperty("状态")
    private Status status;
    
    @ApiModelProperty("申请日期，格式：yyyy-MM-dd")
    private String date;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public CustomerEntity.Status getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(CustomerEntity.Status customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getPlateNumbers() {
		return plateNumbers;
	}

	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	
	
    
    

}
