package cn.damei.web.mobile.Inspector;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.PicRootName;
import cn.damei.entity.mobile.Inspector.IllegalModality;
import cn.damei.entity.mobile.Inspector.InspectItem;
import cn.damei.entity.mobile.Inspector.InspectKind;
import cn.damei.service.mobile.Inspector.CheckItemService;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.QualityControl;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(value = "${adminPath}/app/pqc/checkItem")
public class CheckItemController {

    @Autowired
    private CheckItemService service;


    private Logger logger = LoggerFactory.getLogger(CheckItemController.class);


    @RequestMapping(value = "checkTimeIsAllowed")
    public @ResponseBody
    String checkTimeIsAllowed(String inspectId) {






        InspectKind kind = service.useInspectIdToFindInspectBillByOrderIdOrderByDateLimitOneInOrderToCheckTimeIsAllowedWithFiveMinutes(Integer.parseInt(inspectId));
        if (null == kind) {

            return "-1";
        }


        if (null != kind && kind.getCheckDate().getTime() + 300 * 1000 > new Date().getTime()) {


            long time = (new Date().getTime() - kind.getCheckDate().getTime());
            return String.valueOf((5 - time / 60000));

        } else {


            return "0";
        }
    }



    @RequestMapping(value = "itemsList")
    public String itemsList(Model model, HttpServletRequest request, String inspectId, String customerInfo) throws IOException {

        model.addAttribute("customerInfo", customerInfo);







        List<InspectItem> zanCun = service.findZanCun(Integer.parseInt(inspectId));


        List<ReportCheckDetailsPic> picList = service.findPic(Integer.parseInt(inspectId));
        int picLength = 0;
        if (picList.size() > 0) {
            picLength = picList.size();
        } else {
            picLength = 0;
        }


        if (null != zanCun && zanCun.size() > 0) {
            model.addAttribute("list", zanCun);
            model.addAttribute("inspectId", inspectId);
            model.addAttribute("picList", picList);
            model.addAttribute("picLength", picLength);
            String baseUrl = PicRootName.picPrefixName();
            model.addAttribute("baseUrl", baseUrl);




            List<InspectItem> workerManagerInspectorPackageInfoByOrderId = service.findWorkerManagerInspectorPackageInfoByOrderId(Integer.parseInt(inspectId));

            if (workerManagerInspectorPackageInfoByOrderId != null && workerManagerInspectorPackageInfoByOrderId.size() > 0) {
                model.addAttribute("infoList", workerManagerInspectorPackageInfoByOrderId);
                model.addAttribute("managerId", workerManagerInspectorPackageInfoByOrderId.get(0).getManagerId());
                model.addAttribute("inspectorId", workerManagerInspectorPackageInfoByOrderId.get(0).getInspectorId());

            } else {

                QualityControl qualityControl = service.findMessageInfoByInspectId(Integer.parseInt(inspectId));
                QualityControl findMessageInfoByInspectId2 = service.findMessageInfoByInspectId2(Integer.parseInt(inspectId));

                if (null != qualityControl) {
                    model.addAttribute("managerId", qualityControl.getItemManagerId());

                }
                if (null != findMessageInfoByInspectId2) {

                    model.addAttribute("inspectorId", findMessageInfoByInspectId2.getOrderInspectorId());

                }


            }

            return "mobile/modules/pqc/check/CheckItem/quality_check";
        }


        List<InspectKind> list = service.selectAllCheckItem(Integer.parseInt(inspectId));

        model.addAttribute("list", list);
        model.addAttribute("inspectId", inspectId);
        return "mobile/modules/pqc/check/CheckItem/check_items";
    }


