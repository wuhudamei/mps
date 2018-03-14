/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizMaterialsChoiceBill;
import cn.damei.entity.modules.BizOrderMainMate;

/**
 * 主材订单DAO接口
 * 
 * @author qww
 * @version 2016-10-09
 */
@MyBatisDao
public interface BizOrderMainMateDao extends CrudDao2<BizOrderMainMate> {

	/**
	 * 根据orderId真删除
	 * 
	 * @param orderId
	 * @return
	 */
	public int deleteByOrderId(int orderId);

	public void savebyid(BizOrderMainMate bizOrderMainMate);

	public void updateuni(BizOrderMainMate bizOrderMainMate);

	public BizMaterialsChoiceBill ismaterialschoicebill(String string);

	public List<BizOrderMainMate> findListOne(BizOrderMainMate bizOrderMainMate);

	public int insertpurchaseCount(int parseInt);
}