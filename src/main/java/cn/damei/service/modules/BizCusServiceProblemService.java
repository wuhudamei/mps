
package cn.damei.service.modules;

import java.util.List;

import cn.damei.common.utils.CusserviceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizCusServiceProblemDao;
import cn.damei.entity.modules.BizCusServiceProblem;


@Service
@Transactional(readOnly = true)
public class BizCusServiceProblemService extends CrudService<BizCusServiceProblemDao, BizCusServiceProblem> {
	@Autowired
	private BizCusServiceProblemDao bizCusServiceProblemDao;

	public BizCusServiceProblem get(String id) {
		return super.get(id);
	}

	public List<BizCusServiceProblem> findList(BizCusServiceProblem bizCusServiceProblem) {
		return super.findList(bizCusServiceProblem);
	}

	public Page<BizCusServiceProblem> findPage(Page<BizCusServiceProblem> page, BizCusServiceProblem bizCusServiceProblem) {
        bizCusServiceProblem.setStoreId (CusserviceUtils.getsysOfficeId());
        return super.findPage(page, bizCusServiceProblem);
	}

	@Transactional(readOnly = false)
	public void save(BizCusServiceProblem bizCusServiceProblem) {
		super.save(bizCusServiceProblem);
	}

	@Transactional(readOnly = false)
	public void delete(BizCusServiceProblem bizCusServiceProblem) {
		super.delete(bizCusServiceProblem);
	}

	public String findPicsById(Integer id) {

		return dao.findPicsById(id);
	}

	@Transactional(readOnly = false)
	public void update(BizCusServiceProblem bizCusServiceProblem) {
		dao.update(bizCusServiceProblem);

	}

	@Transactional(readOnly = false)
	public void updateYu(BizCusServiceProblem bizCusServiceProblem) {
		dao.updateYu(bizCusServiceProblem);

	}
}