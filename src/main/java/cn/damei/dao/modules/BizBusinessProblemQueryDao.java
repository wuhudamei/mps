/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBusinessProblemQuery;

import java.util.List;
import java.util.Map;

/**
 * 约检问题上报查询DAO接口
 * @author mh
 * @version 2017-05-27
 */
@MyBatisDao
public interface BizBusinessProblemQueryDao extends CrudDao<BizBusinessProblemQuery> {




    List<BizBusinessProblemQuery> findQcNodeInfoByStoreIdAndProjectModeId(String storeId,String projectMode);
    List<BizBusinessProblemQuery> findIssueTypeByStoreIdAndProjectModeId(String storeId,String projectMode);
    Map<String,String> findIssueDetail(String problemId);
}