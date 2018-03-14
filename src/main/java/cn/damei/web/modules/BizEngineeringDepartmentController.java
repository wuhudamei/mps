
package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.mapper.JsonMapper;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizEngineeringDepartment;
import cn.damei.service.modules.BizEngineeringDepartmentService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "${adminPath}/engdept/bizEngineeringDepartment")
public class BizEngineeringDepartmentController extends BaseController {

    @Autowired
    private BizEngineeringDepartmentService bizEngineeringDepartmentService;

    @Autowired
    private BizEmployeeService2 bizEmployeeService2;

    @ModelAttribute
    public BizEngineeringDepartment get(@RequestParam(required = false) String id) {
        BizEngineeringDepartment entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = bizEngineeringDepartmentService.get(id);
        }
        if (entity == null) {
            entity = new BizEngineeringDepartment();
        }
        return entity;
    }

    @RequiresPermissions("engdept:bizEngineeringDepartment:view")
    @RequestMapping(value = { "list", "" })
    public String list(BizEngineeringDepartment bizEngineeringDepartment, HttpServletRequest request, HttpServletResponse response, Model model) {

        if (StringUtils.isBlank(bizEngineeringDepartment.getStoreId())) {
            bizEngineeringDepartment.setStoreId(UserUtils.getUser().getStoreId());
        }
        if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        }

        User user = UserUtils.getUser();
		if (bizEngineeringDepartment.getProjectMode() == null || "".equals(bizEngineeringDepartment.getProjectMode())) {
			
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (ConstantUtils.EMPLOYEE_PROJECT_MODE_3.equals(be.getProjectMode()) || null == be.getProjectMode()) {
					bizEngineeringDepartment.setProjectMode(null);
				} else {
					bizEngineeringDepartment.setProjectMode(be.getProjectMode());
					model.addAttribute("projectModeEnable", true);
				}
			} else {
				bizEngineeringDepartment.setProjectMode(null);
			}
		}
        Page<BizEngineeringDepartment> page = bizEngineeringDepartmentService.findPage(new Page<BizEngineeringDepartment>(request, response), bizEngineeringDepartment);
        model.addAttribute("page", page);
        return "modules/engdept/bizEngineeringDepartmentList";
    }

    @RequiresPermissions("engdept:bizEngineeringDepartment:view")
    @RequestMapping(value = "form")
    public String form(BizEngineeringDepartment bizEngineeringDepartment, Model model) {

        if (StringUtils.isBlank(bizEngineeringDepartment.getStoreId())) {
            bizEngineeringDepartment.setStoreId(UserUtils.getUser().getStoreId());
        }
        if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
            model.addAttribute("storeDropEnable", true);
        }

        User user = UserUtils.getUser();
        if(StringUtils.isNoneBlank(user.getEmpId())){
            BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
            if(ConstantUtils.PROJECT_MODE_1.equals(be.getProjectMode()) || ConstantUtils.PROJECT_MODE_2.equals(be.getProjectMode())){
                model.addAttribute("projectModeEnable", be.getProjectMode());
            }
        }
        model.addAttribute("bizEngineeringDepartment", bizEngineeringDepartment);
        model.addAttribute("userProjectMode", user.getProjectMode());
        return "modules/engdept/bizEngineeringDepartmentForm";
    }

    @RequiresPermissions("engdept:bizEngineeringDepartment:edit")
    @RequestMapping(value = "save")
    public String save(BizEngineeringDepartment bizEngineeringDepartment, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, bizEngineeringDepartment)) {
            return form(bizEngineeringDepartment, model);
        }
        if(StringUtils.isBlank(bizEngineeringDepartment.getId())){
        	bizEngineeringDepartment.preInsert();
        	bizEngineeringDepartmentService.insert(bizEngineeringDepartment);
        }else {
        	bizEngineeringDepartment.preUpdate();;
        	bizEngineeringDepartmentService.update(bizEngineeringDepartment);
        }

        addMessage(redirectAttributes, "保存工程部管理成功");
        return "redirect:" + Global.getAdminPath() + "/engdept/bizEngineeringDepartment/?repage";
    }

    @RequiresPermissions("engdept:bizEngineeringDepartment:edit")
    @RequestMapping(value = "delete")
    public String delete(BizEngineeringDepartment bizEngineeringDepartment, RedirectAttributes redirectAttributes) {
        bizEngineeringDepartmentService.delete(bizEngineeringDepartment);
        addMessage(redirectAttributes, "删除工程部管理成功");
        return "redirect:" + Global.getAdminPath() + "/engdept/bizEngineeringDepartment/?repage";
    }


    @ResponseBody
    @RequestMapping(value = "engListByStorId")
    public String manager_StoreForm(String storeid, String eid, Model model, HttpServletRequest request) {
        List<BizEngineeringDepartment> list = null;













        list = bizEngineeringDepartmentService.findByStoreId(storeid);
        return JsonMapper.getInstance().toJson(list);
    }
    
    
    @ResponseBody
    @RequestMapping(value = "departmentList")
    public String departmentList(String storeId,String projectMode,String employeeId,HttpServletRequest request){
    	Map<String,Object> map = new HashMap<String,Object>();
    	if(storeId != null && !"".equals(storeId)){
    		map.put("storeId", Integer.parseInt(storeId));
    	}else{
    		map.put("storeId", storeId);
    	}
    	if("3".equals(projectMode)){
    		map.put("projectMode", null);
    	}else{
    		map.put("projectMode", projectMode);
    	}

    	if(employeeId != null && !"".equals(employeeId)){
    		List<Integer> ids = bizEngineeringDepartmentService.findByEmployeeId(Integer.parseInt(employeeId));
    		if(ids != null && ids.size()>0){
    			map.put("ids", ids);
    		}else {
    			map.put("ids", null);
    		}
    	}else{
    		List<Integer> ids = bizEngineeringDepartmentService.findAll();
    		if(ids != null && ids.size()>0){
    			map.put("ids", ids);
    		}else {
    			map.put("ids", null);
    		}
    	}

    	List<DropModel> list = bizEngineeringDepartmentService.findEngDepListByMap(map);
    	
    	return JsonMapper.getInstance().toJson(list);
    }
}