package com.bbsuper.nev.beans.dto.city;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.CityEntity.Status;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 城市查询条件
 * @author liwei
 * @date: 2018年10月26日 下午4:04:47
 *
 */
@ApiModel(description = "城市查询条件")
public class CityCondition {
	
	@ApiModelProperty("省份")
    private String province;

	@ApiModelProperty("状态：NORMAL,DISABLED")
    private Status status;


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
    
    

}
