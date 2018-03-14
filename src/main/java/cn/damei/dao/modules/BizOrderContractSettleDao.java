package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderContractSettle;

/**
 * 订单承包结算Dao
 * @author hyh
 *
 */
@MyBatisDao
public interface BizOrderContractSettleDao extends CrudDao2<BizOrderContractSettle> {
	
	public BizOrderContractSettle findOrderContractSettleByParam(Map<String,Object> param);

}
