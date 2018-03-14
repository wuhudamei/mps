package cn.damei.web.mobile.Manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.impl.cookie.DateParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.Base64Util;
import cn.damei.entity.mobile.Manager.BusinessPic;
import cn.damei.service.mobile.Manager.BusinessPicService;
import cn.damei.entity.mobile.Manager.CompletedOrder;
import cn.damei.entity.mobile.Manager.OrderFinishProjectBill;
import cn.damei.service.mobile.Manager.CompletedOrderService;
import cn.damei.service.mobile.Manager.OrderFinishProjectBillService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.ManagerNormalSettle;
import cn.damei.service.mobile.Manager.ManagerTraditionSettleSerivce;
import cn.damei.entity.mobile.Manager.BizQcBill;
import cn.damei.service.mobile.Manager.QualityControlService;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.service.modules.BizSeiralnumService;

/**
 * 项目经理端APP
 * 申请竣工
 * BIZ_ORDER
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class CompletedOrderController {

    private static Logger logger = LoggerFactory.getLogger(CompletedOrderController.class);

    @Autowired
    private CompletedOrderService completedOrderService;
    @Autowired
    private BizPhoneMsgService bizPhoneMsgService;
    @Autowired
    private OrderFinishProjectBillService OrderFinishProjectBillService;
    @Autowired
    private BusinessPicService businessPicService;
    @Autowired
    private BizEmployeeService2 bizEmployeeService2;
    @Autowired
    private BizMessagegroupService bizMessagegroupService;
    @Autowired
    private BizSeiralnumService bizSeiralnumService;
    @Autowired
    private QualityControlService qualityControlService;
    @Autowired
    private ManagerTraditionSettleSerivce managerTraditionSettleSerivce;

    @Autowired
    private BizBusinessStatusLogDao bizBusinessStatusLogDao;
    /**
     * 列出申请竣工列表
     * 200-施工中至400-确认已竣工
     */
    @RequestMapping(value = {"completedList", ""})
    public String packageList(CompletedOrder completedOrder, Model model, HttpServletRequest request) {
        //已登录的项目经理
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        logger.info("项目经理主键ID========" + manager.getId());

        List<CompletedOrder> orderList = completedOrderService.queryList(manager.getId());
        for (CompletedOrder order : orderList) {
            logger.info("列出当前登陆项目经理编号查询订单表中所有的订单编号：" + order.getId());
        }

        model.addAttribute("orderList", orderList);
        return "mobile/modules/Manager/progressMain/completed/completedList";
    }

    @RequestMapping(value = {"checkCompleted"})
    public @ResponseBody
    String checkCompleted(Integer orderId) {
        String result = "0";
        ManagerNormalSettle managerTraditionSettle = managerTraditionSettleSerivce.queryLastNormalSettleNode(orderId);
        if (managerTraditionSettle == null || managerTraditionSettle.getSettleStatus() == null || managerTraditionSettle.getSettleStatus().equals("") || managerTraditionSettle.getSettleStatus().equals("1")) {
            result = "1";
        }
        return result;
    }

    /**
     * list主页面申请竣工功能
     */
    @RequestMapping(value = {"applyCompleted", ""})
    public String applyCompleted(String orderID, Model model, HttpServletRequest request) {
        logger.info("订单编号：" + orderID);
        CompletedOrder order = null;
        OrderFinishProjectBill proBill = null;
        if (!orderID.equals("")) {
            order = completedOrderService.get(Integer.valueOf(orderID));
        }
        if (null!=order && order.getProjectMode().equals("2")) {//传统订单

        }
        if (null != order) {
            //如果订单是330(结算员竣工审核不通过)PC操作驳回
            if (order.getOrderStatusNumber().equals(ConstantUtils.ORDERSTATUS_330_VALUE)) {
                logger.info("orderstatusNumber=" + order.getOrderStatusNumber());
                proBill = OrderFinishProjectBillService.getByOrderID(order.getId());
            }
        }

        model.addAttribute("order", order);
        model.addAttribute("finishProjectBill", proBill);
        return "mobile/modules/Manager/progressMain/completed/applyCompleted";
    }

    /**
     * 主页面
     * 查看详情功能
     *
     * @throws IOException
     */
    @RequestMapping(value = {"completedtDetail"})
    public String completedtDetail(CompletedOrder completedOrder, HttpServletRequest request,
                                   Model model, String orderID) throws IOException {
        logger.info("orderID==" + orderID);
        OrderFinishProjectBill finishProBill = null;
        List<BusinessPic> picList = null;
        CompletedOrder order = null;
        if (!orderID.equals("")) {
            finishProBill = OrderFinishProjectBillService.queryListByOrderID(Integer.valueOf(orderID));
            order = completedOrderService.get(Integer.valueOf(orderID));
            if (null != finishProBill) {
                picList = businessPicService.queryByBusinessID(finishProBill.getId());
            }
        }

        model.addAttribute("picPrefixName", PicRootName.picPrefixName());
        model.addAttribute("order", order);
        model.addAttribute("busPicList", picList);
        model.addAttribute("orderFinishProBill", finishProBill);
        return "mobile/modules/Manager/progressMain/completed/completedtDetail";
    }

    /**
     * 申请竣工页面
     * 确认申请功能
     */
    @ResponseBody
    @RequestMapping(value = {"cofirmCompletedSubmit", ""})
    public String cofirmCompletedSubmit(String orderID, String photos1, String photos2, String photos3,
                                        String realFinishProjectDate, HttpServletRequest request) throws DateParseException, IOException {
        logger.info("订单编号：" + orderID + "\t\t realFinishProjectDate=" + realFinishProjectDate);
        logger.info("工程竣工验收单：" + photos1);
        logger.info("项目总结书：" + photos2);
        logger.info("工程竣工验收单：" + photos3);
        //已登录的项目经理
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        logger.info("项目经理主键ID========" + manager.getId());

        String result = "0";
        CompletedOrder order = null;
        //获取当前订单数据
        if (!orderID.equals("")) {
            order = completedOrderService.getByID(Integer.valueOf(orderID));
        } else {
            result = "1";
        }
        int finshProjectBillId = 0;

        OrderFinishProjectBill proBill = OrderFinishProjectBillService.getByOrderID(Integer.valueOf(orderID));
        if (proBill == null) {
            //biz_order_finish_project_bill写数据
            if (!realFinishProjectDate.equals("")) {
                //获取编号规则：竣工单的编号规则：“JG”+年月日（四位）+四位流水号；比如：JG201611090001。
                String number = bizSeiralnumService.getDateSequence(ConstantUtils.APP_COMPLETED_STRING);
                logger.info("确认竣工生成编号规则：" + number);
                finshProjectBillId = OrderFinishProjectBillService.insert(realFinishProjectDate, orderID, number, manager.getId());
            } else {
                result = "1";
            }
        } else {

            //如果不为空 则是 更新新的, 直接拿到id 即可  不更改 就是 初始化的0了, 而作为图片的外键 就 永远保存不了新的图片
            finshProjectBillId = proBill.getId();
            result = OrderFinishProjectBillService.updateByMore(realFinishProjectDate, orderID, manager.getId(), DateUtils.getDate1("yyyy-MM-dd HH:mm:ss"), String.valueOf(finshProjectBillId));

            //如果不想看到之前的图片 删掉即可
        }

        if (finshProjectBillId > 0 || result.equals("0")) {
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            String imgUrl = PicRootName.getConfigValue(ConstantUtils.UPLOAD_COMPLETED);
            if (null != photos1) {
                //生成UUID
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                File filePath = new File(rootPath + imgUrl + DateUtils.getDate1());
                //判断该文件是否存在
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                String picUrl = imgUrl + DateUtils.getDate1() + "/" + uuid + ".jpeg";
                String fullPath = filePath + filePath.separator + uuid + ".jpeg";
                logger.info("完整路径：" + fullPath);
                Base64Util.generateImage(photos1, fullPath.toString());//base64解析成图片

                businessPicService.insertPic(photos1, ConstantUtils.COMPLETED_PIC_TYPE_101, finshProjectBillId, picUrl);
            }

            if (null != photos2) {
                //生成UUID
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                File filePath = new File(rootPath + imgUrl + DateUtils.getDate1());
                //判断该文件是否存在
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                String picUrl = imgUrl + DateUtils.getDate1() + "/" + uuid + ".jpeg";
                String fullPath = filePath + filePath.separator + uuid + ".jpeg";
                logger.info("完整路径：" + fullPath);
                Base64Util.generateImage(photos2, fullPath.toString());//base64解析成图片

                businessPicService.insertPic(photos2, ConstantUtils.COMPLETED_PIC_TYPE_102, finshProjectBillId, picUrl);
            }

            if (null != photos3) {
                //生成UUID
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                File filePath = new File(rootPath + imgUrl + DateUtils.getDate1());
                //判断该文件是否存在
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                String picUrl = imgUrl + DateUtils.getDate1() + "/" + uuid + ".jpeg";
                String fullPath = filePath + filePath.separator + uuid + ".jpeg";
                logger.info("完整路径：" + fullPath);
                Base64Util.generateImage(photos3, fullPath.toString());//base64解析成图片

                businessPicService.insertPic(photos3, ConstantUtils.COMPLETED_PIC_TYPE_103, finshProjectBillId, picUrl);
            }
        }

        result = completedOrderService.updateByStatus(ConstantUtils.ORDERSTATUS_300_VALUE,
                ConstantUtils.ORDERSTATUS_300_VALUE_REMARK, orderID);
        
     // 添加状态日志信息
        BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
        statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_4100);//项目经理申请竣工
        statusLog.setBusinessOnlyMarkInt(finshProjectBillId);
        statusLog.setBusinessStatus(ConstantUtils.ORDERSTATUS_300_VALUE);//项目经理申请竣工
        statusLog.setStatusDatetime(new Date());
        statusLog.setBusinessEmployeeId(manager.getId());
        statusLog.setBusinessRemarks("项目经理申请竣工");
        statusLog.preInsert();
        bizBusinessStatusLogDao.insert(statusLog);


        /***********************通知结算员-项目经理申请竣工***************************/
        List<Integer> list = new ArrayList<Integer>();
        BizMessagegroup bizMessagegroup = null;
        if(null!=order && null != order.getStoreId()){
        	bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "7");
        }
        List<BizEmployee2> employeelist = null;
        if (null != bizMessagegroup) {
            String[] str = bizMessagegroup.getEmployees().split(",");
            for (String id : str) {
                list.add(Integer.valueOf(id));
            }
            employeelist = bizEmployeeService2.getById(list);
            if (list.size() > 0 && employeelist.size() > 0) {
                for (BizEmployee2 employee : employeelist) {
                    logger.info("结算员手机号：" + employee.getPhone());
                    /**发送短信给结算员**/
                    BizPhoneMsg p = new BizPhoneMsg();
                    p.setId(null);
                    p.setReceivePhone(employee.getPhone().trim());
                    p.setReceiveEmployeeId(employee.getId());
                    p.setMsgContent("订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName()
                            + "-" + order.getCustomerPhone() + "），项目经理（" + order.getManagerRealName() + "-" + order.getManagerPhone() + "），项目经理已申请竣工，请登录系统进行审核。");
                    p.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
                    p.setMsgTosendDatetime(null);
                    p.setMsgSendedDatetime(null);
                    p.setMsgStatus("0");
                    p.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_500101);
                    p.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
                    p.setRelatedBusinessIdVarchar("");
                    bizPhoneMsgService.save(p);
                }
            }
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "isAllChecked")
    public String isAllChecked(Integer orderId) {
        String flag = "0";
        BizQcBill bill = qualityControlService.findQcBillByOrderIdForCompleted(orderId);
        if (bill != null) {
            flag = "1";
        }
        return flag;
    }
}