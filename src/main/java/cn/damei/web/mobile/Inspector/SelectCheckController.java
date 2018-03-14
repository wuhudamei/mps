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

/**
 * 质检端    抽检
 *
 * @author Administrator
 */
@Controller
@RequestMapping(value = "${adminPath}/app/pqc/selectCheckList")
public class SelectCheckController {

    @Autowired
    private SelectCheckService selectCheckService;


    private Logger logger = LoggerFactory.getLogger(SelectCheckController.class);

    @RequestMapping(value = "list")
    private String list(String text, String timeForbidden, Model model, HttpServletRequest request) {

        //获取质检员信息
        Inspector inspector = SessionUtils.getInspectorSession(request);

        List<Order> list = null;
        if (null != text && !"".equals(text)) {
            inspector.setSelectCheckText(text);
            request.getSession().setAttribute("inspector", inspector);
        }


        //通过质检员id查询订单
        Order or = new Order();
        or.setOrderInspectorId(Integer.valueOf(inspector.getId()));
        or.setText(inspector.getSelectCheckText());

        if (null != inspector.getIsLeader() && 1 == inspector.getIsLeader()) {
            //领导
            if (null != inspector.getSelectCheckText() && !"".equals(inspector.getSelectCheckText())) {
                list = selectCheckService.findOrderByLeaderInspectorId(or);
            }
        } else {
            //质检
            list = selectCheckService.findOrderByInspectorId(or);
        }

        if (null != list && list.size() > 0) {
            for (Order order : list) {
                //根据订单id查询抽检单的id和状态

                if (null == order.getQcBillId()) {
                    //不存在抽检单
                    order.setQcBillId(0);
                    order.setQcBillStatus("-2");

                }
            }
        }
        model.addAttribute("timeForbidden", timeForbidden);
        model.addAttribute("list", list);
        model.addAttribute("text", inspector.getSelectCheckText());

        if (null != inspector.getIsLeader() && 1 == inspector.getIsLeader()) {
            //领导
            return "mobile/modules/pqc/selectCheck/leaderSelectCheck/leader_select_check";
        } else {
            //质检
//        	 return "mobile/modules/pqc/selectCheck/leaderSelectCheck/leader_select_check";
            return "mobile/modules/pqc/selectCheck/select_check";
        }
    }


    @RequestMapping(value = "query_list_ajax")
    private @ResponseBody
    List<Order> queryListAjax(Model model, HttpServletRequest request, String text) {
        //获取质检员信息
        Inspector inspector = SessionUtils.getInspectorSession(request);
        if (null != text && !"".equals(text)) {
            inspector.setSelectCheckText(text);
            request.getSession().setAttribute("inspector", inspector);
        }
        //通过质检员id查询订单
        Order or = new Order();
        or.setOrderInspectorId(Integer.valueOf(inspector.getId()));
        or.setText(text);
        List<Order> list = selectCheckService.findOrderByInspectorId(or);
        if (list.size() > 0) {
            for (Order order : list) {
                //根据订单id查询抽检单的id和状态

                if (null == order.getQcBillId()) {
                    //不存在抽检单
                    order.setQcBillId(0);
                    order.setQcBillStatus("-2");

                }
            }
        }

        return list;

    }


