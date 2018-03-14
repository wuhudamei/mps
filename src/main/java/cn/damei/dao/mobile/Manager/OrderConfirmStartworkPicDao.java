/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.OrderConfirmStartworkPic;

/**
 * 订单管理DAO接口
 * @author wyb
 * @version 2016-09-08
 */
@MyBatisDao
public interface OrderConfirmStartworkPicDao extends CrudDao2<OrderConfirmStartworkPic>{

	
	/**
	 * 保存开工图片
	 * @param workPic
	 * @return
	 */
	boolean insertConfirmStartworkPic(OrderConfirmStartworkPic workPic);

	/**
	 * 批量保存开工图片
	 * @param startList
	 * @return
	 */
	boolean saveConfirmStartPicList(List<OrderConfirmStartworkPic> startList);
	
}