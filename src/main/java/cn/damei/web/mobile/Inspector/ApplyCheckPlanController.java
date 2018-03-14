package cn.damei.web.mobile.Inspector;

import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Inspector.ApplyCheckPlanEntity;
import cn.damei.service.mobile.Inspector.ApplyCheckPlanService;
import cn.damei.entity.mobile.Inspector.Inspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joseph on 2017/6/13.
 */
@Controller
@RequestMapping(value="${adminPath}/app/pqc/apply-check-plan")
public class ApplyCheckPlanController {




    @Autowired
    private ApplyCheckPlanService service;


    @RequestMapping(value="/preList.php")
    public String findList(){



        return "mobile/modules/pqc/applyCheckBase/applyCheckPlan/applyCheckPlanList";
    }

    @RequestMapping(value="/list.php")
    @ResponseBody
    public List<ApplyCheckPlanEntity> findList(String text, HttpServletRequest request){
        Inspector inspector = (Inspector)SessionUtils.getInspectorSession(request);



        Map<String,Object> map = new HashMap();
        map.put("text",text);
        map.put("pqcId",inspector.getId());
        List<ApplyCheckPlanEntity> list =service.findList(map);
        return  list;
    }

    @RequestMapping(value="/{orderId}")
    public String applyCheckPlanDetail(@PathVariable("orderId")  String orderId, Model model){

        List<ApplyCheckPlanEntity> list = service.findApplyCheckPlanDetails(orderId);

        model.addAttribute("orderInfo",list.size()>0?list.get(0).getCustomerAddressInfo():"无");
model.addAttribute("list",list);
        return "mobile/modules/pqc/applyCheckBase/applyCheckPlan/applyCheckPlanDetails";
    }


}
