/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BusinessPic;

/**
 * 公共图片上传
 * @author llp
 * @version 2016-09-08
 */
@MyBatisDao
public interface BusinessPicDao extends CrudDao2<BusinessPic>{

	void insertPic(BusinessPic pic);

	List<BusinessPic> queryByBusinessID(Integer businessID);

	boolean insertPhotos(BusinessPic pic);

	List<BusinessPic> getByBusType(String businessType, Integer businessID);

}