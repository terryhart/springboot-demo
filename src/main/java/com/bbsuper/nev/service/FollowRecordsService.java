package com.bbsuper.nev.service;

import com.bbsuper.nev.beans.dto.customer.ChangeVehicleDto;
import com.bbsuper.nev.beans.dto.customer.EditCustomerDto;
import com.bbsuper.nev.beans.dto.customer.FollowCustomerDto;
import com.bbsuper.nev.beans.dto.customer.RefundDto;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.customer.FollowRecordsInfo;

/**
 * 跟进记录
 * @author liwei
 * @date: 2018年10月31日 上午11:25:20
 *
 */
public interface FollowRecordsService {
	
	/**
	 * 查询跟进记录列表
	 * @param condition
	 * @return
	 */

	ResultData<PaginationResult<FollowRecordsInfo>> followRecords(PaginationCondition<Long> condition);
	/**
	 * 生成跟进记录 放弃
	 * @param editCustomerDto
	 */
	void generateDiscardRecords(EditCustomerDto editCustomerDto);
	/**
	 * 生成跟进记录 跟进
	 * @param followCustomerDto
	 */
	void generateFollowRecords(FollowCustomerDto followCustomerDto);
	
	/**
	 * 生成跟进记录 售后
	 * @param changeVehicleDto
	 */
	void generateAfterSaleRecords(ChangeVehicleDto changeVehicleDto);
	
	/**
	 * 生成跟进记录 售后
	 * @param refundDto
	 */
	void generateAfterSaleRecords(RefundDto refundDto);
	
	/**
	 * 生成关键字
	 * @param followCustomerDto
	 */
	void generateKeyword(FollowCustomerDto followCustomerDto);

}
