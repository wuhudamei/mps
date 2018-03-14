package cn.damei.dao.mobile.Manager;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderTaskpackageAuxiliaryMaterials;

import java.util.List;
import java.util.Map;

/**
 * 项目经理端
 * 现场交底
 * @author qww
 * 2016/10/17
 */
@MyBatisDao
public interface OrderTaskpackageAuxiliaryMaterialsDao extends CrudDao2<OrderTaskpackageAuxiliaryMaterials>{
	
	/**
	 * 修改改结算单
	 * @param auxiliary
	 * @return
	 */
	public int updateOrderTaskpackageAuxiliaryMaterials(OrderTaskpackageAuxiliaryMaterials auxiliary);

	/**
	 * 根据任务包id和辅料编号查询条数
	 * @param map
	 * @return
	 */
	public int queryCountByTaskIdAndAuxiliaryNo(Map<String, Object> map);
	
	/**
	 *  查询出有效结算单下的所有辅料信息
	 * @param param
	 * @return
	 */
	public List<OrderTaskpackageAuxiliaryMaterials> queryAuxiliaryMaterialByPassOrderTaskpackageSettle(Map<String,Object> param);
}
