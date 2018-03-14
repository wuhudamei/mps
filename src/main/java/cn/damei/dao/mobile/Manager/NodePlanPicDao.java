package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.NodePlanPic;
import cn.damei.entity.mobile.Manager.AppOrder;

@MyBatisDao
public interface NodePlanPicDao extends CrudDao<NodePlanPic>{

	AppOrder getNodePlanPicById(String id);

	boolean insertNodePlanPic(NodePlanPic nodePlanPic);

}
