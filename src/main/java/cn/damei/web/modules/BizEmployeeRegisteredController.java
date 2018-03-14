package cn.damei.web.modules;

import cn.damei.common.constantUtils.OrderConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployeeRegistered;
import cn.damei.service.modules.BizEmployeeRegisteredService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Controller
@RequestMapping(value = "${adminPath}/employee/bizEmployeeRegistered")
public class BizEmployeeRegisteredController extends BaseController {

    @Autowired
    private BizEmployeeRegisteredService bizEmployeeRegisteredService;


    @RequiresPermissions("employee:bizEmployeeRegistered:view")
    @RequestMapping(value = "list")
    public String listPage(BizEmployeeRegistered bizEmployeeRegistered, HttpServletRequest request, HttpServletResponse response, Model model) {


        bizEmployeeRegistered.setProjectMode(OrderConstantUtil.ORDER_PROJECT_MODE_INDUSTRY_1);


        Date date = new Date();
        bizEmployeeRegistered.setStartEntryDate(DateUtils.getAssignMonthAndDay(date, 0,0, 1));
        bizEmployeeRegistered.setEndEntryDate(date);

        return "modules/employee/bizEmployeeRegisteredList";
    }



    @RequestMapping(value="exportExcel")
    public void exportExcel(BizEmployeeRegistered bizEmployeeRegistered, HttpServletResponse response) throws Exception{
        bizEmployeeRegisteredService.exportExcel(bizEmployeeRegistered,response);
    }

}