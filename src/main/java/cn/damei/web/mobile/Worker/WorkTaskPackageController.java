	package cn.damei.web.mobile.Worker;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.mobile.Manager.BizEvalActivityOrderTaskpackRel;
import cn.damei.service.mobile.Manager.BizEvalActivityOrderTaskpackRelService;
import cn.damei.entity.mobile.Manager.BizEvalRewardOrderTaskpackRel;
import cn.damei.service.mobile.Manager.BizEvalRewardOrderTaskpackRelService;
import cn.damei.service.mobile.Manager.TaskPackageService;
import cn.damei.service.modules.BizEvalRewardCfgService;
import cn.damei.service.modules.BizEvalActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.config.Global;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.phoneMessage.MessageEmployeePhoneAndId;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.common.Base64Util;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;
import cn.damei.service.mobile.Manager.BizOrderTaskpackageProcedureService;
import cn.damei.web.mobile.Manager.PackTimeChangeController;
import cn.damei.entity.mobile.Manager.TaskPackage;
import cn.damei.entity.mobile.Worker.Message;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.entity.mobile.Worker.EmployeeGroupRa;
import cn.damei.entity.mobile.Worker.EmployeeGroupVo;
import cn.damei.service.mobile.Worker.EmployeeGroupRaService;
import cn.damei.service.mobile.Worker.EmployeeGroupVoService;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.entity.mobile.Worker.TaskPackagePic;
import cn.damei.entity.mobile.Worker.UrgeRecord;
import cn.damei.entity.mobile.Worker.WorkTaskPackage;
import cn.damei.entity.mobile.Worker.WorkerPackProcedure;
import cn.damei.service.mobile.Worker.WorkTaskPackageService;
import cn.damei.entity.mobile.home.BroadCastPicEntity;
import cn.damei.service.mobile.home.BroadCastService;
import cn.damei.entity.modules.BizMsg;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;

@Controller
@RequestMapping(value = "${adminPath}/app/worker")
public class WorkTaskPackageController {

	@Autowired
	private WorkTaskPackageService workTaskPackageService;
	@Autowired
	private BizOrderTaskpackageProcedureService bizOrderTaskpackageProcedureService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	
	private static Logger logger = LoggerFactory.getLogger(PackTimeChangeController.class);//日志
	@Autowired
	private  PhoneMessageDao dao;

	@Autowired
	private TaskPackageService taskPackageService;

	@Autowired
	private BizEvalRewardCfgService bizEvalRewardCfgService;

	@Autowired
	private BizEvalRewardOrderTaskpackRelService bizEvalRewardOrderTaskpackRelService;

	@Autowired
	private BizEvalActivityService bizEvalActivityService;

