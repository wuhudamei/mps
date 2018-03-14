package cn.damei.service.modules;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.ProblemItemReportDao;
import cn.damei.entity.modules.ProblemItemReport;

@Service
@Transactional(readOnly = true)
public class ProblemItemReportService extends CrudService<ProblemItemReportDao, ProblemItemReport> {
	@Autowired
	private ProblemItemReportDao problemItemReportDao;

	/**
	 * 投诉事项报表查询
	 * 
	 * @Title: queryList
	 * @Description: TODO
	 * @param @param problemItemReport 前台传的参数实体
	 * @param @return
	 * @return List<ProblemItemReport>
	 * @author ZhangTongWei
	 * @throws
	 */
	public List<ProblemItemReport> queryList(ProblemItemReport problemItemReport) {
		List<ProblemItemReport> itemList = null;
		try {
			// 查询出单个事项的数量
			if (problemItemReport.getStoreId() == null)
				problemItemReport.setStoreId(2);
			itemList = problemItemReportDao.queryItemList(problemItemReport);
			// 根据工种和区域统计投诉数量
			List<ProblemItemReport> workTypeList = problemItemReportDao.queryWorkTypeList(problemItemReport);
			// 根据区域统计投诉数量
			List<ProblemItemReport> regionList = problemItemReportDao.queryRegionList(problemItemReport);
			if (null != itemList && !itemList.isEmpty()) {
				for (int i = 0; i < itemList.size(); i++) {
					if (!workTypeList.isEmpty()) {
						// 遍历工种下事项投诉数拼接到单个事项数量(工种小计)
						for (int j = 0; j < workTypeList.size(); j++) {
							// 区域和工种一样就说明是一个事项
							if ((itemList.get(i).getAcceptArea().equals(workTypeList.get(j).getAcceptArea())) && (itemList.get(i).getWorkType().equals(workTypeList.get(j).getWorkType()))) {
								itemList.get(i).setWorkTypeSubtotal(workTypeList.get(j).getItemCount());
								itemList.get(i).setWorkTypeSuflag(j);
							}
						}
					}
					// 遍历区域下的投诉事项拼接到单个事项数量上 (分区总计)
					if (null != regionList && !regionList.isEmpty()) {
						for (int j = 0; j < regionList.size(); j++) {
							// 区域如果一样说明是一个区域下面的
							if (itemList.get(i).getAcceptArea().equals(regionList.get(j).getAcceptArea())) {
								itemList.get(i).setAcceptAreaPro(regionList.get(j).getItemCount());
								itemList.get(i).setAccAreaProflag(j);
							}
						}
					}
					// 投诉事项占比 公式:投诉数量/小计
					if (null != itemList.get(i).getItemCount() && null != itemList.get(i).getWorkTypeSubtotal() && itemList.get(i).getWorkTypeSubtotal() > 0) {

						itemList.get(i).setItemProportion(Double.valueOf(String.format("%.2f", itemList.get(i).getItemCount() / itemList.get(i).getWorkTypeSubtotal() * 100)));
					}

					// 投诉工种占比 公式:小计/分区总计
					if (null != regionList && !workTypeList.isEmpty()) {
						for (int j = 0; j < workTypeList.size(); j++) {
							// 区域和工种一样就说明是一个事项
							if ((itemList.get(i).getAcceptArea().equals(workTypeList.get(j).getAcceptArea())) && (itemList.get(i).getWorkType().equals(workTypeList.get(j).getWorkType()))) {
								itemList.get(i).setWorkTypePro(Double.valueOf(String.format("%.2f", itemList.get(i).getWorkTypeSubtotal() / itemList.get(i).getAcceptAreaPro() * 100)));
								itemList.get(i).setWorkTypeProflag(j);
							}
						}
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("cn.damei.service.modules.ProblemItemReportService.queryListt:这个方法error了!!!是error不是warning");
		}

		return itemList;
	}

	/**
	 * 
	 * @Title: excelExportItem
	 * @Description: TODO
	 * @param @param problemItemReport 前台传的参数实体
	 * @param @param itemList
	 * @param @param wb 创建Excel的类
	 * @param @throws Exception
	 * @return void
	 * @author ZhangTongWei
	 * @throws
	 */
	public void excelExportItem(ProblemItemReport problemItemReport, List<ProblemItemReport> itemList, HSSFWorkbook wb) throws Exception {
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("投诉事项报表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);

		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = wb.createFont();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		font.setFontName("宋体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font.setFontHeightInPoints((short) 19);// 设置字体大小
		style.setFont(font);
		cell.setCellStyle(style);
		// 设置单元格内容
		cell.setCellValue("投诉事项统计报表");
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);

		HSSFCell cella = row2.createCell(1);
		HSSFCellStyle stylea = wb.createCellStyle();
		stylea.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		stylea.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font1 = wb.createFont();
		font1.setFontName("宋体");
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font1.setFontHeightInPoints((short) 18);// 设置字体大小
		stylea.setFont(font1);
		cella.setCellStyle(style);

		// 创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("区域");
		row2.createCell(1).setCellValue("工种");
		row2.createCell(2).setCellValue("投诉事项");
		row2.createCell(3).setCellValue("投诉数量");
		row2.createCell(4).setCellValue("小计");
		row2.createCell(5).setCellValue("投诉事项占比");
		row2.createCell(6).setCellValue("投诉工种占比");
		row2.createCell(7).setCellValue("分区总计");
		if (null != itemList && !itemList.isEmpty()) {
			for (int i = 0; i < itemList.size(); i++) {
				// 在sheet里创建第三行
				HSSFRow row3 = sheet.createRow(i + 2);
				row3.createCell(0).setCellValue(itemList.get(i).getAcceptArea());
				row3.createCell(1).setCellValue(itemList.get(i).getWorkType());
				row3.createCell(2).setCellValue(itemList.get(i).getItemName());
				row3.createCell(3).setCellValue(itemList.get(i).getItemCount());
				row3.createCell(4).setCellValue(itemList.get(i).getWorkTypeSubtotal());
				row3.createCell(5).setCellValue(itemList.get(i).getItemProportion());
				row3.createCell(6).setCellValue(itemList.get(i).getWorkTypePro());
				row3.createCell(7).setCellValue(itemList.get(i).getAcceptAreaPro());
			}
		}
		List<ProblemItemReport> regionList = problemItemReportDao.queryRegionCount(problemItemReport);
		int startRow = 0;
		int endRow = 0;
		if (null != regionList && !regionList.isEmpty()) {
			for (int i = 0; i < regionList.size(); i++) {
				if (i == 0) {
					startRow = 2;
					Double itemCount = regionList.get(i).getItemCount();
					endRow = itemCount.intValue() + 1;
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, 7, 7));
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, 0, 0));
				} else {
					if (i == 1)
						endRow = endRow + 1;
					startRow = endRow;
					endRow = (int) (startRow + regionList.get(i).getItemCount());
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow - 1, 7, 7));
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow - 1, 0, 0));
				}
			}
		}
		startRow = 0;
		endRow = 0;
		List<ProblemItemReport> workTypeList = problemItemReportDao.queryWorkTypeCount(problemItemReport);
		if (null != workTypeList && !workTypeList.isEmpty()) {
			for (int i = 0; i < workTypeList.size(); i++) {
				if (i == 0) {
					startRow = 2;
					Double itemCount = workTypeList.get(i).getItemCount();
					endRow = itemCount.intValue() + 1;
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, 1, 1));
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, 4, 4));
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, 6, 6));
				} else {
					if (i == 1)
						endRow = endRow + 1;
					startRow = endRow;
					endRow = (int) (startRow + workTypeList.get(i).getItemCount());
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow - 1, 6, 6));
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow - 1, 4, 4));
					sheet.addMergedRegion(new CellRangeAddress(startRow, endRow - 1, 1, 1));
				}
			}
		}

	}
}
