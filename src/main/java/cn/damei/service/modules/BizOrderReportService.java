package cn.damei.service.modules;

import java.util.*;

import cn.damei.common.constantUtils.BizOrderReportConstantUtil;
import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.dao.modules.BizOrderReportLogDao;
import cn.damei.entity.modules.OrderReportLogEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.dao.modules.BizOrderReportDao;
import cn.damei.entity.modules.BizOrderReport;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class BizOrderReportService extends CrudService2<BizOrderReportDao,BizOrderReport>{

	@Autowired
	private BizBusinessStatusLogDao logDao;
	@Autowired
	private BizOrderReportDao bizOrderReportDao;
	@Autowired
	private ReturnOrderLogBusinessService reportLogService;



	public BizOrderReport get(Integer id){
		return super.get(id);
	}

	public Integer getBizOrderReportByCustomerPhone(String customerPhone){
		return dao.getBizOrderReportByCustomerPhone(customerPhone);
	}

	public List<BizOrderReport> findList(BizOrderReport bizOrderReport){
		return super.findList(bizOrderReport);
	}

	public Page<BizOrderReport> findPage(Page<BizOrderReport> page, BizOrderReport bizOrderReport) {
		return super.findPage(page, bizOrderReport);
	}

	public Page<BizOrderReport> findByParam(Page<BizOrderReport> page, BizOrderReport bizOrderReport){
		bizOrderReport.setPage(page);
		page.setList(dao.findByParam(bizOrderReport));
		return page;
	}


	@Transactional(readOnly = false)
	public void save(BizOrderReport bizOrderReport){
		if(null!=bizOrderReport.getId()){
			super.save(bizOrderReport);
			saveBizBusinessStatusLog(bizOrderReport);
		}else{
			super.save(bizOrderReport);
			BizOrderReport checkedReport=reportLogService.checkOrderReportIsExist(bizOrderReport);
			super.save(checkedReport);
			saveBizBusinessStatusLog(bizOrderReport);
		}




	}
	@Autowired
	private PhoneMessageDao messageDao;
	@Autowired
	private BizOrderReportLogDao orderReportLogDao;


	@Transactional(readOnly = false)
	public void saveBizBusinessStatusLog(BizOrderReport bizOrderReport){
		OrderReportLogEntity logEntity = new OrderReportLogEntity();
		Map<String,Object> logRelatedMap = new HashMap<>();

		if(bizOrderReport.getReportStatus().equals(BizOrderReportConstantUtil.REPORT_STATUS_10)){
			BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
			bizBusinessStatusLog.setBusinessRemarks(BizOrderReportConstantUtil.REPORT_STATUS_10_WORD);
			bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_501);
			bizBusinessStatusLog.setBusinessOnlyMarkInt(bizOrderReport.getId());
			bizBusinessStatusLog.setBusinessStatus(BizOrderReportConstantUtil.REPORT_STATUS_10);
			if (StringUtils.isNotBlank(UserUtils.getUser().getId())){
				bizBusinessStatusLog.setBusinessEmployeeId(Integer.valueOf(UserUtils.getUser().getId()));
			}
			bizBusinessStatusLog.setStatusDatetime(new Date());
			bizBusinessStatusLog.preInsert();
			logDao.insert(bizBusinessStatusLog);
		}else if(bizOrderReport.getReportStatus().equals(BizOrderReportConstantUtil.REPORT_STATUS_30)){
			logEntity.setOperateSource(BizOrderReportConstantUtil.REPORT_SOURCE_TYPE_4);
			logEntity.setOrderReportId(bizOrderReport.getId());
			logEntity.setLogType(BizOrderReportConstantUtil.INSOTRE_TYPE_1);
			logEntity.setLogDateTime(new Date());
			logEntity.setInStoreRemarks(bizOrderReport.getInstoreRemarks());
			logEntity.setInStoreDateTime(bizOrderReport.getInstoreDatetime());
			logEntity.setOperateDateTime(new Date());
			logEntity.setOperateEmployeeName(UserUtils.getUser().getName());
			logEntity.preInsert();

			orderReportLogDao.saveInstoreLog(logEntity);




























		}else if(bizOrderReport.getReportStatus().equals(BizOrderReportConstantUtil.REPORT_STATUS_40)){
			logEntity.setOperateSource(BizOrderReportConstantUtil.REPORT_SOURCE_TYPE_4);
			logEntity.setOrderReportId(bizOrderReport.getId());
			logEntity.setLogType(BizOrderReportConstantUtil.INSOTRE_TYPE_2);
			logEntity.setLogDateTime(new Date());
			logEntity.setLogRemarks(bizOrderReport.getSignBillRemarks());
			logEntity.setSignBillDateTime(bizOrderReport.getSignBillDatetime());
			logEntity.setSignBillRemarks(bizOrderReport.getSignBillRemarks());
			logEntity.setOperateDateTime(new Date());
			logEntity.setOperateEmployeeName(UserUtils.getUser().getName());
			logEntity.preInsert();
			orderReportLogDao.saveInstoreLog(logEntity);

			logRelatedMap.put("logId",logEntity.getId());
			logRelatedMap.put("orderNums",bizOrderReport.getOrderNumber());

			orderReportLogDao.saveLogRelatedOrderNumber(logRelatedMap);




































		}else if(bizOrderReport.getReportStatus().equals(BizOrderReportConstantUtil.REPORT_STATUS_50)){
			logEntity.setOperateSource(BizOrderReportConstantUtil.REPORT_SOURCE_TYPE_4);
			logEntity.setOrderReportId(bizOrderReport.getId());
			logEntity.setLogType(BizOrderReportConstantUtil.SIGN_TYPE_1);
			logEntity.setLogDateTime(new Date());
			logEntity.setLogRemarks(bizOrderReport.getSignBillRemarks());
			logEntity.setOperateDateTime(new Date());
			logEntity.setOperateEmployeeName(UserUtils.getUser().getName());
			logEntity.preInsert();
			orderReportLogDao.saveInstoreLog(logEntity);

			logRelatedMap.put("logId",logEntity.getId());
			logRelatedMap.put("orderNums",bizOrderReport.getOrderNumber());

			orderReportLogDao.saveLogRelatedOrderNumber(logRelatedMap);




























		}


	}

	@Transactional(readOnly = false)
	public void delete(BizOrderReport bizOrderReport) {
		super.delete(bizOrderReport);
	}




	@Transactional(readOnly = false)

	public void updateReturnOrder(BizOrderReport bizOrderReport,	List<BizOrderReport> orderReportList,String orderNums ) {


		bizOrderReportDao.updateReturnOrderRemarks(bizOrderReport);

		bizOrderReport.preInsert();
		bizOrderReportDao.batchInsertRelatedContractContract(orderReportList);



		OrderReportLogEntity logEntity = new OrderReportLogEntity();
		Map<String,Object> logRelatedMap = new HashMap<>();
		logEntity.setOperateSource(BizOrderReportConstantUtil.REPORT_SOURCE_TYPE_4);
		logEntity.setOrderReportId(bizOrderReport.getId());
		logEntity.setLogType(BizOrderReportConstantUtil.SIGN_TYPE_2);
		logEntity.setLogDateTime(new Date());
		logEntity.setSignBillDateTime(new Date());
		logEntity.setLogDateTime(new Date());
		logEntity.setSignBillRemarks(bizOrderReport.getSignBillRemarks());
		logEntity.setOperateDateTime(new Date());
		logEntity.setOperateEmployeeName(UserUtils.getUser().getName());
		logEntity.preInsert();
		orderReportLogDao.saveSignLog(logEntity);

		logRelatedMap.put("logId",logEntity.getId());
		logRelatedMap.put("orderNums",orderNums);

		orderReportLogDao.saveLogRelatedOrderNumber(logRelatedMap);























	}

	public String findRemarksByReturnOrderId(String returnOrderId){

		return dao.findRemarksByReturnOrderId(returnOrderId);

	}


	public List<BizOrderReport> findServiceList(Map<String,String> map){

		return dao.findServiceList(map);
	}

	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	@Autowired
	private BizOrderReportBusinessService bizOrderReportBusinessService;

	@Transactional(readOnly = false)
	public void saveTransferServiceInfo(BizOrderReport report){

		dao.saveTransferServiceInfo(report);

		if("30".equals(report.getReportStatus())){


		}else{

			report.setReportStatus(BizOrderReportConstantUtil.REPORT_STATUS_25);
		}



		dao.updateStatus(report);

		bizOrderReportBusinessService.orderReportDistributionCustomerService(report);



		OrderReportLogEntity logEntity = new OrderReportLogEntity();


		logEntity.setOrderReportId(report.getId());
		logEntity.setLogType(BizOrderReportConstantUtil.DISTRIBUTE_SERVICE_2);
		logEntity.setLogDateTime(new Date());
		logEntity.setServiceId(report.getServiceEmployeeId());
		logEntity.setServiceName(report.getServiceName());
		logEntity.setServicePhone(report.getServicePhone());
		logEntity.setOperateDateTime(new Date());
		logEntity.setOperateEmployeeName(UserUtils.getUser().getName());
		logEntity.setOperateEmployeeId(UserUtils.getUser().getId());
		orderReportLogDao.saveSendLog(logEntity);

	}














	@SuppressWarnings("deprecation")
	public HSSFWorkbook exportExcel(BizOrderReport orderReport) {


	List<String> statusList=	new ArrayList<String>();

			if(null!=orderReport.getReportStatus()){

				String status[] =orderReport.getReportStatus().split(",");


				for(int v=0;v<status.length;v++){

					statusList.add(status[v]);
				}
			}
			if(statusList.size()==0){

				statusList=null;
			}
			orderReport.setReportStatusList(statusList);
		HSSFWorkbook workbook = new HSSFWorkbook();


		HSSFSheet sheet = workbook.createSheet("返单详情");
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFFont.COLOR_RED);
		font.setFontName("黑体");
		font.setFontHeightInPoints((short)50);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFCellStyle cellStyle = workbook.createCellStyle();


		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setLocked(true);
		cellStyle.setWrapText(true);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);






		HSSFCellStyle columnStyle = workbook.createCellStyle();
		columnStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderLeft((short) 1);
		columnStyle.setRightBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderRight((short) 1);
		columnStyle.setTopBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderTop((short) 1);
		columnStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderBottom((short) 1);
		columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);





		sheet.setColumnWidth(0, 1233);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 2000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 2000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 13000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);




		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(30);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnStyle);
		cell.setCellValue(new HSSFRichTextString("返单详情"));

		for(int i=0;i<12;i++){
			HSSFCell cella = rowTitle.createCell(i+1);
			cella.setCellStyle(columnStyle);
		}


		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));



		HSSFRow rowTitle2 = sheet.createRow(1);

		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(columnStyle);
		headCell0.setCellValue("序号");

		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(columnStyle);
		headCell1.setCellValue("门店");

		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(columnStyle);
		headCell2.setCellValue("返单上报日期");

		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(columnStyle);
		headCell3.setCellValue("客户姓名");

		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(columnStyle);
		headCell4.setCellValue("客户手机号");

		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(columnStyle);
		headCell5.setCellValue("小区名称");

		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(columnStyle);
		headCell6.setCellValue("详细地址");

		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(columnStyle);
		headCell7.setCellValue("返单推荐人");

		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(columnStyle);
		headCell8.setCellValue("返单推荐人手机号");

		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(columnStyle);
		headCell9.setCellValue("返单推荐人类型");

		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(columnStyle);
		headCell10.setCellValue("备注");

		HSSFCell headCell11 = rowTitle2.createCell(11);
		headCell11.setCellStyle(columnStyle);
		headCell11.setCellValue("返单状态");




		List<BizOrderReport> list =dao.findListForExcel(orderReport);

		if(CollectionUtils.isNotEmpty(list)){
			Integer listSize = list.size();
			for(int v =0;v<listSize;v++){

				BizOrderReport  entity = list.get(v);

				HSSFRow row = sheet.createRow(v+2);


				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(v+1);


				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if(null!=entity.getStoreName()){
					cell1.setCellValue(entity.getStoreName());
				}



				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if(null!=entity.getReportDatetime()){
					cell2.setCellValue(entity.getReportDatetime());
				}

				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);

				cell3.setCellValue(entity.getCustomerName());


				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getCustomerPhone())){
					cell4.setCellValue(entity.getCustomerPhone());
				}

				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getCommunityName())){
					cell5.setCellValue(entity.getCommunityName());
				}

				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getAddress())){
					cell6.setCellValue(entity.getAddress());
				}

				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if(null!=entity.getReporterName()){
					cell7.setCellValue(entity.getReporterName());
				}

				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getReporterPhone())){
					cell8.setCellValue(entity.getReporterPhone());
				}

				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getReporterType())){



					cell9.setCellValue(entity.getReporterType());
				}

				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(columnStyle);
				if(null!=entity.getReportRemarks()){
					cell10.setCellValue(entity.getReportRemarks());
				}

				HSSFCell cell11 = row.createCell(11);
				cell11.setCellStyle(columnStyle);
				if(null!=entity.getReportStatus()){
					cell11.setCellValue(entity.getReportStatus());
				}


			}




		}



		return workbook;

	}

	public Integer findOrderNumberCountIsExist(Map<String,String> map){



		return dao.findOrderNumberCountIsExist(map);
	}


	public List<OrderReportLogEntity> findLogList1(Integer reportId){

		return dao.findLogList1(reportId);
	}
	public List<OrderReportLogEntity> findLogList2(Integer reportId){

		return dao.findLogList2(reportId);
	}
	public List<OrderReportLogEntity> findLogList3(Integer reportId){

		return dao.findLogList3(reportId);
	}
	public List<OrderReportLogEntity> findLogList4(Integer reportId){

		return dao.findLogList4(reportId);
	}
	public List<OrderReportLogEntity> findLogList5(Integer reportId){

		return dao.findLogList5(reportId);
	}
	public List<OrderReportLogEntity> findLogList6(Integer reportId){

		return dao.findLogList6(reportId);
	}
	public List<OrderReportLogEntity> findLogList7(Integer reportId){

		return dao.findLogList7(reportId);
	}

}
