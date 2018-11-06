package com.bbsuper.nev.beans.dto.finance;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.CustomerEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 收款查询条件
 * @author liwei
 * @date: 2018年10月31日 下午6:35:45
 *
 */
@ApiModel(description = "记录查询条件")
public class RecordsCondition {
	
	@ApiModelProperty("日期起，格式：yyyy-MM-dd")
	private String startDate;
	
	@ApiModelProperty("日期止，格式：yyyy-MM-dd")
	private String endDate;
	
	@ApiModelProperty("客户状态")
	private CustomerEntity.Status status;
	
	@ApiModelProperty("车牌号、姓名、手机号")
	private String matching;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public CustomerEntity.Status getStatus() {
		return status;
	}

	public void setStatus(CustomerEntity.Status status) {
		this.status = status;
	}

	public String getMatching() {
		return matching;
	}

	public void setMatching(String matching) {
		this.matching = matching;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
