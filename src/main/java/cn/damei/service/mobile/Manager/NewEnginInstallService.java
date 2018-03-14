package cn.damei.service.mobile.Manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import cn.damei.common.constantUtils.*;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.common.SessionUtils;
import cn.damei.dao.modules.OrderDao;
import cn.damei.entity.modules.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.Base64Util;
import cn.damei.dao.mobile.Inspector.CheckConfirmDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.dao.mobile.Manager.NewEnginInstallDao;
import cn.damei.entity.mobile.Manager.EnginInstall;
import cn.damei.entity.mobile.Manager.OrderInstallPlan;
import cn.damei.entity.mobile.Manager.PlanAdvanceApplyDTO;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.service.modules.BizBusinessStatusLogService;
import cn.damei.service.modules.BizBusinessUrgeService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.BizOrderInstallPlanAdvanceApply;
import cn.damei.service.modules.BizOrderInstallPlanAdvanceApplyService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;

import net.sf.json.JSONObject;


@Service
@Transactional(readOnly = true)
public class NewEnginInstallService {

	@Autowired
	private NewEnginInstallDao dao;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private CheckConfirmDao checkConfirmDao;
	@Autowired
	private BizOrderInstallPlanAdvanceApplyService bizOrderInstallPlanAdvanceApplyService;
	@Autowired
	private BizBusinessUrgeService bizBusinessUrgeService;
	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BizBusinessStatusLogService logService;
	@Autowired
	private PhoneMessageDao messageDao;


	public List<EnginInstall> queryInstallOrderList(Integer managerId, String secondType, String text) {
		EnginInstall enginInstall = new EnginInstall();
		enginInstall.setItemManagerId(managerId);
		enginInstall.setDelFlag(OrderConstantUtil.ORDER_DEL_FLAG_NO_0);
		enginInstall.setText(text);
		enginInstall.setReceiveMoneyType(secondType);
		return dao.queryInstallOrderList(enginInstall);
	}




	public EnginInstall findOrderMessage(Integer orderId) {
		return dao.findOrderMessage(orderId);
	}
	



	@Transactional(readOnly = false)
	public boolean updateInstallPlan(Integer orderInstallItemId, String status, String dataday2, String remarks,
			String installRemarks) {

		OrderInstallPlan installPlan = new OrderInstallPlan();

		installPlan.setId(orderInstallItemId);
		installPlan.setStatus(status);
		installPlan.setInstallRequire(installRemarks);
		installPlan.setRemarks(remarks);
		installPlan.setApplyIntoCreateDatetime(new Date());
		installPlan.setApplyIntoDate(DateUtils.parseDate(dataday2));
		installPlan.preUpdate();

		return (dao.updateInstallPlan(installPlan)) ? true : false;
	}



