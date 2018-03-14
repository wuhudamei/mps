/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizProjectInstallItem;

/**
 * 工程安装项DAO接口
 * 
 * @author 梅浩
 * @version 2016-09-01
 */
@MyBatisDao
public interface BizProjectInstallItemDao extends CrudDao<BizProjectInstallItem> {

	// 修改状态
	public void isON(BizProjectInstallItem installItem);

	public List<BizProjectInstallItem> queryInstallItemList(BizProjectInstallItem bizProjectInstallItem);
}