package cn.damei.web.modules;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizProjectInstallItemProblemType;
import cn.damei.service.modules.BizProjectInstallItemProblemTypeService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizProjectInstallItemProblemType/bizProjectInstallItemProblemType")
public class BizProjectInstallItemProblemTypeController extends BaseController {

	@Autowired
	private BizProjectInstallItemProblemTypeService bizProjectInstallItemProblemTypeService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizProjectInstallItemProblemType get(@RequestParam(required=false) Integer id) {
		BizProjectInstallItemProblemType entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizProjectInstallItemProblemTypeService.get(id);
		}
		if (entity == null){
			entity = new BizProjectInstallItemProblemType();
		}
		return entity;
	}
	
	@RequiresPermissions("bizProjectInstallItemProblemType:bizProjectInstallItemProblemType:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizProjectInstallItemProblemType bizProjectInstallItemProblemType, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizProjectInstallItemProblemType.getStoreId()){
			if(null!=user.getStoreId()){
				bizProjectInstallItemProblemType.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizProjectInstallItemProblemType.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizProjectInstallItemProblemType.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizProjectInstallItemProblemType.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/bizprojectinstallitemproblemtype/bizProjectInstallItemProblemTypeList";
	}
	
	
	@RequiresPermissions("bizProjectInstallItemProblemType:bizProjectInstallItemProblemType:view")
	@RequestMapping(value = {"list2", ""})
	public String list2(BizProjectInstallItemProblemType bizProjectInstallItemProblemType, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizProjectInstallItemProblemType.getStoreId()){
			if(null!=user.getStoreId()){
				bizProjectInstallItemProblemType.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizProjectInstallItemProblemType.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizProjectInstallItemProblemType.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizProjectInstallItemProblemType.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizProjectInstallItemProblemType> page = bizProjectInstallItemProblemTypeService.findPage(new Page<BizProjectInstallItemProblemType>(request, response), bizProjectInstallItemProblemType); 
		model.addAttribute("page", page);
		return "modules/bizprojectinstallitemproblemtype/bizProjectInstallItemProblemTypeList";
	}

	@RequiresPermissions("bizProjectInstallItemProblemType:bizProjectInstallItemProblemType:view")
	@RequestMapping(value = "form")
	public String form(BizProjectInstallItemProblemType bizProjectInstallItemProblemType, Model model) {
		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
		model.addAttribute("BizProjectInstallItemProblemType", bizProjectInstallItemProblemType);
		return "modules/bizprojectinstallitemproblemtype/bizProjectInstallItemProblemTypeForm";
	}

	@RequiresPermissions("bizProjectInstallItemProblemType:bizProjectInstallItemProblemType:edit")
	@RequestMapping(value = "save")
	public String save(BizProjectInstallItemProblemType bizProjectInstallItemProblemType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizProjectInstallItemProblemType)){
			return form(bizProjectInstallItemProblemType, model);
		}
		bizProjectInstallItemProblemTypeService.save(bizProjectInstallItemProblemType);
		addMessage(redirectAttributes, "保存工程安装问题分类成功");
		return "redirect:"+Global.getAdminPath()+"/bizProjectInstallItemProblemType/bizProjectInstallItemProblemType/list2?repage";
	}
	
	@RequiresPermissions("bizProjectInstallItemProblemType:bizProjectInstallItemProblemType:edit")
	@RequestMapping(value = "delete")
	public String delete(BizProjectInstallItemProblemType bizProjectInstallItemProblemType, RedirectAttributes redirectAttributes) {
		
		if (bizProjectInstallItemProblemType.getIsEnabled().equals("1")) {
			bizProjectInstallItemProblemType.setIsEnabled("0");
		} else {
			bizProjectInstallItemProblemType.setIsEnabled("1");
		}
		bizProjectInstallItemProblemTypeService.delete(bizProjectInstallItemProblemType);
		addMessage(redirectAttributes, "修改工程安装问题分类状态成功");
		return "redirect:"+Global.getAdminPath()+"/bizProjectInstallItemProblemType/bizProjectInstallItemProblemType/list2?repage";
	}

}