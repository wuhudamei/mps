/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;



import cn.damei.common.persistence.DataEntity2;

/**
 * 供应商工人组Entity
 * @author wyb
 * @version 2017-07-13
 */
public class SupplierInstallWorker extends DataEntity2<SupplierInstallWorker> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer installBillId;		// 安装单id
	private Integer employeegroupId;		// 工人组id
	private Integer orderstop;		// 是否停单
	private String address;		// 现住址
	private Integer state;		// 是否删除状态
	private Integer workerId;  // 组长id
	private String workerName;		// 组长姓名
	private String workerPhone;		// 组长电话
	private Integer workerNumber;		// 组内成员数
	private Integer supplierInstallConstrunctBillCount;		// 当前未完工安装项
	private Double distance;		// 距离施工地点（米）
	
	
	public SupplierInstallWorker() {
		super();
	}

	public SupplierInstallWorker(Integer id){
		super(id);
	}

	public Integer getEmployeegroupId() {
		return employeegroupId;
	}

	public void setEmployeegroupId(Integer employeegroupId) {
		this.employeegroupId = employeegroupId;
	}

	public Integer getOrderstop() {
		return orderstop;
	}

	public void setOrderstop(Integer orderstop) {
		this.orderstop = orderstop;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getWorkerPhone() {
		return workerPhone;
	}

	public void setWorkerPhone(String workerPhone) {
		this.workerPhone = workerPhone;
	}

	public Integer getWorkerNumber() {
		return workerNumber;
	}

	public void setWorkerNumber(Integer workerNumber) {
		this.workerNumber = workerNumber;
	}

	public Integer getSupplierInstallConstrunctBillCount() {
		return supplierInstallConstrunctBillCount;
	}

	public void setSupplierInstallConstrunctBillCount(Integer supplierInstallConstrunctBillCount) {
		this.supplierInstallConstrunctBillCount = supplierInstallConstrunctBillCount;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getInstallBillId() {
		return installBillId;
	}

	public void setInstallBillId(Integer installBillId) {
		this.installBillId = installBillId;
	}

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}
	
	
	
}