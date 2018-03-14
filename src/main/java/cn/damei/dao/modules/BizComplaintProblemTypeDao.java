/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizComplaintProblemType;

/**
 * 工程投诉DAO接口
 * 
 * @author mh
 * @version 2017-07-03
 */
@MyBatisDao
public interface BizComplaintProblemTypeDao extends CrudDao<BizComplaintProblemType> {


	List<BizComplaintProblemType> queryComTypeAll(BizComplaintProblemType bizComplaintProblemType);

	BizComplaintProblemType queryComTypeName(BizComplaintProblemType bizComplaintProblemType);

	List<BizComplaintProblemType> queryComTypeid(BizComplaintProblemType bizComplaintProblemType);

	BizComplaintProblemType queryIsordertaskpackag(BizComplaintProblemType bizComplaintProblemType);

	List<Map<String,String>> findPackByStoreId(Map<String, Object> mapp);
}