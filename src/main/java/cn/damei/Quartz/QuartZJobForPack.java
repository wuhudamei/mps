package cn.damei.Quartz;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.damei.common.utils.*;
import cn.damei.service.modules.BizPhoneMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.damei.entity.modules.BizMsg;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.web.modules.QuartZJobTaskPackageController;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.service.modules.service;



public class QuartZJobForPack {

	
	private static Logger logger = LoggerFactory.getLogger(QuartZJobTaskPackageController.class);

	@Autowired
	private service service;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;



	
	String packName;
	String managerPhone;
	String customerInfo;
	String planStartDate;
	String planEndDate;
	Integer packId;
	Integer managerId;
	public void execute() throws UnsupportedEncodingException {
		
		logger.info("---------------------QuartZ定时  每日早9点任务包前三天提醒项目经理-------------");

		Map<String,String> sendMessage = new HashMap<String,String>();
		

		String timeToSendMessage = new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.addDate(new Date(), 3));

			
		List<OrderTaskpackage> list = service.sendFixedTimeMessageToManagerForPackPlanTime(timeToSendMessage);
			
		
		if(null!=list&&list.size()>0){

			for (OrderTaskpackage orderTaskpackage : list) {
				
				

				if(null!=orderTaskpackage.getItemManagerId()&&""!=orderTaskpackage.getItemManagerId()){
					managerId =  Integer.parseInt(orderTaskpackage.getItemManagerId());	
					
				}else{
					logger.warn("====该任务包没有任务包经理id, 请联系系统管理员===任务包id为:  "+orderTaskpackage.getId());
					packName="";
				}

				if(null!=orderTaskpackage.getId()&&""!=orderTaskpackage.getId()){
					packId = Integer.parseInt(orderTaskpackage.getId());	
					
				}else{
					logger.warn("====该任务包没有任务包id, 请联系系统管理员===任务包id为:  "+orderTaskpackage.getId());
					packName="";
				}

				if(null!=orderTaskpackage.getPackageName()&&""!=orderTaskpackage.getPackageName()){
					packName = orderTaskpackage.getPackageName();	
					
				}else{
					logger.warn("====该任务包没有任务包名称, 请联系系统管理员===任务包id为:  "+orderTaskpackage.getId());
					packName="";
				}

				if(null!=orderTaskpackage.getCustomerMessage()&&""!=orderTaskpackage.getCustomerMessage()&&null!=orderTaskpackage.getCustomerName()&&""!=orderTaskpackage.getCustomerName()){
					customerInfo =orderTaskpackage.getCustomerMessage()+"-"+orderTaskpackage.getCustomerName();
					
					
				}else{
					logger.warn("====该任务包没有客户的地址或姓名 请联系系统管理员====任务包id为:  "+orderTaskpackage.getId());
					customerInfo="";
				}

				if(null!=orderTaskpackage.getManagerPhone()&&""!=orderTaskpackage.getManagerPhone()){
					
					managerPhone = orderTaskpackage.getManagerPhone();
					
				}else{
					logger.warn("====该任务包没有相关项目经理电话,请联系系统管理员====任务包id为:  "+orderTaskpackage.getId());
					managerPhone="";
				}

				
				if(null!=orderTaskpackage.getPlanStartdate()){
					
					planStartDate= new SimpleDateFormat("yyyy-MM-dd").format(orderTaskpackage.getPlanStartdate());
				}else{
					planStartDate="";
					logger.warn("====该任务包没有计划开始时间,请联系系统管理员====任务包id为:"+orderTaskpackage.getId());
				}
				

				
				if(null!=orderTaskpackage.getPlanEnddate()){
					planEndDate= new SimpleDateFormat("yyyy-MM-dd").format(orderTaskpackage.getPlanEnddate());
					
				}else{
					planEndDate="";
					logger.warn("====该任务包没有计划结束时间,请联系系统管理员====任务包id为:"+orderTaskpackage.getId());
				}
				
				

				if(""!=customerInfo&&""!=managerPhone&&""!=planStartDate&&""!=planEndDate&&""!=packName&&0!=packId&&0!=managerId){
					
					String messageContext = "订单（"+customerInfo+"）的任务包（"+packName+"），工人调度员将按照计划（"+planStartDate+"  至  "+planEndDate+"）给您派工。";

					bizPhoneMsgService.sendMessage(managerId, managerPhone,
							messageContext, SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200807, packId);

					logger.info("定时早上9点发送短信提醒项目经理任务包的最新进度    成功日志");

					

					BizMsg bizMsg = new BizMsg();
					bizMsg.setMsgTitle("工人调度员按计划派工");
					bizMsg.setMsgTime(new Date());
					bizMsg.setMsgContent("订单（"+customerInfo+"）的任务包（"+packName+"），工人调度员将按照计划（"+planStartDate+"  至  "+planEndDate+"）给您派工.请知晓，如工地实际情况有变动，请及时联系工人调度员");
					bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
					bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100003001);
					bizMsg.setBusiIdInt(packId);
					bizMsg.setEmployeeId(managerId);
					bizProjectChangeBillService.saveBizMsg(bizMsg);

								
					
					
				}
				
				
			}
			
			
			
		}else{
			
			logger.info("当日暂无任务需要提醒项目经理");
		}
		
		
	
		
		
		
		
		
		
	}
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	
}
