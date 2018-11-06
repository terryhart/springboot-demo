package com.bbsuper.nev.beans.dto.customer;

import com.bbsuper.nev.beans.po.CustomerEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 意向客户查询条件
 * @author liwei
 * @date: 2018年10月30日 下午3:16:02
 *
 */
@ApiModel(description = "意向客户查询条件")
public class IntentionCustomerCondition extends CustomerCondition{
	
	@ApiModelProperty("来源")
	private CustomerEntity.Source source;
	
	@ApiModelProperty("申请日期起，格式：yyyy-MM-dd")
	private String applyStartDate;
	
	@ApiModelProperty("申请日期止，格式：yyyy-MM-dd")
	private String applyEndDate;

	public CustomerEntity.Source getSource() {
		return source;
	}

	public void setSource(CustomerEntity.Source source) {
		this.source = source;
	}

	public String getApplyStartDate() {
		return applyStartDate;
	}

	public void setApplyStartDate(String applyStartDate) {
		this.applyStartDate = applyStartDate;
	}

	public String getApplyEndDate() {
		return applyEndDate;
	}

	public void setApplyEndDate(String applyEndDate) {
		this.applyEndDate = applyEndDate;
	}
	
	
	
}
