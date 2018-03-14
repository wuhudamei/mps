package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.WallFloorTileRecheck;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizWallFloorTileOrderCount;
import cn.damei.entity.modules.WallRecheck;

/**
 * 墙地砖实测面积复核
 * 
 * @author Administrator
 *
 */
@MyBatisDao
public interface WallFloorTileRecheckDao {

	/**
	 * 查询该项目经理下的墙地砖复尺单列表
	 * 
	 * @param managerId
	 * @return
	 */
	List<WallFloorTileRecheck> findWallFloorTileRecheckList(Integer managerId);

	/**
	 * 根据墙地砖复尺单id查询相关信息
	 * 
	 * @param wallFloorTileRecheckId
	 * @return
	 */
	WallFloorTileRecheck findWallFloorTileRecheckMessage(Integer wallFloorTileRecheckId);

	/**
	 * 查询该订单的墙砖/地砖面积
	 * 
	 * @param wallFloorTileRecheck
	 * @return
	 */
	Double findSquareCount(WallFloorTileRecheck wallFloorTileRecheck);

	/**
	 * 更新墙地砖复尺单
	 * 
	 * @param wallRecheckNew
	 * @return
	 */
	boolean saveWallFloorTileRecheck(WallRecheck wallRecheckNew);

	/**
	 * 更新订单的相关信息
	 * 
	 * @param bizWallFloorTileOrderCount
	 * @return
	 */
	boolean saveWallFloorTileOrderCount(BizWallFloorTileOrderCount bizWallFloorTileOrderCount);

	/**
	 * 查询操作日志
	 * 
	 * @param bizBusinessStatusLog
	 * @return
	 */
	List<BizBusinessStatusLog> findOperationList(BizBusinessStatusLog bizBusinessStatusLog);

	Double findSquareActual(Integer orderId);

}
