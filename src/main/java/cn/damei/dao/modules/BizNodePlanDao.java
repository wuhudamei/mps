/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNodePlan;

/**
 * 进度节点DAO接口
 * @author llp
 * @version 2016-10-10
 */
@MyBatisDao
public interface BizNodePlanDao extends CrudDao2<BizNodePlan> {

	List<BizNodePlan> getByOrderIdListInIndex(List<Integer> list);

	List<BizNodePlan> getbyOrderIDList();

}