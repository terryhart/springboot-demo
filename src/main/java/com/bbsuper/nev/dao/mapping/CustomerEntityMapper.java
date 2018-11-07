package com.bbsuper.nev.dao.mapping;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbsuper.nev.beans.dto.customer.DiscardCustomerCondition;
import com.bbsuper.nev.beans.dto.customer.FollowCustomerCondition;
import com.bbsuper.nev.beans.dto.customer.IntentionCustomerCondition;
import com.bbsuper.nev.beans.dto.customer.SoldCustomerCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.po.CustomerEntity;
import com.bbsuper.nev.beans.vo.customer.CustomerInfo;
/**
 * 
 * @author liwei
 * @date: 2018年11月7日 上午10:42:35
 *
 */
@Mapper
public interface CustomerEntityMapper {

    int insert(CustomerEntity record);

    CustomerEntity selectById(Long id);

    int updateById(CustomerEntity record);
	
	CustomerInfo queryCustomerInfoById(Long id);

	List<CustomerInfo> queryListByInteresting(PaginationCondition<IntentionCustomerCondition> condition);

	int queryCountByInteresting(PaginationCondition<IntentionCustomerCondition> condition);

	List<CustomerInfo> queryListByFollow(PaginationCondition<FollowCustomerCondition> condition);

	int queryCountByFollow(PaginationCondition<FollowCustomerCondition> condition);

	List<CustomerInfo> queryListBySold(PaginationCondition<SoldCustomerCondition> condition);

	int queryCountBySold(PaginationCondition<SoldCustomerCondition> condition);

	List<CustomerInfo> queryListByDiscard(PaginationCondition<DiscardCustomerCondition> condition);

	int queryCountByDiscard(PaginationCondition<DiscardCustomerCondition> condition);
}