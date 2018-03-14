
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;


@MyBatisDao
public interface BizSupplierInstallConstructBillDao extends CrudDao2<BizSupplierInstallConstructBill> {

	BizSupplierInstallConstructBill getnot90(BizSupplierInstallConstructBill bizSupplierInstallConstructBillb);

}