package cn.damei.web.mobile.Manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.service.mobile.Manager.OrderConfirmStartworkService;


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class OrderConfirmStartworkController {

	private static Logger logger = LoggerFactory.getLogger(OrderConfirmStartworkController.class);
	
	@Autowired
	private OrderConfirmStartworkService orderConfirmStartworkService;
	
}
