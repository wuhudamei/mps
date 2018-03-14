
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
import cn.damei.entity.modules.IllegalModality;
import cn.damei.service.modules.IllegalModalityService;


@Controller
@RequestMapping(value = "${adminPath}/illegalmodality/illegalModality")
public class IllegalModalityController extends BaseController {

	@Autowired
	private IllegalModalityService illegalModalityService;
	
	@ModelAttribute
	public IllegalModality get(@RequestParam(required=false) String id) {
		IllegalModality entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = illegalModalityService.get(Integer.parseInt(id));
		}
		if (entity == null){
			entity = new IllegalModality();
		}
		return entity;
	}
	
	@RequiresPermissions("illegalmodality:illegalModality:view")
	@RequestMapping(value = {"list", ""})
	public String list(IllegalModality illegalModality, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(null==illegalModality.getCheckItemId()){
			
			illegalModality.setCheckItemId(((Integer)request.getSession().getAttribute("checkItemId")));
		}
			
	
		Page<IllegalModality> page = illegalModalityService.findPage(new Page<IllegalModality>(request, response), illegalModality); 

		if(null!=illegalModality.getProjectMode()){
			
			model.addAttribute("projectMode", illegalModality.getProjectMode());
		}else{
			
			model.addAttribute("projectMode", page.getList().get(0).getProjectMode());
			
		}
		
		model.addAttribute("checkItemId", illegalModality.getCheckItemId());
	
		
		request.getSession().setAttribute("checkItemId", illegalModality.getCheckItemId());
		
		model.addAttribute("page", page);
		return "modules/illegalmodality/illegalModalityList";
	}

	@RequiresPermissions("illegalmodality:illegalModality:view")
	@RequestMapping(value = "form")
	public String form(IllegalModality illegalModality, Model model) {


		
			IllegalModality modality = illegalModalityService.getStoreKindItemInfoByIllegalModalityId(illegalModality.getCheckItemId());
			model.addAttribute("modality", modality);
			model.addAttribute("projectMode", modality.getProjectMode());
		return "modules/illegalmodality/illegalModalityForm";
	}
	
	
	@RequiresPermissions("illegalmodality:illegalModality:view")
	@RequestMapping(value = "form1")
	public String form1(IllegalModality illegalModality, Model model) {

			
			model.addAttribute("modality", illegalModality);
		return "modules/illegalmodality/illegalModalityFormEdit";
	}
	

	@RequiresPermissions("illegalmodality:illegalModality:edit")
	@RequestMapping(value = "save")
	public String save(IllegalModality illegalModality, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, illegalModality)){
			return form(illegalModality, model);
		}
		
		
		
		illegalModalityService.save(illegalModality);
		addMessage(redirectAttributes, "保存违规形式成功");
		return "redirect:"+Global.getAdminPath()+"/illegalmodality/illegalModality/?repage";
	}
	
	@RequiresPermissions("illegalmodality:illegalModality:edit")
	@RequestMapping(value = "delete")
	public String delete(IllegalModality illegalModality, RedirectAttributes redirectAttributes) {
		illegalModalityService.delete(illegalModality);
		
		if(illegalModality.getStatus().equals("0")){
			addMessage(redirectAttributes, "停用违规形式成功");
		}else{
			addMessage(redirectAttributes, "启用违规形式成功");
		}
		
		return "redirect:"+Global.getAdminPath()+"/illegalmodality/illegalModality/?repage";
	}

}