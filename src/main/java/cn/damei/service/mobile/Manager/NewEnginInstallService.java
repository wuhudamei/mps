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

/**
 * 工程安装
 * 
 * @author wyb
 */
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

	/**
	 * 动态加载申请安装列表
	 * @param managerId
	 * @param secondType
	 * @param text
	 * @return
	 */
	public List<EnginInstall> queryInstallOrderList(Integer managerId, String secondType, String text) {
		EnginInstall enginInstall = new EnginInstall();
		enginInstall.setItemManagerId(managerId);
		enginInstall.setDelFlag(OrderConstantUtil.ORDER_DEL_FLAG_NO_0);
		enginInstall.setText(text);
		enginInstall.setReceiveMoneyType(secondType);
		return dao.queryInstallOrderList(enginInstall);
	}



	/**
	 * 查新订单信息
	 * 
	 * @param orderId
	 * @return
	 */
	public EnginInstall findOrderMessage(Integer orderId) {
		return dao.findOrderMessage(orderId);
	}
	


	/**
	 * 更新订单安装项
	 * 
	 * @param orderInstallItemId
	 * @param status
	 * @param dataday2
	 * @param remarks
	 * @param installRemarks
	 * @return
	 */
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


	/**
	 * 发送短信给材料员
	 * 
	 * @param orderId
	 * @param status
	 * @param orderInstallItemId
	 * @return
	 */
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
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "4");// 4代表调度员
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

	/**
	 * 查询操作日志
	 * 
	 * @param id
	 * @return
	 */
	public List<BizBusinessStatusLog> findOperationList(Integer id) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		// 业务唯一标识整形
		bizBusinessStatusLog.setBusinessOnlyMarkInt(Integer.valueOf(id));
		// 业务类型
		bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_901);

		return dao.findOperationList(bizBusinessStatusLog);
	}


	/**
	 * 安装申请
	 * 校验
	 *  1.该主材只有复尺后才能申请安装!
	 *  2.订单包含的橱柜、木门、木地板安装项未验收完毕，请及时处理。
	 *  3.该工地2017-08-22开工，按照工程部规定主材（家电安装）开工58天后（2017-10-19）才可以申请安装，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。
	 *  4.每个安装项【提前申请】只能申请一次
	 * @param id
	 * @return
	 */
	public String installPlanChecksizeAjax(String id) {

		String result = "0";
		
		try {
			// 如果安装项id为空
			if (StringUtils.isBlank(id)) {
				result = "1";
				return result;
			}
			
			//一、该主材只有复尺后才能申请安装!
			// 判断是否需要复尺
			String ifNeedChecksize = dao.isChecksize(id);
			// 1需要复尺 0不需要
			if ("1".equals(ifNeedChecksize)) {
				// 判断有没有复尺 0没有 其他有
				String strCheck = dao.isChecksizeProblem(id);
				if (strCheck.equals("0")) {
					result = "2";
					return result;
				}
			}

			
			//二、家具申请时，订单包含的橱柜、木门、木地板安装项未验收完毕，请及时处理。
			Integer acceptCount = dao.findNotAcceptInstallCount(Integer.valueOf(id));
			if(null!=acceptCount && acceptCount>0 ){
				result = "3";
				return result;
			}
			
			//三、该工地2017-08-22开工，按照工程部规定主材（家电安装）开工58天后（2017-10-19）才可以申请安装，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。 
			//该工地2017-08-22开工，按照工程部规定主材（家电安装）开工58天后（2017-10-19）才可以申请安装，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。
			OrderInstallPlan orderInstallPlan = dao.findInstallCanApplyDate(Integer.valueOf(id));
			if (null != orderInstallPlan) {
				
//				String messageRemark = "该工/地" + orderInstallPlan.getOrderConfirmStartWorkDateString() + "开工，按照工程部规定主材（" + orderInstallPlan.getInstallItemName() + "）开工" + orderInstallPlan.getWorkApplyDayString() + "天后（" + orderInstallPlan.getApplyIntoRemarks() + "）才可以申请安装，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。";
//				return messageRemark;
				
				//查询是否已经提交过该主材安装项的提前申请
				//每个安装项【提前申请】只能申请一次。
				BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply = bizOrderInstallPlanAdvanceApplyService.findInstallPlanAdvanceApplyLastRecord(Integer.valueOf(id),OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_1);
				//1.如果没有提交过申请
				if(null == bizOrderInstallPlanAdvanceApply){
					String str = "{code:4,orderConfirmStartWorkDateString:"+orderInstallPlan.getOrderConfirmStartWorkDateString()+",workApplyDayString:"+orderInstallPlan.getWorkApplyDayString()+",applyIntoRemarks:"+orderInstallPlan.getApplyIntoRemarks()+"}";
					JSONObject fromObject = JSONObject.fromObject(str);
					String string = fromObject.toString();
					return string;
				}else if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){
					//2.如果已经提交过申请（但是没有审核通过）
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
	
	
	/**
	 * 校验给订单是否有可申请的安装项
	 * @param orderId
	 * @return
	 */
	public String queryInstallPlanCount(String orderId) {

		String result = "0";
		try {
			
			// 如果安装项id为空
			if (StringUtils.isBlank(orderId)) {
				result = "1";
				return result;
			}
			//2.查询订单可申请的安装项数量
			Integer count = dao.selectInstallPlanCount(Integer.valueOf(orderId));
			//3.如果订单申请的安装项为空时
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
	
	
	/**
	 * 申请安装页面
	 * @param orderId
	 * @param id
	 * @return
	 */
	public PlanAdvanceApplyDTO queryInstallPlan(Integer orderId, Integer id) {
		
		PlanAdvanceApplyDTO dto = new PlanAdvanceApplyDTO();
		//1.查询订单详情
		EnginInstall install = dao.findOrderMessage(orderId);
		//2.查询安装项详情
		OrderInstallPlan orderInstallPlan = dao.findInstallMessage(id);
		dto.setEnginInstall(install);
		dto.setOrderInstallPlan(orderInstallPlan);
		return dto;
	}
	
	
	/**
	 * ajax 安装申请
	 * @param orderId
	 * @param orderInstallItemId
	 * @param dataday2
	 * @param remarks
	 * @param installRemarks
	 * @param isShowInstallDescription
	 * @param manager
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveInstallPlanApply(String orderId, String orderInstallItemId, String dataday2, String remarks,
			String installRemarks, String isShowInstallDescription, Manager manager, HttpServletRequest request) {
		
		String result = "0";
		try {
			
			// 1.订单id为空
			if (StringUtils.isBlank(orderId)) {
				result = "1";
				return result;
			}
			// 2.安装项id为空
			if (StringUtils.isBlank(orderInstallItemId)) {
				result = "2";
				return result;
			}
			// 3.期望进场日期为空
			if (StringUtils.isBlank(dataday2)) {
				result = "3";
				return result;
			}
			// 4.备注说明为空
			if (StringUtils.isBlank(remarks)) {
				result = "4";
				return result;
			}
			// 5.安装要求为空
			if (StringUtils.isNotBlank(isShowInstallDescription) && isShowInstallDescription.equals("1")) {
				if (StringUtils.isBlank(installRemarks)) {
					result = "5";
					return result;
				}
			}
			// 6.查询安装项详情
			OrderInstallPlan installPlan = dao.findInstallDetaill(Integer.valueOf(orderInstallItemId));
			if (null != installPlan) {
				// 只有状态为1:已生成计划 5：已驳回 才可以申请
				if (!(installPlan.getStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_1) || installPlan.getStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_5))) {
					// 否则不可以申请
					result = "6";
					return result;
				}
			} else {
				// 7.安装项内容不存在
				result = "7";
				return result;
			}

			// 状态
			String status = "";
			if (installPlan.getStatus().equals(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_1)) {
				status = InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2;
			} else {
				status = InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6;
			}

			// 8.项目经理登录
			if (null == manager || null == manager.getId()) {
				result = "8";
				return result;
			}

			// 9.更新订单安装项
			boolean flag = updateInstallPlan(Integer.valueOf(orderInstallItemId), status, dataday2, remarks, installRemarks);
			if (!flag) {
				result = "9";
				return result;
			}

			// 10.保存状态日志
			Integer logId = bizBusinessStatusLogService.saveBusinessStatusLog(manager.getId(), Integer.valueOf(orderInstallItemId), BusinessLogConstantUtil.BUSINESS_TYPE_901, status, remarks + "#$%&#" + installRemarks, dataday2);
			if (null == logId || logId < 1) {
				result = "10";
				return result;
			}

			// 11、发送短信给材料员
			sendMessage(Integer.valueOf(orderId), Integer.valueOf(orderInstallItemId), status);

		} catch (NumberFormatException e) {
			result = "20";
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 申请主材安装--快捷申请【提前申请】
	 * @param orderId
	 * @param orderInstallItemId
	 * @return
	 */
	public PlanAdvanceApplyDTO queryPlanAdvanceApply(Integer orderId,Integer orderInstallItemId){
		PlanAdvanceApplyDTO dto = new PlanAdvanceApplyDTO();
		//1.查询订单详情
		EnginInstall install = dao.findOrderMessage(orderId);
		//2.查询安装项详情
		OrderInstallPlan orderInstallPlan = dao.findInstallDetaill(orderInstallItemId);
		dto.setEnginInstall(install);
		dto.setOrderInstallPlan(orderInstallPlan);
		return dto;
	}
	
	/**
	 * 主材安装申请【提前申请】
	 * 
	 * @param orderInstallItemId
	 * @param photo
	 * @param manager
	 * @param request
	 */
	@Transactional(readOnly = false)
	public String saveInstallApplyAdvanceApply(String orderInstallItemId, String[] photo, Manager manager,
			HttpServletRequest request) {
		
		String result = "0";

		try {
			// 1.安装项id为空
			if (StringUtils.isBlank(orderInstallItemId)) {
				result = "1";
				return result;
			}

			// 2.请上传至少一张图片
			if (null == photo || photo.length < 1) {
				result = "2";
				return result;
			}

			// 3.查询该安装项【提前申请】的次数
			// 每个安装项【提前申请】只能申请一次。
			Integer count = bizOrderInstallPlanAdvanceApplyService.findInstallPlanAdvanceApplyCount(
					Integer.valueOf(orderInstallItemId),
					OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_1);
			if (null != count && count > 0) {
				result = "3";
				return result;
			}

			// 4.项目经理登录
			if (null == manager || null == manager.getId()) {
				result = "4";
				return result;
			}
			
			Integer orderItemId = Integer.valueOf(orderInstallItemId);
			
			// 5.查询安装项详情
			OrderInstallPlan installPlan = dao.findInstallDetaill(orderItemId);
			
			// 6.保存该安装项【提前申请】
			Integer advanceApplyId = bizOrderInstallPlanAdvanceApplyService.insertOrderInstallPlanAdvanceApply(installPlan.getOrderId(),installPlan.getInstallItemName(),installPlan.getPlanIntoDate(), orderItemId, manager,
					OrderInstallPlanAdvanceApplyConstantUtil.INSTALL_PLAN_ADVANCE_APPLY_TYPE_1,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1);
			
			// 7.保存该安装项【提前申请】的图片
			saveProblemPic(advanceApplyId, PictureTypeContantUtil.PICTURE_TYPE_2074, photo,
					PicturePathContantUtil.UPLOAD_INSTALL_PLAN_ADVANCE_APPLY_PATH, request);
			
			// 8.保存该安装项【提前申请】对应的状态日志
			bizBusinessStatusLogService.saveBusinessStatusLog(manager.getId(), advanceApplyId, BusinessLogConstantUtil.BUSINESS_TYPE_9021,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_1, null);
		} catch (NumberFormatException e) {
			result = "5";
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * 保存图片
	 * 
	 * @param businessId
	 * @param pictureType
	 * @param photo
	 * @param uploadSiteDesignProblemManagerApply
	 * @param request
	 */
	@Transactional(readOnly = false)
	public void saveProblemPic(Integer businessId, String pictureType, String[] photo,
			String uploadSiteDesignProblemManagerApply, HttpServletRequest request) {

		Date date = new Date();
		List<ReportCheckDetailsPic> pList = new ArrayList<ReportCheckDetailsPic>();
		// 保存图片
		if (null != photo && photo.length > 0) {

			for (String p : photo) {

				String uuid = UUID.randomUUID().toString().replaceAll("-", "");

				// String rootPath = RootName.SystemEnvironment(request);
				String rootPath = request.getSession().getServletContext().getRealPath("");
				File filePath = new File(rootPath + uploadSiteDesignProblemManagerApply + DateUtils.getDate1());
				// 判断该文件是否存在
				if (!filePath.exists() && !filePath.isDirectory()) {
					filePath.mkdirs();
				}
				String filepath = filePath + filePath.separator + uuid + ".jpeg";
				Base64Util.generateImage(p, filepath);

				String picpath = uploadSiteDesignProblemManagerApply + DateUtils.getDate1() + filePath.separator + uuid
						+ ".jpeg";

				// 保存图片到数据库
				ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
				reportCheckDetailsPic.setBusinessIdInt(businessId);
				reportCheckDetailsPic.setBusinessType(pictureType);
				reportCheckDetailsPic.setPicUrl(picpath);
				reportCheckDetailsPic.setPicDatetime(date);
				reportCheckDetailsPic.preInsert();
				pList.add(reportCheckDetailsPic);
			}
			// 批量插入图片
			checkConfirmDao.savePicAll(pList);
		}

	}

	/**
	 * 催促安装，一天最多允许催促2次 申请安装后24小时内不能进行催促安装
	 * @param id
	 * @param manager
	 * @return
	 */
	public String queryPushInstallation(String id, Manager manager) {
		
		String result = "0";
		try {
			

			// 申请安装后24小时内不能进行催促安装
			if (StringUtils.isNotBlank(id)) {
				Integer numCount = dao.findInstallOneDayCount(Integer.valueOf(id));
				if (null != numCount && numCount > 0) {
					result = "2";
					return result;
				}
			}

			// 催促安装，一天最多允许催促2次
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

	/**
	 * ajax 催促安装
	 * @param id
	 * @param orderId
	 * @param operateContent
	 * @param manager
	 * @return
	 */
	@Transactional(readOnly = false)
	public String savePushInstallation(String id, String orderId, String operateContent, Manager manager) {
		
		String result = "0";
		try {

			// 1.催促安装项ID为空
			if (null == id || id.equals("")) {
				result = "1";
				return result;
			}
			// 2.获取项目经理
			if (null == manager || null == manager.getId()) {
				result = "2";
				return result;
			}
			// 3.催促安装内容--5分钟校验
			Integer count = bizBusinessUrgeService.findCountByfiveTime(Integer.valueOf(id), manager.getId(),
					BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1,
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1,
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
			if (null != count && count > 0) {
				result = "3";
				return result;
			}
			// 4.催促安装内容为空
			if (null == operateContent || operateContent.equals("")) {
				result = "4";
				return result;
			}
			// 5.保存催促安装
			Integer urgeId = bizBusinessUrgeService.saveBusinessUrge(manager.getId(), operateContent,
					Integer.valueOf(id), BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1,
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_1,
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
			if (null == urgeId) {
				result = "5";
				return result;
			}
			// 6.保存催促安装图片
		} catch (NumberFormatException e) {
			result = "9";
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ajax 安装申请回复
	 * @param id
	 * @param operateContent
	 * @param manager
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveInstallReply(String id, String operateContent, Manager manager) {
		
		
		String result = "0";
		try {

			// 1.安装申请回复 安装项ID为空
			if (null == id || id.equals("")) {
				result = "1";
				return result;
			}
			// 2.获取项目经理
			if (null == manager || null == manager.getId()) {
				result = "2";
				return result;
			}
			// 3.安装申请回复--5分钟校验
			Integer count = bizBusinessUrgeService.findCountByfiveTime(Integer.valueOf(id), manager.getId(), 
					BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1, 
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2, 
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
			if (null == count || count > 0) {
				result = "3";
				return result;
			}
			// 4.安装申请回复内容为空
			if (null == operateContent || operateContent.equals("")) {
				result = "4";
				return result;
			}
			// 5.保存安装申请回复
			Integer urgeId = bizBusinessUrgeService.saveBusinessUrge(manager.getId(), operateContent, Integer.valueOf(id),
					BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1, 
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2, 
					BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_1);
			if (null == urgeId) {
				result = "5";
				return result;
			}
			// 6.保存安装申请回复图片
			
		} catch (NumberFormatException e) {
			result = "9";
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 该订单未交二期款,赶紧催客户交钱-客户未缴二期款发送一次短信 (设计师+客户)
	 * @param orderId
	 * @param request
	 * @return
	 */
	@Transactional(readOnly = false)
	public String sendUrgeMessage(String orderId, HttpServletRequest request) {
		String result = "0"; // 0没发送. 1 发送了 2:异常 3:客户手机信息不全 4:设计师信息不全
		// 1 :查看是否在business_status_log中 该订单今日已经催促了
		// 参数: 订单id ,status:75 type:404 时间 now
		try {
			Integer count = dao.findBusinessLogCount(orderId, BusinessLogConstantUtil.ORDER_LACK_MIDDLE_MONEY_STATUS_75, BusinessLogConstantUtil.BUSINESS_TYPE_404, DateUtils.formatDate(new Date(), "yyyy-MM-dd"));

			if (0 == count) {
				result = "1";
				// 2 : 没有催促 就查询订单的客户手机和设计师手机 并保存两次短信数据到 phone_mesg表中
				Order order = orderDao.get(orderId);
				if (null != order && null != order.getDesignerName() && null != order.getDesignerPhone()) {
					// 设计师
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
				// 3: 保存一条 带时间的 business_status_log 记录 --> 1 一天发一次
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


	/**
	 * 查询安装项详情
	 * @param id
	 * @return
	 */
	public OrderInstallPlan queryInstallPlanDetails(Integer id) {
		return dao.queryInstallPlanDetails(id);
	}


	/**
	 * 查询订单主材申请列表
	 * @param orderId
	 * @return
	 */
	public List<OrderInstallPlan> queryInstallPlanApplyList(Integer orderId) {
		return dao.queryInstallPlanApplyList(orderId);
	}
}
