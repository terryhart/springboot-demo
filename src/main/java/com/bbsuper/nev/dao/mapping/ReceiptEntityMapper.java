package com.bbsuper.nev.dao.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbsuper.nev.beans.dto.finance.RecordsCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.po.ReceiptEntity;
import com.bbsuper.nev.beans.vo.finance.ReceiptInfo;
/**
 * 
 * @author liwei
 * @date: 2018年11月7日 上午10:42:58
 *
 */
@Mapper
public interface ReceiptEntityMapper {

    int insert(ReceiptEntity record);

    ReceiptEntity selectById(Long id);

    int updateById(ReceiptEntity record);
    
	List<ReceiptInfo> queryListByCondition(PaginationCondition<RecordsCondition> queryCondition);

	int queryCountByCondition(PaginationCondition<RecordsCondition> queryCondition);
}