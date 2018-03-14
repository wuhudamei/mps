package cn.damei.web.mobile.home;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import cn.damei.common.constantUtils.phoneMessgeTypeConstant.PhoneMessageTypeConstant;
import cn.damei.service.modules.BizPhoneMsgService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Manager.PhoneCode;
import cn.damei.service.mobile.Manager.PhoneCodeService;
import cn.damei.entity.mobile.home.HomeLoginLogoutLog;
import cn.damei.service.mobile.home.HomeLoginLogoutLogService;
import cn.damei.entity.mobile.home.CustomerOrder;
import cn.damei.service.mobile.home.CustomerOrderService;


@Controller
@RequestMapping(value="${adminPath}/app/wx/home")
public class WeiChatController {
	
	ObjectMapper mapper = new ObjectMapper(); 
	@Autowired
	private CustomerOrderService customerOrderService;
	@Autowired
	private PhoneCodeService phoneCodeService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private HomeLoginLogoutLogService homeLoginLogoutLogService;
	

	private static String DEAL_TYPE_IN = "in";

	private static String DEAL_TYPE_MODE = "wechat";
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="sendCode")
	@ResponseBody
	public String sendCode(String mobilePhone,String appType) throws Exception  {
		List<CustomerOrder> orders = customerOrderService.findOrderByPhone(mobilePhone);

		if(orders != null && orders.size()>0){
			String messageCode = "";
	        Random random = new Random();
	        for(int i=0; i<6; i++){
	        	messageCode += String.valueOf(random.nextInt(10));
	        }

	        String msgContent = "尊敬的用户，验证码为:"+messageCode;
			String status = bizPhoneMsgService.sendMessageHttps(PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_HTTPS,
					PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_USER_ID,
					PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_ACCOUNT,
					PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_PASSWORD,mobilePhone,msgContent);

	    	if(!"1".equals(status)){
	    		return "messagefail";
	    	}else{

	    		PhoneCode phoneCode = new PhoneCode();
	    		phoneCode.setAppType(appType);
	    		phoneCode.setPhoneNumber(mobilePhone);
	    		phoneCode.setPhoneCode(messageCode);
	    		phoneCode.setValidityDatetime(new Date());
	    		phoneCodeService.add(phoneCode);
	    		return "messagesuccess";
	    	}
		}else{
			return "error";
		}
	}
	
	@RequestMapping(value="login")
	@ResponseBody
	public String login(String code,String mobilePhone,String appType,HttpServletRequest request){

		PhoneCode phoneCode = phoneCodeService.findByUsernameAndCode(mobilePhone,code,appType);
		
		if(null == phoneCode){
			return "codeerror";
		}else{

			HomeLoginLogoutLog homeLoginLogoutLog = new HomeLoginLogoutLog();
			homeLoginLogoutLog.setDealMode(DEAL_TYPE_MODE);
			homeLoginLogoutLog.setDealType(DEAL_TYPE_IN);
			homeLoginLogoutLog.setDealPhone(mobilePhone);
			homeLoginLogoutLog.setDealTime(new Date() );
			homeLoginLogoutLog.setRemarks("登录成功，方法名【login】，登陆端【wechat】");
			
			homeLoginLogoutLogService.save(homeLoginLogoutLog);
			



			request.getSession().setAttribute("customerPhone", mobilePhone);
			return "loginsuccess";
		}
	}
	@RequestMapping(value="login2")
	@ResponseBody
	public String login2(String code,String mobilePhone,String appType,HttpServletRequest request){
		List<CustomerOrder> orders = customerOrderService.findOrderByPhone(mobilePhone);
		if(orders!=null&&orders.size()>0){
			request.getSession().setAttribute("customerPhone", mobilePhone);
			return "loginsuccess";
		}else{
			return "codeerror";
		}
		
	}
}
