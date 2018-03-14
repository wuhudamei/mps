
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmAttendMonth;

import java.util.Date;
import java.util.List;


@MyBatisDao
public interface BizPmAttendMonthDao extends CrudDao<BizPmAttendMonth> {
	
	List<BizPmAttendMonth> getBizPmAttendMonthList(BizPmAttendMonth bizPmAttendMonth);
	
	List<BizPmAttendMonth> getBizPmAttendMonthCount(BizPmAttendMonth bizPmAttendMonth);
	
	List<BizPmAttendMonth> findPmAttendList(BizPmAttendMonth bizPmAttendMonth);
	
	List<BizPmAttendMonth> getBizMonthSalary(BizPmAttendMonth bizPmAttendMonth);
	
	void updatePmAttendMonthStatus(BizPmAttendMonth bizPmAttendMonth);
	
	BizPmAttendMonth bizPmAttendSalaryBillDetail(BizPmAttendMonth bizPmAttendMonth);
	
	List<BizPmAttendMonth> getOrderList(BizPmAttendMonth bizPmAttendMonth);
	
	String getCheckTimeByOrderIdAndNode(int parseInt, String string);
	
	
	void insertMonthOrder(BizPmAttendMonth bizPmAttendMonth);
	
	List<BizPmAttendMonth> getBizMonthOrder(BizPmAttendMonth bizPmAttendMonth);
	
	List<BizPmAttendMonth> findMinthOrder(BizPmAttendMonth bizPmAttendMonth);
	
	void updatePmAttendMonth(BizPmAttendMonth bizPmAttendMonth);
	
	BizPmAttendMonth findAttendMonth(BizPmAttendMonth bizPmAttendMonth);
	
	List<BizPmAttendMonth> findPmAttendDetailList(BizPmAttendMonth bizPmAttendMonth);
	
	List<BizPmAttendMonth> getDetailFormList(String  attendMonth,String itemManagerId,String date,String status);
	
	BizPmAttendMonth getQdcs(BizPmAttendMonth bizPmAttendMonth);
	
	int getSignDateTimesCount(BizPmAttendMonth bizAttendMonth);
	
	int getSignDateTimesCount1(Date date, String orderId, Date attendStartDate);
	
	void deleteMonth(BizPmAttendMonth bizPmAttendMonth);
	
	BizPmAttendMonth findAttendMonthOrder(BizPmAttendMonth bizPmAttendMonth);
	

	String changeManager(String orderId, String itemManagerId, String attendMonth);
	
	int getSignDateTimesChangeManager(Date date, String orderId, Date changeUpdateDate,String signDateTimesChangeManager);
	

	List<BizPmAttendMonth> findOldManagerOrder( String oldEmployeeId);

	BizPmAttendMonth getOldOrder(String orderId);
	
	String changeManagerOld(String orderId, String itemManagerId,String attendMonth);

	String getOrderInsertDate(BizPmAttendMonth bizPmAttendMonth);

    List<BizPmAttendMonth> findOrderByManagerId(String managerId, String kqMonth);

    Date getOrderStartDate(String orderId);

    List<BizPmAttendMonth> findChangeManagerDetail(String orderId, String kqMonth);

    Integer findRealSignTimes(String orderId, String attendMonth, String itemManagerId);

    List<BizPmAttendMonth> getOrderListByManagerId(BizPmAttendMonth bizPmAttendMonth);

    List<BizPmAttendMonth> findChangeManagerList(BizPmAttendMonth bizPmAttendMonth);

    void insertOrderManagerId();

    Integer getRealSignTimes(BizPmAttendMonth bizPmAttendMonth);

    Boolean isCheckedOrder(BizPmAttendMonth value);

    String findProjectModeByManagerId(String itemManagerId);

	BizPmAttendMonth findSalaryAllAttend(String findProjectMode, String storeId,Integer itemManagerStar);
}