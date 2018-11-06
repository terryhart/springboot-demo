package com.bbsuper.nev.beans.dto.vehicle_type;

import javax.validation.constraints.NotEmpty;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.VehicleTypeEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 车辆类目
 * @author liwei
 * @date: 2018年10月29日 下午3:06:41
 *
 */
@ApiModel(description = "车辆类目")
public class VehicleTypeDto {
	
	@NotEmpty
	@ApiModelProperty("分销商")
    private String distributor;

	@NotEmpty
	@ApiModelProperty("车辆品牌")
    private String trademark;

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public VehicleTypeEntity toAddEntity(){
		VehicleTypeEntity entity = new VehicleTypeEntity();
		entity.setStatus(VehicleTypeEntity.Status.NORMAL);
		entity.setDistributor(distributor);
		entity.setTrademark(trademark);
		return entity;
	}
    
    

}
