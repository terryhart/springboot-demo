package com.bbsuper.nev.beans.dto.customer;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.bbsuper.nev.beans.po.CustomerEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 客户查询条件
 * @author liwei
 * @date: 2018年10月30日 下午3:16:02
 *
 */
@ApiModel(description = "客户查询条件")
public class CustomerCondition {
	
	@ApiModelProperty(value= "状态",hidden = true)
	private List<CustomerEntity.Status> statuss;
	
	@ApiModelProperty("用户姓名、手机号")
	private String matching;
	
	@ApiModelProperty(value = "城市ID" ,hidden = true)
	private Long cityId;
	
	@ApiModelProperty(value = "销售ID" ,hidden = true)
	private Long saleId;

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public List<CustomerEntity.Status> getStatuss() {
		return statuss;
	}

	public void setStatuss(List<CustomerEntity.Status> statuss) {
		this.statuss = statuss;
	}

	public String getMatching() {
		return matching;
	}

	public void setMatching(String matching) {
		this.matching = matching;
	}

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	
	
}
