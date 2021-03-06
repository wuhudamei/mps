package cn.damei.web.mobile.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.ApplyProjectChangeEntity;
import cn.damei.entity.mobile.Manager.ProjectItem;
import cn.damei.service.mobile.home.ProjectChangeService;
import cn.damei.entity.mobile.home.CustomerVo;
import cn.damei.entity.modules.BizProjectChangeBill;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;

@Controller
@RequestMapping(value = "${adminPath}/app/home/applyProjectChange")
public class ProjectChangeController {
	private  Logger logger = LoggerFactory.getLogger(ProjectChangeController.class);
	@Autowired
	private ProjectChangeService applyProjectChangeService;
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;

	


	@RequestMapping(value = "list")
	public String list(HttpServletRequest request, Model model) {
		CustomerVo customerVo =SessionUtils.getCustomerSession(request);
		String phone = customerVo.getPhone();

		if (null != phone) {
			List<ApplyProjectChangeEntity> list = applyProjectChangeService.findChangeList(phone);

			model.addAttribute("list", list);

		}
		return "mobile/modules/home/change_list";
	}
	

	@RequestMapping(value = "agree",method=RequestMethod.GET)
	public @ResponseBody String agree(String projectChangeId,String reason,HttpServletRequest request, Model model) {

		String status = "40";
		applyProjectChangeService.updateChangeBill(projectChangeId,reason,status);
		

		BizProjectChangeBill change = bizProjectChangeBillService.findDetails(Integer.valueOf(projectChangeId));
		
		



		String content = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getCustomerPhone()+"），施工变更单客户已审核通过，请登录APP查看详情。";
		BizPhoneMsg phone = new BizPhoneMsg();
		phone.setReceiveEmployeeId(change.getItemManagerId());
		phone.setReceivePhone(change.getItemManagerPhone());
		phone.setMsgContent(content);
		phone.setMsgGenerateDatetime(new Date());
		phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
		phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400401);
		phone.setRelatedBusinessIdInt(Integer.valueOf(projectChangeId));
		bizPhoneMsgService.insert(phone);



		String content2 = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getCustomerPhone()+"），施工变更单客户已审核通过，请及时登录系统存档。";
		BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(change.getStoreId(),"10");
		List<Integer> list = new ArrayList<Integer>();
		List<BizEmployee2> employeelist = null;
		if(null != bizMessagegroup ){
			String[] str = bizMessagegroup.getEmployees().split(",");
			for(String id1: str){
				list.add(Integer.valueOf(id1));
			}
			employeelist = bizEmployeeService2.getById(list);
			if(null != employeelist && employeelist.size()>0){
				for (BizEmployee2 bizEmployee2 : employeelist) {
					
					BizPhoneMsg phone2 = new BizPhoneMsg();
					phone2.setReceiveEmployeeId(bizEmployee2.getId());
					phone2.setReceivePhone(bizEmployee2.getPhone());
					phone2.setMsgContent(content2);
					phone2.setMsgGenerateDatetime(new Date());
					phone2.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
					phone2.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200202);
					phone2.setRelatedBusinessIdInt(Integer.valueOf(projectChangeId));
					bizPhoneMsgService.insert(phone2);
				}
			}
		}


		String content3 = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getCustomerPhone()+"），施工变更单客户已审核通过，请登录系统查看详情。";
		BizPhoneMsg phone3 = new BizPhoneMsg();
		phone3.setReceivePhone(change.getDesignerPhone());
		phone3.setMsgContent(content3);
		phone3.setMsgGenerateDatetime(new Date());
		phone3.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
		phone3.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400403);
		phone3.setRelatedBusinessIdInt(Integer.valueOf(projectChangeId));
		bizPhoneMsgService.insert(phone3);
		


		String content4 = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getCustomerPhone()+"），施工变更单客户已审核通过，请登录系统查看详情。";
		BizMessagegroup bizMessagegroup2 = bizMessagegroupService.getByStoreId(change.getStoreId(),"11");
		List<Integer> list2 = new ArrayList<Integer>();
		List<BizEmployee2> employeelist2 = null;
		if(null != bizMessagegroup ){
			String[] str = bizMessagegroup2.getEmployees().split(",");
			for(String id1: str){
				list2.add(Integer.valueOf(id1));
			}
			employeelist2 = bizEmployeeService2.getById(list2);
			if(null != employeelist2 && employeelist2.size()>0){
				for (BizEmployee2 bizEmployee2 : employeelist2) {
					
					BizPhoneMsg phone4 = new BizPhoneMsg();
					phone4.setReceiveEmployeeId(bizEmployee2.getId());
					phone4.setReceivePhone(bizEmployee2.getPhone());
					phone4.setMsgContent(content4);
					phone4.setMsgGenerateDatetime(new Date());
					phone4.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
					phone4.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400402);
					phone4.setRelatedBusinessIdInt(Integer.valueOf(projectChangeId));
					bizPhoneMsgService.insert(phone4);
				}
			}
		}
		

		return "0";
	}

	@RequestMapping(value = "refuse",method=RequestMethod.GET)
	public @ResponseBody String refuse(String projectChangeId,String reason,HttpServletRequest request, Model model) {

		String status = "35";
		applyProjectChangeService.updateChangeBill(projectChangeId,reason,status);
		

		BizProjectChangeBill change = bizProjectChangeBillService.findDetails(Integer.valueOf(projectChangeId));
		
		



		String content = "订单（"+change.getCommunityName()+"-"+change.getBuildNumber()+"-"+change.getBuildUnit()+"-"+change.getBuildRoom()+"-"+change.getCustomerName()+"-"+change.getCustomerPhone()+"），施工变更单客户审核不通过，驳回原因（"+reason+"），请登录APP重新提交。";
		BizPhoneMsg phone = new BizPhoneMsg();
		phone.setReceiveEmployeeId(change.getItemManagerId());
		phone.setReceivePhone(change.getItemManagerPhone());
		phone.setMsgContent(content);
		phone.setMsgGenerateDatetime(new Date());
		phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
		phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_400405);
		phone.setRelatedBusinessIdInt(Integer.valueOf(projectChangeId));
		bizPhoneMsgService.insert(phone);

		
		return "0";
	}



	



	@RequestMapping(value = "details")
	public String details(Model model, Integer projectChangeId) {

			ApplyProjectChangeEntity bill = applyProjectChangeService.findChangeBillDetailById(projectChangeId);
			List<ProjectItem> itemList = applyProjectChangeService.findChangeItemByChangeBillId(bill.getProjectChangeId());

			model.addAttribute("bill", bill);
			model.addAttribute("itemList", itemList);
			return "mobile/modules/home/change_details";

	}

}
