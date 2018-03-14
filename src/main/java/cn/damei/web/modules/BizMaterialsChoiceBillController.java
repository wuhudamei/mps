
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

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.service.modules.BizMaterialsChoiceBillService;
import cn.damei.entity.modules.BizMaterialsChoiceBillItem;
import cn.damei.service.modules.BizMaterialsChoiceBillItemService;


@Controller
@RequestMapping(value = "${adminPath}/bizmaterialchoicebill/bizMaterialsChoiceBill")
public class BizMaterialsChoiceBillController extends BaseController {

	@Autowired
	private BizMaterialsChoiceBillService bizMaterialsChoiceBillService;
	@Autowired
	private BizMaterialsChoiceBillItemService bizMaterialsChoiceBillItemService;
	
	@ModelAttribute
	public BizMaterialsChoiceBill get(@RequestParam(required=false) Integer id) {
		BizMaterialsChoiceBill entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = bizMaterialsChoiceBillService.get(id);
		}
		if (entity == null){
			entity = new BizMaterialsChoiceBill();
		}
		return entity;
	}
	
	@RequiresPermissions("bizmaterialchoicebill:bizMaterialsChoiceBill:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BizMaterialsChoiceBill bizMaterialsChoiceBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/bizmaterialschoicebill/bizMaterialsChoiceBillList";
	}
	
	@RequiresPermissions("bizmaterialchoicebill:bizMaterialsChoiceBill:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterialsChoiceBill bizMaterialsChoiceBill, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMaterialsChoiceBill> page = bizMaterialsChoiceBillService.findPage(new Page<BizMaterialsChoiceBill>(request, response), bizMaterialsChoiceBill); 
		model.addAttribute("page", page);
		return "modules/bizmaterialschoicebill/bizMaterialsChoiceBillList";
	}
	

	@RequestMapping(value = "materialsChoiceBillDetail")
	public String materialsChoiceBillDetail(Integer id , Model model) {
		

		BizMaterialsChoiceBill bizMaterialsChoiceBill = bizMaterialsChoiceBillService.get(id);

		

		BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
		bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(id);
		List<BizMaterialsChoiceBillItem> materialsChoiceList = bizMaterialsChoiceBillItemService.findList(bizMaterialsChoiceBillItem);
		
		
		model.addAttribute("bizMaterialsChoiceBill", bizMaterialsChoiceBill);
		model.addAttribute("materialsChoiceList", materialsChoiceList);
		
		return "modules/bizmaterialschoicebill/bizMaterialsChoiceBillDetails";
	}
	

	@RequestMapping(value = "order_materials_choice_bill_detail")
	public String orderMaterialsChoiceBillDetail(Integer orderId , Model model) {
		

		BizMaterialsChoiceBill bizMaterialsChoiceBill = bizMaterialsChoiceBillService.findOrder(orderId);
		

		List<BizMaterialsChoiceBillItem> materialsChoiceList = null;
		if(null!=bizMaterialsChoiceBill.getId()){
			BizMaterialsChoiceBillItem bizMaterialsChoiceBillItem = new BizMaterialsChoiceBillItem();
			bizMaterialsChoiceBillItem.setMaterialsChoiceBillId(bizMaterialsChoiceBill.getId());
			materialsChoiceList = bizMaterialsChoiceBillItemService.findList(bizMaterialsChoiceBillItem);
		}
		
		model.addAttribute("bizMaterialsChoiceBill", bizMaterialsChoiceBill);
		model.addAttribute("materialsChoiceList", materialsChoiceList);
		
		return "modules/bizmaterialschoicebill/orderMaterialsChoiceBillDetails";
	}
	

	

}