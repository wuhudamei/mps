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

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月18日 下午2:41:50 
* 类说明 
*/

public class QuartZJobForPack {

	
	private static Logger logger = LoggerFactory.getLogger(QuartZJobTaskPackageController.class);//日志

	@Autowired
	private service service;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;


	//3: 短信内容
	
	String packName;
	String managerPhone;
	String customerInfo;
	String planStartDate;
	String planEndDate;
	Integer packId;
	Integer managerId;
	public void execute() throws UnsupportedEncodingException {
		
		logger.info("---------------------QuartZ定时  每日早9点任务包前三天提醒项目经理-------------");
		//短信map数据集合
		Map<String,String> sendMessage = new HashMap<String,String>();
		
		//1:根据当前时间+3天, 查找一样日期的 任务包,日期比对格式为: yyyy-MM-dd
		String timeToSendMessage = new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.addDate(new Date(), 3));
		//2: 根据 1 的时间条件   将任务包的 计划开始日期和计划结束日期  项目经理手机,任务包名称,客户四连信息取出
			
		List<OrderTaskpackage> list = service.sendFixedTimeMessageToManagerForPackPlanTime(timeToSendMessage);
			
		
		if(null!=list&&list.size()>0){
			//4 :作为短信内容发送  模板为:短信内容模板“您的项目（@）的任务包（@），工人调度员将按照计划（@）给您派工。请知晓，如工地实际情况有变动，请及时联系工人调度员";
			for (OrderTaskpackage orderTaskpackage : list) {
				
				
				//校验: -1 :项目经理Id
				if(null!=orderTaskpackage.getItemManagerId()&&""!=orderTaskpackage.getItemManagerId()){
					managerId =  Integer.parseInt(orderTaskpackage.getItemManagerId());	
					
				}else{
					logger.warn("====该任务包没有任务包经理id, 请联系系统管理员===任务包id为:  "+orderTaskpackage.getId());
					packName="";
				}
				//校验: 0 :任务包Id
				if(null!=orderTaskpackage.getId()&&""!=orderTaskpackage.getId()){
					packId = Integer.parseInt(orderTaskpackage.getId());	
					
				}else{
					logger.warn("====该任务包没有任务包id, 请联系系统管理员===任务包id为:  "+orderTaskpackage.getId());
					packName="";
				}
				//校验: 1 :任务包名称
				if(null!=orderTaskpackage.getPackageName()&&""!=orderTaskpackage.getPackageName()){
					packName = orderTaskpackage.getPackageName();	
					
				}else{
					logger.warn("====该任务包没有任务包名称, 请联系系统管理员===任务包id为:  "+orderTaskpackage.getId());
					packName="";
				}
				//校验: 2 :客户地址和姓名
				if(null!=orderTaskpackage.getCustomerMessage()&&""!=orderTaskpackage.getCustomerMessage()&&null!=orderTaskpackage.getCustomerName()&&""!=orderTaskpackage.getCustomerName()){
					customerInfo =orderTaskpackage.getCustomerMessage()+"-"+orderTaskpackage.getCustomerName();
					
					
				}else{
					logger.warn("====该任务包没有客户的地址或姓名 请联系系统管理员====任务包id为:  "+orderTaskpackage.getId());
					customerInfo="";
				}
				//校验: 3 :任务包的项目经理手机
				if(null!=orderTaskpackage.getManagerPhone()&&""!=orderTaskpackage.getManagerPhone()){
					
					managerPhone = orderTaskpackage.getManagerPhone();
					
				}else{
					logger.warn("====该任务包没有相关项目经理电话,请联系系统管理员====任务包id为:  "+orderTaskpackage.getId());
					managerPhone="";
				}
				//校验: 4:任务包计划开始时间
				
				if(null!=orderTaskpackage.getPlanStartdate()){
					
					planStartDate= new SimpleDateFormat("yyyy-MM-dd").format(orderTaskpackage.getPlanStartdate());
				}else{
					planStartDate="";
					logger.warn("====该任务包没有计划开始时间,请联系系统管理员====任务包id为:"+orderTaskpackage.getId());
				}
				
				// 校验: 5 :任务包计划结束时间
				
				if(null!=orderTaskpackage.getPlanEnddate()){
					planEndDate= new SimpleDateFormat("yyyy-MM-dd").format(orderTaskpackage.getPlanEnddate());
					
				}else{
					planEndDate="";
					logger.warn("====该任务包没有计划结束时间,请联系系统管理员====任务包id为:"+orderTaskpackage.getId());
				}
				
				
				//4: 如果数据没问题
				if(""!=customerInfo&&""!=managerPhone&&""!=planStartDate&&""!=planEndDate&&""!=packName&&0!=packId&&0!=managerId){
					
					String messageContext = "订单（"+customerInfo+"）的任务包（"+packName+"），工人调度员将按照计划（"+planStartDate+"  至  "+planEndDate+"）给您派工。";

					bizPhoneMsgService.sendMessage(managerId, managerPhone,
							messageContext, SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200807, packId);

					logger.info("定时早上9点发送短信提醒项目经理任务包的最新进度    成功日志");
					//=====================================消息推送start========================================================
					
					/**
					 * 消息推送   消息推送类型 101001006-通知项目经理-客户审核不通过
					 * 订单（东晨小区-10-4-202-王维-13333333333）的任务包（泥瓦工程），工人调度员将按照计划（2016-11-26  至  2016-12-07）给您派工。请知晓，如工地实际情况有变动，请及时联系工人调度员
					 */
					BizMsg bizMsg = new BizMsg();
					bizMsg.setMsgTitle("工人调度员按计划派工");
					bizMsg.setMsgTime(new Date());
					bizMsg.setMsgContent("订单（"+customerInfo+"）的任务包（"+packName+"），工人调度员将按照计划（"+planStartDate+"  至  "+planEndDate+"）给您派工.请知晓，如工地实际情况有变动，请及时联系工人调度员");
					bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
					bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100003001);
					bizMsg.setBusiIdInt(packId);
					bizMsg.setEmployeeId(managerId);
					bizProjectChangeBillService.saveBizMsg(bizMsg);
					//=====================================消息推送end========================================================
								
					
					
				}
				
				
			}
			
			
			
		}else{
			
			logger.info("当日暂无任务需要提醒项目经理");
		}
		
		
	
		
		
		
		
		
		
	}
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;
	
}
