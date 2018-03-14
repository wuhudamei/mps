package cn.damei.dao.mobile.Worker;


import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.Sign;
import cn.damei.entity.mobile.Worker.InstallItem;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;
import cn.damei.entity.modules.EnginInstallNew;

@MyBatisDao
public interface InstallSignDao{

	/**
	 * 查询施工单列表（签到）
	 * @param installItem
	 * @return
	 */
	List<InstallItem> findInstallConstructBillSignList(InstallItem installItem);

	/**
	 * 保存签到信息
	 * @param sign
	 * @return
	 */
	Integer saveSignMessage(Sign sign);

	/**
	 * 查询施工单信息（签到）
	 * @param constructBillId
	 * @return
	 */
	InstallItem findInstallConstructBillMessage(Integer constructBillId);

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
