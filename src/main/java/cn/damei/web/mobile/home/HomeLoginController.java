package cn.damei.web.mobile.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.service.mobile.home.ProjectProgressService;
import cn.damei.entity.mobile.home.ProjectProgressVo;
import cn.damei.service.mobile.home.HomeLoginService;
import cn.damei.service.mobile.home.WeiChatOpenIdService;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月24日 下午3:47:13 
* 质检登录系统首页
*/
@Controller
@RequestMapping(value="${adminPath}/app/home")
public class HomeLoginController {
	
	@Autowired
	private HomeLoginService service;
	@Autowired
	private OrderService2 orderService;
	@Autowired
	private WeiChatOpenIdService weiChatOpenIdService;
	@Autowired
	private ProjectProgressService projectProgressService;

	public Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@RequestMapping(value="toLogin1")
	public String toLogin1(Model model,HttpServletRequest request){
		
		return "mobile/modules/home/login1";
		//return "mobile/modules/home/weichat/weiChatLogin";
	}
	@RequestMapping(value="toLogin2")
	public String toLogin2(Model model,HttpServletRequest request){
		
		return "mobile/modules/home/login2";
		//return "mobile/modules/home/weichat/weiChatLogin";
	}
	@RequestMapping(value="login1")
	public @ResponseBody String login1(String username,Model model,HttpServletRequest request){
		
		List<Order2> customer = orderService.findOrderByPhone(username);
		if(null != customer && customer.size()>0){
			HttpSession session = request.getSession();
	        session.setAttribute("customerPhone", username);
			return "success";
		}else{
			return "error";
		}
	}
	
	@RequestMapping(value="isLogin")
	public String isLogin(Model model, HttpServletRequest request){
		
		Map<String,Object> hashMap = new HashMap<>();
		List<String> list = new ArrayList<>();
//		String phone = (String) request.getSession().getAttribute("customerPhone");
//	request.getSession().setAttribute("customerPhone", "");
		String phone = (String) request.getSession().getAttribute("customerPhone");
		model.addAttribute("customerPhone",phone);
		List<Map<String,String>> mapList = service.findUnReadReportCountByCustomerPhone(phone);
		if(mapList.size()>0){
		model.addAttribute("customerName",mapList.get(0).get("customerName"));
			Iterator iterator =  mapList.iterator();
			while (iterator.hasNext()){
				Map<Object,Object> map =(Map)iterator.next();
				list.add(map.get("businessId").toString());
			}
		}
		List<Integer>  progressCountByCustomerPhoneCount= service.findProgressCountByCustomerPhone(phone);
		List<Integer>   changeCount= service.findProjectChangeCountByCustomerPhone(phone);
		List<Integer>   projectProgressCount= service.findProjectProgressCountByCustomerPhone(phone);




		hashMap.put("businessType","3");
		hashMap.put("list",list.size()>0?list:null);
		model.addAttribute("findUnReadReportCountByCustomerPhone",mapList.size()-service.commonViewLogCountByBusinessIntId(hashMap) );

		hashMap.put("businessType","4");
		hashMap.put("list",progressCountByCustomerPhoneCount.size()>0?progressCountByCustomerPhoneCount:null);
		model.addAttribute("findProgressCountByCustomerPhone", progressCountByCustomerPhoneCount.size()-service.commonViewLogCountByBusinessIntId(hashMap) );

		hashMap.put("businessType","5");
		hashMap.put("list",changeCount.size()>0?changeCount: null);
		model.addAttribute("findProjectChangeCountByCustomerPhone", changeCount.size()-service.commonViewLogCountByBusinessIntId(hashMap) );
//			model.addAttribute("findEvalCountByCustomerPhone", service.findEvalCountByCustomerPhone(phone));


		hashMap.put("businessType","555");
		hashMap.put("list",projectProgressCount.size()>0?projectProgressCount:null);
		int i=0;
		if(null!=phone){
			//查询该客户的订单 //统计详情有的个数
			List<ProjectProgressVo> projectProgressList = projectProgressService.getOrderListByCustomerPhone(phone);
			if(list.size()>0) {
				ProjectProgressVo vo = new ProjectProgressVo();
				for (ProjectProgressVo projectProgressVo:projectProgressList) {
					vo.setOrderId(projectProgressVo.getOrderId());
					ProjectProgressVo progressVo = projectProgressService.getConfirmStartInfoByOrderId(vo);
					if(progressVo!=null){
						i++;
					}
				}
			}
			
		}

		model.addAttribute("findProjectProgressCountByCustomerPhone", i-service.commonViewLogCountByBusinessIntId(hashMap) );
		
		return "mobile/modules/home/myHome";
	}
	
