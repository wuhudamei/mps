
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
import cn.damei.entity.modules.BizEvalRewardStar;
import cn.damei.service.modules.BizEvalRewardStarService;


@Controller
@RequestMapping(value = "${adminPath}/bizevalrewardstar/bizEvalRewardStar")
public class BizEvalRewardStarController extends BaseController {

	@Autowired
	private BizEvalRewardStarService bizEvalRewardStarService;
	
	@ModelAttribute
	public BizEvalRewardStar get(@RequestParam(required=false) Integer id) {
		BizEvalRewardStar entity = null;
		if (id != null){
			entity = bizEvalRewardStarService.get(id);
		}
		if (entity == null){
			entity = new BizEvalRewardStar();
		}
		return entity;
	}
	
	@RequiresPermissions("bizevalrewardstar:bizEvalRewardStar:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizEvalRewardStar bizEvalRewardStar, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizEvalRewardStar> page = bizEvalRewardStarService.findPage(new Page<BizEvalRewardStar>(request, response), bizEvalRewardStar); 
		model.addAttribute("page", page);
		return "modules/bizevalrewardstar/bizEvalRewardStarList";
	}

	@RequiresPermissions("bizevalrewardstar:bizEvalRewardStar:view")
	@RequestMapping(value = "form")
	public String form(BizEvalRewardStar bizEvalRewardStar, Model model) {
		model.addAttribute("bizEvalRewardStar", bizEvalRewardStar);
		return "modules/bizevalrewardstar/bizEvalRewardStarForm";
	}

	@RequiresPermissions("bizevalrewardstar:bizEvalRewardStar:edit")
	@RequestMapping(value = "save")
	public String save(BizEvalRewardStar bizEvalRewardStar, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizEvalRewardStar)){
			return form(bizEvalRewardStar, model);
		}
		bizEvalRewardStarService.save(bizEvalRewardStar);
		addMessage(redirectAttributes, "保存评价奖励星级成功");
		return "redirect:"+Global.getAdminPath()+"/bizevalrewardstar/bizEvalRewardStar/?repage";
	}
	
	@RequiresPermissions("bizevalrewardstar:bizEvalRewardStar:edit")
	@RequestMapping(value = "delete")
	public String delete(BizEvalRewardStar bizEvalRewardStar, RedirectAttributes redirectAttributes) {
		bizEvalRewardStarService.delete(bizEvalRewardStar);
		addMessage(redirectAttributes, "删除评价奖励星级成功");
		return "redirect:"+Global.getAdminPath()+"/bizevalrewardstar/bizEvalRewardStar/?repage";
	}

}