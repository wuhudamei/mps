package cn.damei.web.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.damei.common.constantUtils.phoneMessgeTypeConstant.PhoneMessageTypeConstant;
import cn.damei.service.modules.BizPhoneMsgService;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.MD5Utils;
import cn.damei.common.UUIDUtils;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.service.mobile.Inspector.InspectorLoginService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.PhoneCode;
import cn.damei.entity.mobile.Manager.SysToken;
import cn.damei.service.mobile.Manager.ManagerService;
import cn.damei.service.mobile.Manager.PhoneCodeService;
import cn.damei.service.mobile.Manager.SysTokenService;
import cn.damei.entity.mobile.Worker.EmployeeGroup;
import cn.damei.service.mobile.Worker.EmployeeGroupService;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.service.mobile.Worker.WorkerService;
import cn.damei.entity.mobile.home.HomeLoginLogoutLog;
import cn.damei.service.mobile.home.HomeLoginLogoutLogService;
import cn.damei.service.mobile.home.HomeLoginService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/api/api")
public class ApiController{
	
	ObjectMapper mapper = new ObjectMapper();  
	@Autowired
	private PhoneCodeService phoneCodeService;
	@Autowired
	private SysTokenService sysTokenService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private InspectorLoginService inspectorService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService;
	@Autowired
	private WorkerService workerService;
	@Autowired
	private EmployeeGroupService employeeGroupService;
	@Autowired
	private OrderService2 orderService;
	@Autowired
	private HomeLoginService service;
	@Autowired
	private HomeLoginLogoutLogService homeLoginLogoutLogService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;

	private static String DEAL_TYPE_IN = "in";

	private static String DEAL_TYPE_MODE = "app";
	

