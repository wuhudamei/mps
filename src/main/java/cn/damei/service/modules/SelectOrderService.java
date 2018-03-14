/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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
import cn.damei.entity.modules.ItemManagerMap;
import cn.damei.dao.modules.SelectOrderDao;
import cn.damei.entity.modules.SelectOrder;

/**
 * 订单管理Service
 * 
 * @author wyb
 * @version 2016-09-08
 */
@Service
@Transactional(readOnly = true)
public class SelectOrderService extends CrudService2<SelectOrderDao, SelectOrder> {


	public SelectOrder get(Integer id) {
		return super.get(id);
	}

	/**
	 * 订单明细查询
	 */
	public List<SelectOrder> findList(SelectOrder selectOrder) {
		return super.findList(selectOrder);
	}
	
	/**
	 * 工地地图
	 * @param selectOrder
	 * @return
	 */
	public List<SelectOrder> findListMap(SelectOrder selectOrder) {
		
		return dao.findListMap(selectOrder);
	}
	
	public List<ItemManagerMap> findManagerMoreCount(Map<String,Object> map){
		
		
		return dao.findManagerMoreCount(map);
	}
	public List<ItemManagerMap> findManagerMoreCount1(List<SelectOrder> list){
		
		return dao.findManagerMoreCount1(list);
	}
	
	
	
	

	public Page<SelectOrder> findPage(Page<SelectOrder> page, SelectOrder selectOrder) {
		return super.findPage(page, selectOrder);
	}

