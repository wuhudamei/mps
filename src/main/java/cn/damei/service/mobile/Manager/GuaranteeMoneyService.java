package cn.damei.service.mobile.Manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.mobile.Manager.GuaranteeMoneyDao;
import cn.damei.entity.mobile.Manager.GuaranteeMoney;
/** 
* @author 梅浩   qww@zzhyun.cn: 
* @version 创建时间：2016年9月28日 上午10:14:21 
* 类说明 
*/

@Service
@Transactional(readOnly=true)
public class GuaranteeMoneyService  extends  CrudService2<GuaranteeMoneyDao,GuaranteeMoney> {

	/**
	 * 查询工人组长扣质保金次数
	 * @param taskpackageTemplatId
	 * @param employeeId
	 * @return
	 */
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
	
	/**
	 * 查询工人组长所扣质保金累积
	 * @param employeeId
	 * @return
	 */
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
