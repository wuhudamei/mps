/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizSynData;

/**
 * 同步数据DAO接口
 * @author 汪文
 * @version 2017-03-15
 */
@MyBatisDao
public interface BizSynDataDao extends CrudDao2<BizSynData> {
	
	public List<BizSynData> findPrePmSettleFinList(BizSynData bizSynData);
	
}