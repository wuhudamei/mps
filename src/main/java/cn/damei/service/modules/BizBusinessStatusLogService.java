
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

	

	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer managerId, Integer orderInstallItemId, String businessType,
			String status, String remarks, String dataday2) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

		bizBusinessStatusLog.setBusinessOnlyMarkInt(orderInstallItemId);

		bizBusinessStatusLog.setBusinessType(businessType);

		bizBusinessStatusLog.setBusinessStatus(status);

		bizBusinessStatusLog.setBusinessRemarks(remarks);

		bizBusinessStatusLog.setStatusDatetime(new Date());

		bizBusinessStatusLog.setBusinessEmployeeId(managerId);

		bizBusinessStatusLog.setRemarks(dataday2);
		bizBusinessStatusLog.preInsert();

		dao.insert(bizBusinessStatusLog);

		return bizBusinessStatusLog.getId();

	}


	public Integer findFiveTimeApplyCount(Integer businessOnlyMarkInt, String businessType) {
		
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

		bizBusinessStatusLog.setBusinessOnlyMarkInt(businessOnlyMarkInt);

		bizBusinessStatusLog.setBusinessType(businessType);

		return dao.findFiveTimeApplyCount(bizBusinessStatusLog);
	}
	
}