/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcStarCommissionCnfgSnap;

/**
 * 质检员星级提成快照DAO接口
 * @author 汪文文
 * @version 2017-02-13
 */
@MyBatisDao
public interface BizQcStarCommissionCnfgSnapDao extends CrudDao2<BizQcStarCommissionCnfgSnap> {

	BizQcStarCommissionCnfgSnap queryByMap(Map<String, Object> map);

	BizQcStarCommissionCnfgSnap findBqsccsByOrderId(Integer orderId);
	
}