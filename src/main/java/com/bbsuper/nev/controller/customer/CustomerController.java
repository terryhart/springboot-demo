package com.bbsuper.nev.controller.customer;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbsuper.nev.annotation.IgnoreAuthentication;
import com.bbsuper.nev.annotation.Slf4j;
import com.bbsuper.nev.beans.dto.customer.ApplyDto;
import com.bbsuper.nev.beans.dto.customer.ChangeVehicleDto;
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
import com.bbsuper.nev.beans.vo.city.CityInfo;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.customer.CustomerInfo;
import com.bbsuper.nev.beans.vo.customer.FollowRecordsInfo;
import com.bbsuper.nev.beans.vo.vehicle_type.VehicleTypeInfo;
import com.bbsuper.nev.service.CityService;
import com.bbsuper.nev.service.CustomerService;
import com.bbsuper.nev.service.VehicleInfoService;
import com.bbsuper.nev.service.VehicleTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 客户管理
 * @author liwei
 * @date: 2018年10月25日 上午9:04:08
 *
 */
@Api(tags="客户管理")
@RequestMapping("/customer")
@RestController
public class CustomerController {
	
	@Resource
	private CustomerService customerService;
	
	@Resource
	private CityService cityService;
	
	@Resource
	private VehicleTypeService vehicleTypeService;
	
	@Resource
	private VehicleInfoService vehicleInfoService;
	
	@PostMapping("/interesting/queryList")
	@ApiOperation(value = "意向客户列表", notes = "意向客户列表")
	@Slf4j
	public ResultData<PaginationResult<CustomerInfo>> queryInterestingList(@RequestBody @Validated PaginationCondition<IntentionCustomerCondition> condition){

		return customerService.queryInterestingList(condition);
	}
	
	@PostMapping("/follow/queryList")
	@ApiOperation(value = "跟进客户列表", notes = "跟进客户列表")
	@Slf4j
	public ResultData<PaginationResult<CustomerInfo>> queryFollowList(@RequestBody @Validated PaginationCondition<FollowCustomerCondition> condition){

		return customerService.queryFollowList(condition);
	}
	
	@PostMapping("/sold/queryList")
	@ApiOperation(value = "已售客户列表", notes = "已售客户列表")
	@Slf4j
	public ResultData<PaginationResult<CustomerInfo>> querySoldList(@RequestBody @Validated PaginationCondition<SoldCustomerCondition> condition){

		return customerService.querySoldList(condition);
	}
	
	@PostMapping("/discard/queryList")
	@ApiOperation(value = "放弃客户列表", notes = "放弃客户列表")
	@Slf4j
	public ResultData<PaginationResult<CustomerInfo>> queryDiscardList(@RequestBody @Validated PaginationCondition<DiscardCustomerCondition> condition){

		return customerService.queryDiscardList(condition);
	}
	
	@PostMapping("/interesting/querySalesAccount")
	@ApiOperation(value = "查询可分配的销售列表", notes = "查询可分配的销售列表")
	@Slf4j
	public ResultData<List<SalesAccount>> querySalesAccount(@ApiParam("客户id") @RequestParam("id") String id){
		
		return customerService.querySalesAccount(Long.parseLong(id));
	}
	
	@PostMapping("/interesting/allotSale")
	@ApiOperation(value = "分配销售", notes = "分配销售")
	@Slf4j
	public ResultData<Void> allotSale(@ApiParam("客户id") @RequestParam("id") String id,
			@ApiParam("销售id") @RequestParam("saleId") String saleId){
		
		return customerService.allotSale(Long.parseLong(id),Long.parseLong(saleId));
	}
	
	@PostMapping("/follow/cityList")
	@ApiOperation(value = "启用的城市列表", notes = "启用的城市列表")
	@Slf4j
	public ResultData<List<CityInfo>> cityList(){
		
		return cityService.normalList();
	}
	
	@PostMapping("/follow/vehicleTypeList")
	@ApiOperation(value = "启用的车型列表", notes = "启用的车型列表")
	@Slf4j
	@IgnoreAuthentication(needLogin = true)
	public ResultData<List<VehicleTypeInfo>> vehicleTypeList(){
		
		return vehicleTypeService.normalList();
	}
	
	@PostMapping("/follow/add")
	@ApiOperation(value = "新增客户", notes = "新增客户")
	@Slf4j
	public ResultData<Void> add(@Validated CustomerDto customerDto){
		return customerService.add(customerDto);
	}
	
	@PostMapping("/apply")
	@ApiOperation(value = "客户报名入口", notes = "上线之后将原有的web端和wap端的报名请求切换到该地址")
	@Slf4j
	@IgnoreAuthentication
	public ResultData<Void> apply(@Validated ApplyDto applyDto){

		return customerService.apply(applyDto);
	}
	
	@PostMapping("/follow/edit")
	@ApiOperation(value = "编辑客户", notes = "编辑客户")
	@Slf4j
	public ResultData<Void> edit(@Validated EditCustomerDto editCustomerDto){
		return customerService.edit(editCustomerDto);
	}
	
	@PostMapping("/common/followRecords")
	@ApiOperation(value = "查询跟进记录", notes = "根据客户id查询跟进记录")
	@Slf4j
	@IgnoreAuthentication(needLogin = true)
	public ResultData<PaginationResult<FollowRecordsInfo>> followRecords(@RequestBody @Validated PaginationCondition<Long> condition){

		return customerService.followRecords(condition);
	}
	
	@PostMapping("/common/existVehicle")
	@ApiOperation(value = "查询车牌号是否可用", notes = "查询车牌号是否可用")
	@Slf4j
	@IgnoreAuthentication(needLogin = true)
	public ResultData<Void> existVehicle(@ApiParam("车牌号") @RequestParam("plateNumbers") String plateNumbers){

		return vehicleInfoService.existVehicle(plateNumbers);
	}
	
	@PostMapping("/follow/followCustomer")
	@ApiOperation(value = "跟进客户", notes = "备注：1.客户在售前跟进和意向跟进时，可以选择当前状态和后续状态，其他情况只能选择后续状态")
	@Slf4j
	public ResultData<Void> followCustomer(@Validated FollowCustomerDto followCustomerDto){

		return customerService.followCustomer(followCustomerDto);
	}
	
	@PostMapping("/sold/changeVehicle")
	@ApiOperation(value = "更换车辆", notes = "更换车辆")
	@Slf4j
	public ResultData<Void> changeVehicle(@Validated ChangeVehicleDto changeVehicleDto){

		return customerService.changeVehicle(changeVehicleDto);
	}
	
	
	@PostMapping("/sold/refund")
	@ApiOperation(value = "收车退款", notes = "收车退款")
	@Slf4j
	public ResultData<Void> refund(@Validated RefundDto refundDto){

		return customerService.refund(refundDto);
	}
	
	@PostMapping("/discard/recover")
	@ApiOperation(value = "恢复被放弃的客户", notes = "恢复被放弃的客户")
	@Slf4j
	public ResultData<Void> recover(@ApiParam("客户id") @RequestParam("id") String id){

		return customerService.recover(Long.parseLong(id));
	}
	
	
	
	

}
