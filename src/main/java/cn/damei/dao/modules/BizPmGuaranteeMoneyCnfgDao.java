/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.Map;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfg;

/**
 * 项目经理质保金设置DAO接口
 * @author wyb
 * @version 2016-12-26
 */
@MyBatisDao
public interface BizPmGuaranteeMoneyCnfgDao extends CrudDao2<BizPmGuaranteeMoneyCnfg> {

	BizPmGuaranteeMoneyCnfg queryByStoreIdAndProjectModel(Map<String, Object> map);
	
}