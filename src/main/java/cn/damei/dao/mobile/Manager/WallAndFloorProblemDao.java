package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.entity.modules.BizOrderInstallItemProblem;


@MyBatisDao
public interface WallAndFloorProblemDao {


	List<MaterialManagement> findOrderList(MaterialManagement materialManagement);


	MaterialManagement findOrder(Integer orderId);


	List<BizOrderInstallItemProblem> findProblemLogList(BizOrderInstallItemProblem bizOrderInstallItemProblem);

	


}
