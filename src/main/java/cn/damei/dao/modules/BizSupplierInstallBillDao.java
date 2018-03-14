
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


@MyBatisDao
public interface BizSupplierInstallBillDao extends CrudDao2<BizSupplierInstallBill> {


	List<EnginInstallSupplier> findSupplierList(EnginInstallSupplier enginInstallSupplier);


	List<BizProjectInstallItem> findProjectInstallItemList(Integer supplierId);


	List<SupplierInstallWorker> findInstallWorkerList(SupplierInstallWorker supplierInstallWorker);


	boolean updateSupplierConfirmDate(BizSupplierInstallBill bizSupplierInstallBill);


	BizSupplierInstallBill findInstallBillDetails(Integer installBillId);


	boolean updateSupplier(EnginInstallNew enginInstall);


	SupplierInstallWorker findWorkerMessage(Integer employeegroupId);


	BizSupplierInstallConstructBill findEmployeeGroupMessage(Integer installConstructBillId);

	BizSupplierInstallBill getnot90(BizSupplierInstallBill entity);

}