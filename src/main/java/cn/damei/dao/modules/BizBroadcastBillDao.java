/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizBroadcastBill;

/**
 * 播报信息类DAO接口
 * @author meihao
 * @version 2017-01-10
 */
@MyBatisDao
public interface BizBroadcastBillDao extends CrudDao<BizBroadcastBill> {
	
	
	
	public List<String> findPackageTempleteList();
	public List<BizBroadcastBill>findBroadCastInfoAndPic(Integer broadcastId);
}