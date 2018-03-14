package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizAssessRuleTypeDao;
import cn.damei.entity.modules.BizAssessRuleType;


@Service
@Transactional(readOnly = true)
public class BizAssessRuleTypeService extends CrudService2<BizAssessRuleTypeDao, BizAssessRuleType>{
	public Page<BizAssessRuleType> findPage(Page<BizAssessRuleType> page, BizAssessRuleType bizAssessRuleType) {
		return super.findPage(page, bizAssessRuleType);
	}
	
	@Transactional(readOnly = false)
	public void save(BizAssessRuleType bizAssessRuleType){
		super.save(bizAssessRuleType);
	}
	
	public  List<BizAssessRuleType> queryRuleTypeByParam(BizAssessRuleType bizAssessRuleType){
		return dao.findList(bizAssessRuleType);
	}
}
