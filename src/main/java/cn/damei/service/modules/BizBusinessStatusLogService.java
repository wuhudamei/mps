/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;

/**
 * 业务状态记录表Service
 * 
 * @author wyb
 * @version 2017-03-16
 */
@Service
@Transactional(readOnly = true)
public class BizBusinessStatusLogService extends CrudService2<BizBusinessStatusLogDao, BizBusinessStatusLog> {

	public BizBusinessStatusLog get(Integer id) {
		return super.get(id);
	}

	public List<BizBusinessStatusLog> findList(BizBusinessStatusLog bizBusinessStatusLog) {
		return super.findList(bizBusinessStatusLog);
	}

	public Page<BizBusinessStatusLog> findPage(Page<BizBusinessStatusLog> page, BizBusinessStatusLog bizBusinessStatusLog) {
		return super.findPage(page, bizBusinessStatusLog);
	}

	@Transactional(readOnly = false)
	public void save(BizBusinessStatusLog bizBusinessStatusLog) {
		super.save(bizBusinessStatusLog);
	}

	@Transactional(readOnly = false)
	public void delete(BizBusinessStatusLog bizBusinessStatusLog) {
		super.delete(bizBusinessStatusLog);
	}

	/**
	 * 保存状态日志
	 * 
	 * @param empId
	 * @param id
	 * @param businessType
	 * @param installPlanStatus
	 * @param installPlanStatusName
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer empId, Integer id, String businessType, String installPlanStatus, String installPlanStatusName) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(id);
		bizBusinessStatusLog.setBusinessStatus(installPlanStatus);
		bizBusinessStatusLog.setBusinessRemarks(installPlanStatusName);
		bizBusinessStatusLog.setBusinessEmployeeId(empId);
		bizBusinessStatusLog.setStatusDatetime(new Date());
		bizBusinessStatusLog.preInsert();
		return dao.insert(bizBusinessStatusLog);
	}

	/**
	 * 
	 * @Title: insertBusinessStatusLog
	 * @Description: TODO
	 * @param @param empId 业务人员员工id
	 * @param @param id 业务类型ID
	 * @param @param businessType 业务类型(常量)
	 * @param @param installPlanStatus 业务状态(一般也是常量)
	 * @param @param installPlanStatusName 业务备注
	 * @param @return
	 * @return Integer
	 * @author ZhangTongWei
	 * @throws
	 */
	@Transactional(readOnly = false)
	public Integer insertBusinessStatusLog(Integer empId, Integer id, String businessType, String installPlanStatus, String installPlanStatusName) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(id);
		bizBusinessStatusLog.setBusinessStatus(installPlanStatus);
		bizBusinessStatusLog.setBusinessRemarks(installPlanStatusName);
		bizBusinessStatusLog.setBusinessEmployeeId(empId);
		bizBusinessStatusLog.setStatusDatetime(new Date());
		bizBusinessStatusLog.preInsert();
		return dao.insert(bizBusinessStatusLog);
	}

	/**
	 * 查询状态日志
	 * 
	 * @param id
	 * @param businessType
	 * @param installPlanStatus
	 * @return
	 */
	public List<BizBusinessStatusLog> findInstallStatusLog(Integer id, String businessType, String installPlanStatus) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(businessType);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(id);
		bizBusinessStatusLog.setBusinessStatus(installPlanStatus);

		return dao.findInstallStatusLog(bizBusinessStatusLog);
	}

	public BizBusinessStatusLog queryOrderPmSettleLog(Integer orderId, String businessType, String businessStatus) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("businessType", businessType);
		param.put("businessStatus", businessStatus);
		param.put("businessOnlyMarkInt", orderId);
		return dao.queryOrderPmSettleLog(param);
	}

	
	/**
	 * 保存状态日志
	 * 
	 * @param id
	 * @param orderInstallItemId
	 * @param businessType
	 * @param status
	 * @param remarks
	 * @param dataday2
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer managerId, Integer orderInstallItemId, String businessType,
			String status, String remarks, String dataday2) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		// 1.唯一标识
		bizBusinessStatusLog.setBusinessOnlyMarkInt(orderInstallItemId);
		// 2.业务类型
		bizBusinessStatusLog.setBusinessType(businessType);
		// 3.业务状态
		bizBusinessStatusLog.setBusinessStatus(status);
		// 4.业务备注
		bizBusinessStatusLog.setBusinessRemarks(remarks);
		// 5.状态时间
		bizBusinessStatusLog.setStatusDatetime(new Date());
		// 6.业务人员员工id
		bizBusinessStatusLog.setBusinessEmployeeId(managerId);

		bizBusinessStatusLog.setRemarks(dataday2);
		bizBusinessStatusLog.preInsert();

		dao.insert(bizBusinessStatusLog);

		return bizBusinessStatusLog.getId();

	}

	/**
	 * 查询5分钟内操作的数量
	 * @param businessOnlyMarkInt
	 * @param businessType
	 * @return
	 */
	public Integer findFiveTimeApplyCount(Integer businessOnlyMarkInt, String businessType) {
		
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		// 1.唯一标识
		bizBusinessStatusLog.setBusinessOnlyMarkInt(businessOnlyMarkInt);
		// 2.业务类型
		bizBusinessStatusLog.setBusinessType(businessType);

		return dao.findFiveTimeApplyCount(bizBusinessStatusLog);
	}
	
}