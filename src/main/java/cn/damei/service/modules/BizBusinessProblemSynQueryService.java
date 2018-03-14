/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizBusinessSynProblemQueryDao;
import cn.damei.entity.modules.BizBusinessSynProblemQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 经理约检问题统计Service
 * @author mh
 * @version 2017-05-27
 */
@Service
@Transactional(readOnly = true)
public class BizBusinessProblemSynQueryService extends CrudService<BizBusinessSynProblemQueryDao, BizBusinessSynProblemQuery> {

	public BizBusinessSynProblemQuery get(String id) {
		return super.get(id);
	}
	
	public List<BizBusinessSynProblemQuery> findList(BizBusinessSynProblemQuery bizBusinessProblemQuery) {
		return super.findList(bizBusinessProblemQuery);
	}
	
	public Page<BizBusinessSynProblemQuery> findPage(Page<BizBusinessSynProblemQuery> page, BizBusinessSynProblemQuery bizBusinessProblemQuery) {
		return super.findPage(page, bizBusinessProblemQuery);
	}
	
	@Transactional(readOnly = false)
	public void save(BizBusinessSynProblemQuery bizBusinessProblemQuery) {
		super.save(bizBusinessProblemQuery);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizBusinessSynProblemQuery bizBusinessProblemQuery) {
		super.delete(bizBusinessProblemQuery);
	}



	public List<Map<String,String>> findItemManagerIssueReportDetailById(String managerId){

		return dao.findItemManagerIssueReportDetailById(managerId);
	}
}