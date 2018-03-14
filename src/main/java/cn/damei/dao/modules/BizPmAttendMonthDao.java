/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmAttendMonth;

import java.util.Date;
import java.util.List;

/**
 * 考勤月度表DAO接口
 * @author wl
 * @version 2017-08-02
 */
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
	
	//更换项目经理查询日期
	String changeManager(String orderId, String itemManagerId, String attendMonth);
	
	int getSignDateTimesChangeManager(Date date, String orderId, Date changeUpdateDate,String signDateTimesChangeManager);
	
	//查询该项目经理对应的换项目经理信息
	List<BizPmAttendMonth> findOldManagerOrder( String oldEmployeeId);
	//查询换项目经理之前的订单信息
	BizPmAttendMonth getOldOrder(String orderId);
	
	String changeManagerOld(String orderId, String itemManagerId,String attendMonth);
	//获取插入表中的最近一次时间
	String getOrderInsertDate(BizPmAttendMonth bizPmAttendMonth);
//    TODU
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