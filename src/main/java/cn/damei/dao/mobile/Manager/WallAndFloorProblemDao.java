package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.entity.modules.BizOrderInstallItemProblem;

/**
 * 墙地砖问题上报
 * @author Administrator
 *
 */
@MyBatisDao
public interface WallAndFloorProblemDao {

	/**
	 * 墙地砖问题上报订单列表
	 * @param materialManagement
	 * @return
	 */
	List<MaterialManagement> findOrderList(MaterialManagement materialManagement);

	/**
	 * 根据订单id查询订单信息
	 * @param orderId
	 * @return
	 */
	MaterialManagement findOrder(Integer orderId);

	/**
	 *  动态加载墙地砖问题上报记录页面
	 * @param bizOrderInstallItemProblem
	 * @return
	 */
	List<BizOrderInstallItemProblem> findProblemLogList(BizOrderInstallItemProblem bizOrderInstallItemProblem);

	


}
