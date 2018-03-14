package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.ApplyMaterialSelfbuyReimburseStatusLog;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.entity.modules.BizMaterialSelfbuy;
import cn.damei.entity.modules.BizMaterialSelfbuyReimburse;


@MyBatisDao
public interface ApplyMaterialSelfbuyReimburseDao {


	List<MaterialManagement> findOrderList(MaterialManagement materialManagement);


	MaterialManagement findOrder(Integer orderId);


	List<BizMaterialSelfbuy> findMaterialSelfbuyList(BizMaterialSelfbuy bizMaterialSelfbuy);


	Integer findMaterialSelfbuyReimburseCount(BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse);


	List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseRecordList(
			BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse);


	BizMaterialSelfbuyReimburse findLastTimeMaterialSelfbuyDetail(
			BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse);



	List<ReportCheckDetailsPic> findLastPicList(ReportCheckDetailsPic reportCheckDetailsPic);


	List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseDetails(
			BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse);


	List<ApplyMaterialSelfbuyReimburseStatusLog> findMaterialStatusLogDetails(
			ApplyMaterialSelfbuyReimburseStatusLog applyMaterialSelfbuyReimburseStatusLog);




}
