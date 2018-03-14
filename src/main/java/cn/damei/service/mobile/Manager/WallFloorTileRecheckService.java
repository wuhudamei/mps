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


@Service
@Transactional(readOnly = true)
public class WallFloorTileRecheckService {

	@Autowired
	private WallFloorTileRecheckDao dao;
	@Autowired
	private WallAndFloorDao wallAndFloorDao;


	public List<WallFloorTileRecheck> findWallFloorTileRecheckList(Integer managerId) {
		return dao.findWallFloorTileRecheckList(managerId);
	}


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


	public Double findSquareCount(Integer orderId, String purchaseType) {

		WallFloorTileRecheck wallFloorTileRecheck = new WallFloorTileRecheck();
		wallFloorTileRecheck.setOrderId(orderId);
		wallFloorTileRecheck.setPurchaseType(purchaseType);
		return dao.findSquareCount(wallFloorTileRecheck);
	}


	@Transactional(readOnly = false)
	public boolean saveWallFloorTileRecheck(Integer wallFloorTileRecheckId, String flag, String squareMeasure, String measureRemarks, Manager manager) {

		WallRecheck wallRecheckNew = new WallRecheck();

		wallRecheckNew.setId(wallFloorTileRecheckId);

		wallRecheckNew.setSquareMeasure(Double.valueOf(squareMeasure));

		wallRecheckNew.setRealMeasureDate(new Date());

		wallRecheckNew.setMeasureRemarks(measureRemarks);
		if (!StringUtils.isEmpty(flag) && flag.equals("0")) {

			wallRecheckNew.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_55);

			wallRecheckNew.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_55);
		} else {
			wallRecheckNew.setStatus(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_50);

			wallRecheckNew.setStatusDescribe(WallFloorTileRecheckConstantUtil.WAll_FLOOR_TILE_RECHECK_STATUS_NAME_50);

		}

		wallRecheckNew.setStatusDatetime(new Date());

		wallRecheckNew.setStatusOperateEmployeeId(manager.getId());
		wallRecheckNew.preUpdate();

		return (dao.saveWallFloorTileRecheck(wallRecheckNew)) ? true : false;
	}


	@Transactional(readOnly = false)
	public boolean saveWallFloorTileOrderCount(Integer orderId, String squareMeasure) {

		MaterialManagement order = wallAndFloorDao.findOrderMessage(orderId);
		boolean flag = true;

		if (null != order && null != order.getWallFloorTileOrderCountId()) {
			BizWallFloorTileOrderCount bizWallFloorTileOrderCount = new BizWallFloorTileOrderCount();


			bizWallFloorTileOrderCount.setId(order.getWallFloorTileOrderCountId());

			bizWallFloorTileOrderCount.setSquareMeasure(Double.valueOf(squareMeasure));
			bizWallFloorTileOrderCount.preUpdate();

			flag = dao.saveWallFloorTileOrderCount(bizWallFloorTileOrderCount);
		}

		return flag;
	}


	public List<BizBusinessStatusLog> findOperationList(Integer id, String businessType) {

		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();

		bizBusinessStatusLog.setBusinessOnlyMarkInt(Integer.valueOf(id));

		bizBusinessStatusLog.setBusinessType(businessType);

		return dao.findOperationList(bizBusinessStatusLog);
	}

}
