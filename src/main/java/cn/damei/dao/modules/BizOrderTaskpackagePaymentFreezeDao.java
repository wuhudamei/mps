package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentFreeze;
/**
 * 冻结/解冻付款单
 * @author hyh
 *
 */
@MyBatisDao
public interface BizOrderTaskpackagePaymentFreezeDao extends CrudDao2<BizOrderTaskpackagePaymentFreeze>{

}
