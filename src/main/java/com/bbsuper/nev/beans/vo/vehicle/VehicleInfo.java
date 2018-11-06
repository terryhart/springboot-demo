package com.bbsuper.nev.beans.vo.vehicle;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 车辆信息
 * @author liwei
 * @date: 2018年10月29日 下午2:32:46
 *
 */
@ApiModel("车辆信息")
public class VehicleInfo {
	
	@ApiModelProperty("id")
	private Long id;
	
	@ApiModelProperty("车型id")
	private Long vehicleTypeId;
	
	@ApiModelProperty("车型")
    private String trademark;
	
	@ApiModelProperty("车牌号")
	private String plateNumbers;
	
	@ApiModelProperty("分销商")
	private String distributor;
	
	@ApiModelProperty("售价")
	private BigDecimal price;
	
	@ApiModelProperty("成交价")
	private BigDecimal closingCost;
    
	@ApiModelProperty("添加日期，格式yyyy-MM-dd")
    private String addDate;
	
	@ApiModelProperty("销售日期，格式yyyy-MM-dd")
    private String soldDate;
	
	@ApiModelProperty("退回日期，格式yyyy-MM-dd")
    private String backDate;
	
	@ApiModelProperty("退回原因")
	private String returnReason;
	
	@ApiModelProperty("销售姓名")
    private String accountName;
	
	@ApiModelProperty("客户姓名")
    private String customerName;

	@ApiModelProperty("客户手机号")
    private String customerMobile;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}


	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}


	public String getTrademark() {
		return trademark;
	}


	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}


	public String getPlateNumbers() {
		return plateNumbers;
	}


	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}


	public String getDistributor() {
		return distributor;
	}


	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public BigDecimal getClosingCost() {
		return closingCost;
	}


	public void setClosingCost(BigDecimal closingCost) {
		this.closingCost = closingCost;
	}


	public String getAddDate() {
		return addDate;
	}


	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}


	public String getSoldDate() {
		return soldDate;
	}


	public void setSoldDate(String soldDate) {
		this.soldDate = soldDate;
	}


	public String getBackDate() {
		return backDate;
	}


	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}


	public String getReturnReason() {
		return returnReason;
	}


	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}


	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerMobile() {
		return customerMobile;
	}


	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
