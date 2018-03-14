package cn.damei.service.modules;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessProblemConstantUtil;
import cn.damei.common.constantUtils.BusinessUrgeConstantUtil;
import cn.damei.common.constantUtils.PurchaseConstantUtil;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizPrePmSettleFin;
import cn.damei.dao.modules.OrderFullActionDao;
import cn.damei.entity.modules.OrderFullAction;

@Service
@Transactional(readOnly = true)
public class ExportExcelOrderFullActionService{


	@Autowired
	private OrderFullActionDao orderFullActionDao;
	@Autowired
	private OrderFullActionService orderFullActionService;
	
	/**
	 * 导出
	 * @param orderId
	 * @return
	 */
	public HSSFWorkbook exportExcel(String orderId) {
		
		//一、创建excel文件
		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		
		//二、创建excel的sheet
		HSSFSheet sheet1 = wb.createSheet("1.订单基本信息");// 创建一个Excel的Sheet
		HSSFSheet sheet2 = wb.createSheet("2.接单派单做预算");// 创建一个Excel的Sheet
		HSSFSheet sheet3 = wb.createSheet("3.交底开工");// 创建一个Excel的Sheet
		HSSFSheet sheet4 = wb.createSheet("4.基装阶段：材料部分");// 创建一个Excel的Sheet
		HSSFSheet sheet5 = wb.createSheet("5.基装阶段：质检");// 创建一个Excel的Sheet
		HSSFSheet sheet6 = wb.createSheet("6.基装阶段：变更");// 创建一个Excel的Sheet
		HSSFSheet sheet7 = wb.createSheet("7.基装阶段：款项");// 创建一个Excel的Sheet
		HSSFSheet sheet8 = wb.createSheet("8.安装阶段");// 创建一个Excel的Sheet
		
		//三、单元格样式
		//1.设置字体
		HSSFFont font = wb.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL);//字体颜色
		font.setFontName("黑体");//字体
		font.setFontHeightInPoints((short)10);//字体高度
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//宽度
		//2.单元格样式--标题
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
		//3.单元格样式
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
				
				
		//四、数据内容
		
		
		/**
		 * 1.订单基本信息
		 */
		
		
		//1-1.查询订单基本信息  数据
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderId",orderId);
		Map<String, Object> orderDetail = orderFullActionDao.orderDetail(map);
		//1-2.设置单元格宽度
		sheet1.setColumnWidth(0, 6000);
		sheet1.setColumnWidth(1, 6000);
		sheet1.setColumnWidth(2, 6000);
		sheet1.setColumnWidth(3, 6000);
		//1-3.大标题
		HSSFRow orderRow0 = sheet1.createRow(0);
		orderRow0.setHeightInPoints(30);
		HSSFCell orderCell00 = orderRow0.createCell(0);
		orderCell00.setCellStyle(columnHeadStyle);
		orderCell00.setCellValue(new HSSFRichTextString("订单基本信息"));
		for(int i=0;i<3;i++){
			HSSFCell orderCell = orderRow0.createCell(i+1);
			orderCell.setCellStyle(columnHeadStyle);
		}
		//1-4.小标题及数据内容
		//1-4-1.第一行 4列
		HSSFRow orderRow1 = sheet1.createRow(1);
		HSSFCell orderCell10 = orderRow1.createCell(0);
		orderCell10.setCellStyle(columnStyle);
		orderCell10.setCellValue("门店:");
		
		HSSFCell orderCell11 = orderRow1.createCell(1);
		orderCell11.setCellStyle(columnStyle);
		String storeName  = (String) orderDetail.get("storeName");
		if(StringUtils.isNoneBlank(storeName)){
			orderCell11.setCellValue(storeName);
		}
		HSSFCell orderCell12 = orderRow1.createCell(2);
		orderCell12.setCellStyle(columnStyle);
		orderCell12.setCellValue("订单编号:");
		
		HSSFCell orderCell13 = orderRow1.createCell(3);
		orderCell13.setCellStyle(columnStyle);
		String orderNumber  = (String) orderDetail.get("orderNumber");
		if(StringUtils.isNoneBlank(orderNumber)){
			orderCell13.setCellValue(orderNumber);
		}
		
		//1-4-2.第二行 4列
		HSSFRow orderRow2 = sheet1.createRow(2);
		HSSFCell orderCell20 = orderRow2.createCell(0);
		orderCell20.setCellStyle(columnStyle);
		orderCell20.setCellValue("客户姓名:");
		
		HSSFCell orderCell21 = orderRow2.createCell(1);
		orderCell21.setCellStyle(columnStyle);
		String customerName  = (String) orderDetail.get("customerName");
		if(StringUtils.isNoneBlank(customerName)){
			orderCell21.setCellValue(customerName);
		}
		
		HSSFCell orderCell22 = orderRow2.createCell(2);
		orderCell22.setCellStyle(columnStyle);
		orderCell22.setCellValue("客户手机号:");
		
		HSSFCell orderCell23 = orderRow2.createCell(3);
		orderCell23.setCellStyle(columnStyle);
		String customerPhone  = (String) orderDetail.get("customerPhone");
		if(StringUtils.isNoneBlank(customerPhone)){
			orderCell23.setCellValue(customerPhone);
		}
		
		//1-4-3.第三行 4列
		HSSFRow orderRow3 = sheet1.createRow(3);
		HSSFCell orderCell30 = orderRow3.createCell(0);
		orderCell30.setCellStyle(columnStyle);
		orderCell30.setCellValue("客户地址:");
		
		HSSFCell orderCell31 = orderRow3.createCell(1);
		orderCell31.setCellStyle(columnStyle);
		String communityName  = (String) orderDetail.get("communityName");
		String buildNumber  = (String) orderDetail.get("buildNumber");
		String buildUnit  = (String) orderDetail.get("buildUnit");
		String buildRoom  = (String) orderDetail.get("buildRoom");
		if(StringUtils.isNoneBlank(communityName)||StringUtils.isNoneBlank(buildNumber)||StringUtils.isNoneBlank(buildUnit)||StringUtils.isNoneBlank(buildRoom)){
			orderCell31.setCellValue(communityName+"-"+buildNumber+"-"+buildUnit+"-"+buildRoom);
		}
		
		HSSFCell orderCell32 = orderRow3.createCell(2);
		orderCell32.setCellStyle(columnStyle);
		
		HSSFCell orderCell33 = orderRow3.createCell(3);
		orderCell33.setCellStyle(columnStyle);
		
		//1-4-4.第四行 4列
		HSSFRow orderRow4 = sheet1.createRow(4);
		HSSFCell orderCell40 = orderRow4.createCell(0);
		orderCell40.setCellStyle(columnStyle);
		orderCell40.setCellValue("项目经理:");
		
		HSSFCell orderCell41 = orderRow4.createCell(1);
		orderCell41.setCellStyle(columnStyle);
		String itemManagerName  = (String) orderDetail.get("itemManagerName");
		if(StringUtils.isNoneBlank(itemManagerName)){
			orderCell41.setCellValue(itemManagerName);
		}
		
		HSSFCell orderCell42 = orderRow4.createCell(2);
		orderCell42.setCellStyle(columnStyle);
		orderCell42.setCellValue("项目经理手机号:");
		
