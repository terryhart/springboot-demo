package com.bbsuper.nev.controller.finance;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbsuper.nev.annotation.Slf4j;
import com.bbsuper.nev.beans.dto.finance.RecordsCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.po.RefundRecordsEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.finance.RefundInfo;
import com.bbsuper.nev.service.RefundRecordsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 退款记录
 * @author liwei
 * @date: 2018年10月31日 下午6:28:22
 *
 */
@Api(tags="退款列表")
@RequestMapping("/finance/refund")
@RestController
public class RefundController {
	
	@Resource
	private RefundRecordsService refundRecordsService;
	
	@PostMapping("/queryList")
	@ApiOperation(value = "查询列表", notes = "按条件查询类表")
	@Slf4j
	public ResultData<PaginationResult<RefundInfo>> queryList(@RequestBody @Validated PaginationCondition<RecordsCondition> condition){

		return refundRecordsService.queryList(condition);
	}
	
	@PostMapping("/check")
	@ApiOperation(value = "审核退款信息", notes = "审核退款信息")
	@Slf4j
	public ResultData<Void> check(@ApiParam("退款id") @RequestParam("id") String id){
		return refundRecordsService.modifyStatus(Long.parseLong(id), RefundRecordsEntity.Status.REFUND);
	}

}
