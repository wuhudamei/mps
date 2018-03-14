
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNodePlan;


@MyBatisDao
public interface BizNodePlanDao extends CrudDao2<BizNodePlan> {

	List<BizNodePlan> getByOrderIdListInIndex(List<Integer> list);

	List<BizNodePlan> getbyOrderIDList();

}