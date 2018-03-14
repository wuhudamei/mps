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


    public List<InspectKind> selectAllCheckItem(Integer storeId) {

        return dao.selectAllCheckItem(storeId);
    }


    @Transactional(readOnly = false)
    public void saveItems(InspectItem kind) {
        dao.saveItems(kind);
    }


    @Transactional(readOnly = false)
    public void updateCheckItem(HttpServletRequest request) {

        InspectItem item = new InspectItem();

        item.setInspectBillId(Integer.parseInt(request.getParameter("inspectId")));



        String isWarn = request.getParameter("deal1");
        item.setIsOk("0");
        if ("1".equals(isWarn)) {

            item.setIsWarn("1");

        } else {

            item.setIsWarn("0");

        }


        String isLocatedChange = request.getParameter("deal2");

        if ("1".equals(isLocatedChange)) {

            item.setIsLocatedChange("1");
        } else {
            item.setIsLocatedChange("0");
        }


        String isLimitDateChange = request.getParameter("deal3");

        String limitDate = request.getParameter("limitDate");

        String xianxia = request.getParameter("xianxia");
        String xianshang = request.getParameter("xianshang");
        if ("1".equals(isLimitDateChange)) {
            item.setIsLimitDateChange("1");
            try {
                item.setLimitDate(new SimpleDateFormat("yyyy-MM-dd").parse(limitDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if ("1".equals(xianxia)) {
                item.setLimitChangeWay("1");
            } else {

                if ("1".equals(xianshang)) {
                    item.setLimitChangeWay("0");
                }
            }
        } else {
            item.setIsLimitDateChange("0");
            item.setLimitChangeWay("1");

        }


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

            item.setManagerScore(Double.parseDouble(managerScore));
            item.setManagerId(Integer.parseInt(managerId.trim().equals("") ? "0" : managerId));
            item.setActualPunishMoney(Double.parseDouble(managerMoney));
            item.setIsPunishMoney("1");
        } else {

            item.setManagerScore(0d);

            item.setActualPunishMoney(0d);
            item.setIsPunishMoney("0");

        }

        if (JobSiteController.isNum(pqcFine) && !pqcFine.trim().equals("") && "1".equals(pqcFine)) {

            item.setInspectorScore(Double.parseDouble(inspectorScore));
            item.setInspectorId(Integer.parseInt(inspectorId));

            item.setInspectorMoney(Double.parseDouble(inspectorMoney));
            item.setIsPunishMoney("1");
        } else {

            item.setInspectorScore(0d);

            item.setInspectorMoney(0d);
        }

        if (null != packId && !packId.trim().equals("")) {

            Integer workerGroupId = dao.findWorkerInfoByPackId(Integer.parseInt(packId));

            if (null != workerFine && JobSiteController.isNum(workerFine) && !workerFine.trim().equals("") && "1".equals(workerFine)) {

                item.setWorkerScore(Double.parseDouble(workerGroupScore));
                item.setPackId(Integer.parseInt(packId));

                item.setWorkerMoney(Double.parseDouble(workerGroupMoney));
                item.setWorkerGroupId(workerGroupId);
                item.setIsPunishMoney("1");
            } else {

                item.setWorkerScore(0d);

                item.setWorkerMoney(0d);

            }

        }


        String[] ids = request.getParameterValues("id");


        String[] remarks = request.getParameterValues("remarks");
        Integer xn = -1;

        List<IllegalModality> list = new ArrayList<IllegalModality>();

        int checkItemId = 0;
        for (int v = 0; v < ids.length; v++) {
            IllegalModality modality = new IllegalModality();
            xn++;


            InspectItem inspectItem = dao.selectCheckItemInfoByIllegalModalityId(Integer.parseInt(ids[v]));

            item.setCheckItemId(inspectItem.getCheckItemId());

            item.setPreScores(inspectItem.getPreScores());

            item.setActualScores(0d);




            checkItemId = dao.selectCheckItemId(item);
            modality.setCheckItemId(checkItemId);

            modality.setIllegalModalityId(Integer.parseInt(ids[v]));

            modality.setIllegalModalityName(inspectItem.getRemarks());

            modality.setInspectId(item.getInspectBillId());


            if (null != inspectItem.getIsRequired() && !"".equals(inspectItem.getIsRequired()) && inspectItem.getIsRequired().equals("1")) {

                modality.setIsRemarks("1");

                modality.setRemarks(remarks[xn]);
            } else {

                modality.setIsRemarks("0");
                xn--;
            }

            list.add(modality);

        }

        dao.batchDeleteQcBillCheckItemBreak(checkItemId);

        if (list.size() > 0) {

            dao.saveIllegalModality(list);
        }

        dao.updateCheckItem(item);

    }


    public List<InspectKind> changeCheckItem(Integer inspectId) {

        return dao.changeCheckItem(inspectId);
    }


    public List<IllegalModality> findIllegalModalityByCheckItemId(Integer checkItemId) {

        return dao.findIllegalModalityByCheckItemId(checkItemId);
    }


    public InspectItem selectCheckItemInfoByIllegalModalityId(Integer illegalModalityId) {

        return dao.selectCheckItemInfoByIllegalModalityId(illegalModalityId);
    }



    @Transactional(readOnly = false)
    public void saveCheckItem(InspectItem item) {

        dao.saveCheckItem(item);
    }


    public List<InspectItem> selectScoresFromCheckItemRecord(Map<String, Object> map) {

        return dao.selectScoresFromCheckItemRecord(map);
    }

    @Transactional(readOnly = false)
    public void handleEachCheckItems(List<InspectItem> inspectItems, HttpServletRequest request, String inspectBillId) {

        Inspector inspector = SessionUtils.getInspectorSession(request);

        List<InspectItem> itemList = new ArrayList<InspectItem>();

        String status = request.getParameter("status");

        Double totalScores = 0d;
        Double actualScores = 0d;


        Integer x = 0;

        Integer recheckId = null;

        List<Recheck> rechecks = new ArrayList<>();
        if (inspectItems.size() > 0) {
            Integer checkItemsSize = inspectItems.size();

            for (int v = 0; v < checkItemsSize; v++) {

                InspectItem item = inspectItems.get(v);

                if (null == item.getIsOk() || !"0".equals(item.getIsOk())) {


                    item.setIsOk("1");


                    item.setPreScores(item.getScores());
                    item.setActualScores(item.getScores());
                    item.setId(item.getItemId());

                    itemList.add(item);


                    actualScores = PqcUtil.doubleAdd(actualScores, item.getScores());
                    totalScores = PqcUtil.doubleAdd(totalScores, item.getScores());

                } else if ("0".equals(item.getIsOk())) {

                    totalScores = PqcUtil.doubleAdd(totalScores, item.getScores());



                    if ("5".equals(status)) {
                        if ("1".equals(item.getIsLimitDateChange())) {





                            if (x != 1) {
                                InspectKind inspectKind = dao.findOrderIdByBillId(item.getInspectBillId());

                                recheckId = saveRecheck(inspectKind);

                                x++;




                                QualityControl control = dao.findMessageInfoByInspectId(inspector.getId());
                                if (null != control) {
                                    saveMessageContent(control, inspector.getId(), inspector.getRealName(), inspector.getPhone());

                                    saveMsgContent(control, recheckId, inspector.getRealName(), inspector.getPhone());

                                }

                            }
                        }






                        if ("1".equals(item.getIsLimitDateChange())) {

                            batchSaveRecheckList(item, recheckId, rechecks);

                        }




                        if ("1".equals(item.getIsPunishMoney())) {

                            BalanceFine bf = new BalanceFine();
                            if (null != item.getActualPunishMoney() && Double.valueOf(item.getActualPunishMoney()) > 0) {

                                if (null != item.getManagerId()) {


                                    bf.setOrderId(item.getOrderId());

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

            dao.updateCheckItemAll(itemList);
        }
        if (rechecks.size() > 0) {

            dao.batchSaveRecheckCheckItem(rechecks);
        }




        InspectKind kind = new InspectKind();


        kind.setInspectBillId(Integer.parseInt(inspectBillId));

        kind.setActualCheckPersonId(inspector.getId());

        kind.setCheckDate(new Date());

        kind.setTotalScores(totalScores);

        kind.setActualScores(actualScores);


        kind.setStatus(status);


        dao.updateInspect(kind);

    }


    public List<InspectItem> findZanCun(Integer inspectId) {

        return dao.findZanCun(inspectId);
    }


    @Transactional(readOnly = false)
    public void updateInspect(InspectKind kind) {

        dao.updateInspect(kind);
    }


    @Transactional(readOnly = false)
    public void deleteCheckItemsByCheckId(Integer billId) {

        dao.deleteCheckItemsByCheckId(billId);
    }


    public int selectCheckItemId(InspectItem item) {

        return dao.selectCheckItemId(item);
    }


    @Transactional(readOnly = false)
    public void savePic(String[] photo, HttpServletRequest request, String inspectId) {
        List<ReportCheckDetailsPic> picList = new ArrayList<>();

        Date date = new Date();
        String rootPath = request.getSession().getServletContext().getRealPath("");
        File filePath = new File(rootPath + ConstantUtils.UPLOAD_CHECKITEM + DateUtils.getDate1());
        for (String p : photo) {

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");




            if (!filePath.exists() && !filePath.isDirectory()) {
                filePath.mkdirs();
            }
            String filepath = filePath + filePath.separator + uuid + ".jpeg";
            Base64Util.generateImage(p, filepath);

            String picpath = ConstantUtils.UPLOAD_CHECKITEM + DateUtils.getDate1() + filePath.separator + uuid + ".jpeg";

            ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
            reportCheckDetailsPic.setBusinessIdInt(Integer.valueOf(inspectId));
            reportCheckDetailsPic.setBusinessType("2");
            reportCheckDetailsPic.setPicUrl(picpath);
            reportCheckDetailsPic.setPicDatetime(date);
            picList.add(reportCheckDetailsPic);

        }

        dao.savePic(picList);
    }


    public List<ReportCheckDetailsPic> findPic(int inspectId) {
        return dao.findPic(inspectId);
    }


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


    @Transactional(readOnly = false)
    public String qcBillCode() {


        StringBuilder builder = new StringBuilder();
        Date date = new Date();

        ReCheckCode code1 = dao.getCode();

        if (null == code1) {
            dao.saveCode();
            code1 = dao.getCode();
        }


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


        if (code.length() == 1) {

            builder.append("000").append(code);

        } else if (code.length() == 2) {

            builder.append("00").append(code);
        } else if (code.length() == 3) {
            builder.append("0").append(code);
        } else if (code.length() >= 4) {
            builder.append(code);
        }



        return builder.toString();

    }



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


    @Transactional(readOnly = false)
    public void updateFineCount(BalanceFine fine) {

        dao.updateFineCount(fine);
    }


    @Transactional(readOnly = false)
    public void saveSettleFineRecord(BalanceFine fine) {

        dao.saveSettleFineRecord(fine);
    }


    @Transactional(readOnly = false)
    public void saveSettleFineRecordAll(List<BalanceFine> fakuan) {
        dao.saveSettleFineRecordAll(fakuan);

    }


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



        return saveAppMsg(content, recheckId, control.getItemManagerId(), true);


    }

    public void batchSaveRecheckList(InspectItem item, Integer recheckId, List<Recheck> rechecks) {

        Recheck recheck = new Recheck();

        recheck.setRelatedBillId(recheckId);

        recheck.setRelatedCheckItemId(item.getId());
        recheck.setCheckItemId(item.getCheckItemId());

        recheck.setIsOk("0");

        recheck.setPreScores(item.getPreScores());

        recheck.setActualScores(0d);

        recheck.setChangeWay(item.getLimitChangeWay());

        rechecks.add(recheck);

    }


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


    public boolean saveAppMsg(String msgContent, Integer businessId, Integer employeeId, boolean checkDiff) {

        BizMsg bizMsg = new BizMsg();

        bizMsg.setMsgTitle("质检员产生复检单");
        bizMsg.setMsgTime(new Date());
        bizMsg.setMsgContent(msgContent);
        bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);

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
