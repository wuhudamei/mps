package cn.damei.web.modules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.web.BaseController;
import cn.damei.service.mobile.Manager.OrderConfirmStartworkPicService;

/**
 * @author llp
 * @version 创建时间：2016年10月27日 下午5:05:38 类说明
 */
@Controller
@RequestMapping(value = "${adminPath}/bizconfirmstartworkpic/bizConfirmStartworkPic")
public class BizOrderConfirmStartworkPicController extends BaseController{

	private static Logger logger = LoggerFactory.getLogger(BizOrderConfirmStartworkPicController.class);
	
	@Autowired
	private OrderConfirmStartworkPicService orderConfirmStartworkPicService;
	
}
