package cn.damei.web.mobile.home;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.home.BizOrder;
import cn.damei.entity.mobile.home.BizProjectChangeBill;
import cn.damei.service.mobile.home.NewProjectChangeService;
/**
 * 施工变更单
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/app/home/NewApplyProjectChange")
public class NewProjectChangeController {
	
	@Autowired
	private NewProjectChangeService newProjectChangeService;
	
	/**
	 * 施工变更列表页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list")
	public String list(Integer orderId,HttpServletRequest request, Model model) {
		
		
		String customerPhone = (String) request.getSession().getAttribute("customerPhone");
		
		//查询施工变更单列表
		BizOrder order = newProjectChangeService.findProjectChangeBillList(customerPhone,orderId);
		
		if(null!=order){
			List<BizProjectChangeBill> projectChangeBillList = order.getProjectChangeList();
			String exists ="0";
			if(null!=projectChangeBillList && projectChangeBillList.size()>0){
				for(BizProjectChangeBill a :projectChangeBillList){
					if(null!=a.getProjectChangeId()){
						exists="1";
					}
				}
			}
			model.addAttribute("exists",exists);
			model.addAttribute("order", order);
		}
		
		//查询订单列表
		List<BizOrder> list = newProjectChangeService.findOrderList(customerPhone);
		if(null!=list && list.size()>0){
			if(list.size()>1){
				model.addAttribute("list", list);
			}
			model.addAttribute("ordersLength",list.size());
		}else{
			return "mobile/modules/home/projectChange/changeConstruction_null";
		}
		
		return "mobile/modules/home/projectChange/changeConstruction";
	}
	
	/**
	 * 变更单详情
	 * @param model
	 * @param qcBillId
	 * @return
	 */
	@RequestMapping(value = "details")
	public String details(Model model, Integer projectChangeId) {

//		//查看消息是否已读
//		Integer count = newProjectChangeService.findView(projectChangeId);
//		if(count==0){
//			//如果未读则插入已读信息
//			newProjectChangeService.insertView(projectChangeId);
//		}
		
		//变更单详情
		BizProjectChangeBill projectChange = newProjectChangeService.projectChangeDetail(projectChangeId);
		if(null!=projectChange){
			model.addAttribute("projectChange", projectChange);
		}else{
			return "mobile/modules/home/projectChange/changeConstruction_null";
		}
		
		return "mobile/modules/home/projectChange/changeConstruction_xiangqing";
	}
	
	/**
	 * 客户审核通过
	 * @return
	 */
	@RequestMapping(value = "agree",method=RequestMethod.GET)
	public @ResponseBody String agree(Integer projectChangeId,String reason,HttpServletRequest request, Model model) {
		
		//客户审核
		String status = "40";
		newProjectChangeService.updateChangeBill(projectChangeId,reason,status);
		
		//短信
		newProjectChangeService.mesesagePass(projectChangeId);
		
		return "0";
	}
	
	/**
	 * 客户审核不通过
	 * @return
	 */
	@RequestMapping(value = "refuse",method=RequestMethod.GET)
	public @ResponseBody String refuse(Integer projectChangeId,String reason,HttpServletRequest request, Model model) {
		
		//客户审核
		String status = "35";
		newProjectChangeService.updateChangeBill(projectChangeId,reason,status);
		
		//短信
		newProjectChangeService.mesesageRefuse(projectChangeId,reason);
		
		
		return "0";
	}
	
	

}
