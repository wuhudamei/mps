package cn.damei.web.modules;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.MaterialWarning;
import cn.damei.entity.modules.MaterialWarningOrder;
import cn.damei.service.modules.MaterialWarningOrderService;
import cn.damei.service.modules.MaterialWarningSerivce;

@Controller
@RequestMapping(value = "${adminPath}/materialWarning/materialWarningQuery")
public class MaterialWarningController extends BaseController{

	@Autowired
	private MaterialWarningSerivce materialWarningSerivce;
	@Autowired
	private MaterialWarningOrderService materialWarningOrderSerivce;
	@ModelAttribute
	public MaterialWarning get(@RequestParam(required=false) String id) {
		MaterialWarning entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = materialWarningSerivce.get(id);
		}
		if (entity == null){
			entity = new MaterialWarning();
		}
		return entity;
	}
	
	@RequestMapping(value="list")
	public String list(Model modle,MaterialWarning materialWarning,HttpServletRequest request, HttpServletResponse response){	
		List<String> list = materialWarningSerivce.findAllDelayOrderId(2);
		List<String> list2 = materialWarningSerivce.findCompleteOrderId();
		list.removeAll(list2);
		materialWarning.setOrderIds(list);

		Page<MaterialWarning> findPage = materialWarningSerivce.findPage(new Page<MaterialWarning>(request,response), materialWarning);
		modle.addAttribute("page", findPage);
		return "/modules/materialwarning/materialWarningList";
	}
	
	@RequestMapping(value="form")
	public String form(Model model,MaterialWarningOrder materialWarningOrder,HttpServletRequest request, HttpServletResponse response){
		List<String> list = materialWarningSerivce.findAllDelayOrderId(2);
		List<String> list2 = materialWarningSerivce.findCompleteOrderId();
		list.removeAll(list2);
		materialWarningOrder.setOrderIds(list);
		Page<MaterialWarningOrder> page = materialWarningOrderSerivce.findPage(new Page<MaterialWarningOrder>(request, response),materialWarningOrder);
		model.addAttribute("page", page);
		model.addAttribute("info", materialWarningOrder);
		return "/modules/materialwarning/materialWarningListOrder";
	}
	
}
