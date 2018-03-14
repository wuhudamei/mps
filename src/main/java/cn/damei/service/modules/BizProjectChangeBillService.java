
package cn.damei.service.modules;

import java.text.DecimalFormat;
import java.util.List;

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
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.DateUtils;
import cn.damei.entity.modules.BizMsg;
import cn.damei.entity.modules.BizProjectChangeBill;
import cn.damei.entity.modules.BizProjectChangeBillItem;
import cn.damei.entity.modules.User;
import cn.damei.dao.modules.BizProjectChangeBillDao;


@Service
@Transactional(readOnly = true)
public class BizProjectChangeBillService extends CrudService<BizProjectChangeBillDao, BizProjectChangeBill> {

	public BizProjectChangeBill get(String id) {
		return super.get(id);
	}
	
	public List<BizProjectChangeBill> findList(BizProjectChangeBill bizProjectChangeBill) {
		return super.findList(bizProjectChangeBill);
	}

	public Page<BizProjectChangeBill> findPage(Page<BizProjectChangeBill> page, BizProjectChangeBill bizProjectChangeBill) {
		return super.findPage(page, bizProjectChangeBill);
	}
	public Page<BizProjectChangeBill> findPage1(Page<BizProjectChangeBill> page, BizProjectChangeBill bizProjectChangeBill) {
		return super.findPage1(page, bizProjectChangeBill);
	}

	@Transactional(readOnly = false)
	public void updateStatus(BizProjectChangeBill bizProjectChangeBill) {
		dao.updateStatus(bizProjectChangeBill);
	}


	public BizProjectChangeBill findDetails(Integer projectChangeId) {
		return dao.findDetails(projectChangeId);
	}


	public List<BizProjectChangeBillItem> findAddItem(Integer projectChangeId) {
		return dao.findAddItem(projectChangeId);
	}


	public List<BizProjectChangeBillItem> findSubItem(Integer projectChangeId) {
		return dao.findSubItem(projectChangeId);
	}


	public HSSFWorkbook projectChangeBillForExcel(Integer projectChangeId) {
	
		DecimalFormat df = new DecimalFormat("#.00");

		BizProjectChangeBill bizProjectChangeBill = dao.findDetails(projectChangeId);

		BizProjectChangeBill applyDetails = dao.findApplyDetails(projectChangeId);

		List<BizProjectChangeBillItem> addItem = dao.findAddItem(projectChangeId);
		if(null!=addItem && addItem.size()>0){
			for(BizProjectChangeBillItem add:addItem){

				Double allPrice = add.getEveryPrice() * add.getProjectIntemAmount();
				allPrice = Double.parseDouble(df.format(allPrice));
				add.setAllPrice(allPrice);
			}
		}

		List<BizProjectChangeBillItem> subItem = dao.findSubItem(projectChangeId);
		if(null!=subItem && subItem.size()>0){
			for(BizProjectChangeBillItem sub:subItem){

				Double allPrice = sub.getEveryPrice() * sub.getProjectIntemAmount();
				allPrice = Double.parseDouble(df.format(allPrice));
				sub.setAllPrice(allPrice);
			}
		}

		double addMoney = bizProjectChangeBill.getAddItemTotalPrice();

		double reducePrice = bizProjectChangeBill.getSubItemTotalPrice();

		double totalMoney = addMoney-reducePrice;
		totalMoney = Double.parseDouble(df.format(totalMoney));
				
				

		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet = wb.createSheet("变更单");


		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);
		
		