    @RequestMapping(value = "saveItems")
    public String saveItems(String inspectId, Model model, HttpServletRequest request, String customerInfo) throws IOException {
        model.addAttribute("customerInfo", customerInfo);




        service.deleteCheckItemsByCheckId(Integer.parseInt(inspectId));


        List<ReportCheckDetailsPic> picList = service.findPic(Integer.parseInt(inspectId));
        int picLength = 0;
        if (picList.size() > 0) {
            picLength = picList.size();
        } else {
            picLength = 0;
        }

        String[] checkItemId = request.getParameterValues("checkItemId");
        String[] checkItemName = request.getParameterValues("checkItemName");


        List<InspectItem> list = new ArrayList<InspectItem>();

        if (null != checkItemId && checkItemId.length > 0) {

            for (int v = 0; v < checkItemId.length; v++) {
                if (!checkItemId[v].equals("build")) {
                    InspectItem item = new InspectItem();

                    item.setInspectBillId(Integer.parseInt(inspectId));


                    item.setCheckItemName(checkItemName[v]);

                    item.setCheckItemId(Integer.parseInt(checkItemId[v]));
                    item.setCreateDate(new Date());
                    list.add(item);

                    service.saveItems(item);
                }

            }

        }

        model.addAttribute("list", list);
        model.addAttribute("inspectId", inspectId);
        model.addAttribute("picList", picList);
        model.addAttribute("picLength", picLength);
        String baseUrl = PicRootName.picPrefixName();
        model.addAttribute("baseUrl", baseUrl);




        List<InspectItem> workerManagerInspectorPackageInfoByOrderId = service.findWorkerManagerInspectorPackageInfoByOrderId(Integer.parseInt(inspectId));

        if (workerManagerInspectorPackageInfoByOrderId != null && workerManagerInspectorPackageInfoByOrderId.size() > 0) {
            model.addAttribute("infoList", workerManagerInspectorPackageInfoByOrderId);
            model.addAttribute("managerId", workerManagerInspectorPackageInfoByOrderId.get(0).getManagerId());
            model.addAttribute("projectMode", workerManagerInspectorPackageInfoByOrderId.get(0).getProjectMode());
            model.addAttribute("managerName", workerManagerInspectorPackageInfoByOrderId.get(0).getManagerName());
            model.addAttribute("inspectorId", workerManagerInspectorPackageInfoByOrderId.get(0).getInspectorId());

        } else {

            QualityControl qualityControl = service.findMessageInfoByInspectId(Integer.parseInt(inspectId));
            QualityControl findMessageInfoByInspectId2 = service.findMessageInfoByInspectId2(Integer.parseInt(inspectId));

            if (null != qualityControl) {
                model.addAttribute("managerId", qualityControl.getItemManagerId());
                model.addAttribute("managerName", qualityControl.getItemManager());
                model.addAttribute("projectMode", qualityControl.getProjectMode());

            }
            if (null != findMessageInfoByInspectId2) {

                model.addAttribute("inspectorId", findMessageInfoByInspectId2.getOrderInspectorId());

            }


        }


        return "mobile/modules/pqc/check/CheckItem/quality_check";
    }


    @RequestMapping(value = "changeCheckItem")
    public String changeCheckItem(String inspectId, Model model, HttpServletRequest request, String customerInfo) {

        model.addAttribute("customerInfo", customerInfo);

        List<InspectKind> hasChoosedCheckItems = service.changeCheckItem(Integer.parseInt(inspectId));

        List<InspectKind> allCheckItems = service.selectAllCheckItem(Integer.parseInt(inspectId));

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
        model.addAttribute("inspectId", inspectId);
        return "mobile/modules/pqc/check/CheckItem/check_items";
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "findIllegalModality")
    public @ResponseBody
    List<IllegalModality> findIllegalModality(Model model, String checkItemId) {

        List<IllegalModality> illegalModalityList = service
                .findIllegalModalityByCheckItemId(Integer.parseInt(checkItemId));

        if (illegalModalityList.size() > 0) {
            for (IllegalModality illegalModality : illegalModalityList) {

                illegalModality.setManagerFineMoney(null == illegalModality.getManagerFineMoney() ? 0d : illegalModality.getManagerFineMoney());
                illegalModality.setManagerFineScore(null == illegalModality.getManagerFineScore() ? 0d : illegalModality.getManagerFineScore());

                illegalModality.setWorkerFineMoney(null == illegalModality.getWorkerFineMoney() ? 0d : illegalModality.getWorkerFineMoney());
                illegalModality.setWorkerFineScore(null == illegalModality.getWorkerFineScore() ? 0d : illegalModality.getWorkerFineScore());

                illegalModality.setPqcFineMoney(null == illegalModality.getPqcFineMoney() ? 0d : illegalModality.getPqcFineMoney());
                illegalModality.setPqcFineScore(null == illegalModality.getPqcFineScore() ? 0d : illegalModality.getPqcFineScore());
                break;

            }
        }


        return JSONArray.fromObject(illegalModalityList);
    }









    @RequestMapping(value = "saveIllegalModalityItems")
    public @ResponseBody
    String saveCheckList(HttpServletRequest request) throws ParseException {

        service.updateCheckItem(request);

        return "OK";
    }










    @RequestMapping(value = "saveCheckItemReport", method = RequestMethod.POST)
    public @ResponseBody
    String saveCheckItemReport(String[] photo, HttpServletRequest request)
            throws ParseException {


        String inspectBillId = request.getParameter("inspectId");


        if (null != photo && photo.length > 0) {


            service.savePic(photo, request, inspectBillId);

        }



        String[] checkItemIds = request.getParameterValues("checkItemId");





        Map<String, Object> map = new HashMap<>();
        if (null!=checkItemIds&&checkItemIds.length > 0) {
            map.put("inspectBillId", inspectBillId);
            map.put("checkItemIds", checkItemIds);
            List<InspectItem> inspectItems = service.selectScoresFromCheckItemRecord(map);
            try {
                service.handleEachCheckItems(inspectItems, request, inspectBillId);
                return "1";
            } catch (Exception e) {


                e.printStackTrace();
                return "2";
            }


        }else{

            return "3";
        }
    }











    @RequestMapping(value = "deletePic")
    public @ResponseBody
    String deletePic(String picId, Model model, HttpServletRequest request) {

        service.deletePic(Integer.valueOf(picId));
        return "0";
    }


}
