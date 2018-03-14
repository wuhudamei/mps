package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Manager.ConfirmStartOrder;
import cn.damei.service.mobile.Manager.ConfirmStartService;
import cn.damei.entity.mobile.Manager.Manager;



@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class ConfirmStartController {

    private static Logger logger = LoggerFactory.getLogger(ConfirmStartController.class);

    @Autowired
    private ConfirmStartService confirmStartService;


    @RequestMapping(value = {"confirmStart", ""})
    public String packageList(ConfirmStartOrder confirmStartOrder, Model model, HttpServletRequest request) {


        Manager manager = (Manager) request.getSession().getAttribute("manager");
        logger.info("项目经理ID========" + manager.getId());

        List<ConfirmStartOrder> orderList = confirmStartService.queryList(manager.getId());
        for (ConfirmStartOrder order : orderList) {
            logger.info("列出当前登陆项目经理编号查询订单表中所有项目经理的订单编号：" + order.getId());
        }

        model.addAttribute("orderList", orderList);
        return "mobile/modules/Manager/progressMain/confirmStart/startList";
    }


    @RequestMapping(value = {"confirmStartDetail", ""})
    public String confirmStartDetail(ConfirmStartOrder confirmStartOrder, Model model, HttpServletRequest request) {
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        logger.info("orderId====" + orderId);
        ConfirmStartOrder cso = confirmStartService.getByOrderId(orderId);

        logger.info("订单编号(id)：" + cso.getId());
        model.addAttribute("confirmStartOrder", cso);
        model.addAttribute("projectMode", cso.getProjectMode());
        return "mobile/modules/Manager/progressMain/confirmStart/startDetail";
    }



    @RequestMapping(value = {"updateById", ""})
    public @ResponseBody String startUpdate(String houseIsNew, String projectMode, String storeId, String orderId, String input_date, String startRemark, String dateCompare,
                       String delayType, String decorateDelayDays, String isSelfDecorateNeedSign, String[] photos, String isNeedSign, HttpServletRequest request){
       
    	Manager manager = (Manager) request.getSession().getAttribute("manager");
        
        return confirmStartService.saveConfirmStart(houseIsNew, projectMode, storeId, orderId, input_date, startRemark, 
        		dateCompare, delayType, decorateDelayDays, isSelfDecorateNeedSign, 
        		photos, isNeedSign, request,manager);
    } 


    @RequestMapping(value = {"progressBuiletin", ""})
    public String progressBuiletin(ConfirmStartOrder confirmStartOrder, Model model, HttpServletRequest request) {

        Manager manager = (Manager) request.getSession().getAttribute("manager");
        logger.info("项目经理ID========" + manager.getId());

        List<ConfirmStartOrder> progressBulletinList = null;

        progressBulletinList = confirmStartService.queryByManagerIdList(manager.getId());

        model.addAttribute("progressBulletinList", progressBulletinList);
        return "mobile/modules/Manager/progressMain/progressBulletin/progressBulletinList";
    }


    


}
