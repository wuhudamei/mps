package cn.damei.service.modules;


import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.dao.modules.ExchangeOrderDetailsDao;
import cn.damei.entity.modules.ExchangeOrderDetails;
import cn.damei.common.utils.BizDictUtils;
import cn.damei.common.utils.DictUtils;

@Service
@Transactional(readOnly=true)
public class ExchangeOrderDetailsService extends CrudService2<ExchangeOrderDetailsDao, ExchangeOrderDetails> {
	@Autowired
	private BizEmployeeDao bizEmployeeDao;
	public Page<ExchangeOrderDetails> selectDetailsPageById(Page<ExchangeOrderDetails> page,
			ExchangeOrderDetails exchangeOrderDetails){
		
		exchangeOrderDetails.setPage(page);
		page.setList(dao.selectDetailsPageById(exchangeOrderDetails));
		
		return page;
	}
	
	public Page<ExchangeOrderDetails> selectManagerDetailsPageById(Page<ExchangeOrderDetails> page,
			ExchangeOrderDetails exchangeOrderDetails){
		exchangeOrderDetails.setPage(page);
		page.setList(dao.selectManagerDetailsPageById(exchangeOrderDetails));
		return page;
	}
	
	public Page<ExchangeOrderDetails> selectInspectorDetailsPageById(Page<ExchangeOrderDetails> page,
			ExchangeOrderDetails exchangeOrderDetails){
		exchangeOrderDetails.setPage(page);
		page.setList(dao.selectInspectorDetailsPageById(exchangeOrderDetails));
		return page;
	}
	@SuppressWarnings("deprecation")
	public HSSFWorkbook exportManagerExchangeToExcel(BizEmployee bizEmployee) {
		HSSFWorkbook workbook = new HSSFWorkbook();


		HSSFSheet sheet = workbook.createSheet("项目经理被换明细");
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL);
		
		font.setFontName("黑体");

		font.setFontHeight((short)220);
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
		columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFDataFormat format = workbook.createDataFormat(); 

