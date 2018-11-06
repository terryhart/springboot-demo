 package com.bbsuper.nev.beans.enums;

import java.util.List;

import com.bbsuper.nev.beans.vo.user.Privilege;
import com.google.common.collect.Lists;

/**
 * 权限枚举，目前写死
 * @author liwei
 * @date: 2018年10月25日 下午1:40:48
 *
 */
public enum PrivilegeEnum {
	
	A1(1001,"意向客户","/customer/interesting"),
	A2(1002,"跟进客户","/customer/follow"),
	A3(1003,"已售客户","/customer/sold"),
	A4(1004,"放弃客户","/customer/discard"),
	
	B1(1101,"库存车辆","/vehicle/repertory"),
	B2(1102,"已售车辆","/vehicle/sold"),
	B3(1103,"退回车辆","/vehicle/back"),
	
	C1(1201,"收款列表","/finance/receipt"),
	C2(1202,"退款列表","/finance/refund"),
	
	D1(1301,"账号列表","/system/account"),
	D2(1302,"角色管理","/system/role"),
	D3(1303,"开埠城市","/system/city"),
	D4(1304,"车辆类目","/system/vehicleType");
	
	private int code;
	
	private String desc;
	
	private String uri;

	private PrivilegeEnum(int code, String desc, String uri) {
		this.code = code;
		this.desc = desc;
		this.uri = uri;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public String getUri() {
		return uri;
	}

	public static List<Privilege> getAllPrivilege(){
		List<Privilege> privileges = Lists.newArrayList();
		PrivilegeEnum[] codes  = values();
		for(PrivilegeEnum code:codes){
			privileges.add(new Privilege(code));
		}
		return privileges;
	}
	
	public static List<Privilege> getPrivilegeByCodes(String[] codes){
		List<Privilege> privileges = Lists.newArrayList();
		for(String code:codes){
			PrivilegeEnum privilegeEnum = getPrivilegeByCode(Integer.parseInt(code));
			privileges.add(new Privilege(privilegeEnum));
		}
		return privileges;
	}
	
	public static PrivilegeEnum getPrivilegeByCode(int code){
		PrivilegeEnum[] values = PrivilegeEnum.values();
			for(PrivilegeEnum value : values){
				if(value.code==code){
					return value;
				}
		}
		throw new RuntimeException("not exist privilege code:"+code);
	}
	
}
