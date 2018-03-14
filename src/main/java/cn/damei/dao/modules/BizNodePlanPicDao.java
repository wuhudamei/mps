
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNodePlanPic;


@MyBatisDao
public interface BizNodePlanPicDao extends CrudDao2<BizNodePlanPic> {

	List<BizNodePlanPic> getByNodePlanId(Integer nodeId);
	
}