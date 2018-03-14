package cn.damei.common.utils;

import java.io.FileOutputStream;
import java.util.List;

import cn.damei.entity.modules.BizNodePlan;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import cn.damei.entity.modules.BizConstructionSchedule;
import cn.damei.entity.modules.ProgressKanban;

public class ExportExcelKanban {

	public static void main(String[] args) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet("银行存余额表");// 创建一个Excel的Sheet
		// 定义样式
		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);// 表头样工
		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);// 单元格样式
		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 右对齐
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左对齐

		// 设置列宽
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);
		
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 
            // ---------------------------1.初始化带边框的表头------------------------------  
            for (int i = 0; i < 17; i++) {
                row = sheet.createRow(i);  
                for (int j = 0; j <= 17; j++) {  
                    cell = row.createCell(j);  
                    cell.setCellStyle(cellStyleCenter);  
                }  
            }  
            // ---------------------------2.指定单元格填充数据------------------------------  
            cell = sheet.getRow(0).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("美得你工地精细化管理可视看板"));  
            cell = sheet.getRow(1).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("合同编号"));  
            cell = sheet.getRow(1).getCell(1);  
            cell.setCellValue(new HSSFRichTextString("客户/电话"));  
            cell = sheet.getRow(1).getCell(2);  
            cell.setCellValue(new HSSFRichTextString("地址"));  
            cell = sheet.getRow(1).getCell(3);  
            cell.setCellValue(new HSSFRichTextString("设计师/电话"));  
            cell = sheet.getRow(1).getCell(4);  
            cell.setCellValue(new HSSFRichTextString("项目经理/电话"));  
            cell = sheet.getRow(1).getCell(5);  
            cell.setCellValue(new HSSFRichTextString("质检员/电话"));  
            cell = sheet.getRow(1).getCell(6);  
            cell.setCellValue(new HSSFRichTextString("合同开工时间"));  
            cell = sheet.getRow(1).getCell(7);  
            cell.setCellValue(new HSSFRichTextString("合同完工时间"));  
            cell = sheet.getRow(1).getCell(8);  
            cell.setCellValue(new HSSFRichTextString("合同面积"));
            
            // ---------------------------2.指定单元格填充数据------------------------------  
            cell = sheet.getRow(0).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("美得你工地精细化管理可视看板"));  
            cell = sheet.getRow(1).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("合同编号"));  
            cell = sheet.getRow(1).getCell(12);  
            cell.setCellValue(new HSSFRichTextString("合同编号")); 
            cell = sheet.getRow(2).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("合同编号")); 
  
            // ---------------------------3.合并单元格------------------------------  
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));// 开始行，结束行，开始列，结束列  
            //sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 7, 7));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));
            
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 9, 17));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 11));
            
            FileOutputStream fileOut = new FileOutputStream("d:\\银行存款余额表.xls");  
            wb.write(fileOut);  
            fileOut.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }  

	public static HSSFWorkbook exportKanban (List<ProgressKanban> list, List<BizConstructionSchedule> csList, List<BizNodePlan> npList){
		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet("工程进度看板");// 创建一个Excel的Sheet
		// 定义样式
		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);// 表头样工
		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);// 单元格样式
		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 右对齐
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左对齐

		// 设置列宽
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 
            // ---------------------------1.初始化带边框的表头------------------------------  
            for (int i = 0; i < 8 + (int)csList.size()*3; i++) {
                row = sheet.createRow(i);  
                for (int j = 0; j <= 8 + (int)csList.size()*3; j++) {  
                    cell = row.createCell(j);  
                    cell.setCellStyle(cellStyleCenter);  
                }  
            }  
            // ---------------------------2.指定单元格填充数据1------------------------------  
            cell = sheet.getRow(0).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("美得你工地精细化管理可视看板"));  
            cell = sheet.getRow(1).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("合同编号"));  
            cell = sheet.getRow(1).getCell(1);  
            cell.setCellValue(new HSSFRichTextString("客户/电话"));  
            cell = sheet.getRow(1).getCell(2);  
            cell.setCellValue(new HSSFRichTextString("地址"));  
            cell = sheet.getRow(1).getCell(3);  
            cell.setCellValue(new HSSFRichTextString("设计师/电话"));  
            cell = sheet.getRow(1).getCell(4);  
            cell.setCellValue(new HSSFRichTextString("项目经理/电话"));  
            cell = sheet.getRow(1).getCell(5);  
            cell.setCellValue(new HSSFRichTextString("质检员/电话"));  
            cell = sheet.getRow(1).getCell(6);  
            cell.setCellValue(new HSSFRichTextString("合同开工时间"));  
            cell = sheet.getRow(1).getCell(7);  
            cell.setCellValue(new HSSFRichTextString("合同完工时间"));  
            cell = sheet.getRow(1).getCell(8);  
            cell.setCellValue(new HSSFRichTextString("合同面积"));
            
            // ---------------------------2.指定单元格填充数据2------------------------------  
            cell = sheet.getRow(0).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("美得你工地精细化管理可视看板"));
            //计算得到工序表数据
            for (int i = 0; i < csList.size(); i++) {
            	BizConstructionSchedule Schedule = csList.get(i);
            	cell = sheet.getRow(1).getCell((int)i*3 + (int)3*csList.size());  
                cell.setCellValue(new HSSFRichTextString(Schedule.getConstructionScheduleName()));
			} 
            
            for (int i = 0; i < csList.size(); i++) {
            	cell = sheet.getRow(2).getCell((int)9 + i*3);  
                cell.setCellValue(new HSSFRichTextString("计划完工日期"));
                cell = sheet.getRow(2).getCell((int)10 + i*3);  
                cell.setCellValue(new HSSFRichTextString("实际完成日期"));
                cell = sheet.getRow(2).getCell((int)11 + i*3);  
                cell.setCellValue(new HSSFRichTextString("延期天数"));
            }
  
            // ---------------------------3.合并单元格1------------------------------  
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));// 开始行，结束行，开始列，结束列  
            //sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 7, 7));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));
            
            // ---------------------------3.合并单元格2------------------------------ 
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 9, 8+(short)csList.size()*3));
            for (int i = 0; i < csList.size(); i++) {
            	sheet.addMergedRegion(new CellRangeAddress(1, 1, 9 + (i*3),12 + (i*3) -1 ));//1 1 9 12
			}
            
            //循环写数据excel
            for (int i = 0; i < list.size(); i++) {
            	row = sheet.createRow((int) i + 3);
            	ProgressKanban p= list.get(i);
            	for (int j = 0; j < npList.size(); j++) {
            		BizNodePlan np = npList.get(j);
            		if(np.getOrderId().toString().equals(p.getId().toString())){
						for (int k = 0; k < csList.size(); k++) {
							BizConstructionSchedule Schedule = csList.get(k);
							if(Schedule.getSort().toString().equals(np.getNodeIndex().toString())){
								if(null != np){
									row.createCell(9 + k*3).setCellValue(DateUtils.formatDate(np.getExeDoneDate()));
									if(np.getRealDoneDate() == null){
										row.createCell(10 + k*3).setCellValue("");
									}else{
										row.createCell(10 + k*3).setCellValue(DateUtils.formatDate(np.getRealDoneDate()));
									}
									if(np.getDelayDays() == null){
										row.createCell(11 + k*3).setCellValue("");
									}else{
										row.createCell(11 + k*3).setCellValue(np.getDelayDays());
									}
								}else{
									row.createCell(9 + k*3).setCellValue("");
									row.createCell(10 + k*3).setCellValue("");
									row.createCell(11 + k*3).setCellValue("");
								}
							}
						}
            		}
            		// 创建单元格，设置值         
	            	row.createCell(0).setCellValue(p.getContractNumber());
	            	
	            	if(p.getCustomerName() == null && p.getCustomerPhone() == null){
	            		row.createCell(1).setCellValue("");
	            	}else if(p.getCustomerName() != null && p.getCustomerPhone() == null){
	            		row.createCell(1).setCellValue(p.getCustomerName());
	            	}else if(p.getCustomerName() == null && p.getCustomerPhone() != null){
	            		row.createCell(1).setCellValue(p.getCustomerPhone());
	            	}else{
	            		row.createCell(1).setCellValue(p.getCustomerName() +"/"+ p.getCustomerPhone()); 
	            	}
	            	
	            	row.createCell(2).setCellValue(p.getCustomerAddress());
	            	
	            	if(p.getDesignerName() == null && p.getDesignerPhone() == null){
	            		row.createCell(3).setCellValue("");
	            	}else if(p.getDesignerName() != null && p.getDesignerPhone() == null){
	            		row.createCell(3).setCellValue(p.getDesignerName());
	            	}else if(p.getDesignerName() == null && p.getDesignerPhone() != null){
	            		row.createCell(3).setCellValue(p.getDesignerPhone());
	            	}else{
	            		row.createCell(3).setCellValue(p.getDesignerName() +"/"+ p.getDesignerPhone()); 
	            	}
	            	
	            	if(p.getManagerName() == null && p.getManagerPhone() == null){
	            		row.createCell(4).setCellValue("");
	            	}else if(p.getManagerName() != null && p.getManagerPhone() == null){
	            		row.createCell(4).setCellValue(p.getManagerName());
	            	}else if(p.getManagerName() == null && p.getManagerPhone() != null){
	            		row.createCell(4).setCellValue(p.getManagerPhone());
	            	}else{
	            		row.createCell(4).setCellValue(p.getManagerName() +"/"+ p.getManagerPhone()); 
	            	}
	            	
	            	if(p.getInspectorName() == null && p.getInspectorPhone() == null){
	            		row.createCell(5).setCellValue("");
	            	}else if(p.getInspectorName() != null && p.getInspectorPhone() == null){
	            		row.createCell(5).setCellValue(p.getInspectorName());
	            	}else if(p.getInspectorName() == null && p.getInspectorPhone() != null){
	            		row.createCell(5).setCellValue(p.getInspectorPhone());
	            	}else{
	            		row.createCell(5).setCellValue(p.getInspectorName() +"/"+ p.getInspectorPhone()); 
	            	}
	            	
	            	row.createCell(6).setCellValue(DateUtils.formatDate(p.getContractStartDate())); 
	            	row.createCell(7).setCellValue(DateUtils.formatDate(p.getContractEndDate()));
	            	row.createCell(8).setCellValue(p.getContractArea());
				}
			}
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return wb;
	}
}
