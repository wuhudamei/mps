package cn.damei.service.mobile.Manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.mobile.Manager.GuaranteeMoneyDao;
import cn.damei.entity.mobile.Manager.GuaranteeMoney;


@Service
@Transactional(readOnly=true)
public class GuaranteeMoneyService  extends  CrudService2<GuaranteeMoneyDao,GuaranteeMoney> {


	public Integer queryGuaranteeMoneyCount(Integer taskpackageTemplatId, Integer employeeId, Integer settlementId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskpackageTemplatId", taskpackageTemplatId);
		map.put("employeeId", employeeId);
		map.put("isDeduct", ConstantUtils.IS_QUALITY_GUARANTEE_YES);
		if(settlementId != null){
			map.put("settlementId", settlementId);
		}
		return dao.queryGuaranteeMoneyCount(map);
	}
	

	public Double queryGuaranteeMoneySum(Integer employeeId, Integer settlementId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("employeeId", employeeId);
		if(settlementId != null){
			map.put("settlementId", settlementId);
		}
		return dao.queryGuaranteeMoneySum(map);
	}
	
	public GuaranteeMoney queryGuarnteeMoney(Map<String,Object> map){
		return dao.queryGuarnteeMoney(map);
	}
}
