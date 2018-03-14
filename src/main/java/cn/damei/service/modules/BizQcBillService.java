
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizQcBill;
import cn.damei.dao.modules.BizQcBillDao;


@Service
@Transactional(readOnly = true)
public class BizQcBillService extends CrudService<BizQcBillDao, BizQcBill> {

	public BizQcBill get(String id) {
		return super.get(id);
	}
	
	public List<BizQcBill> findList(BizQcBill bizQcBill) {
		return super.findList(bizQcBill);
	}
	
	public Page<BizQcBill> findPage(Page<BizQcBill> page, BizQcBill bizQcBill) {
		return super.findPage(page, bizQcBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BizQcBill bizQcBill) {
		super.save(bizQcBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizQcBill bizQcBill) {
		super.delete(bizQcBill);
	}
	
	public BizQcBill queryQcBillByParam(Map<String,Object> param){
		return dao.queryQcBillByParam(param);
	}

	public List<BizQcBill> findBizQcBillByOrderId(BizQcBill bizQcBill) {

		return dao.findBizQcBillByOrderId(bizQcBill);
	}
	
}