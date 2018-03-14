/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 材料自采报销单Service
 * @author wyb
 * @version 2017-06-22
 */
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

	/**
	 * 保存自采材料报销状态日志
	 * @param materialSelfbuyReimburseId
	 * @param materialSelfbuyReimburseStatusRemarks 
	 * @param materialSelfbuyReimburseStatus 
	 * @param managerId 
	 * @param businessType 
	 * @return
	 */
	@Transactional(readOnly = false)
	public Integer saveBusinessStatusLog(Integer materialSelfbuyReimburseId, Integer managerId, String materialSelfbuyReimburseStatus,
			String reimburseRemarks, String businessType) {
		
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		//1.唯一标识
		bizBusinessStatusLog.setBusinessOnlyMarkInt(materialSelfbuyReimburseId);
		//2.业务类型
		bizBusinessStatusLog.setBusinessType(businessType);
		//3.业务状态
		bizBusinessStatusLog.setBusinessStatus(materialSelfbuyReimburseStatus);
		//4.业务备注
		bizBusinessStatusLog.setBusinessRemarks(reimburseRemarks);
		//5.状态时间
		bizBusinessStatusLog.setStatusDatetime(new Date());
		//6.业务人员员工id
		bizBusinessStatusLog.setBusinessEmployeeId(managerId);
		bizBusinessStatusLog.preInsert();
		
		bizBusinessStatusLogDao.insert(bizBusinessStatusLog);
		
		return bizBusinessStatusLog.getId();
	}

	/**
	 * 更新初次申请的自采材料报销
	 * @param relatedReimburseId
	 * @param reimburseRemarks 
	 * @param materialSelfbuyReimburseStatusRemarks 
	 * @return
	 */
	@Transactional(readOnly=false)
	public boolean updateMaterialSelfbuyReimburse(Integer relatedReimburseId, String materialSelfbuyReimburseStatus, String materialSelfbuyReimburseStatusRemarks) {
		
		BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse = new BizMaterialSelfbuyReimburse();
		
		// 1.报销状态
		bizMaterialSelfbuyReimburse.setReimburseStatus(materialSelfbuyReimburseStatus);
		// 2.报销状态日期时间
		bizMaterialSelfbuyReimburse.setReimburseStatusDatetime(new Date());
		// 3.报销状态说明
		bizMaterialSelfbuyReimburse.setReimburseStatusRemarks(materialSelfbuyReimburseStatusRemarks);
		//4.id
		bizMaterialSelfbuyReimburse.setId(relatedReimburseId);
		bizMaterialSelfbuyReimburse.preUpdate();
		
		return (dao.updateMaterialSelfbuyReimburse(bizMaterialSelfbuyReimburse))?true:false;
		
	}

	/**
	 * 根据本次自采材料id查询详情
	 * @param materialId
	 * @return
	 */
	public BizMaterialSelfbuyReimburse findMaterialAndOrderByMaterialId(Integer materialId) {
		return dao.findMaterialAndOrderByMaterialId(materialId);
	}

	/**
	 * 自采材料报销详情
	 * @param materialId
	 * @return
	 */
	public List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseDetails(Integer materialId) {
		return dao.findMaterialSelfbuyReimburseDetails(materialId);
	}

	/**
	 * 自采材料报销 状态 详情
	 * @param materialId
	 * @param businessType
	 * @return
	 */
	public List<ApplyMaterialSelfbuyReimburseStatusLog> findMaterialStatusLogDetails(Integer materialId,
			String businessType) {
		
		ApplyMaterialSelfbuyReimburseStatusLog applyMaterialSelfbuyReimburseStatusLog = new ApplyMaterialSelfbuyReimburseStatusLog();
		//关联id
		applyMaterialSelfbuyReimburseStatusLog.setRelatedReimburseId(materialId);
		//关联状态类型
		applyMaterialSelfbuyReimburseStatusLog.setBusinessType(businessType);
		
		return dao.findMaterialStatusLogDetails(applyMaterialSelfbuyReimburseStatusLog);
	}
	
}