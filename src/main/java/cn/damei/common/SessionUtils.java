package cn.damei.common;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.entity.mobile.home.CustomerVo;



public class SessionUtils {

	
	
	

	public static Manager getManagerSession(HttpServletRequest request){
		Manager  manager =  (Manager)request.getSession().getAttribute("manager");
		
	return manager;
	}
	

	public static Worker getWorkerSession(HttpServletRequest request){
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		
	return worker;
	}
	


	public static Inspector getInspectorSession(HttpServletRequest request){
		Inspector inspector = (Inspector)request.getSession().getAttribute("inspector");
		
	return inspector;
	}

	public static CustomerVo getCustomerSession(HttpServletRequest request){
		CustomerVo vo  = (CustomerVo)request.getSession().getAttribute("customer");
		
		return vo;
	}
	
}
