package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPrePmSettleFin;

/**
 * 财务收款信息DAO
 * @author hyh
 *
 */
@MyBatisDao
public interface BizPrePmSettleFinDao extends CrudDao2<BizPrePmSettleFin>{
	
	public List<BizPrePmSettleFin> getBinPrePmByOrderIdAndType(BizPrePmSettleFin bizPrePmSettleFin);
	
}
