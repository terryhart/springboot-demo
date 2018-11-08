package com.bbsuper.nev.beans.vo.vehicle.type;

import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 基本车型
 * @author liwei
 * @date: 2018年11月8日 下午2:33:35
 *
 */
@ApiModel("基本车型")
public class BaseVehicleType {
	
    @ApiModelProperty("意向车型id")
    private Long intentionVehicleTypeId;
    
    @ApiModelProperty("意向车型")
    private String intentionVehicleType;
    
    @ApiModelProperty("购买车型id")
    private Long vehicleTypeId;
    
    @ApiModelProperty("购买车型")
    private String vehicleType;

	public Long getIntentionVehicleTypeId() {
		return intentionVehicleTypeId;
	}

	public void setIntentionVehicleTypeId(Long intentionVehicleTypeId) {
		this.intentionVehicleTypeId = intentionVehicleTypeId;
	}

	public String getIntentionVehicleType() {
		return intentionVehicleType;
	}

	public void setIntentionVehicleType(String intentionVehicleType) {
		this.intentionVehicleType = intentionVehicleType;
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

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
    
    

}
