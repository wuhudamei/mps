package cn.damei.dao.mobile.Inspector;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.MaterialsInfo;

@MyBatisDao
public interface MaterialsInfoDao extends CrudDao2<MaterialsInfo>{

	/**
	 * 查询标化辅料、筒灯灯带的材料信息
	 * @param map(orderId,type) type:1  标化辅料  type:2筒灯灯带
	 * @return
	 */
	List<MaterialsInfo> queryMaterialsStandardInfoByParam(Map<String,Object> map);
	
	
	/**
	 * 查询标化辅料的材料信息
	 * @param map(orderId,type) type:1  标化辅料  type:2筒灯灯带
	 * @return
	 */
	List<MaterialsInfo> queryMaterialsStandardInfoByParam2(Map<String,Object> map);
	
	
	
	/**
	 * 查询沙子水泥信息（查询订单任务包下的沙子水泥信息）
	 * @param map
	 * @return
	 */
	List<MaterialsInfo> querySandMaterialsInfo(Integer orderId);
	

    /**
     * 查询墙地砖信息 （查询订单的墙地砖采购单下的墙地砖信息）
     * @param map
     * @return
     */
	List<MaterialsInfo>  queryQdzMaterialsInfo(Integer orderId);
	
	/**
	 * 查询开关面板信息（查询订单的开关面板采购单下的开关面板信息）
	 * @param map
	 * @return
	 */
	List<MaterialsInfo> queryKgmbMaterialsInfo(Integer orderId);
	
	/**
	 * 查询辅料 (查询订单任务包下的辅料信息)
	 * @param map
	 * @return
	 */
	List<MaterialsInfo> queryFlMaterialsInfo(Integer orderId);
}
