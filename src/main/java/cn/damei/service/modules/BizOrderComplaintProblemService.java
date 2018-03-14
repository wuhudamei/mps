/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizOrderComplaintProblemDao;
import cn.damei.entity.modules.BizOrderComplaintProblem;

/**
 * 工程问题Service
 * 
 * @author ztw
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class BizOrderComplaintProblemService extends CrudService<BizOrderComplaintProblemDao, BizOrderComplaintProblem> {

	@Autowired
	private BizOrderComplaintProblemDao bizOrderComplaintProblemDao;

	public BizOrderComplaintProblem get(String id) {
		return super.get(id);
	}

	public List<BizOrderComplaintProblem> findList(BizOrderComplaintProblem bizOrderComplaintProblem) {
		return super.findList(bizOrderComplaintProblem);
	}

	public Page<BizOrderComplaintProblem> findPage(Page<BizOrderComplaintProblem> page, BizOrderComplaintProblem bizOrderComplaintProblem) {
		return super.findPage(page, bizOrderComplaintProblem);
	}

	@Transactional(readOnly = false)
	public void save(BizOrderComplaintProblem bizOrderComplaintProblem) {
		super.save(bizOrderComplaintProblem);
	}

	@Transactional(readOnly = false)
	public void delete(BizOrderComplaintProblem bizOrderComplaintProblem) {
		super.delete(bizOrderComplaintProblem);
	}

	@Transactional(readOnly = false)
	public void Insert(BizOrderComplaintProblem bizOrderComplaintProblem2) {
		bizOrderComplaintProblemDao.insert(bizOrderComplaintProblem2);

	}

	public List<BizOrderComplaintProblem> queryProblemdeal(BizOrderComplaintProblem bizOrderComplaintProblem2) {
		List<BizOrderComplaintProblem> bizOrderComplaintProblem = bizOrderComplaintProblemDao.queryProblemdeal(bizOrderComplaintProblem2);
		return bizOrderComplaintProblem;

	}

	public BizOrderComplaintProblem queryDealPersonType1(BizOrderComplaintProblem bizOrderComplaintProblem2) {
		BizOrderComplaintProblem bizOrderComplaintProblem = bizOrderComplaintProblemDao.queryDealPersonType1(bizOrderComplaintProblem2);
		return bizOrderComplaintProblem;
	}

}