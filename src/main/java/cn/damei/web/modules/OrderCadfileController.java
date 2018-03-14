/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.OrderCadfile;
import cn.damei.service.modules.OrderCadfileService;
import cn.damei.common.utils.UserUtils;

/**
 * 订单图片Controller
 * 
 * @author mh
 * @version 2016-09-08
 */
@Controller
@RequestMapping(value = "${adminPath}/ordercad/orderCadfile")
public class OrderCadfileController extends BaseController {
	
	@Autowired
	private OrderCadfileService orderCadfileService;

	@ModelAttribute
	public OrderCadfile get(@RequestParam(required = false) Integer id) {
		OrderCadfile entity = null;
		if (StringUtils.isNotBlank(id+"")) {
			entity = orderCadfileService.get(id);
		}
		if (entity == null) {
			entity = new OrderCadfile();
		}
		return entity;
	}

	@RequiresPermissions("ordercad:orderCadfile:view")
	@RequestMapping(value = { "list", "" })
	public String list(OrderCadfile orderCadfile, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		Page<OrderCadfile> page = orderCadfileService.findPage(new Page<OrderCadfile>(request, response), orderCadfile);
		model.addAttribute("page", page);
		model.addAttribute("orderCadfile", orderCadfile);
		return "modules/ordercad/orderCadfileList";
	}

	@RequiresPermissions("ordercad:orderCadfile:view")
	@RequestMapping(value = "form") 
	public String form(OrderCadfile orderCadfile, Model model) {
		model.addAttribute("orderCadfile", orderCadfile);
		return "modules/ordercad/orderCadfileForm";
	}
	@RequiresPermissions("ordercad:orderCadfile:view")
	@RequestMapping(value = "details")
	public String details(OrderCadfile orderCadfile, Model model) {
		/*orderCadfile = orderCadfileService.findDetail(orderCadfile);*/
		model.addAttribute("orderCadfile", orderCadfile);
		return "modules/ordercad/orderCadfileFormDetails";
	}

	@RequiresPermissions("ordercad:orderCadfile:edit")
	@RequestMapping(value = "save")
	public String save(OrderCadfile orderCadfile, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderCadfile)) {
			return form(orderCadfile, model);
		}

		// 通过订单id , 得到该订单下的所有图纸
		List<OrderCadfile> list = orderCadfileService.findList(orderCadfile);
		// 判断是否有图纸
		if (null != list && list.size() > 0) {
			
			//new 一个泛型为版本的集合
			List<Integer> versions = new ArrayList<Integer>();

			// 如果有, 遍历
			for (OrderCadfile orderCadfile2 : list) {

				// 把版本 放到一个泛型为版本的 集合中
				versions.add(orderCadfile2.getVersion());

			}
			// 利用工具Collections 排序
			Collections.sort(versions);

			// 如果只有一个图纸, 那么设置新增的图纸对象就是 第一个索引的版本号+1
			if (versions.size() == 1) {
				orderCadfile.setVersion(versions.get(0) + 1);

			}
			// 不止一个图纸 , 那么就是 该集合的最后一个元素 (已经sort过, 是最大的版本) +1
			orderCadfile.setVersion(versions.get(versions.size() - 1) + 1);

		} else {
			// 如果为空, 也就是没有图纸 设置 版本为1
			orderCadfile.setVersion(1);
		}
		// 默认图纸名,就是就上传的图纸的名字
		orderCadfile.setDisplayFileName(orderCadfile.getFileName());
		orderCadfile.setCreateDate(new Date());
		orderCadfile.setCreateBy(UserUtils.getUser());
		orderCadfileService.insertCadfile(orderCadfile);
		addMessage(redirectAttributes, "保存图片成功");
		if(orderCadfile.getOrderId()!=null){
			return "redirect:" + Global.getAdminPath() + "/ordercad/orderCadfile/?repage&orderId="
					+ orderCadfile.getOrderId()+"&orderNumber="+orderCadfile.getOrderNumber()+"&flag="+orderCadfile.getFlag()+"";
		}
		return "redirect:" + Global.getAdminPath() + "/ordercad/orderCadfile/?repage&orderNumber="+orderCadfile.getOrderNumber()+"&flag="+orderCadfile.getFlag()+"";
	}

	@RequiresPermissions("ordercad:orderCadfile:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderCadfile orderCadfile, RedirectAttributes redirectAttributes) {
		orderCadfileService.delete(orderCadfile);
		addMessage(redirectAttributes, "删除图片成功");
		if(orderCadfile.getOrderId()!=null){
			return "redirect:" + Global.getAdminPath() + "/ordercad/orderCadfile/?repage&orderId="
					+ orderCadfile.getOrderId()+"&orderNumber="+orderCadfile.getOrderNumber()+"&flag="+orderCadfile.getFlag()+"";
		}
		return "redirect:" + Global.getAdminPath() + "/ordercad/orderCadfile/?repage&orderNumber="+orderCadfile.getOrderNumber()+"&flag="+orderCadfile.getFlag()+"";
	}

}