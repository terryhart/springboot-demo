package com.bbsuper.nev.beans.po;


import java.text.SimpleDateFormat;

import com.bbsuper.nev.beans.po.common.Entity;
import com.bbsuper.nev.beans.vo.vehicle_type.VehicleTypeInfo;
/**
 * 车辆类目
 * @author liwei
 * @date: 2018年11月1日 下午3:32:48
 *
 */
public class VehicleTypeEntity extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4331206910046682738L;

	/**
	 * 分销商
	 */
    private String distributor;

    /**
     * 车辆品牌
     */
    private String trademark;

    /**
     * 状态
     */
    private Status status;
    
    public enum Status{
    	NORMAL,DISABLED;
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

	public VehicleTypeInfo toInfo() {
		VehicleTypeInfo info = new VehicleTypeInfo();
		info.setId(getId());
		info.setDistributor(distributor);
		info.setTrademark(trademark);
		info.setStatus(status);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
		String format = simpleDateFormat.format(getCreateTime());
		info.setCreateDate(format);
		return info;
	}

}