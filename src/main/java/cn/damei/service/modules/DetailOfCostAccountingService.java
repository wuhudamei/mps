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
import org.springframework.ui.Model;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.DetailOfCostAccountingDao;
import cn.damei.entity.modules.DetailOfCostAccounting;
import cn.damei.web.modules.DetailOfCostAccountingController;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/** 
* @ClassName: DetailOfCostAccountingService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zkj  
* @date 2017年10月18日 上午11:45:06 
* @version V1.0 
*/
@Service
public class DetailOfCostAccountingService extends CrudService2<DetailOfCostAccountingDao, DetailOfCostAccounting>{

	
	
	/** 
	* @Description: 成本核算导出
	* @param @param detailOfCostAccounting
	* @param @param response
	* @author zkj 
	* @date 2017年10月19日 上午11:02:58 
	*/
	public void exportDetailExcel(DetailOfCostAccounting detailOfCostAccounting, HttpServletResponse response) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String fileName = "卞总报表--成本核算("+sf.format(new Date())+")";
		HSSFWorkbook excel = exportExcelDispatch(detailOfCostAccounting);
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

	/** 
	* @Description: 数据导出 
	* @param @param detailOfCostAccounting
	* @param @return
	* @author zkj 
	* @date 2017年10月19日 上午11:04:04 
	*/
	private HSSFWorkbook exportExcelDispatch(DetailOfCostAccounting detailOfCostAccounting) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String sheetName = "卞总报表--成本核算";
		
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
		sheet.setColumnWidth(19, 5000);
		sheet.setColumnWidth(20, 5000);
		sheet.setColumnWidth(21, 5000);
		sheet.setColumnWidth(22, 5000);
		sheet.setColumnWidth(23, 5000);
		sheet.setColumnWidth(24, 5000);
		sheet.setColumnWidth(25, 5000);
		sheet.setColumnWidth(26, 5000);
		sheet.setColumnWidth(27, 5000);
		sheet.setColumnWidth(28, 5000);
		sheet.setColumnWidth(29, 5000);
		sheet.setColumnWidth(30, 5000);
		sheet.setColumnWidth(31, 5000);
		
		
		//标题---订单信息
		HSSFRow rowTitle21 = sheet.createRow(0);
		rowTitle21.setHeightInPoints(30);
		HSSFCell headCell01 = rowTitle21.createCell(0);
		headCell01.setCellStyle(columnHeadStyle);
		headCell01.setCellValue(sheetName);
		int j = 31;
		for(int i=0;i<j;i++){
			HSSFCell cella = rowTitle21.createCell(i+1);
			cella.setCellStyle(columnHeadStyle);
		}
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 31));
		
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));// 序号
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));//门店
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));//区域
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));//工程模式
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));//订单编号
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));//房屋类型
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));//合同面积
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 7, 7));//计划竣工
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));//实际竣工
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 9, 9));//项目经理
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 10,10));//客户信息
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 11,18));//辅材
		
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 19,19));//沙子水泥
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 20,28));//人工成本
		
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 29,29));//项目经理提成
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 30,30));//合同金额
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 31,31));//订单状态
		

		HSSFRow rowTitle2 = sheet.createRow(1);
		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(columnHeadStyle);
		headCell0.setCellValue("序号");
		
		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(columnHeadStyle);
		headCell1.setCellValue("门店");
		
		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(columnHeadStyle);
		headCell2.setCellValue("区域");
		
		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(columnHeadStyle);
		headCell3.setCellValue("工程模式");
		
		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(columnHeadStyle);
		headCell4.setCellValue("订单编号");
		
		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(columnHeadStyle);
		headCell5.setCellValue("房屋类型");
		
		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(columnHeadStyle);
		headCell6.setCellValue("合同面积㎡");
		
		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(columnHeadStyle);
		headCell7.setCellValue("计划竣工");
		
		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(columnHeadStyle);
		headCell8.setCellValue("实际竣工");
		
		
		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(columnHeadStyle);
		headCell9.setCellValue("项目经理");
		
		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(columnHeadStyle);
		headCell10.setCellValue("客户信息");
		
		HSSFCell headCell11 = rowTitle2.createCell(11);
		headCell11.setCellStyle(columnHeadStyle);
		headCell11.setCellValue("辅材成本（元）");
		
		HSSFCell headCell12 = rowTitle2.createCell(12);
		headCell12.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell13 = rowTitle2.createCell(13);
		headCell13.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell14 = rowTitle2.createCell(14);
		headCell14.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell15 = rowTitle2.createCell(15);
		headCell15.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell16 = rowTitle2.createCell(16);
		headCell16.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell17 = rowTitle2.createCell(17);
		headCell17.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell18 = rowTitle2.createCell(18);
		headCell18.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell19 = rowTitle2.createCell(19);
		headCell19.setCellStyle(columnHeadStyle);
		headCell19.setCellValue("沙子水泥（元）");
		
		HSSFCell headCell20 = rowTitle2.createCell(20);
		headCell20.setCellStyle(columnHeadStyle);
		headCell20.setCellValue("人工成本（元）");
		
		HSSFCell headCell21 = rowTitle2.createCell(21);
		headCell21.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell22 = rowTitle2.createCell(22);
		headCell22.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell23 = rowTitle2.createCell(23);
		headCell23.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell24 = rowTitle2.createCell(24);
		headCell24.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell25 = rowTitle2.createCell(25);
		headCell25.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell26 = rowTitle2.createCell(26);
		headCell26.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell27 = rowTitle2.createCell(27);
		headCell27.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell28 = rowTitle2.createCell(28);
		headCell28.setCellStyle(columnHeadStyle);
		
		
		HSSFCell headCell29 = rowTitle2.createCell(29);
		headCell29.setCellStyle(columnHeadStyle);
		headCell29.setCellValue("项目经理提成（元）");
		
		
		HSSFCell headCell30 = rowTitle2.createCell(30);
		headCell30.setCellStyle(columnHeadStyle);
		headCell30.setCellValue("合同金额（元）");
		
		HSSFCell headCell31 = rowTitle2.createCell(31);
		headCell31.setCellStyle(columnHeadStyle);
		headCell31.setCellValue("订单状态");
		
		HSSFRow rowTitle3 = sheet.createRow(2);
		
		for(int i=0;i<j;i++){
			HSSFCell cella = rowTitle3.createCell(i);
			cella.setCellStyle(columnHeadStyle);
			if(i==11){
				cella.setCellValue("水电类");
			}
			if(i==12){
				cella.setCellValue("拆除类");
			}
			if(i==13){
				cella.setCellValue("营销类");
			}	
			if(i==14){
				cella.setCellValue("特殊类");
			}	
			if(i==15){
				cella.setCellValue("瓦工类");
			}	
			if(i==16){
				cella.setCellValue("木工类");
			}	
			if(i==17){
				cella.setCellValue("油工类");
			}
			if(i==18){
				cella.setCellValue("合计");
			}
			
			if(i==20){
				cella.setCellValue("水电类");
			}
			if(i==21){
				cella.setCellValue("拆除类");
			}
			if(i==22){
				cella.setCellValue("营销类");
			}	
			if(i==23){
				cella.setCellValue("特殊类");
			}	
			if(i==24){
				cella.setCellValue("瓦工类");
			}	
			if(i==25){
				cella.setCellValue("木工类");
			}	
			if(i==26){
				cella.setCellValue("油工类");
			}
			if(i==27){
				cella.setCellValue("安装类");
			}
			if(i==28){
				cella.setCellValue("合计");
			}	
			
		}
		
		List<DetailOfCostAccounting> list = dao.findList(detailOfCostAccounting);
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				DetailOfCostAccounting dosa = list.get(i);
				HSSFRow row = sheet.createRow(i+3);
				
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(i+1);
				
				
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				
				if (StringUtils.isNotBlank(dosa.getStoreName() ) ) {
					cell1.setCellValue(dosa.getStoreName() );
				}
				
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(dosa.getProjectModeName() ) ) {
					cell2.setCellValue(dosa.getProjectModeName() );
				}
				
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(dosa.getDepartmentName())) {
					cell3.setCellValue(dosa.getDepartmentName());
				}
				
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(dosa.getOrderNumber())) {
					cell4.setCellValue(dosa.getOrderNumber());
				}
				
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(dosa.getBuildName())) {
					cell5.setCellValue(dosa.getBuildName());
				}
				
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(dosa.getContractArea())) {
					cell6.setCellValue(dosa.getContractArea());
				}
				
				HSSFCell cell8 = row.createCell(7);
				cell8.setCellStyle(columnStyle);
				if (dosa.getContractStartDate() != null) {
					cell8.setCellValue(sf.format(dosa.getContractStartDate()));
				}
				
				HSSFCell cell9 = row.createCell(8);
				cell9.setCellStyle(columnStyle);
				if (dosa.getContractEndDate() != null) {
					cell9.setCellValue(sf.format(dosa.getContractEndDate()));
				}
				
				HSSFCell cell10 = row.createCell(9);
				cell10.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(dosa.getItemManager())) {
					cell10.setCellValue(dosa.getItemManager());
				}
						
				
				
				HSSFCell cell11 = row.createCell(10);
				cell11.setCellStyle(columnStyle);
				if (StringUtils.isNotBlank(dosa.getCustomerName())) {
					cell11.setCellValue(dosa.getCustomerName());
				}
				
				HSSFCell cell20 = row.createCell(11);
				cell20.setCellStyle(columnStyle);
				cell20.setCellValue(dosa.getFuliaoshuidian() );
				
				HSSFCell cell21 = row.createCell(12);
				cell21.setCellStyle(columnStyle);
				cell21.setCellValue(dosa.getFuliaochaichu() );
				
				HSSFCell cell22 = row.createCell(13);
				cell22.setCellStyle(columnStyle);
				cell22.setCellValue(dosa.getFuliaoyingxiao() );
				
				HSSFCell cell23 = row.createCell(14);
				cell23.setCellStyle(columnStyle);
				cell23.setCellValue(dosa.getFuliaoteshu() );
				
				HSSFCell cell24 = row.createCell(15);
				cell24.setCellStyle(columnStyle);
				cell24.setCellValue(dosa.getFuliaowagong() );
				
				HSSFCell cell25 = row.createCell(16);
				cell25.setCellStyle(columnStyle);
				cell25.setCellValue(dosa.getFuliaomugong() );
				
				HSSFCell cell26 = row.createCell(17);
				cell26.setCellStyle(columnStyle);
				cell26.setCellValue(dosa.getFuliaoyougong() );
				
				HSSFCell cell27 = row.createCell(18);
				cell27.setCellStyle(columnStyle);
				cell27.setCellValue(dosa.getFuliaoheji() );
				
				HSSFCell cell28 = row.createCell(19);
				cell28.setCellStyle(columnStyle);
				cell28.setCellValue(dosa.getSandCement() );
				
				
				HSSFCell cell12 = row.createCell(20);
				cell12.setCellStyle(columnStyle);
				cell12.setCellValue(dosa.getShuidian());
				
				HSSFCell cell121 = row.createCell(21);
				cell121.setCellStyle(columnStyle);
				cell121.setCellValue(dosa.getChaichu() );
				
				
				HSSFCell cell13 = row.createCell(22);
				cell13.setCellStyle(columnStyle);
				cell13.setCellValue(dosa.getYingxiao() );
				
				
				HSSFCell cell14 = row.createCell(23);
				cell14.setCellStyle(columnStyle);
				cell14.setCellValue(dosa.getTeshu() );
				
				HSSFCell cell15 = row.createCell(24);
				cell15.setCellStyle(columnStyle);
				cell15.setCellValue(dosa.getWagong() );
					
				HSSFCell cell16 = row.createCell(25);
				cell16.setCellStyle(columnStyle);
				cell16.setCellValue(dosa.getMugong() );
				
				HSSFCell cell17 = row.createCell(26);
				cell17.setCellStyle(columnStyle);
				cell17.setCellValue(dosa.getYougong());
				
				HSSFCell cell18 = row.createCell(27);
				cell18.setCellStyle(columnStyle);
				cell18.setCellValue(dosa.getAnzhuang() );
				
				HSSFCell cell19 = row.createCell(28);
				cell19.setCellStyle(columnStyle);
				cell19.setCellValue(dosa.getRengongheji() );
				
				
				HSSFCell cell29 = row.createCell(29);
				cell29.setCellStyle(columnStyle);
				cell29.setCellValue(dosa.getManagerAmount() );
				
				
				HSSFCell cell30 = row.createCell(30);
				cell30.setCellStyle(columnStyle);
				cell30.setCellValue(dosa.getContractAmount() );
				
				
				HSSFCell cell31 = row.createCell(31);
				cell31.setCellStyle(columnStyle);
				cell31.setCellValue(dosa.getOrderStatusDescription() );
				
			}
		}
		return wb;
		
		
	}

	/** 
	* @Description: 门店和工程模式过滤
	* @param @param detailOfCostAccountingController
	* @param @param detailOfCostAccounting
	* @param @param mode
	* @author zkj 
	* @date 2017年10月19日 上午11:04:36 
	*/
	public void storeAndProjectMode(DetailOfCostAccountingController detailOfCostAccountingController, DetailOfCostAccounting detailOfCostAccounting, Model mode) {
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			detailOfCostAccounting.setStoreId(UserUtils.getUser().getStoreId());
		} else {
			// 门店是总部的查询所有部门信息
			if(detailOfCostAccounting.getStoreId() == null){
				detailOfCostAccounting.setStoreId(null);
			}
		}
		//过滤工程模式
		if (detailOfCostAccounting.getProjectMode() == null || "".equals(detailOfCostAccounting.getProjectMode())) {
			User user = UserUtils.getUser();
			if (null != user.getEmpId()) {
				BizEmployee2 be = detailOfCostAccountingController.bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				detailOfCostAccounting.setProjectMode(be.getProjectMode());
				mode.addAttribute("projectModeEnable", true);
			} else {
				detailOfCostAccounting.setProjectMode(null);
			}
		}
	}
}
