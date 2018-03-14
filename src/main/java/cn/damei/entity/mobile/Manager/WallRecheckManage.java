
package cn.damei.entity.mobile.Manager;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.common.utils.excel.annotation.ExcelField;
import cn.damei.entity.modules.Order;


public class WallRecheckManage extends DataEntity2<WallRecheckManage> {

	private static final long serialVersionUID = 1L;
	private String storeId;
	private String storeName;
	private String orderacceptarea;
	private String orderaccepName;
	private String projectMode;
	private String itemManager;
	private String orderNmber;
	private String designerName;
	private String coveredAdd;
	private String coveredAdd1;
	private Integer orderId;
	private Double squareBudget;
	private Double squareQuota;
	private Double squarePurchase;
	private Double squareMeasure;
	private String auditorName;

	private String squareBudgetString;
	private String squareQuotaString;
	private String squarePurchaseString;
	private String squareMeasureString;

	private Date planMeasureDate;
	private Date realMeasureDate;
	private String measureRemarks;
	private Double price;
	private Double assessSquareError1;
	private Double assessSquareError2;
	private Double assessAmount1;
	private Double assessAmount2;

	private String priceString;
	private String assessSquareError1String;
	private String assessSquareError2String;
	private String assessAmount1String;
	private String assessAmount2String;
	private String assessAmountString1;
	private String assessAmountString2;
	private String assessPersonName1;
	private String assessPersonName2;
	private String status;
	private String statusDescribe;
	private Date statusDatetime;

	private Integer statusOperateEmployeeId;
	private String recheckRemarks;
	private Order order = new Order();
	private Date contractenddate;
	private String customername;
	private String orderinspector;
	private String increase;
	private String picUrl;
	private String isScrap;

	public WallRecheckManage() {
		super();
	}

	public WallRecheckManage(Integer id) {
		super(id);
	}

	@Length(min = 0, max = 11, message = "订单ID长度必须介于 0 和 11 之间")
	public Integer getOrderId() {
		return orderId;
	}

	@ExcelField(title = "地址", align = 2, sort = 35)
	public String getCoveredAdd1() {
		return coveredAdd1;
	}

