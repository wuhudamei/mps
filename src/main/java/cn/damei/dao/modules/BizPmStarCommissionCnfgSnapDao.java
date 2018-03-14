/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;

/**
 * 项目经理星级提成快照DAO接口
 * @author 汪文文
 * @version 2016-12-28
 */
@MyBatisDao
public interface BizPmStarCommissionCnfgSnapDao extends CrudDao2<BizPmStarCommissionCnfgSnap> {

	List<BizPmStarCommissionCnfgSnap> queryByMap(Map<String, Object> map);

	BizPmStarCommissionCnfgSnap findSccs(Integer orderId);
	
}