/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.modules;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.modules.BizOrderConfirmStartworkPic;

/**
 * 订单管理DAO接口
 * @author wyb
 * @version 2016-09-08
 */
@MyBatisDao
public interface BizOrderConfirmStartworkPicDao extends CrudDao2<BizOrderConfirmStartworkPic>{

	List<BizOrderConfirmStartworkPic> getByConfirmStartWorkID(Integer startWorkID);

}