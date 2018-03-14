/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import cn.damei.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizPmAttendCnfg;
import cn.damei.entity.modules.BizPmAttendCnfgStar;
import cn.damei.dao.modules.BizPmAttendCnfgDao;

/**
 * 项目经理考勤基础设置Service
 * @author lzm
 * @version 2017-08-02
 */
@Service
@Transactional(readOnly = true)
public class BizPmAttendCnfgService extends CrudService<BizPmAttendCnfgDao, BizPmAttendCnfg> {
	@Autowired
	private BizPmAttendCnfgDao bizPmAttendCnfgDao;
	
	public BizPmAttendCnfg get(String id) {
		return super.get(id);
	}
	
	public List<BizPmAttendCnfg> findList(BizPmAttendCnfg bizPmAttendCnfg) {
		return super.findList(bizPmAttendCnfg);
	}
	
	public Page<BizPmAttendCnfg> findPage(Page<BizPmAttendCnfg> page, BizPmAttendCnfg bizPmAttendCnfg) {
		return super.findPage(page, bizPmAttendCnfg);
	}
	
	@Transactional(readOnly = false)
	public void saveOrInsert(BizPmAttendCnfg bizPmAttendCnfg) {
		if(bizPmAttendCnfg.getId()==null||(bizPmAttendCnfg.getId()!=null&&bizPmAttendCnfg.getId().equals(""))){
			dao.insert(bizPmAttendCnfg);
			System.out.println();
		}else{
			dao.update(bizPmAttendCnfg);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmAttendCnfg bizPmAttendCnfg) {
		super.delete(bizPmAttendCnfg);
		bizPmAttendCnfgDao.deleteStarByCnfgId(bizPmAttendCnfg.getId());
	}
	@Transactional(readOnly = false)
	public void saveBizPmAttendCnfgStarList(List<BizPmAttendCnfgStar> bizPmAttendCnfgStarList) {
		bizPmAttendCnfgDao.saveBizPmAttendCnfgStarList(bizPmAttendCnfgStarList);
	}
	public List<BizPmAttendCnfgStar> findBizPmAttendCnfgStarListByCnfgId(String id){
		return bizPmAttendCnfgDao.findBizPmAttendCnfgStarListByCnfgId(id);
	}
	@Transactional(readOnly = false)
	public void updateBizPmAttendCnfgStarListById(BizPmAttendCnfgStar bizPmAttendCnfgStar){
		bizPmAttendCnfgDao.updateBizPmAttendCnfgStarListById(bizPmAttendCnfgStar);
	}
	@Transactional(readOnly = false)
	public void deleteStarById(String id){
		bizPmAttendCnfgDao.deleteStarById(id);
	}
	public int checkRepeateByStorIdAndMonth(String storId,String month,String id,String projectMode){
		return  bizPmAttendCnfgDao.checkRepeateByStorIdAndMonth(storId, month,id,projectMode);
	}
	@Transactional(readOnly = false)
	public void updateIsEnabledById(String id,String isEnabled){
		if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(isEnabled)){
			bizPmAttendCnfgDao.updateIsEnabledById(id,isEnabled);
		}


	}
}