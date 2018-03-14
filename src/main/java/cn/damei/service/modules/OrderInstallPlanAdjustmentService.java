
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
	

	public Page<OrderInstallPlanAdjustment> findInstallPage(Page<OrderInstallPlanAdjustment> page, OrderInstallPlanAdjustment orderInstallPlanAdjustment) {
		orderInstallPlanAdjustment.setPage(page);
		page.setList(dao.findInstallList(orderInstallPlanAdjustment));
		return page;
	}

	public Page<OrderInstallPlanAdjustment> findChecksizePage(Page<OrderInstallPlanAdjustment> page, OrderInstallPlanAdjustment orderInstallPlanAdjustment) {
		orderInstallPlanAdjustment.setPage(page);
		page.setList(dao.findChecksizeList(orderInstallPlanAdjustment));
		return page;
	}
	

	

	

	public void storeIdAndProjectMode(OrderInstallPlanAdjustment orderInstallPlanAdjustment, Model model) {
		User user = UserUtils.getUser();

		if(null==orderInstallPlanAdjustment.getStoreId()){
			if(StringUtils.isNotBlank(user.getStoreId())){
				orderInstallPlanAdjustment.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}

		if(StringUtils.isBlank(user.getProjectMode())||user.getProjectMode().equals("3")){
			model.addAttribute("gongcheng", true);
		}else{
			orderInstallPlanAdjustment.setProjectMode(user.getProjectMode());
		}
	}



	

	@Transactional(readOnly = false)
	public String saveOrderInstallPlanInstallDateApply(OrderInstallPlanAdjustment orderInstallPlanAdjustment) {

		String result = "0";
		try {

			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			

			Integer count = bizBusinessStatusLogService.findFiveTimeApplyCount(orderInstallPlanAdjustment.getId(),BusinessLogConstantUtil.BUSINESS_TYPE_9023);
			if(null != count && count > 0){
				result = "2";
				return result;
			}
			

			if(null == orderInstallPlanAdjustment.getPlanIntoDate()){
				result = "3";
				return result;
			}

			if(null == orderInstallPlanAdjustment.getNewPlanApplyDate()){
				result = "4";
				return result;
			}
			

			double advanceDays = DateUtils.daysBetween(orderInstallPlanAdjustment.getPlanIntoDate(),orderInstallPlanAdjustment.getNewPlanApplyDate());
			if(advanceDays == 0d){
				result = "5";
				return result;
			}
			

			bizOrderInstallPlanAdvanceApplyService.saveInstallPlanNewAdvanceApply(orderInstallPlanAdjustment.getOrderId(),advanceDays);
			

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

	@Transactional(readOnly = false)
	public String saveOrderInstallPlanChecksizeDateApply(OrderInstallPlanAdjustment orderInstallPlanAdjustment) {
		
		String result = "0";
		try {

			User user = UserUtils.getUser();
			Integer empId = null;
			if(null!=user && null!=user.getEmpId() && !user.getEmpId().equals("")){
				empId = Integer.valueOf(user.getEmpId());
			}
			

			Integer count = bizBusinessStatusLogService.findFiveTimeApplyCount(orderInstallPlanAdjustment.getId(),BusinessLogConstantUtil.BUSINESS_TYPE_9024);
			if(null != count && count > 0){
				result = "2";
				return result;
			}
			

			if(null == orderInstallPlanAdjustment.getAllowApplyChecksizeDate()){
				result = "3";
				return result;
			}

			if(null == orderInstallPlanAdjustment.getNewPlanApplyDate()){
				result = "4";
				return result;
			}

			double advanceDays = DateUtils.daysBetween(orderInstallPlanAdjustment.getAllowApplyChecksizeDate(),orderInstallPlanAdjustment.getNewPlanApplyDate());
			if(advanceDays == 0d){
				result = "5";
				return result;
			}
			

			bizOrderInstallPlanAdvanceApplyService.saveChecksizeNewAdvanceApply(orderInstallPlanAdjustment.getOrderId(),advanceDays);
			

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