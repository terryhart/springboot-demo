package com.bbsuper.nev.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bbsuper.nev.beans.dto.customer.RefundDto;
import com.bbsuper.nev.beans.dto.finance.RecordsCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.beans.po.RefundRecordsEntity;
import com.bbsuper.nev.beans.po.RefundRecordsEntity.Status;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.finance.RefundInfo;
import com.bbsuper.nev.dao.mapping.RefundRecordsEntityMapper;
import com.bbsuper.nev.service.RefundRecordsService;
import com.bbsuper.nev.service.VehicleTypeService;

/**
 * 退款记录
 * @author liwei
 * @date: 2018年10月31日 下午5:34:44
 *
 */
@Service
public class RefundRecordsImpl implements RefundRecordsService{

	@Resource
	private RefundRecordsEntityMapper refundRecordsEntityMapper;
	
	@Resource
	private VehicleTypeService vehicleTypeService;
	
	
	
	@Override
	public void generateReceipt(RefundDto refundDto,Long vehicleId) {
		RefundRecordsEntity entity = new RefundRecordsEntity();
		entity.setCustomerId(refundDto.getId());
		entity.setAmount(refundDto.getMoney().setScale(2, BigDecimal.ROUND_HALF_UP));
		entity.setReason("收车退款");
		entity.setVehicleId(vehicleId);
		entity.setStatus(Status.STAY_REFUND);
		
		refundRecordsEntityMapper.insert(entity);
		
	}



	@Override
	public ResultData<PaginationResult<RefundInfo>> queryList(PaginationCondition<RecordsCondition> condition) {
		List<RefundInfo> list = refundRecordsEntityMapper.queryListByCondition(condition);
		vehicleTypeService.addTTTType(list);
		int count = refundRecordsEntityMapper.queryCountByCondition(condition);
		PaginationResult<RefundInfo> instance = PaginationResult.getInstance(list, condition.getPageInfo(), count);
		return ResultData.getInstance(instance);
	}



	@Override
	public ResultData<Void> modifyStatus(long id, Status status) {
		RefundRecordsEntity entity = new RefundRecordsEntity();
		entity.setId(id);
		entity.setStatus(status);
		int updateById = refundRecordsEntityMapper.updateById(entity);
		return updateById == 1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

}
