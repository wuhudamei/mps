/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import cn.damei.entity.modules.BizOrderInstallItemProblemLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderInstallItemProblemLogDao;

/**
 * 订单安装项问题日志Service
 * @author 汪文
 * @version 2017-02-20
 */
@Service
@Transactional(readOnly = true)
public class BizOrderInstallItemProblemLogService extends CrudService2<BizOrderInstallItemProblemLogDao, BizOrderInstallItemProblemLog> {

	public BizOrderInstallItemProblemLog get(Integer id) {
		return super.get(id);
	}
	
	public List<BizOrderInstallItemProblemLog> findList(BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog) {
		return super.findList(bizOrderInstallItemProblemLog);
	}
	
	public Page<BizOrderInstallItemProblemLog> findPage(Page<BizOrderInstallItemProblemLog> page, BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog) {
		return super.findPage(page, bizOrderInstallItemProblemLog);
	}
	
	@Transactional(readOnly = false)
	public void save(BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog) {
		super.save(bizOrderInstallItemProblemLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog) {
		super.delete(bizOrderInstallItemProblemLog);
	}

	public BizOrderInstallItemProblemLog queryByProblemId(Map<String, Object> map) {
		return dao.queryByProblemId(map);
	}

	@Transactional(readOnly = false)
	public void insert(BizOrderInstallItemProblemLog bizOrderInstallItemProblemLog) {
		dao.insert(bizOrderInstallItemProblemLog);
	}
	
}