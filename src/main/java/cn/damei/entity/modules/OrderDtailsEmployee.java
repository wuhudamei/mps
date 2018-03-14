package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class OrderDtailsEmployee extends DataEntity2<OrderDtailsEmployee>{

	/**
	 * @author wang
	 * @version 2016-10-10
	 */
	private static final long serialVersionUID = 1L;
	private String realName; // 真实姓名
	private String phone; // 手机号
	private String name; // 员工类型
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public OrderDtailsEmployee() {
		super();
	}
	public OrderDtailsEmployee(String realName, String phone, String name) {
		super();
		this.realName = realName;
		this.phone = phone;
		this.name = name;
	}
	
}
