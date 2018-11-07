package com.bbsuper.nev.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.bbsuper.nev.beans.bo.vehicle.VehicleExcel;
import com.bbsuper.nev.beans.dto.customer.ChangeVehicleDto;
import com.bbsuper.nev.beans.dto.customer.FollowCustomerDto;
import com.bbsuper.nev.beans.dto.paging.PaginationCondition;
import com.bbsuper.nev.beans.dto.paging.PaginationResult;
import com.bbsuper.nev.beans.dto.vehicle.EditVehicleDto;
import com.bbsuper.nev.beans.dto.vehicle.VehicleCondition;
import com.bbsuper.nev.beans.dto.vehicle.VehicleDto;
import com.bbsuper.nev.beans.dto.vehicle.type.VehicleTypeCondition;
import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.beans.enums.result.CustomerCodeEnum;
import com.bbsuper.nev.beans.enums.result.VehicleCodeEnum;
import com.bbsuper.nev.beans.po.VehicleInfoEntity;
import com.bbsuper.nev.beans.po.VehicleInfoEntity.Status;
import com.bbsuper.nev.beans.po.VehicleTypeEntity;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.beans.vo.vehicle.VehicleInfo;
import com.bbsuper.nev.dao.mapping.VehicleInfoEntityMapper;
import com.bbsuper.nev.exception.RetCodeException;
import com.bbsuper.nev.service.VehicleInfoService;
import com.bbsuper.nev.service.VehicleTypeService;
import com.bbsuper.nev.service.handler.ParamCheckService;
import com.google.common.collect.Lists;
/**
 * 车辆信息管理
 * @author liwei
 * @date: 2018年10月30日 上午10:43:53
 *
 */
@Service
public class VehicleInfoServiceImpl implements VehicleInfoService{
	
	private static Logger logger = LoggerFactory.getLogger(VehicleInfoServiceImpl.class);
	
	@Resource
	private VehicleInfoEntityMapper vehicleInfoEntityMapper;
	
	@Resource
	private VehicleTypeService vehicleTypeService;
	
	@Resource
	private ParamCheckService paramCheckService;
	
	@Override
	public ResultData<PaginationResult<VehicleInfo>> queryList(PaginationCondition<VehicleCondition> condition,List<Status> status) {
		if(condition.getCondition()==null){
			condition.setCondition(new VehicleCondition());
		}
		condition.getCondition().setStatuss(status);
		List<VehicleInfo> list = vehicleInfoEntityMapper.queryListByCondition(condition);
		vehicleTypeService.addType(list);
		int count = vehicleInfoEntityMapper.queryCountByCondition(condition);
		PaginationResult<VehicleInfo> instance = PaginationResult.getInstance(list, condition.getPageInfo(), count);
		return ResultData.getInstance(instance);
	}

