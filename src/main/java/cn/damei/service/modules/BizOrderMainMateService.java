
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.dao.modules.BizOrderMainMateDao;
import cn.damei.entity.modules.BizOrderMainMate;


@Service
@Transactional(readOnly = true)
public class BizOrderMainMateService extends CrudService2<BizOrderMainMateDao, BizOrderMainMate> {

	public BizOrderMainMate get(Integer id) {
		return super.get(id);
	}

	public List<BizOrderMainMate> findList(BizOrderMainMate bizOrderMainMate) {
		return super.findList(bizOrderMainMate);
	}

	public Page<BizOrderMainMate> findPage(Page<BizOrderMainMate> page, BizOrderMainMate bizOrderMainMate) {
		return super.findPage(page, bizOrderMainMate);
	}

	@Transactional(readOnly = false)
	public void save(BizOrderMainMate bizOrderMainMate) {
		super.save(bizOrderMainMate);
	}

	@Transactional(readOnly = false)
	public void delete(BizOrderMainMate bizOrderMainMate) {
		super.delete(bizOrderMainMate);
	}

	@Transactional(readOnly = false)
	public int deleteByOrderId(int orderId) {
		return dao.deleteByOrderId(orderId);
	}

	@Transactional(readOnly = false)
	public void insert(BizOrderMainMate bizOrderMainMate) {
		dao.insert(bizOrderMainMate);
	}

	@Transactional(readOnly = false)
	public void savebyid(BizOrderMainMate bizOrderMainMate) {
		dao.savebyid(bizOrderMainMate);

	}

	public BizOrderMainMate findbyidbizOrderMainMate(BizOrderMainMate bizOrderMainMateq) {
		BizOrderMainMate bizOrderMainMate = dao.get(bizOrderMainMateq);
		return bizOrderMainMate;
	}

	@Transactional(readOnly = false)
	public void updateuni(BizOrderMainMate bizOrderMainMate) {
		dao.updateuni(bizOrderMainMate);

	}

	public BizMaterialsChoiceBill ismaterialschoicebill(String string) {
		return dao.ismaterialschoicebill(string);
	}

	public List<BizOrderMainMate> findListOne(BizOrderMainMate bizOrderMainMate) {
		return dao.findListOne(bizOrderMainMate);
	}

	@Transactional(readOnly = false)
	public int insertpurchaseCount(int parseInt) {
		return dao.insertpurchaseCount(parseInt);

	}
}