	/**
	 * 导出excel--订单信息
	 * @param selectOrder
	 * @return
	 */
	public HSSFWorkbook exportExcel(SelectOrder selectOrder) {
		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet("订单信息");// 创建一个Excel的Sheet
		
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
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 6000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 3000);
		sheet.setColumnWidth(11, 3000);
		sheet.setColumnWidth(12, 3000);
		sheet.setColumnWidth(13, 3000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		sheet.setColumnWidth(16, 3000);
		sheet.setColumnWidth(17, 3000);
		
		//标题---订单信息
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(30);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue(new HSSFRichTextString("订单信息"));
		for(int i=0;i<17;i++){
			HSSFCell cella = rowTitle.createCell(i+1);
			cella.setCellStyle(columnHeadStyle);
		}
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 17));// 
		
		//标题
		HSSFRow rowTitle2 = sheet.createRow(1);
		
		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(columnHeadStyle);
		headCell0.setCellValue("序号");
		
		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(columnHeadStyle);
		headCell1.setCellValue("订单号");
		
		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(columnHeadStyle);
		headCell2.setCellValue("接单日期");
		
		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(columnHeadStyle);
		headCell3.setCellValue("派单日期");
		
		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(columnHeadStyle);
		headCell4.setCellValue("客户姓名");
		
		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(columnHeadStyle);
		headCell5.setCellValue("电话");
		
		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(columnHeadStyle);
		headCell6.setCellValue("工程地址");
		
		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(columnHeadStyle);
		headCell7.setCellValue("订单区域");
		
		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(columnHeadStyle);
		headCell8.setCellValue("工程期限");
		
		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(columnHeadStyle);
		headCell10.setCellValue("设计师");
		
		HSSFCell headCell11 = rowTitle2.createCell(11);
		headCell11.setCellStyle(columnHeadStyle);
		headCell11.setCellValue("设计师电话");
		
		HSSFCell headCell12 = rowTitle2.createCell(12);
		headCell12.setCellStyle(columnHeadStyle);
		headCell12.setCellValue("项目经理星级");
		
		HSSFCell headCell13 = rowTitle2.createCell(13);
		headCell13.setCellStyle(columnHeadStyle);
		headCell13.setCellValue("项目经理");
		
		HSSFCell headCell14 = rowTitle2.createCell(14);
		headCell14.setCellStyle(columnHeadStyle);
		headCell14.setCellValue("项目经理电话");
		
		HSSFCell headCell15 = rowTitle2.createCell(15);
		headCell15.setCellStyle(columnHeadStyle);
		headCell15.setCellValue("原工地数");
		
		HSSFCell headCell16 = rowTitle2.createCell(16);
		headCell16.setCellStyle(columnHeadStyle);
		headCell16.setCellValue("现工地数");
		
		HSSFCell headCell17 = rowTitle2.createCell(17);
		headCell17.setCellStyle(columnHeadStyle);
		headCell17.setCellValue("备注");
		
		HSSFRow rowTitle3 = sheet.createRow(2);
		for(int i=0;i<17;i++){
			HSSFCell cella = rowTitle3.createCell(i);
			cella.setCellStyle(columnHeadStyle);
			if(i==8){
				cella.setCellValue("合同签订开工");
			}
			if(i==9){
				cella.setCellValue("合同签订竣工");
			}
		}
		
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 9));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 7, 7));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 10, 10));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 11, 11));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 12, 12));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 14, 14));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 15, 15));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 16, 16));// 
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 17, 17));// 
		
		
		//内容  数据
		String houseisnow =selectOrder.getHouseIsNew();
		if(null!=houseisnow && houseisnow!="" && houseisnow.equals("2")){
			selectOrder.setHouseIsNew("");
		}
		if(selectOrder.getOrderStatusNumber()!=null){
			String[] status = selectOrder.getOrderStatusNumber().split(",");
			if(null!=status && status.length>0){
				selectOrder.setOrderStatusList(Arrays.asList(status));
			}
		}
		List<SelectOrder> list =dao.findList(selectOrder);
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				SelectOrder order = list.get(i);
				HSSFRow row = sheet.createRow(i+3);
				
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(i+1);
				
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getOrderNumber())){
					cell1.setCellValue(order.getOrderNumber());
				}

				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if(null!=order.getGetOrderDatetime()){
					cell2.setCellValue(DateFormatUtils.format(order.getGetOrderDatetime(), "yyyy-MM-dd"));
				}
				
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if(null!=order.getOrderDistributeLogDate()){
					cell3.setCellValue(DateFormatUtils.format(order.getOrderDistributeLogDate(), "yyyy-MM-dd HH:mm:ss"));
				}
				
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getCustomerName())){
					cell4.setCellValue(order.getCustomerName());
				}
				
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getCustomerPhone())){
					cell5.setCellValue(order.getCustomerPhone());
				}
				
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getDetailAddress())){
					cell6.setCellValue(order.getDetailAddress());
				}
				
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getEngineDepartName())){
					cell7.setCellValue(order.getEngineDepartName());
				}
				
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if(null!=order.getContractStartDate()){
					cell8.setCellValue(DateFormatUtils.format(order.getContractStartDate(), "yyyy-MM-dd"));
				}
				
				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(columnStyle);
				if(null!=order.getContractEndDate()){
					cell9.setCellValue(DateFormatUtils.format(order.getContractEndDate(), "yyyy-MM-dd"));
				}
				
				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getDesignerName())){
					cell10.setCellValue(order.getDesignerName());
				}
				
				HSSFCell cell11 = row.createCell(11);
				cell11.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getDesignerPhone())){
					cell11.setCellValue(order.getDesignerPhone());
				}
				
				HSSFCell cell12 = row.createCell(12);
				cell12.setCellStyle(columnStyle);
				if(null != order.getItemManagerStar() && order.getItemManagerStar().intValue()>0){
					cell12.setCellValue(order.getItemManagerStar());
				}
				
				HSSFCell cell13 = row.createCell(13);
				cell13.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getItemManager())){
					cell13.setCellValue(order.getItemManager());
				}
				
				HSSFCell cell14 = row.createCell(14);
				cell14.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getItemManagerPhone())){
					cell14.setCellValue(order.getItemManagerPhone());
				}
				
				HSSFCell cell15 = row.createCell(15);
				cell15.setCellStyle(columnStyle);
				if(null != order.getBeforeSiteCount()){
					cell15.setCellValue(order.getBeforeSiteCount());
				}
				
				HSSFCell cell16 = row.createCell(16);
				cell16.setCellStyle(columnStyle);
				if(null != order.getNowSiteCount()){
					cell16.setCellValue(order.getNowSiteCount());
				}
				
				HSSFCell cell17 = row.createCell(17);
				cell17.setCellStyle(columnStyle);
			}
		}
		
		
		
		
		return wb;
	}

	



}