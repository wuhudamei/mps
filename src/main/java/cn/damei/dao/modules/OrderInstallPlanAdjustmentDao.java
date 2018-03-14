/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderInstallPlanAdjustment;

/**
 * 主材可申请安装/复尺日期查询DAO接口
 * @author wyb
 */
@MyBatisDao
public interface OrderInstallPlanAdjustmentDao extends CrudDao2<OrderInstallPlanAdjustment> {

	/**
	 * 主材可申请安装日期查询【列表页】【安装】
	 * @param orderInstallPlanAdjustment
	 * @return
	 */
	List<OrderInstallPlanAdjustment> findInstallList(OrderInstallPlanAdjustment orderInstallPlanAdjustment);

	/**
	 * 主材可申请复尺日期查询【列表页】【复尺】
	 * @param orderInstallPlanAdjustment
	 * @return
	 */
	List<OrderInstallPlanAdjustment> findChecksizeList(OrderInstallPlanAdjustment orderInstallPlanAdjustment);


	
}