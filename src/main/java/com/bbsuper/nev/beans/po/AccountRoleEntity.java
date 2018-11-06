package com.bbsuper.nev.beans.po;


import java.text.SimpleDateFormat;

import com.bbsuper.nev.beans.po.common.Entity;
import com.bbsuper.nev.beans.vo.role.AccountRoleInfo;
/**
 * 角色
 * @author liwei
 * @date: 2018年11月1日 下午2:59:38
 *
 */
public class AccountRoleEntity extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5576261898826182958L;

	/**
	 * 角色名
	 */
    private String name;

    /**
     * 权限code，多个用逗号隔开
     */
    private String privilegeCode;

    /**
     * 状态
     */
    private Status status;
    
    public enum Status{
    	NORMAL,DELETE,DISABLED;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivilegeCode() {
        return privilegeCode;
    }

    public void setPrivilegeCode(String privilegeCode) {
        this.privilegeCode = privilegeCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

	public AccountRoleInfo toInfo() {
		AccountRoleInfo roleInfo = new AccountRoleInfo();
		roleInfo.setId(getId());
		roleInfo.setName(name);
		roleInfo.setStatus(status);
		roleInfo.setPrivilegeCode(privilegeCode);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String createDate = dateFormat.format(getCreateTime());
		roleInfo.setCreateDate(createDate);
		return roleInfo;
	}

}