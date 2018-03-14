
package cn.damei.entity.modules;



import cn.damei.common.persistence.DataEntity2;


public class SupplierInstallWorker extends DataEntity2<SupplierInstallWorker> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer installBillId;
	private Integer employeegroupId;
	private Integer orderstop;
	private String address;
	private Integer state;
	private Integer workerId;
	private String workerName;
	private String workerPhone;
	private Integer workerNumber;
	private Integer supplierInstallConstrunctBillCount;
	private Double distance;
	
	
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