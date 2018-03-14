
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBusinessProblemQuery;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface BizBusinessProblemQueryDao extends CrudDao<BizBusinessProblemQuery> {




    List<BizBusinessProblemQuery> findQcNodeInfoByStoreIdAndProjectModeId(String storeId,String projectMode);
    List<BizBusinessProblemQuery> findIssueTypeByStoreIdAndProjectModeId(String storeId,String projectMode);
    Map<String,String> findIssueDetail(String problemId);
}