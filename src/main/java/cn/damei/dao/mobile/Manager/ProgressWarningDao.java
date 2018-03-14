package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.Progress;
import cn.damei.entity.mobile.Manager.ProgressWarning;
@MyBatisDao
public interface ProgressWarningDao extends CrudDao2<ProgressWarning>{

	public List<String> findAllDelayOrderId(Integer id);

	public List<String> findPurchaseOrderId(List<String> allOrderId);

	public List<ProgressWarning> findDelayInfo(List<String> allOrderId);

	public List<ProgressWarning> findDelayInfoAdd(List<String> allOrderId);

	public List<String> findCompleteOrderId(List<String> allOrderId);

	public List<ProgressWarning> findDelayMaterialsSum(List<String> allOrderId);

	public List<ProgressWarning> findDelayMaterialInfo(ProgressWarning pw);

	public List<ProgressWarning> findNoPurchaseInfo(List<String> temp);

	public List<Progress> findDelayMaterialsSumInfo(List<String> allOrderId);
	
	public List<Progress> findDelayMaterialsMiss(List<String> allOrderId);
}
