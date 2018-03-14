
package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Manager.ApplyMaterialSelfbuyReimburseStatusLog;
import cn.damei.entity.modules.BizMaterialSelfbuyReimburse;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.dao.modules.BizMaterialSelfbuyReimburseDao;


@Service
@Transactional(readOnly = true)
public class BizMaterialSelfbuyReimburseService extends CrudService2<BizMaterialSelfbuyReimburseDao, BizMaterialSelfbuyReimburse> {

	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
	
	public BizMaterialSelfbuyReimburse get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMaterialSelfbuyReimburse> findList(BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse) {
		return super.findList(bizMaterialSelfbuyReimburse);
	}
	
	public Page<BizMaterialSelfbuyReimburse> findPage(Page<BizMaterialSelfbuyReimburse> page, BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse) {
		return super.findPage(page, bizMaterialSelfbuyReimburse);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse) {
		super.save(bizMaterialSelfbuyReimburse);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse) {
		super.delete(bizMaterialSelfbuyReimburse);
	}


	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer materialSelfbuyReimburseId, Integer managerId, String materialSelfbuyReimburseStatus,
			String reimburseRemarks, String businessType) {
		
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

		bizBusinessStatusLog.setBusinessOnlyMarkInt(materialSelfbuyReimburseId);

		bizBusinessStatusLog.setBusinessType(businessType);

		bizBusinessStatusLog.setBusinessStatus(materialSelfbuyReimburseStatus);

		bizBusinessStatusLog.setBusinessRemarks(reimburseRemarks);

		bizBusinessStatusLog.setStatusDatetime(new Date());

		bizBusinessStatusLog.setBusinessEmployeeId(managerId);
		bizBusinessStatusLog.preInsert();
		
		bizBusinessStatusLogDao.insert(bizBusinessStatusLog);
		
		return bizBusinessStatusLog.getId();
	}


	@Transactional(readOnly=false)
	public boolean updateMaterialSelfbuyReimburse(Integer relatedReimburseId, String materialSelfbuyReimburseStatus, String materialSelfbuyReimburseStatusRemarks) {
		
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();
		

		bizMaterialSelfbuyReimburse.setReimburseStatus(materialSelfbuyReimburseStatus);

		bizMaterialSelfbuyReimburse.setReimburseStatusDatetime(new Date());

		bizMaterialSelfbuyReimburse.setReimburseStatusRemarks(materialSelfbuyReimburseStatusRemarks);

		bizMaterialSelfbuyReimburse.setId(relatedReimburseId);
		bizMaterialSelfbuyReimburse.preUpdate();
		
		return (dao.updateMaterialSelfbuyReimburse(bizMaterialSelfbuyReimburse))?true:false;
		
	}


	public BizMaterialSelfbuyReimburse findMaterialAndOrderByMaterialId(Integer materialId) {
		return dao.findMaterialAndOrderByMaterialId(materialId);
	}


	public List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseDetails(Integer materialId) {
		return dao.findMaterialSelfbuyReimburseDetails(materialId);
	}


	public List<ApplyMaterialSelfbuyReimburseStatusLog> findMaterialStatusLogDetails(Integer materialId,
			String businessType) {
		
		ApplyMaterialSelfbuyReimburseStatusLog applyMaterialSelfbuyReimburseStatusLog = new ApplyMaterialSelfbuyReimburseStatusLog();

		applyMaterialSelfbuyReimburseStatusLog.setRelatedReimburseId(materialId);

		applyMaterialSelfbuyReimburseStatusLog.setBusinessType(businessType);
		
		return dao.findMaterialStatusLogDetails(applyMaterialSelfbuyReimburseStatusLog);
	}
	
}