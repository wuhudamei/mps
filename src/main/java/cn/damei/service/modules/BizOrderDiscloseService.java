package cn.damei.service.modules;

import java.text.SimpleDateFormat;
import java.util.*;

import cn.damei.common.utils.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderDiscloseDao;
import cn.damei.entity.modules.BizOrderDisclose;

/**
 * 订单交底
 * @author llp
 */
@Service
@Transactional(readOnly = true)
public class BizOrderDiscloseService extends CrudService2<BizOrderDiscloseDao, BizOrderDisclose>{
	
	@Autowired
	private BizOrderDiscloseDao bizOrderDiscloseDao;
	
	public BizOrderDisclose get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderDisclose> findList(BizOrderDisclose bizOrderDisclose) {
		return super.findList(bizOrderDisclose);
	}
	
	public Page<BizOrderDisclose> findPage(Page<BizOrderDisclose> page, BizOrderDisclose bizOrderDisclose) {
		return super.findPage(page, bizOrderDisclose);
	}



	@SuppressWarnings("deprecation")
	public HSSFWorkbook exportExcel(BizOrderDisclose bizOrderDisclose) {


		HSSFWorkbook workbook = new HSSFWorkbook();


		HSSFSheet sheet = workbook.createSheet("设计交底信息");
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

		  /*
		  	cellStyle.setFont(font);
		  	cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
		  	cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//上下居中
		  	cellStyle.setLocked(true);
		  	cellStyle.setWrapText(true);

		  	cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);//左边框的颜色
		  	cellStyle.setBorderLeft((short)3);//边框的大小
		  	cellStyle.setBorderRight((short) 3);// 边框的大小
			cellStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框的颜色
			cellStyle.setBorderTop((short) 3);// 边框的大小
			cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);// 下边框的颜色
			cellStyle.setBorderBottom((short) 3);// 边框的大小
			cellStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
*/			/*cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
*/		/*	cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);		*/



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
			/*columnStyle.setLeftBorderColor(HSSFColor.BLACK.index); // 左边框线的颜色
			columnStyle.setBorderLeft((short) 3);// 左边框线的大小
			columnStyle.setRightBorderColor(HSSFColor.BLACK.index); // 右边框线的颜色
			columnStyle.setBorderRight((short) 3);// 右边框线的大小
			columnStyle.setTopBorderColor(HSSFColor.BLACK.index); // 上边框线的颜色
			columnStyle.setBorderTop((short) 3);// 上边框线的大小
			columnStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框线的颜色
			columnStyle.setBorderBottom((short) 3);// 下边框线的大小
			columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);*/



		// 单元格宽度
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 5000);
		sheet.setColumnWidth(4, 7000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 13000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);








		//标题
		HSSFRow rowTitle2 = sheet.createRow(0);

		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(columnStyle);
		headCell0.setCellValue("门店");

		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(columnStyle);
		headCell1.setCellValue("工程模式");

		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(columnStyle);
		headCell2.setCellValue("区域");

		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(columnStyle);
		headCell3.setCellValue("提交时间");

		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(columnStyle);
		headCell4.setCellValue("小区");

		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(columnStyle);
		headCell5.setCellValue("客户");

		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(columnStyle);
		headCell6.setCellValue("详细地址");

		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(columnStyle);
		headCell7.setCellValue("实际交底日期");

		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(columnStyle);
		headCell8.setCellValue("合同开工日期");

		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(columnStyle);
		headCell9.setCellValue("项目经理");

		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(columnStyle);
		headCell10.setCellValue("设计师");





		List<BizOrderDisclose> list =bizOrderDiscloseDao.findListForExcel(bizOrderDisclose);


		if(CollectionUtils.isNotEmpty(list)){
			Integer listSize = list.size();
			for(int v =0;v<listSize;v++){

				BizOrderDisclose  orderDisclose = list.get(v);
				//从第2行开始
				HSSFRow row = sheet.createRow(v+1);

				//第一个是门店
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(orderDisclose.getStoreName());

				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if(null!=orderDisclose.getProjectMode()){
					cell1.setCellValue(orderDisclose.getProjectMode().equals("1")?"产业":"传统");
				}
					//区域
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(orderDisclose.getEngineDepartName())){
					cell2.setCellValue(orderDisclose.getEngineDepartName());
				}
				//提交时间
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if(null!=orderDisclose.getDisCreateDate()){
					cell3.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(orderDisclose.getDisCreateDate()));
				}
				//客户地址
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);

				cell4.setCellValue(orderDisclose.getCommunityName()+"-"+orderDisclose.getBuildNumber()+"-"+orderDisclose.getBuildUnit()+"-"+orderDisclose.getBuildRoom());


				//客户姓名
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(orderDisclose.getCustomerName())){
					cell5.setCellValue(orderDisclose.getCustomerName());
				}

				//详细地址
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(orderDisclose.getDetailAddress())){
					cell6.setCellValue(orderDisclose.getDetailAddress());
				}
				//实际交底日期
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if(null!=orderDisclose.getDiscloseDate()){
					cell7.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(orderDisclose.getDiscloseDate()));
				}
				//合同开工日期
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if(null!=orderDisclose.getContractStartDate()){
					cell8.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(orderDisclose.getContractStartDate()));
				}


				//项目经理
				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(orderDisclose.getManagerRealName())){
					cell9.setCellValue(orderDisclose.getManagerRealName());
				}
				//设计师
				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(orderDisclose.getDesignerName())){
					cell10.setCellValue(orderDisclose.getDesignerName());
				}


			}




		}



		return workbook;

	}

}
