
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizWallFloorTileOrderCount;


@MyBatisDao
public interface BizWallFloorTileOrderCountDao extends CrudDao2<BizWallFloorTileOrderCount> {


	BizWallFloorTileOrderCount findOrderMessage(String orderNumber);
	
}