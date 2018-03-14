/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderTaskpackageProcedure;

/**
 * 订单任务包工序DAO接口
 * @author llp
 * @version 2016-09-23
 */
@MyBatisDao
public interface OrderTaskpackageProcedureDao extends CrudDao<OrderTaskpackageProcedure> {

	boolean insertByOrder1(OrderTaskpackageProcedure opt);

	List<OrderTaskpackageProcedure> getByTaskpackageId(Integer taskpackageId);

	boolean insertProcedure(OrderTaskpackageProcedure p);

	boolean updateById(Double budgetNumber,Double total,String procedureID,String remarks,Double laborBudgetAmount, Double auxiliaryMaterialsBudgetAmount);
	
}