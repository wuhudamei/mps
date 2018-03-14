/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderScrap;
import cn.damei.entity.modules.PublicPic;

/**
 * 订单作废DAO接口
 */
@MyBatisDao
public interface BizOrderScrapDao extends CrudDao2<BizOrderScrap> {

	/**
	 * 订单回收页面
	 * 
	 * @param bizOrderScrap
	 * @return
	 */
	List<BizOrderScrap> findRecoveryList(BizOrderScrap bizOrderScrap);

	void scrapUpdate(BizOrderScrap bizOrderScrap);

	void savePicAll(List<PublicPic> pList);
	void updateOrderNumber(BizOrderScrap bizOrderScrap);
}