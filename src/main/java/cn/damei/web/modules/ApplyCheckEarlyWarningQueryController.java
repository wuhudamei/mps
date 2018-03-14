package cn.damei.web.modules;


import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.ApplyCheckEarlyWarningQueryDetailEntity;
import cn.damei.entity.modules.ApplyCheckEarlyWarningQueryEntity;
import cn.damei.service.modules.ApplyCheckEarlyWarningQueryDetailService;
import cn.damei.service.modules.ApplyCheckEarlyWarningQueryService;
import cn.damei.common.utils.UserUtils;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Controller
@RequestMapping(value="${adminPath}/ApplyCheckEarlyEarningQuery")
public class ApplyCheckEarlyWarningQueryController extends BaseController{

    @Autowired
    private ApplyCheckEarlyWarningQueryService service;
    @Autowired
    private ApplyCheckEarlyWarningQueryDetailService detailService;
    @ModelAttribute
    public ApplyCheckEarlyWarningQueryEntity get() {
        ApplyCheckEarlyWarningQueryEntity entity = new ApplyCheckEarlyWarningQueryEntity();

        return entity;
    }

    @RequiresPermissions("ApplyCheckEarlyEarningQuery:ApplyCheckEarlyEarningQuery:view")
    @RequestMapping(value = {"prelist"})
    public String preList(ApplyCheckEarlyWarningQueryEntity entity,Model model) {
    	model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
        return "modules/bizenginedepartsyntheticquery/ApplyCheckEarlyWarning/ApplyCheckEarlyWarningList";
    }

    @RequiresPermissions("ApplyCheckEarlyEarningQuery:ApplyCheckEarlyEarningQuery:view")
    @RequestMapping(value = {"list"})
    public String list(ApplyCheckEarlyWarningQueryEntity entity, Model model, HttpServletResponse response,
                       HttpServletRequest request) {
        Page<ApplyCheckEarlyWarningQueryEntity> page   = service.findPage(new Page<ApplyCheckEarlyWarningQueryEntity>(request,response),entity);
        model.addAttribute("page",page);


        return "modules/bizenginedepartsyntheticquery/ApplyCheckEarlyWarning/ApplyCheckEarlyWarningList";
    }

    @RequiresPermissions("ApplyCheckEarlyEarningQuery:ApplyCheckEarlyEarningQuery:view")
    @RequestMapping(value = {"delayOrderInfo"})
    public String delayOrderInfo(ApplyCheckEarlyWarningQueryDetailEntity entity, Model model, HttpServletResponse response,
                       HttpServletRequest request) {


        Page<ApplyCheckEarlyWarningQueryDetailEntity> page  = detailService.findPage(new Page<ApplyCheckEarlyWarningQueryDetailEntity>(request,response),entity);
        model.addAttribute("page",page);
        model.addAttribute("applyCheckEarlyWarningQueryEntity",entity);


        return "modules/bizenginedepartsyntheticquery/ApplyCheckEarlyWarning/DelayOrderQcNode";
    }

}
