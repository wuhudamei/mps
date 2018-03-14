package cn.damei.service.modules;

import java.util.Map;

import cn.damei.entity.modules.BizOrderFinanceCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizOrderFinanceCollectionDao;

@Service
@Transactional(readOnly = true)
public class BizOrderFinanceCollectionService extends CrudService2<BizOrderFinanceCollectionDao, BizOrderFinanceCollection>{

	public int checkIsExistByParam(Map<String,Object> param){
		return dao.checkIsExistByParam(param);
	}
	
	public Page<BizOrderFinanceCollection> findListPrePmSettleFinList(Page<BizOrderFinanceCollection> page,BizOrderFinanceCollection bizOrderFinanceCollection){
		bizOrderFinanceCollection.setPage(page);
		page.setList(dao.findListPrePmSettleFinList(bizOrderFinanceCollection));
		return page;
	}
}
