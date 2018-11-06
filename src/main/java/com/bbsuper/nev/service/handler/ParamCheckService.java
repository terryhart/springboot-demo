package com.bbsuper.nev.service.handler;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bbsuper.nev.beans.bo.vehicle.VehicleExcel;
import com.bbsuper.nev.beans.dto.customer.FollowCustomerDto;
import com.bbsuper.nev.beans.dto.role.AccountRoleDto;
import com.bbsuper.nev.beans.enums.PrivilegeEnum;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.beans.enums.result.CustomerCodeEnum;
import com.bbsuper.nev.beans.enums.result.VehicleCodeEnum;
import com.bbsuper.nev.beans.po.CustomerEntity;
import com.bbsuper.nev.beans.po.CustomerEntity.Status;
import com.bbsuper.nev.beans.po.VehicleInfoEntity;
import com.bbsuper.nev.beans.po.VehicleTypeEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.dao.mapping.CustomerEntityMapper;
import com.bbsuper.nev.exception.MissingParameterException;
import com.google.common.collect.Lists;

/**
 * 参数校验服务
 * @author liwei
 * @date: 2018年10月25日 上午10:26:55
 *
 */
@Service
public class ParamCheckService {
	
	@Resource
	private CustomerEntityMapper customerEntityMapper;
	
	/**
	 * 校验角色的权限是否合法
	 * @param accountRoleDto
	 * @return
	 */
	public ResultData<Void> checkRole(AccountRoleDto roleDto) {
		try{
			String[] split = roleDto.getPrivilegeCode().split(",");
			for(String code : split){
				PrivilegeEnum.getPrivilegeByCode(Integer.parseInt(code));
			}
		}catch(Exception e){
			return ResultData.getInstance(BaseRetCode.EINVAL);
		}
		return ResultData.getInstance();
	}
	/**
	 * Excel装换为车辆信息
	 * @param readExcel
	 * @param vehicleType
	 * @return
	 */
	public ResultData<List<VehicleInfoEntity>> transformVehicleInfoEntity(List<VehicleExcel> readExcel,
			List<VehicleTypeEntity> vehicleType) {
		if(CollectionUtils.isEmpty(readExcel)){
			return ResultData.getInstance(VehicleCodeEnum.DATA_EMPTY);
		}
		List<VehicleInfoEntity> entitys = Lists.newArrayList();
		for(VehicleExcel v : readExcel){
			if(StringUtils.isEmpty(v.getTrademark())
					||StringUtils.isEmpty(v.getDistributor())
					||StringUtils.isEmpty(v.getPlateNumbers())
					||StringUtils.isEmpty(v.getPrice())){
					return ResultData.getInstance(VehicleCodeEnum.PARAM_MISSING);
			}
			Long vehicleTypeId = getVehicleTypeId(vehicleType,v);
			if(vehicleTypeId==null){
				return ResultData.getInstance(VehicleCodeEnum.NOT_TYPE);
			}
			VehicleInfoEntity entity = new VehicleInfoEntity();
			entity.setPlateNumbers(v.getPlateNumbers());
			entity.setPrice(new BigDecimal(v.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
			entity.setVehicleTypeId(vehicleTypeId);
			entity.setStatus(VehicleInfoEntity.Status.NORMAL);
			entitys.add(entity);
		}
		return ResultData.getInstance(entitys);
	}
	
	private Long getVehicleTypeId(List<VehicleTypeEntity> vehicleType, VehicleExcel v) {
		Optional<VehicleTypeEntity> findFirst = vehicleType.stream().filter(t->{
			return t.getDistributor().equals(v.getDistributor())&&t.getTrademark().equals(v.getTrademark());
		}).findFirst();
		if(findFirst.isPresent()){
			return findFirst.get().getId();
		}
		return null;
	}
	
	/**
	 * 校验跟进客户的请求
	 * @param followCustomerDto
	 * @return
	 */
	public ResultData<Void> checkFollowCustomerDto(FollowCustomerDto followCustomerDto) {
		//校验状态扭转
		ResultData<Void> code = checkCustomerStatus(followCustomerDto);
		if(!code.success()){
			return code;
		}
		//校验非空参数
		checkCustomerEmpty(followCustomerDto);
		return ResultData.getInstance();
		
	}
	
	private void checkCustomerEmpty(FollowCustomerDto followCustomerDto) {
		Status status = followCustomerDto.getStatus();
		//1.关键字keyword
		if(StringUtils.isEmpty(followCustomerDto.getKeyword())&&status!=Status.SOLD){
			throw new MissingParameterException("keyword");
		}
		//2.意向车型intentionVehicleTypeId
		if(followCustomerDto.getIntentionVehicleTypeId()==null
				&&(status==Status.FOLLOW_1||status==Status.FOLLOW_2||status==Status.FOLLOW_3)){
			throw new MissingParameterException("intentionVehicleTypeId");
		}
		//3.车牌号plateNumbers
		if(StringUtils.isEmpty(followCustomerDto.getPlateNumbers())
				&&status==Status.FOLLOW_5){
			throw new MissingParameterException("plateNumbers");
		}
		//4.保险日期insureDate
		if(StringUtils.isEmpty(followCustomerDto.getInsureDate())
				&&status==Status.FOLLOW_6){
			throw new MissingParameterException("insureDate");
		}
		//5.合同contractLink
		if(StringUtils.isEmpty(followCustomerDto.getContractLink())
				&&status==Status.SOLD){
			throw new MissingParameterException("contractLink");
		}
		
	}
	private ResultData<Void> checkCustomerStatus(FollowCustomerDto followCustomerDto) {
		Status status = followCustomerDto.getStatus();
		if(status == Status.DISCARD||status == Status.DISCARD){
			return ResultData.getInstance(CustomerCodeEnum.NOT_STATUS);
		}
		CustomerEntity selectById = customerEntityMapper.selectById(followCustomerDto.getId());
		Status oldStatus = selectById.getStatus();
		if(status.getCode()<oldStatus.getCode()
				||(status.getCode()==oldStatus.getCode()&&status!=Status.FOLLOW_1&&status!=Status.FOLLOW_3)){
			return ResultData.getInstance(CustomerCodeEnum.NOT_STATUS);
		}
		return ResultData.getInstance();
	}


}
