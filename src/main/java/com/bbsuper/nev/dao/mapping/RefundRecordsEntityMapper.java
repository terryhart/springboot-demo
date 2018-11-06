package com.bbsuper.nev.dao.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbsuper.nev.beans.dto.finance.RecordsCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.po.RefundRecordsEntity;
import com.bbsuper.nev.beans.vo.finance.RefundInfo;

@Mapper
public interface RefundRecordsEntityMapper {

    int insert(RefundRecordsEntity record);

    RefundRecordsEntity selectById(Long id);

    int updateById(RefundRecordsEntity record);
    
	List<RefundInfo> queryListByCondition(PaginationCondition<RecordsCondition> queryCondition);

	int queryCountByCondition(PaginationCondition<RecordsCondition> queryCondition);
}