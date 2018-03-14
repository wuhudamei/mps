
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
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizPmOwnpayCnfg;
import cn.damei.service.modules.BizPmOwnpayCnfgService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.common.utils.StringUtils;


@Controller
@RequestMapping(value = "${adminPath}/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfg")
public class BizPmOwnpayCnfgController extends BaseController {

	@Autowired
	private BizPmOwnpayCnfgService bizPmOwnpayCnfgService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizPmOwnpayCnfg get(@RequestParam(required=false) Integer id) {
		BizPmOwnpayCnfg entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizPmOwnpayCnfgService.get(id);
		}
		if (entity == null){
			entity = new BizPmOwnpayCnfg();
		}
		return entity;
	}
	
	@RequiresPermissions("bizpmownpaycnfg:bizPmOwnpayCnfg:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmOwnpayCnfg bizPmOwnpayCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizPmOwnpayCnfg.getStoreId()){
			if(null!=user.getStoreId()){
				bizPmOwnpayCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizPmOwnpayCnfg.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizPmOwnpayCnfg.setProjectMode(be.getProjectMode());
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
						bizPmOwnpayCnfg.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfgList";
	}
	@RequiresPermissions("bizpmownpaycnfg:bizPmOwnpayCnfg:view")
	@RequestMapping(value = {"list2", ""})
	public String list2(BizPmOwnpayCnfg bizPmOwnpayCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizPmOwnpayCnfg.getStoreId()){
			if(null!=user.getStoreId()){
				bizPmOwnpayCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizPmOwnpayCnfg.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizPmOwnpayCnfg.setProjectMode(be.getProjectMode());
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
						bizPmOwnpayCnfg.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizPmOwnpayCnfg> page = bizPmOwnpayCnfgService.findPage(new Page<BizPmOwnpayCnfg>(request, response), bizPmOwnpayCnfg); 
		model.addAttribute("page", page);
		return "modules/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfgList";
	}

	@RequiresPermissions("bizpmownpaycnfg:bizPmOwnpayCnfg:view")
	@RequestMapping(value = "form")
	public String form(BizPmOwnpayCnfg bizPmOwnpayCnfg, Model model) {
		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
		model.addAttribute("bizPmOwnpayCnfg", bizPmOwnpayCnfg);
		return "modules/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfgForm";
	}

	@RequiresPermissions("bizpmownpaycnfg:bizPmOwnpayCnfg:edit")
	@RequestMapping(value = "save")
	public String save(BizPmOwnpayCnfg bizPmOwnpayCnfg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmOwnpayCnfg)){
			return form(bizPmOwnpayCnfg, model);
		}
		bizPmOwnpayCnfgService.save(bizPmOwnpayCnfg);
		addMessage(redirectAttributes, "保存自主支配项定义成功");
		return "redirect:"+Global.getAdminPath()+"/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfg/list2?repage";
	}
	
	@RequiresPermissions("bizpmownpaycnfg:bizPmOwnpayCnfg:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmOwnpayCnfg bizPmOwnpayCnfg, RedirectAttributes redirectAttributes) {
		
		if (bizPmOwnpayCnfg.getIsEnabled().equals("1")) {
			bizPmOwnpayCnfg.setIsEnabled("0");
		} else {
			bizPmOwnpayCnfg.setIsEnabled("1");
		}
		
		bizPmOwnpayCnfgService.delete(bizPmOwnpayCnfg);
		
		if(bizPmOwnpayCnfg.getIsEnabled().equals("0")){
			addMessage(redirectAttributes, "停用成功");
		}else{
			addMessage(redirectAttributes, "启用成功");
		}
		
		return "redirect:"+Global.getAdminPath()+"/managerSettlement/bizpmownpaycnfg/bizPmOwnpayCnfg/list2?repage";
	}

}