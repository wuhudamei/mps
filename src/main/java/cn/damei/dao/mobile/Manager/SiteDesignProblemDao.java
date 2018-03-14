package cn.damei.dao.mobile.Manager;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.MaterialManagement;

/**
 * 工地设计问题上报
 * @author Administrator
 *
 */
@MyBatisDao
public interface SiteDesignProblemDao {

	/**
	 * 工地设计问题上报订单列表
	 * @param materialManagement
	 * @return
	 */
	List<MaterialManagement> findOrderList(MaterialManagement materialManagement);

	/**
	 * 工地设计问题上报 详情列表
	 * @param map
	 * @return
	 */
	List<Map<String, String>> findProblemLogList(Map<String, Object> map);

}
