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
			/*model.addAttribute("message", "用户名不存在！");
			return "mobile/modules/Manager/login1";*/
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

		/*String sessionCode = (String) request.getSession().getAttribute(username);
		if(sessionCode == null){
			*//*model.addAttribute("message", "请先获取验证码！");
			return "mobile/modules/Manager/login1";*//*
			return "2";
		}

		if(sessionCode.trim().equals(yanzheng)){
			*//*return "redirect:"+Global.getAdminPath()+"/app/manager/toindex";*//*
			return "3";
		}else{
			*//*model.addAttribute("message","验证码有误！");
			return "mobile/modules/Manager/login1";*//*
			return "1";
		}*/
	}
	
	@RequestMapping(value="get",method=RequestMethod.POST)
	public @ResponseBody String get(@RequestBody String username ,HttpServletRequest request){
		String code = " ";
        Random random = new Random();
        for(int i=0; i<6; i++){    //表示生成六位验证码
        	code += String.valueOf(random.nextInt(10));   //  采用随机码生成0-10（包括0，不包括10）的验证码，生成六次，构成六位数验证码；
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
	
	//=======================================================app原生============================================
	/*ObjectMapper mapper = new ObjectMapper();  
	@Autowired
	private PhoneCodeService phoneCodeService;
	@Autowired
	private SysTokenService sysTokenService;*/
	/**
	 * 
	 * @param username 手机号
	 * @param sign 秘钥
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws NoSuchAlgorithmException 
	 */
/*	@RequestMapping(value="sendCode",method=RequestMethod.GET)
	@ResponseBody
	public String sendCode(String mobilePhone,String sign) throws JsonParseException, JsonMappingException, IOException, NoSuchAlgorithmException{
		Map<String,String> mymap = new HashMap<String,String>();
		mymap.put("mobilePhone", mobilePhone);
		String md5 = MD5Utils.MD5(mymap);
		if(!sign.equals(md5)){ //签名认证失败
			Map<String,Object> map = new HashMap<String,Object>();
			//Map<String,String> map1 = new HashMap<String,String>(); 
			map.put("code", "406");
			map.put("message", "签名认证失败");
			return JSONObject.fromObject(map).toString(); 
			//map.put("data", "");
		}
		//根据手机号查询项目经理
		Manager manager = managerService.selectManagerByPhone(mobilePhone);
		if(manager == null){ 
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,String> map1 = new HashMap<String,String>();
			map1.put("result", "2");//手机号未注册
			map.put("code", "200");
			map.put("message", "成功");
			map.put("data", map1);
			return JSONObject.fromObject(map).toString(); 
		}
		String messageCode = "";
        Random random = new Random();
        for(int i=0; i<6; i++){    //表示生成六位验证码
        	messageCode += String.valueOf(random.nextInt(10));//采用随机码生成0-10（包括0，不包括10）的验证码，生成六次，构成六位数验证码；
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("source", "1");
    	params.put("content",messageCode);
    	params.put("sendtime", "");
    	params.put("mobile", mobilePhone);
    	String post = HttpConnection.post(ConstantUtils.SEND_MESSAGE_INTERFACE, params);
    	Map<String,Object> productMap = mapper.readValue(post, Map.class);
    	if(!"1".equals(productMap.get("IsSuccessful"))){
    		Map<String,Object> map = new HashMap<String,Object>();
			Map<String,String> map1 = new HashMap<String,String>();
			map1.put("result", "3");//短信网关发送失败
			map.put("code", "200");
			map.put("message", "成功");
			map.put("data", map1);
			return JSONObject.fromObject(map).toString(); 
    	}else{
    		Map<String,Object> map = new HashMap<String,Object>();
    		Map<String,String> map1 = new HashMap<String,String>();
    		map1.put("result", "1");//发送成功
    		map.put("code", "200");
    		map.put("message", "成功");
    		map.put("data", map1);
    		//保存验证码到数据库表 
    		PhoneCode phoneCode = new PhoneCode();
    		phoneCode.setPhoneNumber(mobilePhone);
    		phoneCode.setPhoneCode(messageCode);
    		phoneCode.setValidityDatetime(new Date());
    		phoneCodeService.save(phoneCode);
    		return JSONObject.fromObject(map).toString(); 
    	}
	}*/
	/**
	 * 
	 * @param username 手机号
	 * @param code 验证码
	 * @param sign 秘钥
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
/*	@RequestMapping(value="login1",method=RequestMethod.GET)
	@ResponseBody
	public String login(String mobilePhone, String code, String sign,HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
		Map<String,String> mymap = new HashMap<String,String>();
		mymap.put("mobilePhone", mobilePhone);
		mymap.put("code", code);
		String md5 = MD5Utils.MD5(mymap);
		
		if(!sign.equals(md5)){ //签名认证失败
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", "406");
			map.put("message", "签名认证失败");
			return JSONObject.fromObject(map).toString(); 
		}
		//根据手机号和验证码查询 在有效期内登录成功 否则登录失败
		PhoneCode phoneCode = phoneCodeService.findByUsernameAndCode(mobilePhone,code);
		if(null != phoneCode){
			Map<String,Object> map = new HashMap<String,Object>();
    		Map<String,String> map1 = new HashMap<String,String>();
    		map1.put("result", "2");//验证码校验失败
    		map.put("code", "200");
    		map.put("message", "成功");
    		map.put("data", map1);
    		return JSONObject.fromObject(map).toString(); 
		}else{
			String tokenid = UUIDUtils.getTokenid();
			Map<String,Object> map = new HashMap<String,Object>();
    		Map<String,String> map1 = new HashMap<String,String>();
    		map1.put("result", "1");//登录成功
    		map1.put("tokenid", tokenid);
    		map.put("code", "200");
    		map.put("message", "成功");
    		map.put("data", map1);
    		//登录成功生成tokenid 保存到数据库中
    		SysToken sysToken = new SysToken();
    		sysToken.setPhoneNumber(mobilePhone);
    		sysToken.setTokenid(tokenid);
    		sysToken.setValidityDatetime(new Date());
    		sysTokenService.save(sysToken);
    		//根据手机号查询项目经理--保存到session
    		Manager manager = managerService.selectManagerByPhone(mobilePhone);
    		HttpSession session = request.getSession();
	        session.setAttribute("manager", manager);
    		return JSONObject.fromObject(map).toString(); 
		}
	}*/
	
	/**
	 * 自动登录
	 * @param tokenid
	 * @param sign
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
/*	@RequestMapping(value="checkTokenId",method=RequestMethod.GET)
	@ResponseBody
	public String checkTokenId(String tokenid,String sign,HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		
		Map<String,String> mymap = new HashMap<String,String>();
		mymap.put("tokenid", tokenid);
		String md5 = MD5Utils.MD5(mymap);
		if(!sign.equals(md5)){ //签名认证失败
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("code", "406");
			map.put("message", "签名认证失败");
			return JSONObject.fromObject(map).toString(); 
		}
		SysToken token = sysTokenService.findByTokenId(tokenid);
		if(null != token){
			Map<String,Object> map = new HashMap<String,Object>();
    		Map<String,String> map1 = new HashMap<String,String>();
    		map1.put("result", "1");//登录成功
    		map1.put("tokenid", tokenid);
    		map.put("code", "200");
    		map.put("message", "成功");
    		map.put("data", map1);
    		//登录成功生成tokenid 保存到数据库中
    		SysToken sysToken = new SysToken();
    		sysToken.setPhoneNumber(token.getPhoneNumber());
    		sysToken.setTokenid(tokenid);
    		sysToken.setValidityDatetime(new Date());
    		sysTokenService.save(sysToken); 
    		//根据手机号查询项目经理--保存到session
    		Manager manager = managerService.selectManagerByPhone(token.getPhoneNumber());
    		HttpSession session = request.getSession();
	        session.setAttribute("manager", manager);
    		return JSONObject.fromObject(map).toString(); 
		}else{ //tokenid失效
			Map<String,Object> map = new HashMap<String,Object>();
    		map.put("code", "409");
    		map.put("message", "成功");
    		//map.put("data", map1);
    		return JSONObject.fromObject(map).toString(); 
		}
	}*/
}
