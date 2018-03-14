/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
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

/**
 * 结算员审核验收单DAO接口
 * @author wyb
 * @version 2016-10-31
 */
@MyBatisDao
public interface InspectorConfirmDao extends CrudDao<InspectorConfirm> {

	//查看验收图片
	List<ReportCheckDetailsPic> findPic(ReportCheckDetailsPic reportCheckDetailsPic);

	//更新约检验收单
	void updateQcBill(InspectorConfirm inspectorConfirm);
	
	//插入结算类目明细
	void saveDetail(PmSettleCategoryDetail details);

	//从biz_pm_star_commission_cnfg_snap(项目经理结算比例快照信息)中获取数据，通过订单id
	BizPmStarCommissionCnfgSnap findFirst(Integer orderId);

	//从biz_pm_settle_category_detail(质检罚款明细)中获取数据
	List<PmSettleCategoryDetail> findSecond(PmSettleCategoryDetail qcBreak);

	//更新biz_pm_settle_category_detail中（质检罚款明细）
	void updateDetail(PmSettleCategoryDetail inspectorCheck);

	//从biz_materials_standard_receive_bill(标化辅料录入表)中获取数据，通过订单id
	List<BizMaterialsStandardReceiveBill> findThree(Integer orderId);

	//更新biz_materials_standard_receive_bill中状态
	void updateMaterials(BizMaterialsStandardReceiveBill details3);
	
	//从biz_pm_ownpay_cnfg_snap(自主支配快照信息)中获取数据，通过订单id
	List<BizPmOwnpayCnfgSnap> findFour(Integer orderId);

	//批量插入结算类目明细
	void saveDetailAll(List<PmSettleCategoryDetail> list4);

	//批量更新中期罚款biz_pm_settle_category_detail中（质检罚款明细）
	void updateDetailAll(PmSettleCategoryDetail qcBreak);

	//从biz_qc_longway_commission_cnfg_snap(质检员远程费提成快照信息)中获取数据，通过订单id
	BizQcLongwayCommissionCnfgSnap findFive(Integer orderId);

	//从biz_qc_star_commission_cnfg_snap(质检员星级配置快照信息)中获取数据，通过订单id
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