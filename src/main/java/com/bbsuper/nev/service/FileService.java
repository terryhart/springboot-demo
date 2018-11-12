package com.bbsuper.nev.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bbsuper.nev.beans.vo.common.ResultData;

/**
 * 文件相关服务
 * @author liwei
 * @date: 2018年11月6日 上午11:03:05
 *
 */
public interface FileService {

	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	ResultData<List<String>> upload(MultipartFile[] files) throws Exception;


}