		cellStyle.setDataFormat(format.getFormat("@"));
		columnStyle.setDataFormat(format.getFormat("@"));

		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 2000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 6000);
		sheet.setColumnWidth(8, 13000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 6000);
		sheet.setColumnWidth(12, 6000);





		HSSFRow rowTitle2 = sheet.createRow(0);

		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(cellStyle);
		headCell0.setCellValue("门店");

		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(cellStyle);
		headCell1.setCellValue("工程模式");

		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(cellStyle);
		headCell2.setCellValue("区域");

		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(cellStyle);
		headCell3.setCellValue("项目经理");

		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(cellStyle);
		headCell4.setCellValue("手机号");

		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(cellStyle);
		headCell5.setCellValue("星级");

		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(cellStyle);
		headCell6.setCellValue("被换累计次数");

		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(cellStyle);
		headCell7.setCellValue("被换时间");

		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(cellStyle);
		headCell8.setCellValue("客户");

		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(cellStyle);
		headCell9.setCellValue("原项目经理");

		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(cellStyle);
		headCell10.setCellValue("新项目经理");

		HSSFCell headCell11 = rowTitle2.createCell(11);
		headCell11.setCellStyle(cellStyle);
		headCell11.setCellValue("原因");

		HSSFCell headCell12 = rowTitle2.createCell(12);
		headCell12.setCellStyle(cellStyle);
		headCell12.setCellValue("说明");
		List<BizEmployee> bizEmployeeList =  bizEmployeeDao.findExCahangeManagerList(bizEmployee);
		if(CollectionUtils.isNotEmpty(bizEmployeeList)){
			Integer listSize = bizEmployeeList.size();
			int rowNo = 1;
			for(int v=0;v<listSize;v++){
				BizEmployee b = bizEmployeeList.get(v);
				ExchangeOrderDetails exchangeOrderDetails = new ExchangeOrderDetails();
				exchangeOrderDetails.setOldEmployeeId(Integer.valueOf(b.getId()));
				exchangeOrderDetails.setStartExChangeDate(b.getStartExchanegeDate());
				exchangeOrderDetails.setEndExChangeDate(b.getEndExchanegeDate());
				List<ExchangeOrderDetails> exchangeOrderDetailsList =  dao.selectManagerDetailsPageById(exchangeOrderDetails);
				if(CollectionUtils.isNotEmpty(exchangeOrderDetailsList)){
					HSSFRow row = null;
					for(int i=0;i<exchangeOrderDetailsList.size();i++){
						ExchangeOrderDetails eo = exchangeOrderDetailsList.get(i);

						row = sheet.createRow(rowNo);

						HSSFCell cell0 = row.createCell(0);
						cell0.setCellStyle(columnStyle);
						cell0.setCellValue(BizDictUtils.getStoreLabel(b.getStoreid(), ""));
						

						HSSFCell cell1 = row.createCell(1);
						cell1.setCellStyle(columnStyle);
						cell1.setCellValue(DictUtils.getDictLabel(b.getProjectMode(), "employee_project_mode", ""));
						

						HSSFCell cell2 = row.createCell(2);
						cell2.setCellStyle(columnStyle);
						cell2.setCellValue(b.getDepartmentName());
						

						HSSFCell cell3 = row.createCell(3);
						cell3.setCellStyle(columnStyle);
						cell3.setCellValue(b.getRealname());


						HSSFCell cell4 = row.createCell(4);
						cell4.setCellStyle(columnStyle);
						cell4.setCellValue(b.getPhone());
						

						HSSFCell cell5 = row.createCell(5);
						cell5.setCellStyle(columnStyle);
						cell5.setCellValue(DictUtils.getDictLabel(b.getStar().toString(), "emp_star", ""));
						


						HSSFCell cell6 = row.createCell(6);
						cell6.setCellStyle(columnStyle);
						cell6.setCellValue(b.getExchangeOrderTimes());
						

						HSSFCell cell7 = row.createCell(7);
						cell7.setCellStyle(columnStyle);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
						cell7.setCellValue(sdf.format(eo.getExChangeDate()));
						

						HSSFCell cell8 = row.createCell(8);
						cell8.setCellStyle(columnStyle);
						cell8.setCellValue(eo.getAdd());
						

						HSSFCell cell9 = row.createCell(9);
						cell9.setCellStyle(columnStyle);
						cell9.setCellValue(eo.getOldLeaderName());
						

						HSSFCell cell10 = row.createCell(10);
						cell10.setCellStyle(columnStyle);
						cell10.setCellValue(eo.getNewLeaderName());
						

						HSSFCell cell11 = row.createCell(11);
						cell11.setCellStyle(columnStyle);
						cell11.setCellValue(DictUtils.getDictLabel(eo.getReasonType(), "re_dispatch_cause", ""));
						

						HSSFCell cell12 = row.createCell(12);
						cell12.setCellStyle(columnStyle);
						cell12.setCellValue(eo.getReasonRemarks());
						
						rowNo++;
					}
					for(int j=0;j<7;j++){

						CellRangeAddress cra=new CellRangeAddress(rowNo-exchangeOrderDetailsList.size(), rowNo-1, j, j);    
						sheet.addMergedRegion(cra);  
						HSSFCell cellRg = row.getCell(j);
						cellRg.setCellStyle(columnStyle);
					}
					
				}
				
			}
			
		}
		return workbook;
		
	}
	@SuppressWarnings("deprecation")
	public HSSFWorkbook exportWorkerExchangeToExcel(BizEmployee bizEmployee) {
		HSSFWorkbook workbook = new HSSFWorkbook();


		HSSFSheet sheet = workbook.createSheet("工人被换明细");
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL);
		
		font.setFontName("黑体");

		font.setFontHeight((short)220);
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
		columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFDataFormat format = workbook.createDataFormat(); 

		cellStyle.setDataFormat(format.getFormat("@"));
		columnStyle.setDataFormat(format.getFormat("@"));

		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 2000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 6000);
		sheet.setColumnWidth(9, 13000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 6000);
		sheet.setColumnWidth(12, 6000);
		sheet.setColumnWidth(13, 6000);




		HSSFRow rowTitle2 = sheet.createRow(0);

		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(cellStyle);
		headCell0.setCellValue("门店");

		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(cellStyle);
		headCell1.setCellValue("工程模式");

		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(cellStyle);
		headCell2.setCellValue("区域");

		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(cellStyle);
		headCell3.setCellValue("工人组长");

		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(cellStyle);
		headCell4.setCellValue("手机号");

		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(cellStyle);
		headCell5.setCellValue("星级");

		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(cellStyle);
		headCell6.setCellValue("工种");
		
		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(cellStyle);
		headCell7.setCellValue("被换累计次数");

		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(cellStyle);
		headCell8.setCellValue("被换时间");

		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(cellStyle);
		headCell9.setCellValue("客户");

		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(cellStyle);
		headCell10.setCellValue("原项目经理");

		HSSFCell headCell11 = rowTitle2.createCell(11);
		headCell11.setCellStyle(cellStyle);
		headCell11.setCellValue("新项目经理");

		HSSFCell headCell12 = rowTitle2.createCell(12);
		headCell12.setCellStyle(cellStyle);
		headCell12.setCellValue("原因");

		HSSFCell headCell13 = rowTitle2.createCell(13);
		headCell13.setCellStyle(cellStyle);
		headCell13.setCellValue("说明");
		List<BizEmployee> bizEmployeeList =  bizEmployeeDao.findLeadList(bizEmployee);
		if(CollectionUtils.isNotEmpty(bizEmployeeList)){
			Integer listSize = bizEmployeeList.size();
			int rowNo = 1;
			for(int v=0;v<listSize;v++){
				BizEmployee b = bizEmployeeList.get(v);
				ExchangeOrderDetails exchangeOrderDetails = new ExchangeOrderDetails();
				exchangeOrderDetails.setOldEmployeeId(Integer.valueOf(b.getId()));
				exchangeOrderDetails.setStartExChangeDate(b.getStartExchanegeDate());
				exchangeOrderDetails.setEndExChangeDate(b.getEndExchanegeDate());
				List<ExchangeOrderDetails> exchangeOrderDetailsList =  dao.selectDetailsPageById(exchangeOrderDetails);
				if(CollectionUtils.isNotEmpty(exchangeOrderDetailsList)){
					HSSFRow row = null;
					for(int i=0;i<exchangeOrderDetailsList.size();i++){
						ExchangeOrderDetails eo = exchangeOrderDetailsList.get(i);

						row = sheet.createRow(rowNo);

						HSSFCell cell0 = row.createCell(0);
						cell0.setCellStyle(columnStyle);
						cell0.setCellValue(BizDictUtils.getStoreLabel(b.getStoreid(), ""));
						

						HSSFCell cell1 = row.createCell(1);
						cell1.setCellStyle(columnStyle);
						cell1.setCellValue(DictUtils.getDictLabel(b.getProjectMode(), "employee_project_mode", ""));
						

						HSSFCell cell2 = row.createCell(2);
						cell2.setCellStyle(columnStyle);
						cell2.setCellValue(b.getDepartmentName());
						

						HSSFCell cell3 = row.createCell(3);
						cell3.setCellStyle(columnStyle);
						cell3.setCellValue(b.getRealname());


						HSSFCell cell4 = row.createCell(4);
						cell4.setCellStyle(columnStyle);
						cell4.setCellValue(b.getPhone()+"");

						

						HSSFCell cell5 = row.createCell(5);
						cell5.setCellStyle(columnStyle);
						if(b.getStar()!=null){
							cell5.setCellValue(DictUtils.getDictLabel(b.getStar().toString(), "emp_star", ""));
						}else{
							cell5.setCellValue("");
						}
						

						HSSFCell cell6 = row.createCell(6);
						cell6.setCellStyle(columnStyle);
						cell6.setCellValue(DictUtils.getDictLabel(b.getWorktype().toString(), "emp_work_type", ""));
						
						

						HSSFCell cell7 = row.createCell(7);
						cell7.setCellStyle(columnStyle);
						cell7.setCellValue(b.getExchangeOrderTimes());
						

						HSSFCell cell8 = row.createCell(8);
						cell8.setCellStyle(columnStyle);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
						cell8.setCellValue(sdf.format(eo.getExChangeDate()));
						

						HSSFCell cell9 = row.createCell(9);
						cell9.setCellStyle(columnStyle);
						cell9.setCellValue(eo.getAdd());
						

						HSSFCell cell10 = row.createCell(10);
						cell10.setCellStyle(columnStyle);
						cell10.setCellValue(eo.getOldLeaderName());
						

						HSSFCell cell11 = row.createCell(11);
						cell11.setCellStyle(columnStyle);
						cell11.setCellValue(eo.getNewLeaderName());
						

						HSSFCell cell12 = row.createCell(12);
						cell12.setCellStyle(columnStyle);
						cell12.setCellValue(DictUtils.getDictLabel(eo.getReasonType(), "re_dispatch_cause", ""));
						

						HSSFCell cell13 = row.createCell(13);
						cell13.setCellStyle(columnStyle);
						cell13.setCellValue(eo.getReasonRemarks());
						rowNo++;
					}
					for(int j=0;j<8;j++){

						CellRangeAddress cra=new CellRangeAddress(rowNo-exchangeOrderDetailsList.size(), rowNo-1, j, j);    
						sheet.addMergedRegion(cra);  
						HSSFCell cellRg = row.getCell(j);
						cellRg.setCellStyle(columnStyle);
					}
					
				}
				
			}
			
		}
		return workbook;
		
	}
}
