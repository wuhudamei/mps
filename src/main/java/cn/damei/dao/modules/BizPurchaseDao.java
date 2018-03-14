/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Date;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPurchase;

/**
 * 采购单DAO接口
 * @author 汪文文
 * @version 2016-09-28
 */
@MyBatisDao
public interface BizPurchaseDao extends CrudDao2<BizPurchase> {

	void updateStatusById(Integer id);

	void updateStatus1ById(int id, String status,Date date);

	void updateStatus2ById(int parseInt, String status);

	/**
	 * 保存开关面板废弃原因
	 * @param bizPurchase
	 * @return
	 */
	boolean updateMainPanelStatus(BizPurchase bizPurchase);

	/**
	 * 判断项目经理的中期结算单是否已存在
	 * @param orderId
	 * @return
	 */
	Integer findSettlementIsExist(Integer orderId);
	
}