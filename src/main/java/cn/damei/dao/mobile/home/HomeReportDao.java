package cn.damei.dao.mobile.home;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.home.CheckBreak;
import cn.damei.entity.mobile.home.CheckItem;
import cn.damei.entity.mobile.home.Order;
import cn.damei.entity.mobile.home.Report;
import cn.damei.entity.mobile.home.ViewLog;
/**
 * 客户端 质检报告
 * @author Administrator
 *
 */
@MyBatisDao
public interface HomeReportDao {

	/**
	 * 查询顾客所有的订单列表
	 * @param order
	 * @return
	 */
	List<Order> findOrderList(Order order);
	
	/**
	 * 查看质检报告列表
	 * @param report
	 * @return
	 */
	Order findQcBill(Order order);

	/**
	 * 质检报告详情
	 * @param qcBillId
	 * @return
	 */
	Report reportDetail(Integer qcBillId);

	/**
	 * 违规形式及处理方式
	 * @param qcBillItemId
	 * @return
	 */
	CheckItem findCheckBreak(Integer qcBillItemId);

	/**
	 * 查看消息是否已读
	 * @param viewLog
	 * @return
	 */
	Integer findView(ViewLog viewLog);

	/**
	 *	如果未读则插入已读信息
	 * @param viewLog
	 */
	void insertView(ViewLog viewLog);

	/**
	 * 质检罚款详情
	 * @param id 任务包id
	 * @return
	 */
	List<Report> queryQcBillList(Integer id);

	/**
	 * 违规形式
	 * @param qcBillItemId
	 * @return
	 */
	List<CheckBreak> queryCheckBreaks(Integer qcBillItemId);

	


	
	
}