    /**
     * 检查项页面
     *
     * @param model
     * @param request
     * @param qcBillId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "selectItemsList")
    public String selectItemsList(Model model, HttpServletRequest request, String qcBillId, String orderId, String customerInfo) throws IOException {
        model.addAttribute("customerInfo", customerInfo);


        //查询该订单最新一次抽检的时间是否间隔有5分钟
        BizQcBill bizQcBill = selectCheckService.findTimeSpan(Integer.valueOf(orderId));
        if (null != bizQcBill) {


            if (bizQcBill.getCheckDatetime().getTime() + 300 * 1000 > new Date().getTime()) {
                //如果小于5分钟 则不允许抽检,并给出提示
                return "redirect:" + Global.getAdminPath() + "/app/pqc/selectCheckList/list?timeForbidden=1";
            }
        }


        //1: 查询检查分类,检查项到页面上

        //条件1:如果该检查项必选, 则必选 (已解决)
        //条件2:如果有暂存,  直接跳到暂存

        //条件2--> 1: 根据抽检单查询是否有暂存检查记录
        List<InspectItem> zanCun = selectCheckService.findZanCun(Integer.parseInt(qcBillId));

        //查询是否有图片
        List<ReportCheckDetailsPic> picList = selectCheckService.findPic(Integer.parseInt(qcBillId));
        int picLength = 0;
        if (picList.size() > 0) {
            picLength = picList.size();
        }

        //根据抽检单id查询是否有签到信息
        Integer signNum = selectCheckService.findSign(Integer.valueOf(qcBillId));

        //条件2--> 2: 如果有 ,查询该检查项, 直接跳到quality_check页面

        if (null != zanCun && zanCun.size() > 0) {
            model.addAttribute("list", zanCun);
            model.addAttribute("inspectId", qcBillId);
            model.addAttribute("orderId", orderId);

            model.addAttribute("picList", picList);
            model.addAttribute("picLength", picLength);
            model.addAttribute("baseUrl", PicRootName.picPrefixName());
            model.addAttribute("signNum", signNum);

            //17-2-16  不合格弹出层更新  显示 质检员+项目经理+工人组长+任务包
            //根据 质检单中的订单id  查询  该订单的已分配的任务包和工人组信息(工人组id+组长姓名) + 质检员id+项目经理id+名称
            List<InspectItem> workerManagerInspectorPackageInfoByOrderId = selectCheckService.findWorkerManagerInspectorPackageInfoByOrderId(Integer.parseInt(qcBillId));
            //工人组+任务包+经理id+质检员id

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
        //根据订单id查询查询检查分类和检查项
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


    /**
     * 保存质检员选择的检查项
     *
     * @param model
     * @param request
     * @param qcBillId
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "saveItems")
    public String saveItems(String qcBillId, String orderId, Model model, HttpServletRequest request, String customerInfo) throws IOException {


        //抽检重复提交
        Integer count = selectCheckService.findIsExistSelectQcBillById(qcBillId);

        if (null != count && count > 0) {
            return "redirect:" + Global.getAdminPath() + "/app/pqc/selectCheckList/list?timeForbidden=2";
        }

        selectCheckService.saveItems(orderId, qcBillId, model, request);

        model.addAttribute("customerInfo", customerInfo);
        return "mobile/modules/pqc/selectCheck/selectCheckItem/quality_check";
    }

    /**
     * 修改检查项
     *
     * @param inspectId
     * @param model
     * @return
     */
    @RequestMapping(value = "changeCheckItem")
    public String changeCheckItem(String inspectId, String orderId, Model model, String customerInfo) {


        if (!"0".equals(inspectId)) {

            //抽检重复提交
            Integer count = selectCheckService.findIsExistSelectQcBillById(inspectId);

            if (null != count && count > 0) {


                return "redirect:" + Global.getAdminPath() + "/app/pqc/selectCheckList/list?timeForbidden=2";


            }


        }


        // 根据质检单id 查询 检查项,回显到页面

        model.addAttribute("customerInfo", customerInfo);
        // 已经选过的检查项
        List<InspectKind> hasChoosedCheckItems = selectCheckService.changeCheckItem(Integer.parseInt(inspectId));

        //根据订单id查询查询检查分类和检查项
        List<InspectKind> allCheckItems = selectCheckService.selectAllCheckItem(Integer.valueOf(orderId));

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
        model.addAttribute("qcBillId", inspectId);
        model.addAttribute("orderId", orderId);
        return "mobile/modules/pqc/selectCheck/selectCheckItem/check_items";
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

        // 1:根据检查项 查询违规形式
        List<IllegalModality> illegalModalityList = selectCheckService.findIllegalModalityByCheckItemId(Integer.parseInt(checkItemId));

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
        InspectItem item = new InspectItem();

        //获取质检员信息
        Inspector inspector = SessionUtils.getInspectorSession(request);

        Date date = new Date();
        List<IllegalModality> list = new ArrayList<IllegalModality>();
        //获取质检单ID
        String inspectBillId = request.getParameter("inspectId");


        //抽检重复提交
        Integer count = selectCheckService.findIsExistSelectQcBillById(inspectBillId);

        if (null != count && count > 0) {
            return "9";
        }
        item.setInspectBillId(Integer.parseInt(inspectBillId));


        //====================================================检查项记录=====================================================
        // 警告
        String isWarn = request.getParameter("deal1");
        item.setIsOk("0");
        if ("1".equals(isWarn)) {
            //不合格,警告
            item.setIsWarn("1");

        } else {
            //不合格, 不警告
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
            item.setLimitDate(new SimpleDateFormat("yyyy-MM-dd").parse(limitDate));
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
            //项目经理罚分
            item.setManagerScore(Double.parseDouble(managerScore));
            item.setManagerId(Integer.parseInt(managerId.trim().equals("") ? "0" : managerId));
            item.setActualPunishMoney(Double.parseDouble(managerMoney));
            item.setIsPunishMoney("1");
        } else {
            //项目经理未罚分
            item.setManagerScore(0d);
            //项目经理未罚钱
            item.setActualPunishMoney(0d);
            item.setIsPunishMoney("0");
        }


        if (JobSiteController.isNum(pqcFine) && !pqcFine.trim().equals("") && "1".equals(pqcFine)) {
            //質檢罚分
            item.setInspectorScore(Double.parseDouble(inspectorScore));
            item.setInspectorId(Integer.parseInt(inspectorId));
            //質檢罚钱
            item.setInspectorMoney(Double.parseDouble(inspectorMoney));
            item.setIsPunishMoney("1");
        } else {
            //質檢未罚分
            item.setInspectorScore(0d);
            //質檢未罚钱
            item.setInspectorMoney(0d);
        }

        if (null != packId && !packId.trim().equals("")) {

            Integer workerGroupId = selectCheckService.findWorkerInfoByPackId(Integer.parseInt(packId));


            if (null != workerFine && JobSiteController.isNum(workerFine) && !workerFine.trim().equals("") && "1".equals(workerFine)) {
                //工人组罚分
                item.setWorkerScore(Double.parseDouble(workerGroupScore));
                item.setPackId(Integer.parseInt(packId));
                //工人组罚钱
                item.setWorkerMoney(Double.parseDouble(workerGroupMoney));
                item.setWorkerGroupId(workerGroupId);
                item.setIsPunishMoney("1");
            } else {
                //工人组未罚分
                item.setWorkerScore(0d);
                //工人组未罚钱
                item.setWorkerMoney(0d);

            }

        }


        item.setUpdateDate(date);

        //违规项ids
        String[] ids = request.getParameterValues("id");
        // 对应的备注
        String[] remarks = request.getParameterValues("remarks");
        Integer xn = -1;
        int checkItemId = 0;
        for (int v = 0; v < ids.length; v++) {
            xn++;
            // 对应的违规形式id,根据id查询检查项id ,所占分数,是否有备注
            InspectItem inspectItem = selectCheckService.selectCheckItemInfoByIllegalModalityId(Integer.parseInt(ids[v]));
            //设置检查项id
            item.setCheckItemId(inspectItem.getCheckItemId());
            //原本分数
            item.setPreScores(inspectItem.getPreScores());
            //全扣了
            item.setActualScores(0d);
//			//保存检查项记录表
//			selectCheckService.updateCheckItem(item);
//====================================================违规记录=====================================================			
            IllegalModality modality = new IllegalModality();
            //违规形式的检查项id
            checkItemId = selectCheckService.selectCheckItemId(item);
            modality.setCheckItemId(checkItemId);
            //违规形式id
            modality.setIllegalModalityId(Integer.parseInt(ids[v]));
            //违规形式name
            modality.setIllegalModalityName(inspectItem.getRemarks());
            //质检单id
            modality.setInspectId(item.getInspectBillId());


            //如果违规形式有备注
            if (null != inspectItem.getIsRequired() && !"".equals(inspectItem.getIsRequired()) && inspectItem.getIsRequired().equals("1")) {
                //有备注
                modality.setIsRemarks("1");
                //备注的名字
                modality.setRemarks(remarks[xn]);
            } else {
                //如果没有备注
                modality.setIsRemarks("0");
                xn--;
            }
            list.add(modality);
            //保存 违规形式记录表
//			selectCheckService.saveIllegalModality(modality);

        }
        //删除之前的该检查项的违规形式
        selectCheckService.batchDeleteQcBillCheckItemBreak(checkItemId);

        //批量保存违规形式纪录表
        if(null != list && list.size() >0){
            selectCheckService.saveIllegalModalityAll(list);
        }
        //保存检查项记录表
        selectCheckService.updateCheckItem(item);
        return "OK";
    }


