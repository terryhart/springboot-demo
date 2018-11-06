package com.bbsuper.nev.beans.dto.customer;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 放弃客户查询条件
 * @author liwei
 * @date: 2018年10月30日 下午3:16:02
 *
 */
@ApiModel(description = "放弃客户查询条件")
public class DiscardCustomerCondition extends CustomerCondition{
	
	@ApiModelProperty("意向车型,格式：id,车型")
	private Long intentionVehicleTypeId;
	
	@ApiModelProperty("保险日期起，格式：yyyy-MM-dd")
	private String insureStartDate;
	
	@ApiModelProperty("保险日期止，格式：yyyy-MM-dd")
	private String insureEndDate;
	

	public Long getIntentionVehicleTypeId() {
		return intentionVehicleTypeId;
	}

	public void setIntentionVehicleTypeId(Long intentionVehicleTypeId) {
		this.intentionVehicleTypeId = intentionVehicleTypeId;
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
