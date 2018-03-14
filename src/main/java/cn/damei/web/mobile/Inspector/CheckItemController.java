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

/**
 * 约检
 *
 * @author 梅浩
 * @2016年11月8日
 * @mdn大美装饰管理平台
 * @author_phone : 18610507472
 * @ClassInfo:约检
 */
@Controller
@RequestMapping(value = "${adminPath}/app/pqc/checkItem")
public class CheckItemController {

    @Autowired
    private CheckItemService service;


    private Logger logger = LoggerFactory.getLogger(CheckItemController.class);


    @RequestMapping(value = "checkTimeIsAllowed")
    public @ResponseBody
    String checkTimeIsAllowed(String inspectId) {
        //2017-01-19 加入 5分钟不可重复提交业务
        //订单5分钟内提交了报告不可再提交
        //1:根据质检单 查询订单 -->根据订单查询已提交的报告 日期最近的一条, 如果为空或者时间大于现在5分钟 则允许提交 ,否则不允许提交


        //查询是空的说明是正确的
        InspectKind kind = service.useInspectIdToFindInspectBillByOrderIdOrderByDateLimitOneInOrderToCheckTimeIsAllowedWithFiveMinutes(Integer.parseInt(inspectId));
        if (null == kind) {

            return "-1";
        }

        //不是空的 时间不允许
        if (null != kind && kind.getCheckDate().getTime() + 300 * 1000 > new Date().getTime()) {
            //不允许

            long time = (new Date().getTime() - kind.getCheckDate().getTime());
            return String.valueOf((5 - time / 60000));

        } else {
            //重复提交

            return "0";
        }
    }


    /**
     * 检查项页面
     *
     * @param model
     * @param request
     * @param inspectId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "itemsList")
    public String itemsList(Model model, HttpServletRequest request, String inspectId, String customerInfo) throws IOException {
        //质检要看客户信息 4-12
        model.addAttribute("customerInfo", customerInfo);


        // 1: 查询检查分类,检查项到页面上
        // 条件1:如果该检查项必选, 则必选 (已解决)
        // 条件2:如果有暂存, 直接跳到暂存

        // 条件2--> 1: 根据质检单查询是否有暂存检查记录
        List<InspectItem> zanCun = service.findZanCun(Integer.parseInt(inspectId));

        // 查询是否有图片
        List<ReportCheckDetailsPic> picList = service.findPic(Integer.parseInt(inspectId));
        int picLength = 0;
        if (picList.size() > 0) {
            picLength = picList.size();
        } else {
            picLength = 0;
        }
        // 条件2--> 2: 如果有 ,查询该检查项, 直接跳到quality_check页面

        if (null != zanCun && zanCun.size() > 0) {
            model.addAttribute("list", zanCun);
            model.addAttribute("inspectId", inspectId);
            model.addAttribute("picList", picList);
            model.addAttribute("picLength", picLength);
            String baseUrl = PicRootName.picPrefixName();
            model.addAttribute("baseUrl", baseUrl);


            //17-2-16  不合格弹出层更新  显示 质检员+项目经理+工人组长+任务包
            //根据 质检单中的订单id  查询  该订单的已分配的任务包和工人组信息(工人组id+组长姓名) + 质检员id+项目经理id+名称
            List<InspectItem> workerManagerInspectorPackageInfoByOrderId = service.findWorkerManagerInspectorPackageInfoByOrderId(Integer.parseInt(inspectId));
            //工人组+任务包+经理id+质检员id
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

        /**
         * 12-8日 加入工程模式
         */
        List<InspectKind> list = service.selectAllCheckItem(Integer.parseInt(inspectId));

