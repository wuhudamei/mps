
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.OrderTaskpackageProcedure;


@MyBatisDao
public interface OrderTaskpackageProcedureDao extends CrudDao<OrderTaskpackageProcedure> {

	boolean insertByOrder1(OrderTaskpackageProcedure opt);

	List<OrderTaskpackageProcedure> getByTaskpackageId(Integer taskpackageId);

	boolean insertProcedure(OrderTaskpackageProcedure p);

	boolean updateById(Double budgetNumber,Double total,String procedureID,String remarks,Double laborBudgetAmount, Double auxiliaryMaterialsBudgetAmount);
	
}