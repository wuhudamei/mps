package cn.damei.service.modules;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.ListSortUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.EngineDepartSyntheticQueryDao;
import cn.damei.entity.modules.EngineDepartEntity;
import cn.damei.entity.modules.ManagerSyntheticStarEntity;
import cn.damei.entity.modules.EngineDepartSyntheticQueryEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by joseph on 2017/4/7.
 */
@Service
@Transactional(readOnly = false)
public class EngineDepartSyntheticQueryService extends CrudService<EngineDepartSyntheticQueryDao, EngineDepartSyntheticQueryEntity> {

    @Autowired
    private EngineDepartSyntheticQueryDao dao;


    //工程日报 (门店+开始日期和结束日期  查询 订单 +派单+交底+开工数量)
    public List<Map<String, Object>> selectEngineDepartSyntheticList(EngineDepartSyntheticQueryEntity entity) {


        Map<String, Object> paraMap = new HashMap<>(48);

        paraMap.put("startDate", entity.getStartDate());
        paraMap.put("endDate", entity.getEndDate());


        List<Map<String, Object>> engineMapList = dao.findEngineByStoreId(String.valueOf(entity.getStoreId()));
        paraMap.put("list", engineMapList);

        List<Map<String, Object>> orderCountByEngine = dao.findOrderCountByEngine(paraMap);
        List<Map<String, Object>> discloseCountByEngine = dao.findDiscloseCountByEngine(paraMap);
        List<Map<String, Object>> startCountByEngine = dao.findStartCountByEngine(paraMap);
        List<Map<String, Object>> distributeCountByEngine = dao.findDistributeCountByEngine(paraMap);
        int size = engineMapList.size();


        int orderSize = orderCountByEngine.size();
        int disCloseSize = discloseCountByEngine.size();
        int startSize = startCountByEngine.size();
        int DistributeSize = distributeCountByEngine.size();
        if (size > 0) {

            for (int i = 0; i < size; i++) {

                Map<String, Object> map = engineMapList.get(i);


                if (orderSize > 0) {

                    for (int v = 0; v < orderSize; v++) {

                        if (String.valueOf(map.get("engineDepartId")).equals(String.valueOf(orderCountByEngine.get(v).get("departId")))) {

                            map.put("accpetOrderCount", orderCountByEngine.get(v).get("count"));
                            break;

                        }

                    }
                }

                if (disCloseSize > 0) {

                    for (int v = 0; v < disCloseSize; v++) {

                        if (String.valueOf(map.get("engineDepartId")).equals(String.valueOf(discloseCountByEngine.get(v).get("departId")))) {

                            map.put("distributeOrderCount", discloseCountByEngine.get(v).get("count"));
                            break;
                        }

                    }
                }

                if (startSize > 0) {

                    for (int v = 0; v < startSize; v++) {

                        if (String.valueOf(map.get("engineDepartId")).equals(String.valueOf(startCountByEngine.get(v).get("departId")))) {
                            map.put("contractStartDayCount", startCountByEngine.get(v).get("count"));
                            break;
                        }

                    }
                }

                if (DistributeSize > 0) {

                    for (int v = 0; v < DistributeSize; v++) {

                        if (String.valueOf(map.get("engineDepartId")).equals(String.valueOf(distributeCountByEngine.get(v).get("departId")))) {
                            map.put("discloseOrderCount", distributeCountByEngine.get(v).get("count"));

                            break;

                        }

                    }
                }


            }


        }


        return engineMapList;

    }

