package com.bbsuper.nev.controller.system;


import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbsuper.nev.annotation.Slf4j;
import com.bbsuper.nev.beans.dto.city.CityCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.po.CityEntity;
import com.bbsuper.nev.beans.vo.city.CityInfo;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.service.CityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 开埠城市
 * @author liwei
 * @date: 2018年10月26日 下午3:34:31
 *
 */

@Api(tags="开埠城市")
@RequestMapping("/system/city")
@RestController
public class CityController {
	
	@Resource
	private CityService cityService;
	
	@PostMapping("/queryList")
	@ApiOperation(value = "查询列表", notes = "按条件查询列表")
	@Slf4j
	public ResultData<PaginationResult<CityInfo>> queryList(@RequestBody @Validated PaginationCondition<CityCondition> condition){

		return cityService.queryList(condition);
	}
	
	@PostMapping("/disabled")
	@ApiOperation(value = "禁用城市", notes = "禁用城市")
	@Slf4j
	public ResultData<Void> disabled(@ApiParam("城市id") @RequestParam("id") String id){
		return cityService.modifyStatus(Long.parseLong(id),CityEntity.Status.DISABLED);
	}
	
	@PostMapping("/enabled")
	@ApiOperation(value = "启用城市", notes = "启用城市")
	@Slf4j
	public ResultData<Void> enabled(@ApiParam("城市id") @RequestParam("id") String id){
		return cityService.modifyStatus(Long.parseLong(id), CityEntity.Status.NORMAL);
	}

}
