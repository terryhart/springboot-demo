package com.bbsuper.nev.service.impl;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bbsuper.nev.beans.dto.customer.ApplyDto;
import com.bbsuper.nev.beans.dto.customer.ChangeVehicleDto;
import com.bbsuper.nev.beans.dto.customer.CustomerCondition;
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
import com.bbsuper.nev.beans.dto.user.AccountCondition;
import com.bbsuper.nev.beans.enums.PrivilegeEnum;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.beans.po.AccountRoleEntity;
import com.bbsuper.nev.beans.po.CustomerEntity;
import com.bbsuper.nev.beans.po.CustomerEntity.Status;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.customer.CustomerInfo;
import com.bbsuper.nev.beans.vo.customer.FollowRecordsInfo;
import com.bbsuper.nev.beans.vo.user.UserInfo;
import com.bbsuper.nev.dao.mapping.CustomerEntityMapper;
import com.bbsuper.nev.exception.MissingParameterException;
import com.bbsuper.nev.service.AccountService;
import com.bbsuper.nev.service.CityService;
import com.bbsuper.nev.service.CustomerService;
import com.bbsuper.nev.service.FollowRecordsService;
import com.bbsuper.nev.service.ReceiptService;
import com.bbsuper.nev.service.RefundRecordsService;
import com.bbsuper.nev.service.VehicleInfoService;
import com.bbsuper.nev.service.VehicleTypeService;
import com.bbsuper.nev.service.handler.ParamCheckService;
import com.bbsuper.nev.utils.ControllerUtil;
import com.google.common.collect.Lists;

