
package cn.damei.service.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.dao.modules.ProjectInstallAndSupplierDao;
import cn.damei.entity.modules.ProjectInstallAndSupplier;
import cn.damei.dao.modules.BizSupplierDao;
import cn.damei.entity.modules.BizSupplier;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class ProjectInstallAndSupplierService extends CrudService<ProjectInstallAndSupplierDao, ProjectInstallAndSupplier> {
	@Autowired
	private BizSupplierDao bizSupplierDao;

	public ProjectInstallAndSupplier get(String id) {
		return super.get(id);
	}

	public List<ProjectInstallAndSupplier> findList(ProjectInstallAndSupplier projectInstallAndSupplier) {
		return super.findList(projectInstallAndSupplier);
	}

	public Page<ProjectInstallAndSupplier> findPage(Page<ProjectInstallAndSupplier> page, ProjectInstallAndSupplier projectInstallAndSupplier) {
		return super.findPage(page, projectInstallAndSupplier);
	}

	@Transactional(readOnly = false)
	public void save(ProjectInstallAndSupplier projectInstallAndSupplier) {
		super.save(projectInstallAndSupplier);
	}

	@Transactional(readOnly = false)
	public void delete(ProjectInstallAndSupplier projectInstallAndSupplier) {

		super.delete(projectInstallAndSupplier);
	}

	@Transactional(readOnly = false)
	public void insert(ProjectInstallAndSupplier projectInstallAndSupplier) {
		if (projectInstallAndSupplier.getSupplierId() != null && !projectInstallAndSupplier.getSupplierId().equals("")) {
			String[] split = projectInstallAndSupplier.getSupplierId().split(",");
			for (String string : split) {
				projectInstallAndSupplier.setSupplierId(string);
				projectInstallAndSupplier.setId(null);
				projectInstallAndSupplier.setCreateBy(UserUtils.getUser());
				projectInstallAndSupplier.setCreateDate(new Date());
				dao.insert(projectInstallAndSupplier);
			}
		}

	}

	public Page<ProjectInstallAndSupplier> findPageListall(Page<ProjectInstallAndSupplier> page, ProjectInstallAndSupplier projectInstallAndSupplier) {
		projectInstallAndSupplier.setPage(page);
		List<ProjectInstallAndSupplier> findAllList = dao.findPageList(projectInstallAndSupplier);
		page.setList(findAllList);
		return page;
	}

	public List<BizSupplier> findPageList(ProjectInstallAndSupplier projectInstallAndSupplier) {




		projectInstallAndSupplier.setProjectInstallItemId(projectInstallAndSupplier.getId());
		List<ProjectInstallAndSupplier> list = dao.querySupplierId(projectInstallAndSupplier);
		List<BizSupplier> bizSupplierList = new ArrayList<BizSupplier>();
		if (list.size() > 0) {

			for (ProjectInstallAndSupplier projectInstallAndSupplier3 : list) {
				BizSupplier bizSupplier = new BizSupplier();
				bizSupplier.setId(projectInstallAndSupplier3.getSupplierId());
				BizSupplier bizSupplier1 = bizSupplierDao.get(bizSupplier);
				bizSupplierList.add(bizSupplier1);
			}
		}
		return bizSupplierList;
	}

	public Page<ProjectInstallAndSupplier> findPageListBack(Page<ProjectInstallAndSupplier> page, ProjectInstallAndSupplier projectInstallAndSupplier) {
		projectInstallAndSupplier.setPage(page);
		List<ProjectInstallAndSupplier> findAllList = dao.findPageList(projectInstallAndSupplier);
		for (ProjectInstallAndSupplier projectInstallAndSupplier2 : findAllList) {



			projectInstallAndSupplier2.setPage(page);
			List<ProjectInstallAndSupplier> list = dao.querySupplierId(projectInstallAndSupplier2);
			page.setList(list);
			if (list.size() > 0) {
				List<BizSupplier> bizSupplierList = new ArrayList<BizSupplier>();
				for (ProjectInstallAndSupplier projectInstallAndSupplier3 : list) {
					BizSupplier bizSupplier = new BizSupplier();
					bizSupplier.setId(projectInstallAndSupplier3.getSupplierId());
					BizSupplier bizSupplier1 = bizSupplierDao.get(bizSupplier);
					bizSupplierList.add(bizSupplier1);
				}
				projectInstallAndSupplier2.setBizSupplierList(bizSupplierList);
			}
		}
		page.setList(findAllList);
		return page;
	}

	public List<ProjectInstallAndSupplier> formUpdata(ProjectInstallAndSupplier projectInstallAndSupplier) {
		projectInstallAndSupplier.setInstallItemName1(projectInstallAndSupplier.getId());
		List<ProjectInstallAndSupplier> findAllList = dao.findPageList(projectInstallAndSupplier);
		return findAllList;
	}

	@Transactional(readOnly = false)
	public void deleteSupplier(ProjectInstallAndSupplier projectInstallAndSupplier) {
		dao.deleteUpdate(projectInstallAndSupplier);

	}
}