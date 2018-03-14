package cn.damei.web.modules;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.OrderManagerChange;
import cn.damei.service.modules.OrderManagerChangeService;

@Controller
@RequestMapping(value = "${adminPath}/selectOrderChange/selectOrderChange/")
public class OrderManagerChangeController extends BaseController {

	@Autowired
	private OrderManagerChangeService orderManagerChangeService; 
	
	@RequiresPermissions("selectOrder:selectOrderChange:view")
	@RequestMapping(value="selectList")
	public String selectList(@ModelAttribute OrderManagerChange orderManagerChange){
		return "modules/selectorder/orderManagerChange";
	}
	
	@RequiresPermissions("selectOrder:selectOrderChange:view")
	@RequestMapping(value="list")
	public String list(@ModelAttribute OrderManagerChange orderManagerChange,HttpServletRequest request, HttpServletResponse response, Model model){
		Page<OrderManagerChange> page = orderManagerChangeService.findPage(new Page<OrderManagerChange>(request,response), orderManagerChange);
		model.addAttribute("page", page);
		return "modules/selectorder/orderManagerChange";
	}
}