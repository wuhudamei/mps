
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.entity.modules.BizQcLongwayCommissionCnfgSnap;
import cn.damei.entity.modules.BizQcStarCommissionCnfgSnap;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBill;
import cn.damei.entity.modules.BizPmOwnpayCnfgSnap;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;
import cn.damei.entity.modules.InspectorConfirm;
import cn.damei.entity.modules.PmSettleCategoryDetail;
import cn.damei.entity.modules.QcBillCheckItemInfo;


@MyBatisDao
public interface InspectorConfirmDao extends CrudDao<InspectorConfirm> {


	List<ReportCheckDetailsPic> findPic(ReportCheckDetailsPic reportCheckDetailsPic);


	void updateQcBill(InspectorConfirm inspectorConfirm);
	

	void saveDetail(PmSettleCategoryDetail details);


	BizPmStarCommissionCnfgSnap findFirst(Integer orderId);


	List<PmSettleCategoryDetail> findSecond(PmSettleCategoryDetail qcBreak);


	void updateDetail(PmSettleCategoryDetail inspectorCheck);


	List<BizMaterialsStandardReceiveBill> findThree(Integer orderId);


	void updateMaterials(BizMaterialsStandardReceiveBill details3);
	

	List<BizPmOwnpayCnfgSnap> findFour(Integer orderId);


	void saveDetailAll(List<PmSettleCategoryDetail> list4);


	void updateDetailAll(PmSettleCategoryDetail qcBreak);


	BizQcLongwayCommissionCnfgSnap findFive(Integer orderId);


	BizQcStarCommissionCnfgSnap findSix(Integer orderId);

	InspectorConfirm findById(Integer qcBillId);
	
	BizPmStarCommissionCnfgSnap queryManagerCommissionByOrderId(Integer orderId);
	
	BizPmStarCommissionCnfgSnap queryManagerCommissionByParam(Map<String,Object> param);
	
	double queryManagerOwnpay(Integer orderId);
	
	double queryManangerPenalty(Map<String,Object> map);
	
	List<BizOrderMaterialsStandard> queryMaterialsStandardByOrderId(Integer orderId);
	
	List<BizPmOwnpayCnfgSnap> queryPmOwnpayCnfgSnapByOrderId(Integer orderId);
	
	List<QcBillCheckItemInfo> queryPmPunishByParam(Map<String,Object> param);
	
	int checkQcCheck(Map<String,Object> param);
}