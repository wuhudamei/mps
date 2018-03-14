package cn.damei.common;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.entity.mobile.home.CustomerVo;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月22日 下午7:44:56 
* session抽取 基类   
*/

public class SessionUtils {

	
	
	
	/**
	 * session域的经理
	 * @param request
	 * @return
	 */
	public static Manager getManagerSession(HttpServletRequest request){
		Manager  manager =  (Manager)request.getSession().getAttribute("manager");
		
	return manager;
	}
	
	/**
	 * session域的工人
	 * @param request
	 * @return
	 */
	public static Worker getWorkerSession(HttpServletRequest request){
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		
	return worker;
	}
	

	/**
	 * session域的质检员
	 * @param request
	 * @return
	 */
	public static Inspector getInspectorSession(HttpServletRequest request){
		Inspector inspector = (Inspector)request.getSession().getAttribute("inspector");
		
	return inspector;
	}
	/**
	 * session域的客户
	 * @param request
	 * @return
	 */
	public static CustomerVo getCustomerSession(HttpServletRequest request){
		CustomerVo vo  = (CustomerVo)request.getSession().getAttribute("customer");
		
		return vo;
	}
	
}
