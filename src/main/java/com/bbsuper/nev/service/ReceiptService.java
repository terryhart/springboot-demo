package com.bbsuper.nev.service;

import com.bbsuper.nev.beans.dto.customer.FollowCustomerDto;
import com.bbsuper.nev.beans.dto.finance.RecordsCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.finance.ReceiptInfo;

/**
 * 收款记录
 * @author liwei
 * @date: 2018年10月31日 下午5:26:05
 *
 */
public interface ReceiptService {

	/**
	 * 生成收款记录
	 * @param followCustomerDto
	 */
	void generateReceipt(FollowCustomerDto followCustomerDto,Long vehicleId);

	/**
	 * 查询收款记录
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<ReceiptInfo>> queryList(PaginationCondition<RecordsCondition> condition);

}
