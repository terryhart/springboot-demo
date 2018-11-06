package com.bbsuper.nev.beans.dto.customer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 更换车辆
 * @author liwei
 * @date: 2018年10月31日 下午4:50:26
 *
 */
@ApiModel(description = "更换车辆")
public class ChangeVehicleDto {
	
	@NotNull
	@ApiModelProperty("客户id")
	private Long id;
	
	@NotEmpty
	@ApiModelProperty("车牌号")
	private String plateNumbers;
	
	@ApiModelProperty("跟进备注")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlateNumbers() {
		return plateNumbers;
	}

	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
