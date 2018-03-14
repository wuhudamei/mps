package cn.damei.web.mobile.Worker;

import java.util.Enumeration;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.damei.common.constantUtils.phoneMessgeTypeConstant.PhoneMessageTypeConstant;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.service.modules.BizPhoneMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Worker.EmployeeGroup;
import cn.damei.service.mobile.Worker.EmployeeGroupService;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.service.mobile.Worker.WorkerService;

@Controller
@RequestMapping(value="${adminPath}/app/worker")
public class WorkerController {
	
	@Autowired
	private WorkerService workerService;
	@Autowired 
	private EmployeeGroupService employeeGroupService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	@RequestMapping(value="toLogin")
	public String toLogin(){
		
		return "mobile/modules/Worker/login";
	}
	
	@RequestMapping(value="toLogout")
	public String toLogout(HttpServletRequest request,HttpServletResponse respons){
		Enumeration<String> em = request.getSession().getAttributeNames();
		 while (em.hasMoreElements()) {
		   request.getSession().removeAttribute(em.nextElement().toString());
		 }
		request.getSession().invalidate();
		return "mobile/modules/Worker/login";
	}
	
	@RequestMapping(value="isExist",method=RequestMethod.POST)
	public @ResponseBody String isExist(@RequestBody String username,HttpServletRequest request){
		
		Worker worker = workerService.selectWorkerByPhone(username);
		//用户不存在
		if(worker==null){
			return "0";
		}else{
	        return "1";
		}
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public @ResponseBody String login(String username,String yanzheng,Model model,HttpServletRequest request,HttpServletResponse response ){
		
		Worker worker = workerService.selectWorkerByPhone(username);
		if(worker==null){
			/*model.addAttribute("message", "用户名不存在");
			return "mobile/modules/Worker/login";*/
			return "4";
		}else{
			HttpSession session = request.getSession();
	        session.setAttribute("worker", worker); 
	        session.setMaxInactiveInterval(3600);
		}
		
		//安装工
		if(worker.getWorktype().equals(4)){
			if(ConstantUtils.USER_NAME_13393163996.equals(username)){
				if(ConstantUtils.PASS_WORD_888888.equals(yanzheng)){
					return "6";
				}
			}
			
			String sessionCode = (String) request.getSession().getAttribute(username+"worker");
			if(StringUtils.isBlank(sessionCode)){
				return "5";
			}else{
				String[] split = sessionCode.split(",");
				StringBuffer sb = new StringBuffer();
				for (String string : split) {
					sb.append(string);
				}
				if(sb.toString().trim().equals(yanzheng)&& worker.getPhone().equals(username)){
					return "6";
				}else{
					return "1";
				}
			}
			
		}

		EmployeeGroup employeeGroup = employeeGroupService.selectEmployeeGroupByGroupId(worker.getId());
		if(employeeGroup != null){ // 组长
			if(ConstantUtils.USER_NAME_13393163996.equals(username)){
				if(ConstantUtils.PASS_WORD_888888.equals(yanzheng)){
					return "2";
				}else{
					String sessionCode = (String) request.getSession().getAttribute(username+"worker");
					if(StringUtils.isBlank(sessionCode)){
						return "5";
					}else{
						String[] split = sessionCode.split(",");
						StringBuffer sb = new StringBuffer();
						for (String string : split) {
							sb.append(string);
						}
						if(sb.toString().trim().equals(yanzheng)&& worker.getPhone().equals(username)){
							return "2";
						}else{
							return "1";
						}
					}
				}
			}else{
				String sessionCode = (String) request.getSession().getAttribute(username+"worker");
				if(StringUtils.isBlank(sessionCode)){
					return "5";
				}else{
					String[] split = sessionCode.split(",");
					StringBuffer sb = new StringBuffer();
					for (String string : split) {
						sb.append(string);
					}
					if(sb.toString().trim().equals(yanzheng)&& worker.getPhone().equals(username)){
						return "2";
					}else{
						return "1";
					}
				}
			}
		}else{ // 工人
			if(ConstantUtils.USER_NAME_13393163996.equals(username)){
				if(ConstantUtils.PASS_WORD_888888.equals(yanzheng)){
					return "3";
				}else{
					String sessionCode = (String) request.getSession().getAttribute(username+"worker");
					if(StringUtils.isBlank(sessionCode)){
						return "5";
					}else{
						String[] split = sessionCode.split(",");
						StringBuffer sb = new StringBuffer();
						for (String string : split) {
							sb.append(string);
						}
						if(sb.toString().trim().equals(yanzheng)&& worker.getPhone().equals(username)){
							return "3";
						}else{
							return "1";
						}
					}
				}
			}else{
				String sessionCode = (String) request.getSession().getAttribute(username+"worker");
				if(StringUtils.isBlank(sessionCode)){
					return "5";
				}else{
					String[] split = sessionCode.split(",");
					StringBuffer sb = new StringBuffer();
					for (String string : split) {
						sb.append(string);
					}
					if(sb.toString().trim().equals(yanzheng)&& worker.getPhone().equals(username)){
						return "3";
					}else{
						return "1";
					}
				}
			}
		}

		/*String sessionCode = (String) request.getSession().getAttribute(username+"worker");
		if(sessionCode ==null){
			return "5"; //请先获取验证码
		}
		//加上验证码是否为null的判断
		String[] split = sessionCode.split(",");
		StringBuffer sb = new StringBuffer();
		for (String string : split) {
			sb.append(string);
		}
		EmployeeGroup employeeGroup = employeeGroupService.selectEmployeeGroupByGroupId(worker.getId());
        if(employeeGroup != null){
        	//组长
        	if((sb.toString().trim().equals(yanzheng)&& worker.getPhone().equals(username)) || (ConstantUtils.USER_NAME_13393163996.equals(username) && ConstantUtils.PASS_WORD_888888.equals(yanzheng))){
        		*//*return "redirect:"+Global.getAdminPath()+"/app/worker/toindex";*//*
        		return "2";
        	}else{
        		*//*model.addAttribute("message","验证码错误");
    			return "mobile/modules/Worker/login";*//*
        		return "1";
        	}
        }else{
        	//工人
        	if((sb.toString().trim().equals(yanzheng)&& worker.getPhone().equals(username)) || (ConstantUtils.USER_NAME_13393163996.equals(username) && ConstantUtils.PASS_WORD_888888.equals(yanzheng))){
        		*//*return "redirect:"+Global.getAdminPath()+"/app/worker/toindex1";*//*
        		return "3";
        	}else{
        		*//*model.addAttribute("message","验证码错误");
    			return "mobile/modules/Worker/login";*//*
        		return "1";
        	}
        }*/
	}
	
	
	@RequestMapping(value="get",method=RequestMethod.POST)
	public @ResponseBody String get(HttpServletRequest request,@RequestBody String username ){
		String code = " ";
        Random random = new Random();
        for(int i=0; i<6; i++){    //表示生成六位验证码
        	code += String.valueOf(random.nextInt(10));   //  采用随机码生成0-10（包括0，不包括10）的验证码，生成六次，构成六位数验证码；
        }
        HttpSession session = request.getSession();
        session.setAttribute(username+"worker", code);

		String msgContent = "尊敬的用户，验证码为:"+code;
		bizPhoneMsgService.sendMessageHttps(PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_HTTPS,
				PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_USER_ID,
				PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_ACCOUNT,
				PhoneMessageTypeConstant.SEND_MESSAGE_INTERFACE_URL_PASSWORD,username,msgContent);

		return code;
	}
	
}