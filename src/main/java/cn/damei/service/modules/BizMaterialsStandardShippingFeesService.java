
package cn.damei.service.modules;

import java.util.List;

import cn.damei.common.service.CrudService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.entity.modules.BizMaterialsStandardShippingFees;
import cn.damei.dao.modules.BizMaterialsStandardShippingFeesDao;


@Service
@Transactional(readOnly = true)
public class BizMaterialsStandardShippingFeesService extends CrudService2<BizMaterialsStandardShippingFeesDao, BizMaterialsStandardShippingFees> {
	@Autowired
	private  BizMaterialsStandardShippingFeesDao bizMaterialsStandardShippingFeesDao;
	@Override
	public BizMaterialsStandardShippingFees get(Integer id) {
		return super.get(id);
	}
	@Override
	public List<BizMaterialsStandardShippingFees> findList(BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees) {
		return super.findList(bizMaterialsStandardShippingFees);
	}
	@Override
	public Page<BizMaterialsStandardShippingFees> findPage(Page<BizMaterialsStandardShippingFees> page, BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees) {
		return super.findPage(page, bizMaterialsStandardShippingFees);
	}
	@Override
	@Transactional(readOnly = false)
	public void save(BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees) {
		Boolean flag=true;

		Integer status = 1;
		if (status.equals(bizMaterialsStandardShippingFees.getStatus())) {
			List<BizMaterialsStandardShippingFees> list = super.findList(new BizMaterialsStandardShippingFees());
			BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees2 = null;
			for (BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees1 : list) {
				if (bizMaterialsStandardShippingFees.getId().equals(bizMaterialsStandardShippingFees1.getId())) {
					bizMaterialsStandardShippingFees2 = bizMaterialsStandardShippingFees1;
				}
			}
			for (BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees3 : list) {
				if (bizMaterialsStandardShippingFees2.getStoreId().equals(bizMaterialsStandardShippingFees3.getStoreId())
						&& bizMaterialsStandardShippingFees2.getBizMaterialsStandardType().equals(bizMaterialsStandardShippingFees3.getBizMaterialsStandardType())
						&& status.equals(bizMaterialsStandardShippingFees3.getStatus())) {
					flag=false;
				}
			}
		}
		if(flag) {
			super.save(bizMaterialsStandardShippingFees);
		}
	}
	@Override
	@Transactional(readOnly = false)
	public void delete(BizMaterialsStandardShippingFees bizMaterialsStandardShippingFees) {
		super.delete(bizMaterialsStandardShippingFees);
	}
	public BizMaterialsStandardShippingFees getShippingFee(Integer storeId,Integer bizMaterialsStandardType) {
		return bizMaterialsStandardShippingFeesDao.getShippingFee(storeId,bizMaterialsStandardType);
	}

}