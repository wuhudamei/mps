package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;


public class AppOrderCadfile extends DataEntity2<AppOrderCadfile>{

	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private String displayFileName;
	private String fileName;
	private String filePath;
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getDisplayFileName() {
		return displayFileName;
	}
	public void setDisplayFileName(String displayFileName) {
		this.displayFileName = displayFileName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
}
