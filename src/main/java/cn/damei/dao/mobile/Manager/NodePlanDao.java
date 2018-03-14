package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.NodePlan;

@MyBatisDao
public interface NodePlanDao extends CrudDao2<NodePlan>{

	public boolean insertByNodePlan(NodePlan np);

	public List<NodePlan> queryByOrderIdList(String orderId);

	public List<NodePlan> getByOrderIdList(String id);

	public NodePlan getByIdLimit(String id);

	public boolean updateByDate(NodePlan np);

	public List<NodePlan> queryByOrderIdListNoLimit(Integer orderId);
	public List<NodePlan> justForTraditionNodePlan(Integer orderId);
	
	public NodePlan getById1(String id);
	
	public NodePlan getById2(String orderId,Integer nodeIndex);

	public boolean updateByDateNextNodeIndexs(NodePlan n);

	/**
	 * 批量保存订单进度节点
	 * @param list
	 * @return
	 */
	public boolean saveNodePlanList(List<NodePlan> list);

}
