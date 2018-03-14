/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质检管理人员Entity
 * @author wyb
 * @version 2016-11-02
 */
public class BizQcManager extends DataEntity2<BizQcManager> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private Integer managerEmployeeId;		// 管理人员员工id -- '
	private Date generatedDatetime;		// 生成日期时间 -- '
	private String isEnabled;		// 是否启用 -- '0.停用；1.启用
	private String no;	//人员编号
	private String realName;	//人员姓名
	private String phone;	//人员电话
	private String projectMode; //工程模式
	public BizQcManager() {
		super();
	}

	public BizQcManager(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getManagerEmployeeId() {
		return managerEmployeeId;
	}

	public void setManagerEmployeeId(Integer managerEmployeeId) {
		this.managerEmployeeId = managerEmployeeId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGeneratedDatetime() {
		return generatedDatetime;
	}

	public void setGeneratedDatetime(Date generatedDatetime) {
		this.generatedDatetime = generatedDatetime;
	}
	
	
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
}