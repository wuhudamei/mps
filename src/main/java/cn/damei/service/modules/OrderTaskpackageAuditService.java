/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.modules.OrderTaskpackageAudit;
import cn.damei.entity.modules.Dict;
import cn.damei.common.utils.UserUtils;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.DropModel;
import cn.damei.dao.modules.OrderTaskpackageAuditDao;

/**
 * 订单任务包审核Service
 * @author llp
 * @version 2016-09-24
 */
@Service
@Transactional(readOnly = true)
public class OrderTaskpackageAuditService extends CrudService<OrderTaskpackageAuditDao, OrderTaskpackageAudit> {

	@Autowired
	private OrderTaskpackageAuditDao orderTaskpackageAuditDao;
	
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
	
	public OrderTaskpackageAudit get(String id) {
		return super.get(id);
	}
	
	public List<OrderTaskpackageAudit> findList(OrderTaskpackageAudit orderTaskpackageAudit) {
		return super.findList(orderTaskpackageAudit);
	}
	
	public Page<OrderTaskpackageAudit> findPage(Page<OrderTaskpackageAudit> page, OrderTaskpackageAudit orderTaskpackageAudit) {
		return super.findPage(page, orderTaskpackageAudit);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderTaskpackageAudit orderTaskpackageAudit) {
		super.save(orderTaskpackageAudit);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderTaskpackageAudit orderTaskpackageAudit) {
		super.delete(orderTaskpackageAudit);
	}

	@Transactional(readOnly = false)
	public void updateOrderTaskpackageByPackageStatusId(String auditResult,String auditRemarks, String id) {
		orderTaskpackageAuditDao.updateOrderTaskpackageByPackageStatusId(auditResult,auditRemarks,id);
	}
	
	/**
	 * 预算员审核
	 * return boolean
	 * @param request 
	 * @param id,auditResult,auditRemarks
	 */
	@Transactional(readOnly = false)
	public boolean updateOrderTaskpackageByPackageStatusIdReturn(String auditResult,String auditRemarks, String id, HttpServletRequest request) {
		boolean flag = false;
		flag = (orderTaskpackageAuditDao.updateOrderTaskpackageByPackageStatusIdReturn(auditResult,auditRemarks,id)) ? true :false;
		logger.info("OrderTaskpackageAuditService updateOrderTaskpackageByPackageStatusId " + flag);
		// 添加状态日志信息
		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		statusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_1001);
		statusLog.setBusinessOnlyMarkInt(Integer.parseInt(id));
		statusLog.setBusinessStatus(ConstantUtils.ORDER_TASKPACKAGE_STATUS_20);//预算员审核通过
		statusLog.setStatusDatetime(new Date());
		String empId = UserUtils.getUser1().getEmpId();
		if (null != empId) {
			statusLog.setBusinessEmployeeId(Integer.parseInt(empId));
		}
		statusLog.setBusinessRemarks("预算员审核通过");
		statusLog.preInsert();
		bizBusinessStatusLogDao.insert(statusLog);
		return flag;
	}

	/**
	 *	根据主键查询数据 
	 * @param id
	 */
	public OrderTaskpackageAudit getById(String id) {
		return orderTaskpackageAuditDao.getById(id);
	}

	public OrderTaskpackageAudit getByIdOrEmployee(String id) {
		// TODO Auto-generated method stub
		return orderTaskpackageAuditDao.getByIdOrEmployee(id);
	}
	
	public List<Dict> getPackageStateid(Integer num1, Integer num2){
		return orderTaskpackageAuditDao.getPackageStateid(num1,num2);
	}
	
	public List<DropModel> findPackNameByStoreId(String storeId){
		return orderTaskpackageAuditDao.findPackNameByStoreId(storeId);
	}
	
	public Integer getStayCountByStoreId(OrderTaskpackageAudit orderTaskpackageAudit){
		return orderTaskpackageAuditDao.getStayCountByStoreId(orderTaskpackageAudit);
	}

	public Integer getSpecialStayCountByStoreId(OrderTaskpackageAudit orderTaskpackageAudit){
		return orderTaskpackageAuditDao.getSpecialStayCountByStoreId(orderTaskpackageAudit);
	}

	public Page<OrderTaskpackageAudit> findSpecialPage(Page<OrderTaskpackageAudit> page, OrderTaskpackageAudit orderTaskpackageAudit) {
		orderTaskpackageAudit.setPage(page);
		page.setList(orderTaskpackageAuditDao.findSpecialList(orderTaskpackageAudit));
		return page;
	}

	public Page<OrderTaskpackageAudit> myFindPage(Page<OrderTaskpackageAudit> page,
			OrderTaskpackageAudit orderTaskpackageAudit) {
		orderTaskpackageAudit.setPage(page);
		page.setList(orderTaskpackageAuditDao.myFindList(orderTaskpackageAudit));
		return page;
	}
}