		HSSFCell orderCell43 = orderRow4.createCell(3);
		orderCell43.setCellStyle(columnStyle);
		String itemManagerPhone  = (String) orderDetail.get("itemManagerPhone");
		if(StringUtils.isNoneBlank(itemManagerPhone)){
			orderCell43.setCellValue(itemManagerPhone);
		}
		//1-4-5.第五行 4列
		HSSFRow orderRow5 = sheet1.createRow(5);
		HSSFCell orderCell50 = orderRow5.createCell(0);
		orderCell50.setCellStyle(columnStyle);
		orderCell50.setCellValue("质检员:");
		
		HSSFCell orderCell51 = orderRow5.createCell(1);
		orderCell51.setCellStyle(columnStyle);
		String orderInspectorName  = (String) orderDetail.get("orderInspectorName");
		if(StringUtils.isNoneBlank(orderInspectorName)){
			orderCell51.setCellValue(orderInspectorName);
		}
		
		HSSFCell orderCell52 = orderRow5.createCell(2);
		orderCell52.setCellStyle(columnStyle);
		orderCell52.setCellValue("质检员手机号:");
		
		HSSFCell orderCell53 = orderRow5.createCell(3);
		orderCell53.setCellStyle(columnStyle);
		String orderInspectorPhone  = (String) orderDetail.get("orderInspectorPhone");
		if(StringUtils.isNoneBlank(orderInspectorPhone)){
			orderCell53.setCellValue(orderInspectorPhone);
		}
		//1-4-6.第六行 4列
		HSSFRow orderRow6 = sheet1.createRow(6);
		HSSFCell orderCell60 = orderRow6.createCell(0);
		orderCell60.setCellStyle(columnStyle);
		orderCell60.setCellValue("设计师:");
		
		HSSFCell orderCell61 = orderRow6.createCell(1);
		orderCell61.setCellStyle(columnStyle);
		String designerName  = (String) orderDetail.get("designerName");
		if(StringUtils.isNoneBlank(designerName)){
			orderCell61.setCellValue(designerName);
		}
		
		HSSFCell orderCell62 = orderRow6.createCell(2);
		orderCell62.setCellStyle(columnStyle);
		orderCell62.setCellValue("设计师手机号:");
		
		HSSFCell orderCell63 = orderRow6.createCell(3);
		orderCell63.setCellStyle(columnStyle);
		String designerPhone  = (String) orderDetail.get("designerPhone");
		if(StringUtils.isNoneBlank(designerPhone)){
			orderCell63.setCellValue(designerPhone);
		}
		//1-4-7.第七行 4列
		HSSFRow orderRow7 = sheet1.createRow(7);
		HSSFCell orderCell70 = orderRow7.createCell(0);
		orderCell70.setCellStyle(columnStyle);
		orderCell70.setCellValue("客服经理:");
		
		HSSFCell orderCell71 = orderRow7.createCell(1);
		orderCell71.setCellStyle(columnStyle);
		String serviceName  = (String) orderDetail.get("serviceName");
		if(StringUtils.isNoneBlank(serviceName)){
			orderCell71.setCellValue(serviceName);
		}
		
		HSSFCell orderCell72 = orderRow7.createCell(2);
		orderCell72.setCellStyle(columnStyle);
		orderCell72.setCellValue("客服经理手机号:");
		
