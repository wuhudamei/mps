/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;

/**
 * 项目经理保证金快照DAO接口
 * @author 汪文文
 * @version 2016-12-28
 */
@MyBatisDao
public interface BizPmGuaranteeMoneyCnfgSnapDao extends CrudDao2<BizPmGuaranteeMoneyCnfgSnap> {

	void save(BizPmGuaranteeMoneyCnfgSnap gmcs);

	BizPmGuaranteeMoneyCnfgSnap findGmc(Integer orderId);
	
}