
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
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.entity.modules.BizMaterialsChoiceCategory;
import cn.damei.service.modules.BizMaterialsChoiceBillItemService;


@Controller
@RequestMapping(value = "${adminPath}/bizmaterialschoicebillitem/bizMaterialsChoiceBillItem")
public class BizMaterialsChoiceBillItemController extends BaseController {

	@Autowired
	private BizMaterialsChoiceBillItemService bizMaterialsChoiceBillItemService;
	
	@ModelAttribute
	public BizMaterialsChoiceBillItem get(@RequestParam(required=false) Integer id) {
		BizMaterialsChoiceBillItem entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizMaterialsChoiceBillItemService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialsChoiceBillItem();
		}
		return entity;
	}
	
	@RequiresPermissions("bizmaterialschoicebillitem:bizMaterialsChoiceBillItem:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/bizmaterialschoicebillitem/bizMaterialsChoiceBillItemList";
	}
	
	@RequiresPermissions("bizmaterialschoicebillitem:bizMaterialsChoiceBillItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMaterialsChoiceBillItem> page = bizMaterialsChoiceBillItemService.findMaterialsPage(new Page<BizMaterialsChoiceBillItem>(request, response), bizMaterialsChoiceBillItem); 
		model.addAttribute("page", page);
		return "modules/bizmaterialschoicebillitem/bizMaterialsChoiceBillItemList";
	}



	@RequestMapping(value = "findFirstMaterialsChoiceCategory")
	public @ResponseBody List<BizMaterialsChoiceCategory> findFirstMaterialsChoiceCategory(String categoryLevel) {
		

		BizMaterialsChoiceCategory bizMaterialsChoiceCategory = new BizMaterialsChoiceCategory();
		if(StringUtils.isNotBlank(categoryLevel)){
			bizMaterialsChoiceCategory.setCategoryLevel(Integer.valueOf(categoryLevel));
		}
		List<BizMaterialsChoiceCategory> list = bizMaterialsChoiceBillItemService.findFirstMaterialsChoiceCategory(bizMaterialsChoiceCategory);
		
		return list;

	}
	

	@RequestMapping(value = "findSecondMaterialsChoiceCategory")
	public @ResponseBody List<BizMaterialsChoiceCategory> findSecondMaterialsChoiceCategory(String categoryLevel,String firstCode) {
		

		BizMaterialsChoiceCategory bizMaterialsChoiceCategory = new BizMaterialsChoiceCategory();
		if(StringUtils.isNotBlank(categoryLevel)){
			bizMaterialsChoiceCategory.setCategoryLevel(Integer.valueOf(categoryLevel));
		}
		if(StringUtils.isNotBlank(firstCode)){
			bizMaterialsChoiceCategory.setCategoryCode(firstCode);
		}
		List<BizMaterialsChoiceCategory> list = bizMaterialsChoiceBillItemService.findSecondMaterialsChoiceCategory(bizMaterialsChoiceCategory);
		
		return list;
		
	}
	

}