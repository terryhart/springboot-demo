package com.bbsuper.nev.beans.dto.customer;

import com.bbsuper.nev.beans.po.CustomerEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 已售客户查询条件
 * @author liwei
 * @date: 2018年10月30日 下午3:16:02
 *
 */
@ApiModel(description = "已售客户查询条件")
public class SoldCustomerCondition extends CustomerCondition{

	@ApiModelProperty("购买方式")
	private CustomerEntity.BuyWay buyWay;
	
	@ApiModelProperty("购买车型ID")
	private Long vehicleTypeId;
	
	@ApiModelProperty("保险日期起，格式：yyyy-MM-dd")
	private String insureStartDate;
	
	@ApiModelProperty("保险日期止，格式：yyyy-MM-dd")
	private String insureEndDate;

	public CustomerEntity.BuyWay getBuyWay() {
		return buyWay;
	}

	public void setBuyWay(CustomerEntity.BuyWay buyWay) {
		this.buyWay = buyWay;
	}

	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getInsureStartDate() {
		return insureStartDate;
	}

	public void setInsureStartDate(String insureStartDate) {
		this.insureStartDate = insureStartDate;
	}

	public String getInsureEndDate() {
		return insureEndDate;
	}

	public void setInsureEndDate(String insureEndDate) {
		this.insureEndDate = insureEndDate;
	}
	
	

}
