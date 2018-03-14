
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;



public class BizOrderInstallItemReport extends DataEntity2<BizOrderInstallItemReport> {
	
	private static final long serialVersionUID = 1L;
	private Integer bizOrderInstallItemReportId;
	private Integer enginDepartId;
	private Integer orderId;
	private String orderNumber;
	private String customerName;
	private String customerPhone;
	private String pmName;
	private Date actualStartDate;
	private String enginDepartName;
	private Date beginActualStartDate;
	private Date endActualStartDate;
	private Integer storeId;
	
	

	private Date checksizeApplyDatetimeChugui;
	private Date installItemApplyDatetimeChugui;
	private Date installItemApplyIntoDateChugui;
	private Date installItemRealIntoDateChugui;
	private Date installItemRealAcceptDateChugui;
	private String installItemIsReturnedChugui;
	private String installItemProblemDescribeChugui;
	private Date installItemProblemApplyDatetimeChugui;
	private Date installItemProblemSolveDatetimeChugui;
	private Double spendTimeChugui;
	
	

	private Date checksizeApplyDatetimeFangmen;
	private Date installItemApplyDatetimeFangmen;
	private Date installItemApplyIntoDateFangmen;
	private Date installItemRealIntoDateFangmen;
	private Date installItemRealAcceptDateFangmen;
	private String installItemIsReturnedFangmen;
	private String installItemProblemDescribeFangmen;
	private Date installItemProblemApplyDatetimeFangmen;
	private Date installItemProblemSolveDatetimeFangmen;
	private Double spendTimeFangmen;
	
	
	

	private Date checksizeApplyDatetimeJieju;
	private Date installItemApplyDatetimeJieju;
	private Date installItemApplyIntoDateJieju;
	private Date installItemRealIntoDateJieju;
	private Date installItemRealAcceptDateJieju;
	private String installItemIsReturnedJieju;
	private String installItemProblemDescribeJieju;
	private Date installItemProblemApplyDatetimeJieju;
	private Date installItemProblemSolveFatetimeJieju;
	private Double spendTimeJieju;
	
	

	private Date checksizeApplyDatetimeBizhi;
	private Date installItemApplyDatetimeBizhi;
	private Date installItemApplyIntoDateBizhi;
	private Date installItemRealIntoDateBizhi;
	private Date installItemRealCompleteDateBizhi;
	private String installItemIsReturnedBizhi;
	private String installItemProblemDescribeBizhi;
	private Date installItemProblemApplyDatetimeBizhi;
	private Date installItemProblemSolveDatetimeBizhi;
	private Double spendTimeBizhi;
	
	

	private Date checksizeApplyDatetimeMudiban;
	private Date installItemApplyDatetimeMudiban;
	private Date installItemApplyIntoDateMudiban;
	private Date installItemRealIntoDateMudiban;
	private Date installItemRealAcceptDateMudiban;
	private String installItemIsReturnedMudiban;
	private String installItemProblemDescribeMudiban;
	private Date installItemProblemApplyDatetimeMudiban;
	private Date installItemProblemSolveDatetimeMudiban;
	private Double spendTimeMudiban;

	

	private Date checksizeApplyDatetimeDiaoding;
	private Date installItemApplyDatetimeDiaoding;
	private Date installItemApplyIntoDateDiaoding;
	private Date installItemRealIntoDateDiaoding;
	private Date installItemRealAcceptDateDiaoding;
	private String installItemIsReturnedDiaoding;
	private String installItemProblemDescribeDiaoding;
	private Date installItemProblemApplyDatetimeDiaoding;
	private Date installItemProblemSolveDatetimeDiaoding;
	private Double spendTimeDiaoding;

	

	private Date checksizeApplyDatetimeDengju;
	private Date installItemApplyDatetimeDengju;
	private Date installItemApplyIntoDateDengju;
	private Date installItemRealIntoDateDengju;
	private Date installItemRealAcceptDateDengju;
	private String installItemIsReturnedDengju;
	private String installItemProblemDescribeDengju;
	private Date installItemProblemApplyDatetimeDengju;
	private Date installItemProblemSolveDatetimeDengju;
	private Double spendTimeDengju;
	
	

	private Date checksizeApplyDatetimeChuanglian;
	private Date installItemApplyDatetimeChuanglian;
	private Date installItemApplyIntoDateChuanglian;
	private Date installItemRealIntoDateChuanglian;
	private Date installItemRealAcceptDateChuanglian;
	private String installItemIsReturnedChuanglian;
	private String installItemProblemDescribeChuanglian;
	private Date installItemProblemApplyDatetimeChuanglian;
	private Date installItemProblemSolveDatetimeChuanglian;
	private Double spendTimeChuanglian;

	

