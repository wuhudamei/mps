package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.SettlementAuxiliary;
import cn.damei.entity.modules.InspectorPunish;
import cn.damei.entity.modules.ProIndustryPmSettleInfo;
import cn.damei.entity.modules.MainPanel;


@MyBatisDao
public interface ProIndustryPmSettleDao extends CrudDao2<ProIndustryPmSettleInfo>{
	

	public List<ProIndustryPmSettleInfo> queryProIndustryPmMidwaySettle(ProIndustryPmSettleInfo proIndustryPmSettleInfo);
	

	public List<ProIndustryPmSettleInfo> queryProIndustryPmCompleteSettle(ProIndustryPmSettleInfo proIndustryPmSettleInfo);
	
	public Double queryAuxiliaryMaterialsAmountByOrderId(Map<String,Object> param);
	
	public Double queryMaterialsStandardAmountByOrderId(Integer orderId);
	
	public Double queryMainPanelAmountByOrderId(Integer orderId);
	
	public Double queryWorkerAmountByOrderId(Integer orderId);
	
	public Double queryPmQcFineByParam(Map<String,Object> param);
	
	public Double querySubsidyPriceByParam(Map<String,Object> param);
	
	public Double queryBaseInstalledAmount(Map<String,Object> param);
	
	public List<SettlementAuxiliary> queryAuxiliaryInfoByParam(Map<String,Object> param);
	
	public List<MainPanel> queryMainPanelListByOrderId(Integer orderId);
	
	public List<InspectorPunish> queryPmQcFineInfoByParam(Map<String,Object> param);
}
