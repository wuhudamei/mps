
package cn.damei.dao.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.DropModel;
import cn.damei.entity.modules.OrderTaskpackGenVo;
import cn.damei.entity.modules.OrderTaskpackVo;
import cn.damei.entity.modules.BizProcedure;


@MyBatisDao
public interface BizProcedureDao extends CrudDao<BizProcedure> {

	BizProcedure getByProcedureNo(String procedureNo);

	List<BizProcedure> getByBatchProcedureNo(List<String> list);

	List<DropModel> findAllProcedure();

	OrderTaskpackVo findProcedureById(String procedureId, String storeId,Date contractStartDate,String projectMode);

	OrderTaskpackGenVo findTaskpackageProcedureById(String procedureId, String storeId, Date contractStartDate);
	
}