	private Date checksizeApplyDatetimeLinyufang;
	private Date installItemApplyDatetimeLinyufang;
	private Date installItemApplyIntoDateLinyufang;
	private Date installItemRealIntoDateLinyufang;
	private Date installItemRealAcceptDateLinyufang;
	private String installItemIsReturnedLinyufang;
	private String installItemProblemDescribeLinyufang;
	private Date installItemProblemApplyDatetimeLinyufang;
	private Date installItemProblemSolveDatetimeLinyufang;
	private Double spendTimeLinyufang;

	

	private Date checksizeApplyDatetimeYigui;
	private Date installItemApplyDatetimeYigui;
	private Date installItemApplyIntoDateYigui;
	private Date installItemRealIntoDateYigui;
	private Date installItemRealAcceptDateYigui;
	private String installItemIsReturnedYigui;
	private String installItemProblemDescribeYigui;
	private Date installItemProblemApplyDatetimeYigui;
	private Date installItemProblemSolveDatetimeYigui;
	private Double spendTimeYigui;
	

	public BizOrderInstallItemReport() {
		super();
	}

	public BizOrderInstallItemReport(Integer id){
		super(id);
	}

	
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getBizOrderInstallItemReportId() {
		return bizOrderInstallItemReportId;
	}

	public void setBizOrderInstallItemReportId(Integer bizOrderInstallItemReportId) {
		this.bizOrderInstallItemReportId = bizOrderInstallItemReportId;
	}


	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}

	public Date getChecksizeApplyDatetimeChugui() {
		return checksizeApplyDatetimeChugui;
	}

	public void setChecksizeApplyDatetimeChugui(Date checksizeApplyDatetimeChugui) {
		this.checksizeApplyDatetimeChugui = checksizeApplyDatetimeChugui;
	}

	public Date getInstallItemApplyDatetimeChugui() {
		return installItemApplyDatetimeChugui;
	}

	public void setInstallItemApplyDatetimeChugui(Date installItemApplyDatetimeChugui) {
		this.installItemApplyDatetimeChugui = installItemApplyDatetimeChugui;
	}

	public Date getInstallItemApplyIntoDateChugui() {
		return installItemApplyIntoDateChugui;
	}

	public void setInstallItemApplyIntoDateChugui(Date installItemApplyIntoDateChugui) {
		this.installItemApplyIntoDateChugui = installItemApplyIntoDateChugui;
	}

	public Date getInstallItemRealIntoDateChugui() {
		return installItemRealIntoDateChugui;
	}

	public void setInstallItemRealIntoDateChugui(Date installItemRealIntoDateChugui) {
		this.installItemRealIntoDateChugui = installItemRealIntoDateChugui;
	}

	public Date getInstallItemRealAcceptDateChugui() {
		return installItemRealAcceptDateChugui;
	}

	public void setInstallItemRealAcceptDateChugui(Date installItemRealAcceptDateChugui) {
		this.installItemRealAcceptDateChugui = installItemRealAcceptDateChugui;
	}

	public String getInstallItemIsReturnedChugui() {
		return installItemIsReturnedChugui;
	}

	public void setInstallItemIsReturnedChugui(String installItemIsReturnedChugui) {
		this.installItemIsReturnedChugui = installItemIsReturnedChugui;
	}

	public String getInstallItemProblemDescribeChugui() {
		return installItemProblemDescribeChugui;
	}

	public void setInstallItemProblemDescribeChugui(String installItemProblemDescribeChugui) {
		this.installItemProblemDescribeChugui = installItemProblemDescribeChugui;
	}

	public Date getInstallItemProblemApplyDatetimeChugui() {
		return installItemProblemApplyDatetimeChugui;
	}

	public void setInstallItemProblemApplyDatetimeChugui(Date installItemProblemApplyDatetimeChugui) {
		this.installItemProblemApplyDatetimeChugui = installItemProblemApplyDatetimeChugui;
	}

	public Date getInstallItemProblemSolveDatetimeChugui() {
		return installItemProblemSolveDatetimeChugui;
	}

	public void setInstallItemProblemSolveDatetimeChugui(Date installItemProblemSolveDatetimeChugui) {
		this.installItemProblemSolveDatetimeChugui = installItemProblemSolveDatetimeChugui;
	}

	public Double getSpendTimeChugui() {
		return spendTimeChugui;
	}

	public void setSpendTimeChugui(Double spendTimeChugui) {
		this.spendTimeChugui = spendTimeChugui;
	}

	public Date getChecksizeApplyDatetimeFangmen() {
		return checksizeApplyDatetimeFangmen;
	}

	public void setChecksizeApplyDatetimeFangmen(Date checksizeApplyDatetimeFangmen) {
		this.checksizeApplyDatetimeFangmen = checksizeApplyDatetimeFangmen;
	}

	public Date getInstallItemApplyDatetimeFangmen() {
		return installItemApplyDatetimeFangmen;
	}

	public void setInstallItemApplyDatetimeFangmen(Date installItemApplyDatetimeFangmen) {
		this.installItemApplyDatetimeFangmen = installItemApplyDatetimeFangmen;
	}

	public Date getInstallItemApplyIntoDateFangmen() {
		return installItemApplyIntoDateFangmen;
	}

	public void setInstallItemApplyIntoDateFangmen(Date installItemApplyIntoDateFangmen) {
		this.installItemApplyIntoDateFangmen = installItemApplyIntoDateFangmen;
	}

	public Date getInstallItemRealIntoDateFangmen() {
		return installItemRealIntoDateFangmen;
	}

	public void setInstallItemRealIntoDateFangmen(Date installItemRealIntoDateFangmen) {
		this.installItemRealIntoDateFangmen = installItemRealIntoDateFangmen;
	}

	public Date getInstallItemRealAcceptDateFangmen() {
		return installItemRealAcceptDateFangmen;
	}

	public void setInstallItemRealAcceptDateFangmen(Date installItemRealAcceptDateFangmen) {
		this.installItemRealAcceptDateFangmen = installItemRealAcceptDateFangmen;
	}

	public String getInstallItemIsReturnedFangmen() {
		return installItemIsReturnedFangmen;
	}

	public void setInstallItemIsReturnedFangmen(String installItemIsReturnedFangmen) {
		this.installItemIsReturnedFangmen = installItemIsReturnedFangmen;
	}

	public String getInstallItemProblemDescribeFangmen() {
		return installItemProblemDescribeFangmen;
	}

	public void setInstallItemProblemDescribeFangmen(String installItemProblemDescribeFangmen) {
		this.installItemProblemDescribeFangmen = installItemProblemDescribeFangmen;
	}

	public Date getInstallItemProblemApplyDatetimeFangmen() {
		return installItemProblemApplyDatetimeFangmen;
	}

	public void setInstallItemProblemApplyDatetimeFangmen(Date installItemProblemApplyDatetimeFangmen) {
		this.installItemProblemApplyDatetimeFangmen = installItemProblemApplyDatetimeFangmen;
	}

	public Date getInstallItemProblemSolveDatetimeFangmen() {
		return installItemProblemSolveDatetimeFangmen;
	}

	public void setInstallItemProblemSolveDatetimeFangmen(Date installItemProblemSolveDatetimeFangmen) {
		this.installItemProblemSolveDatetimeFangmen = installItemProblemSolveDatetimeFangmen;
	}

	public Double getSpendTimeFangmen() {
		return spendTimeFangmen;
	}

	public void setSpendTimeFangmen(Double spendTimeFangmen) {
		this.spendTimeFangmen = spendTimeFangmen;
	}

	public Date getChecksizeApplyDatetimeJieju() {
		return checksizeApplyDatetimeJieju;
	}

	public void setChecksizeApplyDatetimeJieju(Date checksizeApplyDatetimeJieju) {
		this.checksizeApplyDatetimeJieju = checksizeApplyDatetimeJieju;
	}

	public Date getInstallItemApplyDatetimeJieju() {
		return installItemApplyDatetimeJieju;
	}

	public void setInstallItemApplyDatetimeJieju(Date installItemApplyDatetimeJieju) {
		this.installItemApplyDatetimeJieju = installItemApplyDatetimeJieju;
	}

	public Date getInstallItemApplyIntoDateJieju() {
		return installItemApplyIntoDateJieju;
	}

	public void setInstallItemApplyIntoDateJieju(Date installItemApplyIntoDateJieju) {
		this.installItemApplyIntoDateJieju = installItemApplyIntoDateJieju;
	}

	public Date getInstallItemRealIntoDateJieju() {
		return installItemRealIntoDateJieju;
	}

	public void setInstallItemRealIntoDateJieju(Date installItemRealIntoDateJieju) {
		this.installItemRealIntoDateJieju = installItemRealIntoDateJieju;
	}

	public Date getInstallItemRealAcceptDateJieju() {
		return installItemRealAcceptDateJieju;
	}

	public void setInstallItemRealAcceptDateJieju(Date installItemRealAcceptDateJieju) {
		this.installItemRealAcceptDateJieju = installItemRealAcceptDateJieju;
	}

	public String getInstallItemIsReturnedJieju() {
		return installItemIsReturnedJieju;
	}

	public void setInstallItemIsReturnedJieju(String installItemIsReturnedJieju) {
		this.installItemIsReturnedJieju = installItemIsReturnedJieju;
	}

	public String getInstallItemProblemDescribeJieju() {
		return installItemProblemDescribeJieju;
	}

	public void setInstallItemProblemDescribeJieju(String installItemProblemDescribeJieju) {
		this.installItemProblemDescribeJieju = installItemProblemDescribeJieju;
	}

	public Date getInstallItemProblemApplyDatetimeJieju() {
		return installItemProblemApplyDatetimeJieju;
	}

	public void setInstallItemProblemApplyDatetimeJieju(Date installItemProblemApplyDatetimeJieju) {
		this.installItemProblemApplyDatetimeJieju = installItemProblemApplyDatetimeJieju;
	}

	public Date getInstallItemProblemSolveFatetimeJieju() {
		return installItemProblemSolveFatetimeJieju;
	}

	public void setInstallItemProblemSolveFatetimeJieju(Date installItemProblemSolveFatetimeJieju) {
		this.installItemProblemSolveFatetimeJieju = installItemProblemSolveFatetimeJieju;
	}

	public Double getSpendTimeJieju() {
		return spendTimeJieju;
	}

	public void setSpendTimeJieju(Double spendTimeJieju) {
		this.spendTimeJieju = spendTimeJieju;
	}

	public Date getChecksizeApplyDatetimeBizhi() {
		return checksizeApplyDatetimeBizhi;
	}

	public void setChecksizeApplyDatetimeBizhi(Date checksizeApplyDatetimeBizhi) {
		this.checksizeApplyDatetimeBizhi = checksizeApplyDatetimeBizhi;
	}

	public Date getInstallItemApplyDatetimeBizhi() {
		return installItemApplyDatetimeBizhi;
	}

	public void setInstallItemApplyDatetimeBizhi(Date installItemApplyDatetimeBizhi) {
		this.installItemApplyDatetimeBizhi = installItemApplyDatetimeBizhi;
	}

	public Date getInstallItemApplyIntoDateBizhi() {
		return installItemApplyIntoDateBizhi;
	}

	public void setInstallItemApplyIntoDateBizhi(Date installItemApplyIntoDateBizhi) {
		this.installItemApplyIntoDateBizhi = installItemApplyIntoDateBizhi;
	}

	public Date getInstallItemRealIntoDateBizhi() {
		return installItemRealIntoDateBizhi;
	}

	public void setInstallItemRealIntoDateBizhi(Date installItemRealIntoDateBizhi) {
		this.installItemRealIntoDateBizhi = installItemRealIntoDateBizhi;
	}

	public Date getInstallItemRealCompleteDateBizhi() {
		return installItemRealCompleteDateBizhi;
	}

	public void setInstallItemRealCompleteDateBizhi(Date installItemRealCompleteDateBizhi) {
		this.installItemRealCompleteDateBizhi = installItemRealCompleteDateBizhi;
	}

	public String getInstallItemIsReturnedBizhi() {
		return installItemIsReturnedBizhi;
	}

	public void setInstallItemIsReturnedBizhi(String installItemIsReturnedBizhi) {
		this.installItemIsReturnedBizhi = installItemIsReturnedBizhi;
	}

	public String getInstallItemProblemDescribeBizhi() {
		return installItemProblemDescribeBizhi;
	}

	public void setInstallItemProblemDescribeBizhi(String installItemProblemDescribeBizhi) {
		this.installItemProblemDescribeBizhi = installItemProblemDescribeBizhi;
	}

	public Date getInstallItemProblemApplyDatetimeBizhi() {
		return installItemProblemApplyDatetimeBizhi;
	}

	public void setInstallItemProblemApplyDatetimeBizhi(Date installItemProblemApplyDatetimeBizhi) {
		this.installItemProblemApplyDatetimeBizhi = installItemProblemApplyDatetimeBizhi;
	}

	public Date getInstallItemProblemSolveDatetimeBizhi() {
		return installItemProblemSolveDatetimeBizhi;
	}

	public void setInstallItemProblemSolveDatetimeBizhi(Date installItemProblemSolveDatetimeBizhi) {
		this.installItemProblemSolveDatetimeBizhi = installItemProblemSolveDatetimeBizhi;
	}

	public Double getSpendTimeBizhi() {
		return spendTimeBizhi;
	}

	public void setSpendTimeBizhi(Double spendTimeBizhi) {
		this.spendTimeBizhi = spendTimeBizhi;
	}

	public Date getChecksizeApplyDatetimeMudiban() {
		return checksizeApplyDatetimeMudiban;
	}

	public void setChecksizeApplyDatetimeMudiban(Date checksizeApplyDatetimeMudiban) {
		this.checksizeApplyDatetimeMudiban = checksizeApplyDatetimeMudiban;
	}

	public Date getInstallItemApplyDatetimeMudiban() {
		return installItemApplyDatetimeMudiban;
	}

	public void setInstallItemApplyDatetimeMudiban(Date installItemApplyDatetimeMudiban) {
		this.installItemApplyDatetimeMudiban = installItemApplyDatetimeMudiban;
	}

	public Date getInstallItemApplyIntoDateMudiban() {
		return installItemApplyIntoDateMudiban;
	}

	public void setInstallItemApplyIntoDateMudiban(Date installItemApplyIntoDateMudiban) {
		this.installItemApplyIntoDateMudiban = installItemApplyIntoDateMudiban;
	}

	public Date getInstallItemRealIntoDateMudiban() {
		return installItemRealIntoDateMudiban;
	}

	public void setInstallItemRealIntoDateMudiban(Date installItemRealIntoDateMudiban) {
		this.installItemRealIntoDateMudiban = installItemRealIntoDateMudiban;
	}

	public Date getInstallItemRealAcceptDateMudiban() {
		return installItemRealAcceptDateMudiban;
	}

	public void setInstallItemRealAcceptDateMudiban(Date installItemRealAcceptDateMudiban) {
		this.installItemRealAcceptDateMudiban = installItemRealAcceptDateMudiban;
	}

	public String getInstallItemIsReturnedMudiban() {
		return installItemIsReturnedMudiban;
	}

	public void setInstallItemIsReturnedMudiban(String installItemIsReturnedMudiban) {
		this.installItemIsReturnedMudiban = installItemIsReturnedMudiban;
	}

	public String getInstallItemProblemDescribeMudiban() {
		return installItemProblemDescribeMudiban;
	}

	public void setInstallItemProblemDescribeMudiban(String installItemProblemDescribeMudiban) {
		this.installItemProblemDescribeMudiban = installItemProblemDescribeMudiban;
	}

	public Date getInstallItemProblemApplyDatetimeMudiban() {
		return installItemProblemApplyDatetimeMudiban;
	}

	public void setInstallItemProblemApplyDatetimeMudiban(Date installItemProblemApplyDatetimeMudiban) {
		this.installItemProblemApplyDatetimeMudiban = installItemProblemApplyDatetimeMudiban;
	}

	public Date getInstallItemProblemSolveDatetimeMudiban() {
		return installItemProblemSolveDatetimeMudiban;
	}

	public void setInstallItemProblemSolveDatetimeMudiban(Date installItemProblemSolveDatetimeMudiban) {
		this.installItemProblemSolveDatetimeMudiban = installItemProblemSolveDatetimeMudiban;
	}

	public Double getSpendTimeMudiban() {
		return spendTimeMudiban;
	}

	public void setSpendTimeMudiban(Double spendTimeMudiban) {
		this.spendTimeMudiban = spendTimeMudiban;
	}

	public Date getChecksizeApplyDatetimeDiaoding() {
		return checksizeApplyDatetimeDiaoding;
	}

	public void setChecksizeApplyDatetimeDiaoding(Date checksizeApplyDatetimeDiaoding) {
		this.checksizeApplyDatetimeDiaoding = checksizeApplyDatetimeDiaoding;
	}

	public Date getInstallItemApplyDatetimeDiaoding() {
		return installItemApplyDatetimeDiaoding;
	}

	public void setInstallItemApplyDatetimeDiaoding(Date installItemApplyDatetimeDiaoding) {
		this.installItemApplyDatetimeDiaoding = installItemApplyDatetimeDiaoding;
	}

	public Date getInstallItemApplyIntoDateDiaoding() {
		return installItemApplyIntoDateDiaoding;
	}

	public void setInstallItemApplyIntoDateDiaoding(Date installItemApplyIntoDateDiaoding) {
		this.installItemApplyIntoDateDiaoding = installItemApplyIntoDateDiaoding;
	}

	public Date getInstallItemRealIntoDateDiaoding() {
		return installItemRealIntoDateDiaoding;
	}

	public void setInstallItemRealIntoDateDiaoding(Date installItemRealIntoDateDiaoding) {
		this.installItemRealIntoDateDiaoding = installItemRealIntoDateDiaoding;
	}

	public Date getInstallItemRealAcceptDateDiaoding() {
		return installItemRealAcceptDateDiaoding;
	}

	public void setInstallItemRealAcceptDateDiaoding(Date installItemRealAcceptDateDiaoding) {
		this.installItemRealAcceptDateDiaoding = installItemRealAcceptDateDiaoding;
	}

	public String getInstallItemIsReturnedDiaoding() {
		return installItemIsReturnedDiaoding;
	}

	public void setInstallItemIsReturnedDiaoding(String installItemIsReturnedDiaoding) {
		this.installItemIsReturnedDiaoding = installItemIsReturnedDiaoding;
	}

	public String getInstallItemProblemDescribeDiaoding() {
		return installItemProblemDescribeDiaoding;
	}

	public void setInstallItemProblemDescribeDiaoding(String installItemProblemDescribeDiaoding) {
		this.installItemProblemDescribeDiaoding = installItemProblemDescribeDiaoding;
	}

	public Date getInstallItemProblemApplyDatetimeDiaoding() {
		return installItemProblemApplyDatetimeDiaoding;
	}

	public void setInstallItemProblemApplyDatetimeDiaoding(Date installItemProblemApplyDatetimeDiaoding) {
		this.installItemProblemApplyDatetimeDiaoding = installItemProblemApplyDatetimeDiaoding;
	}

	public Date getInstallItemProblemSolveDatetimeDiaoding() {
		return installItemProblemSolveDatetimeDiaoding;
	}

	public void setInstallItemProblemSolveDatetimeDiaoding(Date installItemProblemSolveDatetimeDiaoding) {
		this.installItemProblemSolveDatetimeDiaoding = installItemProblemSolveDatetimeDiaoding;
	}

	public Double getSpendTimeDiaoding() {
		return spendTimeDiaoding;
	}

	public void setSpendTimeDiaoding(Double spendTimeDiaoding) {
		this.spendTimeDiaoding = spendTimeDiaoding;
	}

	public Date getChecksizeApplyDatetimeDengju() {
		return checksizeApplyDatetimeDengju;
	}

	public void setChecksizeApplyDatetimeDengju(Date checksizeApplyDatetimeDengju) {
		this.checksizeApplyDatetimeDengju = checksizeApplyDatetimeDengju;
	}

	public Date getInstallItemApplyDatetimeDengju() {
		return installItemApplyDatetimeDengju;
	}

	public void setInstallItemApplyDatetimeDengju(Date installItemApplyDatetimeDengju) {
		this.installItemApplyDatetimeDengju = installItemApplyDatetimeDengju;
	}

	public Date getInstallItemApplyIntoDateDengju() {
		return installItemApplyIntoDateDengju;
	}

	public void setInstallItemApplyIntoDateDengju(Date installItemApplyIntoDateDengju) {
		this.installItemApplyIntoDateDengju = installItemApplyIntoDateDengju;
	}

	public Date getInstallItemRealIntoDateDengju() {
		return installItemRealIntoDateDengju;
	}

	public void setInstallItemRealIntoDateDengju(Date installItemRealIntoDateDengju) {
		this.installItemRealIntoDateDengju = installItemRealIntoDateDengju;
	}

	public Date getInstallItemRealAcceptDateDengju() {
		return installItemRealAcceptDateDengju;
	}

	public void setInstallItemRealAcceptDateDengju(Date installItemRealAcceptDateDengju) {
		this.installItemRealAcceptDateDengju = installItemRealAcceptDateDengju;
	}

	public String getInstallItemIsReturnedDengju() {
		return installItemIsReturnedDengju;
	}

	public void setInstallItemIsReturnedDengju(String installItemIsReturnedDengju) {
		this.installItemIsReturnedDengju = installItemIsReturnedDengju;
	}

	public String getInstallItemProblemDescribeDengju() {
		return installItemProblemDescribeDengju;
	}

	public void setInstallItemProblemDescribeDengju(String installItemProblemDescribeDengju) {
		this.installItemProblemDescribeDengju = installItemProblemDescribeDengju;
	}

	public Date getInstallItemProblemApplyDatetimeDengju() {
		return installItemProblemApplyDatetimeDengju;
	}

	public void setInstallItemProblemApplyDatetimeDengju(Date installItemProblemApplyDatetimeDengju) {
		this.installItemProblemApplyDatetimeDengju = installItemProblemApplyDatetimeDengju;
	}

	public Date getInstallItemProblemSolveDatetimeDengju() {
		return installItemProblemSolveDatetimeDengju;
	}

	public void setInstallItemProblemSolveDatetimeDengju(Date installItemProblemSolveDatetimeDengju) {
		this.installItemProblemSolveDatetimeDengju = installItemProblemSolveDatetimeDengju;
	}

	public Double getSpendTimeDengju() {
		return spendTimeDengju;
	}

	public void setSpendTimeDengju(Double spendTimeDengju) {
		this.spendTimeDengju = spendTimeDengju;
	}

	public Date getChecksizeApplyDatetimeChuanglian() {
		return checksizeApplyDatetimeChuanglian;
	}

	public void setChecksizeApplyDatetimeChuanglian(Date checksizeApplyDatetimeChuanglian) {
		this.checksizeApplyDatetimeChuanglian = checksizeApplyDatetimeChuanglian;
	}

	public Date getInstallItemApplyDatetimeChuanglian() {
		return installItemApplyDatetimeChuanglian;
	}

	public void setInstallItemApplyDatetimeChuanglian(Date installItemApplyDatetimeChuanglian) {
		this.installItemApplyDatetimeChuanglian = installItemApplyDatetimeChuanglian;
	}

	public Date getInstallItemApplyIntoDateChuanglian() {
		return installItemApplyIntoDateChuanglian;
	}

	public void setInstallItemApplyIntoDateChuanglian(Date installItemApplyIntoDateChuanglian) {
		this.installItemApplyIntoDateChuanglian = installItemApplyIntoDateChuanglian;
	}

	public Date getInstallItemRealIntoDateChuanglian() {
		return installItemRealIntoDateChuanglian;
	}

	public void setInstallItemRealIntoDateChuanglian(Date installItemRealIntoDateChuanglian) {
		this.installItemRealIntoDateChuanglian = installItemRealIntoDateChuanglian;
	}

	public Date getInstallItemRealAcceptDateChuanglian() {
		return installItemRealAcceptDateChuanglian;
	}

	public void setInstallItemRealAcceptDateChuanglian(Date installItemRealAcceptDateChuanglian) {
		this.installItemRealAcceptDateChuanglian = installItemRealAcceptDateChuanglian;
	}

	public String getInstallItemIsReturnedChuanglian() {
		return installItemIsReturnedChuanglian;
	}

	public void setInstallItemIsReturnedChuanglian(String installItemIsReturnedChuanglian) {
		this.installItemIsReturnedChuanglian = installItemIsReturnedChuanglian;
	}

	public String getInstallItemProblemDescribeChuanglian() {
		return installItemProblemDescribeChuanglian;
	}

	public void setInstallItemProblemDescribeChuanglian(String installItemProblemDescribeChuanglian) {
		this.installItemProblemDescribeChuanglian = installItemProblemDescribeChuanglian;
	}

	public Date getInstallItemProblemApplyDatetimeChuanglian() {
		return installItemProblemApplyDatetimeChuanglian;
	}

	public void setInstallItemProblemApplyDatetimeChuanglian(Date installItemProblemApplyDatetimeChuanglian) {
		this.installItemProblemApplyDatetimeChuanglian = installItemProblemApplyDatetimeChuanglian;
	}

	public Date getInstallItemProblemSolveDatetimeChuanglian() {
		return installItemProblemSolveDatetimeChuanglian;
	}

	public void setInstallItemProblemSolveDatetimeChuanglian(Date installItemProblemSolveDatetimeChuanglian) {
		this.installItemProblemSolveDatetimeChuanglian = installItemProblemSolveDatetimeChuanglian;
	}

	public Double getSpendTimeChuanglian() {
		return spendTimeChuanglian;
	}

	public void setSpendTimeChuanglian(Double spendTimeChuanglian) {
		this.spendTimeChuanglian = spendTimeChuanglian;
	}

	public Date getChecksizeApplyDatetimeLinyufang() {
		return checksizeApplyDatetimeLinyufang;
	}

	public void setChecksizeApplyDatetimeLinyufang(Date checksizeApplyDatetimeLinyufang) {
		this.checksizeApplyDatetimeLinyufang = checksizeApplyDatetimeLinyufang;
	}

	public Date getInstallItemApplyDatetimeLinyufang() {
		return installItemApplyDatetimeLinyufang;
	}

	public void setInstallItemApplyDatetimeLinyufang(Date installItemApplyDatetimeLinyufang) {
		this.installItemApplyDatetimeLinyufang = installItemApplyDatetimeLinyufang;
	}

	public Date getInstallItemApplyIntoDateLinyufang() {
		return installItemApplyIntoDateLinyufang;
	}

	public void setInstallItemApplyIntoDateLinyufang(Date installItemApplyIntoDateLinyufang) {
		this.installItemApplyIntoDateLinyufang = installItemApplyIntoDateLinyufang;
	}

	public Date getInstallItemRealIntoDateLinyufang() {
		return installItemRealIntoDateLinyufang;
	}

	public void setInstallItemRealIntoDateLinyufang(Date installItemRealIntoDateLinyufang) {
		this.installItemRealIntoDateLinyufang = installItemRealIntoDateLinyufang;
	}

	public Date getInstallItemRealAcceptDateLinyufang() {
		return installItemRealAcceptDateLinyufang;
	}

	public void setInstallItemRealAcceptDateLinyufang(Date installItemRealAcceptDateLinyufang) {
		this.installItemRealAcceptDateLinyufang = installItemRealAcceptDateLinyufang;
	}

	public String getInstallItemIsReturnedLinyufang() {
		return installItemIsReturnedLinyufang;
	}

	public void setInstallItemIsReturnedLinyufang(String installItemIsReturnedLinyufang) {
		this.installItemIsReturnedLinyufang = installItemIsReturnedLinyufang;
	}

	public String getInstallItemProblemDescribeLinyufang() {
		return installItemProblemDescribeLinyufang;
	}

	public void setInstallItemProblemDescribeLinyufang(String installItemProblemDescribeLinyufang) {
		this.installItemProblemDescribeLinyufang = installItemProblemDescribeLinyufang;
	}

	public Date getInstallItemProblemApplyDatetimeLinyufang() {
		return installItemProblemApplyDatetimeLinyufang;
	}

	public void setInstallItemProblemApplyDatetimeLinyufang(Date installItemProblemApplyDatetimeLinyufang) {
		this.installItemProblemApplyDatetimeLinyufang = installItemProblemApplyDatetimeLinyufang;
	}

	public Date getInstallItemProblemSolveDatetimeLinyufang() {
		return installItemProblemSolveDatetimeLinyufang;
	}

	public void setInstallItemProblemSolveDatetimeLinyufang(Date installItemProblemSolveDatetimeLinyufang) {
		this.installItemProblemSolveDatetimeLinyufang = installItemProblemSolveDatetimeLinyufang;
	}

	public Double getSpendTimeLinyufang() {
		return spendTimeLinyufang;
	}

	public void setSpendTimeLinyufang(Double spendTimeLinyufang) {
		this.spendTimeLinyufang = spendTimeLinyufang;
	}

	public Date getChecksizeApplyDatetimeYigui() {
		return checksizeApplyDatetimeYigui;
	}

	public void setChecksizeApplyDatetimeYigui(Date checksizeApplyDatetimeYigui) {
		this.checksizeApplyDatetimeYigui = checksizeApplyDatetimeYigui;
	}

	public Date getInstallItemApplyDatetimeYigui() {
		return installItemApplyDatetimeYigui;
	}

	public void setInstallItemApplyDatetimeYigui(Date installItemApplyDatetimeYigui) {
		this.installItemApplyDatetimeYigui = installItemApplyDatetimeYigui;
	}

	public Date getInstallItemApplyIntoDateYigui() {
		return installItemApplyIntoDateYigui;
	}

	public void setInstallItemApplyIntoDateYigui(Date installItemApplyIntoDateYigui) {
		this.installItemApplyIntoDateYigui = installItemApplyIntoDateYigui;
	}

	public Date getInstallItemRealIntoDateYigui() {
		return installItemRealIntoDateYigui;
	}

	public void setInstallItemRealIntoDateYigui(Date installItemRealIntoDateYigui) {
		this.installItemRealIntoDateYigui = installItemRealIntoDateYigui;
	}

	public Date getInstallItemRealAcceptDateYigui() {
		return installItemRealAcceptDateYigui;
	}

	public void setInstallItemRealAcceptDateYigui(Date installItemRealAcceptDateYigui) {
		this.installItemRealAcceptDateYigui = installItemRealAcceptDateYigui;
	}

	public String getInstallItemIsReturnedYigui() {
		return installItemIsReturnedYigui;
	}

	public void setInstallItemIsReturnedYigui(String installItemIsReturnedYigui) {
		this.installItemIsReturnedYigui = installItemIsReturnedYigui;
	}

	public String getInstallItemProblemDescribeYigui() {
		return installItemProblemDescribeYigui;
	}

	public void setInstallItemProblemDescribeYigui(String installItemProblemDescribeYigui) {
		this.installItemProblemDescribeYigui = installItemProblemDescribeYigui;
	}

	public Date getInstallItemProblemApplyDatetimeYigui() {
		return installItemProblemApplyDatetimeYigui;
	}

	public void setInstallItemProblemApplyDatetimeYigui(Date installItemProblemApplyDatetimeYigui) {
		this.installItemProblemApplyDatetimeYigui = installItemProblemApplyDatetimeYigui;
	}

	public Date getInstallItemProblemSolveDatetimeYigui() {
		return installItemProblemSolveDatetimeYigui;
	}

	public void setInstallItemProblemSolveDatetimeYigui(Date installItemProblemSolveDatetimeYigui) {
		this.installItemProblemSolveDatetimeYigui = installItemProblemSolveDatetimeYigui;
	}

	public Double getSpendTimeYigui() {
		return spendTimeYigui;
	}

	public void setSpendTimeYigui(Double spendTimeYigui) {
		this.spendTimeYigui = spendTimeYigui;
	}

	public Date getBeginActualStartDate() {
		return beginActualStartDate;
	}

	public void setBeginActualStartDate(Date beginActualStartDate) {
		this.beginActualStartDate = beginActualStartDate;
	}

	public Date getEndActualStartDate() {
		return endActualStartDate;
	}

	public void setEndActualStartDate(Date endActualStartDate) {
		this.endActualStartDate = endActualStartDate;
	}
	
	




	
	
}