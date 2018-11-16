package com.bbsuper.nev.beans.dto.customer;

import com.bbsuper.nev.beans.po.CustomerEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 跟进客户查询条件
 * @author liwei
 * @date: 2018年10月30日 下午3:16:02
 *
 */
@ApiModel(description = "跟进客户查询条件")
public class FollowCustomerCondition extends CustomerCondition{
	
	@ApiModelProperty("跟进状态")
	private CustomerEntity.Status status;
	
	@ApiModelProperty("跟进日期起，格式：yyyy-MM-dd")
	private String followStartDate;
	
	@ApiModelProperty("跟进日期止，格式：yyyy-MM-dd")
	private String followEndDate;

	public CustomerEntity.Status getStatus() {
		return status;
	}

	public void setStatus(CustomerEntity.Status status) {
		this.status = status;
	}

	public String getFollowStartDate() {
		return followStartDate;
	}

	public void setFollowStartDate(String followStartDate) {
		this.followStartDate = followStartDate;
	}

	public String getFollowEndDate() {
		return followEndDate;
	}

	public void setFollowEndDate(String followEndDate) {
		this.followEndDate = followEndDate+" 24:00:00";
	}
	
	
	

}
