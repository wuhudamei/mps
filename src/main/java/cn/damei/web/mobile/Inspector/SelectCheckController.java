package cn.damei.web.mobile.Inspector;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.IllegalModality;
import cn.damei.entity.mobile.Inspector.InspectItem;
import cn.damei.entity.mobile.Inspector.InspectKind;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Inspector.BizQcBill;
import cn.damei.entity.mobile.Inspector.Order;
import cn.damei.service.mobile.Inspector.SelectCheckService;
import cn.damei.entity.mobile.Manager.QualityControl;
import cn.damei.web.mobile.home.JobSiteController;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "${adminPath}/app/pqc/selectCheckList")
public class SelectCheckController {

    @Autowired
    private SelectCheckService selectCheckService;


    private Logger logger = LoggerFactory.getLogger(SelectCheckController.class);

    @RequestMapping(value = "list")
    private String list(String text, String timeForbidden, Model model, HttpServletRequest request) {


        Inspector inspector = SessionUtils.getInspectorSession(request);

        List<Order> list = null;
        if (null != text && !"".equals(text)) {
            inspector.setSelectCheckText(text);
            request.getSession().setAttribute("inspector", inspector);
        }



        Order or = new Order();
        or.setOrderInspectorId(Integer.valueOf(inspector.getId()));
        or.setText(inspector.getSelectCheckText());

        if (null != inspector.getIsLeader() && 1 == inspector.getIsLeader()) {

            if (null != inspector.getSelectCheckText() && !"".equals(inspector.getSelectCheckText())) {
                list = selectCheckService.findOrderByLeaderInspectorId(or);
            }
        } else {

            list = selectCheckService.findOrderByInspectorId(or);
        }

        if (null != list && list.size() > 0) {
            for (Order order : list) {


                if (null == order.getQcBillId()) {

                    order.setQcBillId(0);
                    order.setQcBillStatus("-2");

                }
            }
        }
        model.addAttribute("timeForbidden", timeForbidden);
        model.addAttribute("list", list);
        model.addAttribute("text", inspector.getSelectCheckText());

        if (null != inspector.getIsLeader() && 1 == inspector.getIsLeader()) {

            return "mobile/modules/pqc/selectCheck/leaderSelectCheck/leader_select_check";
        } else {


            return "mobile/modules/pqc/selectCheck/select_check";
        }
    }


    @RequestMapping(value = "query_list_ajax")
    private @ResponseBody
    List<Order> queryListAjax(Model model, HttpServletRequest request, String text) {

        Inspector inspector = SessionUtils.getInspectorSession(request);
        if (null != text && !"".equals(text)) {
            inspector.setSelectCheckText(text);
            request.getSession().setAttribute("inspector", inspector);
        }

        Order or = new Order();
        or.setOrderInspectorId(Integer.valueOf(inspector.getId()));
        or.setText(text);
        List<Order> list = selectCheckService.findOrderByInspectorId(or);
        if (list.size() > 0) {
            for (Order order : list) {


                if (null == order.getQcBillId()) {

                    order.setQcBillId(0);
                    order.setQcBillStatus("-2");

                }
            }
        }

        return list;

    }



