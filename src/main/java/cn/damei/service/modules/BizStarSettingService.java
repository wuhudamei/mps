package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizStarSettingDao;
import cn.damei.entity.modules.BizStarSetting;

/**
 * 工人星级设置Service
 * @author ws
 * @version 2017-09-05
 */
@Service
@Transactional(readOnly = true)
public class BizStarSettingService extends CrudService2<BizStarSettingDao, BizStarSetting> {
	@Autowired
	BizStarSettingDao bizStarSettingDao;

	public BizStarSetting get(Integer id) {
		return super.get(id);
	}
	
	public List<BizStarSetting> findList(BizStarSetting bizStarSetting) {
		return super.findList(bizStarSetting);
	}
	
	public Page<BizStarSetting> findPage(Page<BizStarSetting> page, BizStarSetting bizStarSetting) {
		return super.findPage(page, bizStarSetting);
	}
	
	@Transactional(readOnly = false)
	public void save(BizStarSetting bizStarSetting) {
		super.save(bizStarSetting);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizStarSetting bizStarSetting) {
		super.delete(bizStarSetting);
	}


	@Transactional(readOnly = false)
	public void update(BizStarSetting bizStarSetting) {
		bizStarSettingDao.update(bizStarSetting);
	}

	public List<BizStarSetting> findIsCopy(BizStarSetting bizStarSetting) {
		return bizStarSettingDao.findIsCopy(bizStarSetting);
	}

	public List<BizStarSetting> findIsCopyStar(BizStarSetting bizStarSetting) {
		return bizStarSettingDao.findIsCopyStar(bizStarSetting);
	}
	
	public List<BizStarSetting> findIsNotCopyStar(Integer storeId,String projectMode, Integer star) {
		return bizStarSettingDao.findIsNotCopyStar(storeId,projectMode,star);
	}
}