    public HSSFWorkbook exportExcel(EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity) {
        engineDepartSyntheticQueryEntity.setOrderApplyCompleteStatus(OrderConstantUtil.ORDER_STATUS_APPLY_COMPLETION_300);
        engineDepartSyntheticQueryEntity.setOrderDiscloseStatus(OrderConstantUtil.ORDER_STATUS_DISCLOSE_130);
        engineDepartSyntheticQueryEntity.setOrderConfirmStartStatus(OrderConstantUtil.ORDER_STATUS_CONFIRM_START_200);
        engineDepartSyntheticQueryEntity.setOrderDistributeManagerStatus(OrderConstantUtil.ORDER_STATUS_DISTRIBUTE_MANAGER_120);
        engineDepartSyntheticQueryEntity.setOrderInspectionCheckPassStatus(OrderConstantUtil.ORDER_STATUS_INSPECTION_CHECK_PASS_320);


        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("工程部总表");


        //单元格样式--标题
        HSSFCellStyle columnHeadStyle = wb.createCellStyle();
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

        //单元格样式
        //设置字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFFont.COLOR_NORMAL);//字体颜色
        font.setFontName("宋体");//字体
        font.setFontHeightInPoints((short) 11);//字体高度
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//宽度

        HSSFCellStyle title = wb.createCellStyle();
        title.setFont(font);
        title.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        title.setLeftBorderColor(HSSFColor.BLACK.index); // 左边框线的颜色
        title.setBorderLeft((short) 1);// 左边框线的大小
        title.setRightBorderColor(HSSFColor.BLACK.index); // 右边框线的颜色
        title.setBorderRight((short) 1);// 右边框线的大小
        title.setTopBorderColor(HSSFColor.BLACK.index); // 上边框线的颜色
        title.setBorderTop((short) 1);// 上边框线的大小
        title.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框线的颜色
        title.setBorderBottom((short) 1);// 下边框线的大小
        title.setAlignment(HSSFCellStyle.ALIGN_CENTER);


        // 单元格宽度
        sheet.setColumnWidth(0, 1233);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 3000);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 3000);
        sheet.setColumnWidth(6, 3000);
        sheet.setColumnWidth(7, 3000);
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


        //标题---工程部统计数据
        HSSFRow rowTitle = sheet.createRow(0);
        rowTitle.setHeightInPoints(40);
        HSSFCell cell = rowTitle.createCell(1);
        cell.setCellStyle(title);
        cell.setCellValue(new HSSFRichTextString("工程部统计数据 " + engineDepartSyntheticQueryEntity.getQueryDate()));
        for (int i = 0; i < 19; i++) {
            HSSFCell celltitle = rowTitle.createCell(i + 2);
            celltitle.setCellStyle(title);
        }


        //标题
        HSSFRow rowTitle2 = sheet.createRow(1);

        HSSFCell headCell1 = rowTitle2.createCell(1);
        headCell1.setCellStyle(columnHeadStyle);
        headCell1.setCellValue("序号");

        HSSFCell headCell2 = rowTitle2.createCell(2);
        headCell2.setCellStyle(columnHeadStyle);
        headCell2.setCellValue("大区");

        HSSFCell headCell3 = rowTitle2.createCell(3);
        headCell3.setCellStyle(columnHeadStyle);
        headCell3.setCellValue("接单数");

        HSSFCell headCell4 = rowTitle2.createCell(4);
        headCell4.setCellStyle(columnHeadStyle);

        HSSFCell headCell5 = rowTitle2.createCell(5);
        headCell5.setCellStyle(columnHeadStyle);
        headCell5.setCellValue("派单数");

        HSSFCell headCell6 = rowTitle2.createCell(6);
        headCell6.setCellStyle(columnHeadStyle);

        HSSFCell headCell7 = rowTitle2.createCell(7);
        headCell7.setCellStyle(columnHeadStyle);
        headCell7.setCellValue("设计交底数");

        HSSFCell headCell8 = rowTitle2.createCell(8);
        headCell8.setCellStyle(columnHeadStyle);

        HSSFCell headCell9 = rowTitle2.createCell(9);
        headCell9.setCellStyle(columnHeadStyle);
        headCell9.setCellValue("实际开工数");

        HSSFCell headCell10 = rowTitle2.createCell(10);
        headCell10.setCellStyle(columnHeadStyle);

        HSSFCell headCell11 = rowTitle2.createCell(11);
        headCell11.setCellStyle(columnHeadStyle);
        headCell11.setCellValue("在建工地数");

        HSSFCell headCell12 = rowTitle2.createCell(12);
        headCell12.setCellStyle(columnHeadStyle);
        headCell12.setCellValue("基装施工（第40天基装验收)");


        HSSFCell headCell13 = rowTitle2.createCell(13);
        headCell13.setCellStyle(columnHeadStyle);

        HSSFCell headCell14 = rowTitle2.createCell(14);
        headCell14.setCellStyle(columnHeadStyle);


        HSSFCell headCell15 = rowTitle2.createCell(15);
        headCell15.setCellStyle(columnHeadStyle);
        headCell15.setCellValue("主材施工（第60天竣工验收)");

        HSSFCell headCell16 = rowTitle2.createCell(16);
        headCell16.setCellStyle(columnHeadStyle);

        HSSFCell headCell17 = rowTitle2.createCell(17);
        headCell17.setCellStyle(columnHeadStyle);


        HSSFCell headCell18 = rowTitle2.createCell(18);
        headCell18.setCellStyle(columnHeadStyle);
        headCell18.setCellValue("合同约定工期");

        HSSFCell headCell19 = rowTitle2.createCell(19);
        headCell19.setCellStyle(columnHeadStyle);

        HSSFCell headCell20 = rowTitle2.createCell(20);
        headCell20.setCellStyle(columnHeadStyle);


        //标题
        HSSFRow rowTitleTwo2 = sheet.createRow(2);

        HSSFCell headCellTwo1 = rowTitleTwo2.createCell(1);
        headCellTwo1.setCellStyle(columnHeadStyle);

        HSSFCell headCellTwo2 = rowTitleTwo2.createCell(2);
        headCellTwo2.setCellStyle(columnHeadStyle);

        HSSFCell headCellTwo3 = rowTitleTwo2.createCell(3);
        headCellTwo3.setCellStyle(columnHeadStyle);
        headCellTwo3.setCellValue("累计");

        HSSFCell headCellTwo4 = rowTitleTwo2.createCell(4);
        headCellTwo4.setCellStyle(columnHeadStyle);
        headCellTwo4.setCellValue("当天");

        HSSFCell headCellTwo5 = rowTitleTwo2.createCell(5);
        headCellTwo5.setCellStyle(columnHeadStyle);
        headCellTwo5.setCellValue("累计");

        HSSFCell headCellTwo6 = rowTitleTwo2.createCell(6);
        headCellTwo6.setCellStyle(columnHeadStyle);
        headCellTwo6.setCellValue("当天");

        HSSFCell headCellTwo7 = rowTitleTwo2.createCell(7);
        headCellTwo7.setCellStyle(columnHeadStyle);
        headCellTwo7.setCellValue("累计");

        HSSFCell headCellTwo8 = rowTitleTwo2.createCell(8);
        headCellTwo8.setCellStyle(columnHeadStyle);
        headCellTwo8.setCellValue("当天");

        HSSFCell headCellTwo9 = rowTitleTwo2.createCell(9);
        headCellTwo9.setCellStyle(columnHeadStyle);
        headCellTwo9.setCellValue("累计");

        HSSFCell headCellTwo10 = rowTitleTwo2.createCell(10);
        headCellTwo10.setCellStyle(columnHeadStyle);
        headCellTwo10.setCellValue("当天");

        HSSFCell headCellTwo11 = rowTitleTwo2.createCell(11);
        headCellTwo11.setCellStyle(columnHeadStyle);


        HSSFCell headCellTwo12 = rowTitleTwo2.createCell(12);
        headCellTwo12.setCellStyle(columnHeadStyle);
        headCellTwo12.setCellValue("基装施工中");


        HSSFCell headCellTwo13 = rowTitleTwo2.createCell(13);
        headCellTwo13.setCellStyle(columnHeadStyle);
        headCellTwo13.setCellValue("基装延期数");

        HSSFCell headCellTwo14 = rowTitleTwo2.createCell(14);
        headCellTwo14.setCellStyle(columnHeadStyle);
        headCellTwo14.setCellValue("基装延期占比");

        HSSFCell headCellTwo15 = rowTitleTwo2.createCell(15);
        headCellTwo15.setCellStyle(columnHeadStyle);
        headCellTwo15.setCellValue("主材施工中");

        HSSFCell headCellTwo16 = rowTitleTwo2.createCell(16);
        headCellTwo16.setCellStyle(columnHeadStyle);
        headCellTwo16.setCellValue("60天工期延期数");

        HSSFCell headCellTwo17 = rowTitleTwo2.createCell(17);
        headCellTwo17.setCellStyle(columnHeadStyle);
        headCellTwo17.setCellValue("60天工期延期占比");

        HSSFCell headCellTwo18 = rowTitleTwo2.createCell(18);
        headCellTwo18.setCellStyle(columnHeadStyle);
        headCellTwo18.setCellValue("施工中");

        HSSFCell headCellTwo19 = rowTitleTwo2.createCell(19);
        headCellTwo19.setCellStyle(columnHeadStyle);
        headCellTwo19.setCellValue("延期数");

        HSSFCell headCellTwo20 = rowTitleTwo2.createCell(20);
        headCellTwo20.setCellStyle(columnHeadStyle);
        headCellTwo20.setCellValue("延期占比");


        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 22));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 4));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 6));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 8));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 10));

        sheet.addMergedRegion(new CellRangeAddress(1, 2, 11, 11));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 12, 14));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 15, 17));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 18, 20));


        //数据
        List<EngineDepartSyntheticQueryEntity> list = dao.findList(engineDepartSyntheticQueryEntity);
        int j = 0;
        Integer totalAccept = 0;
        Integer totalAcceptNow = 0;

        Integer totalsend = 0;
        Integer totalsendNow = 0;

        Integer totalDisclose = 0;
        Integer totalDiscloseNow = 0;

        Integer totalConfirmStart = 0;
        Integer totalConfirmStartNow = 0;


        Integer totalbuild = 0;


        Double totalBasicStart = 0D;
        Double totalBasicStartDelay = 0D;
        String totalBasicPercent = "";


        Double totalMainStart = 0D;
        Double totalMainStartDelay = 0D;
        String totalMainPercent = "";


        Double contractStart = 0D;
        Double contractStartDelay = 0D;
        String contractStartPercent = "";

        if (CollectionUtils.isNotEmpty(list)) {
            Integer size = list.size();
            for (int i = 0; i < size; i++) {
                EngineDepartSyntheticQueryEntity item = list.get(i);

                HSSFRow row = sheet.createRow(i + 3);

                j = i + 1;

                HSSFCell cell1 = row.createCell(1);
                cell1.setCellStyle(columnStyle);
                cell1.setCellValue(i + 1);

                HSSFCell cell2 = row.createCell(2);
                cell2.setCellStyle(columnStyle);
                if (StringUtils.isNoneBlank(item.getEngineDepartName())) {
                    cell2.setCellValue(item.getEngineDepartName());
                }

                HSSFCell cell3 = row.createCell(3);
                cell3.setCellStyle(columnStyle);
                cell3.setCellValue(null == item.getAccpetOrderCount() ? 0 : item.getAccpetOrderCount());
                totalAccept += null == item.getAccpetOrderCount() ? 0 : item.getAccpetOrderCount();


                HSSFCell cell4 = row.createCell(4);
                cell4.setCellStyle(columnStyle);
                cell4.setCellValue(null == item.getAccpetOrderCountNow() ? 0 : item.getAccpetOrderCountNow());
                totalAcceptNow += null == item.getAccpetOrderCountNow() ? 0 : item.getAccpetOrderCountNow();


                HSSFCell cell5 = row.createCell(5);
                cell5.setCellStyle(columnStyle);
                cell5.setCellValue(null == item.getDistributeOrderCount() ? 0 : item.getDistributeOrderCount());
                totalsend += (null == item.getDistributeOrderCount() ? 0 : item.getDistributeOrderCount());


                HSSFCell cell6 = row.createCell(6);
                cell6.setCellStyle(columnStyle);
                cell6.setCellValue(null == item.getDistributeOrderCountNow() ? 0 : item.getDistributeOrderCountNow());
                totalsendNow += (null == item.getDistributeOrderCountNow() ? 0 : item.getDistributeOrderCountNow());


                HSSFCell cell7 = row.createCell(7);
                cell7.setCellStyle(columnStyle);
                cell7.setCellValue(null == item.getDiscloseOrderCount() ? 0 : item.getDiscloseOrderCount());
                totalDisclose += (null == item.getDiscloseOrderCount() ? 0 : item.getDiscloseOrderCount());

                HSSFCell cell8 = row.createCell(8);
                cell8.setCellStyle(columnStyle);
                cell8.setCellValue(null == item.getDiscloseOrderCountNow() ? 0 : item.getDiscloseOrderCountNow());
                totalDiscloseNow += (null == item.getDiscloseOrderCountNow() ? 0 : item.getDiscloseOrderCountNow());


                HSSFCell cell9 = row.createCell(9);
                cell9.setCellStyle(columnStyle);
                cell9.setCellValue(null == item.getActualStartOrderCount() ? 0 : item.getActualStartOrderCount());
                totalConfirmStart += (null == item.getActualStartOrderCount() ? 0 : item.getActualStartOrderCount());


                HSSFCell cell10 = row.createCell(10);
                cell10.setCellStyle(columnStyle);
                cell10.setCellValue(null == item.getActualStartOrderCountNow() ? 0 : item.getActualStartOrderCountNow());
                totalConfirmStartNow += (null == item.getActualStartOrderCountNow() ? 0 : item.getActualStartOrderCountNow());


                HSSFCell cell11 = row.createCell(11);
                cell11.setCellStyle(columnStyle);
                cell11.setCellValue(null == item.getBuildOrderIndustryCount() ? 0 : item.getBuildOrderIndustryCount());
                totalbuild += (null == item.getBuildOrderIndustryCount() ? 0 : item.getBuildOrderIndustryCount());


                HSSFCell cell12 = row.createCell(12);
                cell12.setCellStyle(columnStyle);
                cell12.setCellValue(null == item.getBasicDoneCount() ? 0 : item.getBasicDoneCount());
                totalBasicStart += (null == item.getBasicDoneCount() ? 0 : item.getBasicDoneCount());


                HSSFCell cell13 = row.createCell(13);
                cell13.setCellStyle(columnStyle);
                cell13.setCellValue(null == item.getBasicDelayCount() ? 0 : item.getBasicDelayCount());
                totalBasicStartDelay += (null == item.getBasicDelayCount() ? 0 : item.getBasicDelayCount());


                HSSFCell cell14 = row.createCell(14);
                cell14.setCellStyle(columnStyle);
                cell14.setCellValue(null == item.getBasicDelayPercent() ? "0%" : item.getBasicDelayPercent());


                HSSFCell cell15 = row.createCell(15);
                cell15.setCellStyle(columnStyle);
                cell15.setCellValue(null == item.getMainMaterialStartCount() ? 0 : item.getMainMaterialStartCount());
                totalMainStart += (null == item.getMainMaterialStartCount() ? 0 : item.getMainMaterialStartCount());


                HSSFCell cell16 = row.createCell(16);
                cell16.setCellStyle(columnStyle);
                cell16.setCellValue(null == item.getMainMaterialDaysDelayCount() ? 0 : item.getMainMaterialDaysDelayCount());
                totalMainStartDelay += (null == item.getMainMaterialDaysDelayCount() ? 0 : item.getMainMaterialDaysDelayCount());


                HSSFCell cell17 = row.createCell(17);
                cell17.setCellStyle(columnStyle);
                cell17.setCellValue(null == item.getMainMaterialDelayPercent() ? "0%" : item.getMainMaterialDelayPercent());


                HSSFCell cell18 = row.createCell(18);
                cell18.setCellStyle(columnStyle);
                cell18.setCellValue(null == item.getContractStartDayCount() ? 0 : item.getContractStartDayCount());

                contractStart += (null == item.getContractStartDayCount() ? 0 : item.getContractStartDayCount());


                HSSFCell cell19 = row.createCell(19);
                cell19.setCellStyle(columnStyle);
                cell19.setCellValue(null == item.getContractStartDayDelayCount() ? 0 : item.getContractStartDayDelayCount());
                contractStartDelay += (null == item.getContractStartDayDelayCount() ? 0 : item.getContractStartDayDelayCount());


                HSSFCell cell20 = row.createCell(20);
                cell20.setCellStyle(columnStyle);
                cell20.setCellValue(null == item.getContractStartDelayPercent() ? "0%" : item.getContractStartDelayPercent());


            }
        }

        HSSFRow row = sheet.createRow(3 + j);

        HSSFCell cell1 = row.createCell(1);
        cell1.setCellStyle(columnStyle);
        cell1.setCellValue("合计");

        sheet.addMergedRegion(new CellRangeAddress(3 + j, 3 + j, 1, 2));

        HSSFCell cell2 = row.createCell(2);
        cell2.setCellStyle(columnStyle);


        HSSFCell cell3 = row.createCell(3);
        cell3.setCellStyle(columnStyle);
        cell3.setCellValue(totalAccept);

        HSSFCell cell4 = row.createCell(4);
        cell4.setCellStyle(columnStyle);
        cell4.setCellValue(totalAcceptNow);

        HSSFCell cell5 = row.createCell(5);
        cell5.setCellStyle(columnStyle);
        cell5.setCellValue(totalsend);

        HSSFCell cell6 = row.createCell(6);
        cell6.setCellStyle(columnStyle);
        cell6.setCellValue(totalsendNow);

        HSSFCell cell7 = row.createCell(7);
        cell7.setCellStyle(columnStyle);
        cell7.setCellValue(totalDisclose);

        HSSFCell cell8 = row.createCell(8);
        cell8.setCellStyle(columnStyle);
        cell8.setCellValue(totalDiscloseNow);

        HSSFCell cell9 = row.createCell(9);
        cell9.setCellStyle(columnStyle);
        cell9.setCellValue(totalConfirmStart);

        HSSFCell cell10 = row.createCell(10);
        cell10.setCellStyle(columnStyle);
        cell10.setCellValue(totalConfirmStartNow);

        HSSFCell cell11 = row.createCell(11);
        cell11.setCellStyle(columnStyle);
        cell11.setCellValue(totalbuild);

        HSSFCell cell12 = row.createCell(12);
        cell12.setCellStyle(columnStyle);
        cell12.setCellValue(totalBasicStart);

        HSSFCell cell13 = row.createCell(13);
        cell13.setCellStyle(columnStyle);
        cell13.setCellValue(totalBasicStartDelay);


        HSSFCell cell14 = row.createCell(14);
        cell14.setCellStyle(columnStyle);

        DecimalFormat df2 = new DecimalFormat("###.00");

        if (totalBasicStart < 1) {
            cell14.setCellValue("0%");

        } else {
            totalBasicPercent = df2.format((totalBasicStartDelay * 100) / (totalBasicStart * 100)) + "%";

            totalBasicPercent = totalBasicPercent.replaceFirst("\\.", " ");
            if (totalBasicPercent.trim().startsWith("0")) {

                totalBasicPercent = totalBasicPercent.trim().replaceFirst("0", " ");

            }

            cell14.setCellValue(totalBasicPercent);

        }


        HSSFCell cell15 = row.createCell(15);
        cell15.setCellStyle(columnStyle);
        cell15.setCellValue(totalMainStart);

        HSSFCell cell16 = row.createCell(16);
        cell16.setCellStyle(columnStyle);
        cell16.setCellValue(totalMainStartDelay);


        HSSFCell cell17 = row.createCell(17);
        cell17.setCellStyle(columnStyle);

        if (totalMainStart < 1) {
            cell17.setCellValue("0%");

        } else {
            totalMainPercent = df2.format((totalMainStartDelay * 100) / (totalMainStart * 100)) + "%";
            totalMainPercent = totalMainPercent.trim().replaceFirst("\\.", " ");
            if (totalMainPercent.trim().startsWith("0")) {

                totalMainPercent = totalMainPercent.trim().replaceFirst("0", " ");

            }
            cell17.setCellValue(totalMainPercent);

        }


        HSSFCell cell18 = row.createCell(18);
        cell18.setCellStyle(columnStyle);
        cell18.setCellValue(contractStart);

        HSSFCell cell19 = row.createCell(19);
        cell19.setCellStyle(columnStyle);
        cell19.setCellValue(contractStartDelay);


        HSSFCell cell20 = row.createCell(20);
        cell20.setCellStyle(columnStyle);
        if (contractStart < 1) {
            cell20.setCellValue("0%");

        } else {
            contractStartPercent = df2.format((contractStartDelay * 100) / (contractStart * 100)) + "%";
            contractStartPercent = contractStartPercent.replaceFirst("\\.", " ");

            if (contractStartPercent.trim().startsWith("0")) {

                contractStartPercent = contractStartPercent.trim().replaceFirst("0", " ");

            }
            cell20.setCellValue(contractStartPercent);
        }

		/*
        HSSFCell cell21 = row.createCell(21);
		cell21.setCellStyle(columnStyle);
		cell21.setCellValue("");
		
		HSSFCell cell22 = row.createCell(22);
		cell22.setCellStyle(columnStyle);
		cell22.setCellValue("");*/

        return wb;

    }


    public HSSFWorkbook exportExcel2(EngineDepartSyntheticQueryEntity engineDepartSyntheticQueryEntity) {


        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("工程部总表");


        //单元格样式--标题
        HSSFCellStyle columnHeadStyle = wb.createCellStyle();
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

        //单元格样式
        //设置字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFFont.COLOR_NORMAL);//字体颜色
        font.setFontName("宋体");//字体
        font.setFontHeightInPoints((short) 11);//字体高度
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//宽度

        HSSFCellStyle title = wb.createCellStyle();
        title.setFont(font);
        title.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        title.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        title.setLeftBorderColor(HSSFColor.BLACK.index); // 左边框线的颜色
        title.setBorderLeft((short) 1);// 左边框线的大小
        title.setRightBorderColor(HSSFColor.BLACK.index); // 右边框线的颜色
        title.setBorderRight((short) 1);// 右边框线的大小
        title.setTopBorderColor(HSSFColor.BLACK.index); // 上边框线的颜色
        title.setBorderTop((short) 1);// 上边框线的大小
        title.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框线的颜色
        title.setBorderBottom((short) 1);// 下边框线的大小
        title.setAlignment(HSSFCellStyle.ALIGN_CENTER);


        // 单元格宽度
        sheet.setColumnWidth(0, 1233);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 3000);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 3000);
        sheet.setColumnWidth(6, 3000);


        //标题---工程部统计数据
        HSSFRow rowTitle = sheet.createRow(0);
        rowTitle.setHeightInPoints(40);
        HSSFCell cell = rowTitle.createCell(1);
        cell.setCellStyle(title);
        cell.setCellValue(new HSSFRichTextString("工程部统计数据 " + DateUtils.formatDate(engineDepartSyntheticQueryEntity.getStart(), "yyyy-MM-dd") + " 至 " + DateUtils.formatDate(engineDepartSyntheticQueryEntity.getEnd(), "yyyy-MM-dd")));
        for (int i = 0; i < 5; i++) {
            HSSFCell celltitle = rowTitle.createCell(i + 2);
            celltitle.setCellStyle(title);
        }


        //标题
        HSSFRow rowTitle2 = sheet.createRow(1);

        HSSFCell headCell1 = rowTitle2.createCell(1);
        headCell1.setCellStyle(columnHeadStyle);
        headCell1.setCellValue("门店");

        HSSFCell headCell2 = rowTitle2.createCell(2);
        headCell2.setCellStyle(columnHeadStyle);
        headCell2.setCellValue("大区");

        HSSFCell headCell3 = rowTitle2.createCell(3);
        headCell3.setCellStyle(columnHeadStyle);
        headCell3.setCellValue("接单数");

        HSSFCell headCell5 = rowTitle2.createCell(4);
        headCell5.setCellStyle(columnHeadStyle);
        headCell5.setCellValue("派单数");


        HSSFCell headCell7 = rowTitle2.createCell(5);
        headCell7.setCellStyle(columnHeadStyle);
        headCell7.setCellValue("设计交底数");


        HSSFCell headCell9 = rowTitle2.createCell(6);
        headCell9.setCellStyle(columnHeadStyle);
        headCell9.setCellValue("实际开工数");


