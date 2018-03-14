package cn.damei.service.modules;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizEnginInstallDao;
import cn.damei.entity.modules.BizEnginInstall;


@Service
@Transactional(readOnly = true)
public class BizEnginInstallService extends CrudService2<BizEnginInstallDao, BizEnginInstall>{
	
	@Autowired
	private BizEnginInstallDao bizEnginInstallDao;
	
	public BizEnginInstall get(Integer id) {
		return super.get(id);
	}
	
	public List<BizEnginInstall> findList(BizEnginInstall bizEnginInstall) {
		return super.findList(bizEnginInstall);
	}
	
	public Page<BizEnginInstall> findPage(Page<BizEnginInstall> page, BizEnginInstall bizEnginInstall) {
		return super.findPage(page, bizEnginInstall);
	}


	public List<BizEnginInstall> getByList() {
		return bizEnginInstallDao.getByList();
	}

}
