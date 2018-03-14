package cn.damei.service.modules;

import cn.damei.common.constantUtils.EmployeeContantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizEmployeeRegisteredDao;
import cn.damei.entity.modules.BizEmployeeRegistered;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@Service
@Transactional(readOnly = true)
public class BizEmployeeRegisteredService extends CrudService2<BizEmployeeRegisteredDao, BizEmployeeRegistered> {



	public void exportExcel(BizEmployeeRegistered bizEmployeeRegistered, HttpServletResponse response) {

		HSSFWorkbook excel = queryExportExcel(bizEmployeeRegistered);
		ServletOutputStream out= null;
		try {
			response.setContentType("application/binary;charset=utf-8");

			String empTypeName = "工人、项目经理";
			if(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_2.equals(bizEmployeeRegistered.getEmpType())){
				empTypeName = "工人";
			}else if(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_3.equals(bizEmployeeRegistered.getEmpType())){
				empTypeName = "项目经理";
			}
			String headerStr =new String((empTypeName+"在册人数"+ DateFormatUtils.format(bizEmployeeRegistered.getStartEntryDate(), "yyyy-MM-dd")+"至"+ DateFormatUtils.format(bizEmployeeRegistered.getEndEntryDate(), "yyyy-MM-dd")).getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
			out = response.getOutputStream();
			excel.write(out);
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(out!=null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public HSSFWorkbook queryExportExcel(BizEmployeeRegistered bizEmployeeRegistered) {

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("在册人数");


		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL);
		font.setFontName("黑体");
		font.setFontHeightInPoints((short)10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
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


		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);


		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(30);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue(new HSSFRichTextString("在册人数"));
		for(int i=0;i<3;i++){
			HSSFCell cella = rowTitle.createCell(i+1);
			cella.setCellStyle(columnHeadStyle);
		}

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));


		HSSFRow rowTitle2 = sheet.createRow(1);

		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(columnHeadStyle);
		headCell0.setCellValue("门店");

		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(columnHeadStyle);
		headCell1.setCellValue("月份");

		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(columnHeadStyle);
		headCell2.setCellValue("当月录入人数");

		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(columnHeadStyle);
		headCell3.setCellValue("单月在册人数");


		List<BizEmployeeRegistered> list = dao.queryExportExcel(bizEmployeeRegistered);
		if(CollectionUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				BizEmployeeRegistered employeeRegistered = list.get(i);

				HSSFRow row = sheet.createRow(i+2);


				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(employeeRegistered.getStoreIdName())){
					cell0.setCellValue(employeeRegistered.getStoreIdName());
				}


				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(employeeRegistered.getMonth())){
					cell1.setCellValue(employeeRegistered.getMonth());
				}


				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if(null!=employeeRegistered.getEmployeeEntriesCount()){
					cell2.setCellValue(employeeRegistered.getEmployeeEntriesCount());
				}


				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if(null!=employeeRegistered.getEmployeeRegisteredCount()){
					cell3.setCellValue(employeeRegistered.getEmployeeRegisteredCount());
				}
			}
		}

		return wb;
	}

}