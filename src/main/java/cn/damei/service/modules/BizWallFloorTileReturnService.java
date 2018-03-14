/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizWallFloorTileReturn;
import cn.damei.dao.modules.BizWallFloorTileReturnDao;

/**
 * 墙地砖退货表Service
 * @author wyb
 * @version 2017-08-01
 */
@Service
@Transactional(readOnly = true)
public class BizWallFloorTileReturnService extends CrudService2<BizWallFloorTileReturnDao, BizWallFloorTileReturn> {

	public BizWallFloorTileReturn get(Integer id) {
		return super.get(id);
	}
	
	public List<BizWallFloorTileReturn> findList(BizWallFloorTileReturn bizWallFloorTileReturn) {
		return super.findList(bizWallFloorTileReturn);
	}
	
	public Page<BizWallFloorTileReturn> findPage(Page<BizWallFloorTileReturn> page, BizWallFloorTileReturn bizWallFloorTileReturn) {
		return super.findPage(page, bizWallFloorTileReturn);
	}
	
	@Transactional(readOnly = false)
	public void save(BizWallFloorTileReturn bizWallFloorTileReturn) {
		super.save(bizWallFloorTileReturn);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizWallFloorTileReturn bizWallFloorTileReturn) {
		super.delete(bizWallFloorTileReturn);
	}

	/**
	 * 根据订单id查询订单的退货面积总和
	 * @param orderId
	 * @return
	 */
	public Double findSquareReturnAll(Integer orderId) {
		return dao.findSquareReturnAll(orderId);
	}
	
}