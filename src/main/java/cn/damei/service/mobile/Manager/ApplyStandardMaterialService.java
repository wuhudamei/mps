package cn.damei.service.mobile.Manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.dao.mobile.Manager.ApplyStandardMaterialDao;
import cn.damei.entity.mobile.Manager.ApplyMaterialsStandardReceiveDetail;
import cn.damei.entity.mobile.Manager.ApplyStandardMaterial;
import cn.damei.entity.mobile.Manager.BizMaterialsStandard;
import cn.damei.entity.mobile.Manager.BizMaterialsStandardReceiveBillApply;
import cn.damei.entity.mobile.Manager.Seiralnum;
import cn.damei.dao.mobile.Manager.BizEvalRewardTaskpackDao;
import cn.damei.entity.mobile.Manager.BizEvalRewardTaskpack;
import cn.damei.dao.mobile.Manager.AppOrderDao;

@Service
@Transactional
public class ApplyStandardMaterialService extends CrudService2<BizEvalRewardTaskpackDao, BizEvalRewardTaskpack>{
	@Autowired
	private AppOrderDao orderDao;
	@Autowired
	private ApplyStandardMaterialDao applyStandardMaterialDao;
	public List<ApplyStandardMaterial> findOrderListById(ApplyStandardMaterial am,String materislType) {
		List<ApplyStandardMaterial> list =  applyStandardMaterialDao.findOrderListById(am);
		for (ApplyStandardMaterial applyStandardMaterial : list) {
			applyStandardMaterial.setIsBasicworkCompleted(orderDao.getOrderStatusByMaterislType(String.valueOf(applyStandardMaterial.getId()), materislType));
		}
		return list;
		
	}
	public List<BizMaterialsStandardReceiveBillApply> findMaterialStandardBillByOrderId(String orderId,String receiveBillType) {

		List<BizMaterialsStandardReceiveBillApply> list = applyStandardMaterialDao.findMaterialStandardBillByOrderId(orderId,receiveBillType);
		return list;
	}
	public List<BizMaterialsStandard> findBizMaterialsStandardList(String storeid,String orderId) {
		List<BizMaterialsStandard> list = applyStandardMaterialDao.findBizMaterialsStandardList(storeid,orderId);
		return list;
		
	}
	public BizMaterialsStandard findStandardPriceById(String id) {

		return applyStandardMaterialDao.findStandardPriceById(id);
	}
	public Integer saveMaterialsStandardReceiveBill(BizMaterialsStandardReceiveBillApply bsr) {

		return applyStandardMaterialDao.saveMaterialsStandardReceiveBill(bsr);
		 
	}
	public void insertMaterialBillVO(List<ApplyMaterialsStandardReceiveDetail> listBD) {

		applyStandardMaterialDao.insertMaterialBillVO(listBD);
	}
	public ApplyStandardMaterial findApplyStandardMaterialByOrderId(String orderId) {

		return applyStandardMaterialDao.findApplyStandardMaterialByOrderId(orderId);
		
	}
	public List<ApplyMaterialsStandardReceiveDetail> findApplyMaterialsStandardReceiveDetailById(String id) {

		List<ApplyMaterialsStandardReceiveDetail>  list = applyStandardMaterialDao.findApplyMaterialsStandardReceiveDetailById(id);
		return list;
	}
	public BizMaterialsStandardReceiveBillApply findBizMaterialsStandardReceiveBillApplyByid(String id) {

		return applyStandardMaterialDao.findBizMaterialsStandardReceiveBillApplyByid(id);
	}
	public String findStoreIdByEmployeeId(Integer managerId) {

		return applyStandardMaterialDao.findStoreIdByEmployeeId(managerId);
		 
	}
	public Seiralnum findSeiralnum(int i) {

		return applyStandardMaterialDao.findSeiralnum(i);
		 
	}
	public void saveSeiralnum(Seiralnum seiralnum) {

		applyStandardMaterialDao.saveSeiralnum(seiralnum);
	}
	public List<String> getRuleCodeNumber(String materialsStandardId){
		return applyStandardMaterialDao.getRuleCodeNumber(materialsStandardId);
	}
	public String getOrderArea(String orderId){
		return applyStandardMaterialDao.getOrderArea(orderId);
		
	}
}
