package cn.damei.web.mobile.home;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.service.mobile.Manager.SysTokenService;
import cn.damei.entity.mobile.home.HomeLoginLogoutLog;
import cn.damei.service.mobile.home.HomeLoginLogoutLogService;
import cn.damei.service.mobile.home.WeiChatOpenIdService;

@Controller
@RequestMapping(value="${adminPath}/app/home")
public class LogoutController {
	@Autowired
	private SysTokenService sysTokenService;
	@Autowired
	private WeiChatOpenIdService weiChatOpenIdService;
	
	@Autowired
	private HomeLoginLogoutLogService homeLoginLogoutLogService;

	private static String DEAL_TYPE_OUT = "out";
	
	public Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping(value="logout")
	public String logout(HttpServletRequest request){
		
		String phone = (String) request.getSession().getAttribute("customerPhone");
	
		String client = (String) request.getSession().getAttribute("client");
		

		HomeLoginLogoutLog homeLoginLogoutLog = new HomeLoginLogoutLog();
		homeLoginLogoutLog.setDealMode("app");
		homeLoginLogoutLog.setDealType(DEAL_TYPE_OUT);
		homeLoginLogoutLog.setDealPhone(phone);
		homeLoginLogoutLog.setDealTime(new Date() );
		homeLoginLogoutLog.setRemarks( StringUtils.isNotBlank(client) && ("wechat").equals(client) ? "wechat" : "app" + "app退出调用（方法名：logout）");
		homeLoginLogoutLogService.save(homeLoginLogoutLog);
		
		if("wechat".equals(client)){


			request.getSession().removeAttribute("customerPhone");
			return "mobile/modules/home/weichat/weiChatLogin";
		}else{

			sysTokenService.deleteByPhone(phone,ConstantUtils.EMP_TYPE_4);
			request.getSession().removeAttribute("customerPhone");
			return "mobile/modules/api/loginFailed";
		}
		
		
	}
}
