/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;




import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.PurchaseStatistics;

/**
 * 材料统计表DAO接口
 */
@MyBatisDao
public interface PurchaseStatisticsDao {

	/**
	 * 材料统计表
	 * @param purchaseStatistics
	 * @return
	 */
	List<PurchaseStatistics> findList(PurchaseStatistics purchaseStatistics);

	/**
	 * 辅料、开关面板、墙地砖、沙子水泥 发货申请单数
	 * @param entity
	 * @return
	 */
	List<PurchaseStatistics> findApplyList(PurchaseStatistics entity);

	/**
	 * 辅料、开关面板、墙地砖、沙子水泥 转给供应商单数
	 * @param entity
	 * @return
	 */
	List<PurchaseStatistics> findTransferSupplierList(PurchaseStatistics entity);

	/**
	 * 辅料、开关面板、墙地砖、沙子水泥  收货单数
	 * @param entity
	 * @return
	 */
	List<PurchaseStatistics> findReceiveList(PurchaseStatistics entity);

	/**
	 * 标化、筒灯灯带  申请单数
	 * @param entity
	 * @return
	 */
	List<PurchaseStatistics> findStandardApplyList(PurchaseStatistics entity);

	/**
	 * 标化、筒灯灯带  领取单数
	 * @param entity
	 * @return
	 */
	List<PurchaseStatistics> findStandardReceiveList(PurchaseStatistics entity);
	
	

}