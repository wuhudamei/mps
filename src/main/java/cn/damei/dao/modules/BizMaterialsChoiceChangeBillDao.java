/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsChoiceChangeBill;

/**
 * 选材变更单表DAO接口
 * @author wyb
 * @version 2017-06-14
 */
@MyBatisDao
public interface BizMaterialsChoiceChangeBillDao extends CrudDao2<BizMaterialsChoiceChangeBill> {

	/**
	 * 删除选材变更单
	 * @param id
	 */
	void deleteMaterialsChoiceChangeBill(Integer id);

	/**
	 * 根据订单编号查询该订单下所有的变更单
	 * @param orderNumber
	 * @return
	 */
	List<BizMaterialsChoiceChangeBill> findChangeBillMessage(String orderNumber);
	
}