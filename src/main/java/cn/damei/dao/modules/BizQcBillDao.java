/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcBill;

/**
 * 质检报告DAO接口
 * @author wyb
 * @version 2016-10-31
 */
@MyBatisDao
public interface BizQcBillDao extends CrudDao<BizQcBill> {
	
	public BizQcBill queryQcBillByParam(Map<String,Object> param);

	public List<BizQcBill> findBizQcBillByOrderId(BizQcBill bizQcBill);
	
	public int checkSettleCheckNodeByOrderId(Integer ordreId);
	
}