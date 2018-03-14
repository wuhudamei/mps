package cn.damei.service.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.SendItemMgrDetailDao;
import cn.damei.entity.modules.SendItemMgrDetail;

/**
 * @ClassName: SendItemMgrDetailService 
 * @Description: 派项目经理明细Service
 * @author huhanwei 
 * @date 2017年6月21日 下午2:32:45
 */
@Service
@Transactional(readOnly = true)
public class SendItemMgrDetailService extends CrudService2<SendItemMgrDetailDao, SendItemMgrDetail> {
	
	public Page<SendItemMgrDetail> findPage(Page<SendItemMgrDetail> page, SendItemMgrDetail sendItemMgrDetail) {
		return super.findPage(page, sendItemMgrDetail);
	}
	
//	public List<SendItemMgrDetail> findListToExport(SendItemMgrDetail sendItemMgrDetail){
//		return dao.findListToExport(sendItemMgrDetail);
//	}
	
	/**
	 * @Title: exportExcel 
	 * @Description: 导出excel
	 * @param itemMgrStarAndOrdTb Controller传入Vo对象
	 */
	public HSSFWorkbook exportExcel(SendItemMgrDetail sendItemMgrDetail) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String sheetName = "项目经理星级接单明细表-" + sf.format(sendItemMgrDetail.getBeginCreateDate()) + "至" + sf.format(sendItemMgrDetail.getEndCreateDate());
		
		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet(sheetName);// 创建一个Excel的Sheet
		