    @RequestMapping(value = "selectItemsList")
    public String selectItemsList(Model model, HttpServletRequest request, String qcBillId, String orderId, String customerInfo) throws IOException {
        model.addAttribute("customerInfo", customerInfo);



        BizQcBill bizQcBill = selectCheckService.findTimeSpan(Integer.valueOf(orderId));
        if (null != bizQcBill) {


            if (bizQcBill.getCheckDatetime().getTime() + 300 * 1000 > new Date().getTime()) {

                return "redirect:" + Global.getAdminPath() + "/app/pqc/selectCheckList/list?timeForbidden=1";
            }
        }








        List<InspectItem> zanCun = selectCheckService.findZanCun(Integer.parseInt(qcBillId));


        List<ReportCheckDetailsPic> picList = selectCheckService.findPic(Integer.parseInt(qcBillId));
        int picLength = 0;
        if (picList.size() > 0) {
            picLength = picList.size();
        }


        Integer signNum = selectCheckService.findSign(Integer.valueOf(qcBillId));



        if (null != zanCun && zanCun.size() > 0) {
            model.addAttribute("list", zanCun);
            model.addAttribute("inspectId", qcBillId);
            model.addAttribute("orderId", orderId);

            model.addAttribute("picList", picList);
            model.addAttribute("picLength", picLength);
            model.addAttribute("baseUrl", PicRootName.picPrefixName());
            model.addAttribute("signNum", signNum);



            List<InspectItem> workerManagerInspectorPackageInfoByOrderId = selectCheckService.findWorkerManagerInspectorPackageInfoByOrderId(Integer.parseInt(qcBillId));


            if (workerManagerInspectorPackageInfoByOrderId != null && workerManagerInspectorPackageInfoByOrderId.size() > 0) {
                model.addAttribute("infoList", workerManagerInspectorPackageInfoByOrderId);
                model.addAttribute("managerId", workerManagerInspectorPackageInfoByOrderId.get(0).getManagerId());
                model.addAttribute("inspectorId", workerManagerInspectorPackageInfoByOrderId.get(0).getInspectorId());

            } else {

                QualityControl qualityControl = selectCheckService.findMessageInfoByInspectId(Integer.parseInt(qcBillId));
                QualityControl findMessageInfoByInspectId2 = selectCheckService.findMessageInfoByInspectId2(Integer.parseInt(qcBillId));

                if (null != qualityControl) {
                    model.addAttribute("managerId", qualityControl.getItemManagerId());

                }
                if (null != findMessageInfoByInspectId2) {

                    model.addAttribute("inspectorId", findMessageInfoByInspectId2.getOrderInspectorId());

                }


            }

            return "mobile/modules/pqc/selectCheck/selectCheckItem/quality_check";
        }

        List<InspectKind> list = selectCheckService.selectAllCheckItem(Integer.valueOf(orderId));

        for (InspectKind kind : list) {
            List<InspectItem> itemList = kind.getCheckItemList();
            int i = 0;
            for (InspectItem item : itemList) {
                if (!item.getIsRequired().equals("1")) {
                    i++;
                }
            }
            if (i == 0) {
                kind.setIsChoosed("1");
            }
        }

        model.addAttribute("list", list);
        model.addAttribute("qcBillId", qcBillId);
        model.addAttribute("orderId", orderId);
        return "mobile/modules/pqc/selectCheck/selectCheckItem/check_items";
    }



    @RequestMapping(value = "saveItems")
    public String saveItems(String qcBillId, String orderId, Model model, HttpServletRequest request, String customerInfo) throws IOException {



        Integer count = selectCheckService.findIsExistSelectQcBillById(qcBillId);

        if (null != count && count > 0) {
            return "redirect:" + Global.getAdminPath() + "/app/pqc/selectCheckList/list?timeForbidden=2";
        }

        selectCheckService.saveItems(orderId, qcBillId, model, request);

        model.addAttribute("customerInfo", customerInfo);
        return "mobile/modules/pqc/selectCheck/selectCheckItem/quality_check";
    }


    @RequestMapping(value = "changeCheckItem")
    public String changeCheckItem(String inspectId, String orderId, Model model, String customerInfo) {


        if (!"0".equals(inspectId)) {


            Integer count = selectCheckService.findIsExistSelectQcBillById(inspectId);

            if (null != count && count > 0) {


                return "redirect:" + Global.getAdminPath() + "/app/pqc/selectCheckList/list?timeForbidden=2";


            }


        }




        model.addAttribute("customerInfo", customerInfo);

        List<InspectKind> hasChoosedCheckItems = selectCheckService.changeCheckItem(Integer.parseInt(inspectId));


        List<InspectKind> allCheckItems = selectCheckService.selectAllCheckItem(Integer.valueOf(orderId));

        for (InspectKind allitem : allCheckItems) {
            for (InspectKind chooseItem : hasChoosedCheckItems) {

                if (allitem.getCheckKindId().equals(chooseItem.getCheckKindId())) {



                    List<InspectItem> itemList = allitem.getCheckItemList();

                    int i = itemList.size();


                    List<InspectItem> list = chooseItem.getCheckItemList();

                    for (InspectItem inspectItem : itemList) {
                        for (InspectItem item : list) {

                            if (inspectItem.getCheckItemId().equals(item.getCheckItemId())) {

                                inspectItem.setIsChoosed("1");
                                i--;
                            }
                        }
                    }

                    if (i == 0) {

                        allitem.setIsChoosed("1");
                    }
                }
            }
        }

        model.addAttribute("list", allCheckItems);
        model.addAttribute("reCheck", "1");
        model.addAttribute("qcBillId", inspectId);
        model.addAttribute("orderId", orderId);
        return "mobile/modules/pqc/selectCheck/selectCheckItem/check_items";
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "findIllegalModality")
    public @ResponseBody
    List<IllegalModality> findIllegalModality(Model model, String checkItemId) {


        List<IllegalModality> illegalModalityList = selectCheckService.findIllegalModalityByCheckItemId(Integer.parseInt(checkItemId));

        return JSONArray.fromObject(illegalModalityList);
    }


