package com.bbsuper.nev.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bbsuper.nev.annotation.IgnoreAuthentication;
import com.bbsuper.nev.annotation.Slf4j;
import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件服务
 * @author liwei
 * @date: 2018年11月6日 上午11:00:10
 *
 */
@Api(tags="文件服务")
@RequestMapping("/file")
@RestController
public class FileController {
	
	@Resource
	private FileService fileService;
	
	@PostMapping("/upload")
	@ApiOperation(value = "上传文件", notes = "上传文件,支持批量上传，返回文件的url(接口文档调试存在BUG，实际可用)")
	@Slf4j
	@IgnoreAuthentication
    public ResultData<List<String>> upload(@RequestParam("files")MultipartFile[] files) throws Exception{
		
		return fileService.upload(files);
    }

}
