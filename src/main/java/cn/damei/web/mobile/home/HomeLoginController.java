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

	}
	@RequestMapping(value="toLogin2")
	public String toLogin2(Model model,HttpServletRequest request){
		
		return "mobile/modules/home/login2";

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



		hashMap.put("businessType","555");
		hashMap.put("list",projectProgressCount.size()>0?projectProgressCount:null);
		int i=0;
		if(null!=phone){

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
			if("wechat".equals(client)){
				return "1";
			}else{
				model.addAttribute("client", client);
				return "2";
			}
		}else{
			return "3";
		}
	}
	


	
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



			hashMap.put("businessType","555");
			hashMap.put("list",projectProgressCount.size()>0?projectProgressCount:null);

			model.addAttribute("findProjectProgressCountByCustomerPhone", projectProgressCount.size()-service.commonViewLogCountByBusinessIntId(hashMap) );



		}
		String client = request.getParameter("client");
		if("wechat".equals(client)){
			request.getSession().setAttribute("client", "wechat");
		}
		
		return "mobile/modules/home/mdniIndex";
	}

}
