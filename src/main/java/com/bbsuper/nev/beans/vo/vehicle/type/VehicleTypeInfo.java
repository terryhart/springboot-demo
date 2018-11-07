package com.bbsuper.nev.beans.vo.vehicle.type;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.VehicleTypeEntity.Status;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author liwei
 * @date: 2018年10月29日 下午2:32:46
 *
 */
@ApiModel("车辆类目")
public class VehicleTypeInfo {
	
	@ApiModelProperty("id")
	private Long id;
	
	@ApiModelProperty("分销商")
	private String distributor;

	@ApiModelProperty("车辆品牌")
    private String trademark;

	@ApiModelProperty("状态")
    private Status status;
    
	@ApiModelProperty("添加日期，格式yyyy-MM-dd")
    private String createDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
