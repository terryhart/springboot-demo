package com.bbsuper.nev.beans.dto.vehicle_type;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.VehicleTypeEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 车辆类目查询条件
 * @author liwei
 * @date: 2018年10月29日 下午2:27:20
 *
 */
@ApiModel(description = "车辆类目查询条件")
public class VehicleTypeCondition {
	
	@ApiModelProperty("状态NORMAL,DISABLED")
	private VehicleTypeEntity.Status status;
	
	@ApiModelProperty("车辆品牌、分销商")
	private String matching;
	
	@ApiModelProperty("开始日期，格式：yyyy-MM-dd")
	private String startDate;
	
	@ApiModelProperty("结束日期，格式：yyyy-MM-dd")
	private String endDate;

	public VehicleTypeEntity.Status getStatus() {
		return status;
	}

	public void setStatus(VehicleTypeEntity.Status status) {
		this.status = status;
	}

	public String getMatching() {
		return matching;
	}

	public void setMatching(String matching) {
		this.matching = matching;
	}

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

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
