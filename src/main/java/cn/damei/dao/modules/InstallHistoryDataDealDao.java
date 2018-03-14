package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.Inspector;
import cn.damei.entity.mobile.Inspector.BizQcBill;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.InstallHistoryData;

/**
 * 主材安装历史数据处理
 */
@MyBatisDao
public interface InstallHistoryDataDealDao {

	/**
	 * 查询历史数据页面
	 * @param installHistoryData 
	 * @return
	 */
	List<InstallHistoryData> findList(InstallHistoryData installHistoryData);

	/**
	 * 批量删除重复数据 
	 */
	void deleteRepeatedData();

	/**
	 *  批量更新状态为2：申请的日志信息
	 */
	void updateApplyData();

	/**
	 * 批量更新状态为3：下达供应商的日志信息 
	 */
	void updateSupplierData();

	/**
	 * 查出所有需要批量插入的 2：申请日志  的主材信息
	 * @return
	 */
	List<InstallHistoryData> findApplyList();

	/**
	 * 查出所有需要批量插入的 3：下达供应商 日志  的主材信息
	 * @return
	 */
	List<InstallHistoryData> findSupplierList();

	/**
	 * 查出所有需要批量插入的 4：验收 日志  的主材信息
	 * @return
	 */
	List<InstallHistoryData> findAcceptList();

	/**
	 * 批量插入
	 * @param mixInsertList
	 * @return
	 */
	boolean batchInsertList(List<BizBusinessStatusLog> mixInsertList);

	/**
	 * 查询约检单(基装验收和竣工验收)
	 * @return
	 */
	List<BizQcBill> findQcBillList();

	/**
	 * 查询订单的质检员信息
	 * @param orderId
	 * @return
	 */
	Inspector findInspector(Integer orderId);

    

}