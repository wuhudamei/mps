
package cn.damei.dao.mobile.Manager;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.entity.mobile.Manager.AppOrderCadfile;


@MyBatisDao
public interface AppOrderDao extends CrudDao2<AppOrder>{
	

	List<AppOrder> findOrderByitemManager(AppOrder appOrder);

	List<String> selectState(int itemManagerId);

	AppOrder getOrder(Integer id);
	

	boolean updateDelayType(String typeValue, String orderId);

	String findHouseType(AppOrder appOrder);
	

	List<AppOrderCadfile> selectCadfile(Integer id);

	public AppOrder queryOrderByOrderTaskpackageId(Integer orderTaskpackageId);
	

	String getOrderStatusByMaterislType(@Param("orderId")String orderId,@Param("materislType")String materislType);
}