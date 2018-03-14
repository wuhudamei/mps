
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.service.modules.BizEmployeeService2;
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
import cn.damei.entity.modules.BizTodoItemType;
import cn.damei.service.modules.BizTodoItemTypeService;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "${adminPath}/biztodotype/bizTodoItemType")
public class BizTodoItemTypeController extends BaseController {

	@Autowired
	private BizTodoItemTypeService bizTodoItemTypeService;
	
	@ModelAttribute
	public BizTodoItemType get(@RequestParam(required=false) String id) {
		BizTodoItemType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizTodoItemTypeService.get(id);
		}
		if (entity == null){
			entity = new BizTodoItemType();
		}
		return entity;
	}

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	
	@RequiresPermissions("biztodotype:bizTodoItemType:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizTodoItemType bizTodoItemType, HttpServletRequest request, HttpServletResponse response, Model model) {

		Page<BizTodoItemType> page = bizTodoItemTypeService.findPage(new Page<BizTodoItemType>(request, response), bizTodoItemType); 
		model.addAttribute("page", page);
		return "modules/biztodotype/bizTodoItemTypeList";
	}

	@RequiresPermissions("biztodotype:bizTodoItemType:view")
	@RequestMapping(value = "form")
	public String form(BizTodoItemType bizTodoItemType, Model model) {

		model.addAttribute("bizTodoItemType", bizTodoItemType);
		return "modules/biztodotype/bizTodoItemTypeForm";
	}

	@RequiresPermissions("biztodotype:bizTodoItemType:edit")
	@RequestMapping(value = "save")
	public String save(BizTodoItemType bizTodoItemType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizTodoItemType)){
			return form(bizTodoItemType, model);
		}
		bizTodoItemTypeService.save(bizTodoItemType);
		addMessage(redirectAttributes, "保存待办成功");
		return "redirect:"+Global.getAdminPath()+"/biztodotype/bizTodoItemType/?repage";
	}
	
	@RequiresPermissions("biztodotype:bizTodoItemType:edit")
	@RequestMapping(value = "delete")
	public String delete(BizTodoItemType bizTodoItemType, RedirectAttributes redirectAttributes) {
		bizTodoItemTypeService.delete(bizTodoItemType);
		addMessage(redirectAttributes, "删除待办成功");
		return "redirect:"+Global.getAdminPath()+"/biztodotype/bizTodoItemType/?repage";
	}



	@RequiresPermissions("biztodotype:bizTodoItemType:view")
	@RequestMapping(value = "findIdByBusinessType")
	@ResponseBody
	public List<Map<String,String>> findIdByBusinessType(String relatedBusinessType, RedirectAttributes redirectAttributes) {


		List<Map<String,String>> relatedBusinessId = bizTodoItemTypeService.findIdByBusinessType(relatedBusinessType);

		return relatedBusinessId;

	}
@RequiresPermissions("biztodotype:bizTodoItemType:view")
	@RequestMapping(value = "findRelatedBusinessTypeByStoreIdProjectMode")
	@ResponseBody
	public List<Map<String,String>> findRelatedBusinessTypeByStoreIdProjectMode(String storeId,String projectMode, RedirectAttributes redirectAttributes) {


		if(null!=storeId &&null!=projectMode){
			List<Map<String,String>> mapList = bizTodoItemTypeService.findRelatedBusinessTypeByStoreIdProjectMode(storeId,projectMode);
			return mapList;
		}else{


			return null;
		}





	}

}