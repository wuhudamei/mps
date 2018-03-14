package cn.damei.web.mobile.Manager;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.service.mobile.Manager.OrderConfirmStartworkPicService;


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class OrderConfirmStartworkPicController {

	private static Logger logger = LoggerFactory.getLogger(OrderConfirmStartworkPicController.class);
	
	@Autowired
	private OrderConfirmStartworkPicService orderConfirmStartworkPicService;
	


}
