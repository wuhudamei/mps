package cn.damei.service.mobile.Inspector;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.common.ProjectIssueUtil.Purchase.PurchaseCodeUtils;
import cn.damei.common.utils.PqcUtil;
import cn.damei.dao.mobile.Inspector.CheckItemDao;
import cn.damei.entity.mobile.Inspector.BalanceFine;
import cn.damei.entity.mobile.Inspector.IllegalModality;
import cn.damei.entity.mobile.Inspector.InspectItem;
import cn.damei.entity.mobile.Inspector.InspectKind;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import cn.damei.entity.mobile.Inspector.Recheck;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.QualityControl;
import cn.damei.web.mobile.home.JobSiteController;
import cn.damei.entity.modules.BizMsg;
import cn.damei.service.modules.BizProjectChangeBillService;

@Service
@Transactional(readOnly = true)
public class CheckItemService extends CrudService2<CheckItemDao, InspectKind> {
    @Autowired
    private PhoneMessageDao messageDao;
    @Autowired
    private BizProjectChangeBillService bizProjectChangeBillService;

    public InspectKind useInspectIdToFindInspectBillByOrderIdOrderByDateLimitOneInOrderToCheckTimeIsAllowedWithFiveMinutes(Integer inspectId) {

        return dao.useInspectIdToFindInspectBillByOrderIdOrderByDateLimitOneInOrderToCheckTimeIsAllowedWithFiveMinutes(inspectId);
    }

    /**
     * 根据门店查询检查分类和检查项
     *
     * @return
     */
    public List<InspectKind> selectAllCheckItem(Integer storeId) {

        return dao.selectAllCheckItem(storeId);
    }

    /**
     * 保存质检员选择得检查项
     *
     * @param kind
     */
    @Transactional(readOnly = false)
    public void saveItems(InspectItem kind) {
        dao.saveItems(kind);
    }