    @RequestMapping(value = "saveIllegalModalityItems")
    public @ResponseBody
    String saveCheckList(HttpServletRequest request) throws ParseException {
        InspectItem item = new InspectItem();


        Inspector inspector = SessionUtils.getInspectorSession(request);

        Date date = new Date();
        List<IllegalModality> list = new ArrayList<IllegalModality>();

        String inspectBillId = request.getParameter("inspectId");



        Integer count = selectCheckService.findIsExistSelectQcBillById(inspectBillId);

        if (null != count && count > 0) {
            return "9";
        }
        item.setInspectBillId(Integer.parseInt(inspectBillId));




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
            item.setLimitDate(new SimpleDateFormat("yyyy-MM-dd").parse(limitDate));

            if ("1".equals(xianxia)) {
                item.setLimitChangeWay("1");
            } else {

                if ("1".equals(xianshang)) {
                    item.setLimitChangeWay("0");
                }
            }
        } else {
            item.setIsLimitDateChange("0");
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

            Integer workerGroupId = selectCheckService.findWorkerInfoByPackId(Integer.parseInt(packId));


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


        item.setUpdateDate(date);


        String[] ids = request.getParameterValues("id");

        String[] remarks = request.getParameterValues("remarks");
        Integer xn = -1;
        int checkItemId = 0;
        for (int v = 0; v < ids.length; v++) {
            xn++;

            InspectItem inspectItem = selectCheckService.selectCheckItemInfoByIllegalModalityId(Integer.parseInt(ids[v]));

            item.setCheckItemId(inspectItem.getCheckItemId());

            item.setPreScores(inspectItem.getPreScores());

            item.setActualScores(0d);



            IllegalModality modality = new IllegalModality();

            checkItemId = selectCheckService.selectCheckItemId(item);
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

        selectCheckService.batchDeleteQcBillCheckItemBreak(checkItemId);


        if(null != list && list.size() >0){
            selectCheckService.saveIllegalModalityAll(list);
        }

        selectCheckService.updateCheckItem(item);
        return "OK";
    }



    @RequestMapping(value = "saveCheckItemReport", method = RequestMethod.POST)
    public @ResponseBody
    String saveCheckItemReport(String[] photo, HttpServletRequest request) throws ParseException {



        String inspectBillId = request.getParameter("inspectId");




        Integer count = selectCheckService.findIsExistSelectQcBillById(inspectBillId);

        if (null != count && count > 0) {


            return "9";


        }






        String[] checkItemIds = request.getParameterValues("checkItemId");













        Map<String, Object> map = new HashMap<>();
        if (null != checkItemIds && checkItemIds.length > 0) {
            map.put("inspectBillId", inspectBillId);
            map.put("checkItemIds", checkItemIds);
            List<InspectItem> inspectItems = selectCheckService.selectScoresFromCheckItemRecord(map);
            try {
                selectCheckService.handleEachCheckItems(inspectItems, request, inspectBillId, photo);
                return "1";
            } catch (Exception e) {
                e.printStackTrace();
                return "2";
            }


        } else {

            return "3";
        }



































































































































































    }

    @RequestMapping(value = "deletePic")
    public @ResponseBody
    String deletePic(String picId, Model model, HttpServletRequest request) {

        selectCheckService.deletePic(Integer.valueOf(picId));
        return "0";
    }


    @RequestMapping("changeItemToQualified")
    @ResponseBody
    public String changeItemToQualified(String inspectId, String checkItemId) {


        try{
            if (null != inspectId && null != checkItemId) {
                InspectItem item = new InspectItem();
                item.setCheckItemId(Integer.valueOf(checkItemId));
                item.setInspectBillId(Integer.valueOf(inspectId));
                selectCheckService.updateCheckItem(item);

                selectCheckService.batchDeleteQcBillCheckItemBreak(Integer.valueOf(checkItemId));
return "1";
            }

        }catch (Exception e){
            return "2";

        }

        return "2";



    }

}
