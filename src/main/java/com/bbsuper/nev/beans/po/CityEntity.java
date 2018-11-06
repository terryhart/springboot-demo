package com.bbsuper.nev.beans.po;


import java.text.SimpleDateFormat;

import com.bbsuper.nev.beans.po.common.Entity;
import com.bbsuper.nev.beans.vo.city.CityInfo;
/**
 * 城市
 * @author liwei
 * @date: 2018年11月1日 下午3:00:12
 *
 */
public class CityEntity extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7956907187462185036L;

	/**
	 * 省份
	 */
    private String province;

    /**
     * 城市
     */
    private String city;
    
    /**
     * 车牌号
     */
    private String plateNumbers;
    
    /**
     * 状态
     */
    private Status status;
    
    public enum Status{
    	NORMAL,DISABLED;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPlateNumbers() {
		return plateNumbers;
	}

	public void setPlateNumbers(String plateNumbers) {
		this.plateNumbers = plateNumbers;
	}

	public CityInfo toInfo() {
		CityInfo cityInfo = new CityInfo();
		cityInfo.setId(getId());
		cityInfo.setProvince(province);
		cityInfo.setCity(city);
		cityInfo.setPlateNumbers(plateNumbers);
		cityInfo.setStatus(status);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String enabledDate = dateFormat.format(getUpdateTime());
		cityInfo.setEnabledDate(enabledDate);
		return cityInfo;
	}
    
    
}