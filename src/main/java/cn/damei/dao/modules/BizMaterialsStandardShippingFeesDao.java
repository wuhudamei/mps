
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsStandardShippingFees;
import org.apache.ibatis.annotations.Param;


@MyBatisDao
public interface BizMaterialsStandardShippingFeesDao extends CrudDao2<BizMaterialsStandardShippingFees> {

    BizMaterialsStandardShippingFees getShippingFee(@Param("storeId")Integer storeId, @Param("bizMaterialsStandardType")Integer bizMaterialsStandardType);
}