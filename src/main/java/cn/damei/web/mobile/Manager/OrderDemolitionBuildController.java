package cn.damei.web.mobile.Manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.entity.mobile.Manager.OrderDemolitionBuild;
import cn.damei.service.mobile.Manager.OrderDemolitionBuildService;
import cn.damei.entity.mobile.Manager.SignDetail;
import cn.damei.entity.modules.Order;
/** 
* @ClassName: OrderDemolitionBuildController 
* @Description: 拆改交底 
* @author zkj  
* @date 2017年10月19日 下午1:58:46 
* @version V1.0 
*/
@Controller
@RequestMapping("/mobile/modules/manager/orderDemolitionBuild/web/orderDemolitionBuild")
public class OrderDemolitionBuildController {

	@Autowired
	private OrderDemolitionBuildService orderDemolitionBuildService;
	/** 
	* @Description: 项目经理端拆改列表查询
	* @param @param request
	* @param @param model
	* @param @return
	* @author zkj 
	* @date 2017年10月19日 下午4:57:04 
	*/
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request,Model model,String flag,String isDisclose){
		List<Order> list = orderDemolitionBuildService.findOrderDemolitionBuildList(request);
		model.addAttribute("list", list);
		model.addAttribute("flag", flag);
		model.addAttribute("isDisclose", isDisclose);
		return "/mobile/modules/Manager/orderDemolitionBuild/orderDemolitionBuildList";
	}
	
	/** 
	* @Description: 现场签到 
	* @param @param request
	* @param @param model
	* @param @return
	* @author zkj 
	* @date 2017年10月19日 下午5:01:24 
	*/
	@RequestMapping(value="/sceneSign")
	@ResponseBody
	public String sceneSign(HttpServletRequest request,Model model,SignDetail signDetail){
		//返回签到标识
		String sceneSign = orderDemolitionBuildService.sceneSign(signDetail,request);
		return sceneSign;
	}
	
	/** 
	* @Description: 交底
	* @param @return
	* @author zkj
	* @date 2017年10月19日 下午6:54:38 
	*/
	@RequestMapping(value="/disclose")
	@ResponseBody
	public String disclose(HttpServletRequest request,Model model,SignDetail signDetail){
		//返回交底标识
		String disclose = orderDemolitionBuildService.disclose(signDetail,request);
		return disclose;
	}
	
	/** 
	* @Description:提交交底页面 
	* @param @param request
	* @param @return
	* @author zkj 
	* @date 2017年10月20日 上午10:36:30 
	*/
	@RequestMapping(value="/form")
	public String form(HttpServletRequest request,SignDetail signDetail,Model model){
		//查询订单的签到时间
		signDetail = orderDemolitionBuildService.findOrderSignDatetime(signDetail,request);
		model.addAttribute("signDetail", signDetail);
		return "/mobile/modules/Manager/orderDemolitionBuild/orderDemolitionBuildForm";
	}
	
	
	/** 
	* @Description: 保存交底
	* @param @return
	* @author zkj 
	* @date 2017年10月20日 下午3:12:26 
	*/
	@RequestMapping(value="/save")
	public String save(HttpServletRequest request,OrderDemolitionBuild orderDemolitionBuild,Model model){
		String saveDisclose = orderDemolitionBuildService.saveDisclose(orderDemolitionBuild,request);
		return "forward:"+Global.getRootPath()+"/mobile/modules/manager/orderDemolitionBuild/web/orderDemolitionBuild/list?flag=1&isDisclose="+saveDisclose;
		
	}
	
	
}
