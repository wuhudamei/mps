package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizPrePmSettleFinDao;
import cn.damei.entity.modules.BizPrePmSettleFin;


@Service
@Transactional(readOnly = true)
public class BizPrePmSettleFinService extends CrudService2<BizPrePmSettleFinDao,BizPrePmSettleFin>{

	public BizPrePmSettleFin get(Integer id){
		return super.get(id);
	};
	
	public List<BizPrePmSettleFin> findList(BizPrePmSettleFin bizPrePmSettleFin){
		return super.findList(bizPrePmSettleFin);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPrePmSettleFin bizPrePmSettleFin){
		super.save(bizPrePmSettleFin);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPrePmSettleFin bizPrePmSettleFin){
		super.delete(bizPrePmSettleFin);
	}
	
	
}
