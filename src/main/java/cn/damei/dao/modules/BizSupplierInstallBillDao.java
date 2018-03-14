/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.entity.modules.SupplierInstallWorker;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;
import cn.damei.entity.modules.EnginInstallNew;
import cn.damei.entity.modules.EnginInstallSupplier;
import cn.damei.entity.modules.BizProjectInstallItem;

/**
 * 供应商安装单表DAO接口
 * 
 * @author wyb
 * @version 2017-07-13
 */
@MyBatisDao
public interface BizSupplierInstallBillDao extends CrudDao2<BizSupplierInstallBill> {

	/**
	 * 查询供应商列表
	 * 
	 * @param enginInstallSupplier
	 * @return
	 */
	List<EnginInstallSupplier> findSupplierList(EnginInstallSupplier enginInstallSupplier);

	/**
	 * 根据供应商加载安装项列表
	 * 
	 * @param supplierId
	 * @return
	 */
	List<BizProjectInstallItem> findProjectInstallItemList(Integer supplierId);

	/**
	 * 查询工人组
	 * 
	 * @param supplierInstallWorker
	 * @return
	 */
	List<SupplierInstallWorker> findInstallWorkerList(SupplierInstallWorker supplierInstallWorker);

	/**
	 * 更新安装单--确认工期
	 * 
	 * @param bizSupplierInstallBill
	 * @return
	 */
	boolean updateSupplierConfirmDate(BizSupplierInstallBill bizSupplierInstallBill);

	/**
	 * 查询安装单的相关信息
	 * 
	 * @param installBillId
	 * @return
	 */
	BizSupplierInstallBill findInstallBillDetails(Integer installBillId);

	/**
	 * 更新安装项--分派工人组
	 * 
	 * @param enginInstall
	 * @return
	 */
	boolean updateSupplier(EnginInstallNew enginInstall);

	/**
	 * 根据工人组id查询工人组长信息
	 * 
	 * @param employeegroupId
	 * @return
	 */
	SupplierInstallWorker findWorkerMessage(Integer employeegroupId);

	/**
	 * 查询工人组施工单信息
	 * 
	 * @param installConstructBillId
	 * @return
	 */
	BizSupplierInstallConstructBill findEmployeeGroupMessage(Integer installConstructBillId);

	BizSupplierInstallBill getnot90(BizSupplierInstallBill entity);

}