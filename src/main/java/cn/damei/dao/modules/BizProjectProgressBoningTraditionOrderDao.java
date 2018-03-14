/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNodePlanProject;
import cn.damei.entity.modules.BizTraditionOrder;


/**
 * 工程进度大看板DAO接口
 * @author qww
 * @version 2016-10-26
 */
@MyBatisDao
public interface BizProjectProgressBoningTraditionOrderDao extends CrudDao2<BizTraditionOrder> {

	/**
	 * 根据订单ID查询biz_node_plan表节点进度
	 */
	List<BizNodePlanProject> findPlanList(Integer orderId);
	
	/**
	 * excel无分页导出
	 * @param bizTraditionOrder
	 * @return
	 */
	List<BizTraditionOrder> findListExcel(BizTraditionOrder bizTraditionOrder);
    /**
     * excel无分页导出
     * @param bizTraditionOrder
     * @return
     */
    List<BizTraditionOrder> findListExcel1(BizTraditionOrder bizTraditionOrder);
}