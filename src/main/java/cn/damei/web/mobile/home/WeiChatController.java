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
	
	//登录类型(登录)
	private static String DEAL_TYPE_IN = "in";
	//登录模式(登录)
	private static String DEAL_TYPE_MODE = "wechat";
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="sendCode")
	@ResponseBody
	public String sendCode(String mobilePhone,String appType) throws Exception  {
		List<CustomerOrder> orders = customerOrderService.findOrderByPhone(mobilePhone);
		//判断手机号是否是order表中的客户手机号 --是发送短信 否提示用户
		if(orders != null && orders.size()>0){
			String messageCode = "";
	        Random random = new Random();
	        for(int i=0; i<6; i++){    //表示生成六位验证码
	        	messageCode += String.valueOf(random.nextInt(10));//采用随机码生成0-10（包括0，不包括10）的验证码，生成六次，构成六位数验证码；
	        }

	        String msgContent = "尊敬的用户，验证码为:"+messageCode;
			String status = bizPhoneMsgService.sendMessageHttps(PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_HTTPS,
					PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_USER_ID,
					PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_ACCOUNT,
					PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_PASSWORD,mobilePhone,msgContent);

	    	if(!"1".equals(status)){
	    		return "messagefail";
	    	}else{
	    		//发送短信保存到数据库中
	    		PhoneCode phoneCode = new PhoneCode();
	    		phoneCode.setAppType(appType);
	    		phoneCode.setPhoneNumber(mobilePhone);
	    		phoneCode.setPhoneCode(messageCode);
	    		phoneCode.setValidityDatetime(new Date());
	    		phoneCodeService.add(phoneCode);
	    		return "messagesuccess";
	    	}
		}else{
			return "error";//手机号未注册
		}
	}
	
	@RequestMapping(value="login")
	@ResponseBody
	public String login(String code,String mobilePhone,String appType,HttpServletRequest request){
		//根据手机号和验证码查询code表 -- 存在返回success 不存在返回error
		PhoneCode phoneCode = phoneCodeService.findByUsernameAndCode(mobilePhone,code,appType);
		
		if(null == phoneCode){
			return "codeerror";//验证码验证失败
		}else{
			//登录成功，记录登录日志
			HomeLoginLogoutLog homeLoginLogoutLog = new HomeLoginLogoutLog();
			homeLoginLogoutLog.setDealMode(DEAL_TYPE_MODE);
			homeLoginLogoutLog.setDealType(DEAL_TYPE_IN);
			homeLoginLogoutLog.setDealPhone(mobilePhone);
			homeLoginLogoutLog.setDealTime(new Date() );
			homeLoginLogoutLog.setRemarks("登录成功，方法名【login】，登陆端【wechat】");
			
			homeLoginLogoutLogService.save(homeLoginLogoutLog);
			
			//String openid = (String) request.getSession().getAttribute("openid");
			//保存手机号和openid到数据表
			/*WeiChatOpenId openId = new WeiChatOpenId();
			openId.setPhone(mobilePhone);
			openId.setOpenid(openid);
			openId.setBindDatetime(new Date());
			openId.setBindStatus(ConstantUtils.BIND_STATUS_1);
			weiChatOpenIdService.save(openId);*/
			request.getSession().setAttribute("customerPhone", mobilePhone);
			return "loginsuccess"; //--跳转到checkOpenId
		}
	}
	@RequestMapping(value="login2")
	@ResponseBody
	public String login2(String code,String mobilePhone,String appType,HttpServletRequest request){
		List<CustomerOrder> orders = customerOrderService.findOrderByPhone(mobilePhone);
		if(orders!=null&&orders.size()>0){
			request.getSession().setAttribute("customerPhone", mobilePhone);
			return "loginsuccess"; //--跳转到checkOpenId
		}else{
			return "codeerror";//登录失败
		}
		
	}
}
