/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 选材变更单表Controller
 * @author wyb
 * @version 2017-06-14
 */
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
	
	/**
	 * 选材变更单详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "materialsChoiceChangeBillDetail")
	public String materialsChoiceChangeBillDetail(Integer id , Model model) {
		
		//1.选材变更单基本信息详情
		BizMaterialsChoiceChangeBill bizMaterialsChoiceChangeBill = bizMaterialsChoiceChangeBillService.get(id);
		
		BizMaterialsChoiceChangeBillItem bizMaterialsChoiceChangeBillItem = new BizMaterialsChoiceChangeBillItem();
		bizMaterialsChoiceChangeBillItem.setMaterialsChoiceChangeBillId(id);
		
		//2.增项详情
		bizMaterialsChoiceChangeBillItem.setChangeType(ChangeBillConstantUtil.change_bill_change_type_1);
		List<BizMaterialsChoiceChangeBillItem> addList = bizMaterialsChoiceChangeBillItemService.findList(bizMaterialsChoiceChangeBillItem);
		
		//3.减项详情
		bizMaterialsChoiceChangeBillItem.setChangeType(ChangeBillConstantUtil.change_bill_change_type_2);
		List<BizMaterialsChoiceChangeBillItem> subList = bizMaterialsChoiceChangeBillItemService.findList(bizMaterialsChoiceChangeBillItem);
		
		model.addAttribute("bizMaterialsChoiceChangeBill", bizMaterialsChoiceChangeBill);
		model.addAttribute("addList", addList);
		model.addAttribute("subList", subList);
		
		return "modules/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBillDetails";
	}
	
	/**
	 * 订单变更单列表
	 * @param orderNumber
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "materialsChangeBillList")
	public String materialsChangeBillList(String orderNumber , Model model) {
		
		//根据订单编号查询该订单下所有的变更单
		List<BizMaterialsChoiceChangeBill> changeBillList = bizMaterialsChoiceChangeBillService.findChangeBillMessage(orderNumber);
		
		model.addAttribute("changeBillList", changeBillList);
		
		return "modules/bizmaterialschoicechangebill/bizMaterialsChoiceChangeBillListDetails";
	}

	
	

}