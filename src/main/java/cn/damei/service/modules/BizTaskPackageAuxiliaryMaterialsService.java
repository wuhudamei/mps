
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizTaskPackageAuxiliaryMaterials;
import cn.damei.dao.modules.BizTaskPackageAuxiliaryMaterialsDao;


@Service
@Transactional(readOnly = true)
public class BizTaskPackageAuxiliaryMaterialsService extends CrudService<BizTaskPackageAuxiliaryMaterialsDao, BizTaskPackageAuxiliaryMaterials> {

	public BizTaskPackageAuxiliaryMaterials get(String id) {
		return super.get(id);
	}
	
	public List<BizTaskPackageAuxiliaryMaterials> checkTaskPackageByTemplateId(Integer templateId){
		return dao.checkTaskPackageByTemplateId(templateId);
	}
	
	public List<BizTaskPackageAuxiliaryMaterials> findList(BizTaskPackageAuxiliaryMaterials bizTaskPackageAuxiliaryMaterials) {
		return super.findList(bizTaskPackageAuxiliaryMaterials);
	}
	
	public Page<BizTaskPackageAuxiliaryMaterials> findPage(Page<BizTaskPackageAuxiliaryMaterials> page, BizTaskPackageAuxiliaryMaterials bizTaskPackageAuxiliaryMaterials) {
		return super.findPage(page, bizTaskPackageAuxiliaryMaterials);
	}
	
	@Transactional(readOnly = false)
	public void save(BizTaskPackageAuxiliaryMaterials bizTaskPackageAuxiliaryMaterials) {
		super.save(bizTaskPackageAuxiliaryMaterials);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizTaskPackageAuxiliaryMaterials bizTaskPackageAuxiliaryMaterials) {
		super.delete(bizTaskPackageAuxiliaryMaterials);
	}
	
}