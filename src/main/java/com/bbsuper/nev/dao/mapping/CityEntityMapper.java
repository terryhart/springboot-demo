package com.bbsuper.nev.dao.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bbsuper.nev.beans.dto.city.CityCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.po.CityEntity;
/**
 * 
 * @author liwei
 * @date: 2018年11月7日 上午10:42:28
 *
 */
@Mapper
public interface CityEntityMapper {

    int insert(CityEntity record);

    CityEntity selectById(Long id);

    int updateById(CityEntity record);

	List<CityEntity> queryListByCondition(PaginationCondition<CityCondition> queryCondition);

	int queryCountByCondition(PaginationCondition<CityCondition> queryCondition);

	List<CityEntity> queryAll();
}