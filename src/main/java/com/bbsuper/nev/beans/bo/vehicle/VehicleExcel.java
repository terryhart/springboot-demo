package com.bbsuper.nev.beans.bo.vehicle;

import com.alibaba.fastjson.JSON;

/**
 * 车辆信息excel格式
 * @author liwei
 * @date: 2018年10月30日 下午1:51:06
 *
 */
public class VehicleExcel {
	
	/**
	 * 车型
	 */
    private String trademark;
	
	/**
	 * 分销商
	 */
	private String distributor;
	
	/**
	 * 车牌号
	 */
	private String plateNumbers;
	
	/**
	 * 售价
	 */
	private String price;

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getPlateNumbers() {
		return plateNumbers;
	}

	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}
