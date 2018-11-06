package com.bbsuper.nev.service.ftp;

import javax.annotation.PostConstruct;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbsuper.nev.beans.enums.result.BaseRetCode;
import com.bbsuper.nev.exception.RetCodeException;

/**
 * ftp连接池
 * @author liwei
 * @date: 2018年11月6日 上午11:45:56
 *
 */
@Component
public class FtpPool {
	
	private GenericObjectPool<FTPClient> internalPool;
	
	@Autowired
	private FtpConfig ftpConfig;
	
	@Autowired
	private FtpFactory ftpFactory;
	
	
	@PostConstruct
	public void init(){
		this.internalPool = new GenericObjectPool<FTPClient>(ftpFactory, ftpConfig.toObjectPoolConfig());
	}


	public FTPClient getFtp(){
		try {
			return internalPool.borrowObject();
		} catch (Exception e) {
			throw new RetCodeException(BaseRetCode.FAIL);
		}
	}
	
	public void returnFtp(FTPClient ftpClient){
		internalPool.returnObject(ftpClient);
	}

}
