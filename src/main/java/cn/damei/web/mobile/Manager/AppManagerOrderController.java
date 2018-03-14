package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.AppManagerOrder;
import cn.damei.service.mobile.Manager.AppManagerOrderService;


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class AppManagerOrderController {
	private static Logger logger = LoggerFactory.getLogger(AppManagerOrderController.class);
	
	@Autowired
	private AppManagerOrderService appManagerOrderService;
	

	@RequestMapping(value={"orderDisclose",""})
	public String orderCadfileList(AppManagerOrder appManagerOrder, HttpServletRequest request, Model model) {

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		logger.info("当前项目经理ID："+ manager.getId() + "当前项目经理名字：" + manager.getRealname());
		
		List<AppManagerOrder> orderList = appManagerOrderService.getByItemManagerId(manager.getId());
		
		model.addAttribute("orderList", orderList);
		return "mobile/modules/Manager/progressMain/orderDisclose/orderDiscloseList";
	}
}
