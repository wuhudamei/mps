
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.RecheckControlDao;
import cn.damei.entity.modules.RecheckControl;


@Service
@Transactional(readOnly = true)
public class RecheckControlService extends CrudService<RecheckControlDao, RecheckControl> {

	public RecheckControl get(String id) {
		return super.get(id);
	}
	
	public List<RecheckControl> findList(RecheckControl recheckControl) {
		return super.findList(recheckControl);
	}
	
	public Page<RecheckControl> findPage(Page<RecheckControl> page, RecheckControl recheckControl) {
		return super.findPage(page, recheckControl);
	}
	
}