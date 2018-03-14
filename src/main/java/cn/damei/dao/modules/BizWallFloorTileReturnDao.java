/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizWallFloorTileReturn;

/**
 * 墙地砖退货表DAO接口
 * @author wyb
 * @version 2017-08-01
 */
@MyBatisDao
public interface BizWallFloorTileReturnDao extends CrudDao2<BizWallFloorTileReturn> {

	/**
	 * 根据订单id查询订单的退货面积总和
	 * @param orderId
	 * @return
	 */
	Double findSquareReturnAll(Integer orderId);
	
}