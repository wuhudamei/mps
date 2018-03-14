/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.CheckDetails;
import cn.damei.entity.modules.BizQcBill;

/**
 * 项目约检情况查询DAO接口
 * @author wyb
 * @version 2016-10-31
 */
@MyBatisDao
public interface CheckDetailsDao extends CrudDao<CheckDetails> {

	/**
	 * 约检单列表
	 * @param orderId
	 * @return
	 */
	List<BizQcBill> detailsList(Integer orderId);
	
}