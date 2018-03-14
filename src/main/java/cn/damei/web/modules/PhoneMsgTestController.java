/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.PhoneMsgTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信https 接口测试 Controller
 */
@Controller
@RequestMapping(value = "${adminPath}/phonemsg/phoneMsgTest")
public class PhoneMsgTestController extends BaseController {

	@Autowired
	private PhoneMsgTestService phoneMsgTestService;
	
	@RequestMapping(value = {"testList", ""})
	public String testList(BizPhoneMsg bizPhoneMsg, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/phonemsg/phoneMsgTest";
	}

	@RequestMapping(value="sendMessageTest",method= RequestMethod.POST)
	public @ResponseBody String sendMessageTest(String msgPhone ,String msgContent, HttpServletRequest request){

		String SEND_MESSAGE_INTERFACE_URL_HTTPS = "https://sh2.ipyy.com/ensms.ashx";//https短信接口
		String SEND_MESSAGE_INTERFACE_URL_USER_ID = "2738";//企业id
		String SEND_MESSAGE_INTERFACE_URL_ACCOUNT = "jksc602";//用户账号
		String SEND_MESSAGE_INTERFACE_URL_PASSWORD = "263874";//帐号密码

		String status = phoneMsgTestService.sendMessageHttps(SEND_MESSAGE_INTERFACE_URL_HTTPS,
				SEND_MESSAGE_INTERFACE_URL_USER_ID,
				SEND_MESSAGE_INTERFACE_URL_ACCOUNT,
				SEND_MESSAGE_INTERFACE_URL_PASSWORD,
				msgPhone,msgContent);

		return status;
	}


}