package cn.damei.dao.modules;


import java.util.List;

import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Inspector.ReCheckCode;
import cn.damei.entity.modules.BizOrderChecksizeEntity;
import cn.damei.entity.modules.BizPrePmSettleFin;
import cn.damei.entity.modules.BizSupplierInstallBill;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;
import cn.damei.entity.modules.EnginInstallNew;
import cn.damei.entity.modules.EnginInstallSupplier;
import cn.damei.entity.modules.BizProjectInstallItem;
import cn.damei.entity.modules.BizSupplier;

/**
 * 主材安装申请 处理
 */
@MyBatisDao
public interface EnginInstallNewDealDao {

	/**
	 *  更新状态--下达供应商
	 * @param enginInstall
	 * @return
	 */
	boolean updateSupplier(EnginInstallNew enginInstall);

	/**
	 * 更新状态    驳回
	 * @param enginInstall
	 * @return
	 */
	boolean updateReject(EnginInstallNew enginInstall);

	/**
	 * 查询安装说明
	 * @param installId
	 * @return
	 */
	BizProjectInstallItem installExplain(Integer installId);

	/**
	 * 查询复尺内容
	 * @param bizOrderChecksize
	 * @return
	 */
	BizOrderChecksizeEntity findCheckSize(BizOrderChecksizeEntity bizOrderChecksize);

	/**
	 * 查询二期款
	 * @param orderId
	 * @return
	 */
	BizPrePmSettleFin findSecondPayment(Integer orderId);

	/**
	 * 查询该安装项的供应商列表
	 * @param installId
	 * @return
	 */
	List<EnginInstallSupplier> findSupplierList(Integer installId);

	/**
	 * 查询供应商信息
	 * @param supplierId
	 * @return
	 */
	BizSupplier findSupplierMessage(Integer supplierId);
    
	
	/**
	 * 查询code
	 * @param supplierInstallBillCode
	 * @return
	 */
	ReCheckCode getCode(String supplierInstallBillCode);

	/**
	 * 保存code
	 * @param reCheckCode
	 */
	void saveCode(ReCheckCode reCheckCode);

	/**
	 * 跟新code
	 * @param code1
	 */
	void updateCode(ReCheckCode code1);

	/**
	 * 查询最新一次的安装单及施工单
	 * @param installId
	 * @return
	 */
	BizSupplierInstallBill findInstallBillAndConstructBill(Integer installId);

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
	boolean updateSupplierInstallConstructBill(BizSupplierInstallConstructBill bizSupplierInstallConstructBill);

	

}