package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderTaskpackageDao;
import cn.damei.entity.modules.BizOrderTaskpackage;
import cn.damei.entity.modules.TaskpackageProceduces;
import cn.damei.entity.modules.UrgeCheck;

@Service
@Transactional
public class BizOrderTaskpackageService extends CrudService2<BizOrderTaskpackageDao, BizOrderTaskpackage>{
	
	/**
	 * 任务包明细
	 */
	public Page<BizOrderTaskpackage> findPage(Page<BizOrderTaskpackage> page, BizOrderTaskpackage bizOrderTaskpackage) {
		bizOrderTaskpackage.setPage(page);
		page.setList(dao.findList(bizOrderTaskpackage));
		return page;
	}

	public Page<BizOrderTaskpackage> findPage1(Page<BizOrderTaskpackage> page,
			BizOrderTaskpackage bizOrderTaskpackage) {
		bizOrderTaskpackage.setPage(page);
		page.setList(dao.findList1(bizOrderTaskpackage));
		return page;
	}

	public Page<BizOrderTaskpackage> findPage2(Page<BizOrderTaskpackage> page,
			BizOrderTaskpackage bizOrderTaskpackage) {
		bizOrderTaskpackage.setPage(page);
		page.setList(dao.findList2(bizOrderTaskpackage));
		return page;
	}

	public Page<BizOrderTaskpackage> findPage3(Page<BizOrderTaskpackage> page,
			BizOrderTaskpackage bizOrderTaskpackage) {
		bizOrderTaskpackage.setPage(page);
		page.setList(dao.findList3(bizOrderTaskpackage));
		return page;
	}

	public Page<BizOrderTaskpackage> findPage4(Page<BizOrderTaskpackage> page,
			BizOrderTaskpackage bizOrderTaskpackage) {
		bizOrderTaskpackage.setPage(page);
		page.setList(dao.findList4(bizOrderTaskpackage));
		return page;
	}

	public Page<UrgeCheck> findPage5(Page<UrgeCheck> page, UrgeCheck urgeCheck) {
		urgeCheck.setPage(page);
		page.setList(dao.findList5(urgeCheck));
		return page;
	}

	public Page<BizOrderTaskpackage> findPage6(Page<BizOrderTaskpackage> page,
			BizOrderTaskpackage bizOrderTaskpackage) {
		bizOrderTaskpackage.setPage(page);
		page.setList(dao.findList6(bizOrderTaskpackage));
		return page;
	}

	public List<TaskpackageProceduces> queryProceduresByPackageId(Integer id) {
		// TODO Auto-generated method stub
		return dao.queryProceduresByPackageId(id);
	}

	public Page<BizOrderTaskpackage> findPage7(Page<BizOrderTaskpackage> page,
			BizOrderTaskpackage bizOrderTaskpackage) {
		bizOrderTaskpackage.setPage(page);
		page.setList(dao.findList7(bizOrderTaskpackage));
		return page;
	}
}
