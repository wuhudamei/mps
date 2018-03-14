package cn.damei.service.modules;

import java.util.List;

import cn.damei.entity.modules.BizMaterialsStandardRecords;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.modules.BizMaterialsStandardReceiveBillApplyDao;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBillApply;
@Service
@Transactional
public class BizMaterialsStandardReceiveBillApplyService extends CrudService2<BizMaterialsStandardReceiveBillApplyDao, BizMaterialsStandardReceiveBillApply>{

	@Autowired
	BizMaterialsStandardReceiveBillApplyDao bizMaterialsStandardReceiveBillApplyDao;
	
	public void materialsApplyBillById(
			BizMaterialsStandardReceiveBillApply bizMaterialsStandardReceiveBillApply) {
		bizMaterialsStandardReceiveBillApplyDao.materialsApplyBillById(bizMaterialsStandardReceiveBillApply);
	}

	public List<BizMaterialsStandardReceiveBillApply> materialsApplyBillVoById(BizMaterialsStandardReceiveBillApply ba) {
		// TODO Auto-generated method stub
		return bizMaterialsStandardReceiveBillApplyDao.materialsApplyBillVoById(ba);
	}

	public List<BizMaterialsStandardRecords> findApplyMaterialsStandardReceiveDetailById(String billId) {
		// TODO Auto-generated method stub
		return bizMaterialsStandardReceiveBillApplyDao.findApplyMaterialsStandardReceiveDetailById(billId);
	}

	/**
	 * 根据订单 id 查询下面 （已领取）的订单项
	 * @param orderId
	 * @return
	 */
	public List<BizMaterialsStandardReceiveBillApply> findMaterialsApplyBillByOrderId(@Param("orderId") String orderId, @Param("receiveBillType")String receiveBillType){
		return bizMaterialsStandardReceiveBillApplyDao.findMaterialsApplyBillByOrderId(orderId,receiveBillType);
	}
	
	
	

}
