package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.SettlementAuxiliary;
import cn.damei.entity.modules.InspectorPunish;
import cn.damei.entity.modules.ProIndustryPmSettleInfo;
import cn.damei.entity.modules.MainPanel;

/**
 * 准产业订单项目经理结算信息Dao
 * @author hyh
 *
 */
@MyBatisDao
public interface ProIndustryPmSettleDao extends CrudDao2<ProIndustryPmSettleInfo>{
	
	/**
	 *  项目经理中期结算信息
	 * @param proIndustryPmSettleInfo
	 * @return
	 */
	public List<ProIndustryPmSettleInfo> queryProIndustryPmMidwaySettle(ProIndustryPmSettleInfo proIndustryPmSettleInfo);
	
	/**
	 * 项目经理竣工结算信息
	 * @param proIndustryPmSettleInfo
	 * @return
	 */
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
