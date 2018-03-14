/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderInstallItemProblemDao;
import cn.damei.dao.modules.BusinessWallAndFloorProblemDao;

/**
 * 墙地砖问题上报
 */
@Service
@Transactional(readOnly = true)
public class BusinessWallAndFloorProblemService extends CrudService2<BusinessWallAndFloorProblemDao, BizOrderInstallItemProblemVo> {

	@Autowired
	private BusinessWallAndFloorProblemDao businessWallAndFloorProblemDao; 
	@Autowired
	private BizOrderInstallItemProblemDao bizOrderInstallItemProblemDao; 
	
	public BizOrderInstallItemProblemVo get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderInstallItemProblemVo> findList(BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo) {
		return super.findList(bizOrderInstallItemProblemVo);
	}
	
	public Page<BizOrderInstallItemProblemVo> findPage(Page<BizOrderInstallItemProblemVo> page, BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo) {
		return super.findPage(page, bizOrderInstallItemProblemVo);
	}

	/**
	 * 墙地砖问题上报详情页
	 * @param valueOf
	 * @return
	 */
	public BizOrderInstallItemProblemVo findDetails(Integer problemId) {
		
		return dao.findDetails(problemId);
	}

	/**
	 * 更新问题上报状态
	 * @param bizOrderInstallItemProblem
	 * @param problemId
	 * @param businessProblemStatus50
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean updateProblem(BizOrderInstallItemProblem bizOrderInstallItemProblem, Integer problemId,
								 String businessProblemStatus50) {
		
		bizOrderInstallItemProblem.setId(problemId);
		bizOrderInstallItemProblem.setStatus(businessProblemStatus50);
		bizOrderInstallItemProblem.preUpdate();
		
		return (dao.updateProblem(bizOrderInstallItemProblem))?true:false;
	}
	


	
}