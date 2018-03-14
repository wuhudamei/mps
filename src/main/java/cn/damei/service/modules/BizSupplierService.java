
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizSupplierDao;
import cn.damei.entity.modules.BizSupplier;


@Service
@Transactional(readOnly = true)
public class BizSupplierService extends CrudService<BizSupplierDao, BizSupplier> {

	public BizSupplier get(String id) {
		return super.get(id);
	}

	public List<BizSupplier> findList(BizSupplier bizSupplier) {
		return super.findList(bizSupplier);
	}

	public Page<BizSupplier> findPage(Page<BizSupplier> page, BizSupplier bizSupplier) {
		return super.findPage(page, bizSupplier);
	}

	public List<BizSupplier> findListByPhone(String phone) {
		return dao.findListByPhone(phone);
	}

	@Transactional(readOnly = false)
	public void save(BizSupplier bizSupplier) {
		super.save(bizSupplier);
	}

	@Transactional(readOnly = false)
	public void delete(BizSupplier bizSupplier) {
		super.delete(bizSupplier);
	}

	public List<BizSupplier> queryajaxgetSupplier(BizSupplier bizSupplier) {
		return dao.queryajaxgetSupplier(bizSupplier);
	}
}