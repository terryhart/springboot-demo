package com.bbsuper.nev.service;

import java.util.List;

import com.bbsuper.nev.beans.dto.customer.ApplyDto;
import com.bbsuper.nev.beans.dto.customer.ChangeVehicleDto;
import com.bbsuper.nev.beans.dto.customer.CustomerDto;
import com.bbsuper.nev.beans.dto.customer.DiscardCustomerCondition;
import com.bbsuper.nev.beans.dto.customer.EditCustomerDto;
import com.bbsuper.nev.beans.dto.customer.FollowCustomerCondition;
import com.bbsuper.nev.beans.dto.customer.FollowCustomerDto;
import com.bbsuper.nev.beans.dto.customer.IntentionCustomerCondition;
import com.bbsuper.nev.beans.dto.customer.RefundDto;
import com.bbsuper.nev.beans.dto.customer.SalesAccount;
import com.bbsuper.nev.beans.dto.customer.SoldCustomerCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.customer.CustomerInfo;
import com.bbsuper.nev.beans.vo.customer.FollowRecordsInfo;

/**
 * 客户管理
 * @author liwei
 * @date: 2018年10月30日 下午3:12:10
 *
 */
public interface CustomerService {

	/**
	 * 查询可分配的销售列表
	 * @param id
	 * @return
	 */
	ResultData<List<SalesAccount>> querySalesAccount(long id);
	/**
	 * 分配销售
	 * @param id
	 * @param saleId
	 * @return
	 */
	ResultData<Void> allotSale(long id, long saleId);
	/**
	 * 新增客户
	 * @param customerDto
	 * @return
	 */
	ResultData<Void> add(CustomerDto customerDto);
	/**
	 * 编辑客户
	 * @param editCustomerDto
	 * @return
	 */
	ResultData<Void> edit(EditCustomerDto editCustomerDto);
	
	/**
	 * 查询跟进记录
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<FollowRecordsInfo>> followRecords(PaginationCondition<Long> condition);
	
	/**
	 * 跟进客户
	 * @param followCustomerDto
	 * @return
	 */
	ResultData<Void> followCustomer(FollowCustomerDto followCustomerDto);
	
	/**
	 * 更换车辆
	 * @param changeVehicleDto
	 * @return
	 */
	ResultData<Void> changeVehicle(ChangeVehicleDto changeVehicleDto);
	/**
	 * 收车退款
	 * @param refundDto
	 * @return
	 */
	ResultData<Void> refund(RefundDto refundDto);

	/**
	 * 恢复被放弃的客户
	 * @param parseLong
	 * @return
	 */
	ResultData<Void> recover(long id);

	/**
	 * 客户报名
	 * @param applyDto
	 * @return
	 */
	ResultData<Void> apply(ApplyDto applyDto);
	
	/**
	 * 意向客户列表
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<CustomerInfo>> queryInterestingList(PaginationCondition<IntentionCustomerCondition> condition);

	/**
	 * 跟进客户列表
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<CustomerInfo>> queryFollowList(PaginationCondition<FollowCustomerCondition> condition);

	/**
	 * 已售客户列表
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<CustomerInfo>> querySoldList(PaginationCondition<SoldCustomerCondition> condition);

	/**
	 * 放弃客户列表
	 * @param condition
	 * @return
	 */
	ResultData<PaginationResult<CustomerInfo>> queryDiscardList(PaginationCondition<DiscardCustomerCondition> condition);

}
