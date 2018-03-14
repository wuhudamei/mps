package cn.damei.dao.mobile.Worker;


import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Worker.InstallItem;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;
import cn.damei.entity.modules.EnginInstallNew;

@MyBatisDao
public interface InstallApplyForCompletionDao{

	/**
	 * 查询施工单列表（完工）
	 * @param installItem
	 * @return
	 */
	List<InstallItem> findInstallConstructBillApplyForCompletionList(InstallItem installItem);


	/**
	 * 查询施工单信息（完工）
	 * @param constructBillId
	 * @return
	 */
	InstallItem findInstallConstructBillMessage(Integer constructBillId);


	/**
	 * 批量插入图片
	 * @param pList
	 * @return
	 */
	boolean savePicAll(List<ReportCheckDetailsPic> pList);

	/**
	 * 更新安装项计划
	 * @param enginInstall
	 * @return
	 */
	boolean updateInstallPlan(EnginInstallNew enginInstall);

	/**
	 * 更新安装单
	 * @param bizSupplierInstallBill
	 * @return
	 */
	boolean updateSupplierInstallBill(BizSupplierInstallBill bizSupplierInstallBill);

	/**
	 * 更新施工单
	 * @param bizSupplierInstallConstructBill
	 * @return
	 */
	boolean updateSupplierConstructBill(BizSupplierInstallConstructBill bizSupplierInstallConstructBill);





}
