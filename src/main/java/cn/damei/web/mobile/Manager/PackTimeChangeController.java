package cn.damei.web.mobile.Manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.phoneMessage.MessageEmployeePhoneAndId;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.service.modules.BizPhoneMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.SessionUtils;
import cn.damei.service.mobile.Manager.PackTimeChangeService;
import cn.damei.entity.mobile.Manager.TaskPackage;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月19日 下午5:45:26 
* 任务包计划调整 
*/
@Controller
@RequestMapping(value="${adminPath}/app/manager/packTimeList")
public class PackTimeChangeController {

	@Autowired
	private PackTimeChangeService service;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	
	private static Logger logger = LoggerFactory.getLogger(PackTimeChangeController.class);//日志
	
	
	@RequestMapping(value = { "list", "" })
	public String list(Model model, HttpServletRequest request){
		
		TaskPackage taskPackage = new TaskPackage();

taskPackage.setItemManagerId(SessionUtils.getManagerSession(request).getId());
		List<TaskPackage> list = service.selectPackByManagerId(taskPackage);
		if(null!=list&&list.size()>0){
			
			model.addAttribute("list",list);
			
			
			return "mobile/modules/Manager/packTimeChange/task_plan";
		}else{
			model.addAttribute("error","您目前没有可修改的任务包");
			return "mobile/modules/Manager/packTimeChange/none_task_plan";
			
		}
		
		
	}
	@RequestMapping(value = { "query_ajax_list", "" })
	public @ResponseBody List<TaskPackage> queryAjaxList(Model model, HttpServletRequest request,String text){
		
		TaskPackage taskPackage = new TaskPackage();
		taskPackage.setItemManagerId(SessionUtils.getManagerSession(request).getId());
		taskPackage.setText(text);
		List<TaskPackage> list = service.selectPackByManagerId(taskPackage);
		if(null!=list&&list.size()>0){
			return list;
		}else{
			return null;
			
		}
		
		
	}
	
	@RequestMapping(value="detail")
	public String detail(String packId ,Model model){
		
		
		TaskPackage taskPackage = service.packDetailByPackId(Integer.parseInt(packId));
		
		model.addAttribute("taskpackage", taskPackage);
		return "mobile/modules/Manager/packTimeChange/re_assign";
		
	}
	
	@RequestMapping(value="changePlan")
	public @ResponseBody String changePlan(String packName,String customerInfo,String packageId,String planStartDate,String planEndDate,String remarks,Model model,HttpServletRequest request){
		
		TaskPackage taskPackage = new TaskPackage();
		
		taskPackage.setPackageId(Integer.parseInt(packageId));
		try {
			taskPackage.setPlanStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(planStartDate));
			taskPackage.setPlanEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(planEndDate));
			taskPackage.setRemarks(remarks);
			service.changePackTime(taskPackage);
			
			//===================短信发送=====================================
			//项目经理调整了项目（小区名-楼号-单元号-门牌号-客户姓名）的任务包（任务包名称）用工计划，请关注”；
			List<MessageEmployeePhoneAndId> list= pDao.getEmployeePhoneAndId(Integer.parseInt(SessionUtils.getManagerSession(request).getStoreid()),"6");
			if(list.size()>0){
				for (MessageEmployeePhoneAndId entity : list) {
					String managerMessage = "订单（"+customerInfo+"）的任务包（"+packName+"），项目经理"+SessionUtils.getManagerSession(request).getRealname()+"-"+SessionUtils.getManagerSession(request).getPhone()+"已调整任务包计划，请登录系统查看详情。";
					bizPhoneMsgService.sendMessage(entity.getEmployeeId(), entity.getEmployeePhone(),
							managerMessage, SendMsgBusinessType.RELATED_BUSINESS_TYPE_110101, taskPackage.getPackageId());
				}
			}else{
				logger.info("没有工人调度员");
			}
			return "1";
		} catch (ParseException e) {
			System.out.println("转换失败");
			e.printStackTrace();
			logger.error("任务包计划调整的时间转换错误");
			return "redirect:" + Global.getAdminPath() + "/app/manager/packTimeList/list";
		}
		
		
	
	}
	@Autowired
	private PhoneMessageDao  pDao;
}