/**
 * 客户管理
 * @author liwei
 * @date: 2018年10月30日 下午3:12:53
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Resource
	private CustomerEntityMapper customerEntityMapper;
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private FollowRecordsService followRecordsService;
	
	@Resource
	private ParamCheckService paramCheckService;
	
	@Resource
	private VehicleInfoService vehicleInfoService;
	
	@Resource
	private VehicleTypeService vehicleTypeService;
	
	@Resource
	private ReceiptService receiptService;
	
	@Resource
	private RefundRecordsService refundRecordsService;
	
	@Resource
	private CityService cityService;

	@Override
	public ResultData<List<SalesAccount>> querySalesAccount(long id) {
		CustomerEntity entity = customerEntityMapper.selectById(id);
		AccountCondition condition = new AccountCondition();
		condition.setStatus(AccountRoleEntity.Status.NORMAL);
		List<UserInfo> list = accountService.queryListByData(new PaginationCondition<AccountCondition>(condition));
		List<SalesAccount> collect = list.stream().filter(u->{
			//角色状态是否正常
			if(u.getRoleStatus()!=AccountRoleEntity.Status.NORMAL){
				return false;
			}
			//是否有权限
			String privilegeCode = u.getPrivilegeCode();
			if(StringUtils.isEmpty(privilegeCode)){
				return false;
			}
			String[] split = privilegeCode.split(",");
			List<String> codes = Lists.newArrayList(split);
			if(!codes.contains(String.valueOf(PrivilegeEnum.A2.getCode()))){
				return false;
			}
			//所在城市是否匹配
			if(u.getCityId().longValue()!=0&&entity.getCityId().longValue()!=0){
				return u.getCityId().longValue() == entity.getCityId().longValue();
			}
			return true;
		}).map(u->{
			SalesAccount account = new SalesAccount();
			account.setId(u.getId());
			account.setName(u.getName());
			return account;
		}).collect(Collectors.toList());
		return ResultData.getInstance(collect);
	}

	@Override
	public ResultData<Void> allotSale(long id, long saleId) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(id);
		customerEntity.setSalesmanId(saleId);
		customerEntity.setStatus(Status.FOLLOW_1);
		int updateById = customerEntityMapper.updateById(customerEntity);
		return updateById == 1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	public ResultData<Void> add(CustomerDto customerDto) {
		CustomerEntity addEntity = customerDto.toAddEntity();
		addEntity.setSalesmanId(ControllerUtil.getCurrentUser().getId());
		int insert = customerEntityMapper.insert(addEntity);
		return insert==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	@Transactional
	public ResultData<Void> edit(EditCustomerDto editCustomerDto) {
		CustomerEntity entity = editCustomerDto.toEntity();
		if(entity.getStatus()==Status.DISCARD){
			CustomerEntity selectById = customerEntityMapper.selectById(entity.getId());
			entity.setDiscardStatus(selectById.getStatus());
			//生成放弃记录
			followRecordsService.generateDiscardRecords(editCustomerDto);
		}
		int update = customerEntityMapper.updateById(entity);
		return update==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	public ResultData<PaginationResult<FollowRecordsInfo>> followRecords(PaginationCondition<Long> condition) {
		if(condition.getCondition()==null){
			throw new MissingParameterException("condition");
		}
		return followRecordsService.followRecords(condition);
	}

	@Override
	@Transactional
	public ResultData<Void> followCustomer(FollowCustomerDto followCustomerDto) {
		//1.校验请求参数
		ResultData<Void> code = paramCheckService.checkFollowCustomerDto(followCustomerDto);
		if(!code.success()){
			return code;
		}
		//2.锁定车辆信息
		ResultData<Long> ret = vehicleInfoService.lockVehicle(followCustomerDto);
		if(!ret.success()){
			return ResultData.getInstance(ret);
		}
		followRecordsService.generateKeyword(followCustomerDto);
		//3.更新客户状态等信息
		updateCustomerByFollow(followCustomerDto);
		//4.生成跟进记录
		followRecordsService.generateFollowRecords(followCustomerDto);
		//5.生成收款记录 
		receiptService.generateReceipt(followCustomerDto,ret.getData());
		return ResultData.getInstance();
	}

	private void updateCustomerByFollow(FollowCustomerDto followCustomerDto) {
		CustomerEntity entity = new CustomerEntity();
		entity.setId(followCustomerDto.getId());
		
		entity.setStatus(followCustomerDto.getStatus());
		entity.setVehicleTypeId(followCustomerDto.getIntentionVehicleTypeId());
		entity.setKeyword(followCustomerDto.getKeyword());
		if(followCustomerDto.getClosingCost()!=null){
			entity.setClosingCost(followCustomerDto.getClosingCost().setScale(2, BigDecimal.ROUND_HALF_UP));
		}
		entity.setInsureDate(followCustomerDto.getInsureDate());
		entity.setContractLink(followCustomerDto.getContractLink());
		customerEntityMapper.updateById(entity);
	}

	@Override
	@Transactional
	public ResultData<Void> changeVehicle(ChangeVehicleDto changeVehicleDto) {
		//1.原有车辆退回
		CustomerInfo customerInfo = customerEntityMapper.queryCustomerInfoById(changeVehicleDto.getId());
		cityService.addCity(customerInfo);
		vehicleTypeService.addType(customerInfo);
		
		vehicleInfoService.back(customerInfo.getVehicleId(),"更换车辆");
		//2.锁定车辆
		vehicleInfoService.lockVehicle(changeVehicleDto);

		//3.生成跟进记录
		followRecordsService.generateAfterSaleRecords(changeVehicleDto);
		return ResultData.getInstance();
	}

	@Override
	@Transactional
	public ResultData<Void> refund(RefundDto refundDto) {
		//1.原有车辆退回
		CustomerInfo customerInfo = customerEntityMapper.queryCustomerInfoById(refundDto.getId());
		cityService.addCity(customerInfo);
		vehicleTypeService.addType(customerInfo);
		
		vehicleInfoService.back(customerInfo.getVehicleId(),"收车退款");
		//2.放弃客户
		discardCustomer(customerInfo,refundDto);
		//3.生成跟进记录
		followRecordsService.generateAfterSaleRecords(refundDto);
		//4.生成退款记录
		refundRecordsService.generateReceipt(refundDto,customerInfo.getVehicleId());
		return ResultData.getInstance();
	}

	private void discardCustomer(CustomerInfo customerInfo,RefundDto refundDto) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(refundDto.getId());
		customerEntity.setStatus(CustomerEntity.Status.DISCARD);
		customerEntity.setDiscardReason("收车退款");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		customerEntity.setDiscardTime(format.format(new Date()));
		customerEntity.setDiscardStatus(customerInfo.getStatus());
		customerEntityMapper.updateById(customerEntity);
	}

	@Override
	public ResultData<Void> recover(long id) {
		CustomerEntity selectById = customerEntityMapper.selectById(id);
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(id);
		customerEntity.setStatus(selectById.getDiscardStatus());
		customerEntityMapper.updateById(customerEntity);
		return ResultData.getInstance();
	}

	@Override
	public ResultData<Void> apply(ApplyDto applyDto) {
		CustomerEntity entity = applyDto.toEntity();
		int update = customerEntityMapper.insert(entity);
		return update==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	public ResultData<PaginationResult<CustomerInfo>> queryInterestingList(
			PaginationCondition<IntentionCustomerCondition> condition) {
		if(condition.getCondition()==null){
			condition.setCondition(new IntentionCustomerCondition());
		}
		//设置数据隔离参数
		setCommonCondition(condition.getCondition());
		List<CustomerInfo> list = customerEntityMapper.queryListByInteresting(condition);
		//缓存查询城市信息
		cityService.addCCity(list);
		vehicleTypeService.addTType(list);
		
		int count = customerEntityMapper.queryCountByInteresting(condition);
		PaginationResult<CustomerInfo> instance = PaginationResult.getInstance(list,condition.getPageInfo(),count);
		return ResultData.getInstance(instance);
	}

	private void setCommonCondition(CustomerCondition condition) {
		//根据地域信息数据隔离
		UserInfo currentUser = ControllerUtil.getCurrentUser();
		if(currentUser.getCityId().longValue()!=0){
			condition.setCityId(currentUser.getCityId());
		}
		//根据所属的销售隔离
		condition.setSaleId(currentUser.getId());
	}

	@Override
	public ResultData<PaginationResult<CustomerInfo>> queryFollowList(
			PaginationCondition<FollowCustomerCondition> condition) {
		if(condition.getCondition()==null){
			condition.setCondition(new FollowCustomerCondition());
		}
		//设置数据隔离参数
		setCommonCondition(condition.getCondition());
		List<CustomerInfo> list = customerEntityMapper.queryListByFollow(condition);
		//缓存查询城市信息
		cityService.addCCity(list);
		vehicleTypeService.addTType(list);
		
		int count = customerEntityMapper.queryCountByFollow(condition);
		PaginationResult<CustomerInfo> instance = PaginationResult.getInstance(list,condition.getPageInfo(),count);
		return ResultData.getInstance(instance);
	}

	@Override
	public ResultData<PaginationResult<CustomerInfo>> querySoldList(
			PaginationCondition<SoldCustomerCondition> condition) {
		if(condition.getCondition()==null){
			condition.setCondition(new SoldCustomerCondition());
		}
		//设置数据隔离参数
		setCommonCondition(condition.getCondition());
		List<CustomerInfo> list = customerEntityMapper.queryListBySold(condition);
		//缓存查询城市信息
		cityService.addCCity(list);
		vehicleTypeService.addTType(list);
		
		int count = customerEntityMapper.queryCountBySold(condition);
		PaginationResult<CustomerInfo> instance = PaginationResult.getInstance(list,condition.getPageInfo(),count);
		return ResultData.getInstance(instance);
	}

	@Override
	public ResultData<PaginationResult<CustomerInfo>> queryDiscardList(
			PaginationCondition<DiscardCustomerCondition> condition) {
		if(condition.getCondition()==null){
			condition.setCondition(new DiscardCustomerCondition());
		}
		//设置数据隔离参数
		setCommonCondition(condition.getCondition());
		List<CustomerInfo> list = customerEntityMapper.queryListByDiscard(condition);
		//缓存查询城市信息
		cityService.addCCity(list);
		vehicleTypeService.addTType(list);
		
		int count = customerEntityMapper.queryCountByDiscard(condition);
		PaginationResult<CustomerInfo> instance = PaginationResult.getInstance(list,condition.getPageInfo(),count);
		return ResultData.getInstance(instance);
	}

}
