
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class OrderCadfile extends DataEntity2<OrderCadfile> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private String orderNumber;
	private String displayFileName;
	private String fileName;
	private String filePath;
	private Integer version;
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