
package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BusinessPic;


@MyBatisDao
public interface BusinessPicDao extends CrudDao2<BusinessPic>{

	void insertPic(BusinessPic pic);

	List<BusinessPic> queryByBusinessID(Integer businessID);

	boolean insertPhotos(BusinessPic pic);

	List<BusinessPic> getByBusType(String businessType, Integer businessID);

}