		HSSFCell orderCell73 = orderRow7.createCell(3);
		orderCell73.setCellStyle(columnStyle);
		String servicePhone  = (String) orderDetail.get("servicePhone");
		if(StringUtils.isNoneBlank(servicePhone)){
			orderCell73.setCellValue(servicePhone);
		}
		//1-5.合并单元格
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));// 
		sheet1.addMergedRegion(new CellRangeAddress(3, 3, 1, 3));// 
		
		/**
		 * 2.接单派单做预算
		 */
		
		
		//2-1.查询订单交底开工信息  数据
		OrderFullAction orderGet = orderFullActionService.getBudget(orderId);
		//2-2.设置单元格宽度
		sheet2.setColumnWidth(0, 6000);
		sheet2.setColumnWidth(1, 6000);
		sheet2.setColumnWidth(2, 6000);
		sheet2.setColumnWidth(3, 6000);
		//2-3.大标题
		HSSFRow orderGetRow0 = sheet2.createRow(0);
		orderGetRow0.setHeightInPoints(30);
		HSSFCell orderGetCell00 = orderGetRow0.createCell(0);
		orderGetCell00.setCellStyle(columnHeadStyle);
		orderGetCell00.setCellValue(new HSSFRichTextString("接单派单做预算"));
		for(int i=0;i<3;i++){
			HSSFCell orderGetCell = orderGetRow0.createCell(i+1);
			orderGetCell.setCellStyle(columnHeadStyle);
		}
		//2-4.小标题及数据内容
		//2-4-1.第一行 4列
		HSSFRow orderGetRow1 = sheet2.createRow(1);
		HSSFCell orderGetCell10 = orderGetRow1.createCell(0);
		orderGetCell10.setCellStyle(columnStyle);
		orderGetCell10.setCellValue("录入系统日期:");
		
		HSSFCell orderGetCell11 = orderGetRow1.createCell(1);
		orderGetCell11.setCellStyle(columnStyle);
		if(null!=orderGet && null!=orderGet.getEnteringDate()){
			orderGetCell11.setCellValue(DateFormatUtils.format(orderGet.getEnteringDate(), "yyyy-MM-dd HH:mm:ss"));
		}
		HSSFCell orderGetCell12 = orderGetRow1.createCell(2);
		orderGetCell12.setCellStyle(columnStyle);
		orderGetCell12.setCellValue("操作人:");
		
		HSSFCell orderGetCell13 = orderGetRow1.createCell(3);
		orderGetCell13.setCellStyle(columnStyle);
		if(StringUtils.isNoneBlank(orderGet.getKeyboarder())){
			orderGetCell13.setCellValue(orderGet.getKeyboarder());
		}
		
		//2-4-2.第二行 4列
		HSSFRow orderGetRow2 = sheet2.createRow(2);
		HSSFCell orderGetCell20 = orderGetRow2.createCell(0);
		orderGetCell20.setCellStyle(columnStyle);
		orderGetCell20.setCellValue("接单日期:");
		
		HSSFCell orderGetCell21 = orderGetRow2.createCell(1);
		orderGetCell21.setCellStyle(columnStyle);
		if(null!=orderGet.getReceivingDate()){
			orderGetCell21.setCellValue(DateFormatUtils.format(orderGet.getReceivingDate(), "yyyy-MM-dd HH:mm:ss"));
		}
		
		HSSFCell orderGetCell22 = orderGetRow2.createCell(2);
		orderGetCell22.setCellStyle(columnStyle);
		
		HSSFCell orderGetCell23 = orderGetRow2.createCell(3);
		orderGetCell23.setCellStyle(columnStyle);
		
		//2-4-3.第三行 4列
		HSSFRow orderGetRow3 = sheet2.createRow(3);
		HSSFCell orderGetCell30 = orderGetRow3.createCell(0);
		orderGetCell30.setCellStyle(columnStyle);
		orderGetCell30.setCellValue("生成任务包日期:");
		
		HSSFCell orderGetCell31 = orderGetRow3.createCell(1);
		orderGetCell31.setCellStyle(columnStyle);
		if(null!=orderGet.getPackageDate()){
			orderGetCell31.setCellValue(DateFormatUtils.format(orderGet.getPackageDate(), "yyyy-MM-dd HH:mm:ss"));
		}
		
		HSSFCell orderGetCell32 = orderGetRow3.createCell(2);
		orderGetCell32.setCellStyle(columnStyle);
		orderGetCell32.setCellValue("操作人:");
		
		HSSFCell orderGetCell33 = orderGetRow3.createCell(3);
		orderGetCell33.setCellStyle(columnStyle);
		if(StringUtils.isNoneBlank(orderGet.getPackageBy())){
			orderGetCell33.setCellValue(orderGet.getPackageBy());
		}
		
		//2-4-4.第四行 4列
		HSSFRow orderGetRow4 = sheet2.createRow(4);
		HSSFCell orderGetCell40 = orderGetRow4.createCell(0);
		orderGetCell40.setCellStyle(columnStyle);
		orderGetCell40.setCellValue("分配项目经理时间:");
		
		HSSFCell orderGetCell41 = orderGetRow4.createCell(1);
		orderGetCell41.setCellStyle(columnStyle);
		if(null!=orderGet.getAssignedManagerDate()){
			orderGetCell41.setCellValue(DateFormatUtils.format(orderGet.getAssignedManagerDate(), "yyyy-MM-dd HH:mm:ss"));
		}
		
		HSSFCell orderGetCell42 = orderGetRow4.createCell(2);
		orderGetCell42.setCellStyle(columnStyle);
		orderGetCell42.setCellValue("操作人:");
		
		HSSFCell orderGetCell43 = orderGetRow4.createCell(3);
		orderGetCell43.setCellStyle(columnStyle);
		if(StringUtils.isNoneBlank(orderGet.getAssignedManagerBy())){
			orderGetCell43.setCellValue(orderGet.getAssignedManagerBy());
		}
		
		//2-4-5.第五行 4列
		HSSFRow orderGetRow5 = sheet2.createRow(5);
		HSSFCell orderGetCell50 = orderGetRow5.createCell(0);
		orderGetCell50.setCellStyle(columnStyle);
		orderGetCell50.setCellValue("分配质检员时间:");
		
		HSSFCell orderGetCell51 = orderGetRow5.createCell(1);
		orderGetCell51.setCellStyle(columnStyle);
		if(null!=orderGet.getAssignedInspectorDate()){
			orderGetCell41.setCellValue(DateFormatUtils.format(orderGet.getAssignedInspectorDate(), "yyyy-MM-dd HH:mm:ss"));
		}
		
		HSSFCell orderGetCell52 = orderGetRow5.createCell(2);
		orderGetCell52.setCellStyle(columnStyle);
		orderGetCell52.setCellValue("操作人:");
		
		HSSFCell orderGetCell53 = orderGetRow5.createCell(3);
		orderGetCell53.setCellStyle(columnStyle);
		if(StringUtils.isNoneBlank(orderGet.getAssignedInspectorBy())){
			orderGetCell53.setCellValue(orderGet.getAssignedInspectorBy());
		}
		
		//2-5.合并单元格
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet2.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));// 
		
		/**
		 * 3.交底开工
		 */
		
		
		//3-1.查询订单交底开工信息  数据
		Map<String,Object> map3 = new HashMap<String,Object>();
		map3.put("orderId",orderId);
		Map<String, Object> disclosureStarts = orderFullActionDao.disclosureStartsQuery(map3);
		//3-2.设置单元格宽度
		sheet3.setColumnWidth(0, 6000);
		sheet3.setColumnWidth(1, 6000);
		sheet3.setColumnWidth(2, 6000);
		sheet3.setColumnWidth(3, 6000);
		//3-3.大标题
		HSSFRow disclosureStartsRow0 = sheet3.createRow(0);
		disclosureStartsRow0.setHeightInPoints(30);
		HSSFCell disclosureStartsCell00 = disclosureStartsRow0.createCell(0);
		disclosureStartsCell00.setCellStyle(columnHeadStyle);
		disclosureStartsCell00.setCellValue(new HSSFRichTextString("交底开工"));
		for(int i=0;i<3;i++){
			HSSFCell disclosureStartsCell = disclosureStartsRow0.createCell(i+1);
			disclosureStartsCell.setCellStyle(columnHeadStyle);
		}
		//3-4.小标题及数据内容
		//3-4-1.第一行 4列
		HSSFRow disclosureStartsRow1 = sheet3.createRow(1);
		HSSFCell disclosureStartsCell10 = disclosureStartsRow1.createCell(0);
		disclosureStartsCell10.setCellStyle(columnStyle);
		disclosureStartsCell10.setCellValue("交底日期:");
		
		HSSFCell disclosureStartsCell11 = disclosureStartsRow1.createCell(1);
		disclosureStartsCell11.setCellStyle(columnStyle);
		Date discloseDate  = (Date) disclosureStarts.get("discloseDate");
		if(null!=discloseDate){
			disclosureStartsCell11.setCellValue(DateFormatUtils.format(discloseDate, "yyyy-MM-dd"));
		}
		HSSFCell disclosureStartsCell12 = disclosureStartsRow1.createCell(2);
		disclosureStartsCell12.setCellStyle(columnStyle);
		disclosureStartsCell12.setCellValue("实际开工日期:");
		
		HSSFCell disclosureStartsCell13 = disclosureStartsRow1.createCell(3);
		disclosureStartsCell13.setCellStyle(columnStyle);
		Date actualStartDate  = (Date) disclosureStarts.get("actualStartDate");
		if(null!=actualStartDate){
			disclosureStartsCell13.setCellValue(DateFormatUtils.format(actualStartDate, "yyyy-MM-dd"));
		}
		
		//3-4-2.第二行 4列
		HSSFRow disclosureStartsRow2 = sheet3.createRow(2);
		HSSFCell disclosureStartsCell20 = disclosureStartsRow2.createCell(0);
		disclosureStartsCell20.setCellStyle(columnStyle);
		disclosureStartsCell20.setCellValue("客户原因延期天数:");
		
		HSSFCell disclosureStartsCell21 = disclosureStartsRow2.createCell(1);
		disclosureStartsCell21.setCellStyle(columnStyle);
		Integer selfDecorateDelayDays  = (Integer ) disclosureStarts.get("selfDecorateDelayDays");
		if(null!=selfDecorateDelayDays){
			disclosureStartsCell21.setCellValue(selfDecorateDelayDays);
		}
		
		HSSFCell disclosureStartsCell22 = disclosureStartsRow2.createCell(2);
		disclosureStartsCell22.setCellStyle(columnStyle);
		disclosureStartsCell22.setCellValue("合同开工日期:");
		
		HSSFCell disclosureStartsCell23 = disclosureStartsRow2.createCell(3);
		disclosureStartsCell23.setCellStyle(columnStyle);
		Date contractStartDate  = (Date) disclosureStarts.get("contractStartDate");
		if(null!=contractStartDate){
			disclosureStartsCell23.setCellValue(DateFormatUtils.format(contractStartDate, "yyyy-MM-dd"));
		}
		
		//3-4-3.第三行 4列
		HSSFRow disclosureStartsRow3 = sheet3.createRow(3);
		HSSFCell disclosureStartsCell30 = disclosureStartsRow3.createCell(0);
		disclosureStartsCell30.setCellStyle(columnStyle);
		disclosureStartsCell30.setCellValue("客户是否签字:");
		
		HSSFCell disclosureStartsCell31 = disclosureStartsRow3.createCell(1);
		disclosureStartsCell31.setCellStyle(columnStyle);
		String isNeedSign  = (String) disclosureStarts.get("isNeedSign");
		if(null!=isNeedSign && isNeedSign.equals("1")){
			disclosureStartsCell31.setCellValue("是");
		}else if(null!=isNeedSign && isNeedSign.equals("0")){
			disclosureStartsCell31.setCellValue("否");
		}
		
		HSSFCell disclosureStartsCell32 = disclosureStartsRow3.createCell(2);
		disclosureStartsCell32.setCellStyle(columnStyle);
		
		HSSFCell disclosureStartsCell33 = disclosureStartsRow3.createCell(3);
		disclosureStartsCell33.setCellStyle(columnStyle);
		
		//3-5.合并单元格
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet3.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));// 
		
		
		/**
		 * 4.基装阶段：材料部分
		 */
		
		
		//4-1.查询订单材料采购单信息  数据
		Map<String,Object> map4 = new HashMap<String,Object>();
		//订单id
		map4.put("orderId",orderId);
		//采购单状态  字典表类型
		map4.put("purchaseStatus","purchase_auxiliary_status");
		//是否删除 标记
		map4.put("delFlag",PurchaseConstantUtil.PURCHASE_DEL_FLAG_NO_0);
		//催促信息  墙地砖催促
		map4.put("businesType",BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_2);
		//催促信息  催促
		map4.put("operateType",BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1);

		List<Map<String, Object>> purchaseList = orderFullActionDao.purchaseDetail(map4);
		//4-2.设置单元格宽度
		sheet4.setColumnWidth(0, 6000);
		sheet4.setColumnWidth(1, 6000);
		sheet4.setColumnWidth(2, 6000);
		sheet4.setColumnWidth(3, 6000);
		sheet4.setColumnWidth(4, 6000);
		sheet4.setColumnWidth(5, 6000);
		//4-3.大标题
		HSSFRow purchaseRow0 = sheet4.createRow(0);
		purchaseRow0.setHeightInPoints(30);
		HSSFCell purchaseCell00 = purchaseRow0.createCell(0);
		purchaseCell00.setCellStyle(columnHeadStyle);
		purchaseCell00.setCellValue(new HSSFRichTextString("基装阶段：材料部分"));
		for(int i=0;i<5;i++){
			HSSFCell purchaseCell = purchaseRow0.createCell(i+1);
			purchaseCell.setCellStyle(columnHeadStyle);
		}
		//4-4.小标题及数据内容
		//4-4-1-1.辅料--小标题--【辅料】
		HSSFRow purchaseMaterialRow1 = sheet4.createRow(1);
		purchaseMaterialRow1.setHeightInPoints(30);
		HSSFCell purchaseMaterialCell10 = purchaseMaterialRow1.createCell(0);
		purchaseMaterialCell10.setCellStyle(columnHeadStyle);
		purchaseMaterialCell10.setCellValue(new HSSFRichTextString("【辅料】"));
		for(int i=0;i<4;i++){
			HSSFCell purchaseMaterialCell = purchaseMaterialRow1.createCell(i+1);
			purchaseMaterialCell.setCellStyle(columnHeadStyle);
		}
		
		//4-4-1-2.辅料--小标题--标题
		
		HSSFRow purchaseMaterialRow2 = sheet4.createRow(2);
		HSSFCell purchaseMaterialCell20 = purchaseMaterialRow2.createCell(0);
		purchaseMaterialCell20.setCellStyle(columnHeadStyle);
		purchaseMaterialCell20.setCellValue("申请顺序");
		
		HSSFCell purchaseMaterialCell21 = purchaseMaterialRow2.createCell(1);
		purchaseMaterialCell21.setCellStyle(columnHeadStyle);
		purchaseMaterialCell21.setCellValue("当前状态");
		
		HSSFCell purchaseMaterialCell22 = purchaseMaterialRow2.createCell(2);
		purchaseMaterialCell22.setCellStyle(columnHeadStyle);
		purchaseMaterialCell22.setCellValue("提交申请时间");
		
		HSSFCell purchaseMaterialCell23 = purchaseMaterialRow2.createCell(3);
		purchaseMaterialCell23.setCellStyle(columnHeadStyle);
		purchaseMaterialCell23.setCellValue("期望到货日期");
		
		HSSFCell purchaseMaterialCell24 = purchaseMaterialRow2.createCell(4);
		purchaseMaterialCell24.setCellStyle(columnHeadStyle);
		purchaseMaterialCell24.setCellValue("收货日期");
		
		//4-4-1-3.数据内容
		int purchaseMaterialCount = 0;
		if(CollectionUtils.isNotEmpty(purchaseList)){
			for(int i=0;i<purchaseList.size();i++){
				
				Map<String, Object> purchase = purchaseList.get(i);
				
				String purchaseType  = (String) purchase.get("purchaseType");
				
				if(purchaseType.equals("1")){
					purchaseMaterialCount++;
					String purchaseStatusName  = (String) purchase.get("purchaseStatusName");
					String applyTime  = (String) purchase.get("applyTime");
					String applyReceiveTime  = (String) purchase.get("applyReceiveTime");
					String receiveDate  = (String) purchase.get("receiveDate");
					
					
					HSSFRow purchaseMaterialRow = sheet4.createRow(i+3);
					
					HSSFCell purchaseMaterialCell0 = purchaseMaterialRow.createCell(0);
					purchaseMaterialCell0.setCellStyle(columnStyle);
					purchaseMaterialCell0.setCellValue(purchaseMaterialCount);
					
					HSSFCell purchaseMaterialCell1 = purchaseMaterialRow.createCell(1);
					purchaseMaterialCell1.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(purchaseStatusName)){
						purchaseMaterialCell1.setCellValue(purchaseStatusName);
					}
					
					HSSFCell purchaseMaterialCell2 = purchaseMaterialRow.createCell(2);
					purchaseMaterialCell2.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(applyTime)){
						purchaseMaterialCell2.setCellValue(applyTime);
					}
					
					HSSFCell purchaseMaterialCell3 = purchaseMaterialRow.createCell(3);
					purchaseMaterialCell3.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(applyReceiveTime)){
						purchaseMaterialCell3.setCellValue(applyReceiveTime);
					}
					
					HSSFCell purchaseMaterialCell4 = purchaseMaterialRow.createCell(4);
					purchaseMaterialCell4.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(receiveDate)){
						purchaseMaterialCell4.setCellValue(receiveDate);
					}
					
				}
			}
		}
		
		
		//4-4-2-1.墙地砖--小标题--【墙地砖】
		HSSFRow purchaseWallAndFloorRow1 = sheet4.createRow(purchaseMaterialCount+4);
		purchaseWallAndFloorRow1.setHeightInPoints(30);
		HSSFCell purchaseWallAndFloorCell10 = purchaseWallAndFloorRow1.createCell(0);
		purchaseWallAndFloorCell10.setCellStyle(columnHeadStyle);
		purchaseWallAndFloorCell10.setCellValue(new HSSFRichTextString("【墙地砖】"));
		for(int i=0;i<5;i++){
			HSSFCell purchaseWallAndFloorCell = purchaseWallAndFloorRow1.createCell(i+1);
			purchaseWallAndFloorCell.setCellStyle(columnHeadStyle);
		}
		
		//4-4-2-2.墙地砖--小标题--标题
		
		HSSFRow purchaseWallAndFloorRow2 = sheet4.createRow(purchaseMaterialCount+5);
		HSSFCell purchaseWallAndFloorCell20 = purchaseWallAndFloorRow2.createCell(0);
		purchaseWallAndFloorCell20.setCellStyle(columnHeadStyle);
		purchaseWallAndFloorCell20.setCellValue("申请顺序");
		
		HSSFCell purchaseWallAndFloorCell21 = purchaseWallAndFloorRow2.createCell(1);
		purchaseWallAndFloorCell21.setCellStyle(columnHeadStyle);
		purchaseWallAndFloorCell21.setCellValue("当前状态");
		
		HSSFCell purchaseWallAndFloorCell22 = purchaseWallAndFloorRow2.createCell(2);
		purchaseWallAndFloorCell22.setCellStyle(columnHeadStyle);
		purchaseWallAndFloorCell22.setCellValue("提交申请时间");
		
		HSSFCell purchaseWallAndFloorCell23 = purchaseWallAndFloorRow2.createCell(3);
		purchaseWallAndFloorCell23.setCellStyle(columnHeadStyle);
		purchaseWallAndFloorCell23.setCellValue("期望到货日期");
		
		HSSFCell purchaseWallAndFloorCell24 = purchaseWallAndFloorRow2.createCell(4);
		purchaseWallAndFloorCell24.setCellStyle(columnHeadStyle);
		purchaseWallAndFloorCell24.setCellValue("收货日期");
		
		HSSFCell purchaseWallAndFloorCell25 = purchaseWallAndFloorRow2.createCell(5);
		purchaseWallAndFloorCell25.setCellStyle(columnHeadStyle);
		purchaseWallAndFloorCell25.setCellValue("催单次数");
		
		//4-4-2-3.数据内容
		int purchaseWallAndFloorCount = 0;
		if(CollectionUtils.isNotEmpty(purchaseList)){
			for(int i=0;i<purchaseList.size();i++){
				
				Map<String, Object> purchase = purchaseList.get(i);
				
				String purchaseType  = (String) purchase.get("purchaseType");
				
				if(purchaseType.equals("5")){
					purchaseWallAndFloorCount++;
					String purchaseStatusName  = (String) purchase.get("purchaseStatusName");
					String applyTime  = (String) purchase.get("applyTime");
					String applyReceiveTime  = (String) purchase.get("applyReceiveTime");
					String receiveDate  = (String) purchase.get("receiveDate");
					Long urgeCount  = (Long) purchase.get("urgeCount");
					
					
					HSSFRow purchaseWallAndFloorRow = sheet4.createRow(purchaseWallAndFloorCount+purchaseMaterialCount+5);
					
					HSSFCell purchaseWallAndFloorCell0 = purchaseWallAndFloorRow.createCell(0);
					purchaseWallAndFloorCell0.setCellStyle(columnStyle);
					purchaseWallAndFloorCell0.setCellValue(purchaseWallAndFloorCount);
					
					HSSFCell purchaseWallAndFloorCell1 = purchaseWallAndFloorRow.createCell(1);
					purchaseWallAndFloorCell1.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(purchaseStatusName)){
						purchaseWallAndFloorCell1.setCellValue(purchaseStatusName);
					}
					
					HSSFCell purchaseWallAndFloorCell2 = purchaseWallAndFloorRow.createCell(2);
					purchaseWallAndFloorCell2.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(applyTime)){
						purchaseWallAndFloorCell2.setCellValue(applyTime);
					}
					
					HSSFCell purchaseWallAndFloorCell3 = purchaseWallAndFloorRow.createCell(3);
					purchaseWallAndFloorCell3.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(applyReceiveTime)){
						purchaseWallAndFloorCell3.setCellValue(applyReceiveTime);
					}
					
					HSSFCell purchaseWallAndFloorCell4 = purchaseWallAndFloorRow.createCell(4);
					purchaseWallAndFloorCell4.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(receiveDate)){
						purchaseWallAndFloorCell4.setCellValue(receiveDate);
					}
					
					HSSFCell purchaseWallAndFloorCell5 = purchaseWallAndFloorRow.createCell(5);
					purchaseWallAndFloorCell5.setCellStyle(columnStyle);
					if(null!=urgeCount){
						purchaseWallAndFloorCell5.setCellValue(urgeCount);
					}
				}
			}
		}
		
		//4-4-3-1.开关面板--小标题--【开关面板】
		HSSFRow purchaseSwitchPanelRow1 = sheet4.createRow(purchaseWallAndFloorCount+purchaseMaterialCount+7);
		purchaseSwitchPanelRow1.setHeightInPoints(30);
		HSSFCell purchaseSwitchPanelCell10 = purchaseSwitchPanelRow1.createCell(0);
		purchaseSwitchPanelCell10.setCellStyle(columnHeadStyle);
		purchaseSwitchPanelCell10.setCellValue(new HSSFRichTextString("【开关面板】"));
		for(int i=0;i<4;i++){
			HSSFCell purchaseSwitchPanelCell = purchaseSwitchPanelRow1.createCell(i+1);
			purchaseSwitchPanelCell.setCellStyle(columnHeadStyle);
		}
		
		//4-4-3-2.开关面板--小标题--标题
				
		HSSFRow purchaseSwitchPanelRow2 = sheet4.createRow(purchaseWallAndFloorCount+purchaseMaterialCount+8);
		HSSFCell purchaseSwitchPanelCell20 = purchaseSwitchPanelRow2.createCell(0);
		purchaseSwitchPanelCell20.setCellStyle(columnHeadStyle);
		purchaseSwitchPanelCell20.setCellValue("申请顺序");
		
		HSSFCell purchaseSwitchPanelCell21 = purchaseSwitchPanelRow2.createCell(1);
		purchaseSwitchPanelCell21.setCellStyle(columnHeadStyle);
		purchaseSwitchPanelCell21.setCellValue("当前状态");
		
		HSSFCell purchaseSwitchPanelCell22 = purchaseSwitchPanelRow2.createCell(2);
		purchaseSwitchPanelCell22.setCellStyle(columnHeadStyle);
		purchaseSwitchPanelCell22.setCellValue("提交申请时间");
		
		HSSFCell purchaseSwitchPanelCell23 = purchaseSwitchPanelRow2.createCell(3);
		purchaseSwitchPanelCell23.setCellStyle(columnHeadStyle);
		purchaseSwitchPanelCell23.setCellValue("期望到货日期");
		
		HSSFCell purchaseSwitchPanelCell24 = purchaseSwitchPanelRow2.createCell(4);
		purchaseSwitchPanelCell24.setCellStyle(columnHeadStyle);
		purchaseSwitchPanelCell24.setCellValue("收货日期");
		
		//4-4-3-3.数据内容
		int purchaseSwitchPanelCount = 0;
		if(CollectionUtils.isNotEmpty(purchaseList)){
			for(int i=0;i<purchaseList.size();i++){
				
				Map<String, Object> purchase = purchaseList.get(i);
				
				String purchaseType  = (String) purchase.get("purchaseType");
				
				if(purchaseType.equals("2")){
					purchaseSwitchPanelCount++;
					String purchaseStatusName  = (String) purchase.get("purchaseStatusName");
					String applyTime  = (String) purchase.get("applyTime");
					String applyReceiveTime  = (String) purchase.get("applyReceiveTime");
					String receiveDate  = (String) purchase.get("receiveDate");
					
					
					HSSFRow purchaseSwitchPanelRow = sheet4.createRow(purchaseSwitchPanelCount+purchaseWallAndFloorCount+purchaseMaterialCount+8);
					
					HSSFCell purchaseSwitchPanelCell0 = purchaseSwitchPanelRow.createCell(0);
					purchaseSwitchPanelCell0.setCellStyle(columnStyle);
					purchaseSwitchPanelCell0.setCellValue(purchaseSwitchPanelCount);
					
					HSSFCell purchaseSwitchPanelCell1 = purchaseSwitchPanelRow.createCell(1);
					purchaseSwitchPanelCell1.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(purchaseStatusName)){
						purchaseSwitchPanelCell1.setCellValue(purchaseStatusName);
					}
					
					HSSFCell purchaseSwitchPanelCell2 = purchaseSwitchPanelRow.createCell(2);
					purchaseSwitchPanelCell2.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(applyTime)){
						purchaseSwitchPanelCell2.setCellValue(applyTime);
					}
					
					HSSFCell purchaseSwitchPanelCell3 = purchaseSwitchPanelRow.createCell(3);
					purchaseSwitchPanelCell3.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(applyReceiveTime)){
						purchaseSwitchPanelCell3.setCellValue(applyReceiveTime);
					}
					
					HSSFCell purchaseSwitchPanelCell4 = purchaseSwitchPanelRow.createCell(4);
					purchaseSwitchPanelCell4.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(receiveDate)){
						purchaseSwitchPanelCell4.setCellValue(receiveDate);
					}
					
				}
			}
		}		
		
		//4-5.合并单元格
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet4.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));// 
		sheet4.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));// 
		sheet4.addMergedRegion(new CellRangeAddress(purchaseMaterialCount+4, purchaseMaterialCount+4, 0, 5));// 
		sheet4.addMergedRegion(new CellRangeAddress(purchaseWallAndFloorCount+purchaseMaterialCount+7, purchaseWallAndFloorCount+purchaseMaterialCount+7, 0, 4));// 
				
		
		
		/**
		 * 5.基装阶段：质检
		 */
		
		
		//5-1.查询订单质检信息  数据
		Map<String,Object> map5 = new HashMap<>();
		map5.put("orderId",orderId);
		List<Map<String, Object>> pqcList = orderFullActionDao.pqcOrderFullQuery(map5);
		//5-2.设置单元格宽度
		sheet5.setColumnWidth(0, 8000);
		sheet5.setColumnWidth(1, 6000);
		sheet5.setColumnWidth(2, 6000);
		sheet5.setColumnWidth(3, 6000);
		sheet5.setColumnWidth(4, 6000);
		//5-3.大标题
		HSSFRow pqcRow0 = sheet5.createRow(0);
		pqcRow0.setHeightInPoints(30);
		HSSFCell pqcCell00 = pqcRow0.createCell(0);
		pqcCell00.setCellStyle(columnHeadStyle);
		pqcCell00.setCellValue(new HSSFRichTextString("基装阶段：质检"));
		for(int i=0;i<4;i++){
			HSSFCell pqcCell = pqcRow0.createCell(i+1);
			pqcCell.setCellStyle(columnHeadStyle);
		}
		//5-4.小标题及数据内容
		//5-4-1.小标题
		HSSFRow pqcRow1 = sheet5.createRow(1);
		HSSFCell pqcCell10 = pqcRow1.createCell(0);
		pqcCell10.setCellStyle(columnHeadStyle);
		pqcCell10.setCellValue("约检节点");
		
		HSSFCell pqcCell11 = pqcRow1.createCell(1);
		pqcCell11.setCellStyle(columnHeadStyle);
		pqcCell11.setCellValue("项目经理提报申请时间");
		
		HSSFCell pqcCell12 = pqcRow1.createCell(2);
		pqcCell12.setCellStyle(columnHeadStyle);
		pqcCell12.setCellValue("项目经理申请质检员上门日期");
		
		HSSFCell pqcCell13 = pqcRow1.createCell(3);
		pqcCell13.setCellStyle(columnHeadStyle);
		pqcCell13.setCellValue("质检员实际上门日期");
		
		HSSFCell pqcCell14 = pqcRow1.createCell(4);
		pqcCell14.setCellStyle(columnHeadStyle);
		pqcCell14.setCellValue("质检员确认节点验收通过日期");
		
		
		//5-4-2.数据内容
		if(CollectionUtils.isNotEmpty(pqcList)){
			for(int i=0;i<pqcList.size();i++){
				
				Map<String, Object> pqc = pqcList.get(i);
				
				String qcCheckNodeName  = (String) pqc.get("qcCheckNodeName");
				String managerApplyDate  = (String) pqc.get("managerApplyDate");
				String managerExpectPqcDate  = (String) pqc.get("managerExpectPqcDate");
				String pqcAcutalCheckDate  = (String) pqc.get("pqcAcutalCheckDate");
				String pqcDoneDate  = (String) pqc.get("pqcDoneDate");
				
				HSSFRow pqcRow = sheet5.createRow(i+2);
				
				HSSFCell pqcCell0 = pqcRow.createCell(0);
				pqcCell0.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(qcCheckNodeName)){
					pqcCell0.setCellValue(qcCheckNodeName);
				}
				
				HSSFCell pqcCell1 = pqcRow.createCell(1);
				pqcCell1.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(managerApplyDate)){
					pqcCell1.setCellValue(managerApplyDate);
				}
				
				HSSFCell pqcCell2 = pqcRow.createCell(2);
				pqcCell2.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(managerExpectPqcDate)){
					pqcCell2.setCellValue(managerExpectPqcDate);
				}
				
				HSSFCell pqcCell3 = pqcRow.createCell(3);
				pqcCell3.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(pqcAcutalCheckDate)){
					pqcCell3.setCellValue(pqcAcutalCheckDate);
				}
				
				HSSFCell pqcCell4 = pqcRow.createCell(4);
				pqcCell4.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(pqcDoneDate)){
					pqcCell4.setCellValue(pqcDoneDate);
				}
				
				
			}
		}
		
		
		//5-5.合并单元格
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet5.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));// 
		
		
		
		/**
		 * 6.基装阶段：变更
		 */
		
		
		//6-1.查询订单变更信息  数据
		Map<String,Object> map6 = new HashMap<>();
		map6.put("orderId",orderId);
		List<Map<String, Object>> projectChangeList = orderFullActionDao.projectChangeBillQuery(map6);
		//6-2.设置单元格宽度
		sheet6.setColumnWidth(0, 6000);
		sheet6.setColumnWidth(1, 6000);
		sheet6.setColumnWidth(2, 6000);
		sheet6.setColumnWidth(3, 6000);
		sheet6.setColumnWidth(4, 6000);
		//6-3.大标题
		HSSFRow projectChangeRow0 = sheet6.createRow(0);
		projectChangeRow0.setHeightInPoints(30);
		HSSFCell projectChangeCell00 = projectChangeRow0.createCell(0);
		projectChangeCell00.setCellStyle(columnHeadStyle);
		projectChangeCell00.setCellValue(new HSSFRichTextString("基装阶段：变更"));
		for(int i=0;i<4;i++){
			HSSFCell projectChangeCell = projectChangeRow0.createCell(i+1);
			projectChangeCell.setCellStyle(columnHeadStyle);
		}
		//6-4.小标题及数据内容
		//6-4-1.小标题
		HSSFRow projectChangeRow1 = sheet6.createRow(1);
		HSSFCell projectChangeCell10 = projectChangeRow1.createCell(0);
		projectChangeCell10.setCellStyle(columnHeadStyle);
		projectChangeCell10.setCellValue("变更单编号");
		
		HSSFCell projectChangeCell11 = projectChangeRow1.createCell(1);
		projectChangeCell11.setCellStyle(columnHeadStyle);
		projectChangeCell11.setCellValue("提交变更时间");
		
		HSSFCell projectChangeCell12 = projectChangeRow1.createCell(2);
		projectChangeCell12.setCellStyle(columnHeadStyle);
		projectChangeCell12.setCellValue("设计师审核时间");
		
		HSSFCell projectChangeCell13 = projectChangeRow1.createCell(3);
		projectChangeCell13.setCellStyle(columnHeadStyle);
		projectChangeCell13.setCellValue("审计部审核时间");
		
		HSSFCell projectChangeCell14 = projectChangeRow1.createCell(4);
		projectChangeCell14.setCellStyle(columnHeadStyle);
		projectChangeCell14.setCellValue("财务确认时间");
		
		
		//6-4-2.数据内容
		if(CollectionUtils.isNotEmpty(projectChangeList)){
			for(int i=0;i<projectChangeList.size();i++){
				
				Map<String, Object> projectChange = projectChangeList.get(i);
				
				String projectChangeBillCode  = (String) projectChange.get("projectChangeBillCode");
				Date  applyDate  = (Date) projectChange.get("applyDate");
				Date stylistCheckDate  = (Date) projectChange.get("stylistCheckDate");
				Date auditCheckDate  = (Date) projectChange.get("auditCheckDate");
				Date financeCheckDate  = (Date) projectChange.get("financeCheckDate");
				
				HSSFRow projectChangeRow = sheet6.createRow(i+2);
				
				HSSFCell projectChangeCell0 = projectChangeRow.createCell(0);
				projectChangeCell0.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(projectChangeBillCode)){
					projectChangeCell0.setCellValue(projectChangeBillCode);
				}
				
				HSSFCell projectChangeCell1 = projectChangeRow.createCell(1);
				projectChangeCell1.setCellStyle(columnStyle);
				if(null!=applyDate){
					projectChangeCell1.setCellValue(DateFormatUtils.format(applyDate, "yyyy-MM-dd"));
				}
				
				HSSFCell projectChangeCell2 = projectChangeRow.createCell(2);
				projectChangeCell2.setCellStyle(columnStyle);
				if(null!=stylistCheckDate){
					projectChangeCell2.setCellValue(DateFormatUtils.format(stylistCheckDate, "yyyy-MM-dd HH:mm:ss"));
				}
				
				HSSFCell projectChangeCell3 = projectChangeRow.createCell(3);
				projectChangeCell3.setCellStyle(columnStyle);
				if(null!=auditCheckDate){
					projectChangeCell3.setCellValue(DateFormatUtils.format(auditCheckDate, "yyyy-MM-dd HH:mm:ss"));
				}
				
				HSSFCell projectChangeCell4 = projectChangeRow.createCell(4);
				projectChangeCell4.setCellStyle(columnStyle);
				if(null!=financeCheckDate){
					projectChangeCell4.setCellValue(DateFormatUtils.format(financeCheckDate, "yyyy-MM-dd HH:mm:ss"));
				}
				
				
			}
		}
		
		
		//6-5.合并单元格
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet6.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));// 
		
		
		
		
		/**
		 * 7.基装阶段：款项
		 */
		
		//7-1.查询订单款项信息  数据
		List<BizPrePmSettleFin> PmSettleList = orderFullActionDao.queryPrePmSettleFinance(orderId);
		//7-2.设置单元格宽度
		sheet7.setColumnWidth(0, 6000);
		sheet7.setColumnWidth(1, 6000);
		sheet7.setColumnWidth(2, 6000);
		//7-3.大标题
		HSSFRow PmSettleRow0 = sheet7.createRow(0);
		PmSettleRow0.setHeightInPoints(30);
		HSSFCell PmSettleCell00 = PmSettleRow0.createCell(0);
		PmSettleCell00.setCellStyle(columnHeadStyle);
		PmSettleCell00.setCellValue(new HSSFRichTextString("基装阶段：款项"));
		for(int i=0;i<2;i++){
			HSSFCell PmSettleCell = PmSettleRow0.createCell(i+1);
			PmSettleCell.setCellStyle(columnHeadStyle);
		}
		//7-4.小标题及数据内容
		//7-4-1.小标题
		HSSFRow PmSettleRow1 = sheet7.createRow(1);
		HSSFCell PmSettleCell10 = PmSettleRow1.createCell(0);
		PmSettleCell10.setCellStyle(columnHeadStyle);
		PmSettleCell10.setCellValue("交款批次");
		
		HSSFCell PmSettleCell11 = PmSettleRow1.createCell(1);
		PmSettleCell11.setCellStyle(columnHeadStyle);
		PmSettleCell11.setCellValue("催款时间");
		
		HSSFCell PmSettleCell12 = PmSettleRow1.createCell(2);
		PmSettleCell12.setCellStyle(columnHeadStyle);
		PmSettleCell12.setCellValue("客户交款日期");
		
		//7-4-2.数据内容
		if(CollectionUtils.isNotEmpty(PmSettleList)){
			for(int i=0;i<PmSettleList.size();i++){
				
				BizPrePmSettleFin bizPrePmSettleFin = PmSettleList.get(i);
				
				HSSFRow PmSettleRow = sheet7.createRow(i+2);
				
				HSSFCell PmSettleCell0 = PmSettleRow.createCell(0);
				PmSettleCell0.setCellStyle(columnStyle);
				if(null!=bizPrePmSettleFin.getReceiveMoneyType() && bizPrePmSettleFin.getReceiveMoneyType().equals("1")){
					PmSettleCell0.setCellValue("二期款");
				}else if(null!=bizPrePmSettleFin.getReceiveMoneyType() && bizPrePmSettleFin.getReceiveMoneyType().equals("2")){
					PmSettleCell0.setCellValue("尾款");
				}
					
				HSSFCell PmSettleCell1 = PmSettleRow.createCell(1);
				PmSettleCell1.setCellStyle(columnStyle);
				if(null!=bizPrePmSettleFin.getDeptMoneyDate()){
					PmSettleCell1.setCellValue(DateFormatUtils.format(bizPrePmSettleFin.getDeptMoneyDate(), "yyyy-MM-dd"));
				}
				
				HSSFCell PmSettleCell2 = PmSettleRow.createCell(2);
				PmSettleCell2.setCellStyle(columnStyle);
				if(null!=bizPrePmSettleFin.getReceiveMoneyDatetime()){
					PmSettleCell2.setCellValue(DateFormatUtils.format(bizPrePmSettleFin.getReceiveMoneyDatetime(), "yyyy-MM-dd HH:mm:ss"));
				}
				
			}
		}
		
		
		//7-5.合并单元格
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet7.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));// 
		
		
		/**
		 * 8.安装阶段
		 */
		
		
		//8-1.查询订单安装项信息  数据
		Map<String,Object> map8 = new HashMap<String,Object>();
		map8.put("orderId",orderId);
		map8.put("businessType",BusinessProblemConstantUtil.BUSINESS_PROBLEM_BUSINESS_TYPE_1);
		List<Map<String, Object>> installList = orderFullActionDao.installDetail(map8);
		//8-2.设置单元格宽度
		sheet8.setColumnWidth(0, 6000);
		sheet8.setColumnWidth(1, 6000);
		sheet8.setColumnWidth(2, 6000);
		sheet8.setColumnWidth(3, 6000);
		sheet8.setColumnWidth(4, 6000);
		sheet8.setColumnWidth(5, 6000);
		sheet8.setColumnWidth(6, 6000);
		sheet8.setColumnWidth(7, 6000);
		sheet8.setColumnWidth(8, 6000);
		//8-3.大标题
		HSSFRow installRow0 = sheet8.createRow(0);
		installRow0.setHeightInPoints(30);
		HSSFCell installCell00 = installRow0.createCell(0);
		installCell00.setCellStyle(columnHeadStyle);
		installCell00.setCellValue(new HSSFRichTextString("安装阶段"));
		for(int i=0;i<8;i++){
			HSSFCell installCell = installRow0.createCell(i+1);
			installCell.setCellStyle(columnHeadStyle);
		}
		//8-4.小标题及数据内容
		//8-4-1.小标题
		HSSFRow installRow1 = sheet8.createRow(1);
		HSSFCell installCell10 = installRow1.createCell(0);
		installCell10.setCellStyle(columnHeadStyle);
		installCell10.setCellValue("安装项目");
		
		HSSFCell installCell11 = installRow1.createCell(1);
		installCell11.setCellStyle(columnHeadStyle);
		installCell11.setCellValue("项目经理提交申请时间");
		
		HSSFCell installCell12 = installRow1.createCell(2);
		installCell12.setCellStyle(columnHeadStyle);
		installCell12.setCellValue("项目经理期望到场日期");
		
		HSSFCell installCell13 = installRow1.createCell(3);
		installCell13.setCellStyle(columnHeadStyle);
		installCell13.setCellValue("供应商答复到场日期");
		
		HSSFCell installCell14 = installRow1.createCell(4);
		installCell14.setCellStyle(columnHeadStyle);
		installCell14.setCellValue("实际进场日期");
		
		HSSFCell installCell15 = installRow1.createCell(5);
		installCell15.setCellStyle(columnHeadStyle);
		installCell15.setCellValue("实际完工日期");
		
		HSSFCell installCell16 = installRow1.createCell(6);
		installCell16.setCellStyle(columnHeadStyle);
		installCell16.setCellValue("项目经理验收日期");
		
		HSSFCell installCell17 = installRow1.createCell(7);
		installCell17.setCellStyle(columnHeadStyle);
		installCell17.setCellValue("项目经理问题反馈次数");
		
		HSSFCell installCell18 = installRow1.createCell(8);
		installCell18.setCellStyle(columnHeadStyle);
		installCell18.setCellValue("项目经理问题反馈影响工期");
		
		//8-4-2.数据内容
		if(CollectionUtils.isNotEmpty(installList)){
			for(int i=0;i<installList.size();i++){
				
				Map<String, Object> install = installList.get(i);
				
				String installItemName  = (String) install.get("installItemName");
				String applyIntoCreateDatetime  = (String) install.get("applyIntoCreateDatetime");
				String applyIntoDate  = (String) install.get("applyIntoDate");
				String supplierConfirmIntoDate  = (String) install.get("supplierConfirmIntoDate");
				String realIntoDate  = (String) install.get("realIntoDate");
				String realCompleteDate  = (String) install.get("realCompleteDate");
				String realAcceptDate  = (String) install.get("realAcceptDate");
				Long problemCount  = (Long) install.get("problemCount");
				BigDecimal delayDays  = (BigDecimal) install.get("delayDays");
				
				HSSFRow installRow = sheet8.createRow(i+2);
				
				HSSFCell installCell0 = installRow.createCell(0);
				installCell0.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(installItemName)){
					installCell0.setCellValue(installItemName);
				}
				
				HSSFCell installCell1 = installRow.createCell(1);
				installCell1.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(applyIntoCreateDatetime)){
					installCell1.setCellValue(applyIntoCreateDatetime);
				}
				
				HSSFCell installCell2 = installRow.createCell(2);
				installCell2.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(applyIntoDate)){
					installCell2.setCellValue(applyIntoDate);
				}
				
				HSSFCell installCell3 = installRow.createCell(3);
				installCell3.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(supplierConfirmIntoDate)){
					installCell3.setCellValue(supplierConfirmIntoDate);
				}
				
				HSSFCell installCell4 = installRow.createCell(4);
				installCell4.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(realIntoDate)){
					installCell4.setCellValue(realIntoDate);
				}
				
				HSSFCell installCell5 = installRow.createCell(5);
				installCell5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(realCompleteDate)){
					installCell5.setCellValue(realCompleteDate);
				}
				
				HSSFCell installCell6 = installRow.createCell(6);
				installCell6.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(realAcceptDate)){
					installCell6.setCellValue(realAcceptDate);
				}
				
				HSSFCell installCell7 = installRow.createCell(7);
				installCell7.setCellStyle(columnStyle);
				if(null!=problemCount){
					installCell7.setCellValue(String.valueOf(problemCount));
				}
				
				HSSFCell installCell8 = installRow.createCell(8);
				installCell8.setCellStyle(columnStyle);
				if(null!=delayDays){
					installCell8.setCellValue(String.valueOf(delayDays));
				}
				
				
				
			}
		}
		
		
		//8-5.合并单元格
		//合并单元格--开始行，结束行，开始列，结束列  
		sheet8.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));// 
		
		
		
		
		return wb;
	}
	
	
	
}