
package cn.damei.service.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizProjectProgressBoningTraditionOrderDao;
import cn.damei.entity.modules.BizNodePlanProject;
import cn.damei.entity.modules.BizTraditionOrder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional(readOnly = true)
public class BizProjectProgressBoningTraditionOrderService extends CrudService2<BizProjectProgressBoningTraditionOrderDao, BizTraditionOrder> {

	public BizTraditionOrder get(Integer id) {
		return super.get(id);
	}
	
	public List<BizTraditionOrder> findList(BizTraditionOrder bizTraditionOrder) {
		return super.findList(bizTraditionOrder);
	}
	
	public Page<BizTraditionOrder> findPage(Page<BizTraditionOrder> page, BizTraditionOrder bizTraditionOrder) {
		return super.findPage(page, bizTraditionOrder);
	}
	

	public List<BizNodePlanProject> findPlanList(Integer orderId) {
		return dao.findPlanList(orderId);
	}
	

	public HSSFWorkbook exportExcel(BizTraditionOrder bizTraditionOrder) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("传统订单工程进度大看板");


		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 6000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 3000);
		sheet.setColumnWidth(11, 3000);
		sheet.setColumnWidth(12, 3000);
		sheet.setColumnWidth(13, 3000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		sheet.setColumnWidth(16, 3000);
		sheet.setColumnWidth(17, 3000);
		sheet.setColumnWidth(18, 3000);
		sheet.setColumnWidth(19, 3000);
		sheet.setColumnWidth(20, 3000);
		sheet.setColumnWidth(21, 3000);
		sheet.setColumnWidth(22, 3000);
		sheet.setColumnWidth(23, 3000);
		sheet.setColumnWidth(24, 3000);
		sheet.setColumnWidth(25, 3000);
		sheet.setColumnWidth(26, 3000);
		sheet.setColumnWidth(27, 3000);
		sheet.setColumnWidth(28, 3000);
		sheet.setColumnWidth(29, 3000);
		sheet.setColumnWidth(30, 3000);
		sheet.setColumnWidth(31, 3000);
		sheet.setColumnWidth(32, 3000);
		sheet.setColumnWidth(33, 3000);
		sheet.setColumnWidth(34, 3000);
		sheet.setColumnWidth(35, 3000);
		sheet.setColumnWidth(36, 3000);
		sheet.setColumnWidth(37, 3000);
		sheet.setColumnWidth(38, 3000);
		sheet.setColumnWidth(39, 3000);
		sheet.setColumnWidth(40, 3000);
		sheet.setColumnWidth(41, 3000);
		sheet.setColumnWidth(42, 3000);
		sheet.setColumnWidth(43, 3000);
		sheet.setColumnWidth(44, 3000);
		sheet.setColumnWidth(45, 3000);
		sheet.setColumnWidth(46, 3000);
		sheet.setColumnWidth(47, 3000);
		sheet.setColumnWidth(48, 3000);
		sheet.setColumnWidth(49, 3000);
		sheet.setColumnWidth(50, 3000);
		sheet.setColumnWidth(51, 3000);
		sheet.setColumnWidth(52, 3000);
		sheet.setColumnWidth(53, 3000);
		sheet.setColumnWidth(54, 3000);
		sheet.setColumnWidth(55, 3000);
		sheet.setColumnWidth(56, 3000);
		sheet.setColumnWidth(57, 3000);
		sheet.setColumnWidth(58, 3000);
		sheet.setColumnWidth(59, 3000);
		sheet.setColumnWidth(60, 3000);
		sheet.setColumnWidth(61, 3000);
		sheet.setColumnWidth(62, 3000);
		sheet.setColumnWidth(63, 3000);
		sheet.setColumnWidth(64, 3000);
		sheet.setColumnWidth(65, 3000);
		sheet.setColumnWidth(66, 3000);
		sheet.setColumnWidth(67, 3000);
		sheet.setColumnWidth(68, 3000);
		sheet.setColumnWidth(69, 3000);
		sheet.setColumnWidth(70, 3000);
		sheet.setColumnWidth(71, 3000);
		sheet.setColumnWidth(72, 3000);
		sheet.setColumnWidth(73, 3000);
		sheet.setColumnWidth(74, 3000);
		sheet.setColumnWidth(75, 3000);
		sheet.setColumnWidth(76, 3000);
		sheet.setColumnWidth(77, 3000);
		sheet.setColumnWidth(78, 3000);
		sheet.setColumnWidth(79, 3000);
		sheet.setColumnWidth(80, 3000);
		sheet.setColumnWidth(81, 3000);
		sheet.setColumnWidth(82, 3000);
		sheet.setColumnWidth(83, 3000);
		sheet.setColumnWidth(84, 3000);
		sheet.setColumnWidth(85, 3000);
		sheet.setColumnWidth(86, 3000);
		sheet.setColumnWidth(87, 3000);
		

		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setBorderLeft((short) 1);
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setBorderRight((short) 1);
		columnHeadStyle.setTopBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setBorderTop((short) 1);
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setBorderBottom((short) 1);
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		columnHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);		
		
		

		HSSFCellStyle columnStyle = wb.createCellStyle();
		columnStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderLeft((short) 1);
		columnStyle.setRightBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderRight((short) 1);
		columnStyle.setTopBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderTop((short) 1);
		columnStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderBottom((short) 1);
		columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				


		HSSFRow rowTitle1 = sheet.createRow(0);
		rowTitle1.setHeightInPoints(30);
		
		HSSFCell headCell0 = rowTitle1.createCell(0);
		headCell0.setCellStyle(columnHeadStyle);
		headCell0.setCellValue("门店");

		HSSFCell headCell1 = rowTitle1.createCell(1);
		headCell1.setCellStyle(columnHeadStyle);
		headCell1.setCellValue("区域");

		HSSFCell headCell2 = rowTitle1.createCell(2);
		headCell2.setCellStyle(columnHeadStyle);
		headCell2.setCellValue("片区");
		
		HSSFCell headCell3 = rowTitle1.createCell(3);
		headCell3.setCellStyle(columnHeadStyle);
		headCell3.setCellValue("新老房");

		HSSFCell headCell4 = rowTitle1.createCell(4);
		headCell4.setCellStyle(columnHeadStyle);
		headCell4.setCellValue("接单日期");

		HSSFCell headCell5 = rowTitle1.createCell(5);
		headCell5.setCellStyle(columnHeadStyle);
		headCell5.setCellValue("客户姓名");

		HSSFCell headCell6 = rowTitle1.createCell(6);
		headCell6.setCellStyle(columnHeadStyle);
		headCell6.setCellValue("客户电话");

		HSSFCell headCell7 = rowTitle1.createCell(7);
		headCell7.setCellStyle(columnHeadStyle);
		headCell7.setCellValue("工程地址");

		HSSFCell headCell8 = rowTitle1.createCell(8);
		headCell8.setCellStyle(columnHeadStyle);
		headCell8.setCellValue("项目经理");

		HSSFCell headCell9 = rowTitle1.createCell(9);
		headCell9.setCellStyle(columnHeadStyle);

		HSSFCell headCell10 = rowTitle1.createCell(10);
		headCell10.setCellStyle(columnHeadStyle);
		headCell10.setCellValue("设计师");
		
		HSSFCell headCell11 = rowTitle1.createCell(11);
		headCell11.setCellStyle(columnHeadStyle);

		HSSFCell headCell12 = rowTitle1.createCell(12);
		headCell12.setCellStyle(columnHeadStyle);
		headCell12.setCellValue("质检员");
		
		HSSFCell headCell13 = rowTitle1.createCell(13);
		headCell13.setCellStyle(columnHeadStyle);

		HSSFCell headCell14 = rowTitle1.createCell(14);
		headCell14.setCellStyle(columnHeadStyle);
		headCell14.setCellValue("合同工期时间");

		HSSFCell headCell15 = rowTitle1.createCell(15);
		headCell15.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell16 = rowTitle1.createCell(16);
		headCell16.setCellStyle(columnHeadStyle);
		headCell16.setCellValue("实际工期时间");
		
		HSSFCell headCell17 = rowTitle1.createCell(17);
		headCell17.setCellStyle(columnHeadStyle);

		HSSFCell headCell18 = rowTitle1.createCell(18);
		headCell18.setCellStyle(columnHeadStyle);
		headCell18.setCellValue("开工延期天数");
		
		HSSFCell headCell19 = rowTitle1.createCell(19);
		headCell19.setCellStyle(columnHeadStyle);
		headCell19.setCellValue("实际开工客户是否签字");
		
		HSSFCell headCell20 = rowTitle1.createCell(20);
		headCell20.setCellStyle(columnHeadStyle);
		headCell20.setCellValue("变更/停电/停水/客户自装项目延期天数");
		
		HSSFCell headCell21 = rowTitle1.createCell(21);
		headCell21.setCellStyle(columnHeadStyle);
		headCell21.setCellValue("客户是否签字");
		
		HSSFCell headCell22 = rowTitle1.createCell(22);
		headCell22.setCellStyle(columnHeadStyle);
		headCell22.setCellValue("主材第一次核尺");
		
		HSSFCell headCell23 = rowTitle1.createCell(23);
		headCell23.setCellStyle(columnHeadStyle);

		HSSFCell headCell24 = rowTitle1.createCell(24);
		headCell24.setCellStyle(columnHeadStyle);

		HSSFCell headCell25 = rowTitle1.createCell(25);
		headCell25.setCellStyle(columnHeadStyle);
		headCell25.setCellValue("辅材进场");
		
		HSSFCell headCell26 = rowTitle1.createCell(26);
		headCell26.setCellStyle(columnHeadStyle);

		HSSFCell headCell27 = rowTitle1.createCell(27);
		headCell27.setCellStyle(columnHeadStyle);

		HSSFCell headCell28 = rowTitle1.createCell(28);
		headCell28.setCellStyle(columnHeadStyle);
		headCell28.setCellValue("瓷砖");
		
		HSSFCell headCell29 = rowTitle1.createCell(29);
		headCell29.setCellStyle(columnHeadStyle);

		HSSFCell headCell30 = rowTitle1.createCell(30);
		headCell30.setCellStyle(columnHeadStyle);

		HSSFCell headCell31 = rowTitle1.createCell(31);
		headCell31.setCellStyle(columnHeadStyle);
		headCell31.setCellValue("水电隐蔽验收");
		
		HSSFCell headCell32 = rowTitle1.createCell(32);
		headCell32.setCellStyle(columnHeadStyle);

		HSSFCell headCell33 = rowTitle1.createCell(33);
		headCell33.setCellStyle(columnHeadStyle);

		HSSFCell headCell34 = rowTitle1.createCell(34);
		headCell34.setCellStyle(columnHeadStyle);
		headCell34.setCellValue("防水验收");
		
		HSSFCell headCell35 = rowTitle1.createCell(35);
		headCell35.setCellStyle(columnHeadStyle);

		HSSFCell headCell36 = rowTitle1.createCell(36);
		headCell36.setCellStyle(columnHeadStyle);

		HSSFCell headCell37 = rowTitle1.createCell(37);
		headCell37.setCellStyle(columnHeadStyle);
		headCell37.setCellValue("橱柜核尺");
		
		HSSFCell headCell38 = rowTitle1.createCell(38);
		headCell38.setCellStyle(columnHeadStyle);

		HSSFCell headCell39 = rowTitle1.createCell(39);
		headCell39.setCellStyle(columnHeadStyle);

		HSSFCell headCell40 = rowTitle1.createCell(40);
		headCell40.setCellStyle(columnHeadStyle);
		headCell40.setCellValue("瓦工验收");
		
		HSSFCell headCell41 = rowTitle1.createCell(41);
		headCell41.setCellStyle(columnHeadStyle);

		HSSFCell headCell42 = rowTitle1.createCell(42);
		headCell42.setCellStyle(columnHeadStyle);

		HSSFCell headCell43 = rowTitle1.createCell(43);
		headCell43.setCellStyle(columnHeadStyle);
		headCell43.setCellValue("二期款（同瓦工验收日期）");
		
		HSSFCell headCell44 = rowTitle1.createCell(44);
		headCell44.setCellStyle(columnHeadStyle);

		HSSFCell headCell45 = rowTitle1.createCell(45);
		headCell45.setCellStyle(columnHeadStyle);

		HSSFCell headCell46 = rowTitle1.createCell(46);
		headCell46.setCellStyle(columnHeadStyle);
		headCell46.setCellValue("基础施工验收");
		
		HSSFCell headCell47 = rowTitle1.createCell(47);
		headCell47.setCellStyle(columnHeadStyle);

		HSSFCell headCell48 = rowTitle1.createCell(48);
		headCell48.setCellStyle(columnHeadStyle);

		HSSFCell headCell49 = rowTitle1.createCell(49);
		headCell49.setCellStyle(columnHeadStyle);
		headCell49.setCellValue("厨卫吊顶");
		
		HSSFCell headCell50 = rowTitle1.createCell(50);
		headCell50.setCellStyle(columnHeadStyle);

		HSSFCell headCell51 = rowTitle1.createCell(51);
		headCell51.setCellStyle(columnHeadStyle);

		HSSFCell headCell52 = rowTitle1.createCell(52);
		headCell52.setCellStyle(columnHeadStyle);
		headCell52.setCellValue("洁具");
		
		HSSFCell headCell53 = rowTitle1.createCell(53);
		headCell53.setCellStyle(columnHeadStyle);

		HSSFCell headCell54 = rowTitle1.createCell(54);
		headCell54.setCellStyle(columnHeadStyle);

		HSSFCell headCell55 = rowTitle1.createCell(55);
		headCell55.setCellStyle(columnHeadStyle);
		headCell55.setCellValue("五金，灯具，开关面板");
		
		HSSFCell headCell56 = rowTitle1.createCell(56);
		headCell56.setCellStyle(columnHeadStyle);

		HSSFCell headCell57 = rowTitle1.createCell(57);
		headCell57.setCellStyle(columnHeadStyle);

		HSSFCell headCell58 = rowTitle1.createCell(58);
		headCell58.setCellStyle(columnHeadStyle);
		headCell58.setCellValue("橱柜");
		
		HSSFCell headCell59 = rowTitle1.createCell(59);
		headCell59.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell60 = rowTitle1.createCell(60);
		headCell60.setCellStyle(columnHeadStyle);

		HSSFCell headCell61 = rowTitle1.createCell(61);
		headCell61.setCellStyle(columnHeadStyle);
		headCell61.setCellValue("定制衣柜");
		
		HSSFCell headCell62 = rowTitle1.createCell(62);
		headCell62.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell63 = rowTitle1.createCell(63);
		headCell63.setCellStyle(columnHeadStyle);

		HSSFCell headCell64 = rowTitle1.createCell(64);
		headCell64.setCellStyle(columnHeadStyle);
		headCell64.setCellValue("壁纸");
		
		HSSFCell headCell65 = rowTitle1.createCell(65);
		headCell65.setCellStyle(columnHeadStyle);

		HSSFCell headCell66 = rowTitle1.createCell(66);
		headCell66.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell67 = rowTitle1.createCell(67);
		headCell67.setCellStyle(columnHeadStyle);
		headCell67.setCellValue("木门，铝镁门，门窗套");
		
		HSSFCell headCell68 = rowTitle1.createCell(68);
		headCell68.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell69 = rowTitle1.createCell(69);
		headCell69.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell70 = rowTitle1.createCell(70);
		headCell70.setCellStyle(columnHeadStyle);
		headCell70.setCellValue("木地板");
		
		HSSFCell headCell71 = rowTitle1.createCell(71);
		headCell71.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell72 = rowTitle1.createCell(72);
		headCell72.setCellStyle(columnHeadStyle);

		HSSFCell headCell73 = rowTitle1.createCell(73);
		headCell73.setCellStyle(columnHeadStyle);
		headCell73.setCellValue("窗帘");
		
		HSSFCell headCell74 = rowTitle1.createCell(74);
		headCell74.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell75 = rowTitle1.createCell(75);
		headCell75.setCellStyle(columnHeadStyle);

		HSSFCell headCell76 = rowTitle1.createCell(76);
		headCell76.setCellStyle(columnHeadStyle);
		headCell76.setCellValue("竣工验收");
		
		HSSFCell headCell77 = rowTitle1.createCell(77);
		headCell77.setCellStyle(columnHeadStyle);

		HSSFCell headCell78 = rowTitle1.createCell(78);
		headCell78.setCellStyle(columnHeadStyle);

		HSSFCell headCell79 = rowTitle1.createCell(79);
		headCell79.setCellStyle(columnHeadStyle);
		headCell79.setCellValue("尾款");
		
		HSSFCell headCell80 = rowTitle1.createCell(80);
		headCell80.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell81 = rowTitle1.createCell(81);
		headCell81.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell82 = rowTitle1.createCell(82);
		headCell82.setCellStyle(columnHeadStyle);
		headCell82.setCellValue("家电");
		
		HSSFCell headCell83 = rowTitle1.createCell(83);
		headCell83.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell84 = rowTitle1.createCell(84);
		headCell84.setCellStyle(columnHeadStyle);
		
		HSSFCell headCell85 = rowTitle1.createCell(85);
		headCell85.setCellStyle(columnHeadStyle);
		headCell85.setCellValue("家具");
		
		HSSFCell headCell86 = rowTitle1.createCell(86);
		headCell86.setCellStyle(columnHeadStyle);

		HSSFCell headCell87 = rowTitle1.createCell(87);
		headCell87.setCellStyle(columnHeadStyle);

		HSSFRow rowTitle2 = sheet.createRow(1);
		rowTitle2.setHeightInPoints(30);
		
		HSSFCell headCellTwo0 = rowTitle2.createCell(0);
		headCellTwo0.setCellStyle(columnHeadStyle);

		HSSFCell headCellTwo1 = rowTitle2.createCell(1);
		headCellTwo1.setCellStyle(columnHeadStyle);

		HSSFCell headCellTwo2 = rowTitle2.createCell(2);
		headCellTwo2.setCellStyle(columnHeadStyle);
		
		HSSFCell headCellTwo3 = rowTitle2.createCell(3);
		headCellTwo3.setCellStyle(columnHeadStyle);

		HSSFCell headCellTwo4 = rowTitle2.createCell(4);
		headCellTwo4.setCellStyle(columnHeadStyle);

		HSSFCell headCellTwo5 = rowTitle2.createCell(5);
		headCellTwo5.setCellStyle(columnHeadStyle);

		HSSFCell headCellTwo6 = rowTitle2.createCell(6);
		headCellTwo6.setCellStyle(columnHeadStyle);

		HSSFCell headCellTwo7 = rowTitle2.createCell(7);
		headCellTwo7.setCellStyle(columnHeadStyle);

		HSSFCell headCellTwo8 = rowTitle2.createCell(8);
		headCellTwo8.setCellStyle(columnHeadStyle);
		headCellTwo8.setCellValue("姓名");
		
		HSSFCell headCellTwo9 = rowTitle2.createCell(9);
		headCellTwo9.setCellStyle(columnHeadStyle);
		headCellTwo9.setCellValue("电话");

		HSSFCell headCellTwo10 = rowTitle2.createCell(10);
		headCellTwo10.setCellStyle(columnHeadStyle);
		headCellTwo10.setCellValue("姓名");

		HSSFCell headCellTwo11 = rowTitle2.createCell(11);
		headCellTwo11.setCellStyle(columnHeadStyle);
		headCellTwo11.setCellValue("电话");

		HSSFCell headCellTwo12 = rowTitle2.createCell(12);
		headCellTwo12.setCellStyle(columnHeadStyle);
		headCellTwo12.setCellValue("姓名");

		HSSFCell headCellTwo13 = rowTitle2.createCell(13);
		headCellTwo13.setCellStyle(columnHeadStyle);
		headCellTwo13.setCellValue("电话");

		HSSFCell headCellTwo14 = rowTitle2.createCell(14);
		headCellTwo14.setCellStyle(columnHeadStyle);
		headCellTwo14.setCellValue("合同签订开工日期");
		
		HSSFCell headCellTwo15 = rowTitle2.createCell(15);
		headCellTwo15.setCellStyle(columnHeadStyle);
		headCellTwo15.setCellValue("合同签订竣工日期");
		
		HSSFCell headCellTwo16 = rowTitle2.createCell(16);
		headCellTwo16.setCellStyle(columnHeadStyle);
		headCellTwo16.setCellValue("实际开工日期");
		
		HSSFCell headCellTwo17 = rowTitle2.createCell(17);
		headCellTwo17.setCellStyle(columnHeadStyle);
		headCellTwo17.setCellValue("实际竣工日期");
		
		HSSFCell headCellTwo18 = rowTitle2.createCell(18);
		headCellTwo18.setCellStyle(columnHeadStyle);

		HSSFCell headCellTwo19 = rowTitle2.createCell(19);
		headCellTwo19.setCellStyle(columnHeadStyle);

		HSSFCell headCellTwo20 = rowTitle2.createCell(20);
		headCellTwo20.setCellStyle(columnHeadStyle);

		HSSFCell headCellTwo21 = rowTitle2.createCell(21);
		headCellTwo21.setCellStyle(columnHeadStyle);

		HSSFCell headCellTwo22 = rowTitle2.createCell(22);
		headCellTwo22.setCellStyle(columnHeadStyle);
		headCellTwo22.setCellValue("计划");

		HSSFCell headCellTwo23 = rowTitle2.createCell(23);
		headCellTwo23.setCellStyle(columnHeadStyle);
		headCellTwo23.setCellValue("实际");

		HSSFCell headCellTwo24 = rowTitle2.createCell(24);
		headCellTwo24.setCellStyle(columnHeadStyle);
		headCellTwo24.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo25 = rowTitle2.createCell(25);
		headCellTwo25.setCellStyle(columnHeadStyle);
		headCellTwo25.setCellValue("计划");

		HSSFCell headCellTwo26 = rowTitle2.createCell(26);
		headCellTwo26.setCellStyle(columnHeadStyle);
		headCellTwo26.setCellValue("实际");

		HSSFCell headCellTwo27 = rowTitle2.createCell(27);
		headCellTwo27.setCellStyle(columnHeadStyle);
		headCellTwo27.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo28 = rowTitle2.createCell(28);
		headCellTwo28.setCellStyle(columnHeadStyle);
		headCellTwo28.setCellValue("计划");

		HSSFCell headCellTwo29 = rowTitle2.createCell(29);
		headCellTwo29.setCellStyle(columnHeadStyle);
		headCellTwo29.setCellValue("实际");

		HSSFCell headCellTwo30 = rowTitle2.createCell(30);
		headCellTwo30.setCellStyle(columnHeadStyle);
		headCellTwo30.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo31 = rowTitle2.createCell(31);
		headCellTwo31.setCellStyle(columnHeadStyle);
		headCellTwo31.setCellValue("计划");

		HSSFCell headCellTwo32 = rowTitle2.createCell(32);
		headCellTwo32.setCellStyle(columnHeadStyle);
		headCellTwo32.setCellValue("实际");

		HSSFCell headCellTwo33 = rowTitle2.createCell(33);
		headCellTwo33.setCellStyle(columnHeadStyle);
		headCellTwo33.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo34 = rowTitle2.createCell(34);
		headCellTwo34.setCellStyle(columnHeadStyle);
		headCellTwo34.setCellValue("计划");

		HSSFCell headCellTwo35 = rowTitle2.createCell(35);
		headCellTwo35.setCellStyle(columnHeadStyle);
		headCellTwo35.setCellValue("实际");

		HSSFCell headCellTwo36 = rowTitle2.createCell(36);
		headCellTwo36.setCellStyle(columnHeadStyle);
		headCellTwo36.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo37 = rowTitle2.createCell(37);
		headCellTwo37.setCellStyle(columnHeadStyle);
		headCellTwo37.setCellValue("计划");

		HSSFCell headCellTwo38 = rowTitle2.createCell(38);
		headCellTwo38.setCellStyle(columnHeadStyle);
		headCellTwo38.setCellValue("实际");

		HSSFCell headCellTwo39 = rowTitle2.createCell(39);
		headCellTwo39.setCellStyle(columnHeadStyle);
		headCellTwo39.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo40 = rowTitle2.createCell(40);
		headCellTwo40.setCellStyle(columnHeadStyle);
		headCellTwo40.setCellValue("计划");

		HSSFCell headCellTwo41 = rowTitle2.createCell(41);
		headCellTwo41.setCellStyle(columnHeadStyle);
		headCellTwo41.setCellValue("实际");

		HSSFCell headCellTwo42 = rowTitle2.createCell(42);
		headCellTwo42.setCellStyle(columnHeadStyle);
		headCellTwo42.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo43 = rowTitle2.createCell(43);
		headCellTwo43.setCellStyle(columnHeadStyle);
		headCellTwo43.setCellValue("计划");

		HSSFCell headCellTwo44 = rowTitle2.createCell(44);
		headCellTwo44.setCellStyle(columnHeadStyle);
		headCellTwo44.setCellValue("实际");

		HSSFCell headCellTwo45 = rowTitle2.createCell(45);
		headCellTwo45.setCellStyle(columnHeadStyle);
		headCellTwo45.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo46 = rowTitle2.createCell(46);
		headCellTwo46.setCellStyle(columnHeadStyle);
		headCellTwo46.setCellValue("计划");

		HSSFCell headCellTwo47 = rowTitle2.createCell(47);
		headCellTwo47.setCellStyle(columnHeadStyle);
		headCellTwo47.setCellValue("实际");

		HSSFCell headCellTwo48 = rowTitle2.createCell(48);
		headCellTwo48.setCellStyle(columnHeadStyle);
		headCellTwo48.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo49 = rowTitle2.createCell(49);
		headCellTwo49.setCellStyle(columnHeadStyle);
		headCellTwo49.setCellValue("计划");

		HSSFCell headCellTwo50 = rowTitle2.createCell(50);
		headCellTwo50.setCellStyle(columnHeadStyle);
		headCellTwo50.setCellValue("实际");

		HSSFCell headCellTwo51 = rowTitle2.createCell(51);
		headCellTwo51.setCellStyle(columnHeadStyle);
		headCellTwo51.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo52 = rowTitle2.createCell(52);
		headCellTwo52.setCellStyle(columnHeadStyle);
		headCellTwo52.setCellValue("计划");

		HSSFCell headCellTwo53 = rowTitle2.createCell(53);
		headCellTwo53.setCellStyle(columnHeadStyle);
		headCellTwo53.setCellValue("实际");

		HSSFCell headCellTwo54 = rowTitle2.createCell(54);
		headCellTwo54.setCellStyle(columnHeadStyle);
		headCellTwo54.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo55 = rowTitle2.createCell(55);
		headCellTwo55.setCellStyle(columnHeadStyle);
		headCellTwo55.setCellValue("计划");

		HSSFCell headCellTwo56 = rowTitle2.createCell(56);
		headCellTwo56.setCellStyle(columnHeadStyle);
		headCellTwo56.setCellValue("实际");

		HSSFCell headCellTwo57 = rowTitle2.createCell(57);
		headCellTwo57.setCellStyle(columnHeadStyle);
		headCellTwo57.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo58 = rowTitle2.createCell(58);
		headCellTwo58.setCellStyle(columnHeadStyle);
		headCellTwo58.setCellValue("计划");

		HSSFCell headCellTwo59 = rowTitle2.createCell(59);
		headCellTwo59.setCellStyle(columnHeadStyle);
		headCellTwo59.setCellValue("实际");
		
		HSSFCell headCellTwo60 = rowTitle2.createCell(60);
		headCellTwo60.setCellStyle(columnHeadStyle);
		headCellTwo60.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo61 = rowTitle2.createCell(61);
		headCellTwo61.setCellStyle(columnHeadStyle);
		headCellTwo61.setCellValue("计划");;
		
		HSSFCell headCellTwo62 = rowTitle2.createCell(62);
		headCellTwo62.setCellStyle(columnHeadStyle);
		headCellTwo62.setCellValue("实际");
		
		HSSFCell headCellTwo63 = rowTitle2.createCell(63);
		headCellTwo63.setCellStyle(columnHeadStyle);
		headCellTwo63.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo64 = rowTitle2.createCell(64);
		headCellTwo64.setCellStyle(columnHeadStyle);
		headCellTwo64.setCellValue("计划");

		HSSFCell headCellTwo65 = rowTitle2.createCell(65);
		headCellTwo65.setCellStyle(columnHeadStyle);
		headCellTwo65.setCellValue("实际");

		HSSFCell headCellTwo66 = rowTitle2.createCell(66);
		headCellTwo66.setCellStyle(columnHeadStyle);
		headCellTwo66.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo67 = rowTitle2.createCell(67);
		headCellTwo67.setCellStyle(columnHeadStyle);
		headCellTwo67.setCellValue("计划");

		HSSFCell headCellTwo68 = rowTitle2.createCell(68);
		headCellTwo68.setCellStyle(columnHeadStyle);
		headCellTwo68.setCellValue("实际");
		
		HSSFCell headCellTwo69 = rowTitle2.createCell(69);
		headCellTwo69.setCellStyle(columnHeadStyle);
		headCellTwo69.setCellValue("正常/延期/提前天数");
		
		HSSFCell headCellTwo70 = rowTitle2.createCell(70);
		headCellTwo70.setCellStyle(columnHeadStyle);
		headCellTwo70.setCellValue("计划");

		HSSFCell headCellTwo71 = rowTitle2.createCell(71);
		headCellTwo71.setCellStyle(columnHeadStyle);
		headCellTwo71.setCellValue("实际");
		
		HSSFCell headCellTwo72 = rowTitle2.createCell(72);
		headCellTwo72.setCellStyle(columnHeadStyle);
		headCellTwo72.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo73 = rowTitle2.createCell(73);
		headCellTwo73.setCellStyle(columnHeadStyle);
		headCellTwo73.setCellValue("计划");

		HSSFCell headCellTwo74 = rowTitle2.createCell(74);
		headCellTwo74.setCellStyle(columnHeadStyle);
		headCellTwo74.setCellValue("实际");
		
		HSSFCell headCellTwo75 = rowTitle2.createCell(75);
		headCellTwo75.setCellStyle(columnHeadStyle);
		headCellTwo75.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo76 = rowTitle2.createCell(76);
		headCellTwo76.setCellStyle(columnHeadStyle);
		headCellTwo76.setCellValue("计划");

		HSSFCell headCellTwo77 = rowTitle2.createCell(77);
		headCellTwo77.setCellStyle(columnHeadStyle);
		headCellTwo77.setCellValue("实际");

		HSSFCell headCellTwo78 = rowTitle2.createCell(78);
		headCellTwo78.setCellStyle(columnHeadStyle);
		headCellTwo78.setCellValue("正常/延期/提前天数");

		HSSFCell headCellTwo79 = rowTitle2.createCell(79);
		headCellTwo79.setCellStyle(columnHeadStyle);
		headCellTwo79.setCellValue("计划");

		HSSFCell headCellTwo80 = rowTitle2.createCell(80);
		headCellTwo80.setCellStyle(columnHeadStyle);
		headCellTwo80.setCellValue("实际");
		
		HSSFCell headCellTwo81 = rowTitle2.createCell(81);
		headCellTwo81.setCellStyle(columnHeadStyle);
		headCellTwo81.setCellValue("正常/延期/提前天数");
		
		HSSFCell headCellTwo82 = rowTitle2.createCell(82);
		headCellTwo82.setCellStyle(columnHeadStyle);
		headCellTwo82.setCellValue("计划");
		
		HSSFCell headCellTwo83 = rowTitle2.createCell(83);
		headCellTwo83.setCellStyle(columnHeadStyle);
		headCellTwo83.setCellValue("实际");
		
		HSSFCell headCellTwo84 = rowTitle2.createCell(84);
		headCellTwo84.setCellStyle(columnHeadStyle);
		headCellTwo84.setCellValue("正常/延期/提前天数");
		
		HSSFCell headCellTwo85 = rowTitle2.createCell(85);
		headCellTwo85.setCellStyle(columnHeadStyle);
		headCellTwo85.setCellValue("计划");
		
		HSSFCell headCellTwo86 = rowTitle2.createCell(86);
		headCellTwo86.setCellStyle(columnHeadStyle);
		headCellTwo86.setCellValue("实际");
		
		HSSFCell headCellTwo87 = rowTitle2.createCell(87);
		headCellTwo87.setCellStyle(columnHeadStyle);
		headCellTwo87.setCellValue("正常/延期/提前天数");
		

		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 3));
		
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 4, 4));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 5, 5));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 6, 6));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 7, 7));
		
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 9));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 10, 11));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 12, 13));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 14, 15));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 16, 17));
		
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 18, 18));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 19, 19));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 20, 20));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 21, 21));
		
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 22, 24));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 25, 27));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 28, 30));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 31, 33));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 34, 36));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 37, 39));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 40, 42));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 43, 45));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 46, 48));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 49, 51));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 52, 54));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 55, 57));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 58, 60));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 61, 63));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 64, 66));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 67, 69));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 70, 72));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 73, 75));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 76, 78));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 79, 81));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 82, 84));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 85, 87));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		List<BizTraditionOrder> list = dao.findListExcel1(bizTraditionOrder);
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				BizTraditionOrder order = list.get(i);
				HSSFRow row = sheet.createRow(i+2);

				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getStoreName())){
					cell0.setCellValue(order.getStoreName());
				}

				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getEngineDepartName())){
					cell1.setCellValue(order.getEngineDepartName());
				}
				
				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getArea())){
					cell2.setCellValue(order.getArea());
				}

				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getHouseIsNewName())){
					cell3.setCellValue(order.getHouseIsNewName());
				}

				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if(order.getGetOrderDatetime() != null){
					cell4.setCellValue(format.format(order.getGetOrderDatetime()));
				}

				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getCustomerName())){
					cell5.setCellValue(order.getCustomerName());
				}

				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getCustomerPhone())){
					cell6.setCellValue(order.getCustomerPhone());
				}

				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getCommunityName()) || StringUtils.isNoneBlank(order.getBuildNumber()) || StringUtils.isNoneBlank(order.getBuildUnit()) || StringUtils.isNoneBlank(order.getBuildRoom())){
					cell7.setCellValue(order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom());
				}

				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getItemManager())){
					cell8.setCellValue(order.getItemManager());
				}

				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getItemManagerPhone())){
					cell9.setCellValue(order.getItemManagerPhone());
				}
				
				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getDesignerName())){
					cell10.setCellValue(order.getDesignerName());
				}

				HSSFCell cell11 = row.createCell(11);
				cell11.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getDesignerPhone())){
					cell11.setCellValue(order.getDesignerPhone());
				}
				
				HSSFCell cell12 = row.createCell(12);
				cell12.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getInspector())){
					cell12.setCellValue(order.getInspector());
				}
				
				HSSFCell cell13 = row.createCell(13);
				cell13.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getInspectorPhone())){
					cell13.setCellValue(order.getInspectorPhone());
				}
				
				HSSFCell cell14 = row.createCell(14);
				cell14.setCellStyle(columnStyle);
				if(order.getContractStartDate() != null){
					cell14.setCellValue(format.format(order.getContractStartDate()));
				}
				
				HSSFCell cell15 = row.createCell(15);
				cell15.setCellStyle(columnStyle);
				if(order.getContractEndDate() != null){
					cell15.setCellValue(format.format(order.getContractEndDate()));
				}

				HSSFCell cell16 = row.createCell(16);
				cell16.setCellStyle(columnStyle);
				if(order.getActualStartDate() != null){
					cell16.setCellValue(format.format(order.getActualStartDate()));
				}
				
				HSSFCell cell17 = row.createCell(17);
				cell17.setCellStyle(columnStyle);
				if(order.getActualEndDate() != null){
					cell17.setCellValue(format.format(order.getActualEndDate()));
				}

				HSSFCell cell18 = row.createCell(18);
				cell18.setCellStyle(columnStyle);
				if(order.getStartDiffDay() != null){
					cell18.setCellValue(order.getStartDiffDay());
				}
				
				HSSFCell cell19 = row.createCell(19);
				cell19.setCellStyle(columnStyle);
				if("0".equals(order.getIsNeedSign())){
					cell19.setCellValue("否");
				}else if("1".equals(order.getIsNeedSign())){
					cell19.setCellValue("是");
				}
				
				HSSFCell cell20 = row.createCell(20);
				cell20.setCellStyle(columnStyle);
				if(order.getSelfDecorateDelayDays() != null){
					cell20.setCellValue(order.getSelfDecorateDelayDays());
				}
				
				HSSFCell cell21 = row.createCell(21);
				cell21.setCellStyle(columnStyle);
				if("0".equals(order.getIsSelfDecorateNeedSign())){
					cell21.setCellValue("否");
				}else if("1".equals(order.getIsSelfDecorateNeedSign())){
					cell21.setCellValue("是");
				}
				int temp = 21;
				for(int j = 1; j <= 22;j++){
                    for (int s = 1;s <= 3;s++){
                        temp =  temp + 1;
                        if(s == 1){
                            HSSFCell cell22 = row.createCell(temp);
                            cell22.setCellStyle(columnStyle);
                            cell22.setCellValue(order.getPlanDoneDateByList(j-1));
                        }else if(s == 2){
                            HSSFCell cell22 = row.createCell(temp);
                            cell22.setCellStyle(columnStyle);
                            cell22.setCellValue(order.getRealDoneDateByList(j-1));
                        }else{
                            HSSFCell cell22 = row.createCell(temp);
                            cell22.setCellStyle(columnStyle);
                            cell22.setCellValue(order.getPlanDiffDayList(j-1));
                        }
                    }
                }

				/*HSSFCell cell22 = row.createCell(22);
				cell22.setCellStyle(columnStyle);
				HSSFCell cell23 = row.createCell(23);
				cell23.setCellStyle(columnStyle);
				HSSFCell cell24 = row.createCell(24);
				cell24.setCellStyle(columnStyle);
				HSSFCell cell25 = row.createCell(25);
				cell25.setCellStyle(columnStyle);
				HSSFCell cell26 = row.createCell(26);
				cell26.setCellStyle(columnStyle);
				HSSFCell cell27 = row.createCell(27);
				cell27.setCellStyle(columnStyle);
				HSSFCell cell28 = row.createCell(28);
				cell28.setCellStyle(columnStyle);
				HSSFCell cell29 = row.createCell(29);
				cell29.setCellStyle(columnStyle);
				HSSFCell cell30 = row.createCell(30);
				cell30.setCellStyle(columnStyle);
				HSSFCell cell31 = row.createCell(31);
				cell31.setCellStyle(columnStyle);
				HSSFCell cell32 = row.createCell(32);
				cell32.setCellStyle(columnStyle);
				HSSFCell cell33 = row.createCell(33);
				cell33.setCellStyle(columnStyle);
				HSSFCell cell34 = row.createCell(34);
				cell34.setCellStyle(columnStyle);
				HSSFCell cell35 = row.createCell(35);
				cell35.setCellStyle(columnStyle);
				HSSFCell cell36 = row.createCell(36);
				cell36.setCellStyle(columnStyle);
				HSSFCell cell37 = row.createCell(37);
				cell37.setCellStyle(columnStyle);
				HSSFCell cell38 = row.createCell(38);
				cell38.setCellStyle(columnStyle);
				HSSFCell cell39 = row.createCell(39);
				cell39.setCellStyle(columnStyle);
				HSSFCell cell40 = row.createCell(40);
				cell40.setCellStyle(columnStyle);
				HSSFCell cell41 = row.createCell(41);
				cell41.setCellStyle(columnStyle);
				HSSFCell cell42 = row.createCell(42);
				cell42.setCellStyle(columnStyle);
				HSSFCell cell43 = row.createCell(43);
				cell43.setCellStyle(columnStyle);
				HSSFCell cell44 = row.createCell(44);
				cell44.setCellStyle(columnStyle);
				HSSFCell cell45 = row.createCell(45);
				cell45.setCellStyle(columnStyle);
				HSSFCell cell46 = row.createCell(46);
				cell46.setCellStyle(columnStyle);
				HSSFCell cell47 = row.createCell(47);
				cell47.setCellStyle(columnStyle);
				HSSFCell cell48 = row.createCell(48);
				cell48.setCellStyle(columnStyle);
				HSSFCell cell49 = row.createCell(49);
				cell49.setCellStyle(columnStyle);
				HSSFCell cell50 = row.createCell(50);
				cell50.setCellStyle(columnStyle);
				HSSFCell cell51 = row.createCell(51);
				cell51.setCellStyle(columnStyle);
				HSSFCell cell52 = row.createCell(52);
				cell52.setCellStyle(columnStyle);
				HSSFCell cell53 = row.createCell(53);
				cell53.setCellStyle(columnStyle);
				HSSFCell cell54 = row.createCell(54);
				cell54.setCellStyle(columnStyle);
				HSSFCell cell55 = row.createCell(55);
				cell55.setCellStyle(columnStyle);
				HSSFCell cell56 = row.createCell(56);
				cell56.setCellStyle(columnStyle);
				HSSFCell cell57 = row.createCell(57);
				cell57.setCellStyle(columnStyle);
				HSSFCell cell58 = row.createCell(58);
				cell58.setCellStyle(columnStyle);
				HSSFCell cell59 = row.createCell(59);
				cell59.setCellStyle(columnStyle);
				HSSFCell cell60 = row.createCell(60);
				cell60.setCellStyle(columnStyle);
				HSSFCell cell61 = row.createCell(61);
				cell61.setCellStyle(columnStyle);
				HSSFCell cell62 = row.createCell(62);
				cell62.setCellStyle(columnStyle);
				HSSFCell cell63 = row.createCell(63);
				cell63.setCellStyle(columnStyle);
				HSSFCell cell64 = row.createCell(64);
				cell64.setCellStyle(columnStyle);
				HSSFCell cell65 = row.createCell(65);
				cell65.setCellStyle(columnStyle);
				HSSFCell cell66 = row.createCell(66);
				cell66.setCellStyle(columnStyle);
				HSSFCell cell67 = row.createCell(67);
				cell67.setCellStyle(columnStyle);
				HSSFCell cell68 = row.createCell(68);
				cell68.setCellStyle(columnStyle);
				HSSFCell cell69 = row.createCell(69);
				cell69.setCellStyle(columnStyle);
				HSSFCell cell70 = row.createCell(70);
				cell70.setCellStyle(columnStyle);
				HSSFCell cell71 = row.createCell(71);
				cell71.setCellStyle(columnStyle);
				HSSFCell cell72 = row.createCell(72);
				cell72.setCellStyle(columnStyle);
				HSSFCell cell73 = row.createCell(73);
				cell73.setCellStyle(columnStyle);
				HSSFCell cell74 = row.createCell(74);
				cell74.setCellStyle(columnStyle);
				HSSFCell cell75 = row.createCell(75);
				cell75.setCellStyle(columnStyle);
				HSSFCell cell76 = row.createCell(76);
				cell76.setCellStyle(columnStyle);
				HSSFCell cell77 = row.createCell(77);
				cell77.setCellStyle(columnStyle);
				HSSFCell cell78 = row.createCell(78);
				cell78.setCellStyle(columnStyle);
				HSSFCell cell79 = row.createCell(79);
				cell79.setCellStyle(columnStyle);
				HSSFCell cell80 = row.createCell(80);
				cell80.setCellStyle(columnStyle);
				HSSFCell cell81 = row.createCell(81);
				cell81.setCellStyle(columnStyle);
				HSSFCell cell82 = row.createCell(82);
				cell82.setCellStyle(columnStyle);
				HSSFCell cell83 = row.createCell(83);
				cell83.setCellStyle(columnStyle);
				HSSFCell cell84 = row.createCell(84);
				cell84.setCellStyle(columnStyle);
				HSSFCell cell85 = row.createCell(85);
				cell85.setCellStyle(columnStyle);
				HSSFCell cell86 = row.createCell(86);
				cell86.setCellStyle(columnStyle);
				HSSFCell cell87 = row.createCell(87);
				cell87.setCellStyle(columnStyle);
				List<BizNodePlanProject> planList = order.getNodePlanList();
				if(null!=planList && planList.size()>0){
					for(BizNodePlanProject a :planList){
						if("1".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell22.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell23.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell24.setCellValue(a.getPlanDiffDay());
							}
						}
						if("2".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell25.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell26.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell27.setCellValue(a.getPlanDiffDay());
							}
						}
						if("3".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell28.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell29.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell30.setCellValue(a.getPlanDiffDay());
							}
						}
						if("4".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell31.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell32.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell33.setCellValue(a.getPlanDiffDay());
							}
						}
						if("5".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell34.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell35.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell36.setCellValue(a.getPlanDiffDay());
							}
						}
						if("6".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell37.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell38.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell39.setCellValue(a.getPlanDiffDay());
							}
						}
						if("7".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell40.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell41.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell42.setCellValue(a.getPlanDiffDay());
							}
						}
						if("8".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell43.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell44.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell45.setCellValue(a.getPlanDiffDay());
							}
						}
						if("9".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell46.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell47.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell48.setCellValue(a.getPlanDiffDay());
							}
						}
						if("10".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell49.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell50.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell51.setCellValue(a.getPlanDiffDay());
							}
						}
						if("11".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell52.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell53.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell54.setCellValue(a.getPlanDiffDay());
							}
						}
						if("12".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell55.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell56.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell57.setCellValue(a.getPlanDiffDay());
							}
						}
						if("13".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell58.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell59.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell60.setCellValue(a.getPlanDiffDay());
							}
						}
						if("14".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell61.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell62.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell63.setCellValue(a.getPlanDiffDay());
							}
						}
						if("15".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell64.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell65.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell66.setCellValue(a.getPlanDiffDay());
							}
						}
						if("16".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell67.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell68.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell69.setCellValue(a.getPlanDiffDay());
							}
						}
						if("17".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell70.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell71.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell72.setCellValue(a.getPlanDiffDay());
							}
						}
						if("18".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell73.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell74.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell75.setCellValue(a.getPlanDiffDay());
							}
						}
						if("19".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell76.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell77.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell78.setCellValue(a.getPlanDiffDay());
							}
						}
						if("20".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell79.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell80.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell81.setCellValue(a.getPlanDiffDay());
							}
						}
						if("21".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell82.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell83.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell84.setCellValue(a.getPlanDiffDay());
							}
						}
						if("22".equals(a.getNodeIndex())){
							if(a.getPlanDoneDate() != null){
								cell85.setCellValue(format.format(a.getPlanDoneDate()));
							}
							if(a.getRealDoneDate() != null){
								cell86.setCellValue(format.format(a.getRealDoneDate()));
							}
							if(a.getPlanDiffDay() != null){
								cell87.setCellValue(a.getPlanDiffDay());
							}
						}*/
						
						
					/*}*/
				/*}*/
				
			}
		}

		return wb;
	}

	
}