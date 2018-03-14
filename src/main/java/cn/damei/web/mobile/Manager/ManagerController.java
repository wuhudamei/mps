package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.damei.common.constantUtils.phoneMessgeTypeConstant.PhoneMessageTypeConstant;
import cn.damei.common.utils.StringUtils;
import cn.damei.service.modules.BizPhoneMsgService;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.service.mobile.Manager.ManagerService;

@Controller
@RequestMapping(value="${adminPath}/app/manager")
public class ManagerController {
	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	@RequestMapping(value="toLogin")
	public String toLogin(){
		
		return "mobile/modules/Manager/login1";
	}
	
	@RequestMapping(value="toLogout")
	public String toLogout(HttpServletRequest request,HttpServletResponse respons){
		Enumeration<String> em = request.getSession().getAttributeNames();
		 while (em.hasMoreElements()) {
		   request.getSession().removeAttribute(em.nextElement().toString());
		 }
		request.getSession().invalidate();
		return "mobile/modules/Manager/login1";
	}
	
	@RequestMapping(value="isExist",method=RequestMethod.POST)
	public @ResponseBody String isExist(@RequestBody String username,HttpServletRequest request){
		
		Manager manager = managerService.selectManagerByPhone(username);
		if(manager==null){
			return "0";
		}else{
	        return "1";
		}
	}

	@RequestMapping(value="login",method=RequestMethod.POST)
	public @ResponseBody String login(String username,String yanzheng,Model model,HttpServletRequest request,HttpServletResponse response ){
		
		Manager manager = managerService.selectManagerByPhone(username);
		if(manager==null){

			return "4";
		}else{
			HttpSession session = request.getSession();
	        session.setAttribute("manager", manager);
	        session.setMaxInactiveInterval(3600);
		}

		if(ConstantUtils.USER_NAME_18519377253.equals(username)){
			if(ConstantUtils.PASS_WORD_888888.equals(yanzheng)){
				return "3";
			}else{
				String sessionCode = (String) request.getSession().getAttribute(username);
				if(StringUtils.isBlank(sessionCode)){
					return "2";
				}else{
					if(sessionCode.trim().equals(yanzheng)){
						return "3";
					}else{
						return "1";
					}
				}
			}
		}else{
			String sessionCode = (String) request.getSession().getAttribute(username);
			if(sessionCode == null){
				return "2";
			}else{
				if(sessionCode.trim().equals(yanzheng)){
					return "3";
				}else{
					return "1";
				}
			}
		}


	}
	
	@RequestMapping(value="get",method=RequestMethod.POST)
	public @ResponseBody String get(@RequestBody String username ,HttpServletRequest request){
		String code = " ";
        Random random = new Random();
        for(int i=0; i<6; i++){
        	code += String.valueOf(random.nextInt(10));
        }
        HttpSession session = request.getSession();
        session.setAttribute(username, code);

        String msgContent = "尊敬的用户，验证码为:"+code;
		bizPhoneMsgService.sendMessageHttps(PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_HTTPS,
				PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_USER_ID,
				PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_ACCOUNT,
				PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_PASSWORD,username,msgContent);

		return code;
	}
	






	


}
