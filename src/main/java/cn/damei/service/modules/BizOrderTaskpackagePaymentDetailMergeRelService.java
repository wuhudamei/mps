
package cn.damei.service.modules;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeRel;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailMergeRelDao;


@Service
@Transactional(readOnly = true)
public class BizOrderTaskpackagePaymentDetailMergeRelService extends CrudService2<BizOrderTaskpackagePaymentDetailMergeRelDao, BizOrderTaskpackagePaymentDetailMergeRel> {

	public BizOrderTaskpackagePaymentDetailMergeRel get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderTaskpackagePaymentDetailMergeRel> findList(BizOrderTaskpackagePaymentDetailMergeRel bizOrderTaskpackagePaymentDetailMergeRel) {
		return super.findList(bizOrderTaskpackagePaymentDetailMergeRel);
	}
	
	public Page<BizOrderTaskpackagePaymentDetailMergeRel> findPage(Page<BizOrderTaskpackagePaymentDetailMergeRel> page, BizOrderTaskpackagePaymentDetailMergeRel bizOrderTaskpackagePaymentDetailMergeRel) {
		return super.findPage(page, bizOrderTaskpackagePaymentDetailMergeRel);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderTaskpackagePaymentDetailMergeRel bizOrderTaskpackagePaymentDetailMergeRel) {
		super.save(bizOrderTaskpackagePaymentDetailMergeRel);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderTaskpackagePaymentDetailMergeRel bizOrderTaskpackagePaymentDetailMergeRel) {
		super.delete(bizOrderTaskpackagePaymentDetailMergeRel);
	}
	
}