package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.BizNodePlan;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizOrderConstruction;
import cn.damei.entity.modules.BizOrderConstructionService;
import cn.damei.service.modules.BizNodePlanService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/bizconstruction/bizConstruction")
public class BizOrderConstructionController extends BaseController {



	@Autowired
	private BizOrderConstructionService bizOrderConstructionService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizNodePlanService bizNodePlanService;


	@RequiresPermissions("bizconstruction:bizConstruction:view")
	@RequestMapping(value = { "preList", "" })
	public String packageList(BizOrderConstruction bizOrderConstruction, Model model, HttpServletRequest request) {
		logger.info("当前登陆人ID："+UserUtils.getUser().getId());
		
		User user = UserUtils.getUser();

		if(null == bizOrderConstruction.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderConstruction.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizOrderConstruction.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderConstruction.setProjectMode(be.getProjectMode());
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
						bizOrderConstruction.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/bizconfirmstart/bizConstructionList";
	}


	@RequiresPermissions("bizconstruction:bizConstruction:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizOrderConstruction bizOrderConstruction, Model model, HttpServletResponse response,
			HttpServletRequest request) {
		User user = UserUtils.getUser();

		if(null == bizOrderConstruction.getStoreId()){
			if(null != user.getStoreId()){
				bizOrderConstruction.setStoreId(user.getStoreId());
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizOrderConstruction.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizOrderConstruction.setProjectMode(be.getProjectMode());
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
						bizOrderConstruction.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}

		Page<BizOrderConstruction> page = bizOrderConstructionService
				.findPage(new Page<BizOrderConstruction>(request, response), bizOrderConstruction);
		List<BizNodePlan> list = bizNodePlanService.getbyOrderIDList();
		
		model.addAttribute("page", page);
		model.addAttribute("list", list);
		return "modules/bizconfirmstart/bizConstructionList";
	}
}
