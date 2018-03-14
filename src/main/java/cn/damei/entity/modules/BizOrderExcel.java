/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 材料文件上传Entity
 * @author wyb
 * @version 2016-09-08
 */
public class BizOrderExcel extends DataEntity<BizOrderExcel> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单id
	private String fileName;		// excel文件
	private Integer version;		// 文件版本
	private String filePath;		// file_path
	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public BizOrderExcel() {
		super();
	}

	public BizOrderExcel(String id){
		super(id);
	}

	@Length(min=1, max=100, message="订单id长度必须介于 1 和 100 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=1, max=100, message="excel文件长度必须介于 1 和 100 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	@Length(min=0, max=1000, message="file_path长度必须介于 0 和 1000 之间")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}