/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

/**
 * 手机app版本Entity
 * @author qww
 * @version 2016-12-27
 */
public class SysAppVersion extends DataEntity2<SysAppVersion> {
	
	private static final long serialVersionUID = 1L;
	private String appType;		// app类型 -- '
	private String phoneType;		// 手机类型 -- '
	private String version;		// 版本号 -- '
	private String downloadUrl;		// 下载url -- '
	private Integer uploadEmployeeId;		// 上传人 -- '
	private Date uploadDatetime;		// 上传日期时间 -- '
	private String isEnabled;		// 是否可用 -- '

	private String appName;
	private String type;
	private String uploadEmployeeName;
	private Date beginUploadDatetime;
	private Date endUploadDatetime;
	
	public SysAppVersion() {
		super();
	}

	public SysAppVersion(Integer id){
		super(id);
	}

	@Length(min=0, max=10, message="app类型 -- '长度必须介于 0 和 10 之间")
	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}
	
	@Length(min=0, max=10, message="手机类型 -- '长度必须介于 0 和 10 之间")
	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	
	@Length(min=0, max=20, message="版本号 -- '长度必须介于 0 和 20 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Length(min=0, max=200, message="下载url -- '长度必须介于 0 和 200 之间")
	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
	public Integer getUploadEmployeeId() {
		return uploadEmployeeId;
	}

	public void setUploadEmployeeId(Integer uploadEmployeeId) {
		this.uploadEmployeeId = uploadEmployeeId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUploadDatetime() {
		return uploadDatetime;
	}

	public void setUploadDatetime(Date uploadDatetime) {
		this.uploadDatetime = uploadDatetime;
	}
	
	@Length(min=0, max=1, message="是否可用 -- '长度必须介于 0 和 1 之间")
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUploadEmployeeName() {
		return uploadEmployeeName;
	}

	public void setUploadEmployeeName(String uploadEmployeeName) {
		this.uploadEmployeeName = uploadEmployeeName;
	}

	public Date getBeginUploadDatetime() {
		return beginUploadDatetime;
	}

	public void setBeginUploadDatetime(Date beginUploadDatetime) {
		this.beginUploadDatetime = beginUploadDatetime;
	}

	public Date getEndUploadDatetime() {
		return endUploadDatetime;
	}

	public void setEndUploadDatetime(Date endUploadDatetime) {
		this.endUploadDatetime = endUploadDatetime;
	}
}