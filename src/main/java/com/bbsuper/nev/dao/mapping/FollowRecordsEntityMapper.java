package com.bbsuper.nev.dao.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.po.FollowRecordsEntity;
import com.bbsuper.nev.beans.po.FollowRecordsEntity.Status;
import com.bbsuper.nev.beans.vo.customer.FollowRecordsInfo;

/**
 * 
 * @author liwei
 * @date: 2018年11月7日 上午10:42:48
 *
 */
@Mapper
public interface FollowRecordsEntityMapper {

    int insert(FollowRecordsEntity record);

    FollowRecordsEntity selectById(Long id);

    int updateById(FollowRecordsEntity record);
    
	List<FollowRecordsInfo> queryListByCondition(PaginationCondition<Long> queryCondition);

	int queryCountByCondition(PaginationCondition<Long> queryCondition);

	int queryCountByStatus(@Param("status")Status status, @Param("customerId")Long customerId);

}