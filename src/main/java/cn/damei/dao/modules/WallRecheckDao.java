/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.WallRecheck;

/**
 * 墙地砖复尺DAO接口
 * 
 * @author ztw
 * @version 2017-08-01
 */
@MyBatisDao
public interface WallRecheckDao extends CrudDao2<WallRecheck> {

	/**
	 * 根据订单id查询复尺单详情
	 * 
	 * @param orderId
	 * @return
	 */
	WallRecheck findwallRecheckMessage(Integer orderId);

	List<WallRecheck> findListall(WallRecheck wallRecheck);

	void agreRecheckUpdate(WallRecheck wallRecheck);

	void agreRecheckUpdate1(WallRecheck wallRecheck);

	void agreRecheckUpdate2(WallRecheck wallRecheck);

	void updateExamine(WallRecheck wallRecheck);

	void NotagreRecheckUpdate2(WallRecheck wallRecheck);

	String getempName(int parseInt);

	WallRecheck findSquareActualtow(Integer orderId);

	WallRecheck findWallRecheck(Integer orderId);

	Double querySquate(Integer orderId);

}