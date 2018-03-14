package cn.damei.service.mobile.Worker;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Worker.SettlementAuxiliaryDao;
import cn.damei.entity.mobile.Worker.SettlementAuxiliary;

@Service
@Transactional(readOnly = true)
public class SettlementAuxiliaryService extends CrudService2<SettlementAuxiliaryDao, SettlementAuxiliary>{

	public List<SettlementAuxiliary> findAuxiliaryListForSettlement(Integer id) {
		return dao.findAuxiliaryListForSettlement(id);
	}
	
	public List<SettlementAuxiliary> findSandListForSettlement(Integer id){
		return dao.findSandListForSettlement(id);
	}

}