    /**
     * 更新质检单的选择项 及违规形式记录
     *
     * @param request
     */
    @Transactional(readOnly = false)
    public void updateCheckItem(HttpServletRequest request) {

        InspectItem item = new InspectItem();

        item.setInspectBillId(Integer.parseInt(request.getParameter("inspectId")));

        // ====================================================检查项记录=====================================================
        // 警告
        String isWarn = request.getParameter("deal1");
        item.setIsOk("0");
        if ("1".equals(isWarn)) {
            // 不合格,警告
            item.setIsWarn("1");

        } else {
            // 不合格, 不警告
            item.setIsWarn("0");

        }

        // 现场整改
        String isLocatedChange = request.getParameter("deal2");

        if ("1".equals(isLocatedChange)) {

            item.setIsLocatedChange("1");
        } else {
            item.setIsLocatedChange("0");
        }

        // 限期整改
        String isLimitDateChange = request.getParameter("deal3");
        // 限期整改日期
        String limitDate = request.getParameter("limitDate");
        // 线上还是线下
        String xianxia = request.getParameter("xianxia");
        String xianshang = request.getParameter("xianshang");
        if ("1".equals(isLimitDateChange)) {
            item.setIsLimitDateChange("1");
            try {
                item.setLimitDate(new SimpleDateFormat("yyyy-MM-dd").parse(limitDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 线下
            if ("1".equals(xianxia)) {
                item.setLimitChangeWay("1");
            } else {
                // 线上
                if ("1".equals(xianshang)) {
                    item.setLimitChangeWay("0");
                }
            }
        } else {
            item.setIsLimitDateChange("0");
            item.setLimitChangeWay("1");

        }

        // 罚款
        String managerScore = request.getParameter("managerScore");
        String managerId = request.getParameter("managerId");
        String managerMoney = request.getParameter("managerMoney");
        String inspectorScore = request.getParameter("inspectorScore");
        String inspectorId = request.getParameter("inspectorId");
        String inspectorMoney = request.getParameter("inspectorMoney");
        String packId = request.getParameter("packId");
        String workerGroupScore = request.getParameter("workerGroupScore");
        String workerGroupMoney = request.getParameter("workerGroupMoney");
        String managerFine = request.getParameter("managerFine");
        String workerFine = request.getParameter("workerFine");
        String pqcFine = request.getParameter("pqcFine");
        String responsibilityPersonM = request.getParameter("responsibilityPersonM");
        String responsibilityPersonW = request.getParameter("responsibilityPersonW");
        if(!StringUtils.isEmpty(responsibilityPersonM)){
            item.setResponsibilityPersonM(Integer.valueOf(responsibilityPersonM));
        }
        if(!StringUtils.isEmpty(responsibilityPersonW)){
            item.setResponsibilityPersonW(Integer.valueOf(responsibilityPersonW));
        }
        if (JobSiteController.isNum(managerFine) && !managerFine.trim().equals("") && "1".equals(managerFine)) {
            // 项目经理罚分
            item.setManagerScore(Double.parseDouble(managerScore));
            item.setManagerId(Integer.parseInt(managerId.trim().equals("") ? "0" : managerId));
            item.setActualPunishMoney(Double.parseDouble(managerMoney));
            item.setIsPunishMoney("1");
        } else {
            // 项目经理未罚分
            item.setManagerScore(0d);
            // 项目经理未罚钱
            item.setActualPunishMoney(0d);
            item.setIsPunishMoney("0");

        }

        if (JobSiteController.isNum(pqcFine) && !pqcFine.trim().equals("") && "1".equals(pqcFine)) {
            // 質檢罚分
            item.setInspectorScore(Double.parseDouble(inspectorScore));
            item.setInspectorId(Integer.parseInt(inspectorId));
            // 質檢罚钱
            item.setInspectorMoney(Double.parseDouble(inspectorMoney));
            item.setIsPunishMoney("1");
        } else {
            // 質檢未罚分
            item.setInspectorScore(0d);
            // 質檢未罚钱
            item.setInspectorMoney(0d);
        }

        if (null != packId && !packId.trim().equals("")) {

            Integer workerGroupId = dao.findWorkerInfoByPackId(Integer.parseInt(packId));

            if (null != workerFine && JobSiteController.isNum(workerFine) && !workerFine.trim().equals("") && "1".equals(workerFine)) {
                // 工人组罚分
                item.setWorkerScore(Double.parseDouble(workerGroupScore));
                item.setPackId(Integer.parseInt(packId));
                // 工人组罚钱
                item.setWorkerMoney(Double.parseDouble(workerGroupMoney));
                item.setWorkerGroupId(workerGroupId);
                item.setIsPunishMoney("1");
            } else {
                // 工人组未罚分
                item.setWorkerScore(0d);
                // 工人组未罚钱
                item.setWorkerMoney(0d);

            }

        }

        // 违规项ids
        String[] ids = request.getParameterValues("id");
        // 对应的备注

        String[] remarks = request.getParameterValues("remarks");
        Integer xn = -1;

        List<IllegalModality> list = new ArrayList<IllegalModality>();

        int checkItemId = 0;
        for (int v = 0; v < ids.length; v++) {
            IllegalModality modality = new IllegalModality();
            xn++;

            // 对应的违规形式id,根据id查询检查项id ,所占分数,是否有备注
            InspectItem inspectItem = dao.selectCheckItemInfoByIllegalModalityId(Integer.parseInt(ids[v]));
            // 设置检查项id
            item.setCheckItemId(inspectItem.getCheckItemId());
            // 原本分数
            item.setPreScores(inspectItem.getPreScores());
            // 全扣了
            item.setActualScores(0d);
            // 保存检查项记录表

            // ====================================================违规记录=====================================================
            // 违规形式的检查项id
            checkItemId = dao.selectCheckItemId(item);
            modality.setCheckItemId(checkItemId);
            // 违规形式id
            modality.setIllegalModalityId(Integer.parseInt(ids[v]));
            // 违规形式name
            modality.setIllegalModalityName(inspectItem.getRemarks());
            // 质检单id
            modality.setInspectId(item.getInspectBillId());

            // 如果违规形式有备注
            if (null != inspectItem.getIsRequired() && !"".equals(inspectItem.getIsRequired()) && inspectItem.getIsRequired().equals("1")) {
                // 有备注
                modality.setIsRemarks("1");
                // 备注的名字
                modality.setRemarks(remarks[xn]);
            } else {
                // 如果没有备注
                modality.setIsRemarks("0");
                xn--;
            }
            // 保存 违规形式记录表
            list.add(modality);

        }
        //删除之前的该检查项的违规形式
        dao.batchDeleteQcBillCheckItemBreak(checkItemId);

        if (list.size() > 0) {

            dao.saveIllegalModality(list);
        }

        dao.updateCheckItem(item);

    }

    /**
     * 根据质检单id 查询检查项申请记录 以供页面回显
     *
     * @param inspectId
     * @return
     */
    public List<InspectKind> changeCheckItem(Integer inspectId) {

        return dao.changeCheckItem(inspectId);
    }

    /**
     * 根据检查项,查询违规形式
     *
     * @return
     */
    public List<IllegalModality> findIllegalModalityByCheckItemId(Integer checkItemId) {

        return dao.findIllegalModalityByCheckItemId(checkItemId);
    }

    /**
     * 根据违规项id 查询 检查项id,分数,及是否有备注
     *
     * @param illegalModalityId
     * @return
     */
    public InspectItem selectCheckItemInfoByIllegalModalityId(Integer illegalModalityId) {

        return dao.selectCheckItemInfoByIllegalModalityId(illegalModalityId);
    }

    /**
     * 保存违规形式记录
     *
     * @param modality
     * @Transactional(readOnly=false) public void
     *                                saveIllegalModality(IllegalModality
     *                                modality){
     *
     *                                dao.saveIllegalModality(modality); }
     */
    /**
     * 保存检查项的各项不合格数据
     *
     * @param item
     */
    @Transactional(readOnly = false)
    public void saveCheckItem(InspectItem item) {

        dao.saveCheckItem(item);
    }

    /**
     * 根据检查项id 查询是否有记录 如果有 ,为不合格, 无为合格
     *
     * @param map
     * @return id
     */
    public List<InspectItem> selectScoresFromCheckItemRecord(Map<String, Object> map) {

        return dao.selectScoresFromCheckItemRecord(map);
    }

    @Transactional(readOnly = false)
    public void handleEachCheckItems(List<InspectItem> inspectItems, HttpServletRequest request, String inspectBillId) {

        Inspector inspector = SessionUtils.getInspectorSession(request);

        List<InspectItem> itemList = new ArrayList<InspectItem>();

        String status = request.getParameter("status");
        // 检查项的各项分数总分, 保存在质检单中
        Double totalScores = 0d;
        Double actualScores = 0d;

        // 复检单变量
        Integer x = 0;
        // 复检单返回id
        Integer recheckId = null;

        List<Recheck> rechecks = new ArrayList<>();
        if (inspectItems.size() > 0) {
            Integer checkItemsSize = inspectItems.size();

            for (int v = 0; v < checkItemsSize; v++) {

                InspectItem item = inspectItems.get(v);

                if (null == item.getIsOk() || !"0".equals(item.getIsOk())) {
                    // 2:如果是合格的, 保存到检查项记录表中
                    // 合格
                    item.setIsOk("1");

                    // 该检查项不扣分
                    item.setPreScores(item.getScores());
                    item.setActualScores(item.getScores());
                    item.setId(item.getItemId());
                    // 保存合格的检查项
                    itemList.add(item);

                    // 合格的实际分数
                    actualScores = PqcUtil.doubleAdd(actualScores, item.getScores());
                    totalScores = PqcUtil.doubleAdd(totalScores, item.getScores());

                } else if ("0".equals(item.getIsOk())) {
                    // 3:不合格,总分相加, 实际得分没有
                    totalScores = PqcUtil.doubleAdd(totalScores, item.getScores());

                    // 不合格, 要看是否为限期整改
                    // 如果不是暂存
                    if ("5".equals(status)) {
                        if ("1".equals(item.getIsLimitDateChange())) {
                            // 如果是限期整改 , 插入质检单 ,保存不合格的检查项, 作为复检内容
                            // 需要保存的数据有,biz_qc_bill : code,type isRecheck
                            // relatedBillId
                            // , status
                            // 只保存一条复检单数据
                            if (x != 1) {
                                InspectKind inspectKind = dao.findOrderIdByBillId(item.getInspectBillId());

                                recheckId = saveRecheck(inspectKind);

                                x++;

                                // 保存发送短信内容
                                // 订单（东晨小区-10-4-202-王维-13333333333），质检员（王毅-13212341234），已产生复检单，请及时登录APP查看详情。
                                // 发给项目经理, 短信内质检员
                                QualityControl control = dao.findMessageInfoByInspectId(inspector.getId());
                                if (null != control) {
                                    saveMessageContent(control, inspector.getId(), inspector.getRealName(), inspector.getPhone());

                                    saveMsgContent(control, recheckId, inspector.getRealName(), inspector.getPhone());

                                }

                            }
                        }

                        // 在保存该复检单的不合格检查项
                        // 检查项表: biz_qc_bill_check_item : billId ,
                        // relatedCheckItemId ,checkItemId ,isOk PreScores
                        // ,actualScore
                        // changeWay
                        if ("1".equals(item.getIsLimitDateChange())) {

                            batchSaveRecheckList(item, recheckId, rechecks);

                        }

                        // 产生结算类目明细 罚款

                        // 如果有罚款
                        if ("1".equals(item.getIsPunishMoney())) {

                            BalanceFine bf = new BalanceFine();
                            if (null != item.getActualPunishMoney() && Double.valueOf(item.getActualPunishMoney()) > 0) {

                                if (null != item.getManagerId()) {


                                    bf.setOrderId(item.getOrderId());
                                    //罚款人id
                                    bf.setManagerId(item.getManagerId());
                                    bf.setSettleAmount(-Double.valueOf(item.getActualPunishMoney()));
                                    bf.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_4);
                                    bf.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
                                    bf.setSettleStatusTime(new Date());
                                    bf.setRelatedBussinessId(item.getItemId());
                                    bf.setCreateBy(SessionUtils.getInspectorSession(request).getId());
                                    bf.setCreateDate(new Date());
                                    bf.setDelFlag("0");
                                    bf.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
                                    dao.saveSettleFineRecord(bf);

                                }

                            }

                            if (null != item.getInspectorMoney() && Double.valueOf(item.getInspectorMoney()) > 0) {
                                if (null != item.getPqcId()) {


                                    bf.setOrderId(item.getOrderId());
                                    //罚款人id
                                    bf.setManagerId(item.getPqcId());
                                    bf.setSettleAmount(-Double.valueOf(item.getInspectorMoney()));
                                    bf.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_4);
                                    bf.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
                                    bf.setSettleStatusTime(new Date());
                                    bf.setRelatedBussinessId(item.getItemId());
                                    bf.setCreateBy(SessionUtils.getInspectorSession(request).getId());
                                    bf.setCreateDate(new Date());
                                    bf.setDelFlag("0");
                                    bf.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
                                    dao.saveSettleFineRecord(bf);

                                }


                            }


                        }

                    }

                }

            }

        }

        if (itemList.size() > 0) {
            // 批量更新检查项的状态
            dao.updateCheckItemAll(itemList);
        }
        if (rechecks.size() > 0) {
            // 批量插入复检项
            dao.batchSaveRecheckCheckItem(rechecks);
        }

        // =====================================更新质检单=======================================================

        // 1:根据质检单id查询检查项记录表的得分情况
        InspectKind kind = new InspectKind();

        // 质检单id
        kind.setInspectBillId(Integer.parseInt(inspectBillId));
        // 实际质检人id
        kind.setActualCheckPersonId(inspector.getId());
        // 质检时间
        kind.setCheckDate(new Date());
        // 总分
        kind.setTotalScores(totalScores);
        // 实际得分
        kind.setActualScores(actualScores);
        // 暂存还是提交报告

        kind.setStatus(status);// 0或者5

        // 2: 更新质检单
        dao.updateInspect(kind);

    }

    /**
     * 根据质检单id查询是否有暂存
     *
     * @param inspectId
     * @return
     */
    public List<InspectItem> findZanCun(Integer inspectId) {

        return dao.findZanCun(inspectId);
    }

    /**
     * 更新质检单 状态, 得分情况 实际质检人 等
     *
     * @param kind
     */
    @Transactional(readOnly = false)
    public void updateInspect(InspectKind kind) {

        dao.updateInspect(kind);
    }

    /**
     * 业务逻辑: 更改检查项 根据质检单id 删除之前选择过的检查项
     *
     * @param billId
     */
    @Transactional(readOnly = false)
    public void deleteCheckItemsByCheckId(Integer billId) {

        dao.deleteCheckItemsByCheckId(billId);
    }

    /*
     * parameter: 检查项id 和质检单id 查询需要保存的违规形式的检查项外键
     */
    public int selectCheckItemId(InspectItem item) {

        return dao.selectCheckItemId(item);
    }

    // 保存图片
    @Transactional(readOnly = false)
    public void savePic(String[] photo, HttpServletRequest request, String inspectId) {
        List<ReportCheckDetailsPic> picList = new ArrayList<>();

        Date date = new Date();
        String rootPath = request.getSession().getServletContext().getRealPath("");
        File filePath = new File(rootPath + ConstantUtils.UPLOAD_CHECKITEM + DateUtils.getDate1());
        for (String p : photo) {

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            // String rootPath = RootName.SystemEnvironment(request);

            // 判断该文件是否存在
            if (!filePath.exists() && !filePath.isDirectory()) {
                filePath.mkdirs();
            }
            String filepath = filePath + filePath.separator + uuid + ".jpeg";
            Base64Util.generateImage(p, filepath);

            String picpath = ConstantUtils.UPLOAD_CHECKITEM + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";
            // 保存图片到数据库
            ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
            reportCheckDetailsPic.setBusinessIdInt(Integer.valueOf(inspectId));
            reportCheckDetailsPic.setBusinessType("2");
            reportCheckDetailsPic.setPicUrl(picpath);
            reportCheckDetailsPic.setPicDatetime(date);
            picList.add(reportCheckDetailsPic);

        }

        dao.savePic(picList);
    }

    // 查询图片
    public List<ReportCheckDetailsPic> findPic(int inspectId) {
        return dao.findPic(inspectId);
    }

    // 删除数据库图片
    @Transactional(readOnly = false)
    public void deletePic(int picId) {
        dao.deletePic(picId);
    }

    @Transactional(readOnly = false)
    public void saveCode() {
        dao.saveCode();

    }

    public ReCheckCode getCode() {

        return dao.getCode();
    }

    @Transactional(readOnly = false)
    public void updateCode(ReCheckCode code) {

        dao.updateCode(code);

    }

    /**
     * 保存复检单
     *
     * @param inspectKind
     */
    @Transactional(readOnly = false)
    public int saveRecheck(InspectKind inspectKind) {
        Date date = new Date();
        Recheck recheck = new Recheck();
        recheck.setReCheckCode(qcBillCode());
        recheck.setType("1");
        recheck.setIsReCheck("1");
        recheck.setRelatedBillId(inspectKind.getInspectBillId());
        recheck.setStatus("1");
        recheck.setCreateDate(date);
        recheck.setOrderId(inspectKind.getOrderId());
        recheck.setCheckNodeId(inspectKind.getCheckNodeId());
        dao.saveRecheck(recheck);
        return recheck.getReCheckId();
    }

    /**
     * 质检单编号
     *
     * @return
     */
    @Transactional(readOnly = false)
    public String qcBillCode() {
        // 以ZJ开头

        StringBuilder builder = new StringBuilder();
        Date date = new Date();
        // num
        ReCheckCode code1 = dao.getCode();

        if (null == code1) {
            dao.saveCode();
            code1 = dao.getCode();
        }

        //如果不是同一天
        if (!DateUtils.isSameDay(date, code1.getGenerateTime())) {

            code1.setGenerateTime(date);
            code1.setLastSeiralnum(1);

        } else {
            code1.setLastSeiralnum((code1.getLastSeiralnum() + 1));
            code1.setGenerateTime(date);
        }


        dao.updateCode(code1);
        String code = String.valueOf(code1.getLastSeiralnum());
        builder.append(code1.getBussinessType());
        builder.append(new SimpleDateFormat("yyyyMMdd").format(date));

        // 判断长度
        if (code.length() == 1) {

            builder.append("000").append(code);

        } else if (code.length() == 2) {
            // 拼接采购单编号
            builder.append("00").append(code);
        } else if (code.length() == 3) {
            builder.append("0").append(code);
        } else if (code.length() >= 4) {
            builder.append(code);
        }


        // 返回采购单编号
        return builder.toString();

    }


    /**
     * 保存复检单对应的不合格检查项
     *
     * @param recheck
     */
    @Transactional(readOnly = false)
    public void saveRecheckCheckItem(Recheck recheck) {
        dao.saveRecheckCheckItem(recheck);

    }

    public InspectKind findOrderIdByBillId(Integer billId) {

        return dao.findOrderIdByBillId(billId);
    }

    public QualityControl findMessageInfoByInspectId(Integer inspectId) {

        return dao.findMessageInfoByInspectId(inspectId);
    }

    public QualityControl findMessageInfoByInspectId2(Integer inspectId) {

        return dao.findMessageInfoByInspectId2(inspectId);
    }

    public BalanceFine findRecordByOrderIdOfInspectId(Integer inspectId) {

        return dao.findRecordByOrderIdOfInspectId(inspectId);
    }

    /**
     * 更新结算类目明细表
     *
     * @param fine
     */
    @Transactional(readOnly = false)
    public void updateFineCount(BalanceFine fine) {

        dao.updateFineCount(fine);
    }

    /**
     * 保存一条罚款结算类目明细记录
     *
     * @param fine
     */
    @Transactional(readOnly = false)
    public void saveSettleFineRecord(BalanceFine fine) {

        dao.saveSettleFineRecord(fine);
    }

    /**
     * 批量保存罚款结算类目明细
     *
     * @param fakuan
     */
    @Transactional(readOnly = false)
    public void saveSettleFineRecordAll(List<BalanceFine> fakuan) {
        dao.saveSettleFineRecordAll(fakuan);

    }

    // 17-2-16 更新
    public List<InspectItem> findWorkerManagerInspectorPackageInfoByOrderId(Integer inspectId) {

        return dao.findWorkerManagerInspectorPackageInfoByOrderId(inspectId);
    }

    public int findWorkerInfoByPackId(Integer packId) {

        return dao.findWorkerInfoByPackId(packId);
    }

    @Autowired
    private PurchaseCodeUtils codeUtils;

    public boolean saveMessageContent(QualityControl control, Integer pqcId, String pqcName, String pqcPhone) {

        String content = "订单（" + control.getCommunityName() + "-" + control.getBuildNumber() + "-" + control.getBuildUnit() + "-" + control.getBuildRoom() + "-" + control.getCustomerName() + "-" + control.getCustomerPhone() + "，质检员（" + pqcName + "-" + pqcPhone + "），已产生复检单，请及时登录APP查看详情。	";

        return saveMessage(content, control.getItemManagerId(), control.getPhone(), pqcId);
    }

    public boolean saveMsgContent(QualityControl control, Integer recheckId, String pqcName, String pqcPhone) {

        String content = "订单（" + control.getCommunityName() + "-" + control.getBuildNumber() + "-" + control.getBuildUnit() + "-" + control.getBuildRoom() + "-" + control.getCustomerName() + "-" + control.getCustomerPhone() + "，质检员（" + pqcName + "-" + pqcPhone + "），已产生复检单，请及时登录APP查看详情。	";

        // =====================================消息推送start========================================================

        return saveAppMsg(content, recheckId, control.getItemManagerId(), true);
        // =====================================消息推送end========================================================

    }

    public void batchSaveRecheckList(InspectItem item, Integer recheckId, List<Recheck> rechecks) {

        Recheck recheck = new Recheck();
        // 检查项相关联的复检单id
        recheck.setRelatedBillId(recheckId);
        // 不合格的检查项id
        recheck.setRelatedCheckItemId(item.getId());
        recheck.setCheckItemId(item.getCheckItemId());
        // 肯定不合格
        recheck.setIsOk("0");
        // 得分
        recheck.setPreScores(item.getPreScores());
        // 实际得分
        recheck.setActualScores(0d);
        // 线上线下
        recheck.setChangeWay(item.getLimitChangeWay());

        rechecks.add(recheck);

    }

    /**
     * 质检端复检短信保存(待修改)
     *
     * @param messageContent
     * @param receiveEmployeeId
     * @param receivePhone
     * @param relatedBusinessId
     * @return
     */
    public boolean saveMessage(String messageContent, Integer receiveEmployeeId, String receivePhone, Integer relatedBusinessId) {

        PhoneMessageEntity entity = new PhoneMessageEntity();

        entity.setMessageGenerateTime(new Date());
        entity.setMessageContent(messageContent);
        entity.setReceiveEmployeeId(receiveEmployeeId);
        entity.setReceivePhone(receivePhone);
        entity.setStatus("0");
        entity.setRelatedBusinessType("600201");
        entity.setRelatedBusinessId(relatedBusinessId);
        try {
            messageDao.saveMessageContent(entity);
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 质检端抽约检站内消息推送(待修改)
     *
     * @param msgContent
     * @param businessId
     * @param employeeId
     * @return
     */
    public boolean saveAppMsg(String msgContent, Integer businessId, Integer employeeId, boolean checkDiff) {

        BizMsg bizMsg = new BizMsg();

        bizMsg.setMsgTitle("质检员产生复检单");
        bizMsg.setMsgTime(new Date());
        bizMsg.setMsgContent(msgContent);
        bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
        // 约检为true 抽检为false
        bizMsg.setBusiType(checkDiff ? MessagePushType.MESSAGE_PUSH_TYPE_103001001 : MessagePushType.MESSAGE_PUSH_TYPE_103001002);
        bizMsg.setBusiIdInt(businessId);
        bizMsg.setEmployeeId(employeeId);
        try {

            bizProjectChangeBillService.saveBizMsg(bizMsg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Transactional(readOnly = false)
    public void savePic(List<ReportCheckDetailsPic> saveFile) {
        dao.savePic(saveFile);

    }

}
