/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizComplaintProblemItem;
import cn.damei.entity.modules.BizOrderComplaintProblem;

/**
 * 工程投诉分类项DAO接口
 * 
 * @author mh
 * @version 2017-07-03
 */
@MyBatisDao
public interface BizComplaintProblemItemDao extends CrudDao<BizComplaintProblemItem> {

	List<Map<String, String>> findTypeMapByStoreId(String storeId);

	List<BizComplaintProblemItem> getcomplaintProblemTypeId(BizComplaintProblemItem bizComplaintProblemItem);

	List<BizComplaintProblemItem> getcomplaintProblemId(BizComplaintProblemItem bizComplaintProblemItem);

	List<BizComplaintProblemItem> queryComplaintProblemItem(BizOrderComplaintProblem bizOrderComplaintProblem2);

}