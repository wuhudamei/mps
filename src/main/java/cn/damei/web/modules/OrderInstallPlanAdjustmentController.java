/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.OrderInstallPlanAdjustment;
import cn.damei.service.modules.OrderInstallPlanAdjustmentService;

/**
 * 主材可申请安装/复尺日期查询Controller
 * @author wyb
 */
@Controller
@RequestMapping(value = "${adminPath}/modules/orderInstallPlanAdjustment/web/orderInstallPlanAdjustment")
public class OrderInstallPlanAdjustmentController extends BaseController {
	
	@Autowired
	private OrderInstallPlanAdjustmentService orderInstallPlanAdjustmentService;
	
	@ModelAttribute
	public OrderInstallPlanAdjustment get(@RequestParam(required=false) Integer id) {
		OrderInstallPlanAdjustment entity = null;
		if (StringUtils.isNotBlank(id+"")){
			entity = orderInstallPlanAdjustmentService.get(id);
		}
		if (entity == null){
			entity = new OrderInstallPlanAdjustment();
		}
		return entity;
	}
	
	/**
	 * 主材可申请安装日期查询【列表页-前】【安装】
	 * @param orderInstallPlanAdjustment
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("orderInstallPlanAdjustment:orderInstallPlanAdjustment:view")
	@RequestMapping(value = {"preInstallPlanQueryInstallList", ""})
	public String preInstallPlanQueryInstallList(OrderInstallPlanAdjustment orderInstallPlanAdjustment, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		orderInstallPlanAdjustmentService.storeIdAndProjectMode(orderInstallPlanAdjustment, model);
		return "modules/orderInstallPlanAdjustment/bizOrderInstallPlanInstallList";
	}
	
	/**
	 * 主材可申请安装日期查询【列表页】【安装】
	 * @param orderInstallPlanAdjustment
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("orderInstallPlanAdjustment:orderInstallPlanAdjustment:view")
	@RequestMapping(value = {"installPlanQueryInstallList", ""})
	public String installPlanQueryInstallList(OrderInstallPlanAdjustment orderInstallPlanAdjustment, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		orderInstallPlanAdjustmentService.storeIdAndProjectMode(orderInstallPlanAdjustment, model);
		
		Page<OrderInstallPlanAdjustment> page = orderInstallPlanAdjustmentService.findInstallPage(new Page<OrderInstallPlanAdjustment>(request, response), orderInstallPlanAdjustment); 
		model.addAttribute("page", page);
		return "modules/orderInstallPlanAdjustment/bizOrderInstallPlanInstallList";
	}
	
	/**
	 * 主材可申请复尺日期查询【列表页-前】【复尺】
	 * @param orderInstallPlanAdjustment
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("orderInstallPlanAdjustment:orderInstallPlanAdjustment:view")
	@RequestMapping(value = {"preInstallPlanQueryChecksizeList", ""})
	public String preInstallPlanQueryChecksizeList(OrderInstallPlanAdjustment orderInstallPlanAdjustment, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		orderInstallPlanAdjustmentService.storeIdAndProjectMode(orderInstallPlanAdjustment, model);
		return "modules/orderInstallPlanAdjustment/bizOrderInstallPlanChecksizeList";
	}
	

	/**
	 * 主材可申请复尺日期查询【列表页】【复尺】
	 * @param orderInstallPlanAdjustment
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("orderInstallPlanAdjustment:orderInstallPlanAdjustment:view")
	@RequestMapping(value = {"installPlanQueryChecksizeList", ""})
	public String installPlanQueryChecksizeList(OrderInstallPlanAdjustment orderInstallPlanAdjustment, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		orderInstallPlanAdjustmentService.storeIdAndProjectMode(orderInstallPlanAdjustment, model);
		
		Page<OrderInstallPlanAdjustment> page = orderInstallPlanAdjustmentService.findChecksizePage(new Page<OrderInstallPlanAdjustment>(request, response), orderInstallPlanAdjustment); 
		model.addAttribute("page", page);
		return "modules/orderInstallPlanAdjustment/bizOrderInstallPlanChecksizeList";
	}
	
	
	
	
	/**
	 * 主材可申请安装日期查询【同意】【安装】
	 * @param orderInstallPlanAdjustment
	 * @param request
	 * @return
	 */
	@RequiresPermissions("orderInstallPlanAdjustment:orderInstallPlanAdjustment:edit")
	@RequestMapping(value = { "save_order_install_plan_install_date_apply", "" })
	public @ResponseBody String saveOrderInstallPlanInstallDateApply(OrderInstallPlanAdjustment orderInstallPlanAdjustment,HttpServletRequest request) {
		return orderInstallPlanAdjustmentService.saveOrderInstallPlanInstallDateApply(orderInstallPlanAdjustment);
	}
	
	
	/**
	 * 主材可申请复尺日期查询【同意】【复尺】
	 * @param orderInstallPlanAdjustment
	 * @param request
	 * @return
	 */
	@RequiresPermissions("orderInstallPlanAdjustment:orderInstallPlanAdjustment:edit")
	@RequestMapping(value = { "save_order_install_plan_checksize_date_apply", "" })
	public @ResponseBody String saveOrderInstallPlanChecksizeDateApply(OrderInstallPlanAdjustment orderInstallPlanAdjustment,HttpServletRequest request) {
		return orderInstallPlanAdjustmentService.saveOrderInstallPlanChecksizeDateApply(orderInstallPlanAdjustment);
	}


	

}