package cn.damei.service.modules;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizAssessRuleDao;
import cn.damei.entity.modules.BizAssessRule;

/**
 * 考核条例细则Service
 * @author hyh
 *
 */
@Service
@Transactional(readOnly = true)
public class BizAssessRuleService extends CrudService2<BizAssessRuleDao, BizAssessRule>{
	
	public Page<BizAssessRule> findPage(Page<BizAssessRule> page, BizAssessRule bizAssessRule) {
		return super.findPage(page, bizAssessRule);
	}
	
	@Transactional(readOnly = false)
	public void save(BizAssessRule bizAssessRule){
		super.save(bizAssessRule);
	}
}
