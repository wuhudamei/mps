/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.service.modules;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizEmployeeBankcardRelatedIdcard;
import cn.damei.dao.modules.BizEmployeeBankcardRelatedIdcardDao;

/**
 * 员工身份证关联Service
 * @author qww
 * @version 2016-10-31
 */
@Service
@Transactional(readOnly = true)
public class BizEmployeeBankcardRelatedIdcardService extends CrudService2<BizEmployeeBankcardRelatedIdcardDao, BizEmployeeBankcardRelatedIdcard> {

	public BizEmployeeBankcardRelatedIdcard get(Integer id) {
		return super.get(id);
	}
	
	public List<BizEmployeeBankcardRelatedIdcard> findList(BizEmployeeBankcardRelatedIdcard bizEmployeeBankcardRelatedIdcard) {
		return super.findList(bizEmployeeBankcardRelatedIdcard);
	}
	
	public Page<BizEmployeeBankcardRelatedIdcard> findPage(Page<BizEmployeeBankcardRelatedIdcard> page, BizEmployeeBankcardRelatedIdcard bizEmployeeBankcardRelatedIdcard) {
		return super.findPage(page, bizEmployeeBankcardRelatedIdcard);
	}
	
	@Transactional(readOnly = false)
	public void save(BizEmployeeBankcardRelatedIdcard bizEmployeeBankcardRelatedIdcard) {
		super.save(bizEmployeeBankcardRelatedIdcard);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizEmployeeBankcardRelatedIdcard bizEmployeeBankcardRelatedIdcard) {
		super.delete(bizEmployeeBankcardRelatedIdcard);
	}
	
}