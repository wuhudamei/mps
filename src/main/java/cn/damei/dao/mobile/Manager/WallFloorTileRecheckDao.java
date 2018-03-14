package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.WallFloorTileRecheck;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizWallFloorTileOrderCount;
import cn.damei.entity.modules.WallRecheck;


@MyBatisDao
public interface WallFloorTileRecheckDao {


	List<WallFloorTileRecheck> findWallFloorTileRecheckList(Integer managerId);


	WallFloorTileRecheck findWallFloorTileRecheckMessage(Integer wallFloorTileRecheckId);


	Double findSquareCount(WallFloorTileRecheck wallFloorTileRecheck);


	boolean saveWallFloorTileRecheck(WallRecheck wallRecheckNew);


	boolean saveWallFloorTileOrderCount(BizWallFloorTileOrderCount bizWallFloorTileOrderCount);


	List<BizBusinessStatusLog> findOperationList(BizBusinessStatusLog bizBusinessStatusLog);

	Double findSquareActual(Integer orderId);

}
