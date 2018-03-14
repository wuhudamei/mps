
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

import cn.damei.common.constantUtils.ChangeBillConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizMaterialsChoiceChangeBill;
import cn.damei.service.modules.BizMaterialsChoiceChangeBillService;
import cn.damei.entity.modules.BizMaterialsChoiceChangeBillItem;
import cn.damei.service.modules.BizMaterialsChoiceChangeBillItemService;


@Controller
@RequestMapping(value = "${adminPath}/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBill")
public class BizMaterialsChoiceChangeBillController extends BaseController {

	@Autowired
	private BizMaterialsChoiceChangeBillService bizMaterialsChoiceChangeBillService;
	@Autowired
	private BizMaterialsChoiceChangeBillItemService bizMaterialsChoiceChangeBillItemService;
	
	@ModelAttribute
	public BizMaterialsChoiceChangeBill get(@RequestParam(required=false) Integer id) {
		BizMaterialsChoiceChangeBill entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizMaterialsChoiceChangeBillService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialsChoiceChangeBill();
		}
		return entity;
	}
	
	@RequiresPermissions("bizmaterialschoicechangebill:bizMaterialsChoiceChangeBill:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BizMaterialsChoiceChangeBill bizMaterialsChoiceChangeBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBillList";
	}
	
	@RequiresPermissions("bizmaterialschoicechangebill:bizMaterialsChoiceChangeBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialsChoiceChangeBill bizMaterialsChoiceChangeBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMaterialsChoiceChangeBill> page = bizMaterialsChoiceChangeBillService.findPage(new Page<BizMaterialsChoiceChangeBill>(request, response), bizMaterialsChoiceChangeBill); 
		model.addAttribute("page", page);
		return "modules/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBillList";
	}
	

	@RequestMapping(value = "materialsChoiceChangeBillDetail")
	public String materialsChoiceChangeBillDetail(Integer id , Model model) {
		

		BizMaterialsChoiceChangeBill bizMaterialsChoiceChangeBill = bizMaterialsChoiceChangeBillService.get(id);
		
		BizMaterialsChoiceChangeBillItem bizMaterialsChoiceChangeBillItem = new BizMaterialsChoiceChangeBillItem();
		bizMaterialsChoiceChangeBillItem.setMaterialsChoiceChangeBillId(id);
		

		bizMaterialsChoiceChangeBillItem.setChangeType(ChangeBillConstantUtil.change_bill_change_type_1);
		List<BizMaterialsChoiceChangeBillItem> addList = bizMaterialsChoiceChangeBillItemService.findList(bizMaterialsChoiceChangeBillItem);
		

		bizMaterialsChoiceChangeBillItem.setChangeType(ChangeBillConstantUtil.change_bill_change_type_2);
		List<BizMaterialsChoiceChangeBillItem> subList = bizMaterialsChoiceChangeBillItemService.findList(bizMaterialsChoiceChangeBillItem);
		
		model.addAttribute("bizMaterialsChoiceChangeBill", bizMaterialsChoiceChangeBill);
		model.addAttribute("addList", addList);
		model.addAttribute("subList", subList);
		
		return "modules/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBillDetails";
	}
	

	@RequestMapping(value = "materialsChangeBillList")
	public String materialsChangeBillList(String orderNumber , Model model) {
		

		List<BizMaterialsChoiceChangeBill> changeBillList = bizMaterialsChoiceChangeBillService.findChangeBillMessage(orderNumber);
		
		model.addAttribute("changeBillList", changeBillList);
		
		return "modules/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBillListDetails";
	}

	
	

}