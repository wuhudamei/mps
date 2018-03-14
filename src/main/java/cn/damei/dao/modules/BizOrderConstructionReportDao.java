/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderConstructionReport;

/**
 * 大统计表-施工中
 * @author llp
 * @version 2016-11-28
 */
@MyBatisDao
public interface BizOrderConstructionReportDao extends CrudDao2<BizOrderConstructionReport>{

	List<BizOrderConstructionReport> getByStoreList(String storeID);
	
}