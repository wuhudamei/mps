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


	List<InstallItem> findInstallConstructBillSignList(InstallItem installItem);


	Integer saveSignMessage(Sign sign);


	InstallItem findInstallConstructBillMessage(Integer constructBillId);


	boolean updateInstallPlan(EnginInstallNew enginInstall);


	boolean updateSupplierInstallBill(BizSupplierInstallBill bizSupplierInstallBill);


	boolean updateSupplierConstructBill(BizSupplierInstallConstructBill bizSupplierInstallConstructBill);





}
