
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import cn.damei.dao.modules.BizBusinessProblemQueryDao;
import cn.damei.entity.modules.BizBusinessProblemQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;



@Service
@Transactional(readOnly = true)
public class BizBusinessProblemQueryService extends CrudService<BizBusinessProblemQueryDao, BizBusinessProblemQuery> {

	public BizBusinessProblemQuery get(String id) {
		return super.get(id);
	}
	
	public List<BizBusinessProblemQuery> findList(BizBusinessProblemQuery bizBusinessProblemQuery) {
		return super.findList(bizBusinessProblemQuery);
	}
	
	public Page<BizBusinessProblemQuery> findPage(Page<BizBusinessProblemQuery> page, BizBusinessProblemQuery bizBusinessProblemQuery) {
		return super.findPage(page, bizBusinessProblemQuery);
	}
	
	@Transactional(readOnly = false)
	public void save(BizBusinessProblemQuery bizBusinessProblemQuery) {
		super.save(bizBusinessProblemQuery);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizBusinessProblemQuery bizBusinessProblemQuery) {
		super.delete(bizBusinessProblemQuery);
	}



	public List<BizBusinessProblemQuery> findQcNodeInfoByStoreIdAndProjectModeId(String storeId,String projectMode){

		return dao.findQcNodeInfoByStoreIdAndProjectModeId(storeId,projectMode);
	}
	public List<BizBusinessProblemQuery> findIssueTypeByStoreIdAndProjectModeId(String storeId,String projectMode){

		return dao.findIssueTypeByStoreIdAndProjectModeId(storeId,projectMode);
	}

	public Map<String,String> findIssueDetail(String problemId){

		return dao.findIssueDetail(problemId);
	}
}