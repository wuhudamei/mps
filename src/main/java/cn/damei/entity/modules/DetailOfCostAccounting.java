package cn.damei.entity.modules;

import java.text.DecimalFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.damei.common.persistence.DataEntity2;

/** 
* @ClassName: DetailOfCostAccounting 
* @Description: TODO(成本核算明细实体类) 
* @author zkj  
* @date 2017年10月18日 上午11:44:46 
* @version V1.0 
*/
public class DetailOfCostAccounting extends DataEntity2<DetailOfCostAccounting>{
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String projectMode;
	private String storeId;
	//项目经理提成金额
	private double managerAmount;
	//门店名称
	private String storeName;
	//区域ID
	private String engineDepartId;
	//区域名称
	private String departmentName; 
	//工程模式
	private String projectModeName;
	//订单编号
	private String orderNumber;
	//房屋类型
	private String buildName;
	//合同面积
	private String contractArea;
	//合同开工时间
	private Date contractStartDate;
	//合同竣工时间
	private Date contractEndDate;
	//项目经理名称
	private String itemManager;
	//客户姓名
	private String customerName;
	//合同金额
	private double contractAmount;
	//订单状态
	private String orderStatusNumber;
	//订单状态说明
	private String orderStatusDescription;
	//实际开工时间
	private Date startActualStartDate;
	private Date actualStartDate;
	private Date endActualStartDate;
	//人工成本费用
	private double shuidian;
	private double chaichu;
	private double yingxiao;
	private double teshu;
	private double wagong;
	private double mugong;
	private double yougong;
	private double anzhuang;
	//沙子水泥
	private double sandCement;
	//辅料费用
	private double fuliaoshuidian;
	private double fuliaochaichu;
	private double fuliaoyingxiao;
	private double fuliaoteshu;
	private double fuliaowagong;
	private double fuliaomugong;
	private double fuliaoyougong;
	private double fuliaoanzhuang;
	DecimalFormat df = new DecimalFormat("#0.00");  
	
	
	public String getFuliaoheji(){
		double temp = fuliaoshuidian + fuliaochaichu + fuliaoyingxiao + fuliaoteshu + fuliaowagong + fuliaomugong + fuliaoyougong;
		return df.format(temp);
	}
	
	public String getRengongheji(){
		double temp = shuidian +  chaichu + yingxiao + teshu + wagong + mugong + yougong + anzhuang;
		return df.format(temp);
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public void setManagerAmount(double managerAmount) {
		this.managerAmount = managerAmount;
	}
	public void setContractAmount(double contractAmount) {
		this.contractAmount = contractAmount;
	}
	public double getSandCement() {
		return sandCement;
	}
	public void setSandCement(double sandCement) {
		this.sandCement = sandCement;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public String getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(String engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public double getManagerAmount() {
		return managerAmount;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getProjectModeName() {
		return projectModeName;
	}
	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public String getContractArea() {
		return contractArea;
	}
	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}
	
	public Date getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public Date getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
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
	public double getContractAmount() {
		return contractAmount;
	}
	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}
	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}
	public String getOrderStatusDescription() {
		return orderStatusDescription;
	}
	public void setOrderStatusDescription(String orderStatusDescription) {
		this.orderStatusDescription = orderStatusDescription;
	}
	public Date getStartActualStartDate() {
		return startActualStartDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setStartActualStartDate(Date startActualStartDate) {
		this.startActualStartDate = startActualStartDate;
	}
	public Date getEndActualStartDate() {
		return endActualStartDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void setEndActualStartDate(Date endActualStartDate) {
		this.endActualStartDate = endActualStartDate;
	}
	public double getShuidian() {
		return shuidian;
	}
	public void setShuidian(double shuidian) {
		this.shuidian = shuidian;
	}
	public double getChaichu() {
		return chaichu;
	}
	public void setChaichu(double chaichu) {
		this.chaichu = chaichu;
	}
	public double getYingxiao() {
		return yingxiao;
	}
	public void setYingxiao(double yingxiao) {
		this.yingxiao = yingxiao;
	}
	public double getTeshu() {
		return teshu;
	}
	public void setTeshu(double teshu) {
		this.teshu = teshu;
	}
	public double getWagong() {
		return wagong;
	}
	public void setWagong(double wagong) {
		this.wagong = wagong;
	}
	public double getMugong() {
		return mugong;
	}
	public void setMugong(double mugong) {
		this.mugong = mugong;
	}
	public double getYougong() {
		return yougong;
	}
	public void setYougong(double yougong) {
		this.yougong = yougong;
	}
	public double getAnzhuang() {
		return anzhuang;
	}
	public void setAnzhuang(double anzhuang) {
		this.anzhuang = anzhuang;
	}
	public double getFuliaoshuidian() {
		return fuliaoshuidian;
	}
	public void setFuliaoshuidian(double fuliaoshuidian) {
		this.fuliaoshuidian = fuliaoshuidian;
	}
	public double getFuliaochaichu() {
		return fuliaochaichu;
	}
	public void setFuliaochaichu(double fuliaochaichu) {
		this.fuliaochaichu = fuliaochaichu;
	}
	public double getFuliaoyingxiao() {
		return fuliaoyingxiao;
	}
	public void setFuliaoyingxiao(double fuliaoyingxiao) {
		this.fuliaoyingxiao = fuliaoyingxiao;
	}
	public double getFuliaoteshu() {
		return fuliaoteshu;
	}
	public void setFuliaoteshu(double fuliaoteshu) {
		this.fuliaoteshu = fuliaoteshu;
	}
	public double getFuliaowagong() {
		return fuliaowagong;
	}
	public void setFuliaowagong(double fuliaowagong) {
		this.fuliaowagong = fuliaowagong;
	}
	public double getFuliaomugong() {
		return fuliaomugong;
	}
	public void setFuliaomugong(double fuliaomugong) {
		this.fuliaomugong = fuliaomugong;
	}
	public double getFuliaoyougong() {
		return fuliaoyougong;
	}
	public void setFuliaoyougong(double fuliaoyougong) {
		this.fuliaoyougong = fuliaoyougong;
	}
	public double getFuliaoanzhuang() {
		return fuliaoanzhuang;
	}
	public void setFuliaoanzhuang(double fuliaoanzhuang) {
		this.fuliaoanzhuang = fuliaoanzhuang;
	}

}