	@ExcelField(title = "是否作废", dictType = "dict_iscountsquare", align = 2, sort = 90)
	public String getIsScrap() {
		return isScrap;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	@ExcelField(title = "门店", align = 2, sort = 10)
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@ExcelField(title = "区域", align = 2, sort = 15)
	public String getOrderaccepName() {
		return orderaccepName;
	}

	public void setOrderaccepName(String orderaccepName) {
		this.orderaccepName = orderaccepName;
	}

	@ExcelField(title = "考核金额一", align = 2, sort = 80)
	public String getAssessAmountString1() {
		return assessAmountString1;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setAssessAmountString1(String assessAmountString1) {
		this.assessAmountString1 = assessAmountString1;
	}

	@ExcelField(title = "考核金额二", align = 2, sort = 85)
	public String getAssessAmountString2() {
		return assessAmountString2;
	}

	public void setAssessAmountString2(String assessAmountString2) {
		this.assessAmountString2 = assessAmountString2;
	}

	@ExcelField(title = "预算面积", align = 2, sort = 60)
	public String getSquareBudgetString() {
		return getSquareBudget() + "";
	}

	public void setSquareBudgetString(String squareBudgetString) {
		this.squareBudgetString = squareBudgetString;
	}

	public String getSquareQuotaString() {
		return squareQuotaString;
	}

	public void setSquareQuotaString(String squareQuotaString) {
		this.squareQuotaString = squareQuotaString;
	}

	@ExcelField(title = "实际下单面积", align = 2, sort = 70)
	public String getSquarePurchaseString() {
		return getSquarePurchase() == null ? "" : getSquarePurchase() + "";
	}

	public void setSquarePurchaseString(String squarePurchaseString) {
		this.squarePurchaseString = squarePurchaseString;
	}

	@ExcelField(title = "实测面积", align = 2, sort = 65)
	public String getSquareMeasureString() {
		return getSquareMeasure() == null ? "" : getSquareMeasure() + "";
	}

	public void setSquareMeasureString(String squareMeasureString) {
		this.squareMeasureString = squareMeasureString;
	}

	@ExcelField(title = "墙地砖单价", align = 2, sort = 75)
	public String getPriceString() {
		return getPrice() + "";
	}

	public void setPriceString(String priceString) {
		this.priceString = priceString;
	}

	public String getAssessSquareError1String() {
		return assessSquareError1String;
	}

	public void setAssessSquareError1String(String assessSquareError1String) {
		this.assessSquareError1String = assessSquareError1String;
	}

	public String getAssessSquareError2String() {
		return assessSquareError2String;
	}

	public void setAssessSquareError2String(String assessSquareError2String) {
		this.assessSquareError2String = assessSquareError2String;
	}

	public String getAssessAmount1String() {
		return getAssessAmount1() + "";
	}

	public void setAssessAmount1String(String assessAmount1String) {
		this.assessAmount1String = assessAmount1String;
	}

	public String getAssessAmount2String() {
		return getAssessAmount2() + "";
	}

	public void setAssessAmount2String(String assessAmount2String) {
		this.assessAmount2String = assessAmount2String;
	}

	public void setCoveredAdd1(String coveredAdd1) {
		this.coveredAdd1 = coveredAdd1;
	}

	@ExcelField(title = "序号", align = 1, sort = 5)
	public String getIncrease() {
		return increase;
	}

	public void setIncrease(String increase) {
		this.increase = increase;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getSquareBudget() {
		return squareBudget;
	}

	public void setSquareBudget(Double squareBudget) {
		this.squareBudget = squareBudget;
	}

	public String getCoveredAdd() {
		return coveredAdd;
	}

	public void setCoveredAdd(String coveredAdd) {
		this.coveredAdd = coveredAdd;
	}

	@ExcelField(title = "完工日期", align = 2, sort = 55)
	public Date getContractenddate() {
		return contractenddate;
	}

	public void setContractenddate(Date contractenddate) {
		this.contractenddate = contractenddate;
	}

	@ExcelField(title = "业主", align = 2, sort = 30)
	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	@ExcelField(title = "审计员", align = 2, sort = 45)
	public String getOrderinspector() {
		return orderinspector;
	}

	public void setOrderinspector(String orderinspector) {
		this.orderinspector = orderinspector;
	}

	public Order getOrder() {
		return order;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getOrderacceptarea() {
		return orderacceptarea;
	}

	public void setOrderacceptarea(String orderacceptarea) {
		this.orderacceptarea = orderacceptarea;
	}

	@ExcelField(title = "工程模式", dictType = "project_mode", align = 2, sort = 20)
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	@ExcelField(title = "项目经理", align = 2, sort = 50)
	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	@ExcelField(title = "设计师", align = 2, sort = 25)
	public String getOrderNmber() {
		return orderNmber;
	}

	public void setOrderNmber(String orderNmber) {
		this.orderNmber = orderNmber;
	}

	@ExcelField(title = "设计师", align = 2, sort = 40)
	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Double getSquareQuota() {
		return squareQuota;
	}

	public void setSquareQuota(Double squareQuota) {
		this.squareQuota = squareQuota;
	}

	public Double getSquarePurchase() {
		return squarePurchase;
	}

	public void setSquarePurchase(Double squarePurchase) {
		this.squarePurchase = squarePurchase;
	}

	public Double getSquareMeasure() {
		return squareMeasure;
	}

	public void setSquareMeasure(Double squareMeasure) {
		this.squareMeasure = squareMeasure;
	}

	public Date getPlanMeasureDate() {
		return planMeasureDate;
	}

	public void setPlanMeasureDate(Date planMeasureDate) {
		this.planMeasureDate = planMeasureDate;
	}

	public Date getRealMeasureDate() {
		return realMeasureDate;
	}

	public void setRealMeasureDate(Date realMeasureDate) {
		this.realMeasureDate = realMeasureDate;
	}

	public String getMeasureRemarks() {
		return measureRemarks;
	}

	public void setMeasureRemarks(String measureRemarks) {
		this.measureRemarks = measureRemarks;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAssessSquareError1() {
		return assessSquareError1;
	}

	public void setAssessSquareError1(Double assessSquareError1) {
		this.assessSquareError1 = assessSquareError1;
	}

	public Double getAssessSquareError2() {
		return assessSquareError2;
	}

	public void setAssessSquareError2(Double assessSquareError2) {
		this.assessSquareError2 = assessSquareError2;
	}

	public Double getAssessAmount1() {
		return assessAmount1;
	}

	public void setAssessAmount1(Double assessAmount1) {
		this.assessAmount1 = assessAmount1;
	}

	public Double getAssessAmount2() {
		return assessAmount2;
	}

	public void setAssessAmount2(Double assessAmount2) {
		this.assessAmount2 = assessAmount2;
	}

	@ExcelField(title = "被考核人一", align = 2, sort = 88)
	public String getAssessPersonName1() {
		return assessPersonName1;
	}

	public void setAssessPersonName1(String assessPersonName1) {
		this.assessPersonName1 = assessPersonName1;
	}

	@ExcelField(title = "被考核人二", align = 2, sort = 88)
	public String getAssessPersonName2() {
		return assessPersonName2;
	}

	public void setAssessPersonName2(String assessPersonName2) {
		this.assessPersonName2 = assessPersonName2;
	}

	@ExcelField(title = "状态", dictType = "wall_status", align = 2, sort = 100)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDescribe() {
		return statusDescribe;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}

	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

	public Integer getStatusOperateEmployeeId() {
		return statusOperateEmployeeId;
	}

	public void setStatusOperateEmployeeId(Integer statusOperateEmployeeId) {
		this.statusOperateEmployeeId = statusOperateEmployeeId;
	}

	@Length(min = 0, max = 1000, message = "复尺备注长度必须介于 0 和 1000 之间")
	@ExcelField(title = "备注", align = 2, sort = 90)
	public String getRecheckRemarks() {
		return recheckRemarks;
	}

	public void setRecheckRemarks(String recheckRemarks) {
		this.recheckRemarks = recheckRemarks;
	}

}