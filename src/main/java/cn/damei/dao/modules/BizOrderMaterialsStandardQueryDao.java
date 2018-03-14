
package cn.damei.dao.modules;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderMaterialsStandardQuery;


@MyBatisDao
public interface BizOrderMaterialsStandardQueryDao extends CrudDao<BizOrderMaterialsStandardQuery> {
	

	List<BizOrderMaterialsStandardQuery> getBizOrderMaterialsStandardQueryList(BizOrderMaterialsStandardQuery bizOrderMaterialsStandardQuery);

	BizOrderMaterialsStandardQuery getBizOrderMaterialsStandardQueryListByOrderId(@Param("orderId")String orderId,@Param("materialsLargeType")String materialsLargeType);
}