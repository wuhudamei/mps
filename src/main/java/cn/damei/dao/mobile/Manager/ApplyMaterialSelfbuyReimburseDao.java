package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.ApplyMaterialSelfbuyReimburseStatusLog;
import cn.damei.entity.mobile.Manager.MaterialManagement;
import cn.damei.entity.modules.BizMaterialSelfbuy;
import cn.damei.entity.modules.BizMaterialSelfbuyReimburse;

/**
 * 自采材料报销
 * @author Administrator
 *
 */
@MyBatisDao
public interface ApplyMaterialSelfbuyReimburseDao {

	/**
	 * 自采材料报销订单列表
	 * @param materialManagement
	 * @return
	 */
	List<MaterialManagement> findOrderList(MaterialManagement materialManagement);

	/**
	 * 根据订单id查询订单信息
	 * @param orderId
	 * @return
	 */
	MaterialManagement findOrder(Integer orderId);

	/**
	 * 查询自采材料名称列表
	 * @param bizMaterialSelfbuy
	 * @return
	 */
	List<BizMaterialSelfbuy> findMaterialSelfbuyList(BizMaterialSelfbuy bizMaterialSelfbuy);

	/**
	 * 查询该订单5分钟内提交自采材料报销的数量
	 * @param bizMaterialSelfbuyReimburse
	 * @return
	 */
	Integer findMaterialSelfbuyReimburseCount(BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse);

	/**
	 * 动态加载自采材料报销  记录页面
	 * @param bizMaterialSelfbuyReimburse
	 * @return
	 */
	List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseRecordList(
			BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse);

	/**
	 * 最新一次的自采材料报销申请内容
	 * @param bizMaterialSelfbuyReimburse
	 * @return
	 */
	BizMaterialSelfbuyReimburse findLastTimeMaterialSelfbuyDetail(
			BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse);


	/**
	 * 根据id查询出所有的 图片（之前的）
	 * @param reportCheckDetailsPic
	 * @return
	 */
	List<ReportCheckDetailsPic> findLastPicList(ReportCheckDetailsPic reportCheckDetailsPic);

	/**
	 * 自采材料报销详情
	 * @param bizMaterialSelfbuyReimburse
	 * @return
	 */
	List<BizMaterialSelfbuyReimburse> findMaterialSelfbuyReimburseDetails(
			BizMaterialSelfbuyReimburse bizMaterialSelfbuyReimburse);

	/**
	 * 自采材料报销详情--状态日志
	 * @param applyMaterialSelfbuyReimburseStatusLog
	 * @return
	 */
	List<ApplyMaterialSelfbuyReimburseStatusLog> findMaterialStatusLogDetails(
			ApplyMaterialSelfbuyReimburseStatusLog applyMaterialSelfbuyReimburseStatusLog);




}
