/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.modules.WallFloorReturnDao;
import cn.damei.entity.modules.WallFloorReturn;

/**
 * 墙地砖退货Service
 * 
 * @author 张同维
 * @version 2017-09-26
 */
@Service
@Transactional(readOnly = true)
public class WallFloorReturnService extends CrudService<WallFloorReturnDao, WallFloorReturn> {

	public WallFloorReturn get(String id) {
		return super.get(id);
	}

	public List<WallFloorReturn> findList(WallFloorReturn wallFloorReturn) {
		return super.findList(wallFloorReturn);
	}

	public Page<WallFloorReturn> findPage(Page<WallFloorReturn> page, WallFloorReturn wallFloorReturn) {
		if (null != wallFloorReturn.getReturnDatetime()) {
			wallFloorReturn.setReturnDatetimeString(DateUtils.formatDate(wallFloorReturn.getReturnDatetime(), null));
		}
		wallFloorReturn.setPage(page);

		return page.setList(dao.findList30(wallFloorReturn));
	}

	@Transactional(readOnly = false)
	public void save(WallFloorReturn wallFloorReturn) {
		super.save(wallFloorReturn);
	}

	@Transactional(readOnly = false)
	public void delete(WallFloorReturn wallFloorReturn) {
		super.delete(wallFloorReturn);
	}

}