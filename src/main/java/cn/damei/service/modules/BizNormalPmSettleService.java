
package cn.damei.service.modules;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.entity.modules.BizNormalPmSettle;
import cn.damei.dao.modules.BizNormalPmSettleDao;


@Service
@Transactional(readOnly = true)
public class BizNormalPmSettleService extends CrudService<BizNormalPmSettleDao, BizNormalPmSettle> {

	public BizNormalPmSettle get(String id) {
		return super.get(id);
	}
	
	public List<BizNormalPmSettle> findList(BizNormalPmSettle bizNormalPmSettle) {
		return super.findList(bizNormalPmSettle);
	}
	
	public Page<BizNormalPmSettle> findPage(Page<BizNormalPmSettle> page, BizNormalPmSettle bizNormalPmSettle) {
		return super.findPage(page, bizNormalPmSettle);
	}
	
	@Transactional(readOnly = false)
	public void save(BizNormalPmSettle bizNormalPmSettle) {
		super.save(bizNormalPmSettle);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizNormalPmSettle bizNormalPmSettle) {
		super.delete(bizNormalPmSettle);
	}



	public List<String> findSettlePicBySettleId(Integer settleId, String picType){

		return dao.findSettlePicBySettleId(settleId,picType);
	}


	public List<String> findSettleNodeByStoreId(Integer storeId){


		return dao.findSettleNodeByStoreId(storeId);
	}

	public String checkSettleIsExit(Integer settleId){


		return dao.checkSettleIsExist(settleId);
	}
}