/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import cn.damei.entity.modules.BizOrderInstallItemProblemVo;
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

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.InstallConstructBillProblemDao;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 *  安装工主材问题上报
 */
@Service
@Transactional(readOnly = true)
public class InstallConstructBillProblemService extends CrudService2<InstallConstructBillProblemDao, BizOrderInstallItemProblemVo> {

	
	public BizOrderInstallItemProblemVo get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderInstallItemProblemVo> findList(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo) {
		return super.findList(bizOrderInstallItemProblemVo);
	}
	
	public Page<BizOrderInstallItemProblemVo> findPage(Page<BizOrderInstallItemProblemVo> page, BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo) {
		return super.findPage(page, bizOrderInstallItemProblemVo);
	}

	/**
	 * 导出excel--安装工主材问题上报查询
	 * @param bizOrderInstallItemProblemVo
	 * @return
	 */
	public HSSFWorkbook exportExcel(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo) {
		
		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet("安装工主材问题上报查询");// 创建一个Excel的Sheet
		
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
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 3000);
		sheet.setColumnWidth(11, 3000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 4000);
		sheet.setColumnWidth(14, 5000);
		sheet.setColumnWidth(15, 3000);
		sheet.setColumnWidth(16, 8000);
		sheet.setColumnWidth(17, 8000);
		
		HSSFRow row = null;
        HSSFCell cell = null; 
        
        //1.大标题
        row = sheet.createRow(0);
        row.setHeightInPoints(30);
        cell = row.createCell(0);
        cell.setCellStyle(columnHeadStyle);
		cell.setCellValue(new HSSFRichTextString("安装工主材安装问题上报信息"));
		for(int i=0;i<17;i++){
			cell = row.createCell(i+1);
			cell.setCellStyle(columnHeadStyle);
		}
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 17));// 
				
		//2.小标题
		row = sheet.createRow(1);
		row.setHeightInPoints(30);
		
		cell = row.createCell(0);
        cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("序号");
		
		cell = row.createCell(1);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("客户");
		
		cell = row.createCell(2);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("电话");
		
		cell = row.createCell(3);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("地址");
		
		cell = row.createCell(4);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("计划开工");
		
		cell = row.createCell(5);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("计划竣工");
		
		cell = row.createCell(6);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("设计师");
		
		cell = row.createCell(7);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("电话");
		
		cell = row.createCell(8);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("项目经理");
		
		cell = row.createCell(9);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("电话");
		
		cell = row.createCell(10);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("质检员");
		
		cell = row.createCell(11);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("电话");
		
		cell = row.createCell(12);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("安装项名称");
		
		cell = row.createCell(13);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("供应商名称");
		
		cell = row.createCell(14);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("上报时间");
		
		cell = row.createCell(15);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("上报人");
		
		cell = row.createCell(16);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("问题分类");
		
		cell = row.createCell(17);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue("问题描述");
		
		
		User user = UserUtils.getUser();
		//过滤门店
		if(null==bizOrderInstallItemProblemVo.getStoreId()){
			if(null!=user.getStoreId()){
				bizOrderInstallItemProblemVo.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		//状态
		bizOrderInstallItemProblemVo.setStatus(BusinessProblemConstantUtil.BUSINESS_PROBLEM_STATUS_80);
		//业务类型
		bizOrderInstallItemProblemVo.setBusinessType(BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_5);
		
		List<BizOrderInstallItemProblemVo> list =dao.findExport(bizOrderInstallItemProblemVo);
		
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				
				BizOrderInstallItemProblemVo item = list.get(i);
				
				row = sheet.createRow(i+2);
				
				cell = row.createCell(0);
		        cell.setCellStyle(columnStyle);
				cell.setCellValue(i+1);
				
				cell = row.createCell(1);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getCustomerName());
				
				cell = row.createCell(2);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getCustomerPhone());
				
				cell = row.createCell(3);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getCommunityName()+"-"+item.getBuildNumber()+"-"+item.getBuildUnit()+"-"+item.getBuildRoom());
				
				cell = row.createCell(4);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(DateFormatUtils.format(item.getContractStartDate(), "yyyy-MM-dd"));
				
				cell = row.createCell(5);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(DateFormatUtils.format(item.getContractEndDate(), "yyyy-MM-dd"));
				
				cell = row.createCell(6);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getDesignerName());
				
				cell = row.createCell(7);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getDesignerPhone());
				
				cell = row.createCell(8);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getItemManager());
				
				cell = row.createCell(9);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getItemManagerPhone());
				
				cell = row.createCell(10);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getOrderInspector());
				
				cell = row.createCell(11);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getInspectorPhone());
				
				cell = row.createCell(12);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getInstallItemName());
				
				cell = row.createCell(13);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getSupplierName());
				
				cell = row.createCell(14);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(DateFormatUtils.format(item.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
				
				cell = row.createCell(15);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getProblemApplyEmployeeName());
				
				cell = row.createCell(16);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getProblemTypeName());
				
				cell = row.createCell(17);
				cell.setCellStyle(columnStyle);
				cell.setCellValue(item.getProblemDescribe());
				
			}
		}
		
		
		return wb;
		
	}

	


	
}