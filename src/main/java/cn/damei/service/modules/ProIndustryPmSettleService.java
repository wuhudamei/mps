package cn.damei.service.modules;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.mobile.Worker.SettlementAuxiliary;
import cn.damei.entity.modules.InspectorPunish;
import cn.damei.dao.modules.ProIndustryPmSettleDao;
import cn.damei.entity.modules.ProIndustryPmSettleInfo;
import cn.damei.entity.modules.MainPanel;

/**
 * 准产业订单项目经理结算信息Service
 * @author hyh
 *
 */

@Service
@Transactional(readOnly = true)
public class ProIndustryPmSettleService extends CrudService2<ProIndustryPmSettleDao,ProIndustryPmSettleInfo>{

	/**
	 * 项目经理中期结算信息
	 * @param page
	 * @param proIndustryPmSettleInfo
	 * @return
	 */
	public Page<ProIndustryPmSettleInfo> queryProIndustryPmMidwaySettle(Page<ProIndustryPmSettleInfo> page,
			ProIndustryPmSettleInfo proIndustryPmSettleInfo) {
		proIndustryPmSettleInfo.setPage(page);
		page.setList(dao.queryProIndustryPmMidwaySettle(proIndustryPmSettleInfo));
		return page;
	}
	
	/**
	 * 项目经理竣工结算信息
	 * @param page
	 * @param proIndustryPmSettleInfo
	 * @return
	 */
	public Page<ProIndustryPmSettleInfo> queryProIndustryPmCompleteSettle(Page<ProIndustryPmSettleInfo> page,
			ProIndustryPmSettleInfo proIndustryPmSettleInfo){
		proIndustryPmSettleInfo.setPage(page);
		page.setList(dao.queryProIndustryPmCompleteSettle(proIndustryPmSettleInfo));
		return page;
	}
	
	public Double queryAuxiliaryMaterialsAmountByOrderId(Map<String,Object> param){
		return dao.queryAuxiliaryMaterialsAmountByOrderId(param);
	}
	
	public Double queryMaterialsStandardAmountByOrderId(Integer orderId){
		return dao.queryMaterialsStandardAmountByOrderId(orderId);
	}
	
	public Double queryMainPanelAmountByOrderId(Integer orderId){
		return dao.queryMainPanelAmountByOrderId(orderId);
	}
	
	public Double queryWorkerAmountByOrderId(Integer orderId){
		return dao.queryWorkerAmountByOrderId(orderId);
	}
	
	public Double queryPmQcFineByParam(Map<String,Object> param){
		return dao.queryPmQcFineByParam(param);
	}
	
	public Double querySubsidyPriceByParam(Map<String,Object> param){
		return dao.querySubsidyPriceByParam(param);
	}
	
	public Double queryBaseInstalledAmount(Map<String,Object> param){
		return dao.queryBaseInstalledAmount(param);
	}
	
	public List<SettlementAuxiliary> queryAuxiliaryInfoByParam(Map<String,Object> param){
		return dao.queryAuxiliaryInfoByParam(param);
	}
	
	public List<MainPanel> queryMainPanelListByOrderId(Integer orderId){
		return dao.queryMainPanelListByOrderId(orderId);
	}
	
	public List<InspectorPunish> queryPmQcFineInfoByParam(Map<String,Object> param){
		return dao.queryPmQcFineInfoByParam(param);
	}
}
