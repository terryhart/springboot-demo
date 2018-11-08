package com.bbsuper.nev.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bbsuper.nev.beans.dto.customer.FollowCustomerDto;
import com.bbsuper.nev.beans.dto.finance.RecordsCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.po.ReceiptEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.finance.ReceiptInfo;
import com.bbsuper.nev.dao.mapping.ReceiptEntityMapper;
import com.bbsuper.nev.service.ReceiptService;
import com.bbsuper.nev.service.VehicleTypeService;

/**
 * 收款记录
 * @author liwei
 * @date: 2018年10月31日 下午5:26:29
 *
 */
@Service
public class ReceiptServiceImpl implements ReceiptService{
	
	@Resource
	private ReceiptEntityMapper receiptEntityMapper;
	
	@Resource
	private VehicleTypeService vehicleTypeService;
 
	@Override
	public void generateReceipt(FollowCustomerDto followCustomerDto,Long vehicleId) {
		if(followCustomerDto.getMoneyType()!=null&&followCustomerDto.getMoney()!=null){
			ReceiptEntity entity = new ReceiptEntity();
			entity.setCustomerId(followCustomerDto.getId());
			entity.setType(followCustomerDto.getMoneyType());
			entity.setAmount(followCustomerDto.getMoney().setScale(2, BigDecimal.ROUND_HALF_UP));
			if(vehicleId!=null){
				entity.setVehicleId(vehicleId);
			}
			receiptEntityMapper.insert(entity);
		}		
		
	}

	@Override
	public ResultData<PaginationResult<ReceiptInfo>> queryList(PaginationCondition<RecordsCondition> condition) {
		List<ReceiptInfo> list = receiptEntityMapper.queryListByCondition(condition);
		vehicleTypeService.addType(list);
		int count = receiptEntityMapper.queryCountByCondition(condition);
		PaginationResult<ReceiptInfo> instance = PaginationResult.getInstance(list, condition.getPageInfo(), count);
		return ResultData.getInstance(instance);
	}

}
