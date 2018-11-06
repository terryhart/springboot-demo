package com.bbsuper.nev.beans.vo.finance;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.CustomerEntity.Status;
import com.bbsuper.nev.beans.po.ReceiptEntity.Type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 收款记录
 * @author liwei
 * @date: 2018年10月31日 下午6:43:59
 *
 */
@ApiModel(description = "收款记录")
public class ReceiptInfo {
	
	@ApiModelProperty("id")
	private Long id;
	
	@ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String mobile;
    
    @ApiModelProperty("客户状态")
    private Status status;
    
	@ApiModelProperty("意向车型id")
	private Long intentionVehicleTypeId;
	
	@ApiModelProperty("意向车型")
	private String intentionVehicleType;
	
	@ApiModelProperty("购买车型id")
	private Long vehicleTypeId;
	
	@ApiModelProperty("购买车型")
	private String vehicleType;
    
	@ApiModelProperty("车牌号")
    private String plateNumbers;

    @ApiModelProperty("收款金额")
    private BigDecimal amount;

    @ApiModelProperty("收款类型")
    private Type type;
    
    @ApiModelProperty("收款日期，格式：yyyy-MM-dd")
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getIntentionVehicleType() {
		return intentionVehicleType;
	}

	public void setIntentionVehicleType(String intentionVehicleType) {
		this.intentionVehicleType = intentionVehicleType;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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

	public Long getIntentionVehicleTypeId() {
		return intentionVehicleTypeId;
	}

	public void setIntentionVehicleTypeId(Long intentionVehicleTypeId) {
		this.intentionVehicleTypeId = intentionVehicleTypeId;
	}

	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
    
    

}
