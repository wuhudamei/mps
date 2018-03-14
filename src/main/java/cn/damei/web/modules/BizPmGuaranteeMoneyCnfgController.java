
package cn.damei.web.modules;

import java.util.List;

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
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfg;
import cn.damei.service.modules.BizPmGuaranteeMoneyCnfgService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfg")
public class BizPmGuaranteeMoneyCnfgController extends BaseController {

	@Autowired
	private BizPmGuaranteeMoneyCnfgService bizPmGuaranteeMoneyCnfgService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizPmGuaranteeMoneyCnfg get(@RequestParam(required=false) Integer id) {
		BizPmGuaranteeMoneyCnfg entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizPmGuaranteeMoneyCnfgService.get(id);
		}
		if (entity == null){
			entity = new BizPmGuaranteeMoneyCnfg();
		}
		return entity;
	}
	
	@RequiresPermissions("bizpmguaranteemoneycnfg:bizPmGuaranteeMoneyCnfg:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizPmGuaranteeMoneyCnfg bizPmGuaranteeMoneyCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {

		User user = UserUtils.getUser();

		if(null==bizPmGuaranteeMoneyCnfg.getStoreId()){
			if(null!=user.getStoreId()){
				bizPmGuaranteeMoneyCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizPmGuaranteeMoneyCnfg.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizPmGuaranteeMoneyCnfg.setProjectMode(be.getProjectMode());
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
						bizPmGuaranteeMoneyCnfg.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		return "modules/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfgList";
	}
	@RequiresPermissions("bizpmguaranteemoneycnfg:bizPmGuaranteeMoneyCnfg:view")
	@RequestMapping(value = {"list2", ""})
	public String list2(BizPmGuaranteeMoneyCnfg bizPmGuaranteeMoneyCnfg, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizPmGuaranteeMoneyCnfg.getStoreId()){
			if(null!=user.getStoreId()){
				bizPmGuaranteeMoneyCnfg.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizPmGuaranteeMoneyCnfg.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizPmGuaranteeMoneyCnfg.setProjectMode(be.getProjectMode());
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
						bizPmGuaranteeMoneyCnfg.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizPmGuaranteeMoneyCnfg> page = bizPmGuaranteeMoneyCnfgService.findPage(new Page<BizPmGuaranteeMoneyCnfg>(request, response), bizPmGuaranteeMoneyCnfg); 
		model.addAttribute("page", page);
		return "modules/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfgList";
	}

	@RequiresPermissions("bizpmguaranteemoneycnfg:bizPmGuaranteeMoneyCnfg:view")
	@RequestMapping(value = "form")
	public String form(BizPmGuaranteeMoneyCnfg bizPmGuaranteeMoneyCnfg, Model model) {
		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
		model.addAttribute("bizPmGuaranteeMoneyCnfg", bizPmGuaranteeMoneyCnfg);
		return "modules/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfgForm";
	}

	@RequiresPermissions("bizpmguaranteemoneycnfg:bizPmGuaranteeMoneyCnfg:edit")
	@RequestMapping(value = "save")
	public String save(BizPmGuaranteeMoneyCnfg bizPmGuaranteeMoneyCnfg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizPmGuaranteeMoneyCnfg)){
			return form(bizPmGuaranteeMoneyCnfg, model);
		}

		if(null!=bizPmGuaranteeMoneyCnfg.getId()){
			List<BizPmGuaranteeMoneyCnfg> list = bizPmGuaranteeMoneyCnfgService.findList(bizPmGuaranteeMoneyCnfg);
			if(null!=list && list.size()>0){
				for(BizPmGuaranteeMoneyCnfg last:list){
					if(last.getId().equals(bizPmGuaranteeMoneyCnfg.getId())){
						bizPmGuaranteeMoneyCnfgService.save(bizPmGuaranteeMoneyCnfg);
						addMessage(redirectAttributes, "保存项目经理质保金设置成功");
					}else{
						addMessage(redirectAttributes, "项目经理星级和提成已经存在，保存失败");
					}
				}
			}else{
				bizPmGuaranteeMoneyCnfgService.save(bizPmGuaranteeMoneyCnfg);
				addMessage(redirectAttributes, "保存项目经理质保金设置成功");
			}
		}else{

			List<BizPmGuaranteeMoneyCnfg> list = bizPmGuaranteeMoneyCnfgService.findList(bizPmGuaranteeMoneyCnfg);
			if(null!=list && list.size()>0){
				addMessage(redirectAttributes, "项目经理质保金设置已经存在,保存失败");
			}else{
				bizPmGuaranteeMoneyCnfgService.save(bizPmGuaranteeMoneyCnfg);
				addMessage(redirectAttributes, "保存项目经理质保金设置成功");
			}
		}
				
		return "redirect:"+Global.getAdminPath()+"/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfg/list2?repage";
	}
	
	@RequiresPermissions("bizpmguaranteemoneycnfg:bizPmGuaranteeMoneyCnfg:edit")
	@RequestMapping(value = "delete")
	public String delete(BizPmGuaranteeMoneyCnfg bizPmGuaranteeMoneyCnfg, RedirectAttributes redirectAttributes) {
		bizPmGuaranteeMoneyCnfgService.delete(bizPmGuaranteeMoneyCnfg);
		addMessage(redirectAttributes, "删除项目经理质保金设置成功");
		return "redirect:"+Global.getAdminPath()+"/managerSettlement/bizpmguaranteemoneycnfg/bizPmGuaranteeMoneyCnfg/list2?repage";
	}

}