package com.bbsuper.nev.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bbsuper.nev.beans.dto.customer.ChangeVehicleDto;
import com.bbsuper.nev.beans.dto.customer.EditCustomerDto;
import com.bbsuper.nev.beans.dto.customer.FollowCustomerDto;
import com.bbsuper.nev.beans.dto.customer.RefundDto;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.po.CustomerEntity.Status;
import com.bbsuper.nev.beans.po.FollowRecordsEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.customer.FollowRecordsInfo;
import com.bbsuper.nev.dao.mapping.FollowRecordsEntityMapper;
import com.bbsuper.nev.service.FollowRecordsService;
import com.bbsuper.nev.utils.ControllerUtil;
/**
 * 
 * @author liwei
 * @date: 2018年11月7日 上午10:43:54
 *
 */
@Service
public class FollowRecordsServiceImpl implements FollowRecordsService{
	
	@Resource
	private FollowRecordsEntityMapper followRecordsEntityMapper;

	@Override
	public ResultData<PaginationResult<FollowRecordsInfo>> followRecords(PaginationCondition<Long> condition) {
		List<FollowRecordsInfo> list = followRecordsEntityMapper.queryListByCondition(condition);
		int count = followRecordsEntityMapper.queryCountByCondition(condition);
		PaginationResult<FollowRecordsInfo> instance = PaginationResult.getInstance(list, condition.getPageInfo(), count);
		return ResultData.getInstance(instance);
	}

	@Override
	public void generateDiscardRecords(EditCustomerDto editCustomerDto) {
		FollowRecordsEntity entity = new FollowRecordsEntity();
		entity.setStatus(FollowRecordsEntity.Status.DISCARD);
		entity.setCustomerId(editCustomerDto.getId());
		entity.setRemark(editCustomerDto.getDiscardReason());
		entity.setSalesmanId(ControllerUtil.getCurrentUser().getId());
		followRecordsEntityMapper.insert(entity);
		
	}

	@Override
	public void generateFollowRecords(FollowCustomerDto followCustomerDto) {
		FollowRecordsEntity entity = new FollowRecordsEntity();
		int code = followCustomerDto.getStatus().getCode();
		entity.setStatus(FollowRecordsEntity.Status.getByCode(code));
		entity.setCustomerId(followCustomerDto.getId());
		entity.setRemark(followCustomerDto.getRemark());
		entity.setKeyword(followCustomerDto.getKeyword());
		entity.setSalesmanId(ControllerUtil.getCurrentUser().getId());
		followRecordsEntityMapper.insert(entity);
		
	}
	@Override
	public void generateKeyword(FollowCustomerDto followCustomerDto) {
		StringBuilder stringBuilder = new StringBuilder();
		Status status = followCustomerDto.getStatus();
		if(status==Status.FOLLOW_1||status==Status.FOLLOW_3){
			FollowRecordsEntity.Status byCode = FollowRecordsEntity.Status.getByCode(status.getCode());
			int count = followRecordsEntityMapper.queryCountByStatus(byCode,followCustomerDto.getId());
			String countStr = toHanzi(count+1);
			stringBuilder.append(countStr).append("次跟进,");
		}
		if(StringUtils.isNotEmpty(followCustomerDto.getKeyword())){
			stringBuilder.append(followCustomerDto.getKeyword());
		}
		followCustomerDto.setKeyword(stringBuilder.toString());
	}

	private static String toHanzi(int count) {
		//适用于两位数
		String[] ss = {"一","二","三","四","五","六","七","八","九"};
		String str = String.valueOf(count);
		int length = str.length();
		if(length==1){
			int parseInt = Integer.parseInt(str);
			return ss[parseInt-1];
		}
		if(length==2){
			int charAt = str.charAt(0)-'0';
			String string = "";
			if(charAt!=1){
				string+=ss[charAt-1];
			}
			string+="十";
			int charAt1 = str.charAt(1)-'0';
			if(charAt1>0){
				string+=ss[charAt1-1];
			}
			return string;
		}
		return "";
	}

	@Override
	public void generateAfterSaleRecords(ChangeVehicleDto changeVehicleDto) {
		FollowRecordsEntity entity = new FollowRecordsEntity();
		entity.setStatus(FollowRecordsEntity.Status.AFTER_SALE);
		entity.setCustomerId(changeVehicleDto.getId());
		entity.setRemark(changeVehicleDto.getRemark());
		entity.setKeyword("更换车辆,"+changeVehicleDto.getPlateNumbers());
		entity.setSalesmanId(ControllerUtil.getCurrentUser().getId());
		followRecordsEntityMapper.insert(entity);
		
	}

	@Override
	public void generateAfterSaleRecords(RefundDto refundDto) {
		FollowRecordsEntity entity = new FollowRecordsEntity();
		entity.setStatus(FollowRecordsEntity.Status.AFTER_SALE);
		entity.setCustomerId(refundDto.getId());
		entity.setRemark(refundDto.getRemark());
		entity.setKeyword("收车退款,"+refundDto.getKeyword());
		entity.setSalesmanId(ControllerUtil.getCurrentUser().getId());
		followRecordsEntityMapper.insert(entity);
		
	}

}
