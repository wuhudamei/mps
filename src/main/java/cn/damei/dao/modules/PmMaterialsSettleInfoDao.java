package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.PmMaterialsSettleInfo;


@MyBatisDao
public interface PmMaterialsSettleInfoDao extends CrudDao2<PmMaterialsSettleInfo>{

	public List<PmMaterialsSettleInfo> queryPmMaterialsByOrderId(Integer orderId);
	
	public List<PmMaterialsSettleInfo> queryPmMaterialsByParam(Map<String,Object> param);
	
	public List<PmMaterialsSettleInfo> queryPmMaterialsInfoByParam(Map<String,Object> param);
}
