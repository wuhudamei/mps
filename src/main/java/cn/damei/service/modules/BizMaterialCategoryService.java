
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizMaterialCategory;
import cn.damei.dao.modules.BizMaterialCategoryDao;


@Service
@Transactional(readOnly = true)
public class BizMaterialCategoryService extends CrudService<BizMaterialCategoryDao, BizMaterialCategory> {

	public BizMaterialCategory get(String id) {
		return super.get(id);
	}
	
	public List<BizMaterialCategory> findList(BizMaterialCategory bizMaterialCategory) {
		return super.findList(bizMaterialCategory);
	}
	
	public Page<BizMaterialCategory> findPage(Page<BizMaterialCategory> page, BizMaterialCategory bizMaterialCategory) {
		return super.findPage(page, bizMaterialCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialCategory bizMaterialCategory) {
		super.save(bizMaterialCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialCategory bizMaterialCategory) {
		super.delete(bizMaterialCategory);
	}
	
}