/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsStandardShippingFees;
import org.apache.ibatis.annotations.Param;

/**
 * 筒灯灯带标化辅料配送费DAO接口
 * @author Ryze
 * @version 2017-10-25
 */
@MyBatisDao
public interface BizMaterialsStandardShippingFeesDao extends CrudDao2<BizMaterialsStandardShippingFees> {

    BizMaterialsStandardShippingFees getShippingFee(@Param("storeId")Integer storeId, @Param("bizMaterialsStandardType")Integer bizMaterialsStandardType);
}