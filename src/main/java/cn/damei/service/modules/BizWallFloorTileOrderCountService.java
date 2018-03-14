
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizWallFloorTileOrderCount;
import cn.damei.dao.modules.BizWallFloorTileOrderCountDao;


@Service
@Transactional(readOnly = true)
public class BizWallFloorTileOrderCountService extends CrudService2<BizWallFloorTileOrderCountDao, BizWallFloorTileOrderCount> {

	public BizWallFloorTileOrderCount get(Integer id) {
		return super.get(id);
	}
	
	public List<BizWallFloorTileOrderCount> findList(BizWallFloorTileOrderCount bizWallFloorTileOrderCount) {
		return super.findList(bizWallFloorTileOrderCount);
	}
	
	public Page<BizWallFloorTileOrderCount> findPage(Page<BizWallFloorTileOrderCount> page, BizWallFloorTileOrderCount bizWallFloorTileOrderCount) {
		return super.findPage(page, bizWallFloorTileOrderCount);
	}
	
	@Transactional(readOnly = false)
	public void save(BizWallFloorTileOrderCount bizWallFloorTileOrderCount) {
		super.save(bizWallFloorTileOrderCount);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizWallFloorTileOrderCount bizWallFloorTileOrderCount) {
		super.delete(bizWallFloorTileOrderCount);
	}
	
}