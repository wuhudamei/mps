package cn.damei.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.service.modules.CustomerServiceInformationQuarz;
import cn.damei.common.web.BaseController;


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