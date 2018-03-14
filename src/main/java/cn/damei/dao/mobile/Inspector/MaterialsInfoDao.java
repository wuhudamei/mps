package cn.damei.dao.mobile.Inspector;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.MaterialsInfo;

@MyBatisDao
public interface MaterialsInfoDao extends CrudDao2<MaterialsInfo>{


	List<MaterialsInfo> queryMaterialsStandardInfoByParam(Map<String,Object> map);
	
	

	List<MaterialsInfo> queryMaterialsStandardInfoByParam2(Map<String,Object> map);
	
	
	

	List<MaterialsInfo> querySandMaterialsInfo(Integer orderId);
	


	List<MaterialsInfo>  queryQdzMaterialsInfo(Integer orderId);
	

	List<MaterialsInfo> queryKgmbMaterialsInfo(Integer orderId);
	

	List<MaterialsInfo> queryFlMaterialsInfo(Integer orderId);
}
