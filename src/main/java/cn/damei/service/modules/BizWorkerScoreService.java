package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizWorkerScoreDao;
import cn.damei.entity.modules.BizWorkerScore;

/**
 * 分数排名Service
 * @author ws
 * @version 2017-09-14
 */
@Service
public class BizWorkerScoreService extends CrudService<BizWorkerScoreDao, BizWorkerScore> {

	public BizWorkerScore get(String id) {
		return super.get(id);
	}
	
	public List<BizWorkerScore> findList(BizWorkerScore bizWorkerScore) {
		return super.findList(bizWorkerScore);
	}
	
	public Page<BizWorkerScore> findPage(Page<BizWorkerScore> page, BizWorkerScore bizWorkerScore) {
		return super.findPage(page, bizWorkerScore);
	}
	@Transactional(readOnly = false)
	public Page<BizWorkerScore> queryBizWorkerScoreDetail(Page<BizWorkerScore> page, BizWorkerScore bizWorkerScore, String empId) {
		bizWorkerScore.setPage(page);
		bizWorkerScore.setEmpId(empId);
		page.setList(dao.queryBizWorkerScoreDetail(bizWorkerScore));
		return page;
	}

}