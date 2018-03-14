
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizWallFloorTileReturn;


@MyBatisDao
public interface BizWallFloorTileReturnDao extends CrudDao2<BizWallFloorTileReturn> {


	Double findSquareReturnAll(Integer orderId);
	
}