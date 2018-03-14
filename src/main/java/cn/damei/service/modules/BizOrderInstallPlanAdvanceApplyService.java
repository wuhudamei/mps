
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


	public BizOrderInstallPlanAdvanceApply findInstallPlanAdvanceApplyLastRecord(Integer orderInstallPlanId, String applyType) {
		
		BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply = new BizOrderInstallPlanAdvanceApply();

		bizOrderInstallPlanAdvanceApply.setOrderInstallPlanId(orderInstallPlanId);

		bizOrderInstallPlanAdvanceApply.setApplyType(applyType);
		
		return dao.findInstallPlanAdvanceApplyLastRecord(bizOrderInstallPlanAdvanceApply);
	}


	public Integer findInstallPlanAdvanceApplyCount(Integer orderInstallPlanId, String applyType) {
		
		BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply = new BizOrderInstallPlanAdvanceApply();

		bizOrderInstallPlanAdvanceApply.setOrderInstallPlanId(orderInstallPlanId);

		bizOrderInstallPlanAdvanceApply.setApplyType(applyType);
		
		return dao.findInstallPlanAdvanceApplyCount(bizOrderInstallPlanAdvanceApply);
	}
	

	@Transactional(readOnly = false)
	public Integer insertOrderInstallPlanAdvanceApply(Integer orderId, String installItemName,
			Date oldPlanApplyDate, Integer orderInstallItemId,
			Manager manager, String applyType, String dealStatus) {

		Date date = new Date();

		BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply = new BizOrderInstallPlanAdvanceApply();

		bizOrderInstallPlanAdvanceApply.setOrderId(orderId);

		bizOrderInstallPlanAdvanceApply.setOrderInstallPlanId(orderInstallItemId);

		bizOrderInstallPlanAdvanceApply.setApplyType(applyType);

		bizOrderInstallPlanAdvanceApply.setInstallItemName(installItemName);

		bizOrderInstallPlanAdvanceApply.setOldPlanApplyDate(oldPlanApplyDate);

		bizOrderInstallPlanAdvanceApply.setDealStatus(dealStatus);

		bizOrderInstallPlanAdvanceApply.setDealEmployeeId(manager.getId());

		bizOrderInstallPlanAdvanceApply.setDelaTime(date);

		bizOrderInstallPlanAdvanceApply.preInsert();

		dao.insert(bizOrderInstallPlanAdvanceApply);

		return bizOrderInstallPlanAdvanceApply.getId();
	}


	@Transactional(readOnly = false)
	public void saveAdvanceApply(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply,String dealStatus,Integer dealEmployeeId) {
		

		bizOrderInstallPlanAdvanceApply.setDealStatus(dealStatus);

		bizOrderInstallPlanAdvanceApply.setDealEmployeeId(dealEmployeeId);

		bizOrderInstallPlanAdvanceApply.setDelaTime(new Date());
		
		bizOrderInstallPlanAdvanceApply.preUpdate();
		
		dao.update(bizOrderInstallPlanAdvanceApply);
		
	}
	

	public void storeIdAndProjectMode(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply, Model model) {
		User user = UserUtils.getUser();

		if(null==bizOrderInstallPlanAdvanceApply.getStoreId()){
			if(StringUtils.isNotBlank(user.getStoreId())){
				bizOrderInstallPlanAdvanceApply.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
			model.addAttribute("gongcheng", true);
		}else{
			bizOrderInstallPlanAdvanceApply.setProjectMode(user.getProjectMode());
		}
	}


	@Transactional(readOnly = false)
	public String saveInstallAdvanceApplyRefuse(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {
		
		String result = "0";
		
		try {

			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			

			if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){
				result = "1";
				return result;
			}
			

			saveAdvanceApply(bizOrderInstallPlanAdvanceApply,OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_2,empId);
			

			bizBusinessStatusLogService.saveBusinessStatusLog(empId, bizOrderInstallPlanAdvanceApply.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9021,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_2,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_2, null);
			


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


	

	@Transactional(readOnly = false)
	public String saveInstallAdvanceApplyAgree(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {

		String result = "0";
		try {

			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			

			if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){
				result = "2";
				return result;
			}

			if(null == bizOrderInstallPlanAdvanceApply.getOldPlanApplyDate()){
				result = "3";
				return result;
			}

			double advanceDays = DateUtils.daysBetween(bizOrderInstallPlanAdvanceApply.getOldPlanApplyDate(),new Date());
			

			saveInstallPlanNewAdvanceApply(bizOrderInstallPlanAdvanceApply.getOrderId(),advanceDays);
			

			saveAdvanceApply(bizOrderInstallPlanAdvanceApply,OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3,empId);
			

			bizBusinessStatusLogService.saveBusinessStatusLog(empId, bizOrderInstallPlanAdvanceApply.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9021,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_3, null);
			


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
	

	@Transactional(readOnly = false)
	public String saveChecksizeAdvanceApplyRefuse(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {
		
		String result = "0";
		
		try {

			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			

			if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){
				result = "1";
				return result;
			}
			

			saveAdvanceApply(bizOrderInstallPlanAdvanceApply,OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_2,empId);
			

			bizBusinessStatusLogService.saveBusinessStatusLog(empId, bizOrderInstallPlanAdvanceApply.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9022,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_2,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_2, null);
			


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
	
	
	

	@Transactional(readOnly = false)
	public String saveChecksizeAdvanceApplyAgree(BizOrderInstallPlanAdvanceApply bizOrderInstallPlanAdvanceApply) {
		
		String result = "0";
		try {

			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			

			if(!OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_1.equals(bizOrderInstallPlanAdvanceApply.getDealStatus())){
				result = "1";
				return result;
			}

			if(null == bizOrderInstallPlanAdvanceApply.getOldPlanApplyDate()){
				result = "3";
				return result;
			}

			double advanceDays = DateUtils.daysBetween(bizOrderInstallPlanAdvanceApply.getOldPlanApplyDate(),new Date());
			

			saveChecksizeNewAdvanceApply(bizOrderInstallPlanAdvanceApply.getOrderId(),advanceDays);
			

			saveAdvanceApply(bizOrderInstallPlanAdvanceApply,OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3,empId);
			

			bizBusinessStatusLogService.saveBusinessStatusLog(empId, bizOrderInstallPlanAdvanceApply.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9022,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_3,
					OrderInstallPlanAdvanceApplyConstantUtil.ORDER_INSTALL_PLAN_ADVANCE_APPLY_STATUS_NAME_3, null);
			


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
	

	@Transactional(readOnly = false)
	public void saveInstallPlanNewAdvanceApply(Integer orderId, double advanceDays) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("orderId", orderId);
		resultMap.put("advanceDays", advanceDays);

		dao.saveOrderInstallItemForInstallAdvanceApply(resultMap);

		dao.saveOrderInstallPlanForInstallAdvanceApply(resultMap);
	}
	

	@Transactional(readOnly = false)
	public void saveChecksizeNewAdvanceApply(Integer orderId, double advanceDays) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("orderId", orderId);
		resultMap.put("advanceDays", advanceDays);

		dao.saveOrderInstallItemForChecksizeAdvanceApply(resultMap);

		dao.saveOrderInstallPlanForChecksizeAdvanceApply(resultMap);
	}
	
}