package cn.damei.service.modules;

import cn.mdni.commons.excel.export.ExportSingleSheetHelper;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.Order2;
import cn.damei.dao.modules.BizProjectProgressSummaryDataDao;
import cn.damei.entity.modules.BizProjectProgressQueryRuleConfig;
import cn.damei.entity.modules.BizProjectProgressSummaryData;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.text.ParseException;
import java.util.*;

/**
 * 订单工程进度大看板
 * Created by hyh on 2017/12/13.
 */
@Service
@Transactional(readOnly = true)
public class BizProjectProgressSummaryDataService {
    @Autowired
    private BizProjectProgressSummaryDataDao bizProjectProgressSummaryDataDao;
    @Autowired
    private BizProjectProgressQueryRuleConfigService bizProjectProgressQueryRuleConfigService;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Order2> queryOrderByCondition2(){
        return bizProjectProgressSummaryDataDao.queryOrderByCondition2();
    }

    /**
     * 更新大看板消息
     * @param orderId
     */
    @Transactional(readOnly = false)
    public void updateBizProjectProgressSummaryData(Integer orderId){
        Date date = new Date();
        int count = bizProjectProgressSummaryDataDao.queryCountByOrderId(orderId);
        Order2 order = bizProjectProgressSummaryDataDao.queryOrderById(orderId);
        String sql = "";
        Map<String, Object> allMap = new HashMap<String, Object>();
        //获取规则中大看板的所有字段
        List<BizProjectProgressQueryRuleConfig> firstList = bizProjectProgressQueryRuleConfigService.findFirstList();
        String dataSql = null;
        if (CollectionUtils.isNotEmpty(firstList)) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("orderId", orderId);
            for (BizProjectProgressQueryRuleConfig config : firstList) {
                if (config.getIsSpecial().equals(0)) {
                    String confSql = config.getMainSqlContent();
                    confSql = confSql.replace("&gt;",">");
                    confSql = confSql.replace("&lt;","<");
                    confSql = confSql.replace("&nbsp;"," ");
                    List<Map<String, Object>> listMap = namedParameterJdbcTemplate.queryForList(confSql, param);
                    if(listMap != null && listMap.size() > 0){
                        allMap.putAll(listMap.get(0));
                    }

                }
            }
        }
        if (allMap.size() > 0) {
            int keyCount = 1;
            if (count > 0) {//修改
                sql = "update biz_project_progress_summary_data set update_time=:update_time,update_status=:update_status,";
                StringBuilder sb = new StringBuilder();
                for (String key : allMap.keySet()) {
                    sb.append(key + "=:" + key);
                    if (keyCount < allMap.size()) {
                        sb.append(",");
                    }
                    keyCount++;
                }
                sql = sql + sb.toString() + " where order_id = :orderId";

            } else {//新增
                sql = "insert into biz_project_progress_summary_data(order_id,create_time,update_time,del_flag,update_status,";
                StringBuilder sb = new StringBuilder();
                StringBuilder values = new StringBuilder();
                values.append(" VALUES ( :orderId,:create_time,:update_time,:del_flag,:update_status,");
                for (String key : allMap.keySet()) {
                    sb.append(key);
                    values.append(":" + key);
                    if (keyCount < allMap.size()) {
                        sb.append(",");
                        values.append(",");
                    }
                    if (keyCount == allMap.size()) {
                        sb.append(")");
                        values.append(")");
                    }
                    keyCount++;
                }
                allMap.put("create_time", date);
                allMap.put("del_flag", 0);
                sql = sql + sb.toString() + values.toString();
            }
            allMap.put("update_time", date);
            allMap.put("update_status", 1);
            allMap.put("orderId", orderId);
            namedParameterJdbcTemplate.update(sql, allMap);
            //订单已竣工或者订单作废
            if(order.getOrderStatusNumber().equals("400") || order.getIsScrap().equals("1")){
                Map<String,Object> orderMap = new HashMap<String,Object>();
                orderMap.put("orderId",orderId);
                String updateOrderSql = "update biz_order set is_to_refresh_process_data = 1 where id=:orderId";
                namedParameterJdbcTemplate.update(updateOrderSql, orderMap);
            }
        }

    }

    @Transactional(readOnly = false)
    public void updateErrorStatus(Integer orderId) {
        Date date = new Date();
        String sql;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("update_time", date);
        param.put("update_status", 2);
        param.put("orderId", orderId);
        sql = "update biz_project_progress_summary_data set update_time=:update_time,update_status=:update_status where order_id=:orderId";
        namedParameterJdbcTemplate.update(sql, param);
    }

    /**
     * 门店和工程模式权限控制
     * @param bizProjectProgressSummaryData
     * @param model
     */
    public void queryStoreIdAndProjectMode(BizProjectProgressSummaryData bizProjectProgressSummaryData, Model model) {
        User user = UserUtils.getUser();
        //门店
        if(null==bizProjectProgressSummaryData.getStoreId()){
            if(StringUtils.isNotBlank(user.getStoreId())){
                bizProjectProgressSummaryData.setStoreId(Integer.valueOf(user.getStoreId()));
            }
        }
        //工程模式
        if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
            model.addAttribute("gongcheng", true);
        }else{
            bizProjectProgressSummaryData.setProjectMode(user.getProjectMode());
        }
    }

    /**
     * 获取表头配置
     * @return
     */
    public List<BizProjectProgressQueryRuleConfig> queryRuleConfig() {
        List<BizProjectProgressQueryRuleConfig> AllList = bizProjectProgressQueryRuleConfigService.findFirstList();
        if (CollectionUtils.isNotEmpty(AllList)){
            for (BizProjectProgressQueryRuleConfig bizProjectProgressQueryRuleConfig :AllList){
                bizProjectProgressQueryRuleConfigService.recursionFindChildConfig(bizProjectProgressQueryRuleConfig);
            }
        }
        return AllList;
    }

    /**
     * 获取table的表头和数据配置对象
     * @param oneList
     * @return
     */
    public Map<String,Object> queryTableConfig(List<BizProjectProgressQueryRuleConfig> oneList) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        List<Map<String,Object>> listMapOne = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> listMapTwo = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> listMapThree = new ArrayList<Map<String,Object>>();
        for (BizProjectProgressQueryRuleConfig one : oneList){
            List<BizProjectProgressQueryRuleConfig> twoList = one.getChildList();
            int twoListSize = 0;
            if(CollectionUtils.isNotEmpty(twoList)){
                twoListSize = twoList.size();
            }
            if(one.getIsSpecial().equals(1)){
                if(twoListSize > 0){
                    for (BizProjectProgressQueryRuleConfig two : twoList) {
                        List<BizProjectProgressQueryRuleConfig> threeList = two.getChildList();
                        int threeListSize = 0;
                        if(CollectionUtils.isNotEmpty(threeList)){
                            threeListSize = threeList.size();
                        }
                        Map<String,Object> oneMap = new HashMap<String,Object>();
                        oneMap.put("ruleGrade","2");//等级（1级/2级/3级）标题
                        oneMap.put("enColumnName",two.getEnColumnName());//英文字段
                        oneMap.put("cnColumnName",two.getCnColumnName());//中文字段
                        if(threeListSize > 0) {
                            oneMap.put("cellMerge","2");//（1行/2列）合并单元格
                            oneMap.put("cellMergeLengh",threeListSize); //合格单元格长度
                            for (BizProjectProgressQueryRuleConfig three : threeList) {
                                Map<String,Object> twoMap = new HashMap<String,Object>();
                                twoMap.put("ruleGrade","3");//等级（1级/2级/3级）标题
                                twoMap.put("enColumnName",three.getEnColumnName());//英文字段
                                twoMap.put("cnColumnName",three.getCnColumnName());//中文字段
                                listMapTwo.add(twoMap);
                                listMapThree.add(twoMap);
                            }
                        }else {
                            oneMap.put("cellMerge","1");//（1行/2列）合并单元格
                            oneMap.put("cellMergeLengh",2); //合格单元格长度
                            listMapThree.add(oneMap);
                        }
                        listMapOne.add(oneMap);
                    }
                }
            }else{
                Map<String,Object> oneMap = new HashMap<String,Object>();
                oneMap.put("ruleGrade","1");//等级（1级/2级/3级）标题
                oneMap.put("enColumnName",one.getEnColumnName());//英文字段
                oneMap.put("cnColumnName",one.getCnColumnName());//中文字段
                if(twoListSize > 0) {
                    oneMap.put("cellMerge","2");//（1行/2列）合并单元格
                    oneMap.put("cellMergeLengh",twoListSize); //合格单元格长度
                    for (BizProjectProgressQueryRuleConfig two : twoList) {
                        Map<String,Object> twoMap = new HashMap<String,Object>();
                        twoMap.put("ruleGrade","2");//等级（1级/2级/3级）标题
                        twoMap.put("enColumnName",two.getEnColumnName());//英文字段
                        twoMap.put("cnColumnName",two.getCnColumnName());//中文字段
                        listMapTwo.add(twoMap);
                        listMapThree.add(twoMap);
                    }
                }else{
                    oneMap.put("cellMerge","1");//（1行/2列）合并单元格
                    oneMap.put("cellMergeLengh",2); //合格单元格长度
                    listMapThree.add(oneMap);
                }
                listMapOne.add(oneMap);
            }
        }
        resultMap.put("listMapOne",listMapOne);
        resultMap.put("listMapTwo",listMapTwo);
        resultMap.put("listMapThree",listMapThree);
        return resultMap;
    }

    /**
     * 订单工程进度大看板数据
     * @param page
     * @param bizProjectProgressSummaryData
     * @return
     */
    public Page<BizProjectProgressSummaryData> findPage(Page<BizProjectProgressSummaryData> page, BizProjectProgressSummaryData bizProjectProgressSummaryData) {
        bizProjectProgressSummaryData.setPage(page);
        List<BizProjectProgressSummaryData> list = bizProjectProgressSummaryDataDao.findList(bizProjectProgressSummaryData);
        querySummaryData(list);
        page.setList(list);
        return page;
    }

    /**
     * 大看板数据【动态获取】
     * @param list
     */
    public void querySummaryData(List<BizProjectProgressSummaryData> list) {

        if(CollectionUtils.isNotEmpty(list)) {
            //订单id集合
            List<Integer> orderIdList = new ArrayList<Integer>();
            //大看板id集合
            List<Integer> idList = new ArrayList<Integer>();
            for (BizProjectProgressSummaryData bizProjectProgressSummaryData : list) {
                idList.add(bizProjectProgressSummaryData.getId());
                orderIdList.add(bizProjectProgressSummaryData.getOrderId());
            }
            //从配置表中获取订单的查询SQL
            String orderRuleConfig = "SELECT main_sql_content FROM biz_project_progress_query_rule_config WHERE is_special = 1 AND parent_id IS NULL ORDER BY index_no ASC LIMIT 1";
            String orderSql = namedParameterJdbcTemplate.queryForObject(orderRuleConfig, EmptySqlParameterSource.INSTANCE, String.class);
            if (StringUtils.isNotBlank(orderSql)) {

                //获取订单数据List
                orderSql = orderSql + "  WHERE a.id IN (:orderIdList)";
                Map<String, Object> paramTwo = new HashMap<String, Object>();
                paramTwo.put("orderIdList", orderIdList);
                List<Map<String, Object>> orderList = namedParameterJdbcTemplate.queryForList(orderSql, paramTwo);

                //获取大看板的数据List
                String idSql = "SELECT * FROM biz_project_progress_summary_data WHERE id IN (:idList)";
                Map<String, Object> paramThree = new HashMap<String, Object>();
                paramThree.put("idList", idList);
                List<Map<String, Object>> summaryDataList = namedParameterJdbcTemplate.queryForList(idSql, paramThree);

                for (BizProjectProgressSummaryData one : list){
                    Map<String, Object> map = one.getMap();
                    for (Map two : orderList){
                        Integer orderIdOne = one.getOrderId();
                        Integer orderIdTwo = (Integer) two.get("order_id");
                        if(orderIdOne.equals(orderIdTwo)){
                            map.putAll(two);
                            break;
                        }
                    }
                    for (Map three : summaryDataList){
                        Integer idOne = one.getId();
                        Integer idThree = (Integer) three.get("id");
                        if(idOne.equals(idThree)){
                            map.putAll(three);
                            break;
                        }
                    }
                }
            }
        }

    }


    /**
     * 订单工程进度大看板--导出
     * @param bizProjectProgressSummaryData
     * @param ex
     * @throws ParseException
     */
    public void exportProjectProgressSummaryData(BizProjectProgressSummaryData bizProjectProgressSummaryData, ExportSingleSheetHelper<Map<String, Object>> ex) throws ParseException {

        //【1】数据准备
        //【门店】【工程模式】
        User user = UserUtils.getUser();
        if(null==bizProjectProgressSummaryData.getStoreId() && StringUtils.isNotBlank(user.getStoreId())){
                bizProjectProgressSummaryData.setStoreId(Integer.valueOf(user.getStoreId()));
        }
        if(StringUtils.isNotBlank(user.getProjectMode()) && !("3").equals(user.getProjectMode())){
            bizProjectProgressSummaryData.setProjectMode(user.getProjectMode());
        }
        //获取表头配置
        List<BizProjectProgressQueryRuleConfig> list = queryRuleConfig();
        //获取table的表头和数据配置对象
        Map<String,Object> map = queryTableConfig(list);
        //订单工程进度大看板数据
        List<BizProjectProgressSummaryData> dataList = bizProjectProgressSummaryDataDao.findList(bizProjectProgressSummaryData);
        querySummaryData(dataList);

        List<Map<String,Object>> listMapOne = (List<Map<String, Object>>) map.get("listMapOne");
        List<Map<String,Object>> listMapTwo = (List<Map<String, Object>>) map.get("listMapTwo");
        List<Map<String,Object>> listMapThree = (List<Map<String, Object>>) map.get("listMapThree");

        //【2】excel表头
        excelHeader(ex, listMapOne, listMapTwo);

        //【3】excel数据
        excelData(ex, dataList, listMapThree);
    }

    /**
     * 导出【表头】【excel】
     * @param ex
     * @param listMapOne
     * @param listMapTwo
     */
    private void excelHeader(ExportSingleSheetHelper<Map<String, Object>> ex, List<Map<String, Object>> listMapOne, List<Map<String, Object>> listMapTwo) {
        ex.setColSpanTarget("$");
        List<String> paramsOneList = new ArrayList<>();
        List<String> paramsTwoList = new ArrayList<>();
        int paramsTwoListIndex = 0;
        for (Map mapOne : listMapOne){
            String cnColumnName = (null == mapOne.get("cnColumnName"))?"":mapOne.get("cnColumnName").toString();
            String cellMerge = (null == mapOne.get("cellMerge"))?"":mapOne.get("cellMerge").toString();
            String cellMergeLengh = (null == mapOne.get("cellMergeLengh"))?"":mapOne.get("cellMergeLengh").toString();
            int length = 0;
            if(StringUtils.isNotBlank(cellMergeLengh)){
                length = Integer.valueOf(cellMergeLengh);
            }
            //（1行/2列）合并单元格
            if("1".equals(cellMerge)){
                paramsOneList.add(cnColumnName);
                paramsTwoList.add("");
            }else {
                for (int i = 0; i<length ; i++){
                    if(i < length - 1){
                        paramsOneList.add("$");
                    }else{
                        paramsOneList.add(cnColumnName);
                    }
                    Map<String, Object> mapTwo = listMapTwo.get(paramsTwoListIndex);
                    String cnColumnNameTwo = (null == mapTwo.get("cnColumnName"))?"":mapTwo.get("cnColumnName").toString();
                    paramsTwoList.add(cnColumnNameTwo);
                    paramsTwoListIndex++;
                }
            }
        }
        paramsOneList.add("更新状态");
        paramsTwoList.add("");
        String[] paramsOne = paramsOneList.toArray(new String[paramsOneList.size()]);
        String[] paramsTwo = paramsTwoList.toArray(new String[paramsTwoList.size()]);
        ex.darwRowColSpanNoBorder(0,paramsOne);
        ex.darwRowColSpanNoBorder(1,paramsTwo);

        //给单元格赋样式
        HSSFSheet sheet = ex.getSheet();
        int paramsTwoListSize = paramsTwoList.size();
        List<CellRangeAddress> ranges = new ArrayList<>();
        for (int k = 0; k < paramsTwoListSize;k++){
            String StringK = paramsTwoList.get(k);
            CellRangeAddress cellRangeAddressOne = new CellRangeAddress(0, 0, k, k);
            CellRangeAddress cellRangeAddressTwo = new CellRangeAddress(1, 1, k, k);
            CellRangeAddress cellRangeAddressThree = new CellRangeAddress(0, 1, k, k);
            if ("".equals(StringK)){
                sheet.addMergedRegion(cellRangeAddressThree);
                ranges.add(cellRangeAddressThree);
            }else{
                ranges.add(cellRangeAddressOne);
                ranges.add(cellRangeAddressTwo);
            }
        }
        setBorders(ranges,sheet,sheet.getWorkbook());
    }

    /**
     * 导出【表头】【样式】【excel】
     * @param ranges
     * @param sheet
     * @param workbook
     */
    public void setBorders(List<CellRangeAddress> ranges, HSSFSheet sheet, HSSFWorkbook workbook) {
        for (CellRangeAddress range : ranges) {
            RegionUtil.setBorderLeft(1, range, sheet, workbook);
            RegionUtil.setBorderBottom(1, range, sheet, workbook);
            RegionUtil.setBorderRight(1, range, sheet, workbook);
            RegionUtil.setBorderTop(1, range, sheet, workbook);
        }
    }

    /**
     * 导出【数据】【excel】
     * @param ex
     * @param dataList
     * @param listMapThree
     */
    private void excelData(ExportSingleSheetHelper<Map<String, Object>> ex, List<BizProjectProgressSummaryData> dataList, List<Map<String, Object>> listMapThree) {
        int index = 2;
        if(CollectionUtils.isNotEmpty(dataList)){
            for(BizProjectProgressSummaryData data : dataList){
                Map<String, Object> dataMap = data.getMap();
                List<String> paramsList = new ArrayList<>();
                for (Map mapThree : listMapThree){
                    String enColumnName = (null == mapThree.get("enColumnName"))?"":mapThree.get("enColumnName").toString();
                    String param = (null == dataMap.get(enColumnName))?"":dataMap.get(enColumnName).toString();
                    paramsList.add(param);
                }
                String updateStatus = "更新失败";
                if(StringUtils.isNotBlank(data.getUpdateStatus()) && "1".equals(data.getUpdateStatus())){
                    updateStatus = "更新成功";
                }
                paramsList.add(updateStatus);
                String[] params = paramsList.toArray(new String[paramsList.size()]);
                ex.darwRowColSpan(index,params);
                index++;
            }
        }
    }




}
