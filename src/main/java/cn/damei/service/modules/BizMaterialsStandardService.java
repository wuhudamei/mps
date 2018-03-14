
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.DropModel;
import cn.damei.dao.modules.BizMaterialsStandardDao;
import cn.damei.entity.modules.BizMaterialsStandard;


@Service
@Transactional(readOnly = true)
public class BizMaterialsStandardService extends CrudService2<BizMaterialsStandardDao, BizMaterialsStandard> {

	public BizMaterialsStandard get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMaterialsStandard> findList(BizMaterialsStandard bizMaterialsStandard) {
		return super.findList(bizMaterialsStandard);
	}
	
	public Page<BizMaterialsStandard> findPage(Page<BizMaterialsStandard> page, BizMaterialsStandard bizMaterialsStandard) {
		return super.findPage(page, bizMaterialsStandard);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialsStandard bizMaterialsStandard) {
		super.save(bizMaterialsStandard);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialsStandard bizMaterialsStandard) {
		super.delete(bizMaterialsStandard);
	}
	
	@Transactional(readOnly = false)
	public void update(BizMaterialsStandard bizMaterialsStandard) {
		dao.update(bizMaterialsStandard);
	}

	public List<DropModel> findMaterialsByStroeId(String storeId) {
		return dao.findMaterialsByStroeId(storeId);
	}

	public List<BizMaterialsStandard> queryMaterialsByStoreId(String storeId) {

		return dao.queryMaterialsByStoreId(storeId);
	}
	
}