	@Override
	public ResultData<Void> add(VehicleDto vehicleDto) {
		VehicleTypeEntity vehicleType = vehicleTypeService.selectById(vehicleDto.getVehicleTypeId());
		if(vehicleType==null||vehicleType.getStatus()!=VehicleTypeEntity.Status.NORMAL){
			return ResultData.getInstance(VehicleCodeEnum.NOT_VEHICLE_TYPE);
		}
		int insert = vehicleInfoEntityMapper.insert(vehicleDto.toAddEntity());
		return insert==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	public ResultData<Void> edit(EditVehicleDto editVehicleDto) {
		VehicleTypeEntity vehicleType = vehicleTypeService.selectById(editVehicleDto.getVehicleTypeId());
		if(vehicleType==null||vehicleType.getStatus()!=VehicleTypeEntity.Status.NORMAL){
			return ResultData.getInstance(VehicleCodeEnum.NOT_VEHICLE_TYPE);
		}
		int update = vehicleInfoEntityMapper.updateById(editVehicleDto.toEntity());
		return update==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	public ResultData<Void> modifyStatus(long id, Status status) {
		VehicleInfoEntity entity = new VehicleInfoEntity();
		entity.setId(id);
		entity.setStatus(status);
		int update = vehicleInfoEntityMapper.updateById(entity);
		return update==1?ResultData.getInstance():ResultData.getInstance(BaseRetCode.FAIL);
	}

	@Override
	public ResultData<Void> importVehicleList(MultipartFile file) {
		try{
			//读取dExcel
			List<VehicleExcel> readExcel = readExcel(file.getInputStream());
			//查询启用的车型列表
			VehicleTypeCondition condition = new VehicleTypeCondition();
			condition.setStatus(VehicleTypeEntity.Status.NORMAL);
			List<VehicleTypeEntity> vehicleType = vehicleTypeService.queryListData(new PaginationCondition<VehicleTypeCondition>(condition));
			//解析并校验车辆信息
			ResultData<List<VehicleInfoEntity>> result = paramCheckService.transformVehicleInfoEntity(readExcel,vehicleType);
			if(!result.success()){
				return ResultData.getInstance(result);
			}
			//插入数据库
			vehicleInfoEntityMapper.insertBatch(result.getData());
		}catch(Exception e){
			logger.error("importVehicleList err:",e);
			return ResultData.getInstance(VehicleCodeEnum.IMPORT_VEHICLE_FAIL);
		}
		return ResultData.getInstance();
	}
	
	private List<VehicleExcel> readExcel(InputStream in) throws Exception {
    	List<VehicleExcel> excel = Lists.newArrayList();
    	//创建Excel工作薄
    	Workbook workbook = WorkbookFactory.create(in);
        //获取Excel中第一个表
        Sheet sheet = workbook.getSheetAt(0);
        //遍历当前表中的所有行
        for (int i =1; i <= sheet.getLastRowNum(); i++) {
        	Row row = sheet.getRow(i);
            if (row == null ) {
                continue;
            }
            VehicleExcel vehicleExcel = new VehicleExcel();
            //把每个单元格的值付给对象的对应属性
            if (row.getCell(0)!=null){
            	row.getCell(0).setCellType(CellType.STRING);
            	vehicleExcel.setTrademark(row.getCell(0).getStringCellValue());
            }
            if (row.getCell(1)!=null){
            	row.getCell(1).setCellType(CellType.STRING);
            	vehicleExcel.setDistributor(row.getCell(1).getStringCellValue());
            }
            if (row.getCell(2)!=null){
            	row.getCell(2).setCellType(CellType.STRING);
            	vehicleExcel.setPlateNumbers(row.getCell(2).getStringCellValue());
            }
            if (row.getCell(3)!=null){
            	row.getCell(3).setCellType(CellType.STRING);
            	vehicleExcel.setPrice(row.getCell(3).getStringCellValue());
            }
            excel.add(vehicleExcel);
        }
        return excel;
    }

	@Override
	public ResultData<Long> lockVehicle(FollowCustomerDto followCustomerDto) {
		String plateNumbers = followCustomerDto.getPlateNumbers();
		if(StringUtils.isNotEmpty(plateNumbers)){
			VehicleInfoEntity infoEntity = vehicleInfoEntityMapper.queryAvailableByPlateNumbers(plateNumbers);
			if(infoEntity==null){
				return ResultData.getInstance(CustomerCodeEnum.NOT_EXIST_VEHICLE);
			}
			VehicleInfoEntity entity = new VehicleInfoEntity();
			entity.setId(infoEntity.getId());
			entity.setCustomerId(followCustomerDto.getId());
			if(followCustomerDto.getClosingCost()!=null){
				entity.setClosingCost(followCustomerDto.getClosingCost().setScale(2, BigDecimal.ROUND_HALF_UP));
			}
			entity.setDealDate(new Date());
			vehicleInfoEntityMapper.updateById(entity);
			return ResultData.getInstance(infoEntity.getId());
		}
		return new ResultData<>();
	}

	@Override
	public ResultData<Void> existVehicle(String plateNumbers) {
		VehicleInfoEntity infoEntity = vehicleInfoEntityMapper.queryAvailableByPlateNumbers(plateNumbers);
		if(infoEntity==null){
			return ResultData.getInstance(CustomerCodeEnum.NOT_EXIST_VEHICLE);
		}
		return ResultData.getInstance();
	}

	@Override
	public ResultData<Void> lockVehicle(ChangeVehicleDto changeVehicleDto) {
		VehicleInfoEntity infoEntity = vehicleInfoEntityMapper.queryAvailableByPlateNumbers(changeVehicleDto.getPlateNumbers());
		if(infoEntity==null){
			throw new RetCodeException(CustomerCodeEnum.NOT_EXIST_VEHICLE);
		}
		VehicleInfoEntity entity = new VehicleInfoEntity();
		entity.setId(infoEntity.getId());
		entity.setCustomerId(changeVehicleDto.getId());
		entity.setDealDate(new Date());
		vehicleInfoEntityMapper.updateById(entity);
		return ResultData.getInstance();
	}

	@Override
	public void back(Long vehicleId, String reason) {
		VehicleInfoEntity entity = new VehicleInfoEntity();
		entity.setId(vehicleId);
		entity.setStatus(Status.STAY_BACK);
		entity.setReturnReason(reason);
		entity.setReturnDate(new Date());
		vehicleInfoEntityMapper.updateById(entity);
		
	}



}
