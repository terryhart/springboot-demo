package com.bbsuper.nev.controller.finance;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbsuper.nev.annotation.Slf4j;
import com.bbsuper.nev.beans.dto.finance.RecordsCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.finance.ReceiptInfo;
import com.bbsuper.nev.service.ReceiptService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 收款记录
 * @author liwei
 * @date: 2018年10月31日 下午6:27:39
 *
 */
@Api(tags="收款列表")
@RequestMapping("/finance/receipt")
@RestController
public class ReceiptController {
	
	@Resource
	private ReceiptService ReceiptService;
	
	@PostMapping("/queryList")
	@ApiOperation(value = "查询列表", notes = "按条件查询类表")
	@Slf4j
	public ResultData<PaginationResult<ReceiptInfo>> queryList(@RequestBody @Validated PaginationCondition<RecordsCondition> condition){

		return ReceiptService.queryList(condition);
	}

}
