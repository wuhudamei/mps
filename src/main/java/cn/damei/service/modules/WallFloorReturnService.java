
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.DateUtils;
import cn.damei.dao.modules.WallFloorReturnDao;
import cn.damei.entity.modules.WallFloorReturn;


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