	@Autowired
	private BizEvalActivityOrderTaskpackRelService bizEvalActivityOrderTaskpackRelService;
	/**
	 * 消息service
	 */
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = { "taskPackageList", "" })
	public String list(Model model, HttpServletRequest request) {

	
		Worker worker = SessionUtils.getWorkerSession(request);
		List<WorkTaskPackage> list = workTaskPackageService.findTaskPackageByGroupId(worker.getId());
		model.addAttribute("list", list);
		return "mobile/modules/Worker/taskpackage_list";
	}

	/**
	 * 工人组长接受任务包
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "acceptTaskPackage")
	public @ResponseBody String acceptTaskPackage(Integer id, HttpServletRequest request) {

		workTaskPackageService.acceptTaskPackage(id,request);

		WorkTaskPackage packId = workTaskPackageService.selectMessageInfoByPackId(id);
		// 客户信息+客户姓名+任务包名称
		/*String acceptCustomerInfo = packId.getCustomerMessage() + "-" + packId.getCustomerName() + "-"
				+ packId.getPackageName();*/
		// 工人组长名称+手机号
		String workerInfoPack = SessionUtils.getWorkerSession(request).getRealname() + "-"
				+ SessionUtils.getWorkerSession(request).getPhone();
		/*String managerPhonePack = packId.getManagerPhone();*/
		
		List<MessageEmployeePhoneAndId> list = dao.getEmployeePhoneAndId(Integer.parseInt(SessionUtils.getWorkerSession(request).getStoreid()), "6");
		
		if(null!=list&&list.size()>0){
			PhoneMessageEntity entity = new PhoneMessageEntity();
			
			for (MessageEmployeePhoneAndId diaoduyuan : list) {
				
				entity.setReceiveEmployeeId(diaoduyuan.getEmployeeId());
				entity.setReceivePhone(diaoduyuan.getEmployeePhone());
				entity.setMessageContent("订单（"+packId.getCustomerMessage() + "-" + packId.getCustomerName() + "-"+packId.getCustomerPhone()+"）的任务包（"+ packId.getPackageName()+"），工人（"+workerInfoPack+"），已接收任务包，请登录系统查看详情。");
				entity.setMessageGenerateTime(new Date());
				entity.setStatus(ConstantUtils.SETTLEMENT_DETAIL_STATUS_0);
				entity.setRelatedBusinessType("200502");
				entity.setRelatedBusinessId(id);
				dao.saveMessageContent(entity);
			}
			
		}
		

		//=====================================消息推送start========================================================
		
		/**
		 * 消息推送   消息推送类型MESSAGE_PUSH_TYPE_100004001-通知项目经理-工人确认接收任务包
		 */
		BizMsg bizMsg = new BizMsg();
		bizMsg.setMsgTitle("工人确认接收任务包");
		bizMsg.setMsgTime(new Date());
		bizMsg.setMsgContent("订单（"+packId.getCustomerMessage() + "-" + packId.getCustomerName() + "-"+packId.getCustomerPhone()+"）的任务包（"+ packId.getPackageName()+"），工人（"+workerInfoPack+"），已接收任务包，请在【任务包查询】查看详情。。");
		bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
		bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100004001);
		bizMsg.setBusiIdInt(id);
		bizMsg.setEmployeeId(packId.getManagerId());
		bizProjectChangeBillService.saveBizMsg(bizMsg);
		//=====================================消息推送end========================================================
				
		
		
		
		

		return "1";
	}
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;

	/**
	 * 工人组长拒绝任务包
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "refuseTaskPackage")
	public @ResponseBody String refuseTaskPackage(Integer id, HttpServletRequest request) {

		workTaskPackageService.refuseTaskPackage(id,request);

		WorkTaskPackage packId = workTaskPackageService.selectMessageInfoByPackId(id);
		// 客户信息+客户姓名+任务包名称
		/*String refuseCustomerInfo = packId.getCustomerMessage() + "-" + packId.getCustomerName() + "-"
				+ packId.getPackageName();*/
		// 工人组长名称+手机号
		String workerInfoPack = SessionUtils.getWorkerSession(request).getRealname() + "-"
				+ SessionUtils.getWorkerSession(request).getPhone();
		// 项目经理手机
		/*String managerPhonePack = packId.getManagerPhone();*/

		
		List<MessageEmployeePhoneAndId> list = dao.getEmployeePhoneAndId(Integer.parseInt(SessionUtils.getWorkerSession(request).getStoreid()), "6");
		
		//接受拒绝都给调度员发短信
		if(null!=list&&list.size()>0){
			PhoneMessageEntity entity = new PhoneMessageEntity();
			
			for (MessageEmployeePhoneAndId diaoduyuan : list) {
				
				entity.setReceiveEmployeeId(diaoduyuan.getEmployeeId());
				entity.setReceivePhone(diaoduyuan.getEmployeePhone());
				entity.setMessageContent("订单（"+packId.getCustomerMessage() + "-" + packId.getCustomerName() + "-"+packId.getCustomerPhone()+"）的任务包（"+ packId.getPackageName()+"），工人（"+workerInfoPack+"），已拒绝任务包，请登录系统查看详情。");
				entity.setMessageGenerateTime(new Date());
				entity.setStatus(ConstantUtils.SETTLEMENT_DETAIL_STATUS_0);
				entity.setRelatedBusinessType("200502");
				entity.setRelatedBusinessId(id);
				dao.saveMessageContent(entity);
			}
			
		}
		return "1";
	}

	@RequestMapping(value = { "packDetail", "" })
	public String packDetail(TaskPackage taskPackage, Model model, Integer settleStyle) {
		// 1:根据任务包id 查询任务包

		WorkTaskPackage pack = workTaskPackageService.getPackById(taskPackage.getPackageId());

		// 放入组长手机
		pack.setLeaderPhone(workTaskPackageService.getLeaderPhoneById(pack.getManagerId()));
		pack.setLeaderName(workTaskPackageService.getLeaderNameById(pack.getManagerId()));
		// 2:放入model中
		model.addAttribute("pack", pack);
		// 3:根据任务包id查询工序 建立Vo
		List<WorkerPackProcedure> procedureList = workTaskPackageService
				.findProcedureByPackId(taskPackage.getPackageId());
		// 4:查询出的工序放入model中
		model.addAttribute("procedureList", procedureList);

		if (settleStyle == 0 || settleStyle == 1) { // 1-包工包料
			return "mobile/modules/Worker/budget";
		} else { // 2-包工
			return "mobile/modules/Worker/budget_pkgwork";
		}
	}

	// 工人端申请完工页面
	@RequestMapping(value = { "doneApplyList", "" })

	public String doneApplyList(HttpServletRequest request, Model model) {
		Worker worker = SessionUtils.getWorkerSession(request);

		List<WorkTaskPackage> doneApplyList = workTaskPackageService.findDoneApplyListByGroupId(worker.getId());
		model.addAttribute("doneApplyList", doneApplyList);
		return "mobile/modules/Worker/done_apply";
	}

	// 工人端申请完工页面的详情页面
	@RequestMapping(value = { "doneDemandList", "" })
	public String doneDemandList(TaskPackage taskPackage, Model model) {

		WorkTaskPackage doneDemandList = workTaskPackageService.getPackById(taskPackage.getPackageId());
		model.addAttribute("doneDemandList", doneDemandList);
		return "mobile/modules/Worker/done_demand";
	}


	// 申请完工
	@RequestMapping(value = "applyTaskPackage", method = RequestMethod.POST)
	public @ResponseBody String applyTaskPackage(HttpServletRequest request, String[] photo,String customerMessage,String customerName,String packageName,
			Integer orderTaskpackageId) {
		WorkTaskPackage taskPackageInfo= workTaskPackageService.getPackById(orderTaskpackageId);
		if(taskPackageInfo.getPackageStateid().equals("80")){
			return "1";
		}
        List<TaskPackagePic> list = new ArrayList<TaskPackagePic>();
		if (photo.length>0) {
			
			//保存播报记录
			
			BroadCastPicEntity bc = new BroadCastPicEntity();
			
			WorkTaskPackage order = workTaskPackageService.findOrder(orderTaskpackageId);
			
			if(null!=order){
				bc.setOrderId(order.getOrderId());
				
			}else{
				bc.setOrderId(null);
			}
			bc.setBroadCastName(packageName);
			bc.setCusBroadCastType("3");
			bc.setCusBroadCastCode(bcService.getBroadCastCode());
			bc.setPicCount(photo.length);
			bc.setApplyEmployeeId(SessionUtils.getWorkerSession(request).getId());
			bc.setApplyDate(new Date());
			bc.setStatus("10");//10已生成播报单
			bc.setStatusTime(new Date());
			bc.setCreateBy(SessionUtils.getWorkerSession(request).getId());
			bc.setCreateDate(new Date());
			bc.setRelatedBusinessId(orderTaskpackageId);
			bc.setDelFlag("0");
			bcService.saveBroadCastRecord(bc);
			//返回播报单id
			
			
			//保存播报图片  公共字段不用循环保存
			bc.setPicType("501");//播报类型
			bc.setBroadCastId(bc.getId());
			bc.setPicDateTime(new Date());
			
			
			for (String p : photo) {
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				
//				String rootPath = RootName.SystemEnvironment(request);
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + ConstantUtils.UPLOAD_DEMAND + DateUtils.getDate1());
				//判断该文件是否存在
				if(!filePath.exists() && !filePath.isDirectory()){
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);
				
				String picpath = ConstantUtils.UPLOAD_DEMAND + DateUtils.getDate1()+filePath.separator + uuid + ".jpeg";
				TaskPackagePic taskPackagePic = new TaskPackagePic();
				taskPackagePic.setId(null);
				taskPackagePic.setorderTaskpackageId(Integer.valueOf(orderTaskpackageId));
				taskPackagePic.setPicturePath(picpath);
				//workTaskPackageService.savePic(taskPackagePic);
				list.add(taskPackagePic);
				
				//	 仅仅图片需要保存
				bc.setPicUrl(picpath);
				bcService.saveBroadCastPic(bc);
				
			}
	//批量插入申请完工图片
			workTaskPackageService.savePicAll(list);
			workTaskPackageService.applyTaskPackage(Integer.valueOf(orderTaskpackageId));
			
			Worker worker = SessionUtils.getWorkerSession(request);
			WorkTaskPackage workTaskPackage = workTaskPackageService.findOrder(Integer.valueOf(orderTaskpackageId));

			// 评价与奖励
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			TaskPackage taskPackage = taskPackageService.queryRewardActivity(orderTaskpackageId);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("storeId",taskPackage.getStoreId());
			map.put("projectMode",taskPackage.getProjectMode());
			map.put("rewardTargetType",ConstantUtils.REWARD_TARGET_TYPE_1);
			map.put("rewardDatetime",format.format(new Date()));
			map.put("taskpackTempId",taskPackage.getTaskPackageTemplatId());
			map.put("isEnabled",ConstantUtils.IS_ENABLED_1);
			Integer evalRewardCfgId = bizEvalRewardCfgService.queryIdByMap(map);

			if(evalRewardCfgId != null){
				BizEvalRewardOrderTaskpackRel bizEvalRewardOrderTaskpackRel = new BizEvalRewardOrderTaskpackRel();
				bizEvalRewardOrderTaskpackRel.setEvalRewardCfgId(evalRewardCfgId);
				bizEvalRewardOrderTaskpackRel.setOrderTaskpackId(orderTaskpackageId);
				bizEvalRewardOrderTaskpackRelService.insert(bizEvalRewardOrderTaskpackRel);
			}

			Integer evalActivityId = bizEvalActivityService.queryIdByMap(map);
			if(evalActivityId != null){
				BizEvalActivityOrderTaskpackRel bizEvalActivityOrderTaskpackRel = new BizEvalActivityOrderTaskpackRel();
				bizEvalActivityOrderTaskpackRel.setEvalActivityId(evalActivityId);
				bizEvalActivityOrderTaskpackRel.setOrderTaskpackId(orderTaskpackageId);
				bizEvalActivityOrderTaskpackRelService.insert(bizEvalActivityOrderTaskpackRel);
			}

			//=====================================短信start========================================================
			/**
			 * 工人申请完工==项目经理
			 */
			//项目经理【大美装饰管理平台】订单（小区名-楼号-单元号-门牌号-客户姓名-手机号）的任务包（任务包名称），工人（工人组长-手机号）已申请完工，请及时验收任务包。
			String content = "订单（"+customerMessage+"-"+customerName+"-"+workTaskPackage.getCustomerPhone()+"）的任务包（"+packageName+"），工人（"+worker.getRealname()+"-"+worker.getPhone()+"）已申请完工，请及时验收任务包。";
			BizPhoneMsg phone = new BizPhoneMsg();
			phone.setReceiveEmployeeId(workTaskPackage.getManagerId());
			phone.setReceivePhone(workTaskPackage.getManagerPhone());
			phone.setMsgContent(content);
			phone.setMsgGenerateDatetime(new Date());
			phone.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			phone.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_200702);
			phone.setRelatedBusinessIdInt(Integer.valueOf(orderTaskpackageId));
			bizPhoneMsgService.insert(phone);
			
			Message message = new Message();
			message.setMsgTitle("任务包工人申请完工签到");
			message.setMsgTime(new Date());
			message.setMsgContent(content);
			message.setMsgType(MessagePushType.MSG_TYPE_1);
			message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100005002);
			message.setEmployeeId(workTaskPackage.getManagerId());
			message.setBusiIdInt(Integer.valueOf(orderTaskpackageId));
			messageService.insert(message);
			//=====================================短信end========================================================
			
