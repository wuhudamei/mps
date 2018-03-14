
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.BizBizEmployeegroupVoDao;
import cn.damei.entity.modules.BizEmployeegroupVO;
import cn.damei.dao.modules.BizSupplierEmployeeGroupDao;
import cn.damei.entity.modules.BizSupplierEmployeeGroup;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class BizSupplierEmployeeGroupService extends CrudService<BizSupplierEmployeeGroupDao, BizSupplierEmployeeGroup> {
	@Autowired
	private BizBizEmployeegroupVoDao bizBizEmployeegroupVoDao;

	public BizSupplierEmployeeGroup get(String id) {
		return super.get(id);
	}

	public List<BizSupplierEmployeeGroup> findList(BizSupplierEmployeeGroup bizSupplierEmployeeGroup) {
		return super.findList(bizSupplierEmployeeGroup);
	}

	public Page<BizSupplierEmployeeGroup> findPage(Page<BizSupplierEmployeeGroup> page, BizSupplierEmployeeGroup bizSupplierEmployeeGroup) {
		bizSupplierEmployeeGroup.setPage(page);
		List<BizSupplierEmployeeGroup> findPageallsupp = dao.findPageallsupp(bizSupplierEmployeeGroup);
		for (int i = 0; i < findPageallsupp.size(); i++) {
			findPageallsupp.get(i).setIndex((i + 1) + "");
		}
		page.setList(findPageallsupp);

		return page;
	}

	@Transactional(readOnly = false)
	public void save(BizSupplierEmployeeGroup bizSupplierEmployeeGroup) {
		super.save(bizSupplierEmployeeGroup);
	}

	@Transactional(readOnly = false)
	public void delete(BizSupplierEmployeeGroup bizSupplierEmployeeGroup) {
		super.delete(bizSupplierEmployeeGroup);
	}

	@Transactional(readOnly = false)
	public void insert(BizSupplierEmployeeGroup bizSupplierEmployeeGroup) {
		String[] employeeGroupId1 = bizSupplierEmployeeGroup.getEmployeeGroupId1().split(",");
		for (String string : employeeGroupId1) {
			bizSupplierEmployeeGroup.setSupplierId(bizSupplierEmployeeGroup.getSupplierName());
			bizSupplierEmployeeGroup.setEmployeeGroupId(string);
			bizSupplierEmployeeGroup.setCreateDate(new Date());
			bizSupplierEmployeeGroup.setCurrentUser(UserUtils.getUser());
			bizSupplierEmployeeGroup.setId(null);
			dao.insert(bizSupplierEmployeeGroup);
		}

	}

	@Transactional(readOnly = false)
	public List<BizEmployeegroupVO> queryEmployeeGroupList(BizSupplierEmployeeGroup bizSupplierEmployeeGroup) {

		List<BizSupplierEmployeeGroup> findAllList = dao.findListEmployeegroupIds(bizSupplierEmployeeGroup);
		List<BizEmployeegroupVO> bizEmployeegroupVOList = new ArrayList<BizEmployeegroupVO>();
		if (findAllList.size() > 0) {

			BizEmployeegroupVO bizEmployeegroupVO = new BizEmployeegroupVO();
			for (BizSupplierEmployeeGroup bizSupplierEmployeeGroup2 : findAllList) {
				bizEmployeegroupVO.setRecommendManagerName(bizSupplierEmployeeGroup2.getEmployeeGroupId());
				BizEmployeegroupVO bizEmgrouprelation1 = bizBizEmployeegroupVoDao.getbyId(bizEmployeegroupVO);
				bizEmployeegroupVOList.add(bizEmgrouprelation1);
			}
		}
		return bizEmployeegroupVOList;
	}



	public List<BizSupplierEmployeeGroup> findBizSupplierEmployeeGroup(BizSupplierEmployeeGroup bizSupplierEmployeeGroup) {
		List<BizSupplierEmployeeGroup> findPageallsupp = dao.findBizSupplierEmployeeGroup(bizSupplierEmployeeGroup);
		return findPageallsupp;
	}

	@Transactional(readOnly = false)
	public void deleteSupplier(BizSupplierEmployeeGroup bizSupplierEmployeeGroup) {
		dao.deleteSupplier(bizSupplierEmployeeGroup);

	}

}