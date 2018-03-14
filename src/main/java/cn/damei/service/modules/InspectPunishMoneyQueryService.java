package cn.damei.service.modules;

import java.text.SimpleDateFormat;
import java.util.*;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.InspectPunishMoneyQueryDao;
import cn.damei.entity.modules.InspectPunishMoneyQueryEntity;

@Service
@Transactional(readOnly = true)
public class InspectPunishMoneyQueryService extends CrudService<InspectPunishMoneyQueryDao,InspectPunishMoneyQueryEntity> {

	
	public List<InspectPunishMoneyQueryEntity>   findName(Integer itemId){
		
		return dao.findName(itemId);
	}

	@SuppressWarnings("deprecation")
	public HSSFWorkbook exportExcel(InspectPunishMoneyQueryEntity checkEntity) {


		HashMap<String, String> transferMap = new HashMap<>();
		transferMap.put("&nbsp;"," ");
		transferMap.put("&amp;","&");
		transferMap.put("&quot;","\"");
		transferMap.put("&copy;","©");
		transferMap.put("&reg;","®");
		transferMap.put("&trade;","™");
		transferMap.put("&ldquo;","“");
		transferMap.put("&rdquo;","”");
		transferMap.put("&lsquo;","‘");
		transferMap.put("&rsquo;","’");
		transferMap.put("&laquo;","«");
		transferMap.put("&raquo;","»");
		transferMap.put("&lsaquo;","‹");
		transferMap.put("&rsaquo;","›");
		transferMap.put("&hellip;","…");
		transferMap.put("&#124;","|");
		transferMap.put("&le;","<=");
		transferMap.put("&ge;",">=");


		Integer size = transferMap.size();



		HSSFWorkbook workbook = new HSSFWorkbook();
		  String startDate="";
			if (null != checkEntity.getStartDate()) {
				 startDate = new SimpleDateFormat("yyyy-MM-dd").format(checkEntity.getStartDate());

			}
			String endDate="";
			if (null != checkEntity.getEndDate()) {

				 endDate = new SimpleDateFormat("yyyy-MM-dd").format(checkEntity.getEndDate());
			}
		  HSSFSheet sheet = workbook.createSheet("处罚通报表");
		  HSSFFont font = workbook.createFont();
			font.setColor(HSSFFont.COLOR_RED);
			font.setFontName("黑体");
			font.setFontHeightInPoints((short)50);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		  HSSFCellStyle cellStyle = workbook.createCellStyle();


		  cellStyle.setFont(font);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			cellStyle.setLocked(true);
			cellStyle.setWrapText(true);
			cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
			cellStyle.setBorderLeft((short) 1);
			cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
			cellStyle.setBorderRight((short) 1);
			cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
			cellStyle.setBorderTop((short) 1);
			cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
			cellStyle.setBorderBottom((short) 1);
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
			cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);






