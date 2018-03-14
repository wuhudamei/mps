package cn.damei.dao.mobile.Worker;

import java.util.List;
import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Worker.SettlementAuxiliary;
@MyBatisDao
public interface SettlementAuxiliaryDao extends CrudDao2<SettlementAuxiliary>{

	List<SettlementAuxiliary> findAuxiliaryListForSettlement(Integer id);
	
	List<SettlementAuxiliary> findSandListForSettlement(Integer id);

}
