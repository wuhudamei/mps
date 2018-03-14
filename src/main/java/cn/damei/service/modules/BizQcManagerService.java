
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizQcManager;
import cn.damei.dao.modules.BizQcManagerDao;


@Service
@Transactional(readOnly = true)
public class BizQcManagerService extends CrudService2<BizQcManagerDao, BizQcManager> {

	public BizQcManager get(Integer id) {
		return super.get(id);
	}
	
	public List<BizQcManager> findList(BizQcManager bizQcManager) {
		return super.findList(bizQcManager);
	}
	
	public Page<BizQcManager> findPage(Page<BizQcManager> page, BizQcManager bizQcManager) {
		return super.findPage(page, bizQcManager);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcManager bizQcManager) {
		super.save(bizQcManager);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcManager bizQcManager) {
		super.delete(bizQcManager);
	}


	public Integer findStore(Integer managerEmployeeId) {
		return dao.findStore(managerEmployeeId);
	}
	
}