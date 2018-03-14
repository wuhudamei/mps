/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderStatusReport;

/**
 * 大统计表-大阶段(只查询订单状态是100至400 的项目)
 * @author llp
 * @version 2016-11-29
 */
@MyBatisDao
public interface BizOrderStatusReportDao extends CrudDao2<BizOrderStatusReport>{

	List<BizOrderStatusReport> getByStoreID(String storeId);
	
}