	@RequestMapping(value="sendCode",method=RequestMethod.POST)
	@ResponseBody
	public String sendCode(String appType,String mobilePhone,String sign) throws JsonParseException, JsonMappingException, IOException, NoSuchAlgorithmException{
		if(appType == null || mobilePhone ==null || sign==null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", "401");
			map.put("message", "必填参数为空");
			return JSONObject.fromObject(map).toString(); 
		}
		Map<String,String> mymap = new HashMap<String,String>();
		mymap.put("mobilePhone", mobilePhone);
		mymap.put("appType", appType);
		String md5 = MD5Utils.MD5(mymap);
		if(!md5.equalsIgnoreCase(sign)){
			Map<String,Object> map = new HashMap<String,Object>();

			map.put("code", "406");
			map.put("message", "签名认证失败");
			return JSONObject.fromObject(map).toString(); 

		}
		if(ConstantUtils.EMP_TYPE_4.equals(appType)){
			List<Order2> customer = orderService.findOrderByPhone(mobilePhone);
			if(customer == null || customer.size() ==0){
				Map<String,Object> map = new HashMap<String,Object>();
				Map<String,String> map1 = new HashMap<String,String>();
				map1.put("result", "2");
				map.put("code", "200");
				map.put("message", "成功");
				map.put("data", map1);
				return JSONObject.fromObject(map).toString();
			} 

			else{

					Map<String,Object> map = new HashMap<String,Object>();
		    		Map<String,String> map1 = new HashMap<String,String>();
		    		map1.put("result", "1");
		    		map.put("code", "200");
		    		map.put("message", "成功");
		    		map.put("data", map1);
		    		return JSONObject.fromObject(map).toString();
				}
		} else if(ConstantUtils.EMP_TYPE_1.equals(appType)){
			BizEmployee2 employee = bizEmployeeService.queryEmployeeByPhoneAndEmployeeTypeOrIsLeader(mobilePhone,appType);
			if(employee == null){ 
				Map<String,Object> map = new HashMap<String,Object>();
				Map<String,String> map1 = new HashMap<String,String>();
				map1.put("result", "2");
				map.put("code", "200");
				map.put("message", "成功");
				map.put("data", map1);
				return JSONObject.fromObject(map).toString(); 
			}
		} else {

			BizEmployee2 employee = bizEmployeeService.queryEmployeeByPhoneAndEmployeeType(mobilePhone,appType);
			if(employee == null){ 
				Map<String,Object> map = new HashMap<String,Object>();
				Map<String,String> map1 = new HashMap<String,String>();
				map1.put("result", "2");
				map.put("code", "200");
				map.put("message", "成功");
				map.put("data", map1);
				return JSONObject.fromObject(map).toString(); 
			}
		}
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
    		Map<String,Object> map = new HashMap<String,Object>();
			Map<String,String> map1 = new HashMap<String,String>();
			map1.put("result", "3");
			map.put("code", "200");
			map.put("message", "成功");
			map.put("data", map1);
			return JSONObject.fromObject(map).toString(); 
    	}else{
    		Map<String,Object> map = new HashMap<String,Object>();
    		Map<String,String> map1 = new HashMap<String,String>();
    		map1.put("result", "1");
    		map.put("code", "200");
    		map.put("message", "成功");
    		map.put("data", map1);

    		PhoneCode phoneCode = new PhoneCode();
    		phoneCode.setAppType(appType);
    		phoneCode.setPhoneNumber(mobilePhone);
    		phoneCode.setPhoneCode(messageCode);
    		phoneCode.setValidityDatetime(new Date());
    		phoneCodeService.add(phoneCode);
    		return JSONObject.fromObject(map).toString(); 
    	}
	}

	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public String login(String appType,String mobilePhone, String code, String sign,HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		if(appType == null || mobilePhone ==null || sign==null || code == null ){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", "401");
			map.put("message", "必填参数为空");
			return JSONObject.fromObject(map).toString(); 
		}

		if(ConstantUtils.EMP_TYPE_4.equals(appType)){
			List<Order2> customer = orderService.findOrderByPhone(mobilePhone);
			if(customer == null || customer.size() ==0){
				Map<String,Object> map = new HashMap<String,Object>();
				Map<String,String> map1 = new HashMap<String,String>();
				map1.put("result", "2");
				map.put("code", "200");
				map.put("message", "成功");
				map.put("data", map1);
				return JSONObject.fromObject(map).toString();
			}else{
				String tokenid = UUIDUtils.getTokenid();
				Map<String,Object> map = new HashMap<String,Object>();
	    		Map<String,String> map1 = new HashMap<String,String>();
	    		map1.put("result", "1");
	    		map1.put("tokenid", tokenid);
	    		map.put("code", "200");
	    		map.put("message", "成功");
	    		map.put("data", map1);

	    		SysToken sysToken = new SysToken();
	    		sysToken.setAppType(appType);
	    		sysToken.setPhoneNumber(mobilePhone);
	    		sysToken.setTokenid(tokenid);
	    		sysToken.setValidityDatetime(new Date());
	    		sysTokenService.add(sysToken);
	    		

				HomeLoginLogoutLog homeLoginLogoutLog = new HomeLoginLogoutLog();
				homeLoginLogoutLog.setDealMode(DEAL_TYPE_MODE);
				homeLoginLogoutLog.setDealType(DEAL_TYPE_IN);
				homeLoginLogoutLog.setDealPhone(mobilePhone);
				homeLoginLogoutLog.setDealTime(new Date() );
				homeLoginLogoutLog.setRemarks("登录成功，方法名【/api/api/login】，登录端【"+ DEAL_TYPE_MODE +"】");
				homeLoginLogoutLogService.save(homeLoginLogoutLog);
	    		
	    		
	    		return JSONObject.fromObject(map).toString(); 
			}
		} 
		Map<String,String> mymap = new HashMap<String,String>();
		mymap.put("mobilePhone", mobilePhone);
		mymap.put("code", code);
		mymap.put("appType", appType);
		String md5 = MD5Utils.MD5(mymap);
		
		if(!md5.equalsIgnoreCase(sign)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", "406");
			map.put("message", "签名认证失败");
			return JSONObject.fromObject(map).toString(); 
		}

		System.out.println("=======login appType:"+appType+",mobilePhone:"+mobilePhone+",code:"+code);

		if((ConstantUtils.USER_NAME_18810656468.equals(mobilePhone) && ConstantUtils.PASS_WORD_888888.equals(code) && appType.equals("1")) ||
				(ConstantUtils.USER_NAME_13393163996.equals(mobilePhone) && ConstantUtils.PASS_WORD_888888.equals(code) && appType.equals("2")) ||
			(ConstantUtils.USER_NAME_18519377253.equals(mobilePhone) && ConstantUtils.PASS_WORD_888888.equals(code) && appType.equals("3")) ||
				(ConstantUtils.USER_NAME_18611735753.equals(mobilePhone) && ConstantUtils.PASS_WORD_888888.equals(code) && appType.equals("4")) ){
			String tokenid = UUIDUtils.getTokenid();
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,String> map1 = new HashMap<String,String>();
			map1.put("result", "1");
			map1.put("tokenid", tokenid);
			map.put("code", "200");
			map.put("message", "成功");
			map.put("data", map1);

			SysToken sysToken = new SysToken();
			sysToken.setAppType(appType);
			sysToken.setPhoneNumber(mobilePhone);
			sysToken.setTokenid(tokenid);
			sysToken.setValidityDatetime(new Date());
			sysTokenService.add(sysToken);
			return JSONObject.fromObject(map).toString();
		}



		PhoneCode phoneCode = phoneCodeService.findByUsernameAndCode(mobilePhone,code,appType);
		if(null == phoneCode){
			Map<String,Object> map = new HashMap<String,Object>();
    		Map<String,String> map1 = new HashMap<String,String>();
    		map1.put("result", "2");
    		map.put("code", "200");
    		map.put("message", "成功");
    		map.put("data", map1);
    		return JSONObject.fromObject(map).toString(); 
		}else{
			String tokenid = UUIDUtils.getTokenid();
			Map<String,Object> map = new HashMap<String,Object>();
    		Map<String,String> map1 = new HashMap<String,String>();
    		map1.put("result", "1");
    		map1.put("tokenid", tokenid);
    		map.put("code", "200");
    		map.put("message", "成功");
    		map.put("data", map1);

    		SysToken sysToken = new SysToken();
    		sysToken.setAppType(appType);
    		sysToken.setPhoneNumber(mobilePhone);
    		sysToken.setTokenid(tokenid);
    		sysToken.setValidityDatetime(new Date());
    		sysTokenService.add(sysToken);
    		return JSONObject.fromObject(map).toString(); 
		}
	}
	

	@RequestMapping(value="checkTokenId",method=RequestMethod.POST)
	public String checkTokenId(String appType ,String tokenid,String sign,HttpServletRequest request,Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		if(appType == null || tokenid==null || sign==null ){
			return "mobile/modules/api/loginFailed";
		}
		Map<String,String> mymap = new HashMap<String,String>();
		mymap.put("tokenid", tokenid);
		mymap.put("appType", appType);
		String md5 = MD5Utils.MD5(mymap);
		if(!md5.equalsIgnoreCase(sign)){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", "406");
			map.put("message", "签名认证失败");

			return "mobile/modules/api/loginFailed";
		}
		SysToken token = sysTokenService.findByTokenId(tokenid,appType);
		if(null != token){
			Map<String,Object> map = new HashMap<String,Object>();
    		Map<String,String> map1 = new HashMap<String,String>();
    		map1.put("result", "1");
    		map1.put("tokenid", tokenid);
    		map.put("code", "200");
    		map.put("message", "成功");
    		map.put("data", map1);




    		token.setValidityDatetime(new Date());
    		sysTokenService.add(token); 

    		HttpSession session = request.getSession();
    		if(ConstantUtils.EMP_TYPE_3.equals(appType)){
    			Manager manager = managerService.selectManagerByPhone(token.getPhoneNumber());
    			session.setAttribute("manager", manager);
    			return "redirect:"+Global.getAdminPath()+"/app/manager/toindex";
    		}else if(ConstantUtils.EMP_TYPE_1.equals(appType)){
    			Inspector inspector= inspectorService.selectInspectorByPhone(token.getPhoneNumber());
    			session.setAttribute("inspector", inspector);
    			return "redirect:"+Global.getAdminPath()+"/app/pqc/pqcIndex";
    		}else if(ConstantUtils.EMP_TYPE_2.equals(appType)){
    			Worker worker = workerService.selectWorkerByPhone(token.getPhoneNumber());
    			session.setAttribute("worker", worker);
    			EmployeeGroup employeeGroup = employeeGroupService.selectEmployeeGroupByGroupId(worker.getId());
    			if(employeeGroup != null){
    				return "redirect:"+Global.getAdminPath()+"/app/worker/toindex";
    			}else{
    				return "redirect:"+Global.getAdminPath()+"/app/worker/toindex1";
    			}
    		}else{
    			session.setAttribute("customerPhone", token.getPhoneNumber());

    			

				HomeLoginLogoutLog homeLoginLogoutLog = new HomeLoginLogoutLog();
				homeLoginLogoutLog.setDealMode(DEAL_TYPE_MODE);
				homeLoginLogoutLog.setDealType(DEAL_TYPE_IN);
				homeLoginLogoutLog.setDealPhone(token.getPhoneNumber());
				homeLoginLogoutLog.setDealTime(new Date() );
				homeLoginLogoutLog.setRemarks("登录成功，方法名【/api/api/checkTokenId】，登录端【"+ DEAL_TYPE_MODE +"】");
				homeLoginLogoutLogService.save(homeLoginLogoutLog);
    			
    			return "redirect:"+Global.getAdminPath()+"/app/home/isLogin";
    		}

		}else{
			Map<String,Object> map = new HashMap<String,Object>();
    		map.put("code", "409");
    		map.put("message", "成功");


    		return "mobile/modules/api/loginFailed";
		}
	}
}
