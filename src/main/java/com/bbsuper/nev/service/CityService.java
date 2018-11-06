package com.bbsuper.nev.service;

import java.util.List;

import com.bbsuper.nev.beans.dto.city.CityCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.po.CityEntity;
import com.bbsuper.nev.beans.vo.city.CityInfo;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.customer.CustomerInfo;
import com.bbsuper.nev.beans.vo.user.UserInfo;

/**
 * 城市相关服务
 * 
 * @author liwei
 * @date: 2018年10月26日 下午3:48:15
 *
 */
public interface CityService {
	/**
	 * 修改状态
	 * @param parseLong
	 * @param disabled
	 * @return
	 */
	ResultData<Void> modifyStatus(long parseLong, CityEntity.Status status);
	/**
	 * 查询城市列表
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<CityInfo>> queryList(PaginationCondition<CityCondition> condition);
	
	/**
	 * 查询启用的城市列表
	 * @return
	 */
	ResultData<List<CityInfo>> normalList();
	
    void addCity(UserInfo userInfo);
	
	void addCity(List<UserInfo> userInfos);
	
	void addCCity(List<CustomerInfo> list);
	
	void addCity(CustomerInfo customerInfo);

}
