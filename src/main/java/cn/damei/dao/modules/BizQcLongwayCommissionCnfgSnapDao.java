/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizQcLongwayCommissionCnfgSnap;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;

/**
 * 质检员远程费快照DAO接口
 * @author 汪文文
 * @version 2017-02-13
 */
@MyBatisDao
public interface BizQcLongwayCommissionCnfgSnapDao extends CrudDao2<BizQcLongwayCommissionCnfgSnap> {

	BizQcLongwayCommissionCnfgSnap queryByMap(Map<String, Object> map);

	BizQcLongwayCommissionCnfgSnap findBqlccsByOrderId(Integer orderId);

	void insert(BizPmSettleCategoryDetail details);
	
}