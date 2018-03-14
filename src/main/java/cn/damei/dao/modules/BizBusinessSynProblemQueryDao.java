/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;

import cn.damei.entity.modules.BizBusinessSynProblemQuery;

import java.util.List;
import java.util.Map;

/**
 * 经理约检问题统计DAO接口
 * @author 梅浩
 * @version 2017-05-27
 */
@MyBatisDao
public interface BizBusinessSynProblemQueryDao extends CrudDao<BizBusinessSynProblemQuery> {


    List<Map<String,String>> findItemManagerIssueReportDetailById(String managerId);
	
}