/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;




import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ItemManagerMap;
import cn.damei.entity.modules.SelectOrder;

/**
 * 订单管理DAO接口
 * @author wyb
 * @version 2016-09-08
 */
@MyBatisDao
public interface SelectOrderDao extends CrudDao2<SelectOrder> {
	public List<ItemManagerMap> findManagerMoreCount(Map<String,Object> map);
	public List<ItemManagerMap> findManagerMoreCount1(List<SelectOrder> list);
	/**
	 * 工地地图
	 * @param selectOrder
	 * @return
	 */
	public List<SelectOrder> findListMap(SelectOrder selectOrder);

}