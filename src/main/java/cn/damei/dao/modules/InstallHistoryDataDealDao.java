package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Inspector.BizQcBill;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.InstallHistoryData;


@MyBatisDao
public interface InstallHistoryDataDealDao {


	List<InstallHistoryData> findList(InstallHistoryData installHistoryData);


	void deleteRepeatedData();


	void updateApplyData();


	void updateSupplierData();


	List<InstallHistoryData> findApplyList();


	List<InstallHistoryData> findSupplierList();


	List<InstallHistoryData> findAcceptList();


	boolean batchInsertList(List<BizBusinessStatusLog> mixInsertList);


	List<BizQcBill> findQcBillList();


	Inspector findInspector(Integer orderId);

    

}