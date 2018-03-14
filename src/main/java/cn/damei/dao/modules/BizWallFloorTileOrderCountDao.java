/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizWallFloorTileOrderCount;

/**
 * 墙地砖订单统计表DAO接口
 * @author wyb
 * @version 2017-08-01
 */
@MyBatisDao
public interface BizWallFloorTileOrderCountDao extends CrudDao2<BizWallFloorTileOrderCount> {

	/**
	 * 根据订单编号查询订单的相关信息
	 * @param orderNumber
	 * @return
	 */
	BizWallFloorTileOrderCount findOrderMessage(String orderNumber);
	
}