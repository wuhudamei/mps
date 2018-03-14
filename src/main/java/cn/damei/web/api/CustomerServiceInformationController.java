package cn.damei.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.service.modules.CustomerServiceInformationQuarz;
import cn.damei.common.web.BaseController;

/**
 * 远程调用 (用于人工调用) 客服信息同步
 * @author lft
 *	 2017-5-9
 */
@Controller
@RequestMapping(value = "${adminPath}/api/customerService")
public class CustomerServiceInformationController extends BaseController {

	@Autowired
	private CustomerServiceInformationQuarz customerServiceInformationQuarz;
	
	@RequestMapping(value = {"customerServiceInformation", ""})
	public void customerServiceInformation() {

		customerServiceInformationQuarz.execute();
	}
 
	
}