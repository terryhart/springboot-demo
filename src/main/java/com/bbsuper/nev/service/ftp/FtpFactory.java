package com.bbsuper.nev.service.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 
 * @author liwei
 * @date: 2018年11月7日 上午10:43:43
 *
 */
@Component
public class FtpFactory implements PooledObjectFactory<FTPClient>{
	
	@Autowired
	private FtpConfig ftpConfig;

	@Override
	public PooledObject<FTPClient> makeObject() throws Exception {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect(ftpConfig.getHost(), ftpConfig.getPort());
		ftpClient.setControlKeepAliveTimeout(300);
		ftpClient.login(ftpConfig.getUser(), ftpConfig.getPassword());
		ftpClient.enterLocalPassiveMode();
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		return new DefaultPooledObject<>(ftpClient);
	}

	@Override
	public void destroyObject(PooledObject<FTPClient> p) throws Exception {
		if (!p.getObject().isConnected()){
			return;
		}
		p.getObject().disconnect();
	}

	@Override
	public boolean validateObject(PooledObject<FTPClient> p) {
		return p.getObject().isConnected();
	}

	@Override
	public void activateObject(PooledObject<FTPClient> p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passivateObject(PooledObject<FTPClient> p) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