        model.addAttribute("list", list);
        model.addAttribute("inspectId", inspectId);
        return "mobile/modules/pqc/check/CheckItem/check_items";
    }

    /**
     * 保存质检员选择的检查项
     *
     * @param model
     * @param request
     * @param inspectId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "saveItems")
    public String saveItems(String inspectId, Model model, HttpServletRequest request, String customerInfo) throws IOException {
        model.addAttribute("customerInfo", customerInfo);

        // 根据质检单id ,查询 选择检查项的表
        // 如果有记录, 那么是之前选择的,要全删除
        // 如果没有记录, 表示第一次选择
        service.deleteCheckItemsByCheckId(Integer.parseInt(inspectId));

        // 查询是否有图片
        List<ReportCheckDetailsPic> picList = service.findPic(Integer.parseInt(inspectId));
        int picLength = 0;
        if (picList.size() > 0) {
            picLength = picList.size();
        } else {
            picLength = 0;
        }

        String[] checkItemId = request.getParameterValues("checkItemId");
        String[] checkItemName = request.getParameterValues("checkItemName");

        // 扭转到下一个页面的参数
        List<InspectItem> list = new ArrayList<InspectItem>();

        if (null != checkItemId && checkItemId.length > 0) {

            for (int v = 0; v < checkItemId.length; v++) {
                if (!checkItemId[v].equals("build")) {
                    InspectItem item = new InspectItem();
                    // 检查项对应的质检单
                    item.setInspectBillId(Integer.parseInt(inspectId));

                    // 检查项名称不保存,作为下一页面的使用
                    item.setCheckItemName(checkItemName[v]);
                    // 对应biz_qc_check_item表中的 检查项id
                    item.setCheckItemId(Integer.parseInt(checkItemId[v]));
                    item.setCreateDate(new Date());
                    list.add(item);
                    // 保存所有选中的检查项
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


        //17-2-16  不合格弹出层更新  显示 质检员+项目经理+工人组长+任务包
        //根据 质检单中的订单id  查询  该订单的已分配的任务包和工人组信息(工人组id+组长姓名) + 质检员id+项目经理id+名称
        List<InspectItem> workerManagerInspectorPackageInfoByOrderId = service.findWorkerManagerInspectorPackageInfoByOrderId(Integer.parseInt(inspectId));
        //工人组+任务包+经理id+质检员id
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

    /**
     * 修改检查项
     *
     * @param inspectId
     * @param model
     * @return
     */
    @RequestMapping(value = "changeCheckItem")
    public String changeCheckItem(String inspectId, Model model, HttpServletRequest request, String customerInfo) {
        // 根据质检单id 查询 检查项,回显到页面
        model.addAttribute("customerInfo", customerInfo);
        // 已经选过的检查项
        List<InspectKind> hasChoosedCheckItems = service.changeCheckItem(Integer.parseInt(inspectId));
        // 所有的检查项
        List<InspectKind> allCheckItems = service.selectAllCheckItem(Integer.parseInt(inspectId));

        for (InspectKind allitem : allCheckItems) {
            for (InspectKind chooseItem : hasChoosedCheckItems) {
                // 判断分类是否相等
                if (allitem.getCheckKindId().equals(chooseItem.getCheckKindId())) {
                    // 如果这个分类显示过了

                    // 所有的检查项
                    List<InspectItem> itemList = allitem.getCheckItemList();

                    int i = itemList.size();

                    // 申请过的检查项
                    List<InspectItem> list = chooseItem.getCheckItemList();

                    for (InspectItem inspectItem : itemList) {
                        for (InspectItem item : list) {
                            // 如果安装项也一样
                            if (inspectItem.getCheckItemId().equals(item.getCheckItemId())) {
                                // 标识符,标识已选择过的
                                inspectItem.setIsChoosed("1");
                                i--;
                            }
                        }
                    }

                    if (i == 0) {
                        // 如果该分类下所有的检查项都有选过, 分类jsp checked
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

    /**
     * 根据检查项查找违规形式
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "findIllegalModality")
    public @ResponseBody
    List<IllegalModality> findIllegalModality(Model model, String checkItemId) {

        List<IllegalModality> illegalModalityList = service
                .findIllegalModalityByCheckItemId(Integer.parseInt(checkItemId));
        // 1:根据检查项 查询违规形式
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








    /**
     * 保存检查项违规形式
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "saveIllegalModalityItems")
    public @ResponseBody
    String saveCheckList(HttpServletRequest request) throws ParseException {

        service.updateCheckItem(request);

        return "OK";
    }









    /**
     * 参数: isOK , 合格的检查项id ,状态 暂存 ("0")还是提交("5") 需要保存的有 质检id,
     * 状态,质检人id,质检日期,总分,实际得分
     *
     * @param photo
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "saveCheckItemReport", method = RequestMethod.POST)
    public @ResponseBody
    String saveCheckItemReport(String[] photo, HttpServletRequest request)
            throws ParseException {

        // 质检单id
        String inspectBillId = request.getParameter("inspectId");

        // 图片路径
        if (null != photo && photo.length > 0) {


            service.savePic(photo, request, inspectBillId);

        }


        // 所有检查项id
        String[] checkItemIds = request.getParameterValues("checkItemId");


        // 1:根据检查项id,质检单id, 查询检查项不合格记录表


        Map<String, Object> map = new HashMap<>();
        if (null!=checkItemIds&&checkItemIds.length > 0) {
            map.put("inspectBillId", inspectBillId);
            map.put("checkItemIds", checkItemIds);
            List<InspectItem> inspectItems = service.selectScoresFromCheckItemRecord(map);
            try {
                service.handleEachCheckItems(inspectItems, request, inspectBillId);
                return "1"; //成功
            } catch (Exception e) {


                e.printStackTrace();
                return "2";//异常
            }


        }else{

            return "3";//无数据
        }
    }

        // 一共两个表

        // 1 检查项记录表 biz_qc_bill_check_item 保存字段: 质检单id ,检查项id , 是否合格
        // 2 质检单记录表 biz_qc_bill 更新字段: status 暂存 ("0")还是提交("5") 实际质检人id 质检日期
        // 实际得分, 总分

		/*
         * return "redirect:" + Global.getAdminPath() +
		 * "/app/pqc/checkList/list";
		 */



    @RequestMapping(value = "deletePic")
    public @ResponseBody
    String deletePic(String picId, Model model, HttpServletRequest request) {
        // 删除数据库图片
        service.deletePic(Integer.valueOf(picId));
        return "0";
    }


}
