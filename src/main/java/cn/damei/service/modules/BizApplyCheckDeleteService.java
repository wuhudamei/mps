
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizApplyCheckDelete;
import cn.damei.dao.modules.BizApplyCheckDeleteDao;


@Service
@Transactional(readOnly = true)
public class BizApplyCheckDeleteService extends CrudService<BizApplyCheckDeleteDao, BizApplyCheckDelete> {

	public BizApplyCheckDelete get(String id) {
		return super.get(id);
	}
	
	public List<BizApplyCheckDelete> findList(BizApplyCheckDelete bizApplyCheckDelete) {
		return super.findList(bizApplyCheckDelete);
	}
	
	public Page<BizApplyCheckDelete> findPage(Page<BizApplyCheckDelete> page, BizApplyCheckDelete bizApplyCheckDelete) {
		return super.findPage(page, bizApplyCheckDelete);
	}
	
	@Transactional(readOnly = false)
	public void save(BizApplyCheckDelete bizApplyCheckDelete) {
		super.save(bizApplyCheckDelete);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizApplyCheckDelete bizApplyCheckDelete) {
		super.delete(bizApplyCheckDelete);
	}
	
}