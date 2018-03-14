package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderTaskpackageAuxiliaryMaterials;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface OrderTaskpackageAuxiliaryMaterialsDao extends CrudDao2<OrderTaskpackageAuxiliaryMaterials>{
	

	public int updateOrderTaskpackageAuxiliaryMaterials(OrderTaskpackageAuxiliaryMaterials auxiliary);


	public int queryCountByTaskIdAndAuxiliaryNo(Map<String, Object> map);
	

	public List<OrderTaskpackageAuxiliaryMaterials> queryAuxiliaryMaterialByPassOrderTaskpackageSettle(Map<String,Object> param);
}
