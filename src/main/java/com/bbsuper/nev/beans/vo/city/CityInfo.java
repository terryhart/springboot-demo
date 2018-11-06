package com.bbsuper.nev.beans.vo.city;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.CityEntity;
import com.bbsuper.nev.beans.po.CityEntity.Status;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 城市详情
 * @author liwei
 * @date: 2018年10月26日 下午3:57:28
 *
 */
@ApiModel("城市详情")
public class CityInfo {
	
	@ApiModelProperty("id")
	private Long id;
	
	@ApiModelProperty("省份")
    private String province;

	@ApiModelProperty("城市")
    private String city;
    
	@ApiModelProperty("车牌编号")
    private String plateNumbers;
    
	@ApiModelProperty("状态：NORMAL,DISABLED")
    private CityEntity.Status status;
    
	@ApiModelProperty("启用日期，格式yyyy-MM-dd")
    private String enabledDate;
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPlateNumbers() {
		return plateNumbers;
	}

	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getEnabledDate() {
		return enabledDate;
	}

	public void setEnabledDate(String enabledDate) {
		this.enabledDate = enabledDate;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
    
    

}
