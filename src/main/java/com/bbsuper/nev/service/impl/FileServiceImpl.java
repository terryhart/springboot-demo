package com.bbsuper.nev.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bbsuper.nev.beans.vo.common.ResultData;
import com.bbsuper.nev.service.FileService;
import com.bbsuper.nev.service.ftp.FtpConfig;
import com.bbsuper.nev.service.ftp.FtpPool;
import com.google.common.collect.Lists;

/**
 * 文件相关服务
 * @author liwei
 * @date: 2018年11月6日 上午11:03:26
 *
 */
@Service
public class FileServiceImpl implements FileService{
	
	private final static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	private final static String BASE_PATH = "bbsuper/nev/";
	@Resource
	private FtpPool ftpPool;
	
	@Autowired
	private FtpConfig ftpConfig;
	
	@Resource
	private Executor asyncThreadPool;

	@Override
	public ResultData<List<String>> upload(MultipartFile[] files) throws Exception {
		CountDownLatch downLatch = new CountDownLatch(files.length);
		List<String> result = Lists.newArrayList();
		for(MultipartFile file:files){
			uploadFile(file,downLatch,result);
		}
		downLatch.await();
		return ResultData.getInstance(result);
	}
	
	private void uploadFile(MultipartFile file,CountDownLatch downLatch,List<String> result){
		asyncThreadPool.execute(()->{
			FTPClient ftp = ftpPool.getFtp();
			try{
				//生成文件路径
				String path = changeDirectory(ftp,file);
				//生成文件名
				String fileName = generateFileName(file);
				//上传
				ftp.storeFile(fileName, file.getInputStream());
				//返回文件url
				result.add(ftpConfig.getAccessServer() + "/" +path + "/" + fileName);
				
			}catch(Exception e){
				logger.error("uploadFile,error",e);
			}finally{
				downLatch.countDown();
				ftpPool.returnFtp(ftp);
			}
		});
	}
	
	private String generateFileName(MultipartFile file) {
		String name  = UUID.randomUUID().toString();
		if(StringUtils.isNotEmpty(file.getOriginalFilename())){
			String filename = file.getOriginalFilename();
			return name+filename.substring(filename.lastIndexOf("."));
		}
		logger.warn("OriginalFilename is Empty");
		return name+".data";
	}

	private String changeDirectory(FTPClient ftp, MultipartFile file) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
		String path = BASE_PATH + format.format(new Date());
		ftp.changeWorkingDirectory("/");
		if (ftp.changeWorkingDirectory(path)) {
			return path;
		}
		String[] temp = path.split("/");
		for (int i = 0; i < temp.length; i++) {
			String tmp = temp[i];
			if (!ftp.changeWorkingDirectory(tmp)) {
				ftp.makeDirectory(tmp);
				ftp.changeWorkingDirectory(tmp);
			}
		}
		return path;
	}

	
}
