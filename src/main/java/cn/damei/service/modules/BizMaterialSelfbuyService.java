
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizMaterialSelfbuy;
import cn.damei.dao.modules.BizMaterialSelfbuyDao;


@Service
@Transactional(readOnly = true)
public class BizMaterialSelfbuyService extends CrudService2<BizMaterialSelfbuyDao, BizMaterialSelfbuy> {

	public BizMaterialSelfbuy get(Integer id) {
		return super.get(id);
	}
	
	public List<BizMaterialSelfbuy> findList(BizMaterialSelfbuy bizMaterialSelfbuy) {
		return super.findList(bizMaterialSelfbuy);
	}
	
	public Page<BizMaterialSelfbuy> findPage(Page<BizMaterialSelfbuy> page, BizMaterialSelfbuy bizMaterialSelfbuy) {
		return super.findPage(page, bizMaterialSelfbuy);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterialSelfbuy bizMaterialSelfbuy) {
		super.save(bizMaterialSelfbuy);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterialSelfbuy bizMaterialSelfbuy) {
		super.delete(bizMaterialSelfbuy);
	}


	public List<BizMaterialSelfbuy> findMaterialList(BizMaterialSelfbuy bizMaterialSelfbuy) {
		return dao.findMaterialList(bizMaterialSelfbuy);
	}


	public List<BizMaterialSelfbuy> findMaterialSelfbuyListAjax(BizMaterialSelfbuy bizMaterialSelfbuy) {
		return dao.findMaterialSelfbuyListAjax(bizMaterialSelfbuy);
	}
	
}