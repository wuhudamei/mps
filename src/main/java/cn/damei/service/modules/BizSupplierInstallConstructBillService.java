
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizSupplierInstallConstructBillDao;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;


@Service
@Transactional(readOnly = true)
public class BizSupplierInstallConstructBillService extends CrudService2<BizSupplierInstallConstructBillDao, BizSupplierInstallConstructBill> {

	public BizSupplierInstallConstructBill get(Integer id) {
		return super.get(id);
	}

	public List<BizSupplierInstallConstructBill> findList(BizSupplierInstallConstructBill bizSupplierInstallConstructBill) {
		return super.findList(bizSupplierInstallConstructBill);
	}

	public Page<BizSupplierInstallConstructBill> findPage(Page<BizSupplierInstallConstructBill> page, BizSupplierInstallConstructBill bizSupplierInstallConstructBill) {
		return super.findPage(page, bizSupplierInstallConstructBill);
	}

	@Transactional(readOnly = false)
	public void save(BizSupplierInstallConstructBill bizSupplierInstallConstructBill) {
		super.save(bizSupplierInstallConstructBill);
	}

	@Transactional(readOnly = false)
	public void delete(BizSupplierInstallConstructBill bizSupplierInstallConstructBill) {
		super.delete(bizSupplierInstallConstructBill);
	}

	public BizSupplierInstallConstructBill getnot90(BizSupplierInstallConstructBill bizSupplierInstallConstructBillb) {
		return dao.getnot90(bizSupplierInstallConstructBillb);
	}

}