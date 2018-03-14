package cn.damei.web.mobile.Manager;

import cn.damei.entity.mobile.Manager.ManagerNormalSettle;
import cn.damei.entity.mobile.Manager.ManagerTraditionSettleEntity;
import cn.damei.service.mobile.Manager.ManagerTraditionSettleSerivce;


import cn.damei.web.mobile.home.JobSiteController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by joseph on 2017/4/14.
 * 传统经理结算申请
 */
@Controller
@RequestMapping(value="${adminPath}/app/manager/tradition-manager-settle")
public class ManagerTraditionSettleController {
    private Logger log = LoggerFactory.getLogger(ManagerTraditionSettleController.class);//日志

    @Autowired
    private ManagerTraditionSettleSerivce service;


    @RequestMapping(value = "order-settle.php")
    public String orderSettle(HttpServletRequest request, Model model) {
        ManagerTraditionSettleEntity entity = new ManagerTraditionSettleEntity();


        List<ManagerTraditionSettleEntity> list = service.findSettleOrderList(request, entity);


        model.addAttribute("orderList", list);


        return "mobile/modules/Manager/traditionManagerSettle/Settlement";
    }

    /**
     * list搜索
     *
     * @param request
     * @param model
     * @param text
     * @return
     */
    @RequestMapping(value = "query_ajax_list")
    public @ResponseBody
    List<ManagerTraditionSettleEntity> queryAjaxList(HttpServletRequest request, Model model, String text) {

        ManagerTraditionSettleEntity entity = new ManagerTraditionSettleEntity();
        entity.setText(text);
        //根据登录的项目经理 查询名下订单并关联播报表查询相关信息 (模糊搜索)
        List<ManagerTraditionSettleEntity> list = service.findSettleOrderList(request, entity);

        if (null != list && list.size() > 0) {
            return list;
        } else {
            return null;
        }


    }


    @RequestMapping(value = "settleApply.php")
    public String settleApply(HttpServletRequest request, Model model, String orderId) {

        if (null != orderId && JobSiteController.isNum(orderId)) {

            ManagerTraditionSettleEntity entity = service.findSettleInfoByOrderId(Integer.valueOf(orderId));
            List<Integer>isCheckDoneList=service.findIsCheckDoneInfoByOrderId(Integer.valueOf(orderId));
            model.addAttribute("checkDoneList", isCheckDoneList);
            model.addAttribute("entity", entity);
        } else {

            log.warn("项目经理结算,订单id有误 orderid:" + orderId + "/n 时间:" + new Date());
        }


        return "mobile/modules/Manager/traditionManagerSettle/setApply";
    }

    /**
     *
     * @param request
     * @param model
     * @param settleId
     * @param orderId
     * @param settleIndex
     * @return
     */
    @RequestMapping(value = "toUploadSettleInfo.php")
    public  String toUploadSettleInfo(HttpServletRequest request, Model model, String settleId, String orderId,String settleIndex) {

        //如果settleId为空 ,那么表明第一次申请
        //如果settleID不为空, 表明是查看明细

        if ("".equals(settleId.trim())) {
            //不是数字
            //根据订单查询信息
            ManagerTraditionSettleEntity entity = service.findSettleInfoDetailByIndexAndOrderId(orderId,settleIndex);

            model.addAttribute("entity", entity);
            return "mobile/modules/Manager/traditionManagerSettle/setOne";

        } else {
            if (JobSiteController.isNum(settleId)){
                List<ManagerNormalSettle> settleList= service.findSettleInfoDetailBySettleId(settleId);
                ManagerNormalSettle settle=null;
                if(settleList.size()>0){
                    settle=settleList.get(0);
                }
                model.addAttribute("entity", settle);
                model.addAttribute("picList", settleList);
                model.addAttribute("picPrefix",request.getContextPath());

                //已经提交过的结算单,直接查询 并回显详情
                return "mobile/modules/Manager/traditionManagerSettle/state";

            }else{

                log.warn("结算单id有误,在项目经理端申请结算时: settleId=" + settleId);
                return "mobile/modules/Manager/traditionManagerSettle/state";

            }


        }


    }

    /**
     * orderId
     * checkNodeId
     * settleApplyRemarks
     * photos
     *保存settle单
     * @param request
     * @param model
     * @param entity
     * @return
     */
    @RequestMapping(value = "saveSettleInfo")
    public @ResponseBody String saveSettleInfo(HttpServletRequest request, Model model, ManagerTraditionSettleEntity entity,String [] photos) {

    //1:失败 2:成功
               String result= service.saveSettleInfo(request,entity,photos);

            return result;
        }

}