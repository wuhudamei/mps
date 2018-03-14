package cn.damei.dao.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.CrudDao2;
import cn.damei.common.persistence.annotation.MyBatisDao;
import cn.damei.entity.mobile.Manager.BusinessPicture;

@MyBatisDao
public interface BusinessPictureDao extends CrudDao2<BusinessPicture>{

	List<BusinessPicture> queryPicture(Integer purchaseId, String pictureBusinessType5);

	void savePic(BusinessPicture picture);

	void deletePic(Integer id);

	List<BusinessPicture> queryByReceiveBillId(Integer id);

	List<BusinessPicture> queryOverPicture(Integer id, String pictureBusinessType104);

	List<BusinessPicture> queryPictureByBussinessIdAndType(Integer id, String type);

}