	@Transactional(readOnly = false)
	public void sendMessage(Integer orderId, Integer orderInstallItemId, String status) {

		EnginInstall order = dao.findOrderMessage(orderId);
		List<Integer> list = new ArrayList<Integer>();
		List<BizEmployee2> employeelist = null;
		String statusName = "";
		String businessType = "";
		if (status.equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2)) {
			statusName = InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_2;
			businessType = SendMsgBusinessType.RELATED_BUSINESS_TYPE_110201;
		} else {
			statusName = InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_6;
			businessType = SendMsgBusinessType.RELATED_BUSINESS_TYPE_110202;
		}
		if (null != order) {
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "4");
			if (null != bizMessagegroup) {
				String[] str = bizMessagegroup.getEmployees().split(",");
				for (String ids : str) {
					list.add(Integer.valueOf(ids));
				}
				employeelist = bizEmployeeService2.getById(list);
				if (employeelist.size() > 0) {
					for (BizEmployee2 employee : employeelist) {

						BizPhoneMsg ddMsg = new BizPhoneMsg();
						ddMsg.setId(null);
						ddMsg.setReceiveEmployeeId(employee.getId());
						ddMsg.setReceivePhone(employee.getPhone().trim());
						ddMsg.setMsgContent("订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-"
								+ order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName()
								+ "-" + order.getCustomerPhone() + "），项目经理（" + order.getEmployeeRealName() + "-"
								+ order.getEmployeePhone() + "），项目经理" + statusName + "安装计划，请及时登录系统查看。");
						ddMsg.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
						ddMsg.setMsgTosendDatetime(null);
						ddMsg.setMsgSendedDatetime(null);
						ddMsg.setMsgStatus("0");
						ddMsg.setRelatedBusinessType(businessType);
						ddMsg.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
						ddMsg.setRelatedBusinessIdVarchar("");
						bizPhoneMsgService.save(ddMsg);
					}
				}
			}
		}
	}


	public List<BizBusinessStatusLog> findOperationList(Integer id) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

		bizBusinessStatusLog.setBusinessOnlyMarkInt(Integer.valueOf(id));

		bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_901);

		return dao.findOperationList(bizBusinessStatusLog);
	}



	public String installPlanChecksizeAjax(String id) {

		String result = "0";
		
		try {

			if (StringUtils.isBlank(id)) {
				result = "1";
				return result;
			}
			


			String ifNeedChecksize = dao.isChecksize(id);

			if ("1".equals(ifNeedChecksize)) {

				String strCheck = dao.isChecksizeProblem(id);
				if (strCheck.equals("0")) {
					result = "2";
					return result;
				}
			}

			

			Integer acceptCount = dao.findNotAcceptInstallCount(Integer.valueOf(id));
			if(null!=acceptCount && acceptCount>0 ){
				result = "3";
				return result;
			}
			


			OrderInstallPlan orderInstallPlan = dao.findInstallCanApplyDate(Integer.valueOf(id));
			if (null != orderInstallPlan) {
				


				


				BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply = bizOrderInstallPlanAdvanceApplyService.findInstallPlanAdvanceApplyLastRecord(Integer.valueOf(id),OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_1);

				if(null == bizOrderInstallPlanAdvanceApply){
					String str = "{code:4,orderConfirmStartWorkDateString:"+orderInstallPlan.getOrderConfirmStartWorkDateString()+",workApplyDayString:"+orderInstallPlan.getWorkApplyDayString()+",applyIntoRemarks:"+orderInstallPlan.getApplyIntoRemarks()+"}";
					JSONObject fromObject = JSONObject.fromObject(str);
					String string = fromObject.toString();
					return string;
				}else if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){

					String str = "{code:5,orderConfirmStartWorkDateString:"+orderInstallPlan.getOrderConfirmStartWorkDateString()+",workApplyDayString:"+orderInstallPlan.getWorkApplyDayString()+",applyIntoRemarks:"+orderInstallPlan.getApplyIntoRemarks()+"}";
					JSONObject fromObject = JSONObject.fromObject(str);
					String string = fromObject.toString();
					return string;
				}
			}
			
			
			
		} catch (NumberFormatException e) {
			result = "9";
			e.printStackTrace();
		}
		return result;
	}
	
	

	public String queryInstallPlanCount(String orderId) {

		String result = "0";
		try {
			

			if (StringUtils.isBlank(orderId)) {
				result = "1";
				return result;
			}

			Integer count = dao.selectInstallPlanCount(Integer.valueOf(orderId));

			if(null==count || count<1){
				result = "2";
				return result;
			}
				
		} catch (NumberFormatException e) {
			result = "9";
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	

	public PlanAdvanceApplyDTO queryInstallPlan(Integer orderId, Integer id) {
		
		PlanAdvanceApplyDTO dto = new PlanAdvanceApplyDTO();

		EnginInstall install = dao.findOrderMessage(orderId);

		OrderInstallPlan orderInstallPlan = dao.findInstallMessage(id);
		dto.setEnginInstall(install);
		dto.setOrderInstallPlan(orderInstallPlan);
		return dto;
	}
	
	

	@Transactional(readOnly = false)
	public String saveInstallPlanApply(String orderId, String orderInstallItemId, String dataday2, String remarks,
			String installRemarks, String isShowInstallDescription, Manager manager, HttpServletRequest request) {
		
		String result = "0";
		try {
			

			if (StringUtils.isBlank(orderId)) {
				result = "1";
				return result;
			}

			if (StringUtils.isBlank(orderInstallItemId)) {
				result = "2";
				return result;
			}

			if (StringUtils.isBlank(dataday2)) {
				result = "3";
				return result;
			}

			if (StringUtils.isBlank(remarks)) {
				result = "4";
				return result;
			}

			if (StringUtils.isNotBlank(isShowInstallDescription) && isShowInstallDescription.equals("1")) {
				if (StringUtils.isBlank(installRemarks)) {
					result = "5";
					return result;
				}
			}

			OrderInstallPlan installPlan = dao.findInstallDetaill(Integer.valueOf(orderInstallItemId));
			if (null != installPlan) {

				if (!(installPlan.getStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_1) || installPlan.getStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_5))) {

					result = "6";
					return result;
				}
			} else {

				result = "7";
				return result;
			}


			String status = "";
			if (installPlan.getStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_1)) {
				status = InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2;
			} else {
				status = InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6;
			}


			if (null == manager || null == manager.getId()) {
				result = "8";
				return result;
			}


			boolean flag = updateInstallPlan(Integer.valueOf(orderInstallItemId), status, dataday2, remarks, installRemarks);
			if (!flag) {
				result = "9";
				return result;
			}


			Integer logId = bizBusinessStatusLogService.saveBusinessStatusLog(manager.getId(), Integer.valueOf(orderInstallItemId), BusinessLogConstantUtil.BUSINESS_TYPE_901, status, remarks + "#$%&#" + installRemarks, dataday2);
			if (null == logId || logId < 1) {
				result = "10";
				return result;
			}


			sendMessage(Integer.valueOf(orderId), Integer.valueOf(orderInstallItemId), status);

		} catch (NumberFormatException e) {
			result = "20";
			e.printStackTrace();
		}
		return result;
	}
	
	

	public PlanAdvanceApplyDTO queryPlanAdvanceApply(Integer orderId,Integer orderInstallItemId){
		PlanAdvanceApplyDTO dto = new PlanAdvanceApplyDTO();

		EnginInstall install = dao.findOrderMessage(orderId);

		OrderInstallPlan orderInstallPlan = dao.findInstallDetaill(orderInstallItemId);
		dto.setEnginInstall(install);
		dto.setOrderInstallPlan(orderInstallPlan);
		return dto;
	}
	

	@Transactional(readOnly = false)
	public String saveInstallApplyAdvanceApply(String orderInstallItemId, String[] photo, Manager manager,
			HttpServletRequest request) {
		
		String result = "0";

		try {

			if (StringUtils.isBlank(orderInstallItemId)) {
				result = "1";
				return result;
			}


			if (null == photo || photo.length < 1) {
				result = "2";
				return result;
			}



			Integer count = bizOrderInstallPlanAdvanceApplyService.findInstallPlanAdvanceApplyCount(
					Integer.valueOf(orderInstallItemId),
					OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_1);
			if (null != count && count > 0) {
				result = "3";
				return result;
			}


			if (null == manager || null == manager.getId()) {
				result = "4";
				return result;
			}
			
			Integer orderItemId = Integer.valueOf(orderInstallItemId);
			

			OrderInstallPlan installPlan = dao.findInstallDetaill(orderItemId);
			

			Integer advanceApplyId = bizOrderInstallPlanAdvanceApplyService.insertOrderInstallPlanAdvanceApply(installPlan.getOrderId(),installPlan.getInstallItemName(),installPlan.getPlanIntoDate(), orderItemId, manager,
					OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_1,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1);
			

			saveProblemPic(advanceApplyId, PictureTypeContantUtil.PICTURE_TYPE_2074, photo,
					PicturePathContantUtil.UPLOAD_INSTALL_PLAN_ADVANCE_APPLY_PATH, request);
			

			bizBusinessStatusLogService.saveBusinessStatusLog(manager.getId(), advanceApplyId, BusinessLogConstantUtil.BUSINESS_TYPE_9021,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_1, null);
		} catch (NumberFormatException e) {
			result = "5";
			e.printStackTrace();
		}
		return result;
	}

	

	@Transactional(readOnly = false)
	public void saveProblemPic(Integer businessId, String pictureType, String[] photo,
			String uploadSiteDesignProblemManagerApply, HttpServletRequest request) {

		Date date = new Date();
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();

		if (null != photo && photo.length > 0) {

			for (String p : photo) {

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");


				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + uploadSiteDesignProblemManagerApply + DateUtils.getDate1());

				if (!filePath.exists() && !filePath.isDirectory()) {
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);

				String picpath = uploadSiteDesignProblemManagerApply + DateUtils.getDate1() + filePath.separator + uuid
						+ ".jpeg";


				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				reportCheckDetailsPic.setBusinessIdInt(businessId);
				reportCheckDetailsPic.setBusinessType(pictureType);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.preInsert();
				pList.add(reportCheckDetailsPic);
			}

			checkConfirmDao.savePicAll(pList);
		}

	}


	public String queryPushInstallation(String id, Manager manager) {
		
		String result = "0";
		try {
			


			if (StringUtils.isNotBlank(id)) {
				Integer numCount = dao.findInstallOneDayCount(Integer.valueOf(id));
				if (null != numCount && numCount > 0) {
					result = "2";
					return result;
				}
			}


			if (StringUtils.isNotBlank(id)) {
				Integer count = bizBusinessUrgeService.findCount(manager, Integer.valueOf(id), BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
				if (null != count && count > 1) {
					result = "1";
					return result;
				}
			}
			
		} catch (NumberFormatException e) {
			result = "5";
			e.printStackTrace();
		}
		
		return result;
	}


	@Transactional(readOnly = false)
	public String savePushInstallation(String id, String orderId, String operateContent, Manager manager) {
		
		String result = "0";
		try {


			if (null == id || id.equals("")) {
				result = "1";
				return result;
			}

			if (null == manager || null == manager.getId()) {
				result = "2";
				return result;
			}

			Integer count = bizBusinessUrgeService.findCountByfiveTime(Integer.valueOf(id), manager.getId(),
					BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1,
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1,
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
			if (null != count && count > 0) {
				result = "3";
				return result;
			}

			if (null == operateContent || operateContent.equals("")) {
				result = "4";
				return result;
			}

			Integer urgeId = bizBusinessUrgeService.saveBusinessUrge(manager.getId(), operateContent,
					Integer.valueOf(id), BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1,
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1,
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
			if (null == urgeId) {
				result = "5";
				return result;
			}

		} catch (NumberFormatException e) {
			result = "9";
			e.printStackTrace();
		}
		return result;
	}


	@Transactional(readOnly = false)
	public String saveInstallReply(String id, String operateContent, Manager manager) {
		
		
		String result = "0";
		try {


			if (null == id || id.equals("")) {
				result = "1";
				return result;
			}

			if (null == manager || null == manager.getId()) {
				result = "2";
				return result;
			}

			Integer count = bizBusinessUrgeService.findCountByfiveTime(Integer.valueOf(id), manager.getId(), 
					BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1, 
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2, 
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
			if (null == count || count > 0) {
				result = "3";
				return result;
			}

			if (null == operateContent || operateContent.equals("")) {
				result = "4";
				return result;
			}

			Integer urgeId = bizBusinessUrgeService.saveBusinessUrge(manager.getId(), operateContent, Integer.valueOf(id),
					BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1, 
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2, 
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
			if (null == urgeId) {
				result = "5";
				return result;
			}

			
		} catch (NumberFormatException e) {
			result = "9";
			e.printStackTrace();
		}
		return result;
	}



	@Transactional(readOnly = false)
	public String sendUrgeMessage(String orderId, HttpServletRequest request) {
		String result = "0";


		try {
			Integer count = dao.findBusinessLogCount(orderId, BusinessLogConstantUtil.ORDER_LACK_MIDDLE_MONEY_STATUS_75, BusinessLogConstantUtil.BUSINESS_TYPE_404, DateUtils.formatDate(new Date(), "yyyy-MM-dd"));

			if (0 == count) {
				result = "1";

				Order order = orderDao.get(orderId);
				if (null != order && null != order.getDesignerName() && null != order.getDesignerPhone()) {

					PhoneMessageEntity message = new PhoneMessageEntity();
					message.setReceivePhone(order.getDesignerPhone());
					message.setMessageContent("（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-" + order.getCustomerPhone() + "）基础装修已完成，请及时催促\n" + "\n" + "客户交纳二期款。");
					message.setMessageGenerateTime(new Date());
					message.setStatus(BusinessLogConstantUtil.SEND_MSG_STATUS_0);
					message.setRelatedBusinessId(Integer.valueOf(orderId));
					messageDao.saveMessageContent(message);
				} else {
					result = "4";
				}

				BizBusinessStatusLog log = new BizBusinessStatusLog();
				log.setBusinessOnlyMarkInt(Integer.parseInt(orderId));
				log.setBusinessStatus(BusinessLogConstantUtil.ORDER_LACK_MIDDLE_MONEY_STATUS_75);
				log.setStatusDatetime(new Date());
				log.setBusinessRemarks("客户未交二期款,项目经理申请安装项时发送催促客户和设计师短信记录");
				log.setBusinessEmployeeId(SessionUtils.getManagerSession(request).getId());
				log.setBusinessEmployeeName(SessionUtils.getManagerSession(request).getRealname());
				log.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_404);
				logService.save(log);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			result = "2";
		}
		return result;
	}



	public OrderInstallPlan queryInstallPlanDetails(Integer id) {
		return dao.queryInstallPlanDetails(id);
	}



	public List<OrderInstallPlan> queryInstallPlanApplyList(Integer orderId) {
		return dao.queryInstallPlanApplyList(orderId);
	}
}
