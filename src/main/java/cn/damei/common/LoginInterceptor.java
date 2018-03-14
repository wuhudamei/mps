package cn.damei.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Worker.Worker;
public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String[] ignore_url = {"/login", "/toLogin","/get","/isExist","/method"};

		String requestURI = request.getRequestURI();
		boolean flag = false;
		for (String s : ignore_url) {
            if (requestURI.contains(s)) {
                flag = true;
                break;
            }
        }


		if(requestURI.contains("/home")){
			return true;
		}else if(requestURI.contains("/worker")){
			if (!flag) {
	        	Worker worker =(Worker) request.getSession().getAttribute("worker");
	            if (worker != null) {
	            	flag = true;
	            }else{
	            	response.sendRedirect(GetUrlUtils.getUrl(requestURI));
	            }
	        }
	        return flag;
		}else if(requestURI.contains("/manager")){
			if (!flag) {
	        	Manager manager =(Manager) request.getSession().getAttribute("manager");
	            if (manager != null) {
	            	flag = true;
	            }else{
	            	response.sendRedirect(GetUrlUtils.getUrl(requestURI));
	            }
	        }
	        return flag;
		}else {
			if (!flag) {
	        Inspector inspector =(Inspector)request.getSession().getAttribute("inspector");
	            if (inspector != null) {
	            	flag = true;
	            }else{
	            	response.sendRedirect(GetUrlUtils.getUrl(requestURI));
	            }
	        }
	        return flag;

		}


	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
	
}
