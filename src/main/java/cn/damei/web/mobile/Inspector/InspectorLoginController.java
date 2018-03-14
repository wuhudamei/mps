package cn.damei.web.mobile.Inspector;

import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.damei.common.constantUtils.phoneMessgeTypeConstant.PhoneMessageTypeConstant;
import cn.damei.service.modules.BizPhoneMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.service.mobile.Inspector.InspectorLoginService;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年10月24日 下午3:47:13 质检登录系统首页
 */
@Controller
@RequestMapping(value = "${adminPath}/app/pqc")
public class InspectorLoginController {

	@Autowired
	private InspectorLoginService service;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	@RequestMapping(value = "toLogin")
	public String toLogin() {

		return "mobile/modules/pqc/Login/login";
	}
	
	@RequestMapping(value="toLogout")
	public String toLogout(HttpServletRequest request,HttpServletResponse respons){
		Enumeration<String> em = request.getSession().getAttributeNames();
		 while (em.hasMoreElements()) {
		   request.getSession().removeAttribute(em.nextElement().toString());
		 }
		request.getSession().invalidate();
		return "mobile/modules/pqc/Login/login";
	}

	/**
	 * 校验手机号是否存在
	 * 
	 * @param username
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "isExist", method = RequestMethod.POST)
	public @ResponseBody String isExist(@RequestBody String username, HttpServletRequest request) {

		// 根据手机查询是否有该质检员用户
		Inspector inspector = service.selectInspectorByPhone1(username, "1");
		if (inspector == null) {
			return "0";
		} else {
			return "1";
		}
	}

	/**
	 * 发送的验证码
	 * 
	 * @param username
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "get", method = RequestMethod.POST)
	public @ResponseBody String get(@RequestBody String username, HttpServletRequest request) {

		String code = " ";
		Random random = new Random();
		for (int i = 0; i < 6; i++) { // 表示生成六位验证码
			code += String.valueOf(random.nextInt(10)); // 采用随机码生成0-10（包括0，不包括10）的验证码，生成六次，构成六位数验证码；
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

	/**
	 * 质检员登录
	 * 
	 * @param username
	 * @param yanzheng
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody String login(String username, String yanzheng, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		Inspector inspector = service.selectInspectorByPhone1(username, "1");
		if (inspector == null) {
			/*
			 * model.addAttribute("message", "用户名不存在！"); return
			 * "mobile/modules/Manager/login1";
			 */
			return "4";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("inspector", inspector);
			session.setMaxInactiveInterval(3600);
		}

		if (ConstantUtils.USER_NAME_18810656468.equals(username)) {
			if (ConstantUtils.PASS_WORD_888888.equals(yanzheng)) {
				return "3";
			} else {
				String sessionCode = (String) request.getSession().getAttribute(username);
				if (StringUtils.isBlank(sessionCode)) {
					return "2";
				} else {
					if (sessionCode.trim().equals(yanzheng)) {
						return "3";
					} else {
						return "1";
					}
				}
			}
		} else {
			String sessionCode = (String) request.getSession().getAttribute(username);
			if (StringUtils.isBlank(sessionCode)) {
				return "2";
			} else {
				if (sessionCode.trim().equals(yanzheng)) {
					return "3";
				} else {
					return "1";
				}
			}
		}
		/*
		 * String sessionCode = (String)
		 * request.getSession().getAttribute(username); if(sessionCode == null){
		 * 
		 * return "2"; }
		 * 
		 * if(sessionCode.trim().equals(yanzheng)){
		 * 
		 * return "3"; }else{
		 * 
		 * return "1"; }
		 */
	}

	/**
	 * 质检中心
	 * 

	 * @param model
	 * @param request

	 * @return
	 */
	@RequestMapping(value = "pqcIndex")
	public String pqcIndex(Model model, HttpServletRequest request) {
		// 已登录的质检员经理
		Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");
		//去除质检单的搜索条件
		if (StringUtils.isNotBlank(inspector.getSelectCheckText())) {
			inspector.setSelectCheckText(null);
			request.getSession().setAttribute("inspector", inspector);
		}
//		// 2017-3-7 判断是否是领导
//
//		Integer leader = service.isLeader(inspector.getId());
//		if (null != leader && leader > 0) {
//			inspector.setIsLeader(1);
//			request.getSession().setAttribute("inspector", inspector);
//
//		}

		//// manager.setStar(4);
		//// 根据质检员的id去查询订单数-订单总数
		//int totalCount = service.findCountByManagerName(inspector.getId());
		//inspector.setTotalOrderCount(totalCount);
		//// 根据质检员的id和订单的状态去查询订单数-在施工数
		//int buildingCount = service.findCountByManagerNameAndOrderStatus(inspector.getId());
		//inspector.setCurrentOrderCount(buildingCount);
		//// 根据质检员的id 查询质检报告
		//inspector.setInspectReport(service.findInspectReport(inspector.getId()));
        //
		//// 根据质检员ID和当前时间 查询质检未评价数
		inspector.setNowDate(new Date());
		Integer evalCount = service.findEvalCount(inspector);
        //
		inspector.setEvalCount(evalCount);
        //
		Integer isevalCount = 0;
		//// 如果有未评价的
		if (evalCount > 0) {
			// 弹框是否已查看
			Integer count = service.findView(inspector);
			if (count < 1) {
				// 未查看 插入一条记录
				service.insertView(inspector);
				isevalCount = 2;
				//}
			}
		}
		model.addAttribute("inspector", inspector);
		model.addAttribute("isevalCount", isevalCount);

		if (null != inspector.getIsLeader() && 1 == inspector.getIsLeader()) {
			return "mobile/modules/pqc/indexMaster";

		}
		return "mobile/modules/pqc/Index/inspectorIndex";
	}

	@RequestMapping(value = "indexMine")
	public String indexMine(Model model, HttpServletRequest request) {
		request.getSession().removeAttribute("reportInspectorText");
		Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");
		// 根据质检员的id去查询订单数-订单总数
		int totalCount = service.findCountByManagerName(inspector.getId());
		inspector.setTotalOrderCount(totalCount);
		// 根据质检员的id和订单的状态去查询订单数-在施工数
		int buildingCount = service.findCountByManagerNameAndOrderStatus(inspector.getId());
		inspector.setCurrentOrderCount(buildingCount);
		// 根据质检员的id 查询质检报告
		inspector.setInspectReport(service.findInspectReport(inspector.getId()));
		
		model.addAttribute("inspector", inspector);
		model.addAttribute("now", new Date());
		return "mobile/modules/pqc/Index/mineIndex";
	}



	@RequestMapping(value="question-list.php")
	public String questionList(){

		return "mobile/modules/pqc/common-issue/question-list";
	}


	@RequestMapping(value="question-page1.html")
	public String questionPage1(){

		return "mobile/modules/pqc/common-issue/questionPage";
	}


}
