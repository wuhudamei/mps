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
		font.setColor(HSSFFont.COLOR_NORMAL);//字体颜色
		
		font.setFontName("黑体");//字体
		//font.setFontHeightInPoints((short)20);//字体高度
		font.setFontHeight((short)220);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//宽度
		//單元格樣式--标题
		HSSFCellStyle cellStyle = workbook.createCellStyle();


		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setLocked(true);
		cellStyle.setWrapText(true);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		cellStyle.setBorderLeft((short) 1);// 边框的大小
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		cellStyle.setBorderRight((short) 1);// 边框的大小
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框的颜色
		cellStyle.setBorderTop((short) 1);// 边框的大小
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);// 下边框的颜色
		cellStyle.setBorderBottom((short) 1);// 边框的大小
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


		//单元格样式
		HSSFCellStyle columnStyle = workbook.createCellStyle();
		columnStyle.setLeftBorderColor(HSSFColor.BLACK.index); // 左边框线的颜色
		columnStyle.setBorderLeft((short) 1);// 左边框线的大小
		columnStyle.setRightBorderColor(HSSFColor.BLACK.index); // 右边框线的颜色
		columnStyle.setBorderRight((short) 1);// 右边框线的大小
		columnStyle.setTopBorderColor(HSSFColor.BLACK.index); // 上边框线的颜色
		columnStyle.setBorderTop((short) 1);// 上边框线的大小
		columnStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框线的颜色
		columnStyle.setBorderBottom((short) 1);// 下边框线的大小
		columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		HSSFDataFormat format = workbook.createDataFormat(); 
		//设置单元格为@就是指文本型，防止大数字变成科学计数法
		cellStyle.setDataFormat(format.getFormat("@"));
		columnStyle.setDataFormat(format.getFormat("@"));
		// 单元格宽度
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

		/*//标题---订单信息
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(30);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnStyle);
		cell.setCellValue(new HSSFRichTextString("项目经理被换明细"));

		for(int i=0;i<13;i++){
			HSSFCell cella = rowTitle.createCell(i+1);
			cella.setCellStyle(columnStyle);
		}

		//合并单元格--开始行，结束行，开始列，结束列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));*/


		//标题
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
			int rowNo = 1;//当前行号，从第1行开始
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
						//从第三行开始
						row = sheet.createRow(rowNo);
						//第一个是门店
						HSSFCell cell0 = row.createCell(0);
						cell0.setCellStyle(columnStyle);
						cell0.setCellValue(BizDictUtils.getStoreLabel(b.getStoreid(), ""));
						
						//第二个是工程模式
						HSSFCell cell1 = row.createCell(1);
						cell1.setCellStyle(columnStyle);
						cell1.setCellValue(DictUtils.getDictLabel(b.getProjectMode(), "employee_project_mode", ""));
						
						//第三个是区域
						HSSFCell cell2 = row.createCell(2);
						cell2.setCellStyle(columnStyle);
						cell2.setCellValue(b.getDepartmentName());
						
						//第四个是项目经理
						HSSFCell cell3 = row.createCell(3);
						cell3.setCellStyle(columnStyle);
						cell3.setCellValue(b.getRealname());

						//第五个是手机号
						HSSFCell cell4 = row.createCell(4);
						cell4.setCellStyle(columnStyle);
						cell4.setCellValue(b.getPhone());
						
						//6星级
						HSSFCell cell5 = row.createCell(5);
						cell5.setCellStyle(columnStyle);
						cell5.setCellValue(DictUtils.getDictLabel(b.getStar().toString(), "emp_star", ""));
						

						//第7个是被换累计次数
						HSSFCell cell6 = row.createCell(6);
						cell6.setCellStyle(columnStyle);
						cell6.setCellValue(b.getExchangeOrderTimes());
						
						//第8个是被换时间
						HSSFCell cell7 = row.createCell(7);
						cell7.setCellStyle(columnStyle);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
						cell7.setCellValue(sdf.format(eo.getExChangeDate()));
						
						//第9个是客户
						HSSFCell cell8 = row.createCell(8);
						cell8.setCellStyle(columnStyle);
						cell8.setCellValue(eo.getAdd());
						
						//第10个是原项目经理
						HSSFCell cell9 = row.createCell(9);
						cell9.setCellStyle(columnStyle);
						cell9.setCellValue(eo.getOldLeaderName());
						
						//第11个是新项目经理
						HSSFCell cell10 = row.createCell(10);
						cell10.setCellStyle(columnStyle);
						cell10.setCellValue(eo.getNewLeaderName());
						
						//第12个是原因
						HSSFCell cell11 = row.createCell(11);
						cell11.setCellStyle(columnStyle);
						cell11.setCellValue(DictUtils.getDictLabel(eo.getReasonType(), "re_dispatch_cause", ""));
						
						//第13个是原因
						HSSFCell cell12 = row.createCell(12);
						cell12.setCellStyle(columnStyle);
						cell12.setCellValue(eo.getReasonRemarks());
						
						rowNo++;
					}
					for(int j=0;j<7;j++){
						//合并单元格
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
		font.setColor(HSSFFont.COLOR_NORMAL);//字体颜色
		
		font.setFontName("黑体");//字体
		//font.setFontHeightInPoints((short)20);//字体高度
		font.setFontHeight((short)220);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//宽度
		//單元格樣式--标题
		HSSFCellStyle cellStyle = workbook.createCellStyle();


		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setLocked(true);
		cellStyle.setWrapText(true);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		cellStyle.setBorderLeft((short) 1);// 边框的大小
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		cellStyle.setBorderRight((short) 1);// 边框的大小
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框的颜色
		cellStyle.setBorderTop((short) 1);// 边框的大小
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);// 下边框的颜色
		cellStyle.setBorderBottom((short) 1);// 边框的大小
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


		//单元格样式
		HSSFCellStyle columnStyle = workbook.createCellStyle();
		columnStyle.setLeftBorderColor(HSSFColor.BLACK.index); // 左边框线的颜色
		columnStyle.setBorderLeft((short) 1);// 左边框线的大小
		columnStyle.setRightBorderColor(HSSFColor.BLACK.index); // 右边框线的颜色
		columnStyle.setBorderRight((short) 1);// 右边框线的大小
		columnStyle.setTopBorderColor(HSSFColor.BLACK.index); // 上边框线的颜色
		columnStyle.setBorderTop((short) 1);// 上边框线的大小
		columnStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框线的颜色
		columnStyle.setBorderBottom((short) 1);// 下边框线的大小
		columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		columnStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		HSSFDataFormat format = workbook.createDataFormat(); 
		//设置单元格为@就是指文本型，防止变成科学计数法
		cellStyle.setDataFormat(format.getFormat("@"));
		columnStyle.setDataFormat(format.getFormat("@"));
		// 单元格宽度
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
		/*//标题---订单信息
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(30);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnStyle);
		cell.setCellValue(new HSSFRichTextString("项目经理被换明细"));

		for(int i=0;i<13;i++){
			HSSFCell cella = rowTitle.createCell(i+1);
			cella.setCellStyle(columnStyle);
		}

		//合并单元格--开始行，结束行，开始列，结束列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));*/


		//标题
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
			int rowNo = 1;//当前行号，从第1行开始
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
						//从第2行开始
						row = sheet.createRow(rowNo);
						//第一个是门店
						HSSFCell cell0 = row.createCell(0);
						cell0.setCellStyle(columnStyle);
						cell0.setCellValue(BizDictUtils.getStoreLabel(b.getStoreid(), ""));
						
						//第二个是工程模式
						HSSFCell cell1 = row.createCell(1);
						cell1.setCellStyle(columnStyle);
						cell1.setCellValue(DictUtils.getDictLabel(b.getProjectMode(), "employee_project_mode", ""));
						
						//第三个是区域
						HSSFCell cell2 = row.createCell(2);
						cell2.setCellStyle(columnStyle);
						cell2.setCellValue(b.getDepartmentName());
						
						//第四个是工人组长
						HSSFCell cell3 = row.createCell(3);
						cell3.setCellStyle(columnStyle);
						cell3.setCellValue(b.getRealname());

						//第五个是手机号
						HSSFCell cell4 = row.createCell(4);
						cell4.setCellStyle(columnStyle);
						cell4.setCellValue(b.getPhone()+"");
						//cell4.setcell
						
						//6星级
						HSSFCell cell5 = row.createCell(5);
						cell5.setCellStyle(columnStyle);
						if(b.getStar()!=null){
							cell5.setCellValue(DictUtils.getDictLabel(b.getStar().toString(), "emp_star", ""));
						}else{
							cell5.setCellValue("");
						}
						
						//第7个是工种
						HSSFCell cell6 = row.createCell(6);
						cell6.setCellStyle(columnStyle);
						cell6.setCellValue(DictUtils.getDictLabel(b.getWorktype().toString(), "emp_work_type", ""));
						
						
						// 第8个是被换累计次数
						HSSFCell cell7 = row.createCell(7);
						cell7.setCellStyle(columnStyle);
						cell7.setCellValue(b.getExchangeOrderTimes());
						
						//第9个是被换时间
						HSSFCell cell8 = row.createCell(8);
						cell8.setCellStyle(columnStyle);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
						cell8.setCellValue(sdf.format(eo.getExChangeDate()));
						
						// 第10个是客户
						HSSFCell cell9 = row.createCell(9);
						cell9.setCellStyle(columnStyle);
						cell9.setCellValue(eo.getAdd());
						
						// 第11个是原工人组长
						HSSFCell cell10 = row.createCell(10);
						cell10.setCellStyle(columnStyle);
						cell10.setCellValue(eo.getOldLeaderName());
						
						//第12个是新工人组长
						HSSFCell cell11 = row.createCell(11);
						cell11.setCellStyle(columnStyle);
						cell11.setCellValue(eo.getNewLeaderName());
						
						//第13个是原因
						HSSFCell cell12 = row.createCell(12);
						cell12.setCellStyle(columnStyle);
						cell12.setCellValue(DictUtils.getDictLabel(eo.getReasonType(), "re_dispatch_cause", ""));
						
						//第14个是说明
						HSSFCell cell13 = row.createCell(13);
						cell13.setCellStyle(columnStyle);
						cell13.setCellValue(eo.getReasonRemarks());
						rowNo++;
					}
					for(int j=0;j<8;j++){
						//合并单元格
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
