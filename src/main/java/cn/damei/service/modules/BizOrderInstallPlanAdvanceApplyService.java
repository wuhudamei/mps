/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.OrderInstallPlanAdvanceApplyConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.modules.BizOrderInstallPlanAdvanceApply;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.BizOrderInstallPlanAdvanceApplyDao;

/**
 * 主材安装项提前申请记录Service
 * @author wyb
 */
@Service
@Transactional(readOnly = true)
public class BizOrderInstallPlanAdvanceApplyService extends CrudService2<BizOrderInstallPlanAdvanceApplyDao, BizOrderInstallPlanAdvanceApply> {

	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	
	public BizOrderInstallPlanAdvanceApply get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderInstallPlanAdvanceApply> findList(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {
		return super.findList(bizOrderInstallPlanAdvanceApply);
	}
	
	public Page<BizOrderInstallPlanAdvanceApply> findPage(Page<BizOrderInstallPlanAdvanceApply> page, BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {
		return super.findPage(page, bizOrderInstallPlanAdvanceApply);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {
		super.save(bizOrderInstallPlanAdvanceApply);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {
		super.delete(bizOrderInstallPlanAdvanceApply);
	}

	/**
	 * 查询是否已经提交过该主材安装项的提前申请(安装/复尺)
	 * @param orderInstallPlanId
	 * @param applyType 
	 * @return
	 */
	public BizOrderInstallPlanAdvanceApply findInstallPlanAdvanceApplyLastRecord(Integer orderInstallPlanId, String applyType) {
		
		BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply = new BizOrderInstallPlanAdvanceApply();
		//1.主材安装项id
		bizOrderInstallPlanAdvanceApply.setOrderInstallPlanId(orderInstallPlanId);
		//2.主材安装项提前申请类型（1.安装 2.复尺）
		bizOrderInstallPlanAdvanceApply.setApplyType(applyType);
		
		return dao.findInstallPlanAdvanceApplyLastRecord(bizOrderInstallPlanAdvanceApply);
	}

	/**
	 * 查询该安装项【提前申请】的次数
	 * @param orderInstallPlanId
	 * @param applyType
	 * @return
	 */
	public Integer findInstallPlanAdvanceApplyCount(Integer orderInstallPlanId, String applyType) {
		
		BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply = new BizOrderInstallPlanAdvanceApply();
		//1.主材安装项id
		bizOrderInstallPlanAdvanceApply.setOrderInstallPlanId(orderInstallPlanId);
		//2.主材安装项提前申请类型（1.安装 2.复尺）
		bizOrderInstallPlanAdvanceApply.setApplyType(applyType);
		
		return dao.findInstallPlanAdvanceApplyCount(bizOrderInstallPlanAdvanceApply);
	}
	
	/**
	 * 保存该安装项【提前申请】
	 * @param orderId
	 * @param installItemName
	 * @param oldPlanApplyDate
	 * @param orderInstallItemId
	 * @param manager
	 * @param applyType
	 * @param dealStatus
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer insertOrderInstallPlanAdvanceApply(Integer orderId, String installItemName,
			Date oldPlanApplyDate, Integer orderInstallItemId,
			Manager manager, String applyType, String dealStatus) {

		Date date = new Date();

		BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply = new BizOrderInstallPlanAdvanceApply();
		// 1.订单id
		bizOrderInstallPlanAdvanceApply.setOrderId(orderId);
		// 2.主材安装项id
		bizOrderInstallPlanAdvanceApply.setOrderInstallPlanId(orderInstallItemId);
		// 3.主材安装项提前申请类型（1.安装 2.复尺）
		bizOrderInstallPlanAdvanceApply.setApplyType(applyType);
		// 4.安装项计划名称
		bizOrderInstallPlanAdvanceApply.setInstallItemName(installItemName);
		// 5.原计划可申请日期
		bizOrderInstallPlanAdvanceApply.setOldPlanApplyDate(oldPlanApplyDate);
		// 6.处理状态
		bizOrderInstallPlanAdvanceApply.setDealStatus(dealStatus);
		// 7.处理人
		bizOrderInstallPlanAdvanceApply.setDealEmployeeId(manager.getId());
		// 8.处理时间
		bizOrderInstallPlanAdvanceApply.setDelaTime(date);

		bizOrderInstallPlanAdvanceApply.preInsert();

		dao.insert(bizOrderInstallPlanAdvanceApply);

		return bizOrderInstallPlanAdvanceApply.getId();
	}

	/**
	 * 【提前申请】修改申请单的状态
	 * @param bizOrderInstallPlanAdvanceApply
	 * @param dealStatus
	 * @param dealEmployeeId
	 */
	@Transactional(readOnly = false)
	public void saveAdvanceApply(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply,String dealStatus,Integer dealEmployeeId) {
		
		// 处理状态
		bizOrderInstallPlanAdvanceApply.setDealStatus(dealStatus);
		// 处理人id
		bizOrderInstallPlanAdvanceApply.setDealEmployeeId(dealEmployeeId);
		// 处理时间
		bizOrderInstallPlanAdvanceApply.setDelaTime(new Date());
		
		bizOrderInstallPlanAdvanceApply.preUpdate();
		
		dao.update(bizOrderInstallPlanAdvanceApply);
		
	}
	
	/**
	 * 门店和工程模式权限控制 
	 * @param bizOrderInstallPlanAdvanceApply
	 * @param model
	 */
	public void storeIdAndProjectMode(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply, Model model) {
		User user = UserUtils.getUser();
		//门店
		if(null==bizOrderInstallPlanAdvanceApply.getStoreId()){
			if(StringUtils.isNotBlank(user.getStoreId())){
				bizOrderInstallPlanAdvanceApply.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		//工程模式
		if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
			model.addAttribute("gongcheng", true);
		}else{
			bizOrderInstallPlanAdvanceApply.setProjectMode(user.getProjectMode());
		}
	}

	/**
	 * 主材可申请安装日期处理【拒绝】【安装】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveInstallAdvanceApplyRefuse(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {
		
		String result = "0";
		
		try {
			//1.当前登录人
			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			
			//2.【提前申请】是否已经处理
			if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){
				result = "1";
				return result;
			}
			
			//3.更新安装项【提前申请】
			saveAdvanceApply(bizOrderInstallPlanAdvanceApply,OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_2,empId);
			
			//4.保存安装项【提前申请】对应的状态日志
			bizBusinessStatusLogService.saveBusinessStatusLog(empId, bizOrderInstallPlanAdvanceApply.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9021,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_2,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_2, null);
			
			//5.发送短信给项目经理
			//订单（北京东城区南小街-12-1-1-韩信-13301102121）的安装项（木门安装）提前申请安装被拒绝，如有问题请及时联系大区经理或大区助理。
			String msgContent = "订单（"+bizOrderInstallPlanAdvanceApply.getCommunityName()+"-"+bizOrderInstallPlanAdvanceApply.getBuildNumber()+"-"
					+bizOrderInstallPlanAdvanceApply.getBuildUnit()+"-"+bizOrderInstallPlanAdvanceApply.getBuildRoom()+"-"
					+bizOrderInstallPlanAdvanceApply.getCustomerName()+"-"+bizOrderInstallPlanAdvanceApply.getCustomerPhone()
					+"）的安装项（"+bizOrderInstallPlanAdvanceApply.getInstallItemName()+"）提前申请安装被拒绝，如有问题请及时联系大区经理或大区助理。";
			bizPhoneMsgService.sendMessage(bizOrderInstallPlanAdvanceApply.getItemManagerId(), bizOrderInstallPlanAdvanceApply.getItemManagerPhone(), 
					msgContent, SendMsgBusinessType.RELATED_BUSINESS_TYPE_110502, bizOrderInstallPlanAdvanceApply.getId());
			
		} catch (NumberFormatException e) {
			result = "20";
			e.printStackTrace();
		}
		return result;
	}


	
	/**
	 * 主材可申请安装日期处理【同意】【安装】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveInstallAdvanceApplyAgree(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {

		String result = "0";
		try {
			//1.当前登录人
			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			
			//2.【提前申请】是否已经处理
			if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){
				result = "2";
				return result;
			}
			//3.确认开工后可申请安装日期为空
			if(null == bizOrderInstallPlanAdvanceApply.getOldPlanApplyDate()){
				result = "3";
				return result;
			}
			//4.获取提前的天数
			double advanceDays = DateUtils.daysBetween(bizOrderInstallPlanAdvanceApply.getOldPlanApplyDate(),new Date());
			
			//5.生成新的【订单安装项】【订单安装项计划】【安装】
			saveInstallPlanNewAdvanceApply(bizOrderInstallPlanAdvanceApply.getOrderId(),advanceDays);
			
			//6.更新安装项【提前申请】
			saveAdvanceApply(bizOrderInstallPlanAdvanceApply,OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3,empId);
			
			//7.保存安装项【提前申请】对应的状态日志
			bizBusinessStatusLogService.saveBusinessStatusLog(empId, bizOrderInstallPlanAdvanceApply.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9021,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_3, null);
			
			//8.发送短信给项目经理
			//订单（北京东城区南小街-12-1-1-韩信-13301102121）的安装项（木门安装）申请安装时间调整为（2017-10-09），请及时申请主材安装。
			int advanceDaysInt = (int) advanceDays;
			String canApplyTime = DateUtils.formatDate(DateUtils.addDate(bizOrderInstallPlanAdvanceApply.getOldPlanApplyDate(),advanceDaysInt));
			String msgContent = "订单（"+bizOrderInstallPlanAdvanceApply.getCommunityName()+"-"+bizOrderInstallPlanAdvanceApply.getBuildNumber()+"-"
					+bizOrderInstallPlanAdvanceApply.getBuildUnit()+"-"+bizOrderInstallPlanAdvanceApply.getBuildRoom()+"-"
					+bizOrderInstallPlanAdvanceApply.getCustomerName()+"-"+bizOrderInstallPlanAdvanceApply.getCustomerPhone()
					+"）的安装项（"+bizOrderInstallPlanAdvanceApply.getInstallItemName()+"）申请安装时间调整为（"+canApplyTime+"），请及时申请主材安装。";
			bizPhoneMsgService.sendMessage(bizOrderInstallPlanAdvanceApply.getItemManagerId(), bizOrderInstallPlanAdvanceApply.getItemManagerPhone(), 
					msgContent, SendMsgBusinessType.RELATED_BUSINESS_TYPE_110501, bizOrderInstallPlanAdvanceApply.getId());
			
		} catch (NumberFormatException e) {
			result = "20";
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 主材可申请复尺日期处理【拒绝】【复尺】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveChecksizeAdvanceApplyRefuse(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {
		
		String result = "0";
		
		try {
			//1.当前登录人
			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			
			//2.【提前申请】是否已经处理
			if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){
				result = "1";
				return result;
			}
			
			//3.更新复尺【提前申请】
			saveAdvanceApply(bizOrderInstallPlanAdvanceApply,OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_2,empId);
			
			//4.保存复尺【提前申请】对应的状态日志
			bizBusinessStatusLogService.saveBusinessStatusLog(empId, bizOrderInstallPlanAdvanceApply.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9022,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_2,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_2, null);
			
			//5.发送短信给项目经理
			//订单（北京东城区南小街-12-1-1-韩信-13301102121）的安装项（木门安装）提前申请复尺被拒绝，如有问题请及时联系大区经理或大区助理。
			String msgContent = "订单（"+bizOrderInstallPlanAdvanceApply.getCommunityName()+"-"+bizOrderInstallPlanAdvanceApply.getBuildNumber()+"-"
					+bizOrderInstallPlanAdvanceApply.getBuildUnit()+"-"+bizOrderInstallPlanAdvanceApply.getBuildRoom()+"-"
					+bizOrderInstallPlanAdvanceApply.getCustomerName()+"-"+bizOrderInstallPlanAdvanceApply.getCustomerPhone()
					+"）的安装项（"+bizOrderInstallPlanAdvanceApply.getInstallItemName()+"）提前申请复尺被拒绝，如有问题请及时联系大区经理或大区助理。";
			bizPhoneMsgService.sendMessage(bizOrderInstallPlanAdvanceApply.getItemManagerId(), bizOrderInstallPlanAdvanceApply.getItemManagerPhone(), 
					msgContent, SendMsgBusinessType.RELATED_BUSINESS_TYPE_110504, bizOrderInstallPlanAdvanceApply.getId());
			
		} catch (NumberFormatException e) {
			result = "20";
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	/**
	 * 主材可申请复尺日期处理【同意】【复尺】
	 * @param bizOrderInstallPlanAdvanceApply
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveChecksizeAdvanceApplyAgree(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {
		
		String result = "0";
		try {
			//1.当前登录人
			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			
			//2.【提前申请】是否已经处理
			if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){
				result = "1";
				return result;
			}
			//3.确认开工后可申请复尺日期为空
			if(null == bizOrderInstallPlanAdvanceApply.getOldPlanApplyDate()){
				result = "3";
				return result;
			}
			//4.获取提前的天数
			double advanceDays = DateUtils.daysBetween(bizOrderInstallPlanAdvanceApply.getOldPlanApplyDate(),new Date());
			
			//5.生成新的【订单安装项】【订单安装项计划】【复尺】
			saveChecksizeNewAdvanceApply(bizOrderInstallPlanAdvanceApply.getOrderId(),advanceDays);
			
			//6.更新复尺【提前申请】
			saveAdvanceApply(bizOrderInstallPlanAdvanceApply,OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3,empId);
			
			//7.保存复尺【提前申请】对应的状态日志
			bizBusinessStatusLogService.saveBusinessStatusLog(empId, bizOrderInstallPlanAdvanceApply.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9022,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_3, null);
			
			//8.发送短信给项目经理
			//订单（北京东城区南小街-12-1-1-韩信-13301102121）的安装项（木门安装）申请复尺时间调整为（2017-10-09），请及时申请复尺。
			int advanceDaysInt = (int) advanceDays;
			String canApplyTime = DateUtils.formatDate(DateUtils.addDate(bizOrderInstallPlanAdvanceApply.getOldPlanApplyDate(),advanceDaysInt));
			String msgContent = "订单（"+bizOrderInstallPlanAdvanceApply.getCommunityName()+"-"+bizOrderInstallPlanAdvanceApply.getBuildNumber()+"-"
					+bizOrderInstallPlanAdvanceApply.getBuildUnit()+"-"+bizOrderInstallPlanAdvanceApply.getBuildRoom()+"-"
					+bizOrderInstallPlanAdvanceApply.getCustomerName()+"-"+bizOrderInstallPlanAdvanceApply.getCustomerPhone()
					+"）的安装项（"+bizOrderInstallPlanAdvanceApply.getInstallItemName()+"）申请复尺时间调整为（"+canApplyTime+"），请及时申请复尺。";
			bizPhoneMsgService.sendMessage(bizOrderInstallPlanAdvanceApply.getItemManagerId(), bizOrderInstallPlanAdvanceApply.getItemManagerPhone(), 
					msgContent, SendMsgBusinessType.RELATED_BUSINESS_TYPE_110503, bizOrderInstallPlanAdvanceApply.getId());
			
		} catch (NumberFormatException e) {
			result = "20";
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 生成新的【订单安装项】【订单安装项计划】【安装】
	 * @param orderId
	 * @param advanceDays
	 */
	@Transactional(readOnly = false)
	public void saveInstallPlanNewAdvanceApply(Integer orderId, double advanceDays) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("orderId", orderId);
		resultMap.put("advanceDays", advanceDays);
		//生成新的【订单安装项】【安装】
		dao.saveOrderInstallItemForInstallAdvanceApply(resultMap);
		//生成新的【订单安装项计划】【安装】
		dao.saveOrderInstallPlanForInstallAdvanceApply(resultMap);
	}
	
	/**
	 * 生成新的【订单安装项】【订单安装项计划】【复尺】
	 * @param orderId
	 * @param advanceDays
	 */
	@Transactional(readOnly = false)
	public void saveChecksizeNewAdvanceApply(Integer orderId, double advanceDays) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("orderId", orderId);
		resultMap.put("advanceDays", advanceDays);
		//生成新的【订单安装项】【复尺】
		dao.saveOrderInstallItemForChecksizeAdvanceApply(resultMap);
		//生成新的【订单安装项计划】【复尺】
		dao.saveOrderInstallPlanForChecksizeAdvanceApply(resultMap);
	}
	
}