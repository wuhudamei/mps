/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 订单图片Entity
 * @author mh
 * @version 2016-09-08
 */
public class OrderCadfile extends DataEntity2<OrderCadfile> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// 所有订单id
	private String orderNumber;//订单编号
	private String displayFileName;		// 订单图纸文件名-显示
	private String fileName;		// 订单图纸文件名-系统生成的文件名
	private String filePath;		// 订单图纸文件路径
	private Integer version;		// 文件版本
	private String flag;
	
	
	
	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public OrderCadfile() {
		super();
	}

	public OrderCadfile(Integer id){
		super(id);
	}



	public Integer getOrderId() {
		
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Length(min=1, max=200, message="订单图纸文件名-显示长度必须介于 1 和 200 之间")
	public String getDisplayFileName() {
		return displayFileName;
	}

	public void setDisplayFileName(String displayFileName) {
		this.displayFileName = displayFileName;
	}
	
	@Length(min=1, max=200, message="订单图纸文件名-系统生成的文件名长度必须介于 1 和 200 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Length(min=1, max=1000, message="订单图纸文件路径长度必须介于 1 和1000 之间")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}