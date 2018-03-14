
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.RecheckCnfgDao;
import cn.damei.entity.modules.RecheckCnfg;


@Service
@Transactional(readOnly = true)
public class RecheckCnfgService extends CrudService<RecheckCnfgDao, RecheckCnfg> {

	public RecheckCnfg get(String id) {
		return super.get(id);
	}

	public List<RecheckCnfg> findList(RecheckCnfg recheckCnfg) {
		return super.findList(recheckCnfg);
	}

	public Page<RecheckCnfg> findPage(Page<RecheckCnfg> page, RecheckCnfg recheckCnfg) {
		return super.findPage(page, recheckCnfg);
	}

	@Transactional(readOnly = false)
	public void save(RecheckCnfg recheckCnfg) {
		super.save(recheckCnfg);
	}

	@Transactional(readOnly = false)
	public void datalesave(RecheckCnfg recheckCnfg) {
		dao.deleteall(recheckCnfg);
		if (null != recheckCnfg.getSquareOverMaxRate()) {
			recheckCnfg.setSquareOverMaxRate((Double.parseDouble(recheckCnfg.getSquareOverMaxRate()) * 0.01) + "");
			dao.insert(recheckCnfg);
		}
	}

	@Transactional(readOnly = false)
	public void delete(RecheckCnfg recheckCnfg) {
		super.delete(recheckCnfg);
	}


	public RecheckCnfg findRecheckCnfgMessage() {
		return dao.findRecheckCnfgMessage();
	}

}