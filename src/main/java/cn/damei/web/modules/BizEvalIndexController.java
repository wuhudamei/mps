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
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizEvalIndex;
import cn.damei.service.modules.BizEvalIndexService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/evaluate/bizevalindex/bizEvalIndex")
public class BizEvalIndexController extends BaseController {

	@Autowired
	private BizEvalIndexService bizEvalIndexService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@ModelAttribute
	public BizEvalIndex get(@RequestParam(required=false) Integer id) {
		BizEvalIndex entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizEvalIndexService.get(id);
		}
		if (entity == null){
			entity = new BizEvalIndex();
		}
		return entity;
	}
	
	@RequiresPermissions("bizevalindex:bizEvalIndex:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizEvalIndex bizEvalIndex, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if(null==bizEvalIndex.getStoreId()){
			if(null!=user.getStoreId()){
				bizEvalIndex.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizEvalIndex.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizEvalIndex.setProjectMode(be.getProjectMode());
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
						bizEvalIndex.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/evaluate/bizevalindex/bizEvalIndexList";
	}
	
	@RequiresPermissions("bizevalindex:bizEvalIndex:view")
	@RequestMapping(value = {"list1", ""})
	public String list1(BizEvalIndex bizEvalIndex, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		User user = UserUtils.getUser();

		if(null==bizEvalIndex.getStoreId()){
			if(null!=user.getStoreId()){
				bizEvalIndex.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}

		if(StringUtils.isBlank(bizEvalIndex.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizEvalIndex.setProjectMode(be.getProjectMode());
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
						bizEvalIndex.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizEvalIndex> page = bizEvalIndexService.findPage(new Page<BizEvalIndex>(request, response), bizEvalIndex); 
		model.addAttribute("page", page);
		return "modules/evaluate/bizevalindex/bizEvalIndexList";
	}

	@RequiresPermissions("bizevalindex:bizEvalIndex:view")
	@RequestMapping(value = "form")
	public String form(BizEvalIndex bizEvalIndex, Model model) {
		
		model.addAttribute("readOnly", UserUtils.getUser().getProjectMode());
		model.addAttribute("bizEvalIndex", bizEvalIndex);
		return "modules/evaluate/bizevalindex/bizEvalIndexForm";
	}

	@RequiresPermissions("bizevalindex:bizEvalIndex:edit")
	@RequestMapping(value = "save")
	public String save(BizEvalIndex bizEvalIndex, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizEvalIndex)){
			return form(bizEvalIndex, model);
		}
		bizEvalIndexService.save(bizEvalIndex);
		addMessage(redirectAttributes, "保存评价指标设置成功");
		return "redirect:"+Global.getAdminPath()+"/evaluate/bizevalindex/bizEvalIndex/list1?repage";
	}
	
	@RequiresPermissions("bizevalindex:bizEvalIndex:edit")
	@RequestMapping(value = "delete")
	public String delete(BizEvalIndex bizEvalIndex, RedirectAttributes redirectAttributes) {
		
		if (bizEvalIndex.getIsEnabled().equals("1")) {
			bizEvalIndex.setIsEnabled("0");
		} else {
			bizEvalIndex.setIsEnabled("1");
		}
			
		bizEvalIndexService.delete(bizEvalIndex);
		addMessage(redirectAttributes, "修改评价指标设置状态成功");
		return "redirect:"+Global.getAdminPath()+"/evaluate/bizevalindex/bizEvalIndex/list1?repage";
	}
	

}