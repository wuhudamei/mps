
package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderMaterialsStandard;


@MyBatisDao
public interface BizOrderMaterialsStandardDao extends CrudDao2<BizOrderMaterialsStandard> {

	public List<BizOrderMaterialsStandard> getList(@Param("storeId")String storeId,@Param("orderId")String orderId,@Param("materialsLargeType")String materialsLargeType);

	public BizOrderMaterialsStandard getBizOrderMaterialsStandard(@Param("materialsStandardId")String materialsStandardId,@Param("orderId")String orderId);

	String getMaterialIsView(String orderId);

	String selectBillView(String billId);
	

	void updateBill(@Param("isViewed")String isViewed,
			@Param("viewDatetime")Date viewDatetime,
			@Param("id")String id
			);

	void updateOrderMaterialsByOrderIdAndStandId(BizOrderMaterialsStandard bizOrderMaterialsStandard);

	List<BizOrderMaterialsStandard> getMaterialsByOrderId(@Param("orderId")String orderId,@Param("materialsLargeType")String materialsLargeType);
}