
package cn.damei.dao.modules;




import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.PurchaseStatistics;


@MyBatisDao
public interface PurchaseStatisticsDao {


	List<PurchaseStatistics> findList(PurchaseStatistics purchaseStatistics);


	List<PurchaseStatistics> findApplyList(PurchaseStatistics entity);


	List<PurchaseStatistics> findTransferSupplierList(PurchaseStatistics entity);


	List<PurchaseStatistics> findReceiveList(PurchaseStatistics entity);


	List<PurchaseStatistics> findStandardApplyList(PurchaseStatistics entity);


	List<PurchaseStatistics> findStandardReceiveList(PurchaseStatistics entity);
	
	

}