		//设置字体
		HSSFFont font = wb.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL);//字体颜色
		font.setFontName("黑体");//字体
		font.setFontHeightInPoints((short)10);//字体高度
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//宽度
		
		//单元格样式--标题
		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		columnHeadStyle.setFont(font);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		columnHeadStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框的颜色
		columnHeadStyle.setBorderTop((short) 1);// 边框的大小
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);// 下边框的颜色
		columnHeadStyle.setBorderBottom((short) 1);// 边框的大小
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		columnHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		columnHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);		
		
		//单元格样式
		HSSFCellStyle columnStyle = wb.createCellStyle();
		columnStyle.setLeftBorderColor(HSSFColor.BLACK.index); // 左边框线的颜色
		columnStyle.setBorderLeft((short) 1);// 左边框线的大小
		columnStyle.setRightBorderColor(HSSFColor.BLACK.index); // 右边框线的颜色
		columnStyle.setBorderRight((short) 1);// 右边框线的大小
		columnStyle.setTopBorderColor(HSSFColor.BLACK.index); // 上边框线的颜色
		columnStyle.setBorderTop((short) 1);// 上边框线的大小
		columnStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框线的颜色
		columnStyle.setBorderBottom((short) 1);// 下边框线的大小
		columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		// 单元格宽度
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 3000);
		sheet.setColumnWidth(11, 5000);
		
		
		//标题---订单信息
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(30);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue(new HSSFRichTextString(sheetName));
		for(int i=0;i<11;i++){
			HSSFCell cella = rowTitle.createCell(i+1);
			cella.setCellStyle(columnHeadStyle);
		}
		
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));

		//标题
		HSSFRow rowTitle2 = sheet.createRow(1);
		
		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(columnHeadStyle);
		headCell0.setCellValue("序号");
		
		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(columnHeadStyle);
		headCell1.setCellValue("门店");
		
		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(columnHeadStyle);
		headCell2.setCellValue("工程模式");
		
		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(columnHeadStyle);
		headCell3.setCellValue("区域");
		
		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(columnHeadStyle);
		headCell4.setCellValue("员工编号");
		
		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(columnHeadStyle);
		headCell5.setCellValue("项目经理");
		
		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(columnHeadStyle);
		headCell6.setCellValue("项目经理电话");
		
		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(columnHeadStyle);
		headCell7.setCellValue("星级");
		
		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(columnHeadStyle);
		headCell8.setCellValue("接单数");
		
		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(columnHeadStyle);
		headCell9.setCellValue("总接单数");
		
		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(columnHeadStyle);
		headCell10.setCellValue("是否停单");
		
		HSSFCell headCell11 = rowTitle2.createCell(11);
		headCell11.setCellStyle(columnHeadStyle);
		headCell11.setCellValue("备注");
		
		List<SendItemMgrDetail> list = dao.findListToExport(sendItemMgrDetail);
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				SendItemMgrDetail mgrDetail = list.get(i);
				HSSFRow row = sheet.createRow(i+2);
				
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(i+1);
				
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getStoreName())) {
					cell1.setCellValue(mgrDetail.getStoreName());
				}
				
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getProjectModeName())) {
					cell2.setCellValue(mgrDetail.getProjectModeName());
				}
				
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getEngineDepartName())) {
					cell3.setCellValue(mgrDetail.getEngineDepartName());
				}
				
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getItemManagerId())) {
					cell4.setCellValue(mgrDetail.getItemManagerId());
				}
				
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getItemManager())) {
					cell5.setCellValue(mgrDetail.getItemManager());
				}
				
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getItemManagerPhone())) {
					cell6.setCellValue(mgrDetail.getItemManagerPhone());
				}
				
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if (null != mgrDetail.getStar() && mgrDetail.getStar().intValue() > 0) {
					String itemMgrStar = "";
					switch (mgrDetail.getStar()) {
					case 1:
						itemMgrStar = "一星";
						break;
					case 2:
						itemMgrStar = "二星";
						break;
					case 3:
						itemMgrStar = "三星";
						break;
					case 4:
						itemMgrStar = "四星";
						break;
					case 5:
						itemMgrStar = "五星";
						break;
					default:
						itemMgrStar = "";
						break;
					}
					cell7.setCellValue(itemMgrStar);
				}
				
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getOrderCount())) {
					cell8.setCellValue(mgrDetail.getOrderCount());
				}
				
				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getOrderTotalCount())) {
					cell9.setCellValue(mgrDetail.getOrderTotalCount());
				}
				
				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(columnStyle);
				if (null != mgrDetail.getOrderStop()) {
					if (mgrDetail.getOrderStop() == 0) {
						cell10.setCellValue("否");
					} else {
						cell10.setCellValue("是");
					}
				}
				
				HSSFCell cell11 = row.createCell(11);
				cell11.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getRemarks())) {
					cell11.setCellValue(mgrDetail.getRemarks());
				}
			}
		}
		return wb;
	}

	public void exportDetailExcel(SendItemMgrDetail sendItemMgrDetail, HttpServletResponse response) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String fileName = sf.format(sendItemMgrDetail.getBeginCreateDate()) + "-" + sf.format(sendItemMgrDetail.getEndCreateDate())+"派单表";
		HSSFWorkbook excel = exportExcelDispatch(sendItemMgrDetail);
		
		ServletOutputStream out= null;//创建一个输出流对象
		try {
			response.setContentType("application/binary;charset=utf-8");
			String headerStr =new String(fileName.getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(out != null){
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 导出派单明细表
	 * @param sendItemMgrDetail
	 * @return
	 */
	private HSSFWorkbook exportExcelDispatch(SendItemMgrDetail sendItemMgrDetail) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String sheetName = "派单日报表";
		
		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet(sheetName);// 创建一个Excel的Sheet
		
		//设置字体
		HSSFFont font = wb.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL);//字体颜色
		font.setFontName("黑体");//字体
		font.setFontHeightInPoints((short)10);//字体高度
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//宽度
		
		//单元格样式--标题
		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		columnHeadStyle.setFont(font);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		
		columnHeadStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框的颜色
		columnHeadStyle.setBorderTop((short) 1);// 边框的大小
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);// 下边框的颜色
		columnHeadStyle.setBorderBottom((short) 1);// 边框的大小
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		columnHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		columnHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);		
		
		//单元格样式
		HSSFCellStyle columnStyle = wb.createCellStyle();
		columnStyle.setLeftBorderColor(HSSFColor.BLACK.index); // 左边框线的颜色
		columnStyle.setBorderLeft((short) 1);// 左边框线的大小
		columnStyle.setRightBorderColor(HSSFColor.BLACK.index); // 右边框线的颜色
		columnStyle.setBorderRight((short) 1);// 右边框线的大小
		columnStyle.setTopBorderColor(HSSFColor.BLACK.index); // 上边框线的颜色
		columnStyle.setBorderTop((short) 1);// 上边框线的大小
		columnStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框线的颜色
		columnStyle.setBorderBottom((short) 1);// 下边框线的大小
		columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		// 单元格宽度
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 3000);
		sheet.setColumnWidth(11, 5000);
		sheet.setColumnWidth(12, 5000);
		sheet.setColumnWidth(13, 5000);
		sheet.setColumnWidth(14, 5000);
		sheet.setColumnWidth(15, 5000);
		sheet.setColumnWidth(16, 5000);
		sheet.setColumnWidth(17, 5000);
		sheet.setColumnWidth(18, 5000);
		
		
		//标题---订单信息
		
		HSSFRow rowTitle21 = sheet.createRow(0);
		rowTitle21.setHeightInPoints(30);
		HSSFCell headCell01 = rowTitle21.createCell(0);
		headCell01.setCellStyle(columnHeadStyle);
		headCell01.setCellValue(sheetName);
	
		for(int i=0;i<17;i++){
			HSSFCell cella = rowTitle21.createCell(i+1);
			cella.setCellStyle(columnHeadStyle);
		}
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 17));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));// 序号
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));//订单编号
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));//派单日期
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));//客户
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));//电话
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));//工程地址
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));//片区
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 7,8));//工程期限
		
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 9,9));//设计师
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 10,10));//设计师电话
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 11,11));//星级
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 12,12));//项目经理
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 13,13));//项目经理电话
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 14,14));//日派单数
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 15,15));//月累计
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 16,16));//总累计数
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 17,17));//备注
		

		//标题
		
		
		HSSFRow rowTitle2 = sheet.createRow(1);
		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(columnHeadStyle);
		headCell0.setCellValue("序号");
		
		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(columnHeadStyle);
		headCell1.setCellValue("订单编号");
		
		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(columnHeadStyle);
		headCell2.setCellValue("派单日期");
		
		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(columnHeadStyle);
		headCell3.setCellValue("客户");
		
		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(columnHeadStyle);
		headCell4.setCellValue("电话");
		
		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(columnHeadStyle);
		headCell5.setCellValue("工程地址");
		
		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(columnHeadStyle);
		headCell6.setCellValue("区域");
		
		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(columnHeadStyle);
		headCell7.setCellValue("工程期限");
		
		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(columnHeadStyle);
		
		
		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(columnHeadStyle);
		headCell9.setCellValue("设计师");
		
		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(columnHeadStyle);
		headCell10.setCellValue("设计师电话");
		
		HSSFCell headCell11 = rowTitle2.createCell(11);
		headCell11.setCellStyle(columnHeadStyle);
		headCell11.setCellValue("星级");
		
		HSSFCell headCell12 = rowTitle2.createCell(12);
		headCell12.setCellStyle(columnHeadStyle);
		headCell12.setCellValue("项目经理");
		
		HSSFCell headCell13 = rowTitle2.createCell(13);
		headCell13.setCellStyle(columnHeadStyle);
		headCell13.setCellValue("项目经理电话");
		
		HSSFCell headCell14 = rowTitle2.createCell(14);
		headCell14.setCellStyle(columnHeadStyle);
		headCell14.setCellValue("日派单数");
		
		HSSFCell headCell15 = rowTitle2.createCell(15);
		headCell15.setCellStyle(columnHeadStyle);
		headCell15.setCellValue("月累计");
		
		HSSFCell headCell16 = rowTitle2.createCell(16);
		headCell16.setCellStyle(columnHeadStyle);
		headCell16.setCellValue("总累计数");
		
		HSSFCell headCell17 = rowTitle2.createCell(17);
		headCell17.setCellStyle(columnHeadStyle);
		headCell17.setCellValue("备注");
		
		HSSFRow rowTitle3 = sheet.createRow(2);
		for(int i=0;i<18;i++){
			HSSFCell cella = rowTitle3.createCell(i);
			cella.setCellStyle(columnHeadStyle);
			if(i==7){
				cella.setCellValue("开工日期");
			}
			if(i==8){
				cella.setCellValue("竣工日期");
			}	
		}
		
	
		
		List<SendItemMgrDetail> list = dao.findDispatchDetailToExport(sendItemMgrDetail);
		//统计累计月
		List<String> month = new ArrayList<>();
		//统计累计日
		List<String> days = new ArrayList<>();
		
		List<SendItemMgrDetail> listManagerCount =  dao.findManagerCount();
		for (SendItemMgrDetail sendItemMgrDetail2 : list) {
			month.add(sendItemMgrDetail2.getDispatchMonth());
			days.add(sendItemMgrDetail2.getDispatchDays());
			String itemManagerId = sendItemMgrDetail2.getItemManagerId();
			for (SendItemMgrDetail sendItemMgrDetail3 : listManagerCount) {
				String itemManagerId2 = sendItemMgrDetail3.getItemManagerId();
				if(itemManagerId2 != null){
					if(itemManagerId2.equals(itemManagerId)){
						sendItemMgrDetail2.setManagerCount(sendItemMgrDetail3.getManagerCount());
					}
				}
				
			}
		}
		Map<String,String> map = new HashMap<String,String>();
		Set<String> uniqueSet = new HashSet<>(month);
	    for (String temp : uniqueSet) {
	    	int frequency = Collections.frequency(month, temp);
	        map.put(temp, frequency+"");
	       }
	    Set<String> keySet = map.keySet();
	        for (String str : keySet) {
	        	for (SendItemMgrDetail sendItemMgrDetail2 : list) {
	    			if(str.equals(sendItemMgrDetail2.getDispatchMonth())){
	    				String string = map.get(str);
	    				sendItemMgrDetail2.setMonthDispathCount(string);
	    			}
	    		}
			}
	   
	      
				
				Map<String,String> mapdays = new HashMap<String,String>();
				Set<String> uniqueSetdays = new HashSet<>(days);
			    for (String temp : uniqueSetdays) {
			    	int frequency = Collections.frequency(days, temp);
			    	mapdays.put(temp, frequency+"");
			       }
		    	Set<String> keySetdays = mapdays.keySet();
		        for (String str : keySetdays) {
		        	for (SendItemMgrDetail sendItemMgrDetail2 : list) {
		    			if(str.equals(sendItemMgrDetail2.getDispatchDays())){
		    				String string = mapdays.get(str);
		    				sendItemMgrDetail2.setDailySendSingular(string);
		    			}
		    		}
				}
		
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				SendItemMgrDetail mgrDetail = list.get(i);
				HSSFRow row = sheet.createRow(i+3);
				
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(i+1);
				
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getOrderNumber())) {
					cell1.setCellValue(mgrDetail.getOrderNumber());
				}
				
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if (mgrDetail.getCreateDate() != null) {
					cell2.setCellValue(sf.format(mgrDetail.getCreateDate()));
				}
				
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getCustomerName())) {
					cell3.setCellValue(mgrDetail.getCustomerName());
				}
				
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getCustomerPhone())) {
					cell4.setCellValue(mgrDetail.getCustomerPhone());
				}
				
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getCustomerAddress())) {
					cell5.setCellValue(mgrDetail.getCustomerAddress());
				}
				
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getEngineDepartName())) {
					cell6.setCellValue(mgrDetail.getEngineDepartName());
				}
				
				HSSFCell cell8 = row.createCell(7);
				cell8.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getContractStartDate())) {
					cell8.setCellValue(mgrDetail.getContractStartDate());
				}
				
				HSSFCell cell9 = row.createCell(8);
				cell9.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getContractEndDate())) {
					cell9.setCellValue(mgrDetail.getContractEndDate());
				}
				
				HSSFCell cell10 = row.createCell(9);
				cell10.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getDesignerName())) {
					cell10.setCellValue(mgrDetail.getDesignerName());
				}
						
				
				
				HSSFCell cell11 = row.createCell(10);
				cell11.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getDesignerPhone())) {
					cell11.setCellValue(mgrDetail.getDesignerPhone());
				}
				
				HSSFCell cell7 = row.createCell(11);
				cell7.setCellStyle(columnStyle);
				if (null != mgrDetail.getStar() && !"".equals(mgrDetail.getStar()) && mgrDetail.getStar() > 0) {
					String itemMgrStar = "";
					switch (mgrDetail.getStar()) {
					case 1:
						itemMgrStar = "一星";
						break;
					case 2:
						itemMgrStar = "二星";
						break;
					case 3:
						itemMgrStar = "三星";
						break;
					case 4:
						itemMgrStar = "四星";
						break;
					case 5:
						itemMgrStar = "五星";
						break;
					default:
						itemMgrStar = "";
						break;
					}
					cell7.setCellValue(itemMgrStar);
				}
				
				
				HSSFCell cell12 = row.createCell(12);
				cell12.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getItemManager() ) ) {
					cell12.setCellValue(mgrDetail.getItemManager() );
				}
				
				HSSFCell cell13 = row.createCell(13);
				cell13.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getItemManagerPhone() ) ) {
					cell13.setCellValue(mgrDetail.getItemManagerPhone() );
				}
				
				HSSFCell cell14 = row.createCell(14);
				cell14.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getItemManagerPhone() ) ) {
					cell14.setCellValue(mgrDetail.getDailySendSingular() );
				}
				
				HSSFCell cell15 = row.createCell(15);
				cell15.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getMonthDispathCount() ) ) {
					cell15.setCellValue(mgrDetail.getMonthDispathCount() );
				}
				HSSFCell cell16 = row.createCell(16);
				cell16.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(mgrDetail.getMonthDispathCount() ) ) {
					cell16.setCellValue(mgrDetail.getManagerCount() );
				}
				HSSFCell cell17 = row.createCell(17);
				cell17.setCellStyle(columnStyle);
			}
		}
		return wb;
	}
		
	
}
