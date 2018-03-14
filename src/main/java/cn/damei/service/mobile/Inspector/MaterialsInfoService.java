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


	public List<MaterialsInfo> querySandMaterialsInfo(Integer orderId) {
		return materialsInfoDao.querySandMaterialsInfo(orderId);
	}


	public List<MaterialsInfo> queryQdzMaterialsInfo(Integer orderId) {
		return materialsInfoDao.queryQdzMaterialsInfo(orderId);
	}


	public List<MaterialsInfo> queryKgmbMaterialsInfo(Integer orderId) {
		return materialsInfoDao.queryKgmbMaterialsInfo(orderId);
	}


	public List<MaterialsInfo> queryFlMaterialsInfo(Integer orderId) {
		return materialsInfoDao.queryFlMaterialsInfo(orderId);
	}
}