    /**
     * 参数: isOK , 合格的检查项id ,状态  暂存 ("0")还是提交("5")
     * 需要保存的有 质检id, 状态,质检人id,质检日期,总分,实际得分
     *
     * @param
     * @param request
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "saveCheckItemReport", method = RequestMethod.POST)
    public @ResponseBody
    String saveCheckItemReport(String[] photo, HttpServletRequest request) throws ParseException {


        //质检单id
        String inspectBillId = request.getParameter("inspectId");


//		InspectKind inspectKind = selectCheckService.findOrderIdByBillId(Integer.parseInt(inspectBillId));
////抽检重复提交
        Integer count = selectCheckService.findIsExistSelectQcBillById(inspectBillId);

        if (null != count && count > 0) {


            return "9";


        }


//		//质检员id
//		Integer checkManId = SessionUtils.getInspectorSession(request).getId();//质检日期

        //所有检查项id
        String[] checkItemIds = request.getParameterValues("checkItemId");

//
//		//检查项的各项分数总分, 保存在质检单中
//		Double  totalScores =0d;
//		Double actualScores=0d;
//		Integer x = 0;
//		Integer recheckId = null;
//		InspectItem inspectItem = new InspectItem();
//
//		List<InspectItem> hege = new ArrayList<InspectItem>();
//		List<Recheck> buhege = new ArrayList<Recheck>();


        Map<String, Object> map = new HashMap<>();
        if (null != checkItemIds && checkItemIds.length > 0) {
            map.put("inspectBillId", inspectBillId);
            map.put("checkItemIds", checkItemIds);
            List<InspectItem> inspectItems = selectCheckService.selectScoresFromCheckItemRecord(map);
            try {
                selectCheckService.handleEachCheckItems(inspectItems, request, inspectBillId, photo);
                return "1"; //成功
            } catch (Exception e) {
                e.printStackTrace();
                return "2";//异常
            }


        } else {

            return "3";//无数据
        }
//		//1:根据检查项id,质检单id, 查询检查项不合格记录表
//		if(null!=checkItemIds){
//
//			for(int v=0;v<checkItemIds.length;v++){
//				inspectItem.setCheckItemId(Integer.parseInt(checkItemIds[v]));
//				inspectItem.setInspectBillId(Integer.parseInt(inspectBillId));
//				InspectItem record = selectCheckService.selectScoresFromCheckItemRecord(inspectItem);
//				Double scores=0d;
//				//根据该检查项id 查询分数等
//				scores = selectCheckService.selectCheckItemScores(Integer.parseInt(checkItemIds[v]));
//				if(null==record.getIsOk() || !record.getIsOk().equals("0")){
//					//2:如果查不到记录,表示合格, 保存到检查项记录表中
//
//					InspectItem item = new InspectItem();
//					//质检单id
//					item.setInspectBillId(Integer.parseInt(inspectBillId));
//					//检查项id
//					item.setCheckItemId(Integer.parseInt(checkItemIds[v]));
//					//合格
//					item.setIsOk("1");
//					//该检查项不扣分
//					item.setPreScores(scores);
//					item.setActualScores(scores);
//					item.setUpdateDate(date);
//					item.setId(record.getCheckItemId());
//					//保存合格的检查项
////					selectCheckService.updateCheckItem(item);
//					hege.add(item);
//					//合格的实际分数
//					actualScores+=scores;
//					totalScores+=scores;
//				}else if(record.getIsOk().equals("0")){
//					//3:如果一样   总分相加
//					totalScores+=scores;
//					// 不合格, 要看是否为限期整改
//
//					if("5".equals(request.getParameter("status"))){
//
//						if (record.getIsLimitDateChange().equals("1")) {
//							// 如果是限期整改 , 插入质检单 ,保存不合格的检查项, 作为复检内容
//							// 需要保存的数据有,biz_qc_bill : code,type isRecheck relatedBillId
//							// , status
//							// 先保存一条复检单数据
//							if (x != 1) {
//								Recheck recheck;
//								recheck = new Recheck();
//								recheck.setReCheckCode(selectCheckService.qcBillCode());
//								recheck.setType("2");
//								recheck.setIsReCheck("1");
//								recheck.setRelatedBillId(Integer.parseInt(inspectBillId));
//								recheck.setStatus("1");
//								recheck.setCreateDate(date);
//								recheck.setOrderId(inspectKind.getOrderId());
//								recheck.setCheckNodeId(inspectKind.getCheckNodeId());
//								selectCheckService.saveRecheck(recheck);
//								recheckId = recheck.getReCheckId();
//								x++;
//
//								// 保存发送短信内容
//								// 订单（东晨小区-10-4-202-王维-13333333333），质检员（王毅-13212341234），已产生复检单，请及时登录APP查看详情。
//								//发给项目经理, 短信内质检员
//
//								QualityControl control = checkItemService.findMessageInfoByInspectId(Integer.parseInt(inspectBillId));
//								String content = "订单（" + control.getCommunityName() + "-" + control.getBuildNumber() + "-" + control.getBuildUnit() + "-" + control.getBuildRoom() + "-" + control.getCustomerName() + "-" + control.getCustomerPhone() + "，质检员（" + SessionUtils.getInspectorSession(request).getRealName() + "-" + SessionUtils.getInspectorSession(request).getPhone() + "），已产生复检单，请及时登录APP查看详情。	";
//								PhoneMessageEntity entity = new PhoneMessageEntity();
//								entity.setReceiveEmployeeId(control.getItemManagerId());
//								entity.setReceivePhone(control.getPhone());
//								entity.setMessageContent(content);
//								entity.setMessageGenerateTime(date);
//								entity.setStatus("0");
//								entity.setRelatedBusinessId(Integer.parseInt(inspectBillId));
//								entity.setRelatedBusinessType("600201");
//								dao.saveMessageContent(entity);
//
//								//=====================================消息推送start========================================================
//
//								/**
//								 * 消息推送   消息推送类型 103001002-通知项目经理-抽检已产生复检单
//								 */
//								BizMsg bizMsg = new BizMsg();
//								bizMsg.setMsgTitle("质检员产生复检单");
//								bizMsg.setMsgTime(date);
//								bizMsg.setMsgContent(content);
//								bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
//								bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_103001002);
//								bizMsg.setBusiIdInt(recheckId);
//								bizMsg.setEmployeeId(control.getItemManagerId());
//								bizProjectChangeBillService.saveBizMsg(bizMsg);
//								//=====================================消息推送end========================================================
//
//
//							}
//
//							// 在保存该复检单的不合格检查项
//							// 检查项表: biz_qc_bill_check_item : billId ,
//							// relatedCheckItemId ,checkItemId ,isOk PreScores
//							// ,actualScore
//							// changeWay
//							if (record.getIsLimitDateChange().equals("1")) {
//								Recheck recheck2 = new Recheck();
//								// 检查项相关联的复检单id
//								recheck2.setRelatedBillId(recheckId);
//								// 不合格的检查项id
//								recheck2.setRelatedCheckItemId(record.getCheckItemId());
//								recheck2.setCheckItemId(Integer.parseInt(checkItemIds[v]));
//								// 肯定不合格
//								recheck2.setIsOk("0");
//								// 得分
//								recheck2.setPreScores(scores);
//								// 实际得分
//								recheck2.setActualScores(0d);
//								// 线上线下
//								recheck2.setChangeWay(record.getLimitChangeWay());
//
//								recheck2.setCreateBy(checkManId);
//								recheck2.setCreateDate(date);
//								recheck2.setUpdateBy(checkManId);
//								recheck2.setUpdateDate(date);
//
//								buhege.add(recheck2);
////								selectCheckService.saveRecheckCheckItem(recheck2);
//							}
//						}
//
//
//
//
//					}
//
//				}
//
//
//			}
//
//			if(null!=fakuan && fakuan.size()>0){
//
//				//批量保存罚款明细
//				checkItemService.saveSettleFineRecordAll(fakuan);
//			}
//		}
//	//=====================================更新质检单=======================================================
//
//		//1:根据质检单id查询检查项记录表的得分情况
//	InspectKind kind = new InspectKind();
//
//	//质检单id
//	kind.setInspectBillId(Integer.parseInt(inspectBillId));
//	//实际质检人id
//	kind.setActualCheckPersonId(checkManId);
//	//质检时间
//	kind.setCheckDate(new Date());
//	//总分
//	kind.setTotalScores(totalScores);
//	//实际得分
//	kind.setActualScores(actualScores);
//		//暂存还是提交报告
//		String status = request.getParameter("status");
//			kind.setStatus(status);//0或者5
//
//	//2:   更新抽检单
//			selectCheckService.updateInspect(kind);
//
//		return "1";
    }

    @RequestMapping(value = "deletePic")
    public @ResponseBody
    String deletePic(String picId, Model model, HttpServletRequest request) {
        //删除数据库图片
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
                //删除之前的该检查项的违规形式
                selectCheckService.batchDeleteQcBillCheckItemBreak(Integer.valueOf(checkItemId));
return "1";
            }

        }catch (Exception e){
            return "2";

        }

        return "2";



    }

}
