package cn.damei.service.mobile.Manager;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.WallFloorTileRecheckConstantUtil;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.dao.mobile.Manager.WallAndFloorDao;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.dao.mobile.Manager.WallFloorTileRecheckDao;
import cn.damei.entity.mobile.Manager.WallFloorTileRecheck;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizWallFloorTileOrderCount;
import cn.damei.entity.modules.WallRecheck;

/**
 * 墙地砖实测面积复核Service
 * 
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true)
public class WallFloorTileRecheckService {

	@Autowired
	private WallFloorTileRecheckDao dao;
	@Autowired
	private WallAndFloorDao wallAndFloorDao;

	/**
	 * 查询该项目经理下的墙地砖复尺单列表
	 * 
	 * @param managerId
	 * @return
	 */
	public List<WallFloorTileRecheck> findWallFloorTileRecheckList(Integer managerId) {
		return dao.findWallFloorTileRecheckList(managerId);
	}

	/**
	 * 根据墙地砖复尺单id查询相关信息
	 * 
	 * @param wallFloorTileRecheckId
	 * @return
	 */
	public WallFloorTileRecheck findWallFloorTileRecheckMessage(Integer wallFloorTileRecheckId) {
		WallFloorTileRecheck findWallFloorTileRecheckMessage = dao.findWallFloorTileRecheckMessage(wallFloorTileRecheckId);
		Double squareActual = null;
		if (null == findWallFloorTileRecheckMessage.getSquareMeasure()) {
			if (null != findWallFloorTileRecheckMessage.getOrderId()) {
				squareActual = dao.findSquareActual(findWallFloorTileRecheckMessage.getOrderId());
				findWallFloorTileRecheckMessage.setSquareMeasure(squareActual);
			}
		}
		return findWallFloorTileRecheckMessage;
	}

	/**
	 * 查询该订单的墙砖/地砖面积
	 * 
	 * @param orderId
	 * @param purchaseType
	 * @return
	 */
	public Double findSquareCount(Integer orderId, String purchaseType) {

		WallFloorTileRecheck wallFloorTileRecheck = new WallFloorTileRecheck();
		wallFloorTileRecheck.setOrderId(orderId);
		wallFloorTileRecheck.setPurchaseType(purchaseType);
		return dao.findSquareCount(wallFloorTileRecheck);
	}

	/**
	 * 更新墙地砖复尺单
	 * 
	 * @param wallFloorTileRecheckId
	 * @param squareMeasure
	 * @param measureRemarks
	 * @param measureRemarks2
	 * @param wallFloorTileRecheck
	 * @param manager
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveWallFloorTileRecheck(Integer wallFloorTileRecheckId, String flag, String squareMeasure, String measureRemarks, Manager manager) {

		WallRecheck wallRecheckNew = new WallRecheck();
		// id
		wallRecheckNew.setId(wallFloorTileRecheckId);
		// 实测面积
		wallRecheckNew.setSquareMeasure(Double.valueOf(squareMeasure));
		// 实际测量日期
		wallRecheckNew.setRealMeasureDate(new Date());
		// 实测说明
		wallRecheckNew.setMeasureRemarks(measureRemarks);
		if (!StringUtils.isEmpty(flag) && flag.equals("0")) {
			// 状态
			wallRecheckNew.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_55);
			// 状态描述
			wallRecheckNew.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_55);
		} else {
			wallRecheckNew.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_50);
			// 状态描述
			wallRecheckNew.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_50);

		}
		// 状态日期
		wallRecheckNew.setStatusDatetime(new Date());
		// 状态操作人员ID
		wallRecheckNew.setStatusOperateEmployeeId(manager.getId());
		wallRecheckNew.preUpdate();

		return (dao.saveWallFloorTileRecheck(wallRecheckNew)) ? true : false;
	}

	/**
	 * 更新订单的相关信息
	 * 
	 * @param orderId
	 * @param squareMeasure
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean saveWallFloorTileOrderCount(Integer orderId, String squareMeasure) {

		MaterialManagement order = wallAndFloorDao.findOrderMessage(orderId);
		boolean flag = true;

		if (null != order && null != order.getWallFloorTileOrderCountId()) {
			BizWallFloorTileOrderCount bizWallFloorTileOrderCount = new BizWallFloorTileOrderCount();

			// 订单id
			bizWallFloorTileOrderCount.setId(order.getWallFloorTileOrderCountId());
			// 实测面积
			bizWallFloorTileOrderCount.setSquareMeasure(Double.valueOf(squareMeasure));
			bizWallFloorTileOrderCount.preUpdate();

			flag = dao.saveWallFloorTileOrderCount(bizWallFloorTileOrderCount);
		}

		return flag;
	}

	/**
	 * 查询操作日志
	 * 
	 * @param id
	 * @param businessType
	 * @return
	 */
	public List<BizBusinessStatusLog> findOperationList(Integer id, String businessType) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		// 业务唯一标识整形
		bizBusinessStatusLog.setBusinessOnlyMarkInt(Integer.valueOf(id));
		// 业务类型
		bizBusinessStatusLog.setBusinessType(businessType);

		return dao.findOperationList(bizBusinessStatusLog);
	}

}
