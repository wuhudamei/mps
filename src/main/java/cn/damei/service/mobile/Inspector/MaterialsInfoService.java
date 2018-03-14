package cn.damei.service.mobile.Inspector;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.mobile.Inspector.MaterialsInfoDao;
import cn.damei.entity.mobile.Inspector.MaterialsInfo;

@Service
@Transactional(readOnly = true)
public class MaterialsInfoService {
	@Autowired
	private MaterialsInfoDao materialsInfoDao;

	/**
	 * 查询标化辅料、筒灯灯带的材料信息
	 * 
	 * @param map(orderId,type)
	 *            type:1 标化辅料 type:2筒灯灯带
	 * @return
	 */
	public List<MaterialsInfo> queryMaterialsStandardInfoByParam(Map<String, Object> map) {
		String type = (String) map.get("type");
		List<MaterialsInfo> list = null;
		if(type.equals("1")){
			list= materialsInfoDao.queryMaterialsStandardInfoByParam(map);
		}else if(type.equals("2")){
			list= materialsInfoDao.queryMaterialsStandardInfoByParam2(map);
		}
		return list;
	}

	/**
	 * 查询沙子水泥信息（查询订单任务包下的沙子水泥信息）
	 * 
	 * @param map
	 * @return
	 */
	public List<MaterialsInfo> querySandMaterialsInfo(Integer orderId) {
		return materialsInfoDao.querySandMaterialsInfo(orderId);
	}

	/**
	 * 查询墙地砖信息 （查询订单的墙地砖采购单下的墙地砖信息）
	 * 
	 * @param map
	 * @return
	 */
	public List<MaterialsInfo> queryQdzMaterialsInfo(Integer orderId) {
		return materialsInfoDao.queryQdzMaterialsInfo(orderId);
	}

	/**
	 * 查询开关面板信息（查询订单的开关面板采购单下的开关面板信息）
	 * 
	 * @param map
	 * @return
	 */
	public List<MaterialsInfo> queryKgmbMaterialsInfo(Integer orderId) {
		return materialsInfoDao.queryKgmbMaterialsInfo(orderId);
	}

	/**
	 * 查询辅料 (查询订单任务包下的辅料信息)
	 * 
	 * @param map
	 * @return
	 */
	public List<MaterialsInfo> queryFlMaterialsInfo(Integer orderId) {
		return materialsInfoDao.queryFlMaterialsInfo(orderId);
	}
}
