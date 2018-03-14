
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizComplaintProblemItemDao;
import cn.damei.entity.modules.BizComplaintProblemItem;


@Service
@Transactional(readOnly = true)
public class BizComplaintProblemItemService extends CrudService<BizComplaintProblemItemDao, BizComplaintProblemItem> {

	public BizComplaintProblemItem get(String id) {
		return super.get(id);
	}

	public List<BizComplaintProblemItem> findList(BizComplaintProblemItem bizComplaintProblemItem) {
		return super.findList(bizComplaintProblemItem);
	}

	public Page<BizComplaintProblemItem> findPage(Page<BizComplaintProblemItem> page, BizComplaintProblemItem bizComplaintProblemItem) {
		return super.findPage(page, bizComplaintProblemItem);
	}

	@Transactional(readOnly = false)
	public void save(BizComplaintProblemItem bizComplaintProblemItem) {
		super.save(bizComplaintProblemItem);
	}

	@Transactional(readOnly = false)
	public void delete(BizComplaintProblemItem bizComplaintProblemItem) {
		super.delete(bizComplaintProblemItem);
	}

	public List<Map<String, String>> findTypeMapByStoreId(String storeId) {
		return dao.findTypeMapByStoreId(storeId);

	}

	public List<BizComplaintProblemItem> getcomplaintProblemTypeId(BizComplaintProblemItem bizComplaintProblemItem) {
		return dao.getcomplaintProblemTypeId(bizComplaintProblemItem);
	}

	public List<BizComplaintProblemItem> getcomplaintProblemId(BizComplaintProblemItem bizComplaintProblemItem) {
		return dao.getcomplaintProblemId(bizComplaintProblemItem);
	}
}