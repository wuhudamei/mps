
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizComplaintProblemType;
import cn.damei.service.modules.BizComplaintProblemTypeService;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "${adminPath}/bizcomplaintproblemtype/bizComplaintProblemType")
public class BizComplaintProblemTypeController extends BaseController {

	@Autowired
	private BizComplaintProblemTypeService bizComplaintProblemTypeService;
	
	@ModelAttribute
	public BizComplaintProblemType get(@RequestParam(required=false) String id) {
		BizComplaintProblemType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizComplaintProblemTypeService.get(id);
		}
		if (entity == null){
			entity = new BizComplaintProblemType();
		}
		return entity;
	}
	
	@RequiresPermissions("bizcomplaintproblemtype:bizComplaintProblemType:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizComplaintProblemType bizComplaintProblemType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizComplaintProblemType> page = bizComplaintProblemTypeService.findPage(new Page<BizComplaintProblemType>(request, response), bizComplaintProblemType); 
		model.addAttribute("page", page);
		return "modules/bizcomplaintproblemtype/bizComplaintProblemTypeList";
	}

	@RequiresPermissions("bizcomplaintproblemtype:bizComplaintProblemType:view")
	@RequestMapping(value = "form")
	public String form(BizComplaintProblemType bizComplaintProblemType, Model model) {
		model.addAttribute("bizComplaintProblemType", bizComplaintProblemType);
		return "modules/bizcomplaintproblemtype/bizComplaintProblemTypeForm";
	}

	@RequiresPermissions("bizcomplaintproblemtype:bizComplaintProblemType:edit")
	@RequestMapping(value = "save")
	public String save(BizComplaintProblemType bizComplaintProblemType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizComplaintProblemType)){
			return form(bizComplaintProblemType, model);
		}
		bizComplaintProblemTypeService.save(bizComplaintProblemType);
		addMessage(redirectAttributes, "保存问题分类成功");
		return "redirect:"+Global.getAdminPath()+"/bizcomplaintproblemtype/bizComplaintProblemType/?repage";
	}
	
	@RequiresPermissions("bizcomplaintproblemtype:bizComplaintProblemType:edit")
	@RequestMapping(value = "delete")
	public String delete(BizComplaintProblemType bizComplaintProblemType, RedirectAttributes redirectAttributes) {
		bizComplaintProblemTypeService.delete(bizComplaintProblemType);
		addMessage(redirectAttributes, "设置成功");
		return "redirect:"+Global.getAdminPath()+"/bizcomplaintproblemtype/bizComplaintProblemType/?repage";
	}
	@RequiresPermissions("bizcomplaintproblemtype:bizComplaintProblemType:view")
	@RequestMapping(value = "findPackByStoreId")
	@ResponseBody
	public List<Map<String,String>> findPackByStoreId(String storeId,String projectMode) {

	List<Map<String,String>> list=	bizComplaintProblemTypeService.findPackByStoreId(storeId,projectMode);

		return list;
	}

}