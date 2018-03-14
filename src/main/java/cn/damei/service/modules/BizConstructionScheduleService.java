/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizConstructionSchedule;
import cn.damei.dao.modules.BizConstructionScheduleDao;

/**
 * 工程进度节点Service
 * @author 魏建勇
 * @version 2016-09-05
 */
@Service
@Transactional(readOnly = true)
public class BizConstructionScheduleService extends CrudService<BizConstructionScheduleDao, BizConstructionSchedule> {

	@Autowired
	private BizConstructionScheduleDao BizConstructionScheduleDao;
	
	public BizConstructionSchedule get(String id) {
		return super.get(id);
	}
	
	public List<BizConstructionSchedule> findList(BizConstructionSchedule bizConstructionSchedule) {
		return super.findList(bizConstructionSchedule);
	}
	
	public Page<BizConstructionSchedule> findPage(Page<BizConstructionSchedule> page, BizConstructionSchedule bizConstructionSchedule) {
		return super.findPage(page, bizConstructionSchedule);
	}
	
	@Transactional(readOnly = false)
	public void save(BizConstructionSchedule bizConstructionSchedule) {
		super.save(bizConstructionSchedule);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizConstructionSchedule bizConstructionSchedule) {
		super.delete(bizConstructionSchedule);
	}
	
	@Transactional(readOnly = false)
	public List<BizConstructionSchedule> getConsScheduleByIsOldHouseAndStoreId(String storeId, String houseIsNew, String projectMode) {
		return BizConstructionScheduleDao.getConsScheduleByIsOldHouseAndStoreId(storeId,houseIsNew,projectMode);
	}

	/**
	 * 获取
	 * @return
	 */
	public List<BizConstructionSchedule> getByList() {
		return null;
	}

	public List<BizConstructionSchedule> getByStoreIdList(String storeId) {
		return BizConstructionScheduleDao.getByStoreIdList(storeId);
	}

	/**
	 * 
	 * @param isOldHouse 
	 * @return List
	 */
	public List<BizConstructionSchedule> getByStoreIdAndDelflag(Integer stroeID, String isOldHouse) {
		return BizConstructionScheduleDao.getByStoreIdAndDelflag(stroeID,isOldHouse);
	}

	/**
	 * 获取新房的节点
	 * @param isOldHouse 
	 * @param string 
	 * @return List
	 */
	public List<BizConstructionSchedule> getByEnableOrNewHouse(String stroeID, String isOldHouse) {
		// TODO Auto-generated method stub
		return BizConstructionScheduleDao.getByEnableOrNewHouse(stroeID,isOldHouse);
	}
	
	/**
	 * 获取老房的节点
	 * @return List
	 */
	public List<BizConstructionSchedule> getByEnableOrOldHouse() {
		return BizConstructionScheduleDao.getByEnableOrOldHouse();
	}
	
}