//合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 6));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));


        //数据
        List<Map<String, Object>> list = this.selectEngineDepartSyntheticList(engineDepartSyntheticQueryEntity);
        int j = 0;
        Integer totalAccept = 0;
        Integer totalAcceptNow = 0;

        Integer totalsend = 0;
        Integer totalsendNow = 0;


        if (CollectionUtils.isNotEmpty(list)) {
            Integer size = list.size();
            for (int i = 0; i < size; i++) {
                Map<String, Object> item = list.get(i);

                HSSFRow row = sheet.createRow(i + 3);

                j = i + 1;

                HSSFCell cell1 = row.createCell(1);
                cell1.setCellStyle(columnStyle);
                cell1.setCellValue(String.valueOf(item.get("storeName")));

                HSSFCell cell2 = row.createCell(2);
                cell2.setCellStyle(columnStyle);
                if (StringUtils.isNoneBlank(item.get("engineDepartName").toString())) {
                    cell2.setCellValue(item.get("engineDepartName").toString());
                }

                HSSFCell cell3 = row.createCell(3);
                cell3.setCellStyle(columnStyle);
                cell3.setCellValue(null == item.get("accpetOrderCount") ? 0 : Integer.valueOf(item.get("accpetOrderCount").toString()));
                totalAccept += null == item.get("accpetOrderCount") ? 0 : Integer.valueOf(item.get("accpetOrderCount").toString());


                HSSFCell cell4 = row.createCell(4);
                cell4.setCellStyle(columnStyle);
                cell4.setCellValue(null == item.get("distributeOrderCount") ? 0 : Integer.valueOf(item.get("distributeOrderCount").toString()));
                totalAcceptNow += null == item.get("distributeOrderCount") ? 0 : Integer.valueOf(item.get("distributeOrderCount").toString());

                HSSFCell cell5 = row.createCell(5);
                cell5.setCellStyle(columnStyle);
                cell5.setCellValue(null == item.get("discloseOrderCount") ? 0 : Integer.valueOf(item.get("discloseOrderCount").toString()));
                totalsend += null == item.get("discloseOrderCount") ? 0 : Integer.valueOf(item.get("discloseOrderCount").toString());


                HSSFCell cell6 = row.createCell(6);
                cell6.setCellStyle(columnStyle);
                cell6.setCellValue(null == item.get("contractStartDayCount") ? 0 : Integer.valueOf(item.get("contractStartDayCount").toString()));
                totalsendNow += null == item.get("contractStartDayCount") ? 0 : Integer.valueOf(item.get("contractStartDayCount").toString());

            }
        }

        HSSFRow row = sheet.createRow(3 + j);

        HSSFCell cell1 = row.createCell(1);
        cell1.setCellStyle(columnStyle);
        cell1.setCellValue("合计");

        sheet.addMergedRegion(new CellRangeAddress(3 + j, 3 + j, 1, 2));

        HSSFCell cell2 = row.createCell(2);
        cell2.setCellStyle(columnStyle);


        HSSFCell cell3 = row.createCell(3);
        cell3.setCellStyle(columnStyle);
        cell3.setCellValue(totalAccept);

        HSSFCell cell4 = row.createCell(4);
        cell4.setCellStyle(columnStyle);
        cell4.setCellValue(totalAcceptNow);

        HSSFCell cell5 = row.createCell(5);
        cell5.setCellStyle(columnStyle);
        cell5.setCellValue(totalsend);

        HSSFCell cell6 = row.createCell(6);
        cell6.setCellStyle(columnStyle);
        cell6.setCellValue(totalsendNow);







		/*
        HSSFCell cell21 = row.createCell(21);
		cell21.setCellStyle(columnStyle);
		cell21.setCellValue("");

		HSSFCell cell22 = row.createCell(22);
		cell22.setCellStyle(columnStyle);
		cell22.setCellValue("");*/

        return wb;

    }

    public List<EngineDepartSyntheticQueryEntity> pqcSyntheticQuery(EngineDepartSyntheticQueryEntity entity) {


        return dao.pqcSyntheticQuery(entity);
    }

    public List<Map<String, Object>> pqcSyntheticDataInfo(Map<String, Object> map) {


        return dao.pqcSyntheticDataInfo(map);
    }

    public List<Map<String, Object>> pqcSyntheticDataInfoForSameDay(Map<String, Object> map) {


        return dao.pqcSyntheticDataInfoForSameDay(map);
    }


    /**
     * 2017-08-17  统计报表 需求
     */


    private final String dictType = "manager_star";

    /**
     * 根据门店和模式查询大区下的经理星级
     *
     * @param storeId
     * @param projectMode
     * @return
     */
    public List<EngineDepartEntity> findManagerStarGroupByEngineDepartByStoreIdAndProjectMode(String storeId, String projectMode) {

        List<EngineDepartEntity> list = dao.findManagerStarGroupByEngineDepartByStoreIdAndProjectMode(storeId, projectMode);

        int listSize = list.size();

        if (listSize > 0) {

            List<Integer> valueList = dao.getDictListByTypeOrderByValue(dictType);

            int valueListSize = valueList.size();

            ListSortUtils<ManagerSyntheticStarEntity> utils = new ListSortUtils<>();
            for (int i = 0; i < listSize; i++) {

                List<ManagerSyntheticStarEntity> entities = list.get(i).getList();

                //兼容那些星级不满的大区
                if (entities.size() > 0 && entities.size() != valueListSize) {

                    int distanceSize = entities.size();


                    for (int v = 0; v < valueListSize; v++) {
                        int changeVariable = 0;

                        for (int f = 0; f < distanceSize; f++) {


                            if (valueList.get(v).equals(entities.get(f).getStar_level())) {

                                changeVariable = 1;

                                break;
                            }


                        }


                        if(0==changeVariable){



                            ManagerSyntheticStarEntity entity = new ManagerSyntheticStarEntity();
                            entity.setStar(0);
                            entity.setStar_level(valueList.get(v));
                            entities.add(entity);



                        }






                    }


                    utils.sort(entities, "star_level", "ASC");


                }


            }

        }


        return list;
    }


    /**
     * 2017-08-17  统计报表 需求
     */





    private final String workerType="emp_work_type";
    /**
     * 根据门店和模式查询大区下的工人工种和数量
     *
     * @param storeId
     * @param projectMode
     * @return
     */
    public List<EngineDepartEntity> findWorkerTypeGroupByEngineDepartByStoreIdAndProjectMode(String storeId, String projectMode) {
        List<EngineDepartEntity> list =  dao.findWorkerTypeGroupByEngineDepartByStoreIdAndProjectMode(storeId, projectMode);

        int listSize = list.size();

        if (listSize > 0) {

            List<Integer> valueList = dao.getDictListByTypeOrderByValue(workerType);

            int valueListSize = valueList.size();

            ListSortUtils<ManagerSyntheticStarEntity> utils = new ListSortUtils<>();
            for (int i = 0; i < listSize; i++) {

                List<ManagerSyntheticStarEntity> entities = list.get(i).getList();

                //兼容那些工种不满的大区
                if (entities.size() > 0 && entities.size() != valueListSize) {

                    int distanceSize = entities.size();


                    for (int v = 0; v < valueListSize; v++) {
                        int changeVariable = 0;

                        for (int f = 0; f < distanceSize; f++) {


                            if (valueList.get(v).equals(entities.get(f).getStar_level())) {

                                changeVariable = 1;

                                break;
                            }


                        }


                        if(0==changeVariable){



                            ManagerSyntheticStarEntity entity = new ManagerSyntheticStarEntity();
                            entity.setStar(0);
                            entity.setStar_level(valueList.get(v));
                            entities.add(entity);



                        }






                    }


                    utils.sort(entities, "star_level", "ASC");


                }


            }

        }


        return list;


    }


    public List<String> findWorkerTypeList() {


        return dao.findWorkerTypeList();
    }


    /**
     * 根据门店+模式+时间段  查询 质检单的所有统计数据
     *
     * @return
     */

    private static Map<String, Object> map = null;

    static {

        map = new HashMap<>(12);

    }

    public Map<String, Object> pqcSyntheticWorkQuery(EngineDepartSyntheticQueryEntity entity) {

        Map<String, Object> returnMap = new HashMap<>(24);

        map.put("storeId", entity.getStoreId());
        map.put("projectMode", entity.getProjectMode());
        map.put("startDate", entity.getStart());
        map.put("endDate", entity.getEnd());


        Integer qcBillReportCount = dao.qcBillReportCount(map);
        Integer applyCheckTotalCount = dao.managerApplyCheckQcBillCount(map);
        Integer qcSignCount = dao.qcSignCount(map);
        Integer qcApplyCheckCount = dao.qcApplyCheckCount(map);
        Integer qcBillDoneCount = dao.qcBillDoneCount(map);
        Integer issueReportCount = dao.qcIssueReportCount(map);
        Integer qcRecheckCount = dao.qcRecheckCount(map);

        //fineOrderCount  fineMoneyTotal
        Map<String, Object> orderCountAndTotalMoney = dao.fineOrderCountAndTotalMoney(map);


        returnMap.put("qcBillReportCount", null == qcBillReportCount ? 0 : qcBillReportCount);


        returnMap.put("applyCheckTotalCount", null == applyCheckTotalCount ? 0 : applyCheckTotalCount);


        returnMap.put("qcSignCount", null == qcSignCount ? 0 : qcSignCount);

        returnMap.put("qcApplyCheckCount", null == qcApplyCheckCount ? 0 : qcApplyCheckCount);

        returnMap.put("qcBillDoneCount", null == qcBillDoneCount ? 0 : qcBillDoneCount);

        returnMap.put("issueReportCount", null == issueReportCount ? 0 : issueReportCount);

        returnMap.put("qcRecheckCount", null == qcRecheckCount ? 0 : qcRecheckCount);
        returnMap.put("fineOrderCount", null == orderCountAndTotalMoney.get("fineOrderCount") ? 0 : orderCountAndTotalMoney.get("fineOrderCount"));
        returnMap.put("fineMoneyTotal", null == orderCountAndTotalMoney.get("fineMoneyTotal") ? 0 : orderCountAndTotalMoney.get("fineMoneyTotal"));


        return returnMap;
    }

}
