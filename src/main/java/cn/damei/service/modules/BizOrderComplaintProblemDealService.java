
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizOrderComplaintProblemDealDao;
import cn.damei.entity.modules.BizOrderComplaintProblemDeal;


@Service
@Transactional(readOnly = true)
public class BizOrderComplaintProblemDealService extends CrudService<BizOrderComplaintProblemDealDao, BizOrderComplaintProblemDeal> {
	@Autowired
	private BizOrderComplaintProblemDealDao bizOrderComplaintProblemDealDao;

	public BizOrderComplaintProblemDeal get(String id) {
		return super.get(id);
	}

	public List<BizOrderComplaintProblemDeal> findList(BizOrderComplaintProblemDeal bizOrderComplaintProblemDeal) {
		return super.findList(bizOrderComplaintProblemDeal);
	}

	public Page<BizOrderComplaintProblemDeal> findPage(Page<BizOrderComplaintProblemDeal> page, BizOrderComplaintProblemDeal bizOrderComplaintProblemDeal) {
		return super.findPage(page, bizOrderComplaintProblemDeal);
	}

	@Transactional(readOnly = false)
	public void save(BizOrderComplaintProblemDeal bizOrderComplaintProblemDeal) {
		super.save(bizOrderComplaintProblemDeal);
	}

	@Transactional(readOnly = false)
	public void delete(BizOrderComplaintProblemDeal bizOrderComplaintProblemDeal) {
		super.delete(bizOrderComplaintProblemDeal);
	}

	@Transactional(readOnly = false)
	public void insert(BizOrderComplaintProblemDeal complaintProblemDeal) {
		bizOrderComplaintProblemDealDao.insert(complaintProblemDeal);

	}

}