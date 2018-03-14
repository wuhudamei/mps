
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


	public BizOrderInstallItemProblemVo findDetails(Integer problemId) {
		
		return dao.findDetails(problemId);
	}


	@Transactional(readOnly = false)
	public boolean updateProblem(BizOrderInstallItemProblem bizOrderInstallItemProblem, Integer problemId,
								 String businessProblemStatus50) {
		
		bizOrderInstallItemProblem.setId(problemId);
		bizOrderInstallItemProblem.setStatus(businessProblemStatus50);
		bizOrderInstallItemProblem.preUpdate();
		
		return (dao.updateProblem(bizOrderInstallItemProblem))?true:false;
	}
	


	
}