		HSSFFont font = wb.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL);
		font.setFontName("黑体");
		font.setFontHeightInPoints((short)10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		columnHeadStyle.setFont(font);
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

		columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);

		
		
		

		HSSFFont font2 = wb.createFont();
		font2.setColor(HSSFFont.COLOR_NORMAL);
		font2.setFontName("黑体");
		font2.setFontHeightInPoints((short)18);
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFCellStyle columnHeadStyle2 = wb.createCellStyle();
		columnHeadStyle2.setFont(font2);
		columnHeadStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		columnHeadStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		columnHeadStyle2.setLocked(true);
		columnHeadStyle2.setWrapText(true);
		columnHeadStyle2.setLeftBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle2.setBorderLeft((short) 1);
		columnHeadStyle2.setRightBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle2.setBorderRight((short) 1);
		columnHeadStyle2.setTopBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle2.setBorderTop((short) 1);
		columnHeadStyle2.setBottomBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle2.setBorderBottom((short) 1);
		columnHeadStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		columnHeadStyle2.setBottomBorderColor(HSSFColor.BLACK.index);


		columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		
		

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
				
				
				
		

		HSSFRow rowTitle = sheet.createRow(0);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnHeadStyle2);
		cell.setCellValue(new HSSFRichTextString("变更单详情"));
		for(int i=0;i<9;i++){
			HSSFCell cella = rowTitle.createCell(i+1);
			cella.setCellStyle(columnHeadStyle2);
		}

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
		
		
		HSSFRow row2 = sheet.createRow(1);
		HSSFCell cellCustName = row2.createCell(0);
		cellCustName.setCellValue("客户姓名：");
		cellCustName.setCellStyle(columnHeadStyle);
		
		HSSFCell cellCustName1 = row2.createCell(1);
		cellCustName1.setCellStyle(columnHeadStyle);
		
		HSSFCell cellCustName2 = row2.createCell(2);
		cellCustName2.setCellValue(bizProjectChangeBill.getCustomerName());
		cellCustName2.setCellStyle(columnStyle);
		
		HSSFCell cellCustName3 = row2.createCell(3);
		cellCustName3.setCellStyle(columnHeadStyle);
		
		HSSFCell cellCustName4 = row2.createCell(4);
		cellCustName4.setCellStyle(columnHeadStyle);
		
		HSSFCell cellCustPhone = row2.createCell(5);
		cellCustPhone.setCellValue("客户电话：");
		cellCustPhone.setCellStyle(columnHeadStyle);
		
		HSSFCell cellCustName6 = row2.createCell(6);
		cellCustName6.setCellStyle(columnHeadStyle);
		
		HSSFCell cellCustName7 = row2.createCell(7);
		cellCustName7.setCellValue(bizProjectChangeBill.getCustomerPhone());
		cellCustName7.setCellStyle(columnStyle);
		
		HSSFCell cellCustName8 = row2.createCell(8);
		cellCustName8.setCellStyle(columnHeadStyle);

		HSSFCell cellCustName9 = row2.createCell(9);
		cellCustName9.setCellStyle(columnHeadStyle);
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 6));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 9));
		
		
		HSSFRow row3 = sheet.createRow(2);
		HSSFCell cellAdd = row3.createCell(0);
		cellAdd.setCellValue("工程地址：");
		cellAdd.setCellStyle(columnHeadStyle);
		
		HSSFCell cellAdd1 = row3.createCell(1);
		cellAdd1.setCellStyle(columnHeadStyle);
		
		HSSFCell cellAdd2 = row3.createCell(2);
		cellAdd2.setCellValue(bizProjectChangeBill.getCommunityName()+"-"+bizProjectChangeBill.getBuildNumber()+"-"+bizProjectChangeBill.getBuildUnit()+"-"+bizProjectChangeBill.getBuildRoom());
		cellAdd2.setCellStyle(columnStyle);
		
		HSSFCell cellAdd3 = row3.createCell(3);
		cellAdd3.setCellStyle(columnHeadStyle);
		
		HSSFCell cellAdd4 = row3.createCell(4);
		cellAdd4.setCellStyle(columnHeadStyle);
		
		
		HSSFCell cellArea = row3.createCell(5);
		cellArea.setCellValue("建筑面积：");
		cellArea.setCellStyle(columnHeadStyle);
		
		HSSFCell cellAdd6 = row3.createCell(6);
		cellAdd6.setCellStyle(columnHeadStyle);
		
		HSSFCell cellAdd7 = row3.createCell(7);
		cellAdd7.setCellValue(bizProjectChangeBill.getContractArea());
		cellAdd7.setCellStyle(columnStyle);
		
		HSSFCell cellAdd8 = row3.createCell(8);
		cellAdd8.setCellStyle(columnHeadStyle);
		
		HSSFCell cellAdd9 = row3.createCell(9);
		cellAdd9.setCellStyle(columnHeadStyle);
		
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 5, 6));
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 7, 9));
		
		HSSFRow row4 = sheet.createRow(3);
		HSSFCell cellItemManager = row4.createCell(0);
		cellItemManager.setCellValue("项目经理：");
		cellItemManager.setCellStyle(columnHeadStyle);
		
		HSSFCell bufferUsed1 = row4.createCell(1);
		bufferUsed1.setCellStyle(columnHeadStyle);
		
		HSSFCell bufferUsed2 = row4.createCell(2);
		bufferUsed2.setCellValue(bizProjectChangeBill.getItemManager());
		bufferUsed2.setCellStyle(columnStyle);
		
		HSSFCell bufferUsed3 = row4.createCell(3);
		bufferUsed3.setCellStyle(columnHeadStyle);
		
		HSSFCell bufferUsed4 = row4.createCell(4);
		bufferUsed4.setCellStyle(columnHeadStyle);
		
		HSSFCell cellManagerPhone = row4.createCell(5);
		cellManagerPhone.setCellValue("项目经理电话：");
		cellManagerPhone.setCellStyle(columnHeadStyle);
		
		HSSFCell bufferUsed6 = row4.createCell(6);
		bufferUsed6.setCellStyle(columnHeadStyle);
		
		HSSFCell bufferUsed7 = row4.createCell(7);
		bufferUsed7.setCellValue(bizProjectChangeBill.getItemManagerPhone());
		bufferUsed7.setCellStyle(columnStyle);
		
		HSSFCell bufferUsed8 = row4.createCell(8);
		bufferUsed8.setCellStyle(columnHeadStyle);
		
		HSSFCell bufferUsed9 = row4.createCell(9);
		bufferUsed9.setCellStyle(columnHeadStyle);
		
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 6));
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 7, 9));
		
		HSSFRow row5 = sheet.createRow(4);
		HSSFCell cellDesignerName = row5.createCell(0);
		cellDesignerName.setCellValue("设计师：");
		cellDesignerName.setCellStyle(columnHeadStyle);
		
		HSSFCell cellDesignerName1 = row5.createCell(1);
		cellDesignerName1.setCellStyle(columnHeadStyle);
		
		HSSFCell cellDesignerName2 = row5.createCell(2);
		cellDesignerName2.setCellValue(bizProjectChangeBill.getDesignerName());
		cellDesignerName2.setCellStyle(columnStyle);
		
		HSSFCell cellDesignerName3 = row5.createCell(3);
		cellDesignerName3.setCellStyle(columnHeadStyle);
		
		HSSFCell cellDesignerName4 = row5.createCell(4);
		cellDesignerName4.setCellStyle(columnHeadStyle);
		
		HSSFCell cellDesignerPhone = row5.createCell(5);
		cellDesignerPhone.setCellValue("设计师电话：");
		cellDesignerPhone.setCellStyle(columnHeadStyle);
		
		HSSFCell cellDesignerName6 = row5.createCell(6);
		cellDesignerName6.setCellStyle(columnHeadStyle);
		
		HSSFCell cellDesignerName7 = row5.createCell(7);
		cellDesignerName7.setCellValue(bizProjectChangeBill.getDesignerPhone());
		cellDesignerName7.setCellStyle(columnStyle);
		
		HSSFCell cellDesignerName8 = row5.createCell(8);
		cellDesignerName8.setCellStyle(columnHeadStyle);
		
		HSSFCell cellDesignerName9 = row5.createCell(9);
		cellDesignerName9.setCellStyle(columnHeadStyle);
		
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 5, 6));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 7, 9));
		
		HSSFRow row6 = sheet.createRow(5);
		HSSFCell cellContractNumber = row6.createCell(0);
		cellContractNumber.setCellValue("合同编号：");
		cellContractNumber.setCellStyle(columnHeadStyle);
		
		HSSFCell cellContractNumber1 = row6.createCell(1);
		cellContractNumber1.setCellStyle(columnHeadStyle);
		
		HSSFCell cellContractNumber2 = row6.createCell(2);
		cellContractNumber2.setCellValue(bizProjectChangeBill.getContractNumber());
		cellContractNumber2.setCellStyle(columnStyle);
		
		HSSFCell cellContractNumber3 = row6.createCell(3);
		cellContractNumber3.setCellStyle(columnHeadStyle);
		
		HSSFCell cellContractNumber4 = row6.createCell(4);
		cellContractNumber4.setCellStyle(columnHeadStyle);
		
		HSSFCell cellApplyDate = row6.createCell(5);
		cellApplyDate.setCellValue("申请日期：");
		cellApplyDate.setCellStyle(columnHeadStyle);
		
		HSSFCell cellContractNumber6 = row6.createCell(6);
		cellContractNumber6.setCellStyle(columnHeadStyle);
		
		HSSFCell cellContractNumber7 = row6.createCell(7);
		cellContractNumber7.setCellValue(DateUtils.formatDate(bizProjectChangeBill.getApplyDate()));
		cellContractNumber7.setCellStyle(columnStyle);
		
		HSSFCell cellContractNumber8 = row6.createCell(8);
		cellContractNumber8.setCellStyle(columnHeadStyle);
		
		HSSFCell cellContractNumber9 = row6.createCell(9);
		cellContractNumber9.setCellStyle(columnHeadStyle);
		
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 5, 6));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 7, 9));
		
		HSSFRow row7 = sheet.createRow(6);
		HSSFCell cellIsElevator = row7.createCell(0);
		cellIsElevator.setCellValue("有无电梯：");
		cellIsElevator.setCellStyle(columnHeadStyle);
		
		HSSFCell cellIsElevator1 = row7.createCell(1);
		cellIsElevator1.setCellStyle(columnHeadStyle);
		
		if(bizProjectChangeBill.getIsElevator().equals("0")){
			HSSFCell cellIsElevator2 = row7.createCell(2);
			cellIsElevator2.setCellValue("无");
			cellIsElevator2.setCellStyle(columnStyle);
		}else if(bizProjectChangeBill.getIsElevator().equals("1")){
			HSSFCell cellIsElevator2 = row7.createCell(2);
			cellIsElevator2.setCellValue("有");
			cellIsElevator2.setCellStyle(columnStyle);
		}else{
			HSSFCell cellIsElevator2 = row7.createCell(2);
			cellIsElevator2.setCellValue("");
			cellIsElevator2.setCellStyle(columnStyle);
		}
		
		HSSFCell cellIsElevator3 = row7.createCell(3);
		cellIsElevator3.setCellStyle(columnHeadStyle);
		
		HSSFCell cellIsElevator4 = row7.createCell(4);
		cellIsElevator4.setCellStyle(columnHeadStyle);
		
		HSSFCell cellChangeReason = row7.createCell(5);
		cellChangeReason.setCellValue("变更原因：");
		cellChangeReason.setCellStyle(columnHeadStyle);
		
		HSSFCell cellIsElevator6 = row7.createCell(6);
		cellIsElevator6.setCellStyle(columnHeadStyle);
		
		HSSFCell cellIsElevator7 = row7.createCell(7);
		cellIsElevator7.setCellValue(bizProjectChangeBill.getChangeReason());
		cellIsElevator7.setCellStyle(columnStyle);
		
		HSSFCell cellIsElevator8 = row7.createCell(8);
		cellIsElevator8.setCellStyle(columnHeadStyle);
		
		HSSFCell cellIsElevator9 = row7.createCell(9);
		cellIsElevator9.setCellStyle(columnHeadStyle);
		
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 5, 6));
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 7, 9));
		
		

		HSSFRow rowTitle2 = sheet.createRow(7);
		HSSFCell cell2 = rowTitle2.createCell(0);
		cell2.setCellStyle(columnHeadStyle2);
		cell2.setCellValue(new HSSFRichTextString("增项清单"));
		for(int i=0;i<9;i++){
			HSSFCell cella = rowTitle2.createCell(i+1);
			cella.setCellStyle(columnHeadStyle2);
		}

		sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 9));
				
		HSSFRow row9 = sheet.createRow(8);
		row9.createCell(7).setCellValue("合计：");
		row9.createCell(8).setCellValue(addMoney);
		
		HSSFRow row10 = sheet.createRow(9);
		HSSFCell add = row10.createCell(0);
		HSSFCell add1 = row10.createCell(1);
		HSSFCell add12 = row10.createCell(2);
		HSSFCell add2 = row10.createCell(3);
		HSSFCell add3 = row10.createCell(4);
		HSSFCell add4 = row10.createCell(5);
		HSSFCell add5 = row10.createCell(6);
		HSSFCell add6 = row10.createCell(7);
		HSSFCell add7 = row10.createCell(8);
		HSSFCell add8 = row10.createCell(9);
		
		add.setCellStyle(columnHeadStyle);
		add1.setCellStyle(columnHeadStyle);
		add12.setCellStyle(columnHeadStyle);
		add2.setCellStyle(columnHeadStyle);
		add3.setCellStyle(columnHeadStyle);
		add4.setCellStyle(columnHeadStyle);
		add5.setCellStyle(columnHeadStyle);
		add6.setCellStyle(columnHeadStyle);
		add7.setCellStyle(columnHeadStyle);
		add8.setCellStyle(columnHeadStyle);
		add.setCellValue(new HSSFRichTextString("序号"));
		add1.setCellValue(new HSSFRichTextString("项目名称"));
		add2.setCellValue(new HSSFRichTextString("单位"));
		add3.setCellValue(new HSSFRichTextString("数量"));
		add4.setCellValue(new HSSFRichTextString("损耗"));
		add5.setCellValue(new HSSFRichTextString("人工费（元）"));
		add6.setCellValue(new HSSFRichTextString("综合单价（元）"));
		add7.setCellValue(new HSSFRichTextString("总价"));
		add8.setCellValue(new HSSFRichTextString("说明"));
		sheet.addMergedRegion(new CellRangeAddress(9, 9, 1, 2));
		

		Integer addItemSize = 0;
		if(null!=addItem && addItem.size()>0){
			addItemSize = addItem.size();
			for(int i=0; i<addItemSize; i++){
				BizProjectChangeBillItem vo = addItem.get(i);
				HSSFRow addrow = sheet.createRow(i+10);
				HSSFCell addItemCell = addrow.createCell(0);
				addItemCell.setCellValue(i+1);
				addItemCell.setCellStyle(columnHeadStyle);
				
				HSSFCell addItemCell1 = addrow.createCell(1);
				addItemCell1.setCellValue(vo.getProjectIntemName());
				addItemCell1.setCellStyle(columnStyle);
				
				HSSFCell addItemCell2 = addrow.createCell(2);
				addItemCell2.setCellStyle(columnHeadStyle);
				
				HSSFCell addItemCell3 = addrow.createCell(3);
				addItemCell3.setCellValue(vo.getProjectIntemUnit());
				addItemCell3.setCellStyle(columnStyle);
				
				HSSFCell addItemCell4 = addrow.createCell(4);
				addItemCell4.setCellValue(vo.getProjectIntemAmount());
				addItemCell4.setCellStyle(columnStyle);
				
				HSSFCell addItemCell5 = addrow.createCell(5);
				addItemCell5.setCellValue("");
				addItemCell5.setCellStyle(columnHeadStyle);
				
				HSSFCell addItemCell6 = addrow.createCell(6);
				addItemCell6.setCellValue("");
				addItemCell6.setCellStyle(columnHeadStyle);
				
				HSSFCell addItemCell7 = addrow.createCell(7);
				addItemCell7.setCellValue(vo.getEveryPrice());
				addItemCell7.setCellStyle(columnStyle);
				
				HSSFCell addItemCell8 = addrow.createCell(8);
				addItemCell8.setCellValue(vo.getAllPrice());
				addItemCell8.setCellStyle(columnStyle);
				
				HSSFCell addItemCell9 = addrow.createCell(9);
				addItemCell9.setCellValue(vo.getExplainWords());
				addItemCell9.setCellStyle(columnStyle);
				
				sheet.addMergedRegion(new CellRangeAddress(i+10, i+10, 1, 2));
			}
		}
		

		HSSFRow rowTitle3 = sheet.createRow(addItemSize+10);
		HSSFCell cell3 = rowTitle3.createCell(0);
		cell3.setCellStyle(columnHeadStyle2);
		cell3.setCellValue(new HSSFRichTextString("减项清单"));
		for(int i=0;i<9;i++){
			HSSFCell cella = rowTitle3.createCell(i+1);
			cella.setCellStyle(columnHeadStyle2);
		}

		sheet.addMergedRegion(new CellRangeAddress(addItemSize+10, addItemSize+10, 0, 9));
		
		HSSFRow subrow = sheet.createRow(addItemSize+11);
		subrow.createCell(7).setCellValue("合计：");
		subrow.createCell(8).setCellValue(reducePrice);
		
		HSSFRow subrow2 = sheet.createRow(addItemSize+12);
		HSSFCell sub = subrow2.createCell(0);
		HSSFCell sub1 = subrow2.createCell(1);
		HSSFCell sub12 = subrow2.createCell(2);
		HSSFCell sub2 = subrow2.createCell(3);
		HSSFCell sub3 = subrow2.createCell(4);
		HSSFCell sub4 = subrow2.createCell(5);
		HSSFCell sub5 = subrow2.createCell(6);
		HSSFCell sub6 = subrow2.createCell(7);
		HSSFCell sub7 = subrow2.createCell(8);
		HSSFCell sub8 = subrow2.createCell(9);
		
		sub.setCellStyle(columnHeadStyle);
		sub1.setCellStyle(columnHeadStyle);
		sub12.setCellStyle(columnHeadStyle);
		sub2.setCellStyle(columnHeadStyle);
		sub3.setCellStyle(columnHeadStyle);
		sub4.setCellStyle(columnHeadStyle);
		sub5.setCellStyle(columnHeadStyle);
		sub6.setCellStyle(columnHeadStyle);
		sub7.setCellStyle(columnHeadStyle);
		sub8.setCellStyle(columnHeadStyle);
		sub.setCellValue(new HSSFRichTextString("序号"));
		sub1.setCellValue(new HSSFRichTextString("项目名称"));
		sub2.setCellValue(new HSSFRichTextString("单位"));
		sub3.setCellValue(new HSSFRichTextString("数量"));
		sub4.setCellValue(new HSSFRichTextString("损耗"));
		sub5.setCellValue(new HSSFRichTextString("人工费（元）"));
		sub6.setCellValue(new HSSFRichTextString("综合单价（元）"));
		sub7.setCellValue(new HSSFRichTextString("总价"));
		sub8.setCellValue(new HSSFRichTextString("说明"));
		sheet.addMergedRegion(new CellRangeAddress(addItemSize+12, addItemSize+12, 1, 2));
		
		Integer subItemSize = 0;
		if(null!=subItem && subItem.size()>0){
			subItemSize = subItem.size();
			for(int i=0; i<subItemSize; i++){
				BizProjectChangeBillItem vo = subItem.get(i);
				HSSFRow addrow = sheet.createRow(i+addItemSize+13);
				HSSFCell subItemCell = addrow.createCell(0);
				subItemCell.setCellValue(i+1);
				subItemCell.setCellStyle(columnHeadStyle);
				
				HSSFCell subItemCell1 = addrow.createCell(1);
				subItemCell1.setCellValue(vo.getProjectIntemName());
				subItemCell1.setCellStyle(columnStyle);
				
				HSSFCell subItemCell2 = addrow.createCell(2);
				subItemCell2.setCellStyle(columnHeadStyle);
				
				HSSFCell subItemCell3 = addrow.createCell(3);
				subItemCell3.setCellValue(vo.getProjectIntemUnit());
				subItemCell3.setCellStyle(columnStyle);
				
				HSSFCell subItemCell4 = addrow.createCell(4);
				subItemCell4.setCellValue(vo.getProjectIntemAmount());
				subItemCell4.setCellStyle(columnStyle);
				
				HSSFCell subItemCell5 = addrow.createCell(5);
				subItemCell5.setCellValue("");
				subItemCell5.setCellStyle(columnHeadStyle);
				
				HSSFCell subItemCell6 = addrow.createCell(6);
				subItemCell6.setCellValue("");
				subItemCell6.setCellStyle(columnHeadStyle);
				
				HSSFCell subItemCell7 = addrow.createCell(7);
				subItemCell7.setCellValue(vo.getEveryPrice());
				subItemCell7.setCellStyle(columnStyle);
				
				HSSFCell subItemCell8 = addrow.createCell(8);
				subItemCell8.setCellValue(vo.getAllPrice());
				subItemCell8.setCellStyle(columnStyle);
				
				HSSFCell subItemCell9 = addrow.createCell(9);
				subItemCell9.setCellValue(vo.getExplainWords());
				subItemCell9.setCellStyle(columnStyle);
				
				sheet.addMergedRegion(new CellRangeAddress(i+addItemSize+13, i+addItemSize+13, 1, 2));
			}
		}
		
		HSSFRow last = sheet.createRow(addItemSize+subItemSize+14);
		last.createCell(7).setCellValue("合计：");
		last.createCell(8).setCellValue(totalMoney);
		

		HSSFRow rowTitle4 = sheet.createRow(addItemSize+subItemSize+15);
		HSSFCell cell4 = rowTitle4.createCell(0);
		cell4.setCellStyle(columnHeadStyle2);
		cell4.setCellValue(new HSSFRichTextString("审批轨迹"));
		for(int i=0;i<9;i++){
			HSSFCell cella = rowTitle4.createCell(i+1);
				cella.setCellStyle(columnHeadStyle2);
		}

		sheet.addMergedRegion(new CellRangeAddress(addItemSize+subItemSize+15, addItemSize+subItemSize+15, 0, 9));
				
		HSSFRow subrow3 = sheet.createRow(addItemSize+subItemSize+16);
		HSSFCell auditSub = subrow3.createCell(0);
		HSSFCell auditSsub1 = subrow3.createCell(1);
		HSSFCell auditSsub12 = subrow3.createCell(2);
		HSSFCell auditSsub2 = subrow3.createCell(3);
		HSSFCell auditSsub3 = subrow3.createCell(4);
		HSSFCell auditSsub4 = subrow3.createCell(5);
		HSSFCell auditSsub6 = subrow3.createCell(6);
		HSSFCell auditSsub7 = subrow3.createCell(7);
		HSSFCell auditSsub8 = subrow3.createCell(8);
		HSSFCell auditSsub9 = subrow3.createCell(9);
				
		auditSub.setCellStyle(columnHeadStyle);
		auditSsub1.setCellStyle(columnHeadStyle);
		auditSsub12.setCellStyle(columnHeadStyle);
		auditSsub2.setCellStyle(columnHeadStyle);
		auditSsub3.setCellStyle(columnHeadStyle);
		auditSsub4.setCellStyle(columnHeadStyle);
		auditSsub6.setCellStyle(columnHeadStyle);
		auditSsub7.setCellStyle(columnHeadStyle);
		auditSsub8.setCellStyle(columnHeadStyle);
		auditSsub9.setCellStyle(columnHeadStyle);
			
		auditSub.setCellValue(new HSSFRichTextString("项目经理"));
		auditSsub1.setCellValue(new HSSFRichTextString("项目经理申请时间"));
		auditSsub12.setCellValue(new HSSFRichTextString("设计师审核操作人"));
		auditSsub2.setCellValue(new HSSFRichTextString("设计师审核通过时间"));
		auditSsub3.setCellValue(new HSSFRichTextString("审计员操作人"));
		auditSsub4.setCellValue(new HSSFRichTextString("审计员审核通过时间"));
		auditSsub6.setCellValue(new HSSFRichTextString(""));
		auditSsub7.setCellValue(new HSSFRichTextString(""));
		auditSsub8.setCellValue(new HSSFRichTextString(""));
		auditSsub9.setCellValue(new HSSFRichTextString(""));
				
		HSSFRow addrow = sheet.createRow(addItemSize+subItemSize+17);
		HSSFCell applyCell = addrow.createCell(0);
		
		if(applyDetails.getItemManager()== null){
			applyCell.setCellValue("");
			applyCell.setCellStyle(columnStyle);
		}else{
			applyCell.setCellValue(applyDetails.getItemManager());
			applyCell.setCellStyle(columnStyle);
		}
		
		HSSFCell applyCell1 = addrow.createCell(1);
		if(applyDetails.getApplyDate() == null){
			applyCell1.setCellValue("");
			applyCell1.setCellStyle(columnStyle);
		}else{
			applyCell1.setCellValue(DateUtils.formatDateTime(applyDetails.getApplyDate()));
			applyCell1.setCellStyle(columnStyle);
		}
		
		if(applyDetails.getStylistCheckBy()==null){
			HSSFCell applyCell2 = addrow.createCell(2);
			applyCell2.setCellValue("");
			applyCell2.setCellStyle(columnStyle);
		}else{
			HSSFCell applyCell2 = addrow.createCell(2);
			applyCell2.setCellValue(applyDetails.getStylistCheckBy());
			applyCell2.setCellStyle(columnStyle);
		}
		
		if(applyDetails.getStylistCheckDate()==null){
			
			HSSFCell applyCell3 = addrow.createCell(3);
			applyCell3.setCellValue("");
			applyCell3.setCellStyle(columnStyle);
		}else{
			HSSFCell applyCell3 = addrow.createCell(3);
			applyCell3.setCellValue(DateUtils.formatDateTime(applyDetails.getStylistCheckDate()));
			applyCell3.setCellStyle(columnStyle);
		}
		
		if(applyDetails.getAuditCheckBy()==null){
			HSSFCell applyCell4 = addrow.createCell(4);
			applyCell4.setCellValue("");
			applyCell4.setCellStyle(columnStyle);
		}else{
			HSSFCell applyCell4 = addrow.createCell(4);
			applyCell4.setCellValue(applyDetails.getAuditCheckBy());
			applyCell4.setCellStyle(columnStyle);
		}
		
		if(applyDetails.getAuditCheckDate()==null){
			HSSFCell applyCell5 = addrow.createCell(5);
			applyCell5.setCellValue("");
			applyCell5.setCellStyle(columnStyle);
		}else{
			HSSFCell applyCell5 = addrow.createCell(5);
			applyCell5.setCellValue(DateUtils.formatDateTime(applyDetails.getAuditCheckDate()));
			applyCell5.setCellStyle(columnStyle);
		}
		
		HSSFCell applyCell6 = addrow.createCell(6);
		applyCell6.setCellValue("");
		applyCell6.setCellStyle(columnStyle);
		
		HSSFCell applyCell7 = addrow.createCell(7);
		applyCell7.setCellValue("");
		applyCell7.setCellStyle(columnStyle);
		
		HSSFCell applyCell8 = addrow.createCell(8);
		applyCell8.setCellValue("");
		applyCell8.setCellStyle(columnStyle);
		
		HSSFCell applyCell9 = addrow.createCell(9);
		applyCell9.setCellValue("");
		applyCell9.setCellStyle(columnStyle);
		return wb;
	}


	@Transactional(readOnly=false)
	public void saveBizMsg(BizMsg bizMsg) {
		dao.saveBizMsg(bizMsg);
	}

	public String findDescribeByRoleId(String roleId) {
		
		return dao.findDescribeByRoleId(roleId);
	}

	public List<BizProjectChangeBill> findAllStore(String string) {

		return dao.findAllStore(string);
	}

	public User getCountByPhone(String phone) {

		return dao.getCountByPhone(phone);
	}

	public Page<BizProjectChangeBill> findPageQuery(Page<BizProjectChangeBill> page,
			BizProjectChangeBill bizProjectChangeBill) {
		bizProjectChangeBill.setPage(page);
		page.setList(dao.findList2(bizProjectChangeBill));
		return page;
	}

	public List<BizProjectChangeBill> findDetail(BizProjectChangeBill bizProjectChangeBill) {

		return dao.findDetail(bizProjectChangeBill);
	}
@Transactional(readOnly=false)
	public void updataIsDealed(BizProjectChangeBill bizProjectChangeBill) {

		dao.updataIsDealed(bizProjectChangeBill);
	}

	
}