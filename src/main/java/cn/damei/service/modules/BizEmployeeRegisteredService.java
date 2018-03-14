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


/**
 * 在册人员Service
 * 
 * @author wyb
 */
@Service
@Transactional(readOnly = true)
public class BizEmployeeRegisteredService extends CrudService2<BizEmployeeRegisteredDao, BizEmployeeRegistered> {


	/**
	 * 导出在册人员excel
	 * @param bizEmployeeRegistered
	 * @param response
	 */
	public void exportExcel(BizEmployeeRegistered bizEmployeeRegistered, HttpServletResponse response) {

		HSSFWorkbook excel = queryExportExcel(bizEmployeeRegistered);
		ServletOutputStream out= null;//创建一个输出流对象
		try {
			response.setContentType("application/binary;charset=utf-8");
			//导出的文件名称为"工人/项目经理在册人数20171101至20171121.xls"
			String empTypeName = "工人、项目经理";
			if(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_2.equals(bizEmployeeRegistered.getEmpType())){
				empTypeName = "工人";
			}else if(EmployeeContantUtil.EMPLOYEE_EMP_TYPE_3.equals(bizEmployeeRegistered.getEmpType())){
				empTypeName = "项目经理";
			}
			String headerStr =new String((empTypeName+"在册人数"+ DateFormatUtils.format(bizEmployeeRegistered.getStartEntryDate(), "yyyy-MM-dd")+"至"+ DateFormatUtils.format(bizEmployeeRegistered.getEndEntryDate(), "yyyy-MM-dd")).getBytes("utf-8"), "ISO8859-1");//headerString为中文时转码
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");//filename是下载的xls的名
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

	/**
	 * 导出在册人员-查询数据
	 * @param bizEmployeeRegistered
	 * @return
	 */
	public HSSFWorkbook queryExportExcel(BizEmployeeRegistered bizEmployeeRegistered) {

		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet("在册人数");// 创建一个Excel的Sheet

		//单元格样式--标题
		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL);//字体颜色
		font.setFontName("黑体");//字体
		font.setFontHeightInPoints((short)10);//字体高度
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//宽度
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
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);

		//标题---订单信息
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(30);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue(new HSSFRichTextString("在册人数"));
		for(int i=0;i<3;i++){
			HSSFCell cella = rowTitle.createCell(i+1);
			cella.setCellStyle(columnHeadStyle);
		}
		//合并单元格--开始行，结束行，开始列，结束列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));//

		//标题
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

				//门店
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(employeeRegistered.getStoreIdName())){
					cell0.setCellValue(employeeRegistered.getStoreIdName());
				}

				//月份
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(employeeRegistered.getMonth())){
					cell1.setCellValue(employeeRegistered.getMonth());
				}

				//当月录入人数
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if(null!=employeeRegistered.getEmployeeEntriesCount()){
					cell2.setCellValue(employeeRegistered.getEmployeeEntriesCount());
				}

				//当月在册人数
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