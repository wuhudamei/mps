package cn.damei.common.utils;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import cn.damei.entity.modules.BizCustomerReturnVisitContent;
import cn.damei.entity.modules.BizCustomerReturnVisitRecordAnswer;
import cn.damei.entity.modules.BizCustomerReturnVisitRecord;
/**
 * 
 * @author lft
 * 导出客户回访记录
 */
public class ExportCustomerReturnVisitRecord {
	
	public static HSSFWorkbook exportCustomerReturnVisitRecord(List<BizCustomerReturnVisitContent> questionList,List<BizCustomerReturnVisitRecord> list){
        //问题个数
        int size = 0;
        if(questionList !=null && questionList.size() > 0 ){
            size=questionList.size();
        }
		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet("客户回访记录统计表");// 创建一个Excel的Sheet
		// 定义样式
		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);// 表头样工
		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);// 单元格样式
		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 右对齐
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左对齐

		// 设置列宽
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10,3000);
		sheet.setColumnWidth(11,3000);
		sheet.setColumnWidth(12,3000);
		sheet.setColumnWidth(13,3000);
		sheet.setColumnWidth(14,4000);
		sheet.setColumnWidth(15,3000);
		sheet.setColumnWidth(16,3000);
		for (int i = 0; i < size; i++) {
			sheet.setColumnWidth(i+17,5000);	
		}
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 
            // ---------------------------1.初始化带边框的表头------------------------------  
            for (int i = 0; i < 3; i++) {
                row = sheet.createRow(i);  
                for (int j = 0; j < 17+size; j++) {  
                    cell = row.createCell(j);  
                    cell.setCellStyle(cellStyleCenter);  
                }  
            }  
            // ---------------------------2.指定单元格填充数据------------------------------
            sheet.getRow(0).setHeight((short) 600);
            cell = sheet.getRow(0).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("客户回访记录统计表")); 
            cell = sheet.getRow(1).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("门店"));  
            cell = sheet.getRow(1).getCell(1);  
            cell.setCellValue(new HSSFRichTextString("工程模式"));  
            cell = sheet.getRow(1).getCell(2);  
            cell.setCellValue(new HSSFRichTextString("区域"));  
            cell = sheet.getRow(1).getCell(3);  
            cell.setCellValue(new HSSFRichTextString("订单编号"));  
            cell = sheet.getRow(1).getCell(4);  
            cell.setCellValue(new HSSFRichTextString("工程地址"));  
            cell = sheet.getRow(1).getCell(5);  
            cell.setCellValue(new HSSFRichTextString("客户姓名"));  
            cell = sheet.getRow(1).getCell(6);  
            cell.setCellValue(new HSSFRichTextString("客户电话"));  
            cell = sheet.getRow(1).getCell(7);  
            cell.setCellValue(new HSSFRichTextString("设计师"));  
            cell = sheet.getRow(1).getCell(8);  
            cell.setCellValue(new HSSFRichTextString("设计师手机号"));  
            cell = sheet.getRow(1).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("项目经理"));
            cell = sheet.getRow(1).getCell(10);  
            cell.setCellValue(new HSSFRichTextString("项目经理手机号"));
            cell = sheet.getRow(1).getCell(11);  
            cell.setCellValue(new HSSFRichTextString("质检"));
            cell = sheet.getRow(1).getCell(12);  
            cell.setCellValue(new HSSFRichTextString("质检手机号"));
            cell = sheet.getRow(1).getCell(13);  
            cell.setCellValue(new HSSFRichTextString("实际开工时间"));
            cell = sheet.getRow(1).getCell(14);  
            cell.setCellValue(new HSSFRichTextString("回访节点"));
            cell = sheet.getRow(1).getCell(15);  
            cell.setCellValue(new HSSFRichTextString("回访员"));
            cell = sheet.getRow(1).getCell(16);  
            cell.setCellValue(new HSSFRichTextString("回访日期"));
            for (int i = 0; i < size; i++) {
            	 cell = sheet.getRow(1).getCell(17+i);  
                 cell.setCellValue(new HSSFRichTextString(questionList.get(i).getQuestionContent()));
    		}
// ---------------------------3.合并单元格------------------------------  
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 16+size));// 开始行，结束行，开始列，结束列  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 7, 7));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 9, 9));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 10, 10));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 11, 11));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 12, 12));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 14, 14));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 15, 15));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 16, 16));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 17, 17));
            for (int i = 0; i < size; i++) {
            	sheet.addMergedRegion(new CellRangeAddress(1, 2, 17+i, 17+i));
            }
            for (int i= 0; i<list.size();i++) {
            	row = sheet.createRow(i+3);
				row.createCell(0).setCellValue(list.get(i).getStoreName());
				row.createCell(1).setCellValue(list.get(i).getProjectModeName());
				row.createCell(2).setCellValue(list.get(i).getAreaName());
				row.createCell(3).setCellValue(list.get(i).getOrderNumber());
				row.createCell(4).setCellValue(list.get(i).getCustomerAddress());
				row.createCell(5).setCellValue(list.get(i).getCustomerName());
				row.createCell(6).setCellValue(list.get(i).getCustomerPhone());
				row.createCell(7).setCellValue(list.get(i).getDesignerName());
				row.createCell(8).setCellValue(list.get(i).getDesignerPhone());
				row.createCell(9).setCellValue(list.get(i).getItemManager());
				row.createCell(10).setCellValue(list.get(i).getItemManagerPhone());
				row.createCell(11).setCellValue(list.get(i).getOrderInspector());
				row.createCell(12).setCellValue(list.get(i).getOrderInspectorPhone());
				row.createCell(13).setCellValue(DateUtils.formatDate(list.get(i).getActualStartDate()));
				row.createCell(14).setCellValue(list.get(i).getReturnVisitNodeName());
				row.createCell(15).setCellValue(list.get(i).getCustomServiceName());
				row.createCell(16).setCellValue(DateUtils.formatDateTime(list.get(i).getReturnVisitTime()));
				List<BizCustomerReturnVisitRecordAnswer> answerList2 = list.get(i).getAnswerList();
				
				//判断回访记录的问题数是否小于当前回访问题数，如果小于，按照回访记录输出，避免出现数组越界异常
				if( answerList2.size() < size ){
					for (int j = 0; j < answerList2.size(); j++) {
						row.createCell(17+j).setCellValue(answerList2.get(j).getQuestionAnswer());
		            }
				}else{
					for (int j = 0; j < size; j++) {
						row.createCell(17+j).setCellValue(answerList2.get(j).getQuestionAnswer());
		            }
				}
				
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return wb;
	}

}
