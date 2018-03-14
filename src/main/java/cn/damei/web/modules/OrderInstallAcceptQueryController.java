package cn.damei.web.modules;

import cn.damei.common.constantUtils.BusinessUrgeConstantUtil;
import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizOrderInstallPlan;
import cn.damei.service.modules.BizOrderInstallPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "${adminPath}/bizorderinstallplan/orderinstallacceptquery")
@Controller
public class OrderInstallAcceptQueryController {

    @Autowired
    private BizOrderInstallPlanService  bizOrderInstallPlanService;
    @ModelAttribute
    public BizOrderInstallPlan get(@RequestParam(required = false) Integer id) {
        BizOrderInstallPlan entity = null;
        if (StringUtils.isNotBlank(id + "")) {
            entity = bizOrderInstallPlanService.get(id);
        }
        if (entity == null) {
            entity = new BizOrderInstallPlan();
        }
        return entity;
    }

    @RequestMapping(value="list")
    public String list(BizOrderInstallPlan bizOrderInstallPlan, HttpServletResponse response, HttpServletRequest request, Model model){
        Page<BizOrderInstallPlan> page = bizOrderInstallPlanService.findUnqualifiedLogPage(new Page<BizOrderInstallPlan>(request,response),bizOrderInstallPlan,model);
        model.addAttribute("page",page);
        return "/modules/enginInstallNew/orderinstallacceptquery/OrderInstallAcceptQueryList";
    }


    @RequestMapping(value = "photo")
    @ResponseBody
    public List<String> phone(String acceptType,String id){
        List<String> list = new ArrayList<>();
        if(BusinessUrgeConstantUtil.BUSINESS_URGE_ACCEPT_TYPE_1.equals(acceptType)){
            list = bizOrderInstallPlanService.findPhone(id);
        }else if(BusinessUrgeConstantUtil.BUSINESS_URGE_ACCEPT_TYPE_2.equals(acceptType)){
            list = bizOrderInstallPlanService.findUnPhone(id, PictureTypeContantUtil.PICTURE_TYPE_2076);
        }
        return list;
    }

    @RequestMapping(value = "findOrderInstallAccept")
    public String findOrderInstallAccept(BizOrderInstallPlan bizOrderInstallPlan, HttpServletResponse response, HttpServletRequest request, Model model){
        Page<BizOrderInstallPlan> page = bizOrderInstallPlanService.findOrderInstallAccept(new Page<BizOrderInstallPlan>(request,response),bizOrderInstallPlan,model);
        model.addAttribute("page",page);
        return "/modules/enginInstallNew/orderinstallacceptquery/OrderInstallUnAcceptQueryList";
    }

    @RequestMapping(value = "detail")
    public String detail(BizOrderInstallPlan bizOrderInstallPlan, HttpServletResponse response, HttpServletRequest request, Model model){


        BizOrderInstallPlan orderDetail = bizOrderInstallPlanService.getOrderDetail(bizOrderInstallPlan);


        List<BizOrderInstallPlan> listPlanLog = bizOrderInstallPlanService.findItemPlanLog(bizOrderInstallPlan);
        model.addAttribute("orderDetail",orderDetail);
        model.addAttribute("listPlanLog",listPlanLog);
        return "/modules/enginInstallNew/orderinstallacceptquery/OrderInstallAcceptDetail";
    }

    @RequestMapping(value="unqualifiedInstallItemCount")
    public String UnqualifiedInstallItemCount(BizOrderInstallPlan bizOrderInstallPlan, HttpServletResponse response, HttpServletRequest request, Model model){
        Page<BizOrderInstallPlan> page = bizOrderInstallPlanService.findUnqualifiedInstallItemCountPage(new Page<BizOrderInstallPlan>(request,response),bizOrderInstallPlan,model);
        model.addAttribute("page",page);
        return "/modules/enginInstallNew/orderinstallacceptquery/UnqualifiedInstallItemCount";
    }

    @RequestMapping(value="unqualifiedResonCount")
    public String unqualifiedResonCount(BizOrderInstallPlan bizOrderInstallPlan, HttpServletResponse response, HttpServletRequest request, Model model){
        Page<BizOrderInstallPlan> page = bizOrderInstallPlanService.findUnqualifiedResonCount(new Page<BizOrderInstallPlan>(request,response),bizOrderInstallPlan,model);
        model.addAttribute("page",page);
        return "/modules/enginInstallNew/orderinstallacceptquery/UnqualifiedResonCount";
    }


    @RequestMapping(value="exportList")
    public ModelAndView exportList(BizOrderInstallPlan bizOrderInstallPlan, HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
        return  bizOrderInstallPlanService.exportList(bizOrderInstallPlan,request);
    }
    
    

    @RequestMapping(value="exportOrderInstallAccept")
    public ModelAndView exportOrderInstallAccept(BizOrderInstallPlan bizOrderInstallPlan, HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
        return  bizOrderInstallPlanService.exportOrderInstallAccept(bizOrderInstallPlan,request);
    }
    
}
