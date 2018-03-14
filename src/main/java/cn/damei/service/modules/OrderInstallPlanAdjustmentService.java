/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.OrderInstallPlanAdjustmentDao;
import cn.damei.entity.modules.OrderInstallPlanAdjustment;

/**
 * 主材可申请安装/复尺日期查询Service
 * @author wyb
 */
@Service
@Transactional(readOnly = true)
public class OrderInstallPlanAdjustmentService extends CrudService2<OrderInstallPlanAdjustmentDao, OrderInstallPlanAdjustment> {

	@Autowired
	private BizOrderInstallPlanAdvanceApplyService bizOrderInstallPlanAdvanceApplyService;
	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	
	public OrderInstallPlanAdjustment get(Integer id) {
		return super.get(id);
	}
	
	/**
	 * 主材可申请安装日期查询【列表页】【安装】
	 */
	public Page<OrderInstallPlanAdjustment> findInstallPage(Page<OrderInstallPlanAdjustment> page, OrderInstallPlanAdjustment orderInstallPlanAdjustment) {
		orderInstallPlanAdjustment.setPage(page);
		page.setList(dao.findInstallList(orderInstallPlanAdjustment));
		return page;
	}
	/**
	 * 主材可申请复尺日期查询【列表页】【复尺】
	 */
	public Page<OrderInstallPlanAdjustment> findChecksizePage(Page<OrderInstallPlanAdjustment> page, OrderInstallPlanAdjustment orderInstallPlanAdjustment) {
		orderInstallPlanAdjustment.setPage(page);
		page.setList(dao.findChecksizeList(orderInstallPlanAdjustment));
		return page;
	}
	

	

	
	/**
	 * 门店和工程模式权限控制 
	 * @param bizOrderInstallPlanAdvanceApply
	 * @param model
	 */
	public void storeIdAndProjectMode(OrderInstallPlanAdjustment orderInstallPlanAdjustment, Model model) {
		User user = UserUtils.getUser();
		//门店
		if(null==orderInstallPlanAdjustment.getStoreId()){
			if(StringUtils.isNotBlank(user.getStoreId())){
				orderInstallPlanAdjustment.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		//工程模式
		if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
			model.addAttribute("gongcheng", true);
		}else{
			orderInstallPlanAdjustment.setProjectMode(user.getProjectMode());
		}
	}



	
	/**
	 * 主材可申请安装日期查询【同意】【安装】
	 * @param orderInstallPlanAdjustment
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveOrderInstallPlanInstallDateApply(OrderInstallPlanAdjustment orderInstallPlanAdjustment) {

		String result = "0";
		try {
			//1.当前登录人
			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			
			//2.您刚刚该安装项计划的可申请安装日期，请耐心等待五分钟后再次操作。
			Integer count = bizBusinessStatusLogService.findFiveTimeApplyCount(orderInstallPlanAdjustment.getId(),BusinessLogConstantUtil.BUSINESS_TYPE_9023);
			if(null != count && count > 0){
				result = "2";
				return result;
			}
			
			//3.确认开工后可申请安装日期为空
			if(null == orderInstallPlanAdjustment.getPlanIntoDate()){
				result = "3";
				return result;
			}
			//4.修改后可申请安装日期为空
			if(null == orderInstallPlanAdjustment.getNewPlanApplyDate()){
				result = "4";
				return result;
			}
			
			//5.获取提前的天数
			double advanceDays = DateUtils.daysBetween(orderInstallPlanAdjustment.getPlanIntoDate(),orderInstallPlanAdjustment.getNewPlanApplyDate());
			if(advanceDays == 0d){
				result = "5";
				return result;
			}
			
			//6.生成新的【订单安装项】【订单安装项计划】【安装】
			bizOrderInstallPlanAdvanceApplyService.saveInstallPlanNewAdvanceApply(orderInstallPlanAdjustment.getOrderId(),advanceDays);
			
			//7.保存 修改申请安装日期 对应的状态日志
			String oldPlanIntoDateString = DateUtils.formatDate(orderInstallPlanAdjustment.getPlanIntoDate());
			String newPlanApplyDateString = DateUtils.formatDate(orderInstallPlanAdjustment.getNewPlanApplyDate());
			bizBusinessStatusLogService.saveBusinessStatusLog(empId, orderInstallPlanAdjustment.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9023,
					orderInstallPlanAdjustment.getStatus(),oldPlanIntoDateString,newPlanApplyDateString);
			
		} catch (NumberFormatException e) {
			result = "20";
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 主材可申请复尺日期查询【同意】【复尺】
	 * @param orderInstallPlanAdjustment
	 * @return
	 */
	@Transactional(readOnly = false)
	public String saveOrderInstallPlanChecksizeDateApply(OrderInstallPlanAdjustment orderInstallPlanAdjustment) {
		
		String result = "0";
		try {
			//1.当前登录人
			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			
			//2.您刚刚该安装项计划的可申请复尺日期，请耐心等待五分钟后再次操作。
			Integer count = bizBusinessStatusLogService.findFiveTimeApplyCount(orderInstallPlanAdjustment.getId(),BusinessLogConstantUtil.BUSINESS_TYPE_9024);
			if(null != count && count > 0){
				result = "2";
				return result;
			}
			
			//3.确认开工后可申请复尺日期为空
			if(null == orderInstallPlanAdjustment.getAllowApplyChecksizeDate()){
				result = "3";
				return result;
			}
			//4.修改后可申请复尺日期为空
			if(null == orderInstallPlanAdjustment.getNewPlanApplyDate()){
				result = "4";
				return result;
			}
			//5.获取提前的天数
			double advanceDays = DateUtils.daysBetween(orderInstallPlanAdjustment.getAllowApplyChecksizeDate(),orderInstallPlanAdjustment.getNewPlanApplyDate());
			if(advanceDays == 0d){
				result = "5";
				return result;
			}
			
			//6.生成新的【订单安装项】【订单安装项计划】【复尺】
			bizOrderInstallPlanAdvanceApplyService.saveChecksizeNewAdvanceApply(orderInstallPlanAdjustment.getOrderId(),advanceDays);
			
			//7.保存 修改申请复尺日期 对应的状态日志
			String oldAllowApplyChecksizeDate = DateUtils.formatDate(orderInstallPlanAdjustment.getAllowApplyChecksizeDate());
			String newPlanApplyDateString = DateUtils.formatDate(orderInstallPlanAdjustment.getNewPlanApplyDate());
			bizBusinessStatusLogService.saveBusinessStatusLog(empId, orderInstallPlanAdjustment.getId(), BusinessLogConstantUtil.BUSINESS_TYPE_9024,
					orderInstallPlanAdjustment.getStatus(),oldAllowApplyChecksizeDate,newPlanApplyDateString);
			
		} catch (NumberFormatException e) {
			result = "20";
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}