	@RequestMapping(value="toweichat")
	public String toweichat(Model model,HttpServletRequest request){
		request.getSession().setAttribute("client","wechat");
		return "mobile/modules/home/weichat/weiChatLogin";
	}
	@RequestMapping(value="toweichat2")
	public String toweichat2(Model model,HttpServletRequest request){
		return "mobile/modules/home/weichat/weiChatLogin2";
	}
	@RequestMapping(value="toMyHome")
	@ResponseBody
	public String toMyHome(Model model,HttpServletRequest request){
		String phone = (String) request.getSession().getAttribute("customerPhone");
		String client = (String) request.getSession().getAttribute("client");
		
		if(phone == null){
			if("wechat".equals(client)){ //微信跳转到登录页面
				return "1";
			}else{//app调用原生
				model.addAttribute("client", client);
				return "2";
			}
		}else{//session存在已登录 直接到我的页面
			return "3";
		}
	}
	
	/*@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request){
		String client = (String) request.getParameter("client"); 
		String client1 = (String)request.getSession().getAttribute("client");
		if("wechat".equals(client) || "wechat".equals(client1)){
			request.getSession().setAttribute("client", client);
			String code = (String) request.getAttribute("code");
			Map<String,Object> map = WeichatUtils.getOpenId(WeichatUtils.URL, WeichatUtils.APP_ID, WeichatUtils.APP_SECRET, code, WeichatUtils.GRANT_AYPE);
			if(map != null){
				if(map.containsKey("errcode")){
					return "";//跳转到错误页面
				}else{
					String access_token = (String) map.get("access_token");
					String openid = (String) map.get("openid");
					//根据openid查询数据库 判断是否存在
					WeiChatOpenId openId = weiChatOpenIdService.findByOpendId(openid);
					if(openId != null){
						request.getSession().setAttribute("customerPhone",openId.getPhone());
					}else{
						request.getSession().setAttribute("openid", openid);
						request.getSession().setAttribute("access_token", access_token);
					}
					model.addAttribute("client", "aaaaa"+client);//调试 ---------------------
					return "mobile/modules/home/mdniIndex";//跳转到首页
				}
			}else{
				return "mobile/modules/home/no_log";//错误页面
				return "";
			}
		}else{
			model.addAttribute("client", "bbbbb"+client);//调试 -----------------------------
			return "mobile/modules/home/mdniIndex";
		}
	}*/
/*	@RequestMapping(value="authorization")
	public void authorization(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String redirect_uri = URLEncoder.encode("http://mpst.mdni.cn/a/app/home/index", "utf-8");
		String response_type = "code";
		String scope = "snsapi_userinfo";
		String state = "123";
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxbd2b4cb23a053488&redirect_uri="+redirect_uri
				   + "&response_type="+response_type
				   + "&scope="+scope+"&state="+state+"#wechat_redirect";
		System.out.println(url);
		response.sendRedirect(url);
		System.out.println("===========================================================================================");
	}*/
	
	@RequestMapping(value="index")
	public String index(Model model,HttpServletRequest request){
		
		String islogin = request.getParameter("isLogin");
		Map<String,Object> hashMap = new HashMap<>();
		List<String> list = new ArrayList<>();
		if("success".equals(islogin)){
			String phone = (String) request.getSession().getAttribute("customerPhone");
			List<Map<String,String>> maps = service.findUnReadReportCountByCustomerPhone(phone);
			if(maps.size()>0){



				model.addAttribute("customerName",maps.get(0).get("customerName"));
				Iterator iterator =  maps.iterator();
				while (iterator.hasNext()){
					Map<String,String> map =(Map)iterator.next();
					list.add(map.get("businessId"));
				}
			}
			List<Integer>  progressCountByCustomerPhoneCount= service.findProgressCountByCustomerPhone(phone);
			List<Integer>   changeCount= service.findProjectChangeCountByCustomerPhone(phone);
			List<Integer>   projectProgressCount= service.findProjectProgressCountByCustomerPhone(phone);




			hashMap.put("businessType","3");
			hashMap.put("list",list.size()>0?list:null);
			model.addAttribute("findUnReadReportCountByCustomerPhone",maps.size()-service.commonViewLogCountByBusinessIntId(hashMap) );

			hashMap.put("businessType","4");
			hashMap.put("list",progressCountByCustomerPhoneCount.size()>0?progressCountByCustomerPhoneCount:null);
			model.addAttribute("findProgressCountByCustomerPhone", progressCountByCustomerPhoneCount.size()-service.commonViewLogCountByBusinessIntId(hashMap) );

			hashMap.put("businessType","5");
			hashMap.put("list",changeCount.size()>0?changeCount: null);
			model.addAttribute("findProjectChangeCountByCustomerPhone", changeCount.size()-service.commonViewLogCountByBusinessIntId(hashMap) );
//			model.addAttribute("findEvalCountByCustomerPhone", service.findEvalCountByCustomerPhone(phone));


			hashMap.put("businessType","555");
			hashMap.put("list",projectProgressCount.size()>0?projectProgressCount:null);

			model.addAttribute("findProjectProgressCountByCustomerPhone", projectProgressCount.size()-service.commonViewLogCountByBusinessIntId(hashMap) );


			//return "redirect:"+Global.getAdminPath()+"/app/home/isLogin";
		}
		String client = request.getParameter("client");
		if("wechat".equals(client)){//说明是微信端
			request.getSession().setAttribute("client", "wechat");
		}
		
		return "mobile/modules/home/mdniIndex";
	}

}