			HSSFCellStyle columnStyle = workbook.createCellStyle();
			columnStyle.setLeftBorderColor(HSSFColor.BLACK.index);
			columnStyle.setBorderLeft((short) 1);
			columnStyle.setRightBorderColor(HSSFColor.BLACK.index);
			columnStyle.setBorderRight((short) 1);
			columnStyle.setTopBorderColor(HSSFColor.BLACK.index);
			columnStyle.setBorderTop((short) 1);
			columnStyle.setBottomBorderColor(HSSFColor.BLACK.index);
			columnStyle.setBorderBottom((short) 1);
			columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);





			sheet.setColumnWidth(0, 1233);
			sheet.setColumnWidth(1, 4000);
			sheet.setColumnWidth(2, 2000);
			sheet.setColumnWidth(3, 5000);
			sheet.setColumnWidth(4, 4000);
			sheet.setColumnWidth(5, 2000);
			sheet.setColumnWidth(6, 3000);
			sheet.setColumnWidth(7, 3000);
			sheet.setColumnWidth(8, 3000);
			sheet.setColumnWidth(9, 13000);
			sheet.setColumnWidth(10, 4000);
			sheet.setColumnWidth(11, 4000);
			sheet.setColumnWidth(12, 4000);




			HSSFRow rowTitle = sheet.createRow(0);
			rowTitle.setHeightInPoints(30);
			HSSFCell cell = rowTitle.createCell(0);
			cell.setCellStyle(columnStyle);
			cell.setCellValue(new HSSFRichTextString("北京大美装饰管理平台工程部质检处工地处罚通报表("+startDate+"-"+endDate+")"));

			for(int i=0;i<12;i++){
				HSSFCell cella = rowTitle.createCell(i+1);
				cella.setCellStyle(columnStyle);
			}


			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));



			HSSFRow rowTitle2 = sheet.createRow(1);

			HSSFCell headCell0 = rowTitle2.createCell(0);
			headCell0.setCellStyle(columnStyle);
			headCell0.setCellValue("序号");

			HSSFCell headCell1 = rowTitle2.createCell(1);
			headCell1.setCellStyle(columnStyle);
			headCell1.setCellValue("日期");

			HSSFCell headCell2 = rowTitle2.createCell(2);
			headCell2.setCellStyle(columnStyle);
			headCell2.setCellValue("客户");

			HSSFCell headCell3 = rowTitle2.createCell(3);
			headCell3.setCellStyle(columnStyle);
			headCell3.setCellValue("地址");

			HSSFCell headCell4 = rowTitle2.createCell(4);
			headCell4.setCellStyle(columnStyle);
			headCell4.setCellValue("工程模式");

			HSSFCell headCell5 = rowTitle2.createCell(5);
			headCell5.setCellStyle(columnStyle);
			headCell5.setCellValue("区域");

			HSSFCell headCell6 = rowTitle2.createCell(6);
			headCell6.setCellStyle(columnStyle);
			headCell6.setCellValue("质检");

			HSSFCell headCell7 = rowTitle2.createCell(7);
			headCell7.setCellStyle(columnStyle);
			headCell7.setCellValue("项目经理");

			HSSFCell headCell8 = rowTitle2.createCell(8);
			headCell8.setCellStyle(columnStyle);
			headCell8.setCellValue("工人");

			HSSFCell headCell9 = rowTitle2.createCell(9);
			headCell9.setCellStyle(columnStyle);
			headCell9.setCellValue("原因");

			HSSFCell headCell10 = rowTitle2.createCell(10);
			headCell10.setCellStyle(columnStyle);
			headCell10.setCellValue("项目经理罚款金额");

			HSSFCell headCell11 = rowTitle2.createCell(11);
			headCell11.setCellStyle(columnStyle);
			headCell11.setCellValue("工人罚款金额");
			HSSFCell headCell12 = rowTitle2.createCell(12);
			headCell12.setCellStyle(columnStyle);
			headCell12.setCellValue("质检罚款金额");





			List<InspectPunishMoneyQueryEntity> list =dao.findListForExcel(checkEntity);
			for (InspectPunishMoneyQueryEntity item : list) {
				StringBuilder sb = new StringBuilder();
				List<InspectPunishMoneyQueryEntity> name = dao.findName(item.getCheckItemId());

				for (InspectPunishMoneyQueryEntity inspectPunishMoneyQueryEntity : name) {
					sb.append(inspectPunishMoneyQueryEntity.getIllegalName());
					sb.append(",");
				}
				item.setIllegalName(sb.toString());

			}

			if(CollectionUtils.isNotEmpty(list)){
				Integer listSize = list.size();
				for(int v =0;v<listSize;v++){

					InspectPunishMoneyQueryEntity  entity = list.get(v);

					HSSFRow row = sheet.createRow(v+2);


					HSSFCell cell0 = row.createCell(0);
					cell0.setCellStyle(columnStyle);
					cell0.setCellValue(v+1);

					HSSFCell cell1 = row.createCell(1);
					cell1.setCellStyle(columnStyle);
					if(null!=entity.getCreateDate()){
						cell1.setCellValue(DateFormatUtils.format(entity.getCreateDate(), "yyyy-MM-dd"));
					}



					HSSFCell cell2 = row.createCell(2);
					cell2.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getCustomerName())){
						cell2.setCellValue(entity.getCustomerName());
					}

					HSSFCell cell3 = row.createCell(3);
					cell3.setCellStyle(columnStyle);

						cell3.setCellValue(entity.getXiaoqu()+"-"+entity.getLou()+"-"+entity.getDanyuan()+"-"+entity.getShi());


					HSSFCell cell4 = row.createCell(4);
					cell4.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getProjectMode())){
						cell4.setCellValue(entity.getProjectMode().equals("1")?"产业":"传统");
					}

					HSSFCell cell5 = row.createCell(5);
					cell5.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getEngineDepartName())){
						cell5.setCellValue(entity.getEngineDepartName());
					}

					HSSFCell cell6 = row.createCell(6);
					cell6.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getOrderInspectorName())){
						cell6.setCellValue(entity.getOrderInspectorName());
					}

					HSSFCell cell7 = row.createCell(7);
					cell7.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getManagerName())){
						cell7.setCellValue(entity.getManagerName());
					}

					HSSFCell cell8 = row.createCell(8);
					cell8.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getWorkerGroupLeaderName())){
						cell8.setCellValue(entity.getWorkerGroupLeaderName());
					}

					HSSFCell cell9 = row.createCell(9);
					cell9.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getIllegalName())){
						Set<Map.Entry<String, String>> entrySet = transferMap.entrySet();
						final Iterator<Map.Entry<String, String>> iterator = entrySet.iterator();
						while(iterator.hasNext()){
							Map.Entry<String, String> entry=iterator.next();
							System.out.println(entry.getKey());
							System.out.println(entry.getValue());
							System.out.println("集合中的key value=======================================");
							if(entity.getIllegalName().contains(entry.getKey())) {
								System.out.println(entity.getIllegalName());
								System.out.println(entry.getKey());
								System.out.println("包含的语句=======================================");

								entity.setIllegalName(entity.getIllegalName().replace(entry.getKey(), entry.getValue()));
								System.out.println(entity.getIllegalName());

							}

						}


						cell9.setCellValue(entity.getIllegalName());
					}

					HSSFCell cell10 = row.createCell(10);
					cell10.setCellStyle(columnStyle);
					if(null!=entity.getPunishMoney()&&entity.getPunishMoney()!=0){
						cell10.setCellValue(entity.getPunishMoney());
					}

					HSSFCell cell11 = row.createCell(11);
					cell11.setCellStyle(columnStyle);
					if(null!=entity.getWorkerMoney()&&entity.getWorkerMoney()!=0){
						cell11.setCellValue(entity.getWorkerMoney());
					}

					HSSFCell cell12 = row.createCell(12);
					cell12.setCellStyle(columnStyle);
					if(null!=entity.getInspectorMoney()&&entity.getInspectorMoney()!=0){
						cell12.setCellValue(entity.getInspectorMoney());
					}


				}




			}



			 return workbook;

	}
	
}
