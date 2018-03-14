
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;

import cn.damei.entity.modules.BizBusinessSynProblemQuery;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface BizBusinessSynProblemQueryDao extends CrudDao<BizBusinessSynProblemQuery> {


    List<Map<String,String>> findItemManagerIssueReportDetailById(String managerId);
	
}