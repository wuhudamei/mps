package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Worker.Message;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.entity.modules.BizConfirmCompleted;
import cn.damei.service.modules.BizConfirmCompletedService;
import cn.damei.service.modules.BizOrderFinishProjectBillService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 *	订单
 *	确认竣工
 *	biz_order
 */
@Controller
@RequestMapping(value = "${adminPath}/bizconfirmcompleted/bizConfirmCompleted")
public class BizConfirmCompletedController extends BaseController{
	
	@Autowired
	private BizConfirmCompletedService bizConfirmCompletedService;
	@Autowired
	private BizOrderFinishProjectBillService bizOrderFinishProjectBillService;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private MessageService messageService;
	
	@ModelAttribute
	public BizConfirmCompleted get(@RequestParam(required = false) Integer id) {
		BizConfirmCompleted entity = null;
		if (StringUtils.isNotBlank(id + "")) {

			entity = bizConfirmCompletedService.get(id);
		}
		if (entity == null) {
			entity = new BizConfirmCompleted();
		}
		return entity;
	}
	
	@RequiresPermissions("bizconfirmcompleted:bizConfirmCompleted:view")
	@RequestMapping(value = {"preList", ""})
	public String preList(BizConfirmCompleted bizConfirmCompleted, HttpServletRequest request, 
			HttpServletResponse response, Model model) {
		if(null == bizConfirmCompleted.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					bizConfirmCompleted.setEnginDepartIds(list);
				}else{
					bizConfirmCompleted.setEnginDepartIds(null);
				}
			} else {
				bizConfirmCompleted.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(bizConfirmCompleted.getEnginDepartId());
			bizConfirmCompleted.setEnginDepartIds(list);
		}
		User user = UserUtils.getUser();
		//过滤门店
		if(null == bizConfirmCompleted.getStoreId()){
			if(null != user.getStoreId()){
				bizConfirmCompleted.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizConfirmCompleted.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizConfirmCompleted.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizConfirmCompleted.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		model.addAttribute("bizConfirmCompleted",bizConfirmCompleted);
		return "modules/bizcompleted/confirmCompletedList";
	}
	
	@RequiresPermissions("bizconfirmcompleted:bizConfirmCompleted:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizConfirmCompleted bizConfirmCompleted, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if(null == bizConfirmCompleted.getEnginDepartId()){
			if(null!= UserUtils.getUser().getEmpId()){
				List<Integer> list = bizEmployeeService2.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if(list != null && list.size()>0){
					bizConfirmCompleted.setEnginDepartIds(list);
				}else{
					bizConfirmCompleted.setEnginDepartIds(null);
				}
			} else {
				bizConfirmCompleted.setEnginDepartIds(null);
			}
		}else{
			List<Integer> list = new ArrayList<>();
			list.add(bizConfirmCompleted.getEnginDepartId());
			bizConfirmCompleted.setEnginDepartIds(list);
		}
		//过滤门店
		if(null == bizConfirmCompleted.getStoreId()){
			if(null != user.getStoreId()){
				bizConfirmCompleted.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if(StringUtils.isBlank(user.getStoreId())){
			model.addAttribute("storeDropEnable", true);
		}
		//过滤工程模式
		if(StringUtils.isBlank(bizConfirmCompleted.getProjectMode())){
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizConfirmCompleted.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}else{
			if(null != user.getEmpId()){
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if(StringUtils.isBlank(be.getProjectMode())){
					model.addAttribute("gongcheng", true);
				}else{
					if(be.getProjectMode().equals("3")){
						model.addAttribute("gongcheng", true);
					}else{
						bizConfirmCompleted.setProjectMode(be.getProjectMode());
					}
				}
			}else{
				model.addAttribute("gongcheng", true);
			}
		}
		
		Page<BizConfirmCompleted> page = bizConfirmCompletedService.findPage(new Page<BizConfirmCompleted>(request, response), bizConfirmCompleted); 
		model.addAttribute("page", page);
		model.addAttribute("employeeId",UserUtils.getUser().getEmpId());
		model.addAttribute("bizConfirmCompleted",bizConfirmCompleted);
		return "modules/bizcompleted/confirmCompletedList";
	}
	
	/**
	 * 确认竣工
	 */
	@ResponseBody
	@RequestMapping(value = "conCompleted")
	public String conCompleted(BizConfirmCompleted bizConfirmCompleted, HttpServletRequest request, HttpServletResponse response, 
			Model model, String orderID) {
		
		String result = "0";
		BizConfirmCompleted order = null;
		if(!orderID.equals("")){
			result = bizOrderFinishProjectBillService.updateByStatusOrRemarks(ConstantUtils.ORDER_FINISH_PROJECT_BILL_STATUS_4,
					ConstantUtils.ORDERSTATUS_400_VALUE_REMARK,DateUtils.getDate1("yyyy-MM-dd HH:mm:ss"),Integer.valueOf(orderID));
			
			order = bizConfirmCompletedService.getByID(Integer.valueOf(orderID));
			if(order != null){
				logger.info("发送对象：项目经理手机号："+order.getEmployeePhone());
				List<Integer> list = new ArrayList<Integer>();
				BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "7");
				List<BizEmployee2> employeelist = null;
				if(null != bizMessagegroup ){
					String[] str = bizMessagegroup.getEmployees().split(",");
					for(String id: str){
						list.add(Integer.valueOf(id));
					}
					employeelist = bizEmployeeService2.getById(list);
					if(list.size() > 0 && employeelist.size() > 0){
						for(BizEmployee2 employee : employeelist){
							BizPhoneMsg ddMsg = new BizPhoneMsg();
							ddMsg.setId(null);
							ddMsg.setReceiveEmployeeId(order.getEmployeeID());
							ddMsg.setReceivePhone(order.getEmployeePhone());
							ddMsg.setMsgContent("订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()
									+"-"+order.getCustomerPhone()+"），结算员（"+employee.getRealname()+"-"+employee.getPhone()+"），竣工申请结算员已确认竣工，请登录APP查看详情。");
							ddMsg.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
							ddMsg.setMsgTosendDatetime(null);
							ddMsg.setMsgSendedDatetime(null);
							ddMsg.setMsgStatus("0");
							ddMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_500301);
							ddMsg.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
							ddMsg.setRelatedBusinessIdVarchar("");
							bizPhoneMsgService.save(ddMsg);
						}
					}
				}
				
				User user = UserUtils.getUser();
				Message message = new Message();
				message.setMsgTitle("结算员审核通过");
				message.setMsgTime(new Date());
				message.setMsgContent("订单（"+order.getCommunityName()+"-"+order.getBuildNumber()+"-"+order.getBuildUnit()+"-"+order.getBuildRoom()+"-"+order.getCustomerName()
				+"-"+order.getCustomerPhone()+"），结算员（"+user.getName()+"-"+user.getPhone()+"），竣工申请结算员已确认竣工，请登录APP查看详情。");
				message.setMsgType(MessagePushType.MSG_TYPE_1);
				message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_102002001);
				message.setEmployeeId(order.getItemManagerId());
				message.setBusiIdInt(Integer.valueOf(orderID));
				messageService.insert(message);
				
			}
		}
		
		if(result.equals("0")){
			result = bizConfirmCompletedService.updateByOrderStatusOrCompleted(ConstantUtils.ORDERSTATUS_400_VALUE,ConstantUtils.ORDERSTATUS_400_VALUE_REMARK,Integer.valueOf(orderID));
		}
		return result;
	}
	
}