//			String phone = workTaskPackageService.findPhone(Integer.valueOf(orderTaskpackageId));
//			//===================短信发送=====================================	
//			//工人已申请完工（小区名称-楼牌号-客户姓名-任务包名称）组长：工人组长姓名-手机号，请尽快进行验收。
//			Map<String, String> params = new HashMap<String, String>();
//			params.put("source", "1");
//	    	params.put("content", "工人已申请完工（"+customerMessage+"-"+customerName+"-"+packageName+"）组长："+worker.getRealname()+worker.getPhone()+"，请尽快进行验收。");
//	    	params.put("mobile", phone);
//	    	params.put("sendtime", "");
//	    	try {
//				HttpConnection.post(ConstantUtils.SEND_MESSAGE_INTERFACE, params);
//				logger.info("确认申请成功，已通知项目经理");
//	    	} catch (Exception e) {
//				e.printStackTrace();
//			}
			
			
		}
		return "0";

	}

	// 工人端工艺工法
	@RequestMapping(value = { "method", "" })
	public String method() {
		return "mobile/modules/Worker/method";
	}

	// 工人端工艺工法--水电工程
	@RequestMapping(value = { "waterPower", "" })
	public String waterPower() {
		return "mobile/modules/Worker/waterPower";
	}

	// 工人端工艺工法--泥瓦工程
	@RequestMapping(value = { "mud", "" })
	public String mud() {
		return "mobile/modules/Worker/mud";
	}

	// 工人端工艺工法--木土工程
	@RequestMapping(value = { "wood", "" })
	public String wood() {
		return "mobile/modules/Worker/wood";
	}

	// 工人端工艺工法--油工工程
	@RequestMapping(value = { "paint", "" })
	public String paint() {
		return "mobile/modules/Worker/paint";
	}

	// 工人端工艺工法--木作安装
	@RequestMapping(value = { "funiture", "" })
	public String funiture() {
		return "mobile/modules/Worker/funiture";
	}

	// 工人端工艺工法--服务规范
	@RequestMapping(value = { "service", "" })
	public String service() {
		return "mobile/modules/Worker/service";
	}

	/**
	 * 催促验收
	 * 
	 * @return
	 */
	@RequestMapping(value = "hurryToCheck")
	public String hurryToCheck(HttpServletRequest request, Model model) {
		Worker worker = SessionUtils.getWorkerSession(request);
		// 查询任务包 状态为: 80 工人申请完工
		List<WorkTaskPackage> list = workTaskPackageService.selectPackByWorkerIdForManagerCheck(worker.getId());

		for (WorkTaskPackage workTaskPackage : list) {

			// 1:根据任务包id查询任务包催促记录表, 判断催促时间是否少于一天
			List<UrgeRecord> record = workTaskPackageService.urgeRecord(workTaskPackage.getId());
			if (null != record && record.size()>0) {
				for (UrgeRecord urgeRecord : record) {
					// 2:如果不是今天, 允许再次催促 ,设置任务包备注名字为:"YES"
					String format = new SimpleDateFormat("yyyy-MM-dd").format(urgeRecord.getUrgeTime());
					if(!format.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
						workTaskPackage.setRemarks("YES");
						
					}else{
						// 3:jsp判断 是否是NO , 是就灰色按钮
						workTaskPackage.setRemarks("NO");
					}
					
					
					
				}

			}else{
				//没有催促记录的 也能催
				workTaskPackage.setRemarks("YES");
				
			}

		}

		String workerInfo = SessionUtils.getWorkerSession(request).getRealname() + "-"
				+ SessionUtils.getWorkerSession(request).getPhone();
		// 1:工人姓名+手机
		model.addAttribute("workerInfo", workerInfo);
		// 2:list中有customerInfo和name

		// 3:由 ajax获取

		model.addAttribute("list", list);

		return "mobile/modules/Worker/check";
	}

	@RequestMapping(value = "gongqi",method=RequestMethod.GET)
	public @ResponseBody String gongqi(String packId, Model model, HttpServletRequest request) {
		WorkTaskPackage messageInfo;
		// 返回由任务包id查询的工期
		if (null != packId && !"".equals(packId)) {

			//短信信息
			messageInfo = workTaskPackageService.packActuallyDoneDays(Integer.parseInt(packId));
			
			// 开工日期
						Date startdate = messageInfo.getActualStartdate();
						String start = new SimpleDateFormat("yyyy-MM-dd").format(startdate);

						// 结束日期
						Date enddate = messageInfo.getActualEnddate();
						String end = new SimpleDateFormat("yyyy-MM-dd").format(enddate);
						
			
			// 发送站内消息

						// 发送站内消息
						//=====================================消息推送start========================================================
						
						/**
						 * 消息推送   消息推送类型MESSAGE_PUSH_TYPE_100005003-通知项目经理-工人催促验收
						 * 订单（东晨小区-10-4-202-王维-13333333333）的任务包（泥瓦工程），已发送给您，请在【我要接单】中接收，我们为您保留60分钟。
						 */
						BizMsg bizMsg = new BizMsg();
						bizMsg.setMsgTitle("工人催促验收");
						bizMsg.setMsgTime(new Date());
						bizMsg.setMsgContent("快验收" + messageInfo.getPackageName() + "任务包吧，我已经等不及了 (地点: "
								+ messageInfo.getCustomerMessage() + "-" + messageInfo.getCustomerName() + ",  工人: "
								+ messageInfo.getWorkerName()+ "-"
								+ messageInfo.getWorkerPhone()+" , 工期: "+start+"至"+end);
						bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
						bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100005003);
						bizMsg.setBusiIdInt(Integer.parseInt(packId));
						bizMsg.setEmployeeId(messageInfo.getManagerId());
						bizProjectChangeBillService.saveBizMsg(bizMsg);
						//=====================================消息推送end========================================================
								
						
						
						
						
						

			
						
			//保存催促短信记录
						PhoneMessageEntity entity = new PhoneMessageEntity();
						
					entity.setMessageContent("订单（" + messageInfo.getCommunityName() + "-" + messageInfo.getBuildNumber()
									+ "-" + messageInfo.getBuildUnit() + "-" + messageInfo.getBuildRoom() + "-"
									+ messageInfo.getCustomerName() + "-" + messageInfo.getCustomerPhone() + ")的任务包("+messageInfo.getPackageName()+")，工人（"+messageInfo.getWorkerName()+"-"+messageInfo.getWorkerPhone()+"）请验收任务包。	");	
						
					entity.setReceiveEmployeeId(messageInfo.getManagerId());
					entity.setReceivePhone(messageInfo.getManagerPhone());
					entity.setMessageGenerateTime(new Date());
					entity.setStatus("0");
					entity.setRelatedBusinessType("200703");
					entity.setRelatedBusinessId(Integer.parseInt(packId));
					messageDao.saveMessageContent(entity);	
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
			
			
			return String.valueOf(messageInfo.getManagerId());
		} else {

			return "NO";
		}

	}
	
	@Autowired
	private PhoneMessageDao messageDao;
	

	@RequestMapping(value = "saveUrgeRecord")
	public String saveUrgeRecord(String packId, String managerId, HttpServletRequest request) {

		// 工人组长id
		Integer id = SessionUtils.getWorkerSession(request).getId();

		UrgeRecord record = new UrgeRecord();
		// 任务包id
		record.setPackId(Integer.parseInt(packId));
		// 被催促人
		record.setBeUrgeMan(Integer.parseInt(managerId));
		// 催促人
		record.setUrgeMan(id);
		// 催促时间
		record.setUrgeTime(new Date());

		// 保存催促记录表
		workTaskPackageService.saveUrgeRecord(record);

		return "redirect:" + Global.getAdminPath() + "/app/worker/hurryToCheck";
	}
	
	@Autowired
	private EmployeeGroupRaService employeeGroupRaService;
	@Autowired
	private EmployeeGroupVoService employeeGroupVoService;
	/**
	 * 我的任务
	 */
	@RequestMapping(value="myTaskpackageList")
	public String myTaskpackageList(Model model, HttpServletRequest request){
		
		//已登录的工人
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		//根据员工id去查询工人组id
		EmployeeGroupRa employeeGroupRa = employeeGroupRaService.findByEmployeeId(worker.getId());
		
		List<WorkTaskPackage> list = new ArrayList<WorkTaskPackage>();
		//根据工人组id查询组长的id
		if(employeeGroupRa != null){
			EmployeeGroupVo employeeGroupVo = employeeGroupVoService.findById(employeeGroupRa.getGroupId());
			list = workTaskPackageService.queryAllTaskpackage(employeeGroupVo.getGroupid());
		}
		model.addAttribute("list", list);
		return "mobile/modules/Worker/my_list";
	}
	
	
	@RequestMapping(value="taskpackageDetail")
	public String taskpackageDetail(Integer taskpackageId , Model model){
		
		DecimalFormat df = new DecimalFormat("#.00");
		WorkTaskPackage pack = workTaskPackageService.getPackById(taskpackageId);
		// 放入组长手机
		pack.setLeaderPhone(workTaskPackageService.getLeaderPhoneById(pack.getGroupId()));
		pack.setLeaderName(workTaskPackageService.getLeaderNameById(pack.getGroupId()));
		double budgetTotalMoney = 0d;  //工料费预算总金额
		double laborDudgetTotalMoney = 0d;  //人工预算总金额
		double auxiliaryMaterialsBudgetTotalMoney =0d; //辅料费预算总金额
		double settleTotalMoney = 0d;//工料费结算总金额
		double laborSettleTotalMoney = 0d; ;//人工费结算总金额
		double auxiliaryMaterialsSettleTotalMoney = 0d; //辅料费结算总金额
		List<BizOrderTaskpackageProcedure> procedures = bizOrderTaskpackageProcedureService.queryOrderTaskpackageProcedure(taskpackageId);
		for (BizOrderTaskpackageProcedure procedure : procedures) {
			budgetTotalMoney += procedure.getLaborAuxiliaryMaterialsBudgetAmount();
			laborDudgetTotalMoney += procedure.getLaborDudgetAmount();
			auxiliaryMaterialsBudgetTotalMoney += procedure.getAuxiliaryMaterialsBudgetAmount();
			if(procedure.getLaborAuxiliaryMaterialsSettleAmount() != null){
				settleTotalMoney += procedure.getLaborAuxiliaryMaterialsSettleAmount();
				laborSettleTotalMoney += procedure.getLaborSettleAmount();
				auxiliaryMaterialsSettleTotalMoney += procedure.getAuxiliaryMaterialsSettleAmount();
			}
			/*procedure.setBudgetTotal(procedure.getBudgetNumber()*procedure.getSynthesizePrice());
			budgetTotalMoney += procedure.getBudgetNumber()*procedure.getSynthesizePrice();
			if(procedure.getLaborAuxiliaryMaterialsBudgetAmount() != null){
				settleTotalMoney += procedure.getLaborAuxiliaryMaterialsBudgetAmount();
			}*/
		}
		settleTotalMoney = Double.parseDouble(df.format(settleTotalMoney));
		budgetTotalMoney = Double.parseDouble(df.format(budgetTotalMoney));
		model.addAttribute("settleTotalMoney",settleTotalMoney);
		model.addAttribute("laborSettleTotalMoney",laborSettleTotalMoney);
		model.addAttribute("auxiliaryMaterialsSettleTotalMoney",auxiliaryMaterialsSettleTotalMoney);
		model.addAttribute("budgetTotalMoney",budgetTotalMoney);
		model.addAttribute("laborDudgetTotalMoney",laborDudgetTotalMoney);
		model.addAttribute("auxiliaryMaterialsBudgetTotalMoney",auxiliaryMaterialsBudgetTotalMoney);
		model.addAttribute("pack",pack);
		model.addAttribute("procedures",procedures);
		return "mobile/modules/Worker/taskpackageDetail";
	}
	
	
	@Autowired
	private BroadCastService bcService;
}
