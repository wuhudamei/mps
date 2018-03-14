/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;
import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmOwnpayCnfgSnap;

/**
 * 自主支配快照DAO接口
 * @author 汪文文
 * @version 2016-12-28
 */
@MyBatisDao
public interface BizPmOwnpayCnfgSnapDao extends CrudDao2<BizPmOwnpayCnfgSnap> {

	List<BizPmOwnpayCnfgSnap> findListByMap(Map<String, Object> map2);

	void deleteByOrderId(Integer id);

	List<BizPmOwnpayCnfgSnap> findByOrderId(Integer id);

	void insertList(List<BizPmOwnpayCnfgSnap> list);
	
	
}