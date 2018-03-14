
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
import cn.damei.entity.modules.BizEvalRewardTaskpackTemp;
import cn.damei.service.modules.BizEvalRewardTaskpackTempService;


@Controller
@RequestMapping(value = "${adminPath}/bizevalrewardtaskpacktemp/bizEvalRewardTaskpackTemp")
public class BizEvalRewardTaskpackTempController extends BaseController {

	@Autowired
	private BizEvalRewardTaskpackTempService bizEvalRewardTaskpackTempService;
	
	@ModelAttribute
	public BizEvalRewardTaskpackTemp get(@RequestParam(required=false) Integer id) {
		BizEvalRewardTaskpackTemp entity = null;
		if (id != null){
			entity = bizEvalRewardTaskpackTempService.get(id);
		}
		if (entity == null){
			entity = new BizEvalRewardTaskpackTemp();
		}
		return entity;
	}
	
	@RequiresPermissions("bizevalrewardtaskpacktemp:bizEvalRewardTaskpackTemp:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizEvalRewardTaskpackTemp bizEvalRewardTaskpackTemp, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizEvalRewardTaskpackTemp> page = bizEvalRewardTaskpackTempService.findPage(new Page<BizEvalRewardTaskpackTemp>(request, response), bizEvalRewardTaskpackTemp); 
		model.addAttribute("page", page);
		return "modules/bizevalrewardtaskpacktemp/bizEvalRewardTaskpackTempList";
	}

	@RequiresPermissions("bizevalrewardtaskpacktemp:bizEvalRewardTaskpackTemp:view")
	@RequestMapping(value = "form")
	public String form(BizEvalRewardTaskpackTemp bizEvalRewardTaskpackTemp, Model model) {
		model.addAttribute("bizEvalRewardTaskpackTemp", bizEvalRewardTaskpackTemp);
		return "modules/bizevalrewardtaskpacktemp/bizEvalRewardTaskpackTempForm";
	}

	@RequiresPermissions("bizevalrewardtaskpacktemp:bizEvalRewardTaskpackTemp:edit")
	@RequestMapping(value = "save")
	public String save(BizEvalRewardTaskpackTemp bizEvalRewardTaskpackTemp, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizEvalRewardTaskpackTemp)){
			return form(bizEvalRewardTaskpackTemp, model);
		}
		bizEvalRewardTaskpackTempService.save(bizEvalRewardTaskpackTemp);
		addMessage(redirectAttributes, "保存评价奖励任务包模板成功");
		return "redirect:"+Global.getAdminPath()+"/bizevalrewardtaskpacktemp/bizEvalRewardTaskpackTemp/?repage";
	}
	
	@RequiresPermissions("bizevalrewardtaskpacktemp:bizEvalRewardTaskpackTemp:edit")
	@RequestMapping(value = "delete")
	public String delete(BizEvalRewardTaskpackTemp bizEvalRewardTaskpackTemp, RedirectAttributes redirectAttributes) {
		bizEvalRewardTaskpackTempService.delete(bizEvalRewardTaskpackTemp);
		addMessage(redirectAttributes, "删除评价奖励任务包模板成功");
		return "redirect:"+Global.getAdminPath()+"/bizevalrewardtaskpacktemp/bizEvalRewardTaskpackTemp/?repage";
	}

}