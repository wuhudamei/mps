package cn.damei.service.modules;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.mobile.Manager.SignDetail;
import cn.damei.dao.modules.OrderDemolitionBuildDaoPC;

/** 
* @ClassName: OrderDemolitionBuildService 
* @Description: 拆改交底 
* @author zkj  
* @date 2017年10月19日 下午1:59:41 
* @version V1.0 
*/
@Service
public class OrderDemolitionBuildServicePC extends CrudService2<OrderDemolitionBuildDaoPC, SignDetail>{

	
	
	public void exportDetailExcel(SignDetail signDetail, HttpServletResponse response) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String fileName = "拆改交底("+sf.format(new Date())+")";
		HSSFWorkbook excel = export(signDetail);
		//创建一个输出流对象
		ServletOutputStream out= null;
		try {
			response.setContentType("application/binary;charset=utf-8");
			//headerString为中文时转码
			String headerStr =new String(fileName.getBytes("utf-8"), "ISO8859-1");
			//filename是下载的xls的名称
			response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
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
	

	public HSSFWorkbook export(SignDetail signDetail) {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String sheetName = "拆改交底";
		
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
		sheet.setColumnWidth(11, 3000);
		
		
		//标题---订单信息
		HSSFRow rowTitle21 = sheet.createRow(0);
		rowTitle21.setHeightInPoints(30);
		HSSFCell headCell01 = rowTitle21.createCell(0);
		headCell01.setCellStyle(columnHeadStyle);
		headCell01.setCellValue(sheetName);
		int j = 10;
		for(int i=0;i<j;i++){
			HSSFCell cella = rowTitle21.createCell(i+1);
			cella.setCellStyle(columnHeadStyle);
		}
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 0));// 序号
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 1));//门店
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 2));//区域
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 3));//工程模式
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 4));//订单编号
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 5));//项目经理
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 6));//顾客信息
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 7));//签到日期
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 8));//签到地址
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 9));//交底日期
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 10,10));//延期天数
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 11,11));//误差（米）
		
		

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
		headCell4.setCellValue("订单编号");
		
		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(columnHeadStyle);
		headCell5.setCellValue("项目经理");
		
		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(columnHeadStyle);
		headCell6.setCellValue("顾客信息");
		
		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(columnHeadStyle);
		headCell7.setCellValue("签到日期");
		
		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(columnHeadStyle);
		headCell8.setCellValue("签到地址");
		
		
		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(columnHeadStyle);
		headCell9.setCellValue("交底日期");
		
		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(columnHeadStyle);
		headCell10.setCellValue("延期天数");
		
		HSSFCell headCell11 = rowTitle2.createCell(11);
		headCell11.setCellStyle(columnHeadStyle);
		headCell11.setCellValue("误差（米）");
		
		
		List<SignDetail> list = dao.findList(signDetail);
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				SignDetail sd = list.get(i);
				HSSFRow row = sheet.createRow(i+2);
				
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(i+1);
				
				
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				
				if (StringUtils.isNotBlank(sd.getStoreName() ) ) {
					cell1.setCellValue(sd.getStoreName() );
				}
				
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(sd.getProjectModeName() ) ) {
					cell2.setCellValue(sd.getProjectModeName() );
				}
				
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(sd.getEnginDepartName())) {
					cell3.setCellValue(sd.getEnginDepartName());
				}
				
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(sd.getOrderNumber())) {
					cell4.setCellValue(sd.getOrderNumber());
				}
				
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(sd.getManagerName())) {
					cell5.setCellValue(sd.getManagerName());
				}
				
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(sd.getCustomerAddress())) {
					cell6.setCellValue(sd.getCustomerAddress());
				}
				
				HSSFCell cell8 = row.createCell(7);
				cell8.setCellStyle(columnStyle);
				if (sd.getSignDate() != null) {
					cell8.setCellValue(sf.format(sd.getSignDate()));
				}
				
				HSSFCell cell9 = row.createCell(8);
				cell9.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(sd.getSignAddress())) {
					cell9.setCellValue((sd.getSignAddress()));
				}
				
				HSSFCell cell10 = row.createCell(9);
				cell10.setCellStyle(columnStyle);
				if (sd.getDemolitionBuildDate() != null) {
					cell10.setCellValue(sf.format(sd.getDemolitionBuildDate()));
				}
				
				HSSFCell cell11 = row.createCell(10);
				cell11.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(sd.getDelayDays())) {
					cell11.setCellValue(sd.getDelayDays());
				}
				
				HSSFCell cell20 = row.createCell(11);
				cell20.setCellStyle(columnStyle);
				cell20.setCellValue(sd.getSignDistance() );
			}
		}
		return wb;
		
	}
}
