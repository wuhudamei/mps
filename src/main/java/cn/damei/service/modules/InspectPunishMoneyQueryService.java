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

		/**
		 * 特殊字符的转义
		 *&nbsp; &#160; 空格
		 & &amp; &#38; and符号，与
		 " &quot; &#34; 引号
		 © &copy; &#169; 版权标志
		 ® &reg; &#187; 注册标志
		 ™ &trade; &#153; 商标标志
		 “ &ldquo; &#147; 左双引号
		 ” &rdquo; &#148; 右双引号
		 ‘ &lsquo; &#145; 做单引号
		 ’ &rsquo; &#146; 右单引号
		 « &laquo; &#171; 左三角双引号
		 » &raquo; &#187; 右三角双引号
		 ‹ &lsaquo; &#8249; 左三角单引号
		 › &rsaquo; &#8250; 右三角单引号
		 § &sect; &#167; 章节标志
		 ¶ &para; &#182; 段落标志
		 • &bull; &#149; 列表圆点（大）
		 • &middot; &#183; 列表圆点（中）
		 … &hellip; &#8230; 省略号
		 | &#124; 竖线
		 ¦ &brvbar; &#166; 断的竖线
		 – &ndash; &#150; 短破折号
		 — &mdash; &#151; 长破折号
		 <= &le;   小于等于
		 >= &ge;   大于等于
		 */
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

		//map的 size
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



			//标题---订单信息
			HSSFRow rowTitle = sheet.createRow(0);
			rowTitle.setHeightInPoints(30);
			HSSFCell cell = rowTitle.createCell(0);
			cell.setCellStyle(columnStyle);
			cell.setCellValue(new HSSFRichTextString("北京美得你工程部质检处工地处罚通报表("+startDate+"-"+endDate+")"));

			for(int i=0;i<12;i++){
				HSSFCell cella = rowTitle.createCell(i+1);
				cella.setCellStyle(columnStyle);
			}

			//合并单元格--开始行，结束行，开始列，结束列
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));//


			//标题
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
					//从第三行开始
					HSSFRow row = sheet.createRow(v+2);

					//第一个是序号
					HSSFCell cell0 = row.createCell(0);
					cell0.setCellStyle(columnStyle);
					cell0.setCellValue(v+1);

					HSSFCell cell1 = row.createCell(1);
					cell1.setCellStyle(columnStyle);
					if(null!=entity.getCreateDate()){
						cell1.setCellValue(DateFormatUtils.format(entity.getCreateDate(), "yyyy-MM-dd"));
					}


					//客户姓名
					HSSFCell cell2 = row.createCell(2);
					cell2.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getCustomerName())){
						cell2.setCellValue(entity.getCustomerName());
					}
					//客户地址
					HSSFCell cell3 = row.createCell(3);
					cell3.setCellStyle(columnStyle);

						cell3.setCellValue(entity.getXiaoqu()+"-"+entity.getLou()+"-"+entity.getDanyuan()+"-"+entity.getShi());

					//工程模式
					HSSFCell cell4 = row.createCell(4);
					cell4.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getProjectMode())){
						cell4.setCellValue(entity.getProjectMode().equals("1")?"产业":"传统");
					}
					//区域
					HSSFCell cell5 = row.createCell(5);
					cell5.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getEngineDepartName())){
						cell5.setCellValue(entity.getEngineDepartName());
					}
					//质检员
					HSSFCell cell6 = row.createCell(6);
					cell6.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getOrderInspectorName())){
						cell6.setCellValue(entity.getOrderInspectorName());
					}
					//项目经理
					HSSFCell cell7 = row.createCell(7);
					cell7.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getManagerName())){
						cell7.setCellValue(entity.getManagerName());
					}
					//工人
					HSSFCell cell8 = row.createCell(8);
					cell8.setCellStyle(columnStyle);
					if(StringUtils.isNoneBlank(entity.getWorkerGroupLeaderName())){
						cell8.setCellValue(entity.getWorkerGroupLeaderName());
					}
					//原因(违规形式)
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
					//项目经理罚款金额
					HSSFCell cell10 = row.createCell(10);
					cell10.setCellStyle(columnStyle);
					if(null!=entity.getPunishMoney()&&entity.getPunishMoney()!=0){
						cell10.setCellValue(entity.getPunishMoney());
					}
					//工人罚款
					HSSFCell cell11 = row.createCell(11);
					cell11.setCellStyle(columnStyle);
					if(null!=entity.getWorkerMoney()&&entity.getWorkerMoney()!=0){
						cell11.setCellValue(entity.getWorkerMoney());
					}
					//质检罚款
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
