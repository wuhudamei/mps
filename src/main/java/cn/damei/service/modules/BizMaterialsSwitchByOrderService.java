package cn.damei.service.modules;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.damei.entity.modules.Dict;
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
import cn.damei.dao.modules.BizMaterialsSwitchByOrderDao;
import cn.damei.entity.modules.BizMaterialsSwitchByOrder;

/**
 * 材料类按订单汇总查询（开关面板）
 * @author 王硕
 * @version 2017-10-27
 */
@Service
@Transactional(readOnly = true)
public class BizMaterialsSwitchByOrderService extends CrudService2<BizMaterialsSwitchByOrderDao, BizMaterialsSwitchByOrder> {

	public List<BizMaterialsSwitchByOrder> findList(BizMaterialsSwitchByOrder bizMaterialsSwitchByOrder) {
		return super.findList(bizMaterialsSwitchByOrder);
	}
	
	public Page<BizMaterialsSwitchByOrder> findPage(Page<BizMaterialsSwitchByOrder> page, BizMaterialsSwitchByOrder bizMaterialsSwitchByOrder) {
		return super.findPage(page, bizMaterialsSwitchByOrder);
	}

	public BizMaterialsSwitchByOrder findByOrderId(Integer orderId) {
		return dao.findByOrderId(orderId);
	}

	public List<BizMaterialsSwitchByOrder> findHejiByOrderId(Integer orderId) {
		return dao.findHejiByOrderId(orderId);
	}

	public List<BizMaterialsSwitchByOrder> findMingxiByOrderId(Integer orderId) {
		return dao.findMingxiByOrderId(orderId);
	}
	

	public HSSFWorkbook exportExcel(BizMaterialsSwitchByOrder bizMaterialsSwitchByOrder) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("开关面板采购明细");
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFFont.COLOR_RED);//字体颜色
		font.setFontName("黑体");//字体
		font.setFontHeightInPoints((short)50);//字体高度
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
		// 单元格宽度
		sheet.setColumnWidth(0, 1233);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 2000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 13000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 4000);
		sheet.setColumnWidth(14, 4000);
		sheet.setColumnWidth(15, 6000);
		//标题
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(30);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnStyle);
		cell.setCellValue(new HSSFRichTextString("罗格朗开关面板系统"));

		for(int i=0;i<15;i++){
			HSSFCell cella = rowTitle.createCell(i+1);
			cella.setCellStyle(columnStyle);
		}
		//合并单元格--开始行，结束行，开始列，结束列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));//
		//标题
		HSSFRow rowTitle2 = sheet.createRow(1);
		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(columnStyle);
		headCell0.setCellValue("序号");

		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(columnStyle);
		headCell1.setCellValue("提交申请时间");

		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(columnStyle);
		headCell2.setCellValue("送货日期");

		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(columnStyle);
		headCell3.setCellValue("采购单编号");

		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(columnStyle);
		headCell4.setCellValue("订单编号");

		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(columnStyle);
		headCell5.setCellValue("客户姓名");

		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(columnStyle);
		headCell6.setCellValue("项目经理");

		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(columnStyle);
		headCell7.setCellValue("项目经理电话");

		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(columnStyle);
		headCell8.setCellValue("配送地址");

		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(columnStyle);
		headCell9.setCellValue("完成收货日期");

		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(columnStyle);
		headCell10.setCellValue("品牌");
		
		HSSFCell headCell11 = rowTitle2.createCell(11);
		headCell11.setCellStyle(columnStyle);
		headCell11.setCellValue("商品名称");
		
		HSSFCell headCell12 = rowTitle2.createCell(12);
		headCell12.setCellStyle(columnStyle);
		headCell12.setCellValue("规格型号");
		
		HSSFCell headCell13 = rowTitle2.createCell(13);
		headCell13.setCellStyle(columnStyle);
		headCell13.setCellValue("申请数量");
		
		HSSFCell headCell14 = rowTitle2.createCell(14);
		headCell14.setCellStyle(columnStyle);
		headCell14.setCellValue("收货数量");
		
		HSSFCell headCell15 = rowTitle2.createCell(15);
		headCell15.setCellStyle(columnStyle);
		headCell15.setCellValue("商品金额（工人结算价）");
		

		List<BizMaterialsSwitchByOrder> list =dao.export(bizMaterialsSwitchByOrder);

		if(CollectionUtils.isNotEmpty(list)){
			Integer listSize = list.size();
			for(int v =0;v<listSize;v++){

				BizMaterialsSwitchByOrder  entity = list.get(v);
				//从第三行开始
				HSSFRow row = sheet.createRow(v+2);

				//第一个是序号
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(v+1);

				//提交申请时间
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				
				if(null!=entity.getApplyTime()){
					String applyDate=(new SimpleDateFormat("yyyy-MM-dd")).format(entity.getApplyTime()); 
					cell1.setCellValue(applyDate);
				}
				//送货日期
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if(null!=entity.getApplyReceiveTime()){
					String applyReceiveDate=(new SimpleDateFormat("yyyy-MM-dd")).format(entity.getApplyReceiveTime()); 
					cell2.setCellValue(applyReceiveDate);
				}
				//采购单编号
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getPurchaseCode())){
					cell3.setCellValue(entity.getPurchaseCode());
				}
				//订单编号
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getOrderNumber())){
					cell4.setCellValue(entity.getOrderNumber());
				}
				//客户姓名
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getCustomerName())){
					cell5.setCellValue(entity.getCustomerName());
				}
				//项目经理
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getRealName())){
					cell6.setCellValue(entity.getRealName());
				}
				//项目经理电话
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getPhone())){
					cell7.setCellValue(entity.getPhone());
				}
				//配送地址
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getCustomerAddress())){
					cell8.setCellValue(entity.getCustomerAddress());
				}
				//完成收货日期
				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(columnStyle);
				if(null!=entity.getFinalReceiveTime()){
					String finalReceiveTime=(new SimpleDateFormat("yyyy-MM-dd")).format(entity.getFinalReceiveTime()); 
					cell9.setCellValue(finalReceiveTime);
				}
				//品牌
				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getBrands())){
					cell10.setCellValue(entity.getBrands());
				}
				//商品名称
				HSSFCell cell11 = row.createCell(11);
				cell11.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getAuxiMaterialName())){
					cell11.setCellValue(entity.getAuxiMaterialName());
				}
				//规格型号
				HSSFCell cell12 = row.createCell(12);
				cell12.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(entity.getSpecifications())){
					cell12.setCellValue(entity.getSpecifications());
				}
				//申请数量
				HSSFCell cell13 = row.createCell(13);
				cell13.setCellStyle(columnStyle);
				if(null!=entity.getAuxiMateCount()){
					cell13.setCellValue(entity.getAuxiMateCount());
				}
				//收货数量
				HSSFCell cell14 = row.createCell(14);
				cell14.setCellStyle(columnStyle);
				if(null!=entity.getReceivedAuxiMateCount()){
					cell14.setCellValue(entity.getReceivedAuxiMateCount());
				}
				//商品金额（工人结算价）
				HSSFCell cell15 = row.createCell(15);
				cell15.setCellStyle(columnStyle);
				if(null!=entity.getLaborPrice()){
					cell15.setCellValue(entity.getLaborPrice());
				}
			}

		}

		return workbook;
	}

    public List<Dict> findPurchaseStatus() {
		return dao.findPurchaseStatus();
    }
}