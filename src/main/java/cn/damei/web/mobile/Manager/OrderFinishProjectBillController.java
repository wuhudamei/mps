package cn.damei.web.mobile.Manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.service.mobile.Manager.OrderFinishProjectBillService;

/**
 * 项目经理端APP
 * 申请竣工
 * biz_order_finish_project_bill
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class OrderFinishProjectBillController {
	@Autowired
	private OrderFinishProjectBillService orderFinishProjectBillService;
}
