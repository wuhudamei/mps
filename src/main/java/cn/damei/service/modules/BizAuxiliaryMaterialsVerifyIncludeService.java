package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizAuxiliaryMaterialsVerifyIncludeDao;
import cn.damei.entity.modules.BizAuxiliaryMaterialsVerifyInclude;


@Service
@Transactional(readOnly = true)
public class BizAuxiliaryMaterialsVerifyIncludeService extends CrudService2<BizAuxiliaryMaterialsVerifyIncludeDao, BizAuxiliaryMaterialsVerifyInclude>{

	public BizAuxiliaryMaterialsVerifyInclude get(Integer id) {
		return super.get(id);
	}


	public List<BizAuxiliaryMaterialsVerifyInclude> findList(BizAuxiliaryMaterialsVerifyInclude bizAuxiliaryMaterialsVerifyInclude) {
		return super.findList(bizAuxiliaryMaterialsVerifyInclude);
	}

	public Page<BizAuxiliaryMaterialsVerifyInclude> findPage(Page<BizAuxiliaryMaterialsVerifyInclude> page, BizAuxiliaryMaterialsVerifyInclude bizAuxiliaryMaterialsVerifyInclude) {
		return super.findPage(page, bizAuxiliaryMaterialsVerifyInclude);
	}


	@Transactional(readOnly = false)
	public void save(BizAuxiliaryMaterialsVerifyInclude bizAuxiliaryMaterialsVerifyInclude) {
		super.save(bizAuxiliaryMaterialsVerifyInclude);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizAuxiliaryMaterialsVerifyInclude bizAuxiliaryMaterialsVerifyInclude){
		super.delete(bizAuxiliaryMaterialsVerifyInclude);
	}
}
