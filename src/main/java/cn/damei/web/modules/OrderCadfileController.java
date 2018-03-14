
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

		model.addAttribute("orderCadfile", orderCadfile);
		return "modules/ordercad/orderCadfileFormDetails";
	}

	@RequiresPermissions("ordercad:orderCadfile:edit")
	@RequestMapping(value = "save")
	public String save(OrderCadfile orderCadfile, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderCadfile)) {
			return form(orderCadfile, model);
		}


		List<OrderCadfile> list = orderCadfileService.findList(orderCadfile);

		if (null != list && list.size() > 0) {
			

			List<Integer> versions = new ArrayList<Integer>();


			for (OrderCadfile orderCadfile2 : list) {


				versions.add(orderCadfile2.getVersion());

			}

			Collections.sort(versions);


			if (versions.size() == 1) {
				orderCadfile.setVersion(versions.get(0) + 1);

			}

			orderCadfile.setVersion(versions.get(versions.size() - 1) + 1);

		} else {

			orderCadfile.setVersion(1);
		}

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