package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizProjectInstallItemProblemTypeDao;
import cn.damei.entity.modules.BizProjectInstallItemProblemType;

/**
 * 工程安装问题分类设置Service
 * @author wyb
 * @version 2016-10-27
 */
@Service
@Transactional(readOnly = true)
public class BizProjectInstallItemProblemTypeService extends CrudService2<BizProjectInstallItemProblemTypeDao, BizProjectInstallItemProblemType> {

	public BizProjectInstallItemProblemType get(Integer id) {
		return super.get(id);
	}
	
	public List<BizProjectInstallItemProblemType> findList(BizProjectInstallItemProblemType bizProjectInstallItemProblemType) {
		return super.findList(bizProjectInstallItemProblemType);
	}
	
	public Page<BizProjectInstallItemProblemType> findPage(Page<BizProjectInstallItemProblemType> page, BizProjectInstallItemProblemType bizProjectInstallItemProblemType) {
		return super.findPage(page, bizProjectInstallItemProblemType);
	}
	
	@Transactional(readOnly = false)
	public void save(BizProjectInstallItemProblemType bizProjectInstallItemProblemType) {
		super.save(bizProjectInstallItemProblemType);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizProjectInstallItemProblemType bizProjectInstallItemProblemType) {
		super.delete(bizProjectInstallItemProblemType);
	}

	
}