package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.PmMaterialsSettleInfoDao;
import cn.damei.entity.modules.PmMaterialsSettleInfo;

/**
 * 项目经理材料结算信息Service
 * @author hyh
 *
 */
@Service
@Transactional(readOnly = true)
public class PmMaterialsSettleInfoService extends CrudService2<PmMaterialsSettleInfoDao, PmMaterialsSettleInfo>{
	
	public Page<PmMaterialsSettleInfo> findPage(Page<PmMaterialsSettleInfo> page, PmMaterialsSettleInfo pmMaterialsSettleInfo){
		return super.findPage(page, pmMaterialsSettleInfo);
	}
 
	public List<PmMaterialsSettleInfo> queryPmMaterialsByOrderId(Integer orderId){
		return dao.queryPmMaterialsByOrderId(orderId);
	} 
	
	public List<PmMaterialsSettleInfo> queryPmMaterialsByParam(Map<String,Object> param){
		return dao.queryPmMaterialsByParam(param);
	}
	
	public List<PmMaterialsSettleInfo> queryPmMaterialsInfoByParam(Map<String,Object> param){
		return dao.queryPmMaterialsInfoByParam(param);
	}

}
