
package cn.damei.dao.modules;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizCustomerReturnVisitOrderEnable;


@MyBatisDao
public interface BizCustomerReturnVisitOrderEnableDao extends CrudDao2<BizCustomerReturnVisitOrderEnable> {
	Integer selectCount(@Param("orderId")Integer id);
}