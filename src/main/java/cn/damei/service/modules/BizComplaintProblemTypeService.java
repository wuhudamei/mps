
package cn.damei.service.modules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizComplaintProblemTypeDao;
import cn.damei.entity.modules.BizComplaintProblemType;


@Service
@Transactional(readOnly = true)
public class BizComplaintProblemTypeService extends CrudService<BizComplaintProblemTypeDao, BizComplaintProblemType> {

	public BizComplaintProblemType get(String id) {
		return super.get(id);
	}

	public List<BizComplaintProblemType> findList(BizComplaintProblemType bizComplaintProblemType) {
		return super.findList(bizComplaintProblemType);
	}

	public Page<BizComplaintProblemType> findPage(Page<BizComplaintProblemType> page, BizComplaintProblemType bizComplaintProblemType) {
		return super.findPage(page, bizComplaintProblemType);
	}

	@Transactional(readOnly = false)
	public void save(BizComplaintProblemType bizComplaintProblemType) {
		super.save(bizComplaintProblemType);
	}

	@Transactional(readOnly = false)
	public void delete(BizComplaintProblemType bizComplaintProblemType) {
		super.delete(bizComplaintProblemType);
	}

	public List<Map<String, String>> findPackByStoreId(String storeId , String projectMode) {
		Map<String ,Object> mapp=new HashMap<String ,Object>();
		mapp.put("storeId",storeId);
		mapp.put("projectMode",projectMode);
		return dao.findPackByStoreId(mapp);
	}

	public List<BizComplaintProblemType> queryComTypeAll() {
		return dao.queryComTypeAll(null);
	}

	public BizComplaintProblemType queryComTypeName(BizComplaintProblemType bizComplaintProblemType) {
		return dao.queryComTypeName(bizComplaintProblemType);
	}

	public BizComplaintProblemType queryIsordertaskpackag(BizComplaintProblemType bizComplaintProblemType) {
		return dao.queryIsordertaskpackag(bizComplaintProblemType);

	}

	public List<BizComplaintProblemType> queryComTypeid(BizComplaintProblemType bizComplaintProblemType) {
		return dao.queryComTypeid(bizComplaintProblemType);

	}
}