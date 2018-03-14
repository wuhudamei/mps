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


@MyBatisDao
public interface EnginInstallNewDealDao {


	boolean updateSupplier(EnginInstallNew enginInstall);


	boolean updateReject(EnginInstallNew enginInstall);


	BizProjectInstallItem installExplain(Integer installId);


	BizOrderChecksizeEntity findCheckSize(BizOrderChecksizeEntity bizOrderChecksize);


	BizPrePmSettleFin findSecondPayment(Integer orderId);


	List<EnginInstallSupplier> findSupplierList(Integer installId);


	BizSupplier findSupplierMessage(Integer supplierId);
    
	

	ReCheckCode getCode(String supplierInstallBillCode);


	void saveCode(ReCheckCode reCheckCode);


	void updateCode(ReCheckCode code1);


	BizSupplierInstallBill findInstallBillAndConstructBill(Integer installId);


	boolean updateSupplierInstallBill(BizSupplierInstallBill bizSupplierInstallBill);


	boolean updateSupplierInstallConstructBill(BizSupplierInstallConstructBill bizSupplierInstallConstructBill);

	

}