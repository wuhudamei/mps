
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import cn.damei.entity.modules.BizOrderInstallItemProblem;
import cn.damei.entity.modules.BizOrderInstallItemProblemLog;
import cn.damei.entity.modules.BizOrderInstallItemProblemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderInstallItemProblemLogDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.dao.modules.BizOrderInstallItemProblemDao;


@Service
@Transactional(readOnly = true)
public class BizOrderInstallItemProblemService extends CrudService2<BizOrderInstallItemProblemDao, BizOrderInstallItemProblem> {

	@Autowired
	private BizOrderInstallItemProblemLogDao bizOrderInstallItemProblemLogDao; 
	
	public BizOrderInstallItemProblem get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderInstallItemProblem> findList(BizOrderInstallItemProblem bizOrderInstallItemProblem) {
		return super.findList(bizOrderInstallItemProblem);
	}
	
	public Page<BizOrderInstallItemProblem> findPage(Page<BizOrderInstallItemProblem> page, BizOrderInstallItemProblem bizOrderInstallItemProblem) {
		return super.findPage(page, bizOrderInstallItemProblem);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderInstallItemProblem bizOrderInstallItemProblem) {
		super.save(bizOrderInstallItemProblem);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderInstallItemProblem bizOrderInstallItemProblem) {
		super.delete(bizOrderInstallItemProblem);
	}

	public BizOrderInstallItemProblemVo getOrderInstallItemProblemVo(Integer id) {
		return dao.getOrderInstallItemProblemVo(id);
	}


	public Page<BizOrderInstallItemProblemVo> findVoPage(Page<BizOrderInstallItemProblemVo> page,
			BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo) {
		bizOrderInstallItemProblemVo.setPage(page);
		page.setList(dao.queryOrderInstallItemProblemVoList(bizOrderInstallItemProblemVo));
		return page;
	}

	public List<DropModel> queryInstallItemProblemTypeList(Map map) {
		return dao.queryInstallItemProblemTypeList(map);
	}
	
	@Transactional(readOnly = false)
	public void updateAndInsert(BizOrderInstallItemProblem bizOrderInstallItemProblem, BizOrderInstallItemProblemLog log) {
		bizOrderInstallItemProblem.preUpdate();
		dao.update(bizOrderInstallItemProblem);
		bizOrderInstallItemProblemLogDao.insert(log);
	}

	public BizOrderInstallItemProblemVo queryById(Integer id) {
		return dao.queryById(id);
	}

	@Transactional(readOnly = false)
	public Integer insert1(BizOrderInstallItemProblem bizOrderInstallItemProblem) {
		return dao.insert1(bizOrderInstallItemProblem);
	}


	public Page<BizOrderInstallItemProblemVo> findVoPage3(Page<BizOrderInstallItemProblemVo> page,
			BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo) {
		bizOrderInstallItemProblemVo.setPage(page);
		page.setList(dao.queryOrderInstallItemProblemVoList3(bizOrderInstallItemProblemVo));
		return page;
	}

	public List<BizOrderInstallItemProblemVo> queryOrderInstallItemProblemVoList3(
			BizOrderInstallItemProblemVo bizOrderInstallItemProblemVo) {
		return dao.queryOrderInstallItemProblemVoList3(bizOrderInstallItemProblemVo);
	}
	
}