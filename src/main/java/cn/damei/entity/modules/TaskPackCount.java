package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class TaskPackCount extends DataEntity<TaskPackCount>{

	

	private static final long serialVersionUID = 1L;
	private String storeId;
	private String area;
	private String employeName;
	
	private String orderNumber;
	
	private String itemManager;
	
	private String customerName;
	
	private String orderTaskPackageCode;
	
	private String packageName;
	
	private Date taskPackStatus10;
	private Date taskPackStatus20;
	private Date taskPackStatus50;
	private Date taskPackStatus55;
	private Date taskPackStatus60;
	private Date taskPackStatus70;
	private Date taskPackStatus80;
	
	private String planStartdate;
	private String planEnddate;
	private String isScrap;
	private Date scrapDatetime;
	
	
	
	public Date getScrapDatetime() {
		return scrapDatetime;
	}
	public void setScrapDatetime(Date scrapDatetime) {
		this.scrapDatetime = scrapDatetime;
	}
	public String getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getOrderTaskPackageCode() {
		return orderTaskPackageCode;
	}
	public void setOrderTaskPackageCode(String orderTaskPackageCode) {
		this.orderTaskPackageCode = orderTaskPackageCode;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	
	
	
	
	public Date getTaskPackStatus10() {
		return taskPackStatus10;
	}
	public void setTaskPackStatus10(Date taskPackStatus10) {
		this.taskPackStatus10 = taskPackStatus10;
	}
	public Date getTaskPackStatus20() {
		return taskPackStatus20;
	}
	public void setTaskPackStatus20(Date taskPackStatus20) {
		this.taskPackStatus20 = taskPackStatus20;
	}
	public Date getTaskPackStatus50() {
		return taskPackStatus50;
	}
	public void setTaskPackStatus50(Date taskPackStatus50) {
		this.taskPackStatus50 = taskPackStatus50;
	}
	public Date getTaskPackStatus55() {
		return taskPackStatus55;
	}
	public void setTaskPackStatus55(Date taskPackStatus55) {
		this.taskPackStatus55 = taskPackStatus55;
	}
	public Date getTaskPackStatus60() {
		return taskPackStatus60;
	}
	public void setTaskPackStatus60(Date taskPackStatus60) {
		this.taskPackStatus60 = taskPackStatus60;
	}
	public Date getTaskPackStatus70() {
		return taskPackStatus70;
	}
	public void setTaskPackStatus70(Date taskPackStatus70) {
		this.taskPackStatus70 = taskPackStatus70;
	}
	public Date getTaskPackStatus80() {
		return taskPackStatus80;
	}
	public void setTaskPackStatus80(Date taskPackStatus80) {
		this.taskPackStatus80 = taskPackStatus80;
	}
	

	public String getPlanStartdate() {
		return planStartdate;
	}
	public void setPlanStartdate(String planStartdate) {
		this.planStartdate = planStartdate;
	}
	public String getPlanEnddate() {
		return planEnddate;
	}
	public void setPlanEnddate(String planEnddate) {
		this.planEnddate = planEnddate;
	}






	private Integer shuidian = 0;
	private Integer wagong = 0;
	private Integer mugong = 0;
	private Integer yougong = 0;
	private Integer chaichu = 0;
	private Integer anzhuang = 0;
	private Integer teshu = 0;
	private Integer yingxiao = 0;
	private Integer count = 0;
	
	public Integer getYingxiao() {
		return yingxiao;
	}
	public void setYingxiao(Integer yingxiao) {
		this.yingxiao = yingxiao;
	}
	public Integer getTeshu() {
		return teshu;
	}
	public void setTeshu(Integer teshu) {
		this.teshu = teshu;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getEmployeName() {
		return employeName;
	}
	public void setEmployeName(String employeName) {
		this.employeName = employeName;
	}
	public Integer getShuidian() {
		return shuidian;
	}
	public void setShuidian(Integer shuidian) {
		this.shuidian = shuidian;
	}
	public Integer getWagong() {
		return wagong;
	}
	public void setWagong(Integer wagong) {
		this.wagong = wagong;
	}
	
	public Integer getMugong() {
		return mugong;
	}
	public void setMugong(Integer mugong) {
		this.mugong = mugong;
	}
	public Integer getYougong() {
		return yougong;
	}
	public void setYougong(Integer yougong) {
		this.yougong = yougong;
	}
	public Integer getChaichu() {
		return chaichu;
	}
	public void setChaichu(Integer chaichu) {
		this.chaichu = chaichu;
	}
	public Integer getAnzhuang() {
		return anzhuang;
	}
	public void setAnzhuang(Integer anzhuang) {
		this.anzhuang = anzhuang;
	}
	public Integer getCount() {
		return shuidian + wagong + mugong + yougong + chaichu + anzhuang + teshu;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
