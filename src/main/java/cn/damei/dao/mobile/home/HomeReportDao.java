package cn.damei.dao.mobile.home;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.CheckBreak;
import cn.damei.entity.mobile.home.CheckItem;
import cn.damei.entity.mobile.home.Order;
import cn.damei.entity.mobile.home.Report;
import cn.damei.entity.mobile.home.ViewLog;

@MyBatisDao
public interface HomeReportDao {


	List<Order> findOrderList(Order order);
	

	Order findQcBill(Order order);


	Report reportDetail(Integer qcBillId);


	CheckItem findCheckBreak(Integer qcBillItemId);


	Integer findView(ViewLog viewLog);


	void insertView(ViewLog viewLog);


	List<Report> queryQcBillList(Integer id);


	List<CheckBreak> queryCheckBreaks(Integer qcBillItemId);

	


	
	
}
