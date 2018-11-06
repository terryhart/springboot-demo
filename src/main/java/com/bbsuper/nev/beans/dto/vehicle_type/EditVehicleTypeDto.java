package com.bbsuper.nev.beans.dto.vehicle_type;

import javax.validation.constraints.NotNull;

import com.bbsuper.nev.beans.po.VehicleTypeEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 编辑车辆类目
 * @author liwei
 * @date: 2018年10月29日 下午3:06:51
 *
 */
@ApiModel(description = "编辑车辆类目")
public class EditVehicleTypeDto extends VehicleTypeDto{
	
	@NotNull
	@ApiModelProperty("id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public VehicleTypeEntity toEntity(){
		VehicleTypeEntity entity = new VehicleTypeEntity();
		entity.setId(id);
		entity.setDistributor(getDistributor());
		entity.setTrademark(getTrademark());
		return entity;
	}

}
