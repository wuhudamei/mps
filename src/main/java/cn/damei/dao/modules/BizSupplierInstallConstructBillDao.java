/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSupplierInstallConstructBill;

/**
 * 供应商安装施工单表DAO接口
 * 
 * @author wyb
 * @version 2017-07-14
 */
@MyBatisDao
public interface BizSupplierInstallConstructBillDao extends CrudDao2<BizSupplierInstallConstructBill> {

	BizSupplierInstallConstructBill getnot90(BizSupplierInstallConstructBill bizSupplierInstallConstructBillb);

}