package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderFinanceCollection;

@MyBatisDao
public interface BizOrderFinanceCollectionDao extends CrudDao2<BizOrderFinanceCollection>{
	
	public int checkIsExistByParam(Map<String,Object> param);
	
	public List<BizOrderFinanceCollection> findListPrePmSettleFinList(BizOrderFinanceCollection bizOrderFinanceCollection);

}
