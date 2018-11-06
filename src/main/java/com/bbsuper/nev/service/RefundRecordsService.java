package com.bbsuper.nev.service;

import com.bbsuper.nev.beans.dto.customer.RefundDto;
import com.bbsuper.nev.beans.dto.finance.RecordsCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.po.RefundRecordsEntity.Status;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.finance.RefundInfo;

/**
 * 退款记录
 * @author liwei
 * @date: 2018年10月31日 下午5:34:07
 *
 */
public interface RefundRecordsService {

	/**
	 * 生成退款记录
	 * @param refundDto
	 */
	void generateReceipt(RefundDto refundDto,Long vehicleId);
	
	/**
	 * 查询退款记录
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<RefundInfo>> queryList(PaginationCondition<RecordsCondition> condition);
	/**
	 * 修改退款记录的状态
	 * @param parseLong
	 * @param refund
	 * @return
	 */
	ResultData<Void> modifyStatus(long id, Status status);

}
