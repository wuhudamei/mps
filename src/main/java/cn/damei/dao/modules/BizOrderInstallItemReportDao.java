/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderCheckSize;
import cn.damei.entity.modules.BizOrderInstallItemReport;
import cn.damei.entity.modules.BizOrderInstasllPlanAndProblem;

/**
 * 工程部主材工期统计表
 */
@MyBatisDao
public interface BizOrderInstallItemReportDao extends CrudDao2<BizOrderInstallItemReport> {

	/**
	 * 查询订单信息
	 * @return
	 */
	List<BizOrderInstallItemReport> findOrderMessage();

	/**
	 * 批量更新  --- 工程部主材工期统计表
	 * @param mixUpdateList
	 * @return
	 */
	boolean batchUpdateList(List<BizOrderInstallItemReport> mixUpdateList);

	/**
	 * 批量插入  --- 工程部主材工期统计表
	 * @param mixInsertListList
	 * @return
	 */
	boolean batchInsertList(List<BizOrderInstallItemReport> mixInsertListList);

	/**
	 * 根据订单查询    厂家复尺信息
	 * @param orderId
	 * @return
	 */
	List<BizOrderCheckSize> findCheckSizeMessage(Integer orderId);

	/**
	 * 根据订单查询   主材安装以及问题上报信息
	 * @param orderId
	 * @return
	 */
	List<BizOrderInstasllPlanAndProblem> findInstallPlanAndProblemMessage(Integer orderId);

	
}