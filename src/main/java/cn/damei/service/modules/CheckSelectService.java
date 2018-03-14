
package cn.damei.service.modules;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.CheckSelectDao;
import cn.damei.entity.modules.CheckSelect;


@Service
@Transactional(readOnly = true)
public class CheckSelectService extends CrudService<CheckSelectDao, CheckSelect> {

	public CheckSelect get(String id) {
		return super.get(id);
	}
	
	public List<CheckSelect> findList(CheckSelect checkSelect) {
		return super.findList(checkSelect);
	}
	
	public Page<CheckSelect> findPage(Page<CheckSelect> page, CheckSelect checkSelect) {
		return super.findPage(page, checkSelect);
	}
	

	public HSSFWorkbook exportExcel(CheckSelect checkSelect) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("项目约检信息");
		

		HSSFFont font = wb.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL);
		font.setFontName("黑体");
		font.setFontHeightInPoints((short)10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		

		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		columnHeadStyle.setFont(font);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setBorderLeft((short) 1);
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setBorderRight((short) 1);
		columnHeadStyle.setTopBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setBorderTop((short) 1);
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setBorderBottom((short) 1);
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		columnHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);		
		
		

		HSSFCellStyle columnStyle = wb.createCellStyle();
		columnStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderLeft((short) 1);
		columnStyle.setRightBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderRight((short) 1);
		columnStyle.setTopBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderTop((short) 1);
		columnStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderBottom((short) 1);
		columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		

		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 6000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 6000);
		sheet.setColumnWidth(10, 6000);
		sheet.setColumnWidth(11, 6000);
		sheet.setColumnWidth(12, 6000);
		sheet.setColumnWidth(13, 6000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		
		

		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(30);
		
		HSSFCell cell0 = rowTitle.createCell(0);
		cell0.setCellStyle(columnHeadStyle);
		cell0.setCellValue("门店");
		
		HSSFCell cell1 = rowTitle.createCell(1);
		cell1.setCellStyle(columnHeadStyle);
		cell1.setCellValue("工程模式");
		
		HSSFCell cell2 = rowTitle.createCell(2);
		cell2.setCellStyle(columnHeadStyle);
		cell2.setCellValue("区域");
		
		HSSFCell cell3 = rowTitle.createCell(3);
		cell3.setCellStyle(columnHeadStyle);
		cell3.setCellValue("小区");
		
		HSSFCell cell4 = rowTitle.createCell(4);
		cell4.setCellStyle(columnHeadStyle);
		cell4.setCellValue("客户");
		
		HSSFCell cell5 = rowTitle.createCell(5);
		cell5.setCellStyle(columnHeadStyle);
		cell5.setCellValue("详细地址");
		
		HSSFCell cell6 = rowTitle.createCell(6);
		cell6.setCellStyle(columnHeadStyle);
		cell6.setCellValue("项目经理");
		
		HSSFCell cell7 = rowTitle.createCell(7);
		cell7.setCellStyle(columnHeadStyle);
		cell7.setCellValue("质检员");
		
		HSSFCell cell8 = rowTitle.createCell(8);
		cell8.setCellStyle(columnHeadStyle);
		cell8.setCellValue("实际开工日期");
		
		HSSFCell cell9 = rowTitle.createCell(9);
		cell9.setCellStyle(columnHeadStyle);
		cell9.setCellValue("约检节点名称");
		
		HSSFCell cell10 = rowTitle.createCell(10);
		cell10.setCellStyle(columnHeadStyle);
		cell10.setCellValue("项目经理提报申请时间");
		
		HSSFCell cell11 = rowTitle.createCell(11);
		cell11.setCellStyle(columnHeadStyle);
		cell11.setCellValue("项目经理申请质检员上门日期");
		
		HSSFCell cell12 = rowTitle.createCell(12);
		cell12.setCellStyle(columnHeadStyle);
		cell12.setCellValue("质检员实际上门日期");
		
		HSSFCell cell13 = rowTitle.createCell(13);
		cell13.setCellStyle(columnHeadStyle);
		cell13.setCellValue("质检员确认节点验收通过日期");
		
		HSSFCell cell14 = rowTitle.createCell(14);
		cell14.setCellStyle(columnHeadStyle);
		cell14.setCellValue("总分");
		
		HSSFCell cell15 = rowTitle.createCell(15);
		cell15.setCellStyle(columnHeadStyle);
		cell15.setCellValue("实际得分");
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatTwo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		checkSelect.setIsRecheck(ConstantUtils.IS_RECHECK_0);
		checkSelect.setQcBillType(ConstantUtils.QC_BILL_TYPE_1);
		checkSelect.setSignType(ConstantUtils.SIGN_TYPE_INSPECTOR_CHECK_301);
		List<CheckSelect> list =dao.findList(checkSelect);
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				CheckSelect check = list.get(i);
				HSSFRow row = sheet.createRow(i+1);
				
				HSSFCell cellData0 = row.createCell(0);
				cellData0.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(check.getStoreName())){
					cellData0.setCellValue(check.getStoreName());
				}
				
				HSSFCell cellData1 = row.createCell(1);
				cellData1.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(check.getProjectModeName())){
					cellData1.setCellValue(check.getProjectModeName());
				}
				
				HSSFCell cellData2 = row.createCell(2);
				cellData2.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(check.getEngineDepartName())){
					cellData2.setCellValue(check.getEngineDepartName());
				}
				
				HSSFCell cellData3 = row.createCell(3);
				cellData3.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(check.getCommunityName()) || StringUtils.isNoneBlank(check.getBuildNumber()) || StringUtils.isNoneBlank(check.getBuildUnit()) || StringUtils.isNoneBlank(check.getBuildRoom())){
					cellData3.setCellValue(check.getCommunityName()+"-"+check.getBuildNumber()+"-"+check.getBuildUnit()+"-"+check.getBuildRoom());
				}
				
				HSSFCell cellData4 = row.createCell(4);
				cellData4.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(check.getCustomerName())){
					cellData4.setCellValue(check.getCustomerName());
				}
				
				HSSFCell cellData5 = row.createCell(5);
				cellData5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(check.getDetailAddress())){
					cellData5.setCellValue(check.getDetailAddress());
				}
				
				HSSFCell cellData6 = row.createCell(6);
				cellData6.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(check.getItemManager())){
					cellData6.setCellValue(check.getItemManager());
				}
				
				HSSFCell cellData7 = row.createCell(7);
				cellData7.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(check.getOrderInspector())){
					cellData7.setCellValue(check.getOrderInspector());
				}
				
				HSSFCell cellData8 = row.createCell(8);
				cellData8.setCellStyle(columnStyle);
				if(check.getActualStartDate() != null){
					cellData8.setCellValue(format.format(check.getActualStartDate()));
				}
				
				HSSFCell cellData9 = row.createCell(9);
				cellData9.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(check.getQcCheckNodeName())){
					cellData9.setCellValue(check.getQcCheckNodeName());
				}
				
				HSSFCell cellData10 = row.createCell(10);
				cellData10.setCellStyle(columnStyle);
				if(check.getCreateDate() != null){
					cellData10.setCellValue(formatTwo.format(check.getCreateDate()));
				}
				
				HSSFCell cellData11 = row.createCell(11);
				cellData11.setCellStyle(columnStyle);
				if(check.getExpectCheckDatetime() != null){
					cellData11.setCellValue(format.format(check.getExpectCheckDatetime()));
				}
				
				HSSFCell cellData12 = row.createCell(12);
				cellData12.setCellStyle(columnStyle);
				if(check.getCheckDatetime() != null){
					cellData12.setCellValue(format.format(check.getCheckDatetime()));
				}
				
				HSSFCell cellData13 = row.createCell(13);
				cellData13.setCellStyle(columnStyle);
				if(check.getAcceptCheckDatetime() != null){
					cellData13.setCellValue(format.format(check.getAcceptCheckDatetime()));
				}
				
				HSSFCell cellData14 = row.createCell(14);
				cellData14.setCellStyle(columnStyle);
				if(check.getTotalScore() != null){
					cellData14.setCellValue(check.getTotalScore());
				}
				
				HSSFCell cellData15 = row.createCell(15);
				cellData15.setCellStyle(columnStyle);
				if(check.getGotScore() != null){
					cellData15.setCellValue(check.getGotScore());
				}
				
				
			}
		}
		return wb;
	}
}