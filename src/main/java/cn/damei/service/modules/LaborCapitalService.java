package cn.damei.service.modules;

import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.utils.BizDictUtils;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.LaborCapitalDao;
import cn.damei.entity.modules.LaborCapital;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional(readOnly=true)
public class LaborCapitalService extends CrudService2<LaborCapitalDao, LaborCapital> {

    public void store(@ModelAttribute LaborCapital laborCapital, Model model) {
        if (laborCapital.getStoreId() == null) {
            String storeId = UserUtils.getUser().getStoreId();
            if (StringUtils.isNoneBlank(storeId)) {
                laborCapital.setStoreId(storeId);
            }
        }
        if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        }
    }
    /**
    * @Description: 导出
    * @Author zhangkangjian
    * @param
    * @return
    * @Date 2017/11/23 16:43
    */
    public void exportDetailExcel(LaborCapital laborCapital,HttpServletResponse response) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String fileName = "工人接单和罚款情况";
        HSSFWorkbook excel = export(laborCapital);
        //创建一个输出流对象
        ServletOutputStream out= null;
        try {
            response.setContentType("application/binary;charset=utf-8");
            //headerString为中文时转码
            String headerStr =new String(fileName.getBytes("utf-8"), "ISO8859-1");
            //filename是下载的xls的名称
            response.setHeader("Content-disposition","attachment; filename="+headerStr+".xls");
            out = response.getOutputStream();
            excel.write(out);
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    /**
     * @Description: 导出数据处理
     * @param @param delayBill
     * @param @return
     * @author zkj
     * @date 2017年10月26日 下午3:31:17
     */
    public HSSFWorkbook export(LaborCapital laborCapital) {

        SimpleDateFormat sfdatatime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sheetName = "工人接单和罚款情况";

        HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
        HSSFSheet sheet = wb.createSheet(sheetName);// 创建一个Excel的Sheet

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
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 4000);
        sheet.setColumnWidth(6, 4000);
        sheet.setColumnWidth(7, 5000);
        sheet.setColumnWidth(8, 3000);
        sheet.setColumnWidth(9, 3000);
        sheet.setColumnWidth(10, 3000);
        sheet.setColumnWidth(11, 3000);
        sheet.setColumnWidth(12, 5000);
        sheet.setColumnWidth(13, 5000);
        sheet.setColumnWidth(14, 3000);
        sheet.setColumnWidth(15, 3000);
        sheet.setColumnWidth(16, 3000);
        sheet.setColumnWidth(17, 3000);


        //标题---订单信息
       /* HSSFRow rowTitle21 = sheet.createRow(0);
        rowTitle21.setHeightInPoints(20);
        HSSFCell headCell01 = rowTitle21.createCell(0);
        headCell01.setCellStyle(columnHeadStyle);
        headCell01.setCellValue(sheetName);*/
      /*  int j = 16;
        for(int i=0;i<j;i++){
            HSSFCell cella = rowTitle21.createCell(i+1);
            cella.setCellStyle(columnHeadStyle);
        }*/
        //合并单元格--开始行，结束行，开始列，结束列
      /*  sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 16));*/

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 0));// 门店
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 1));//区域
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 2));//客户姓名
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 3));//客户地址
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 4));//项目经理
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 5));//质检员
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 6));//订单状态
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 7));//订单接单时间
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 8));//任务包类型
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 9));//任务包状态
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 10,10));//工人组长
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 11,11));//工人组组长手机号
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 12,12));//任务包开始时间
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 13,13));//任务包结束时间
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 14,14));//管理扣款
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 15,15));//延期扣款
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 16,16));//公司扣款




        HSSFRow rowTitle2 = sheet.createRow(0);
        HSSFCell headCell0 = rowTitle2.createCell(0);
        headCell0.setCellStyle(columnHeadStyle);
        headCell0.setCellValue("门店");

        HSSFCell headCell1 = rowTitle2.createCell(1);
        headCell1.setCellStyle(columnHeadStyle);
        headCell1.setCellValue("区域");

        HSSFCell headCell2 = rowTitle2.createCell(2);
        headCell2.setCellStyle(columnHeadStyle);
        headCell2.setCellValue("客户姓名");

        HSSFCell headCell3 = rowTitle2.createCell(3);
        headCell3.setCellStyle(columnHeadStyle);
        headCell3.setCellValue("客户地址");

        HSSFCell headCell4 = rowTitle2.createCell(4);
        headCell4.setCellStyle(columnHeadStyle);
        headCell4.setCellValue("项目经理");

        HSSFCell headCell5 = rowTitle2.createCell(5);
        headCell5.setCellStyle(columnHeadStyle);
        headCell5.setCellValue("质检员");

        HSSFCell headCell6 = rowTitle2.createCell(6);
        headCell6.setCellStyle(columnHeadStyle);
        headCell6.setCellValue("订单状态");

        HSSFCell headCell61 = rowTitle2.createCell(7);
        headCell61.setCellStyle(columnHeadStyle);
        headCell61.setCellValue("订单接单时间");

        HSSFCell headCell7 = rowTitle2.createCell(8);
        headCell7.setCellStyle(columnHeadStyle);
        headCell7.setCellValue("任务包类型");

        HSSFCell headCell8 = rowTitle2.createCell(9);
        headCell8.setCellStyle(columnHeadStyle);
        headCell8.setCellValue("任务包状态");


        HSSFCell headCell9 = rowTitle2.createCell(10);
        headCell9.setCellStyle(columnHeadStyle);
        headCell9.setCellValue("工人组长");

        HSSFCell headCell10 = rowTitle2.createCell(11);
        headCell10.setCellStyle(columnHeadStyle);
        headCell10.setCellValue("工人组组长手机号");

        HSSFCell headCell11 = rowTitle2.createCell(12);
        headCell11.setCellStyle(columnHeadStyle);
        headCell11.setCellValue("任务包开始时间");

        HSSFCell headCell13 = rowTitle2.createCell(13);
        headCell13.setCellStyle(columnHeadStyle);
        headCell13.setCellValue("任务包结束时间");

        HSSFCell headCell14 = rowTitle2.createCell(14);
        headCell14.setCellStyle(columnHeadStyle);
        headCell14.setCellValue("管理扣款");

        HSSFCell headCell15 = rowTitle2.createCell(15);
        headCell15.setCellStyle(columnHeadStyle);
        headCell15.setCellValue("延期扣款");

        HSSFCell headCell16 = rowTitle2.createCell(16);
        headCell16.setCellStyle(columnHeadStyle);
        headCell16.setCellValue("公司扣款");


        /*List<DelayBill> list = new ArrayList<>();*/

        List<LaborCapital> list = dao.exportQuery(laborCapital);
        if(CollectionUtils.isNotEmpty(list)){
            for(int i=0;i<list.size();i++){
                LaborCapital sd = list.get(i);
                HSSFRow row = sheet.createRow(i+1);

                HSSFCell cell0 = row.createCell(0);
                cell0.setCellStyle(columnStyle);
                cell0.setCellValue(BizDictUtils.getStoreLabel(sd.getStoreId(), ""));

                HSSFCell cell1 = row.createCell(1);
                cell1.setCellStyle(columnStyle);
                if (StringUtils.isNotBlank(sd.getDepartmentName() ) ) {
                    cell1.setCellValue(sd.getDepartmentName());
                }

                HSSFCell cell2 = row.createCell(2);
                cell2.setCellStyle(columnStyle);
                if (StringUtils.isNotBlank(sd.getCustumer()) ) {
                    cell2.setCellValue(sd.getCustumer() );
                }

                HSSFCell cell3 = row.createCell(3);
                cell3.setCellStyle(columnStyle);
                if (StringUtils.isNotBlank(sd.getCustomerAddress())) {
                    cell3.setCellValue(sd.getCustomerAddress());
                }

                HSSFCell cell4 = row.createCell(4);
                cell4.setCellStyle(columnStyle);
                if (StringUtils.isNotBlank(sd.getItemManager())) {
                    cell4.setCellValue(sd.getItemManager());
                }

                HSSFCell cell5 = row.createCell(5);
                cell5.setCellStyle(columnStyle);
                if (StringUtils.isNotBlank(sd.getInspectorName())) {
                    cell5.setCellValue(sd.getInspectorName());
                }

                HSSFCell cell6 = row.createCell(6);
                cell6.setCellStyle(columnStyle);
                if (StringUtils.isNotBlank(sd.getOrderStatusDescription())) {
                    cell6.setCellValue(sd.getOrderStatusDescription());
                }

                HSSFCell cell8 = row.createCell(7);
                cell8.setCellStyle(columnStyle);
                if (sd.getGetOrderDatetime()!=null) {
                    cell8.setCellValue(DateUtils.formatDateTime(sd.getGetOrderDatetime()));
                }

                HSSFCell cell9 = row.createCell(8);
                cell9.setCellStyle(columnStyle);
                if (StringUtils.isNotBlank(sd.getTaskPackageType())) {
                    cell9.setCellValue(BizDictUtils.getTaskPackageTypeLabel(sd.getTaskPackageType(),""));
                    /*BizDictUtils.getStoreLabel(sd.getStoreId(), "")*/
                }

                HSSFCell cell10 = row.createCell(9);
                cell10.setCellStyle(columnStyle);
                if (StringUtils.isNotBlank(sd.getPackageStateId())) {
                    cell10.setCellValue(sd.getPackageStatename());
                }

                HSSFCell cell11 = row.createCell(10);
                cell11.setCellStyle(columnStyle);
                if (StringUtils.isNotBlank(sd.getGroupLeaderName())) {
                    cell11.setCellValue(sd.getGroupLeaderName());
                }

                HSSFCell cell20 = row.createCell(11);
                cell20.setCellStyle(columnStyle);
                if(StringUtils.isNotBlank(sd.getGroupLeaderPhone())){
                    cell20.setCellValue(sd.getGroupLeaderPhone() );
                }


                HSSFCell cell13 = row.createCell(12);
                cell13.setCellStyle(columnStyle);
                if(sd.getActualStartdate()!= null){
                    cell13.setCellValue(DateUtils.formatDateTime(sd.getActualStartdate()));
                }

                HSSFCell cell14= row.createCell(13);
                cell14.setCellStyle(columnStyle);
                if(sd.getActualEnddate() != null){
                    cell14.setCellValue(DateUtils.formatDateTime(sd.getActualEnddate()));
                }

                HSSFCell cell15 = row.createCell(14);
                cell15.setCellStyle(columnStyle);
                if(sd.getPunishAmerce() != null && sd.getPunishAmerce() > 0){
                    cell15.setCellValue(sd.getPunishAmerce() );
                }

                HSSFCell cell16 = row.createCell(15);
                cell16.setCellStyle(columnStyle);
                if(sd.getDelayAmerce() != null && sd.getDelayAmerce() > 0){
                    cell16.setCellValue(sd.getDelayAmerce() );
                }

                HSSFCell cell17 = row.createCell(16);
                cell17.setCellStyle(columnStyle);
                if(sd.getCompanyDeductAmount() != null && sd.getCompanyDeductAmount() > 0){
                    cell17.setCellValue(sd.getCompanyDeductAmount());
                }
            }
        }
        return wb;
    }

}
