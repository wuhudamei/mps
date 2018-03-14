package cn.damei.common.utils;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import cn.damei.entity.modules.BizEmployee2;
import cn.damei.entity.modules.PaymentDetailForExcel;
import cn.damei.entity.modules.Auxiliary;
import cn.damei.entity.modules.BizPurchaseVo;

public class ExportProjectExcel {

	public static void main(String[] args) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("结算明细表");

		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);
		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);
		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);


		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 8000);
		sheet.setColumnWidth(6, 2000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 3000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		sheet.setColumnWidth(16, 3000);
		sheet.setColumnWidth(17, 3000);
		sheet.setColumnWidth(18, 3000);
		sheet.setColumnWidth(19, 3000);
		
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 

            for (int i = 0; i < 17; i++) {
                row = sheet.createRow(i);  
                for (int j = 0; j <= 17; j++) {  
                    cell = row.createCell(j);  
                    cell.setCellStyle(cellStyleCenter);  
                }  
            }  

            sheet.getRow(0).setHeight((short) 600);
            cell = sheet.getRow(0).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("产业工人结算明细表")); 
            cell = sheet.getRow(1).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("序号"));  
            cell = sheet.getRow(1).getCell(1);  
            cell.setCellValue(new HSSFRichTextString("结算单编号"));  
            cell = sheet.getRow(1).getCell(2);  
            cell.setCellValue(new HSSFRichTextString("对应批次号"));  
            cell = sheet.getRow(1).getCell(3);  
            cell.setCellValue(new HSSFRichTextString("客户名称"));  
            cell = sheet.getRow(1).getCell(4);  
            cell.setCellValue(new HSSFRichTextString("电话"));  
            cell = sheet.getRow(1).getCell(5);  
            cell.setCellValue(new HSSFRichTextString("地址"));  
            cell = sheet.getRow(1).getCell(6);  
            cell.setCellValue(new HSSFRichTextString("合同面积（M²）"));  
            cell = sheet.getRow(1).getCell(7);  
            cell.setCellValue(new HSSFRichTextString("项目经理"));  
            cell = sheet.getRow(1).getCell(8);  
            cell.setCellValue(new HSSFRichTextString("工人"));
            cell = sheet.getRow(1).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("身份证号"));
            cell = sheet.getRow(1).getCell(10);  
            cell.setCellValue(new HSSFRichTextString("任务包"));
            cell = sheet.getRow(1).getCell(11);  
            cell.setCellValue(new HSSFRichTextString("结算单申请日期"));
            cell = sheet.getRow(1).getCell(12);  
            cell.setCellValue(new HSSFRichTextString("结算单审核日期"));
            cell = sheet.getRow(1).getCell(13);  
            cell.setCellValue(new HSSFRichTextString("结算总金额"));
            cell = sheet.getRow(1).getCell(14);  
            cell.setCellValue(new HSSFRichTextString("首款"));
            cell = sheet.getRow(1).getCell(16);  
            cell.setCellValue(new HSSFRichTextString("尾款"));
            cell = sheet.getRow(2).getCell(14);  
            cell.setCellValue(new HSSFRichTextString("结算比例"));
            cell = sheet.getRow(2).getCell(15);  
            cell.setCellValue(new HSSFRichTextString("首次结算金额（元）"));
            cell = sheet.getRow(2).getCell(16);  
            cell.setCellValue(new HSSFRichTextString("余款结算比例"));
            cell = sheet.getRow(2).getCell(17);  
            cell.setCellValue(new HSSFRichTextString("实发金额（元）"));

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 17));
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
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 14, 15));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 16, 17));
            FileOutputStream fileOut = new FileOutputStream("d:\\产业工人结算汇总表.xls");  
            wb.write(fileOut);  
            fileOut.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    }  
	
	
	
	public static HSSFWorkbook exportProject(List<PaymentDetailForExcel> list){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("结算明细表");

		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);
		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);
		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);


		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 8000);
		sheet.setColumnWidth(7, 2000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 4000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		sheet.setColumnWidth(16, 3000);
		sheet.setColumnWidth(17, 3000);
		sheet.setColumnWidth(18, 3000);
		sheet.setColumnWidth(19, 3000);
		sheet.setColumnWidth(20, 3000);
		sheet.setColumnWidth(21, 3000);
		
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 

            for (int i = 0; i < 3; i++) {
                row = sheet.createRow(i);  
                for (int j = 0; j <= 19; j++) {  
                    cell = row.createCell(j);  
                    cell.setCellStyle(cellStyleCenter);  
                }  
            }  

            cell = sheet.getRow(0).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("产业工人结算明细表"));  
            cell = sheet.getRow(1).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("序号"));  
            cell = sheet.getRow(1).getCell(1);  
            cell.setCellValue(new HSSFRichTextString("订单编号"));  
            cell = sheet.getRow(1).getCell(2);  
            cell.setCellValue(new HSSFRichTextString("结算单编号"));  
            cell = sheet.getRow(1).getCell(3);  
            cell.setCellValue(new HSSFRichTextString("对应批次号"));  
            cell = sheet.getRow(1).getCell(4);  
            cell.setCellValue(new HSSFRichTextString("客户名称"));  
            cell = sheet.getRow(1).getCell(5);  
            cell.setCellValue(new HSSFRichTextString("电话"));  
            cell = sheet.getRow(1).getCell(6);  
            cell.setCellValue(new HSSFRichTextString("地址"));  
            cell = sheet.getRow(1).getCell(7);  
            cell.setCellValue(new HSSFRichTextString("合同面积（M²）"));  
            cell = sheet.getRow(1).getCell(8);  
            cell.setCellValue(new HSSFRichTextString("项目经理"));  
            cell = sheet.getRow(1).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("工人"));
            cell = sheet.getRow(1).getCell(10);  
            cell.setCellValue(new HSSFRichTextString("身份证号"));
            cell = sheet.getRow(1).getCell(11);  
            cell.setCellValue(new HSSFRichTextString("任务包"));
            cell = sheet.getRow(1).getCell(12);  
            cell.setCellValue(new HSSFRichTextString("结算单申请日期"));
            cell = sheet.getRow(1).getCell(13);  
            cell.setCellValue(new HSSFRichTextString("结算单审核日期"));
            cell = sheet.getRow(1).getCell(14);  
            cell.setCellValue(new HSSFRichTextString("结算总金额"));
            
            cell = sheet.getRow(1).getCell(15);  
            cell.setCellValue(new HSSFRichTextString("工人组结算金额"));
            
            cell = sheet.getRow(1).getCell(15+1);  
            cell.setCellValue(new HSSFRichTextString("首款"));
            cell = sheet.getRow(1).getCell(17+1);  
            cell.setCellValue(new HSSFRichTextString("尾款"));
            cell = sheet.getRow(2).getCell(15+1);  
            cell.setCellValue(new HSSFRichTextString("结算比例"));
            cell = sheet.getRow(2).getCell(16+1);  
            cell.setCellValue(new HSSFRichTextString("首次结算金额（元）"));
            cell = sheet.getRow(2).getCell(17+1);  
            cell.setCellValue(new HSSFRichTextString("余款结算比例"));
            cell = sheet.getRow(2).getCell(18+1);  
            cell.setCellValue(new HSSFRichTextString("实发金额（元）"));

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 19));
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
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 16, 17));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 18, 19));
            
            for (int i= 0; i<list.size();i++) {
            	row = sheet.createRow(i+3);
            	for(int j = 0; j< 20; j++ ){
					row.createCell(0).setCellValue(i+1);
					row.createCell(1).setCellValue(list.get(i).getOrderNumber());
					row.createCell(2).setCellValue(list.get(i).getSettlementCode());
					row.createCell(3).setCellValue(list.get(i).getSummaryCode());
					row.createCell(4).setCellValue(list.get(i).getCustomerName());
					row.createCell(5).setCellValue(list.get(i).getCustomerPhone());
					row.createCell(6).setCellValue(list.get(i).getCustomerAddress());
					row.createCell(7).setCellValue(list.get(i).getContractArea());
					row.createCell(8).setCellValue(list.get(i).getItemManager());
					row.createCell(9).setCellValue(list.get(i).getWorkerName());
					row.createCell(10).setCellValue(list.get(i).getIdCard());
					row.createCell(11).setCellValue(list.get(i).getPackageName());
					row.createCell(12).setCellValue(DateUtils.formatDate(list.get(i).getSettlementApplyDate()));
					row.createCell(13).setCellValue(DateUtils.formatDate(list.get(i).getSettlementExamineDate()));
					row.createCell(14).setCellValue(list.get(i).getSettlementAmount());
					row.createCell(15).setCellValue(list.get(i).getWorkerGroupSettleAmount());
					if(list.get(i).getAdvanceRate() == null){
						row.createCell(16).setCellValue("");
					}else{
						row.createCell(16).setCellValue(list.get(i).getAdvanceRate());
					}
					
					if(list.get(i).getAdvancePayment() == null){
						row.createCell(17).setCellValue("");
					}else{
						row.createCell(17).setCellValue(list.get(i).getAdvancePayment());
					}
					
					if(list.get(i).getRestRate() == null){
						row.createCell(18).setCellValue("");
					}else{
						row.createCell(18).setCellValue(list.get(i).getRestRate());
					}
					if(list.get(i).getRestPayment() == null){
						row.createCell(19).setCellValue("");
					}else{
						row.createCell(19).setCellValue(list.get(i).getRestPayment());
					}
				}
			}
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return wb;
	}
	
	public static HSSFWorkbook exportPurchaseOrders(BizPurchaseVo bizPurchaseVo,BizEmployee2 employee,List<Auxiliary> list){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(bizPurchaseVo.getCustomerName()+"--"+bizPurchaseVo.getItemManager());

		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);
		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);
		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);


		sheet.setColumnWidth(0, 6000);
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
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 3000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		sheet.setColumnWidth(16, 3000);
		sheet.setColumnWidth(17, 3000);
		sheet.setColumnWidth(18, 3000);
		sheet.setColumnWidth(19, 8000);
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
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 30));
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 

            for (int i = 0; i < 2; i++) {
                row = sheet.createRow(i);  
                for (int j = 0; j <= 30; j++) {  
                    cell = row.createCell(j);  
                    cell.setCellStyle(cellStyleCenter);  
                }  
            }  

            cell = sheet.getRow(0).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("采购单明细表"));  
            cell = sheet.getRow(1).getCell(0);
            cell.setCellValue(new HSSFRichTextString("单据编号"));  
            cell = sheet.getRow(1).getCell(1);  
            cell.setCellValue(new HSSFRichTextString("录单日期"));  
            cell = sheet.getRow(1).getCell(2);  
            cell.setCellValue(new HSSFRichTextString("分支机构编号"));  
            cell = sheet.getRow(1).getCell(3);  
            cell.setCellValue(new HSSFRichTextString("往来单位编号"));  
            cell = sheet.getRow(1).getCell(4);  
            cell.setCellValue(new HSSFRichTextString("结算单位编号"));  
            cell = sheet.getRow(1).getCell(5);  
            cell.setCellValue(new HSSFRichTextString("经手人编号"));  
            cell = sheet.getRow(1).getCell(6);  
            cell.setCellValue(new HSSFRichTextString("部门编号"));  
            cell = sheet.getRow(1).getCell(7);  
            cell.setCellValue(new HSSFRichTextString("出库仓库编号"));  
            cell = sheet.getRow(1).getCell(8);  
            cell.setCellValue(new HSSFRichTextString("币种编号"));  
            cell = sheet.getRow(1).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("收款期限"));
            cell = sheet.getRow(1).getCell(10);  
            cell.setCellValue(new HSSFRichTextString("运单号"));
            cell = sheet.getRow(1).getCell(11);  
            cell.setCellValue(new HSSFRichTextString("物件数量"));
            cell = sheet.getRow(1).getCell(12);  
            cell.setCellValue(new HSSFRichTextString("包装方式"));
            cell = sheet.getRow(1).getCell(13);  
            cell.setCellValue(new HSSFRichTextString("承运单位"));
            cell = sheet.getRow(1).getCell(14);  
            cell.setCellValue(new HSSFRichTextString("说明"));
            cell = sheet.getRow(1).getCell(15);  
            cell.setCellValue(new HSSFRichTextString("摘要"));
            cell = sheet.getRow(1).getCell(16);  
            cell.setCellValue(new HSSFRichTextString("明细仓库编号"));
            cell = sheet.getRow(1).getCell(17);  
            cell.setCellValue(new HSSFRichTextString("货位编号"));
            cell = sheet.getRow(1).getCell(18);  
            cell.setCellValue(new HSSFRichTextString("商品编号"));
            cell = sheet.getRow(1).getCell(19);  
            cell.setCellValue(new HSSFRichTextString("商品名称"));
            cell = sheet.getRow(1).getCell(20);  
            cell.setCellValue(new HSSFRichTextString("自由项"));
            cell = sheet.getRow(1).getCell(21);  
            cell.setCellValue(new HSSFRichTextString("批号"));
            cell = sheet.getRow(1).getCell(22);  
            cell.setCellValue(new HSSFRichTextString("生产日期"));
            cell = sheet.getRow(1).getCell(23);  
            cell.setCellValue(new HSSFRichTextString("效期至"));
            cell = sheet.getRow(1).getCell(24);  
            cell.setCellValue(new HSSFRichTextString("销售数量"));
            cell = sheet.getRow(1).getCell(25);  
            cell.setCellValue(new HSSFRichTextString("销售单位编号"));
            cell = sheet.getRow(1).getCell(26);  
            cell.setCellValue(new HSSFRichTextString("销售单价"));
            cell = sheet.getRow(1).getCell(27);  
            cell.setCellValue(new HSSFRichTextString("浮动单位数量"));
            cell = sheet.getRow(1).getCell(28);  
            cell.setCellValue(new HSSFRichTextString("扣率"));
            cell = sheet.getRow(1).getCell(29);  
            cell.setCellValue(new HSSFRichTextString("税率"));
            cell = sheet.getRow(1).getCell(30);  
            cell.setCellValue(new HSSFRichTextString("备注"));
           
            

            
            for (int i= 0; i<list.size();i++) {
            	row = sheet.createRow(i+2);
            	for(int j = 0; j< 30; j++ ){
					row.createCell(0).setCellValue(bizPurchaseVo.getPurchaseCode());
					row.createCell(1).setCellValue(DateUtils.formatDate(bizPurchaseVo.getApplyTime()));
					row.createCell(2).setCellValue("");
					row.createCell(3).setCellValue("");
					row.createCell(4).setCellValue("");
					row.createCell(5).setCellValue("");
					row.createCell(6).setCellValue("");
					row.createCell(7).setCellValue("");
					row.createCell(8).setCellValue("");
					row.createCell(9).setCellValue("");
					row.createCell(10).setCellValue("");
					row.createCell(11).setCellValue("");
					row.createCell(12).setCellValue("");
					row.createCell(13).setCellValue("");
					row.createCell(14).setCellValue("");
					row.createCell(15).setCellValue("");
					row.createCell(16).setCellValue("");
					row.createCell(17).setCellValue("");
					row.createCell(18).setCellValue(list.get(i).getAuxiMateCode());
					row.createCell(19).setCellValue(StringEscapeUtils.unescapeHtml(list.get(i).getAuxiliaryMaterialsName()));
					row.createCell(20).setCellValue("");
					row.createCell(21).setCellValue("");
					row.createCell(22).setCellValue("");
					row.createCell(23).setCellValue("");
					row.createCell(24).setCellValue(list.get(i).getAuxiMateCount());
					row.createCell(25).setCellValue("");
					row.createCell(26).setCellValue(list.get(i).getLaborPrice());
					row.createCell(27).setCellValue("");
					row.createCell(28).setCellValue("");
					row.createCell(29).setCellValue("");
					row.createCell(30).setCellValue("");
            	}}
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return wb;
	}
		
	}

