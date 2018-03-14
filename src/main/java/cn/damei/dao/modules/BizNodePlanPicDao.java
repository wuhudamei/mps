/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizNodePlanPic;

/**
 * llpDAO接口
 * @author llp
 * @version 2016-10-11
 */
@MyBatisDao
public interface BizNodePlanPicDao extends CrudDao2<BizNodePlanPic> {

	List<BizNodePlanPic> getByNodePlanId(Integer nodeId);
	
}