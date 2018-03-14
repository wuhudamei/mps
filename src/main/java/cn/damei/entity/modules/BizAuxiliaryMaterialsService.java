/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizAuxiliaryMaterials;
import cn.damei.dao.modules.BizAuxiliaryMaterialsDao;

/**
 * 辅料管理Service
 * @author chy
 * @version 2016-09-09
 */
@Service
@Transactional(readOnly = true)
public class BizAuxiliaryMaterialsService extends CrudService<BizAuxiliaryMaterialsDao, BizAuxiliaryMaterials> {

	@Autowired
	private BizAuxiliaryMaterialsDao bizAuxiliaryMaterialsDao;

	public BizAuxiliaryMaterials get(String id) {
		return super.get(id);
	}
	
	public List<BizAuxiliaryMaterials> findList(BizAuxiliaryMaterials bizAuxiliaryMaterials) {
		return super.findList(bizAuxiliaryMaterials);
	}
	
	public Page<BizAuxiliaryMaterials> findPage(Page<BizAuxiliaryMaterials> page, BizAuxiliaryMaterials bizAuxiliaryMaterials) {
		return super.findPage(page, bizAuxiliaryMaterials);
	}
	
	@Transactional(readOnly = false)
	public void save(BizAuxiliaryMaterials bizAuxiliaryMaterials) {
		super.save(bizAuxiliaryMaterials);
	}
	@Transactional(readOnly = false)
	public void update(BizAuxiliaryMaterials bizAuxiliaryMaterials) {
		super.save(bizAuxiliaryMaterials);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizAuxiliaryMaterials bizAuxiliaryMaterials) {
		super.delete(bizAuxiliaryMaterials);
	}

	public List<BizAuxiliaryMaterials> getSKUNo(String storeId) {
		BizAuxiliaryMaterials param = new BizAuxiliaryMaterials();
        param.setStoreId(storeId);
		param.setStatus("1");
		return bizAuxiliaryMaterialsDao.findList(param);
	}
	
	/**
	 * 导出excel--辅料
	 * @param selectOrder
	 * @return
	 * @throws ParseException 
	 */
	public HSSFWorkbook exportExcel(BizAuxiliaryMaterials bizAuxiliaryMaterials) throws ParseException {
		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet("辅料商品清单");// 创建一个Excel的Sheet
		
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
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		
		
		
		//标题
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(50);
		HSSFCell headCell0 = rowTitle.createCell(0);
		headCell0.setCellStyle(columnHeadStyle);
		headCell0.setCellValue("材料编号");
		
		HSSFCell headCell1 = rowTitle.createCell(1);
		headCell1.setCellStyle(columnHeadStyle);
		headCell1.setCellValue("材料名称");
		
		HSSFCell headCell2 = rowTitle.createCell(2);
		headCell2.setCellStyle(columnHeadStyle);
		headCell2.setCellValue("常用工种");
		
		HSSFCell headCell3 = rowTitle.createCell(3);
		headCell3.setCellStyle(columnHeadStyle);
		headCell3.setCellValue("规格型号");
		
		HSSFCell headCell4 = rowTitle.createCell(4);
		headCell4.setCellStyle(columnHeadStyle);
		headCell4.setCellValue("单位");
		
		HSSFCell headCell5 = rowTitle.createCell(5);
		headCell5.setCellStyle(columnHeadStyle);
		headCell5.setCellValue("品牌");
		
		HSSFCell headCell6 = rowTitle.createCell(6);
		headCell6.setCellStyle(columnHeadStyle);
		headCell6.setCellValue("工人结算价");
		
		HSSFCell headCell7 = rowTitle.createCell(7);
		headCell7.setCellStyle(columnHeadStyle);
		headCell7.setCellValue("供应商供货价");
		
		HSSFCell headCell8 = rowTitle.createCell(8);
		headCell8.setCellStyle(columnHeadStyle);
		headCell8.setCellValue("门店结算价");
		
		
			
		//内容  数据
		BizAuxiliaryMaterials auxiliaryMaterials = new BizAuxiliaryMaterials();
		auxiliaryMaterials.setStatus("1");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String format2 = format.format(date);
		Date parse = format.parse(format2);
		List<BizAuxiliaryMaterials> list =dao.findExport(parse,bizAuxiliaryMaterials.getStoreId());
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				BizAuxiliaryMaterials item = list.get(i);
				HSSFRow row = sheet.createRow(i+1);
				
				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(item.getAuxiliaryMaterialsNo())){
					cell0.setCellValue(item.getAuxiliaryMaterialsNo());
				}
				
				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(item.getAuxiliaryMaterialsName())){
					cell1.setCellValue(item.getAuxiliaryMaterialsName());
				}

				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(item.getEmpWorkType())){
					cell2.setCellValue(item.getEmpWorkType());
				}
				
				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(item.getSpecifications())){
					cell3.setCellValue(StringEscapeUtils.unescapeHtml(item.getSpecifications()));
				}
				
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(item.getMeasurementUnit())){
					cell4.setCellValue(item.getMeasurementUnit());
				}
				
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(item.getBrands())){
					cell5.setCellValue(item.getBrands());
				}
				
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if(null!=item.getPrice()){
					cell6.setCellValue(item.getPrice());
				}
				
				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if(null!=item.getSupplierPrice()){
					cell7.setCellValue(item.getSupplierPrice());
				}
				
				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if(null!=item.getWangzhenPrice()){
					cell8.setCellValue(item.getWangzhenPrice());
				}
				
				
			}
		}
		
		return wb;
	}
}